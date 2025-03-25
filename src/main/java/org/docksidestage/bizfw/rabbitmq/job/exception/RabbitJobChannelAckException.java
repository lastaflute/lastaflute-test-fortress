package org.docksidestage.bizfw.rabbitmq.job.exception;

/**
 * @author jflute
 */
public class RabbitJobChannelAckException extends RabbitJobBaseException { // #rabbit

    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private boolean multiple;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RabbitJobChannelAckException(String msg) {
        super(msg);
    }

    public RabbitJobChannelAckException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // -----------------------------------------------------
    //                                                Option
    //                                                ------
    public RabbitJobChannelAckException asMultiple() {
        multiple = true;
        return this;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isMultiple() {
        return multiple;
    }
}
