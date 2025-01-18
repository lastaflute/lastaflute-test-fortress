package org.docksidestage.bizfw.rabbitmq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dbflute.util.DfAssertUtil;
import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.RabbitJobResource;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.bridge.AsyncStateBridge;
import org.lastaflute.core.util.Lato;
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
 * RabbitMQ の Consumer の登録/接続/切断、そして、Jobへの連携を管理するクラス。<br>
 * 別スレッドで queue ごとに basicConsume() を実行してポーリングする。<br>
 * メッセージを受け取ったら、queue ごとに対応する LastaJob の Job を実行する。<br>
 * その Job は、AllJobScheduler で nonCron で登録しておく。
 * @author jflute
 */
public class RabbitMQConsumerManager {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumerManager.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    protected AsyncManager asyncManager;
    @Resource
    protected JobManager jobManager;

    // singletonのDIコンポーネント前提で保持する。synchronizedの中で利用すること。
    protected final List<ConsumerThreadBlock> blockList = DfCollectionUtil.newArrayList();

    public static class ConsumerThreadBlock {

        private final String queueName; // not null

        public ConsumerThreadBlock(String queueName) {
            this.queueName = queueName;
        }

        @Override
        public String toString() {
            return Lato.string(this);
        }

        public String getQueueName() {
            return queueName;
        }
    }

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
        ConsumerThreadBlock block = new ConsumerThreadBlock(queueName); // 自前ポーリング用のブロックオブジェクト
        blockList.add(block); // アプリ停止時に開放するようにする想定で登録
        new Thread(() -> { // consumber登録後のポーリングに献上するスレッドなのでその場new
            try {
                doBoot(queueName, jobUnique, connectionFactoryProvider, block);
            } catch (Throwable e) {
                // ずっと止まる想定のスレッドにContextなどを保持させ続けるのも気持ち悪いから、
                // AsyncManagerのcross()は使わずに自前でエラーハンドリングするようにした。
                // (ここではContext類は要らないはずなので)
                throw new IllegalStateException("Failed to boot the RabbitMQ consumer: " + queueName, e);
            }
        }).start();
    }

    protected void doBoot(String queueName, LaJobUnique jobUnique, MQConnectionFactoryProvider connectionFactoryProvider, Object block) { // in new thread and crossed
        ConnectionFactory connectionFactory = connectionFactoryProvider.provide();
        try (Connection conn = connectionFactory.newConnection(); Channel channel = conn.createChannel()) {
            queueDeclare(queueName, channel);
            basicConsume(queueName, jobUnique, channel); // 登録だけでポーリングするわけではない
            awaitConsumer(block); // ここで自前ポーリング、アプリ停止時に解放されてConnectionのclose()が動く
        } catch (IOException | TimeoutException e) {
            String msg = "Failed to consume message queue: " + queueName + ", " + jobUnique;
            throw new IllegalStateException(msg, e);
        }
    }

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

    protected void awaitConsumer(Object block) {
        synchronized (block) {
            try {
                block.wait(); // ここで止まって
            } catch (InterruptedException e) {
                throw new IllegalStateException("Interrupted the wait: block=" + block, e);
            }
        }
    }

    // -----------------------------------------------------
    //                                       DeliverCallback
    //                                       ---------------
    protected DeliverCallback createDeliverCallback(String queueName, LaJobUnique jobUnique) {
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        return (consumerTag, delivery) -> { // ここはまた別スレッドのはず
            bridge.cross(() -> { // launch処理内での例外ハンドリングなどをいい感じにするために
                String messageText = extractMessageText(delivery);
                launchRabbitJob(queueName, jobUnique, consumerTag, messageText);
            });
        };
    }

    protected String extractMessageText(Delivery delivery) {
        return new String(delivery.getBody(), StandardCharsets.UTF_8);
    }

    // ===================================================================================
    //                                                                           Lasta Job
    //                                                                           =========
    protected void launchRabbitJob(String queueName, LaJobUnique jobUnique, String consumerTag, String messageText) {
        jobManager.findJobByUniqueOf(jobUnique).alwaysPresent(job -> {
            job.launchNow(op -> { // JobはLastaJob側のスレッドで非同期で実行される
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
        logger.info("#mq ...Notifying MQ consumer blocks: {}", blockList);
        for (ConsumerThreadBlock block : blockList) {
            synchronized (block) {
                block.notify();
            }
        }
    }
}
