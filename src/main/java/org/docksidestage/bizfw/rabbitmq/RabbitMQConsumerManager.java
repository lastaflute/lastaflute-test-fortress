/*
 * Copyright 2015-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.rabbitmq;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.dbflute.util.DfAssertUtil;
import org.docksidestage.bizfw.rabbitmq.job.RabbitJobResource;
import org.docksidestage.bizfw.rabbitmq.job.exception.RabbitJobChannelAckException;
import org.docksidestage.bizfw.rabbitmq.job.exception.RabbitJobChannelRejectException;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.bridge.AsyncStateBridge;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.job.subsidiary.LaunchedProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

/**
 * RabbitMQ の Consumer の登録/接続/切断、そして、Jobへの連携を管理するクラス。
 * 
 * <p>別スレッドで queue ごとに basicConsume() を実行してポーリングする。
 * メッセージを受け取ったら、queue ごとに対応する LastaJob の Job を実行する。
 * その Job は、AllJobScheduler で nonCron で登録しておく。</p>
 * 
 * @author jflute
 */
public class RabbitMQConsumerManager { // #rabbit

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumerManager.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    protected AsyncManager asyncManager;
    @Resource
    protected JobManager jobManager;

    // -----------------------------------------------------
    //                                        Thread Control
    //                                        --------------
    // singletonのDIコンポーネント前提で保持する。スレッド間で利用されるのでスレッドセーフに。
    /** consumerのplling用のLatch。(NotNull) */
    protected final List<ConsumerPollingLatch> pollingLatchList = new CopyOnWriteArrayList<>();

    /** アプリ停止時に最後のdelivery実行終了を待つためのLatch。(NotNull) */
    protected final List<ConsumerRunningLatch> runningLatchList = new CopyOnWriteArrayList<>();

    /** アプリ停止時にconsumerの接続が全部closeされたことを待つためのLatch。(NotNull) */
    protected final List<ConsumerTerminatingLatch> terminatingLatchList = new CopyOnWriteArrayList<>();

    // -----------------------------------------------------
    //                                    Destroying Control
    //                                    ------------------
    /** アプリ停止要求が来たかどうか？ */
    protected volatile boolean destroyingRequested; // スレッド間で参照するのでスレッドセーフに

    // ===================================================================================
    //                                                                               Boot
    //                                                                              ======
    /**
     * 一つの Consumer を非同期で起動する。<br>
     * 新しいスレッドで起動させるので、このメソッド自体はすぐに戻って来る。
     * @param queueName 起動するConsumerが対応するキューの名前 (NotNull)
     * @param jobUnique 対応する LastaJob の Job を特定するためのユニークオブジェクト (NotNull)
     * @param connectionFactoryProvider このbootで使うMQのConnectionFactoryを提供するコールバック (NotNull)
     */
    public synchronized void asyncBoot(String queueName, LaJobUnique jobUnique, MQConnectionFactoryProvider connectionFactoryProvider) {
        DfAssertUtil.assertStringNotNullAndNotTrimmedEmpty("queueName", queueName);
        DfAssertUtil.assertObjectNotNull("jobUnique", jobUnique);
        DfAssertUtil.assertObjectNotNull("connectionFactoryProvider", connectionFactoryProvider);

        // 自前ポーリング用のラッチ (インスタンス変数登録するので献上スレッド外(前)で呼ぶこと)
        ConsumerPollingLatch pollingLatch = preparePollingLatch(queueName);

        Runnable bootRunner = () -> { // consumer登録後のポーリングに献上するスレッドなのでその場new
            try {
                doBoot(queueName, jobUnique, connectionFactoryProvider, pollingLatch);
            } catch (Throwable e) {
                // ずっと止まる想定のスレッドにContextなどを保持させ続けるのも気持ち悪いから、
                // AsyncManagerのcross()は使わずに自前でエラーハンドリングするようにした。
                // (ここではContext類は要らないはずなので)
                logger.error("Failed to boot the RabbitMQ consumer: " + queueName, e);
            }
        };
        createPollingThread(bootRunner, queueName).start();
    }

