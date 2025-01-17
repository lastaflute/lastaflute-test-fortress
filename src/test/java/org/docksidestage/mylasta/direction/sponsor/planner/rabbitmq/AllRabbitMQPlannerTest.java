package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.annotation.Resource;

import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.unit.UnitFortressJobTestCase;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import com.rabbitmq.client.Envelope;

/**
 * @author jflute
 */
public class AllRabbitMQPlannerTest extends UnitFortressJobTestCase {

    @Resource
    private FortressConfig config;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    public void setUp() throws Exception {
        super.setUp();

        // CurtainBeforeHook の方の起動ログと区別するための境界
        // (hookの方のconsumerはことごとくConnection接続エラーで落ちるはず)
        log("=======================================================================");
        log("==================== ここからテスト対象のConsumerの起動 ===================");
        log("=======================================================================");
    }

    @Override
    protected boolean isUseJobScheduling() {
        return true; // Jobを起動するために
    }

    // ===================================================================================
    //                                                                               Boot
    //                                                                              ======
    public void test_bootAllConsumer_bootOnly() {
        // ## Arrange ##
        Set<String> calledSet = newHashSet();
        AllRabbitMQPlanner planner = createMockAllRabbitMQPlanner(calledSet);

        // ## Act ##
        planner.bootAllConsumer();

        // ## Assert ##
        sleep(5000); // 待たないとログが出ないので

        // cannot assert order because of asynchronous
        assertEquals(4, calledSet.size());
        calledSet.contains("queueDeclare(): seaQueue");
        calledSet.contains("queueDeclare(): landQueue");
        calledSet.contains("basicConsume(): seaQueue");
        calledSet.contains("basicConsume(): landQueue");

        // and visual check, confirm that consumer bootings are correct on log
    }

    public void test_bootAllConsumer_withCallback() {
        // ## Arrange ##
        Set<String> calledSet = newHashSet();
        AllRabbitMQPlanner planner = createMockAllRabbitMQPlannerWithCallback(calledSet);

        // ## Act ##
        planner.bootAllConsumer();

        // ## Assert ##
        sleep(5000); // 待たないとログが出ないので

        // cannot assert order because of asynchronous
        assertEquals(4, calledSet.size());
        calledSet.contains("queueDeclare(): seaQueue");
        calledSet.contains("queueDeclare(): landQueue");
        calledSet.contains("basicConsume(): seaQueue");
        calledSet.contains("basicConsume(): landQueue");

        // and visual check, confirm that two jobs are executed on log
    }

    private AllRabbitMQPlanner createMockAllRabbitMQPlanner(Set<String> calledSet) {
        return doCreateMockAllRabbitMQPlanner(calledSet, /*withCallback*/false);
    }

    private AllRabbitMQPlanner createMockAllRabbitMQPlannerWithCallback(Set<String> calledSet) {
        return doCreateMockAllRabbitMQPlanner(calledSet, /*withCallback*/true);
    }

    private AllRabbitMQPlanner doCreateMockAllRabbitMQPlanner(Set<String> calledSet, boolean withCallback) {
        RabbitMQMocker mocker = new RabbitMQMocker(config);
        mocker.assertCall_queueDeclare(queue -> {
            calledSet.add("queueDeclare(): " + queue);
            log("#rabbit queueDeclare(): {}", queue);
        });
        mocker.assertCall_basicConsume((queue, deliverCallback) -> {
            calledSet.add("basicConsume(): " + queue);
            log("#rabbit basicConsume(): {}", queue);

            if (withCallback) {
                executeCallback(queue, deliverCallback);
            }
        });
        return mocker.createMockAllRabbitMQPlanner();
    }

    private void executeCallback(String queue, DeliverCallback deliverCallback) {
        Envelope envelope = new Envelope(1L, false, queue + "Exchange", queue + "routingKey");

        String bodyText;
        if (queue.startsWith("sea")) {
            bodyText = "{stageName: \"hangar\", oneDayShowCount: 5}";
        } else { // land
            bodyText = "{stageName: \"showbase\", showLastDate: \"2019-12-13\"}";
        }
        String encoding = "UTF-8";
        byte[] bodyBytes;
        try {
            bodyBytes = bodyText.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Failed to get bytes: encoding=" + encoding, e);
        }

        Delivery message = new Delivery(envelope, new BasicProperties(), bodyBytes);
        try {
            deliverCallback.handle(queue + "Tag", message);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to handle the deliver callback: envelope=" + envelope, e);
        }
    }
}
