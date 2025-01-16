package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import org.docksidestage.mylasta.direction.FortressConfig;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.key.LaJobUnique;

import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ のプランを立てるクラス。
 * @author jflute
 */
public class AllRabbitMQPlanner {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AllRabbitMQPlanner(FortressConfig config) {
        this.config = config;
    }

    // ===================================================================================
    //                                                                            Consumer
    //                                                                            ========
    /**
     * RabbitMQ のすべての Consumer を起動する。<br>
     * アプリ起動時 (アプリ初期化済み、かつ、Tomcat開放直前) に実行されることを前提。<br>
     * (例えば LastaFluteなら、CurtainBeforeHook にて)
     */
    public void bootAllConsumer() {
        RabbitMQConsumerSetupper consumerSetupper = prepareConsumerSetupper();

        // your queues here
        consumerSetupper.asyncBoot("seaQueue", toUnique("mysticJob"));
        consumerSetupper.asyncBoot("landQueue", toUnique("onemanJob"));
    }

    protected RabbitMQConsumerSetupper prepareConsumerSetupper() {
        AsyncManager asyncManager = ContainerUtil.getComponent(AsyncManager.class);
        JobManager jobManager = ContainerUtil.getComponent(JobManager.class);
        return new RabbitMQConsumerSetupper(asyncManager, jobManager, () -> {
            return prepareConnectionFactory();
        });
    }

    // -----------------------------------------------------
    //                                         MQ Connection
    //                                         -------------
    protected ConnectionFactory prepareConnectionFactory() {
        // your settings here
        // #for_now jflute configを連れてきて、[app]_env.properties に定義した値を持ってくるのが良い (2025/01/17)
        //  e.g. String host = config.getRabbitMQConsumerHost();
        ConnectionFactory factory = newConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("land");
        factory.setPassword("oneman");
        return factory;
    }

    protected ConnectionFactory newConnectionFactory() {
        return new ConnectionFactory();
    }

    // -----------------------------------------------------
    //                                             Lasta Job
    //                                             ---------
    protected LaJobUnique toUnique(String uniqueCode) {
        return LaJobUnique.of(uniqueCode);
    }
}