    protected Thread createPollingThread(Runnable bootRunner, String queueName) {
        Thread thread = new Thread(bootRunner);
        switchThreadName(queueName, thread);
        return thread;
    }

    protected void switchThreadName(String queueName, Thread thread) {
        String newName = queueName + "_" + thread.getName(); // e.g. seaQueue_Thread_1
        thread.setName(newName);
    }

    // -----------------------------------------------------
    //                                     in Polling Thread
    //                                     -----------------
    protected void doBoot(String queueName, LaJobUnique jobUnique, MQConnectionFactoryProvider connectionFactoryProvider,
            ConsumerPollingLatch pollingLatch) { // works in polling thread
        ConsumerTerminatingLatch terminatingLatch = new ConsumerTerminatingLatch(queueName);
        RunningLatchContainer runningLatchContainer = new RunningLatchContainer();
        ConnectionFactory connectionFactory = connectionFactoryProvider.provide();
        try (Connection conn = connectionFactory.newConnection(); Channel channel = conn.createChannel()) {
            terminatingLatchList.add(terminatingLatch); // destroy()でclose待ちしてもらうため

            basicQos(queueName, channel); // quality of service
            queueDeclare(queueName, channel); // まずはキューを宣言
            basicConsume(queueName, jobUnique, channel, runningLatchContainer); // 登録だけでポーリングするわけではない
            awaitConsumer(pollingLatch); // ここで自前ポーリング、アプリ停止時に解放されてConnectionのclose()が動く
            awaitRunning(runningLatchContainer); // ここに来たということはアプリ停止なので実行中のdeliveryが終わるのを待つ

            logger.info("...Closing consumer connection and channel for the queue: {}", queueName);
        } catch (IOException | TimeoutException e) {
            String msg = "Failed to consume message queue: " + queueName + ", " + jobUnique;
            throw new IllegalStateException(msg, e);
        } finally {
            logger.info("Closed consumer connection and channel for the queue: {}", queueName);
            terminatingLatch.countDown(); // ポーリング終わってconnection解放したよと言うため
        }
    }

    // ===================================================================================
    //                                                                    Polling Consumer
    //                                                                    ================
    // -----------------------------------------------------
    //                                    Quality of Service
    //                                    ------------------
    protected void basicQos(String queueName, Channel channel) throws IOException {
        OptionalThing<Integer> optPrefetchCount = getQosOptionPrefetchCount();
        if (optPrefetchCount.isPresent()) { // チェック例外あるのでis方式
            channel.basicQos(optPrefetchCount.get());
        }
    }

    // #genba_fitting prefetchCount これでいいのか？恐らく現場で要調整 (インフラでスケールするか？など) by jflute (2025/01/16)
    protected OptionalThing<Integer> getQosOptionPrefetchCount() { // no set if empty
        return OptionalThing.of(1); // as default
    }

    // -----------------------------------------------------
    //                                               Declare
    //                                               -------
    // #genba_fitting そもそも現場の運用的にqueueDeclare()が必要かどうか？必要でなければ削除。 by jflute (2025/01/22)
    protected void queueDeclare(String queueName, Channel channel) throws IOException {
        boolean durable = isDeclarationOptionDurable();
        boolean exclusive = isDeclarationOptionExclusive();
        boolean autoDelete = isDeclarationOptionAutoDelete();
        Map<String, Object> arguments = getDeclarationOptionArguments();
        channel.queueDeclare(queueName, durable, exclusive, autoDelete, arguments);
    }

    // #genba_fitting queueDeclare()のオプション引数たちこれでいいのか？恐らく現場で要調整 by jflute (2025/01/16)
    protected boolean isDeclarationOptionDurable() {
        return true; // as default
    }

    protected boolean isDeclarationOptionExclusive() {
        return false; // as default
    }

    protected boolean isDeclarationOptionAutoDelete() {
        return false; // as default
    }

    protected Map<String, Object> getDeclarationOptionArguments() {
        return null; // as default
    }

