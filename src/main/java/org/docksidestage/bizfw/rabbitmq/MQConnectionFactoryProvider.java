package org.docksidestage.bizfw.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jflute
 */
public interface MQConnectionFactoryProvider {

    ConnectionFactory provide(); // not null
}
