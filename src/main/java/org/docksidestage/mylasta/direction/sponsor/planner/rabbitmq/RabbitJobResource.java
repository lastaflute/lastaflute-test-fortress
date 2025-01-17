package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import org.lastaflute.core.util.Lato;

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
    private final String messageText; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RabbitJobResource(String queueName, String consumerTag, String messageText) {
        this.queueName = queueName;
        this.consumerTag = consumerTag;
        this.messageText = messageText;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return Lato.string(this);
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

    public String getMessageText() {
        return messageText;
    }
}