    // -----------------------------------------------------
    //                                               Consume
    //                                               -------
    protected void basicConsume(String queueName, LaJobUnique jobUnique, Channel channel, RunningLatchContainer runningLatchContainer)
            throws IOException {
        boolean autoAck = isConsumerOptionAutoAck();
        DeliverCallback deliverCallback = createDeliverCallback(queueName, jobUnique, channel, runningLatchContainer);
        CancelCallback cancelCallback = createCancelCallback(queueName, jobUnique, channel);

        logger.info("#mq ...Registering MQ consumer: {} to {}", queueName, jobUnique);
        channel.basicConsume(queueName, autoAck, deliverCallback, cancelCallback);
    }

    // #genba_fitting basicConsume()のオプション引数autoAckを使うかどうかは現場次第 by jflute (2025/01/16)
    protected boolean isConsumerOptionAutoAck() {
        return false; // as default
    }

    // -----------------------------------------------------
    //                                       DeliverCallback
    //                                       ---------------
    protected DeliverCallback createDeliverCallback(String queueName, LaJobUnique jobUnique, Channel channel,
            RunningLatchContainer runningLatchContainer) {
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        return (consumerTag, delivery) -> { // ここはまた別スレッド、1consumerで同時に1thread前提
            bridge.cross(() -> { // launch自体の処理の例外ハンドリングなどをいい感じにするために
                // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
                // アプリ停止要求時に接続closeが最後のdeliveryの処理が完了するのを待つためのlatch
                // _/_/_/_/_/_/_/_/_/_/
                ConsumerRunningLatch runningLatch = new ConsumerRunningLatch(queueName);
                runningLatchContainer.setup(runningLatch);
                try {
                    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
                    // アプリ停止要求時の接続closeすれ違いによってdelivery動いちゃったときにreject
                    // o polling側がclose直前で実行中deliveryの終了を待ってcloseしようとした瞬間にまた次のdeliveryに入りこまれたとき
                    // o polling側がcloseした直後の瞬間にまた次のdeliveryに入りこまれたとき (このときは接続切れてるのでrejectもできない)
                    //
                    // 補足: 接続closeの実行中delivery待ちよりも前に destroyingRequested は true になる
                    // _/_/_/_/_/_/_/_/_/_/
                    // #hope jflute 本当は新規メッセージの受付を止めるってのができたらこれやらなくてもいいのだけど... (2025/01/28)
                    if (destroyingRequested) { // delivery動いちゃったけど、すでにdestroy要求が来ているとき (closeすれ違いなど)
                        rejectOnDestroyingIfPossible(queueName, channel, delivery); // できたらrejectする
                        return; // 仮にrejectうまくいってなかったとしても、どうにもならないのでおしまい
                    }

                    // _/_/_/_/_/_/_/_/_/_/
                    // ようやっと本処理ここ
                    // _/_/_/_/
                    doDeliver(queueName, jobUnique, consumerTag, delivery, channel);
                } finally {
                    runningLatch.countDown(); // 終わったからアプリ停止のために接続closeしてもいいよと言うため
                    runningLatchContainer.clear();
                }
            });
        };
    }

    protected void rejectOnDestroyingIfPossible(String queueName, Channel channel, Delivery delivery) {
        long deliveryTag = delivery.getEnvelope().getDeliveryTag();
        try {
            boolean requeue = true; // アプリ停止のcloseすれ違いで実行できなかったので次回に動いてねと
            channel.basicReject(deliveryTag, requeue);
        } catch (IOException continued) { // このときはすでに接続close()終わってたら例外、どうにもできないのでログ残して終わり
            logger.warn("#mq Failed to basicReject() in destroy-requested state: {}", queueName, continued);
        }
    }

    protected void doDeliver(String queueName, LaJobUnique jobUnique, String consumerTag, Delivery delivery, Channel channel) {
        if (logger.isDebugEnabled()) {
            logger.debug("#mq ...Delivering the queue request: {}, {}", queueName, jobUnique);
        }
        String messageText = extractMessageText(delivery);
        launchRabbitJob(queueName, jobUnique, consumerTag, messageText, delivery, channel);
    }

    protected String extractMessageText(Delivery delivery) {
        return new String(delivery.getBody(), getMessageTextCharset());
    }

