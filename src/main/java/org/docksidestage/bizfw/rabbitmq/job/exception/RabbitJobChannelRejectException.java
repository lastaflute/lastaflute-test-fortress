package org.docksidestage.bizfw.rabbitmq.job.exception;

/**
 * @author jflute
 */
public class RabbitJobChannelRejectException extends RabbitJobBaseException { // #rabbit

    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private boolean requeue;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RabbitJobChannelRejectException(String msg) {
        super(msg);
    }

    public RabbitJobChannelRejectException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // -----------------------------------------------------
    //                                                Option
    //                                                ------
    public RabbitJobChannelRejectException asRequeue() {
        requeue = true;
        return this;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isRequeue() {
        return requeue;
    }
}
