package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.queue.base;

import org.dbflute.util.DfAssertUtil;
import org.docksidestage.bizfw.rabbitmq.RabbitMQConsumerManager;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.lastaflute.job.key.LaJobUnique;

import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ のキューへの接続/起動を司る抽象クラス。<br>
 * 例えば、Jobと連携するConsumerを起動する。
 * @author jflute
 */
public abstract class MQBaseAgent { // #rabbit

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config; // not null
    protected final RabbitMQConsumerManager consumerManager; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public MQBaseAgent(FortressConfig config, RabbitMQConsumerManager consumerManager) {
        DfAssertUtil.assertObjectNotNull("config", config);
        DfAssertUtil.assertObjectNotNull("consumerManager", consumerManager);
        this.config = config;
        this.consumerManager = consumerManager;
    }

    // ===================================================================================
    //                                                                            Consumer
    //                                                                            ========
    /**
     * 該当キューに対する RabbitMQ の Consumer を起動する。<br>
     * アプリ起動時 (アプリ初期化済み、かつ、Tomcat開放直前) に実行されることを前提。<br>
     * (例えば LastaFluteなら、CurtainBeforeHook にて)
     */
    public void bootConsumer() {
        String queueName = getQueueName();
        LaJobUnique jobUnique = LaJobUnique.of(getJobUniqueCode());
        consumerManager.asyncBoot(queueName, jobUnique, () -> prepareConnectionFactory());
    }

    // -----------------------------------------------------
    //                                         Queue and Job
    //                                         -------------
    protected abstract String getQueueName(); // not null

    protected abstract String getJobUniqueCode(); // not null

    // -----------------------------------------------------
    //                                         MQ Connection
    //                                         -------------
    protected abstract ConnectionFactory prepareConnectionFactory(); // not null

    protected ConnectionFactory newConnectionFactory() {
        return new ConnectionFactory();
    }
}