    protected Charset getMessageTextCharset() {
        return StandardCharsets.UTF_8;
    }

    // -----------------------------------------------------
    //                                        CancelCallback
    //                                        --------------
    // #genba_fitting cancel時の処理はこれでいいのか？恐らく現場で要調整 by jflute (2025/01/19)
    protected CancelCallback createCancelCallback(String queueName, LaJobUnique jobUnique, Channel channel) {
        return consumerTag -> {
            logger.info("The queue consuming was cancelled: {}, {}", queueName, jobUnique);
        };
    }

    // -----------------------------------------------------
    //                                                 Await
    //                                                 -----
    protected void awaitConsumer(ConsumerPollingLatch pollingLatch) {
        try {
            // ここで止まってポーリング状態に入り、try-with-resources の Connection は開いたままでConsumerが利用する。
            // アプリ停止の destroy() で countDown されることで、処理が続行されて try-with-resources の close処理が実行される。
            pollingLatch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted the consumer wait: pollingLatch=" + pollingLatch, e);
        }
    }

    protected void awaitRunning(RunningLatchContainer runningLatchContainer) {
        ConsumerRunningLatch runningLatch = runningLatchContainer.getRunningLatch();
        if (runningLatch != null) { // 実行中のdeliveryがあれば
            try {
                runningLatch.await(); // 終わるまで待つ (delivery終わるときにcountDown()してもらう想定)
            } catch (InterruptedException continued) { // もうアプリ停止の処理なので
                logger.info("#mq Failed to await running latch: {}", runningLatch, continued);
            }
        }
    }

    // ===================================================================================
    //                                                                      Thread Control
    //                                                                      ==============
    // -----------------------------------------------------
    //                                         Polling Latch
    //                                         -------------
    protected ConsumerPollingLatch preparePollingLatch(String queueName) {
        ConsumerPollingLatch pollingLatch = createConsumberPollingLatch(queueName);
        pollingLatchList.add(pollingLatch); // アプリ停止時に開放するようにする想定で登録
        return pollingLatch;
    }

    protected ConsumerPollingLatch createConsumberPollingLatch(String queueName) {
        return new ConsumerPollingLatch(queueName);
    }

    public static class ConsumerPollingLatch extends CountDownLatch { // デバッグ用にキューの名前を持たせておくため

        private final String queueName; // not null

        public ConsumerPollingLatch(String queueName) {
            super(/*count*/1); // close()で一回countDownされて解放されることを想定して固定数
            this.queueName = queueName;
        }

        @Override
        public String toString() { // キュー名を表示できるように
            return "pollingLatch:{" + getCount() + ", " + queueName + "}";
        }

        public String getQueueName() {
            return queueName;
        }
    }

    // -----------------------------------------------------
    //                                         Running Latch
    //                                         -------------
    // 1consumerで同時に1thread前提の作りをしている。万が一複数threadになったら、listで持つ？
    public static class RunningLatchContainer {

        private volatile ConsumerRunningLatch runningLatch; // スレッド間で共有するため, null allowed

        public ConsumerRunningLatch getRunningLatch() { // null allowed
            return runningLatch;
        }

        public synchronized void setup(ConsumerRunningLatch runningLatch) {
            DfAssertUtil.assertObjectNotNull("runningLatch", runningLatch);
            this.runningLatch = runningLatch;
        }

        public synchronized void clear() {
            this.runningLatch = null;
        }
    }

    public static class ConsumerRunningLatch extends CountDownLatch { // 型間違いをしないためにも別クラスで作る

        private final String queueName; // not null

        public ConsumerRunningLatch(String queueName) {
            super(/*count*/1); // 一つのdeliverの終了で一回countDownされて接続closeされる想定で固定数
            this.queueName = queueName;
        }

        @Override
        public String toString() { // キュー名を表示できるように
            return "runningLatch:{" + getCount() + ", " + queueName + "}";
        }

        public String getQueueName() {
            return queueName;
        }
    }

    // -----------------------------------------------------
    //                                     Terminating Latch
    //                                     -----------------
    public static class ConsumerTerminatingLatch extends CountDownLatch { // こっちも型間違いで別クラスに

