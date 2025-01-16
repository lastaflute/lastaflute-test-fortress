package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import org.dbflute.util.DfAssertUtil;
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
 * RabbitMQ の Consumer をセットアップするクラス。<br>
 * 別スレッドで queue ごとに basicConsume() を実行してポーリングする。<br>
 * メッセージを受け取ったら、queue ごとに対応する LastaJob の Job を実行する。<br>
 * その Job は、AllJobScheduler で nonCron で登録しておく。
 * @author jflute
 */
public class RabbitMQConsumerSetupper {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    public static final String JOB_PARAMETER_MESSAGE_KEY = "message";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final AsyncManager asyncManager; // not null
    protected final JobManager jobManager; // not null
    protected final Supplier<ConnectionFactory> connectionFactoryProvider; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param asyncManager LastaFluteの非同期処理を司るコンポーネント (NotNull)
     * @param jobManager LastaJobのJobを管理している司るコンポーネント (NotNull)
     * @param connectionFactoryProvider RabbitMQのサーバーへの接続を生成するクラスを提供するコールバック (NotNull)
     */
    public RabbitMQConsumerSetupper(AsyncManager asyncManager, JobManager jobManager,
            Supplier<ConnectionFactory> connectionFactoryProvider) {
        DfAssertUtil.assertObjectNotNull("asyncManager", asyncManager);
        DfAssertUtil.assertObjectNotNull("jobManager", jobManager);
        DfAssertUtil.assertObjectNotNull("connectionFactoryProvider", connectionFactoryProvider);
        this.asyncManager = asyncManager;
        this.jobManager = jobManager;
        this.connectionFactoryProvider = connectionFactoryProvider;
    }

    // ===================================================================================
    //                                                                               Boot
    //                                                                              ======
    /**
     * 一つの Consumer を非同期で起動する。<br>
     * 新しいスレッドで起動させるので、このメソッド自体はすぐに戻って来る。
     * @param queueName 起動するConsumerが対応するキューの名前 (NotNull)
     * @param jobUnique 対応する LastaJob の Job を特定するためのユニークオブジェクト (NotNull)
     */
    public void asyncBoot(String queueName, LaJobUnique jobUnique) {
        DfAssertUtil.assertStringNotNullAndNotTrimmedEmpty("queueName", queueName);
        DfAssertUtil.assertObjectNotNull("jobUnique", jobUnique);
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        new Thread(() -> { // basicConsume()のポーリングに献上するスレッドなのでその場new
            bridge.cross(() -> { // ただ、boot中の例外ハンドリングとかいい感じにするために
                doBoot(queueName, jobUnique);
            });
        }).start();
    }

    protected void doBoot(String queueName, LaJobUnique jobUnique) { // in new thread and crossed
        ConnectionFactory connectionFactory = connectionFactoryProvider.get();
        try (Connection conn = connectionFactory.newConnection(); Channel channel = conn.createChannel()) {
            queueDeclare(queueName, channel);
            basicConsume(queueName, jobUnique, channel); // ここで待ち受けするので止まる
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException("Failed to wait message queue: " + queueName, e);
        }
    }

    protected void queueDeclare(String queueName, Channel channel) throws IOException {
        // #for_now jflute queueDeclare()のオプション引数たちこれでいいのか？恐らく現場で要調整 (2025/01/16)
        channel.queueDeclare(queueName, /*durable*/true, /*exclusive*/false, /*autoDelete*/false, /*arguments*/null);
    }

    protected void basicConsume(String queueName, LaJobUnique jobUnique, Channel channel) throws IOException {
        // #for_now jflute basicConsume()のオプション引数autoAckやconsumerTagはこれでいいのか？恐らく現場で要調整 (2025/01/16)
        DeliverCallback deliverCallback = createDeliverCallback(jobUnique);
        channel.basicConsume(queueName, /*autoAck*/true, deliverCallback, consumerTag -> {});
    }

    // -----------------------------------------------------
    //                                       DeliverCallback
    //                                       ---------------
    protected DeliverCallback createDeliverCallback(LaJobUnique jobUnique) {
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        return (consumerTag, delivery) -> { // ここはまた別スレッドのはず
            bridge.cross(() -> { // launch処理内での例外ハンドリングなどをいい感じにするために
                String message = extractStringBody(delivery);
                launchRabbitJob(jobUnique, message);
            });
        };
    }

    protected String extractStringBody(Delivery delivery) {
        return new String(delivery.getBody(), StandardCharsets.UTF_8);
    }

    // ===================================================================================
    //                                                                           Lasta Job
    //                                                                           =========
    protected void launchRabbitJob(LaJobUnique jobUnique, String message) {
        // TODO jflute Stringじゃなく統一的な引数オブジェクトがあった方が汎用性が高い (2025/01/16)
        jobManager.findJobByUniqueOf(jobUnique).alwaysPresent(job -> {
            job.launchNow(op -> { // JobはLastaJob側のスレッドで非同期で実行される
                op.param(JOB_PARAMETER_MESSAGE_KEY, message);

                // Consumerがパラレルに受け付け実行する設定になっても、同じJobはデフォルトではシリアルに実行される
                // もし同じJobをパラレルで実行したい場合はこちらのオプションがあるが実験的な機能ではある。
                //op.asOutlawParallel()
            });
        });
    }
}
