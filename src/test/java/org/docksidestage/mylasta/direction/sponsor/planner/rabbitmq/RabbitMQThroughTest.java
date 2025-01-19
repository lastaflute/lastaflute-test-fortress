/*
 * Copyright 2015-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.annotation.Resource;

import org.docksidestage.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.magic.async.AsyncManager;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import ch.qos.logback.core.util.StatusPrinter;

/**
 * RabbitMQをDockerで起動して実際にpublishしてconsumerを動かす一気通貫テスト。
 * @author jflute
 */
public class RabbitMQThroughTest extends UnitFortressBasicTestCase {

    private static final String SEA_QUEUE = "seaQueue";

    @Resource
    private PurchaseBhv purchaseBhv;
    @Resource
    private AsyncManager asyncManager;

    @Override
    protected boolean isUseOneTimeContainer() {
        return true;
    }

    @Override
    protected boolean isUseJobScheduling() {
        return true;
    }

    public void test_basic() throws Exception {
        publishCall(channel -> {
            String exchange = ""; // as producer
            String routingKey = SEA_QUEUE;
            BasicProperties props = null;
            byte[] bodyBytes = "{stageName: \"hangar\", oneDayShowCount: 5}".getBytes("UTF-8");
            channel.basicPublish(exchange, routingKey, props, bodyBytes);
        });
    }

    public static interface TestEasyPublisherCall {

        void callback(Channel channel) throws IOException;
    }

    private void publishCall(TestEasyPublisherCall publisher) throws Exception {
        ConnectionFactory factory = createConnectionFactory();
        try (Connection conn = factory.newConnection(); Channel channel = conn.createChannel()) {
            publisher.callback(channel);
        }
        sleep(1000); // consumer側のログを見るためにちょっとの時間が必要
    }

    private ConnectionFactory createConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("maihama");
        factory.setPassword("park");
        return factory;
    }

    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall

    // does not need logback initialization logs
    static { // should be before logback initialization
        StatusPrinter.setPrintStream(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                // do nothing
            }
        }));
    }
}