        private final String queueName; // not null

        public ConsumerTerminatingLatch(String queueName) {
            super(/*count*/1); // 接続closeで一回countDownされて解放されることを想定して固定数
            this.queueName = queueName;
        }

        @Override
        public String toString() { // キュー名を表示できるように
            return "terminatingLatch:{" + getCount() + ", " + queueName + "}";
        }

        public String getQueueName() {
            return queueName;
        }
    }

    // ===================================================================================
    //                                                                           Lasta Job
    //                                                                           =========
    // #genba_fitting 業務処理の途中で channel を使って細かく制御したい場合は、Job方式だとちょっとやりづらいので... by jflute (2025/01/20)
    // その場合はJob方式ではなく、Jobの代わりに bizfw に自前の rich component を handler にした方が良いかも。
    // ただ、このスレッドに対して、AccessContext などのフレームワック設定をしてあげる必要ので、もう少し仕組みの実装が必要。
    protected void launchRabbitJob(String queueName, LaJobUnique jobUnique // queue and job
            , String consumerTag, String messageText // request information
            , Delivery delivery, Channel channel // rabbit components
    ) {
        jobManager.findJobByUniqueOf(jobUnique).alwaysPresent(job -> {
            LaunchedProcess launchedProcess = job.launchNow(op -> { // Job は LastaJob側のスレッドで実行される
                RabbitJobResource resource = new RabbitJobResource(queueName, consumerTag, messageText);
                op.param(RabbitJobResource.JOB_PARAMETER_KEY, resource);
                // Consumerがパラレルに受け付け実行する設定になっても、同じJobはデフォルトではシリアルに実行される
                // もし同じJobをパラレルで実行したい場合はこちらのオプションがあるが実験的な機能ではある。
                //op.asOutlawParallel()
            });
            if (logger.isDebugEnabled()) {
                logger.debug("...Launching rabbitJob: {}", launchedProcess.getScheduledJob());
            }

            OptionalThing<LaJobHistory> jobEnding = launchedProcess.waitForEnding(); // jobの終わりを待つ
            jobEnding.ifPresent(jobHistory -> { // まず確実に存在する
                handleJobHistory(queueName, delivery, channel, jobHistory);
            }).orElse(() -> { // まずありえない
                // historyのlimitはデフォルトで300とかなので、信じられないほどJobが同時起動しまくってなければ大丈夫。
                // ただ、その万が一のときの処理をどう書くか？ちょっと悩みどころなので、とりあえず warnログとしている。
                logger.warn("*Not found the rabbitJob history: {}", queueName);

                long deliveryTag = delivery.getEnvelope().getDeliveryTag();
                basicAck(queueName, channel, deliveryTag, isBasicAckSuccessMultiple());
            });
            // 注: LastaJob-0.5.7までだと、アプリ停止時にhistoryがクリアされてしまって、
            // 「アプリ停止の接続close直前で終了待ちされてる最後のdelivery」のhistoryが取れず正確にackなどができない。
            // LastaJob-0.5.8以降ではhistoryの持ち方を変更して、この時点ではまだhistoryが使える状態にする予定。
        });
    }

    protected void handleJobHistory(String queueName, Delivery delivery, Channel channel, LaJobHistory jobHistory) {
        long deliveryTag = delivery.getEnvelope().getDeliveryTag();
        jobHistory.getCause().ifPresent(cause -> {
            if (cause instanceof RabbitJobChannelAckException) { // 例外業務で ack したい場合
                // autoAckであっても例外を投げたからには basicAck() するようにしているが、もしエラーになるならやめたほうがいい
                boolean multiple = ((RabbitJobChannelAckException) cause).isMultiple();
                basicAck(queueName, channel, deliveryTag, multiple);
            } else if (cause instanceof RabbitJobChannelRejectException) { // 例外業務で reject したい場合
                boolean requeue = ((RabbitJobChannelRejectException) cause).isRequeue();
                basicReject(queueName, channel, deliveryTag, requeue);
            }
            throw new IllegalStateException("RabbitJob failure: " + queueName + ", " + jobHistory, cause);
        }).orElse(() -> { // success
            if (!isConsumerOptionAutoAck()) { // autoAckでなければ (autoAckであれば不要になるので)
                logger.debug("...Acknowledging the queue message as success: {}", queueName);
                basicAck(queueName, channel, deliveryTag, isBasicAckSuccessMultiple());
            }
        });
    }

