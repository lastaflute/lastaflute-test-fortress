package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.queue;

import org.docksidestage.bizfw.rabbitmq.RabbitMQConsumerManager;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.queue.base.MQBaseAgent;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jflute
 */
public class SeaMQAgent extends MQBaseAgent { // #rabbit

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // #genba_fitting ずばりキューの名前
    private static final String QUEUE_NAME = "seaQueue";

    // #genba_fitting AllJobScheduler から参照するためのJobのユニークコードの定数定義、名前ズレしないように。
    // app外からapp内は参照できないので、(外の)plannerに定義して(appの)AllJobSchedulerで参照する。
    public static final String JOB_UNIQUE_CODE = "mysticJob";

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public SeaMQAgent(FortressConfig config, RabbitMQConsumerManager consumerManager) {
        super(config, consumerManager);
    }

    // ===================================================================================
    //                                                                            Consumer
    //                                                                            ========
    // -----------------------------------------------------
    //                                         Queue and Job
    //                                         -------------
    @Override
    protected String getQueueName() {
        return QUEUE_NAME;
    }

    @Override
    protected String getJobUniqueCode() {
        return JOB_UNIQUE_CODE;
    }

    // -----------------------------------------------------
    //                                         MQ Connection
    //                                         -------------
    protected ConnectionFactory prepareConnectionFactory() {
        // #genba_fitting キューへの接続設定 by jflute (2025/01/17)
        // 実際には config を連れてきて、[app]_env.properties に定義した値を持ってくるのが良い
        //  e.g. String host = config.getRabbitMQConsumerHost();
        ConnectionFactory factory = newConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("maihama");
        factory.setPassword("park");
        return factory;
    }
}
