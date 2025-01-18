package org.docksidestage.bizfw.rabbitmq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dbflute.util.DfAssertUtil;
import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.RabbitJobResource;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.bridge.AsyncStateBridge;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.key.LaJobUnique;

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

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    protected AsyncManager asyncManager;
    @Resource
    protected JobManager jobManager;

    protected final List<Object> blockList = DfCollectionUtil.newArrayList();

    // ===================================================================================
    //                                                                               Boot
    //                                                                              ======
    /**
     * 一つの Consumer を非同期で起動する。<br>
     * 新しいスレッドで起動させるので、このメソッド自体はすぐに戻って来る。
     * @param queueName 起動するConsumerが対応するキューの名前 (NotNull)
     * @param jobUnique 対応する LastaJob の Job を特定するためのユニークオブジェクト (NotNull)
     */
    public synchronized void asyncBoot(String queueName, LaJobUnique jobUnique, Supplier<ConnectionFactory> connectionFactoryProvider) {
        DfAssertUtil.assertStringNotNullAndNotTrimmedEmpty("queueName", queueName);
        DfAssertUtil.assertObjectNotNull("jobUnique", jobUnique);
        Object block = new Object();
        blockList.add(block);
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        new Thread(() -> { // consumber登録後のポーリングに献上するスレッドなのでその場new
            bridge.cross(() -> { // ただ、boot中の例外ハンドリングとかいい感じにするために
                doBoot(queueName, jobUnique, connectionFactoryProvider, block);
            });
        }).start();
    }

    protected void doBoot(String queueName, LaJobUnique jobUnique, Supplier<ConnectionFactory> connectionFactoryProvider, Object block) { // in new thread and crossed
        ConnectionFactory connectionFactory = connectionFactoryProvider.get();
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
        channel.basicConsume(queueName, /*autoAck*/true, deliverCallback, consumerTag -> {});
    }

    protected void awaitConsumer(Object block) {
        while (true) {
            try {
                // TODO jflute この方法でいいのか？ (2025/01/18)
                block.wait();
            } catch (InterruptedException e) {
                String msg = "Interrupted the wait: block=" + block;
                throw new IllegalStateException(msg, e);
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
        for (Object block : blockList) {
            block.notifyAll();
        }
    }
}