    protected boolean isBasicAckSuccessMultiple() {
        // #genba_fitting 処理成功時の ack の multiple をどうするか？は現場調整必要 by jflute (2025/01/21)
        return /*multiple*/false;
    }

    protected void basicAck(String queueName, Channel channel, long deliveryTag, boolean multiple) {
        try {
            channel.basicAck(deliveryTag, multiple);
        } catch (IOException warned) {
            String msg = "Failed to basicAck(): " + queueName + ", " + deliveryTag + ", " + multiple;
            logger.warn(msg, warned);
        }
    }

    protected void basicReject(String queueName, Channel channel, long deliveryTag, boolean requeue) {
        try {
            channel.basicReject(deliveryTag, requeue);
        } catch (IOException warned) {
            String msg = "Failed to basicReject(): " + queueName + ", " + deliveryTag + ", " + requeue;
            logger.warn(msg, warned);
        }
    }

    // ===================================================================================
    //                                                                             Destroy
    //                                                                             =======
    @PreDestroy
    public synchronized void destroy() { // destroy中に asyncBoot() で新しいconsumerが登録しないようにsync
        // manager全体に落とすよーってお知らせして各自正常に落ちるためにあれこれ制御してもらう
        destroyingRequested = true; // pollingのcountDown()よりも先に設定することで、接続closeすれ違いを防ぐ

        // pollingしてるconsumerたちに終わってーと言う
        countDownPolling();

        // pollingしてるconsumerたちが接続closeまで本当に終わったかを待つ
        // (でないと、DIコンテナのdestroy処理が先に進んじゃってMQの接続close中にアプリのプロセスが強制終了する可能性がある)
        awaitTerminating();
    }

    protected void countDownPolling() {
        logger.info("#mq ...Releasing MQ consumer polling: pollingLatchList={}", pollingLatchList);
        for (ConsumerPollingLatch pollingLatch : pollingLatchList) {
            pollingLatch.countDown(); // consumerのポーリング待ちを解放
            // 厳密には、pollingLatchListにはconsumerの登録処理に失敗したlatchも含まれている可能性あるが...
            // 別にawaitしてないlatchでcountDownしても何も起きないだけなので問題なし。
        }
    }

    protected void awaitTerminating() {
        recoverFreezedTerminating();

        // まだ実行中のdeliveryがあるなどで接続closeがちょっと遅れているようであればここでちょっと待つ
        logger.info("#mq ...Awaiting MQ consumer terminating: terminatingLatchList={}", terminatingLatchList);
        for (ConsumerTerminatingLatch terminatingLatch : terminatingLatchList) {
            try {
                terminatingLatch.await(); // consumerの接続close待ち
            } catch (InterruptedException continued) { // もうアプリ停止時なので落とさない
                logger.warn("Failed to await the terminating: {}", terminatingLatch, continued);
            }
        }
        logger.info("#mq Complete ending of MQ consumer: terminatingLatchList={}", terminatingLatchList);
    }

    protected void recoverFreezedTerminating() {
        new Thread(() -> {
            // こんだけ待っても終わらないterminatingあったら強制的にcountDownさせる
            // (万が一終わらないterminatingがあってdestroyが終わらず止まってしまうのを避けるため)
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException continued) {
                logger.warn("Failed to sleep the thread for terminating recovery count-down.", continued);
            }
            logger.info("#mq ...Releasing MQ consumer terminating forcedly: terminatingLatchList={}", terminatingLatchList);
            for (ConsumerTerminatingLatch terminatingLatch : terminatingLatchList) {
                terminatingLatch.countDown(); // 強制
            }
        }).start();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isDestroyingRequested() {
        return destroyingRequested;
    }
}
