package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

/**
 * @author jflute
 */
public class RabbitJobResource {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    public static final String JOB_PARAMETER_KEY = "rabbit";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String queueName; // not null
    private final String consumerTag; // not null
    private final String messageBody; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RabbitJobResource(String queueName, String consumerTag, String messageBody) {
        this.queueName = queueName;
        this.consumerTag = consumerTag;
        this.messageBody = messageBody;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getQueueName() {
        return queueName;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
