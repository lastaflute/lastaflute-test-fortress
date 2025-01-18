package org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.unit.UnitFortressJobTestCase;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;

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
    @Resource
    private JobManager jobManager;

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
        Set<String> calledSet = createCallerSet();
        AllRabbitMQPlanner planner = createMockAllRabbitMQPlanner(calledSet);

        // ## Act ##
        planner.bootAllConsumer();

        // ## Assert ##
        sleep(2000); // 待たないとログが出ないので

        // cannot assert order because of asynchronous
        log("calledSet: {}", calledSet);
        assertEquals(4, calledSet.size());
        calledSet.contains("queueDeclare(): seaQueue");
        calledSet.contains("queueDeclare(): landQueue");
        calledSet.contains("basicConsume(): seaQueue");
        calledSet.contains("basicConsume(): landQueue");

        // and visual check, confirm that consumer bootings are correct on log
        List<LaJobHistory> jobHistoryList = jobManager.searchJobHistoryList();
        log("jobHistory: {}", jobHistoryList);
        assertHasZeroElement(jobHistoryList); // because of booting only
    }

    public void test_bootAllConsumer_withCallback() {
        // ## Arrange ##
        Set<String> calledSet = createCallerSet();
        AllRabbitMQPlanner planner = createMockAllRabbitMQPlannerWithCallback(calledSet);

        // ## Act ##
        planner.bootAllConsumer();

        // ## Assert ##
        sleep(2000); // 待たないとログが出ないので

        // cannot assert order because of asynchronous
        log("calledSet: {}", calledSet);
        assertEquals(4, calledSet.size());
        calledSet.contains("queueDeclare(): seaQueue");
        calledSet.contains("queueDeclare(): landQueue");
        calledSet.contains("basicConsume(): seaQueue");
        calledSet.contains("basicConsume(): landQueue");

        // and visual check, confirm that two jobs are executed on log
        List<LaJobHistory> jobHistoryList = jobManager.searchJobHistoryList();
        log("jobHistory:");
        for (LaJobHistory jobHistory : jobHistoryList) {
            log("  " + jobHistory);
        }
        assertEquals(2, jobHistoryList.size());
        List<String> uniqueList = jobHistoryList.stream().map(history -> {
            return history.getJobUnique().get().value();
        }).collect(Collectors.toList());
        assertTrue(uniqueList.contains(AllRabbitMQPlanner.mysticJobUnique));
        assertTrue(uniqueList.contains(AllRabbitMQPlanner.onemanJobUnique));
    }

    // ===================================================================================
    //                                                                         Test Helper
    //                                                                         ===========
    private CopyOnWriteArraySet<String> createCallerSet() {
        return new CopyOnWriteArraySet<>(); // used in multi-thread
    }

    // -----------------------------------------------------
    //                                               Planner
    //                                               -------
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

    // -----------------------------------------------------
    //                                       DeliverCallback
    //                                       ---------------
    private void executeCallback(String queue, DeliverCallback deliverCallback) {
        Envelope envelope = new Envelope(1L, false, queue + "Exchange", queue + "routingKey");

        String bodyText = prepareBodyText(queue);
        byte[] bodyBytes = toBodyBytes(bodyText);

        Delivery message = new Delivery(envelope, new BasicProperties(), bodyBytes);
        try {
            String consumerTag = queue + "Tag"; // tekito
            deliverCallback.handle(consumerTag, message);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to handle the deliver callback: envelope=" + envelope, e);
        }
    }

    private String prepareBodyText(String queue) {
        String bodyText;
        if (queue.startsWith("sea")) {
            bodyText = "{stageName: \"hangar\", oneDayShowCount: 5}";
        } else if (queue.startsWith("land")) {
            bodyText = "{stageName: \"showbase\", showLastDate: \"2019-12-13\"}";
        } else {
            throw new IllegalStateException("Unknown queue: " + queue);
        }
        return bodyText;
    }

    private byte[] toBodyBytes(String bodyText) {
        String encoding = "UTF-8";
        byte[] bodyBytes;
        try {
            bodyBytes = bodyText.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Failed to get bytes: encoding=" + encoding, e);
        }
        return bodyBytes;
    }
}
