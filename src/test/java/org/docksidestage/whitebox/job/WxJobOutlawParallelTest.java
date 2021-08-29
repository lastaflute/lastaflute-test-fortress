/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.whitebox.job;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;
import org.lastaflute.job.LaScheduledJob;
import org.lastaflute.job.cron4j.Cron4jJob;
import org.lastaflute.job.cron4j.Cron4jJobHistory;
import org.lastaflute.job.cron4j.Cron4jTask;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.job.subsidiary.ExecResultType;
import org.lastaflute.job.subsidiary.JobExecutingSnapshot;
import org.lastaflute.job.subsidiary.LaunchNowOption;
import org.lastaflute.job.subsidiary.LaunchedProcess;
import org.lastaflute.job.subsidiary.SnapshotExecState;

import it.sauronsoftware.cron4j.TaskExecutor;

/**
 * @author jflute
 */
public class WxJobOutlawParallelTest extends UnitFortressBasicTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JobManager jobManager;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    protected boolean isUseJobScheduling() {
        return true;
    }

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_launchNow_parallel_basic() {
        // ## Arrange ##
        LaScheduledJob job = findParallelJob("mystic-parallel");

        // ## Act ##
        LaunchedProcess firstProcess = job.launchNow(op -> setupWaitTime(op).asOutlawParallel());
        LaunchedProcess secondProcess = job.launchNow(op -> setupWaitTime(op).asOutlawParallel());

        // ## Assert ##
        assertState(job);
        assertJobHistory(firstProcess.waitForEnding(), ExecResultType.SUCCESS);
        assertJobHistory(secondProcess.waitForEnding(), ExecResultType.SUCCESS);
        assertOutlawParallelTaskList(job);
        assertExecutorListEmpty(job);
    }

    // ===================================================================================
    //                                                                        Large Launch
    //                                                                        ============
    public void test_launchNow_parallel_large_basic() {
        // ## Arrange ##
        LaScheduledJob job = findParallelJob("mystic-parallel");
        List<LaunchedProcess> processList = new ArrayList<>();

        // ## Act ##
        for (int i = 0; i < 10; i++) {
            LaunchedProcess process = job.launchNow(op -> setupWaitTime(op).asOutlawParallel());
            processList.add(process);
        }

        // ## Assert ##
        assertState(job);
        for (LaunchedProcess process : processList) {
            assertJobHistory(process.waitForEnding(), ExecResultType.SUCCESS);
        }
        assertOutlawParallelTaskList(job);
        assertExecutorListEmpty(job);
    }

    public void test_launchNow_parallel_large_more() {
        // ## Arrange ##
        LaScheduledJob job = findParallelJob("mystic-parallel");
        job.launchNow(op -> setupWaitTime(op).asOutlawParallel()); // to initialize anything
        long before = Runtime.getRuntime().totalMemory();

        // ## Act ##
        for (int i = 0; i < 5; i++) { // change for more more large test
            List<LaunchedProcess> processList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                LaunchedProcess process = job.launchNow(op -> setupWaitTime(op).asOutlawParallel());
                processList.add(process);
            }

            // ## Assert ##
            assertState(job);
            for (LaunchedProcess process : processList) {
                assertJobHistory(process.waitForEnding(), ExecResultType.SUCCESS);
            }
            assertOutlawParallelTaskList(job);
        }
        assertExecutorListEmpty(job);

        sleep(1000);
        log("");
        log("[History]");
        List<LaJobHistory> historyList = Cron4jJobHistory.list();
        for (LaJobHistory history : historyList) {
            log(history);
        }

        long afterNonGc = Runtime.getRuntime().totalMemory();
        Runtime.getRuntime().gc();
        long afterWithGc = Runtime.getRuntime().totalMemory();
        log("");
        log("[Memory]");
        log("before large   : {}", before);
        log("after(non-gc)  : {}", afterNonGc);
        log("after(with-gc) : {}", afterWithGc);
    }

    // ===================================================================================
    //                                                                            Stop Now
    //                                                                            ========
    public void test_launchNow_parallel_stopNow_basic() {
        // ## Arrange ##
        LaScheduledJob job = findParallelJob("mystic-parallel");

        // ## Act ##
        LaunchedProcess firstProcess = job.launchNow(op -> setupWaitTime(op).asOutlawParallel());
        LaunchedProcess secondProcess = job.launchNow(op -> setupWaitTime(op).asOutlawParallel());

        // ## Assert ##
        job.stopNow();
        assertState(job);
        assertJobHistory(firstProcess.waitForEnding(), ExecResultType.CAUSED_BY_APPLICATION);
        assertJobHistory(secondProcess.waitForEnding(), ExecResultType.CAUSED_BY_APPLICATION);
        assertOutlawParallelTaskList(job);
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private LaScheduledJob findParallelJob(String uniqueCode) {
        LaJobUnique jobUnique = LaJobUnique.of(uniqueCode);
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();
        assertTrue(job.isOutlawParallelGranted());
        return job;
    }

    private LaunchNowOption setupWaitTime(LaunchNowOption op) {
        return op.param("waitTime", 100L);
    }

    private void assertState(LaScheduledJob job) {
        boolean existsNotExecutingNow = false;
        boolean existsExecutingNow = false;
        for (int i = 0; i < 10; i++) {
            JobExecutingSnapshot snapshot = job.takeSnapshotNow();
            boolean executingNow = job.isExecutingNow(); // should near snapshot (but not perfect logic)
            int executingCount = snapshot.getExecutingCount();
            OptionalThing<SnapshotExecState> mainExecState = snapshot.getMainExecState();
            List<SnapshotExecState> outlawParallelExecStateList = snapshot.getOutlawParallelExecStateList();
            log(snapshot);
            if (executingCount == 0) {
                // sometimes different by snapshot and determination timing so use exists-boolean
                if (!executingNow) {
                    existsNotExecutingNow = true;
                }
            } else {
                if (executingNow) {
                    existsExecutingNow = true;
                }
            }
            assertFalse(mainExecState.isPresent()); // always
            assertEquals(executingCount, outlawParallelExecStateList.size());
            sleep(80);
        }
        assertTrue(existsNotExecutingNow);
        assertTrue(existsExecutingNow);
    }

    private void assertJobHistory(OptionalThing<LaJobHistory> optHistory, ExecResultType execResultType) {
        LaJobHistory history = optHistory.get();
        log(history);
        assertEquals(execResultType, history.getExecResultType());
    }

    private void assertOutlawParallelTaskList(LaScheduledJob job) { // called after ending
        List<Cron4jTask> beforeList = ((Cron4jJob) job).getOutlawParallelTaskList();
        log("outlawParallelTaskList: before={}", beforeList.size()); // expected about full count

        LaunchedProcess process = job.launchNow(op -> setupWaitTime(op).asOutlawParallel()); // with deleting ended tasks
        process.waitForEnding();

        List<Cron4jTask> afterList = ((Cron4jJob) job).getOutlawParallelTaskList();
        log("outlawParallelTaskList: after={}", afterList.size());
        assertEquals(1, afterList.size()); // so only one
    }

    private void assertExecutorListEmpty(LaScheduledJob job) {
        List<TaskExecutor> executorList = ((Cron4jJob) job).getCron4jNow().getCron4jScheduler().getExecutorList();
        assertEquals(0, executorList.size());
    }
}
