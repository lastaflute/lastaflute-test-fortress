package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import org.dbflute.util.DfAssertUtil;
import org.docksidestage.bizfw.rabbitmq.RabbitMQConsumerManager;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.queue.LandMQAgent;
import org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.queue.SeaMQAgent;

/**
 * RabbitMQ のプランを立てるクラス。
 * @author jflute
 */
public class AllRabbitMQPlanner { // #rabbit

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config; // not null
    protected final RabbitMQConsumerManager consumerManager; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AllRabbitMQPlanner(FortressConfig config, RabbitMQConsumerManager consumerManager) {
        DfAssertUtil.assertObjectNotNull("config", config);
        DfAssertUtil.assertObjectNotNull("consumerManager", consumerManager);
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
        createSeaMQAgent().bootConsumer();
        createLandMQAgent().bootConsumer();
    }

    protected SeaMQAgent createSeaMQAgent() {
        return new SeaMQAgent(config, consumerManager);
    }

    protected LandMQAgent createLandMQAgent() {
        return new LandMQAgent(config, consumerManager);
    }
}
