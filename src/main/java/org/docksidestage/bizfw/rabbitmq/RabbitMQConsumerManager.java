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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.dbflute.util.DfAssertUtil;
import org.dbflute.util.DfCollectionUtil;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.bridge.AsyncStateBridge;
import org.lastaflute.job.JobManager;
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

    // singletonのDIコンポーネント前提で保持する。synchronizedの中で利用すること。
    /** ポーリング用のLatch。(NotNull) */
    protected final List<ConsumerPollingLatch> pollingLatchList = DfCollectionUtil.newArrayList();

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

        new Thread(() -> { // consumber登録後のポーリングに献上するスレッドなのでその場new
            try {
                doBoot(queueName, jobUnique, connectionFactoryProvider, pollingLatch);
            } catch (Throwable e) {
                // ずっと止まる想定のスレッドにContextなどを保持させ続けるのも気持ち悪いから、
                // AsyncManagerのcross()は使わずに自前でエラーハンドリングするようにした。
                // (ここではContext類は要らないはずなので)
                logger.error("Failed to boot the RabbitMQ consumer: " + queueName, e);
            }
        }).start();
    }

    // -----------------------------------------------------
    //                                     in Polling Thread
    //                                     -----------------
    protected void doBoot(String queueName, LaJobUnique jobUnique, MQConnectionFactoryProvider connectionFactoryProvider,
            CountDownLatch pollingLatch) { // works in polling thread
        ConnectionFactory connectionFactory = connectionFactoryProvider.provide();
        try (Connection conn = connectionFactory.newConnection(); Channel channel = conn.createChannel()) {
            basicQos(queueName, channel);
            queueDeclare(queueName, channel); // まずはキューを宣言
            basicConsume(queueName, jobUnique, channel); // 登録だけでポーリングするわけではない
            awaitConsumer(pollingLatch); // ここで自前ポーリング、アプリ停止時に解放されてConnectionのclose()が動く
        } catch (IOException | TimeoutException e) {
            String msg = "Failed to consume message queue: " + queueName + ", " + jobUnique;
            throw new IllegalStateException(msg, e);
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

    // #genba_fitting prefetchCount これでいいのか？恐らく現場で要調整 by jflute (2025/01/16)
    protected OptionalThing<Integer> getQosOptionPrefetchCount() { // no set if empty
        return OptionalThing.of(1); // as default
    }

    // -----------------------------------------------------
    //                                               Declare
    //                                               -------
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
    protected void basicConsume(String queueName, LaJobUnique jobUnique, Channel channel) throws IOException {
        boolean autoAck = isConsumerOptionAutoAck();
        DeliverCallback deliverCallback = createDeliverCallback(queueName, jobUnique, channel);
        CancelCallback cancelCallback = createCancelCallback(queueName, jobUnique, channel);

        logger.info("#mq ...Registering MQ consumer: {} to {}", queueName, jobUnique);
        channel.basicConsume(queueName, autoAck, deliverCallback, cancelCallback);
    }

    // #genba_fitting basicConsume()のオプション引数autoAckはこれでいいのか？恐らく現場で要調整 by jflute (2025/01/16)
    protected boolean isConsumerOptionAutoAck() {
        return true; // as default
    }

    // -----------------------------------------------------
    //                                                 Await
    //                                                 -----
    protected void awaitConsumer(CountDownLatch pollingLatch) {
        try {
            // ここで止まってポーリング状態に入り、try-with-resources の Connection は開いたままでConsumerが利用する。
            // アプリ停止の destroy() で countDown されることで、処理が続行されて try-with-resources の close処理が実行される。
            pollingLatch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted the consumer wait: pollingLatch=" + pollingLatch, e);
        }
    }

    // -----------------------------------------------------
    //                                       DeliverCallback
    //                                       ---------------
    protected DeliverCallback createDeliverCallback(String queueName, LaJobUnique jobUnique, Channel channel) {
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        return (consumerTag, delivery) -> { // ここはまた別スレッドのはず
            bridge.cross(() -> { // launch自体の処理の例外ハンドリングなどをいい感じにするために
                doDeliver(queueName, jobUnique, consumerTag, delivery, channel);
            });
        };
    }

    // #genba_fitting ackを例外有無で制御するのであれば、ここでtry/catch (かつ、launch側でJobの例外有無の判定) by jflute (2025/01/19)
    protected void doDeliver(String queueName, LaJobUnique jobUnique, String consumerTag, Delivery delivery, Channel channel) {
        if (logger.isDebugEnabled()) {
            logger.debug("#mq ...Delivering the queue request: {}, {}", queueName, jobUnique);
        }
        String messageText = extractMessageText(delivery);
        launchRabbitJob(queueName, jobUnique, consumerTag, messageText, channel);
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
            logger.info("The queue consuming was cancelled: " + queueName + ", " + jobUnique);
        };
    }

    // ===================================================================================
    //                                                                       Polling Latch
    //                                                                       =============
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

    // ===================================================================================
    //                                                                           Lasta Job
    //                                                                           =========
    // #genba_fitting 業務処理の途中で channel を使って細かく制御したい場合は、Job方式だとちょっとやりづらいので... by jflute (2025/01/20)
    // その場合はJob方式ではなく、Jobの代わりに bizfw に自前の rich component を handler にした方が良いかも。
    // ただ、このスレッドに対して、AccessContext などのフレームワック設定をしてあげる必要ので、もう少し仕組みの実装が必要。
    protected void launchRabbitJob(String queueName, LaJobUnique jobUnique, String consumerTag, String messageText, Channel channel) {
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

            // #genba_fitting もし、Jobの処理をconsumerスレッドで待ちたい場合はこちら by jflute (2025/01/19)
            // Jobで発生した例外内容によってchannelを使ってキューリクエストを制御したい場合は、history から例外を取得して制御する。
            //OptionalThing<LaJobHistory> jobEnding = launchedProcess.waitForEnding();
            //jobEnding.ifPresent(jobHistory -> { // まず確実に存在する
            //    ExecResultType execResultType = jobHistory.getExecResultType();
            //    if (execResultType.isErrorResult()) {
            //        throw new IllegalStateException("RabbitJob failure: " + queueName + ", " + jobHistory);
            //    }
            //}).orElse(() -> { // まずありえない
            //    // historyのlimitはデフォルトで300とかなので、信じられないほどJobが同時起動しまくってなければ大丈夫
            //    logger.warn("*Not found the rabbitJob history: " + queueName);
            //});
        });
    }

    // ===================================================================================
    //                                                                             Destroy
    //                                                                             =======
    @PreDestroy
    public synchronized void destroy() {
        logger.info("#mq ...Releasing MQ consumer polling: pollingLatchList={}", pollingLatchList);
        for (ConsumerPollingLatch pollingLatch : pollingLatchList) {
            pollingLatch.countDown(); // consumerのポーリング待ちを解放
        }
        // consumerの登録処理に失敗したlatchも含まれている可能性あるが...
        // 別にawaitしてないlatchでcountDownしても何も起きないだけなので問題なし。
    }
}
