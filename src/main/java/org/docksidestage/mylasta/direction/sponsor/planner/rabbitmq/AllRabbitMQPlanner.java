package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import org.docksidestage.bizfw.rabbitmq.RabbitMQConsumerManager;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.lastaflute.job.key.LaJobUnique;

import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ のプランを立てるクラス。
 * @author jflute
 */
public class AllRabbitMQPlanner {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // AllJobScheduler から参照するための定数定義、名前ズレしないように。
    // 外からappは参照できないので、(外の)plannerに定義して(appの)AllJobSchedulerで参照する。
    // #rabbit 現場でのconsumerに対応するJobのユニークコードの定義 by jflute (2025/01/17)
    public static final String mysticJobUnique = "mysticJob";
    public static final String onemanJobUnique = "onemanJob";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config; // not null
    protected final RabbitMQConsumerManager consumerManager; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AllRabbitMQPlanner(FortressConfig config, RabbitMQConsumerManager consumerManager) {
        this.config = config;
        this.consumerManager = consumerManager;
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
        // #rabbit 現場でのconsumerの設定 by jflute (2025/01/17)
        consumerManager.asyncBoot("seaQueue", LaJobUnique.of(mysticJobUnique), () -> prepareConnectionFactory());
        consumerManager.asyncBoot("landQueue", LaJobUnique.of(onemanJobUnique), () -> prepareConnectionFactory());
    }

    // -----------------------------------------------------
    //                                         MQ Connection
    //                                         -------------
    protected ConnectionFactory prepareConnectionFactory() {
        // #rabbit 現場での接続設定、configを連れてきて、[app]_env.properties に定義した値を持ってくるのが良い by jflute (2025/01/17)
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
}
