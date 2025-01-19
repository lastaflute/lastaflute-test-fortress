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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.docksidestage.bizfw.rabbitmq.RabbitMQConsumerManager;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.direction.sponsor.planner.rabbitmq.mock.RabbitMQConsumerMocker;
import org.docksidestage.unit.UnitFortressJobTestCase;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import com.rabbitmq.client.Envelope;

/**
 * mockを使ったplannerのテスト。RabbitMQにはアクセスしない。
 * @author jflute
 */
public class AllRabbitMQPlannerTest extends UnitFortressJobTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private FortressConfig config;
    @Resource
    private JobManager jobManager;
    @Resource
    private RabbitMQConsumerManager consumerManager;

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
        return true; // Jobを起動してlaunchされるか確認するために
    }

    @Override
    protected boolean isUseOneTimeContainer() {
        return true; // destroyの挙動も確認するために (でないとキャッシュされたままでdestoryされない)
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        log("========================= tearDown()のあと =============================");
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
        RabbitMQConsumerMocker mocker = new RabbitMQConsumerMocker(config, consumerManager);
        mocker.assertCall_queueDeclare(queue -> {
            calledSet.add("queueDeclare(): " + queue);
            log("#mq queueDeclare(): {}", queue);
        });
        mocker.assertCall_basicConsume((queue, deliverCallback) -> {
            calledSet.add("basicConsume(): " + queue);
            log("#mq basicConsume(): {}", queue);

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
