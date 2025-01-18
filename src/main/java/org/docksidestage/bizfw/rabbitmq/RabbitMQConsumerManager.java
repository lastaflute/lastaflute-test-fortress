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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dbflute.util.DfAssertUtil;
import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.RabbitJobResource;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.bridge.AsyncStateBridge;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.key.LaJobUnique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            queueDeclare(queueName, channel); // まずはキューを宣言
            basicConsume(queueName, jobUnique, channel); // 登録だけでポーリングするわけではない
            awaitConsumer(pollingLatch); // ここで自前ポーリング、アプリ停止時に解放されてConnectionのclose()が動く
        } catch (IOException | TimeoutException e) {
            String msg = "Failed to consume message queue: " + queueName + ", " + jobUnique;
            throw new IllegalStateException(msg, e);
        }
    }

    // -----------------------------------------------------
    //                                         Polling Steps
    //                                         -------------
    protected void queueDeclare(String queueName, Channel channel) throws IOException {
        // #rabbit queueDeclare()のオプション引数たちこれでいいのか？恐らく現場で要調整 by jflute (2025/01/16)
        channel.queueDeclare(queueName, /*durable*/true, /*exclusive*/false, /*autoDelete*/false, /*arguments*/null);
    }

    protected void basicConsume(String queueName, LaJobUnique jobUnique, Channel channel) throws IOException {
        // #rabbit basicConsume()のオプション引数autoAckやconsumerTagはこれでいいのか？恐らく現場で要調整 by jflute (2025/01/16)
        DeliverCallback deliverCallback = createDeliverCallback(queueName, jobUnique);

        logger.info("#mq ...Registering MQ consumer: {} to {}", queueName, jobUnique);
        channel.basicConsume(queueName, /*autoAck*/true, deliverCallback, consumerTag -> {});
    }

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
    protected DeliverCallback createDeliverCallback(String queueName, LaJobUnique jobUnique) {
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        return (consumerTag, delivery) -> { // ここはまた別スレッドのはず
            bridge.cross(() -> { // launch自体の処理の例外ハンドリングなどをいい感じにするために
                String messageText = extractMessageText(delivery);
                launchRabbitJob(queueName, jobUnique, consumerTag, messageText);
            });
        };
    }

    protected String extractMessageText(Delivery delivery) {
        return new String(delivery.getBody(), getMessageTextCharset());
    }

    protected Charset getMessageTextCharset() {
        return StandardCharsets.UTF_8;
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
    protected void launchRabbitJob(String queueName, LaJobUnique jobUnique, String consumerTag, String messageText) {
        jobManager.findJobByUniqueOf(jobUnique).alwaysPresent(job -> {
            job.launchNow(op -> { // Job は LastaJob側のスレッドで非同期で実行される
                RabbitJobResource resource = new RabbitJobResource(queueName, consumerTag, messageText);
                op.param(RabbitJobResource.JOB_PARAMETER_KEY, resource);

                // Consumerがパラレルに受け付け実行する設定になっても、同じJobはデフォルトではシリアルに実行される
                // もし同じJobをパラレルで実行したい場合はこちらのオプションがあるが実験的な機能ではある。
                //op.asOutlawParallel()
            });
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
