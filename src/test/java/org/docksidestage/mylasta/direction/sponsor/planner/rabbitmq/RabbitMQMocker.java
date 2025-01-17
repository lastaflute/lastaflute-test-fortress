package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.mylasta.direction.FortressConfig;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.impl.AMQConnection;
import com.rabbitmq.client.impl.AMQImpl.Queue.DeclareOk;
import com.rabbitmq.client.impl.ConnectionParams;
import com.rabbitmq.client.impl.ConsumerWorkService;
import com.rabbitmq.client.impl.Frame;
import com.rabbitmq.client.impl.FrameHandler;
import com.rabbitmq.client.impl.recovery.AutorecoveringConnection;
import com.rabbitmq.client.impl.recovery.RecoveryAwareChannelN;

/**
 * @author jflute
 */
public class RabbitMQMocker {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final FortressConfig config; // not null
    private Consumer<String> queueDeclareCall; // null allowed
    private BiConsumer<String, DeliverCallback> basicConsumeCall; // null allowed

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RabbitMQMocker(FortressConfig config) {
        this.config = config;
    }

    // ===================================================================================
    //                                                                         Assert Call
    //                                                                         ===========
    public RabbitMQMocker assertCall_queueDeclare(Consumer<String> queueDeclareCall) {
        this.queueDeclareCall = queueDeclareCall;
        return this;
    }

    public RabbitMQMocker assertCall_basicConsume(BiConsumer<String, DeliverCallback> basicConsumeCall) {
        this.basicConsumeCall = basicConsumeCall;
        return this;
    }

    // ===================================================================================
    //                                                                             Planner
    //                                                                             =======
    public AllRabbitMQPlanner createMockAllRabbitMQPlanner() {
        return new AllRabbitMQPlanner(config) {
            @Override
            protected ConnectionFactory newConnectionFactory() {
                return createMockConnectionFactory();
            }
        };
    }

    // -----------------------------------------------------
    //                                     ConnectionFactory
    //                                     -----------------
    private ConnectionFactory createMockConnectionFactory() {
        return new ConnectionFactory() {
            @Override
            public Connection newConnection() throws IOException, TimeoutException {
                return createMockAutorecoveringConnection();
            }
        };
    }

    // -----------------------------------------------------
    //                                            Connection
    //                                            ----------
    private AutorecoveringConnection createMockAutorecoveringConnection() {
        return new AutorecoveringConnection(new ConnectionParams(), null, null, null, null) {
            @Override
            public Channel createChannel() throws IOException {
                AMQConnection amqConnection = createMockAMQConnection();
                ConsumerWorkService workService = createMockConsumerWorkService();
                return createMockRecoveryAwareChannelN(amqConnection, workService);
            }

            @Override
            public void init() throws IOException, TimeoutException {
                // do nothing
            }

            @Override
            public void close() throws IOException {
                // do nothing
            }
        };
    }

    private AMQConnection createMockAMQConnection() {
        ConnectionParams params = new ConnectionParams();
        params.setClientProperties(DfCollectionUtil.emptyMap());
        FrameHandler frameHandler = new MockFrameHandler();
        return new AMQConnection(params, frameHandler);
    }

    // -----------------------------------------------------
    //                                           WorkService
    //                                           -----------
    private ConsumerWorkService createMockConsumerWorkService() {
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().build();
        return new ConsumerWorkService((ExecutorService) null, threadFactory, 1000);
    }

    // -----------------------------------------------------
    //                                               Channel
    //                                               -------
    private RecoveryAwareChannelN createMockRecoveryAwareChannelN(AMQConnection amqConnection, ConsumerWorkService workService) {
        return new RecoveryAwareChannelN(amqConnection, 1, workService) {
            @Override
            public DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                    Map<String, Object> arguments) throws IOException {
                if (queueDeclareCall != null) {
                    queueDeclareCall.accept(queue);
                }
                return new DeclareOk(queue, 0, 0); // mock
            }

            @Override
            public String basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback)
                    throws IOException {
                if (basicConsumeCall != null) {
                    basicConsumeCall.accept(queue, deliverCallback);
                }
                return "basicConsumeCalled";
            }

            @Override
            public void close() throws IOException, TimeoutException {
                // do nothing
            }
        };
    }

    // ===================================================================================
    //                                                                          Mock Class
    //                                                                          ==========
    public static class MockFrameHandler implements FrameHandler {

        @Override
        public InetAddress getLocalAddress() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public InetAddress getAddress() {
            return null;
        }

        @Override
        public int getPort() {
            return 0;
        }

        @Override
        public void setTimeout(int timeoutMs) throws SocketException {
        }

        @Override
        public int getTimeout() throws SocketException {
            return 0;
        }

        @Override
        public void sendHeader() throws IOException {
        }

        @Override
        public void initialize(AMQConnection connection) {
        }

        @Override
        public Frame readFrame() throws IOException {
            return null;
        }

        @Override
        public void writeFrame(Frame frame) throws IOException {
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void close() {
        }
    }
}
