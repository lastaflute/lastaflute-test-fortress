package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.bridge.AsyncStateBridge;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.key.LaJobUnique;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author jflute
 */
public class RabbitMQConsumerSetupper {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    public static final String JOB_PARAMETER_MESSAGE_KEY = "message";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final AsyncManager asyncManager;
    protected final JobManager jobManager;
    protected final Supplier<ConnectionFactory> connectionFactoryProvider;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RabbitMQConsumerSetupper(AsyncManager asyncManager, JobManager jobManager,
            Supplier<ConnectionFactory> connectionFactoryProvider) {
        this.asyncManager = asyncManager;
        this.jobManager = jobManager;
        this.connectionFactoryProvider = connectionFactoryProvider;
    }

    // ===================================================================================
    //                                                                               Boot
    //                                                                              ======
    public void asyncBoot(String queueName, LaJobUnique jobUnique) {
        asyncManager.async(() -> {
            doBoot(queueName, jobUnique);
        });
    }

    protected void doBoot(String queueName, LaJobUnique jobUnique) {
        ConnectionFactory connectionFactory = connectionFactoryProvider.get();
        try (Connection conn = connectionFactory.newConnection(); Channel channel = conn.createChannel()) {
            queueDeclare(queueName, channel);
            basicConsume(queueName, jobUnique, channel);
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException("Failed to wait message queue: " + queueName, e);
        }
    }

    protected void queueDeclare(String queueName, Channel channel) throws IOException {
        channel.queueDeclare(queueName, /*durable*/true, /*exclusive*/false, /*autoDelete*/false, /*arguments*/null);
    }

    protected void basicConsume(String queueName, LaJobUnique jobUnique, Channel channel) throws IOException {
        DeliverCallback deliverCallback = createDeliverCallback(jobUnique);
        channel.basicConsume(queueName, /*autoAck*/true, deliverCallback, consumerTag -> {});
    }

    // -----------------------------------------------------
    //                                       DeliverCallback
    //                                       ---------------
    protected DeliverCallback createDeliverCallback(LaJobUnique jobUnique) {
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        return (consumerTag, delivery) -> {
            bridge.cross(() -> { // to handle errors of launching process
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                launchRabbitJob(jobUnique, message);
            });
        };
    }

    // ===================================================================================
    //                                                                           Lasta Job
    //                                                                           =========
    protected void launchRabbitJob(LaJobUnique jobUnique, String message) {
        jobManager.findJobByUniqueOf(jobUnique).alwaysPresent(job -> {
            job.launchNow(op -> op.param(JOB_PARAMETER_MESSAGE_KEY, message));
        });
    }
}
