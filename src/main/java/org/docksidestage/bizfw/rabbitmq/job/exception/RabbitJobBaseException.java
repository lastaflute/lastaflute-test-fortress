package org.docksidestage.bizfw.rabbitmq.job.exception;

/**
 * @author jflute
 */
public abstract class RabbitJobBaseException extends RuntimeException { // #rabbit

    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RabbitJobBaseException(String msg) {
        super(msg);
    }

    public RabbitJobBaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
