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

import java.util.List;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;
import org.lastaflute.job.LaScheduledJob;
import org.lastaflute.job.cron4j.Cron4jJob;
import org.lastaflute.job.cron4j.Cron4jScheduler;
import org.lastaflute.job.exception.JobAlreadyDisappearedException;
import org.lastaflute.job.exception.JobAlreadyUnscheduleException;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.job.subsidiary.ExecResultType;
import org.lastaflute.job.subsidiary.LaunchedProcess;

import it.sauronsoftware.cron4j.Scheduler;
import it.sauronsoftware.cron4j.SchedulingPattern;
import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutor;

/**
 * @author jflute
 */
public class WxJobManagerTest extends UnitFortressWebTestCase {

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
    //                                                                          Reschedule
    //                                                                          ==========
    public void test_reschedule_to_reschedule_to_launch_to_stop() {
        // ## Arrange ##
        LaJobUnique jobUnique = LaJobUnique.of("mystic");
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();
        assertCron4jNativeTaskNotExists(job); // as default

        // ## Act ##
        job.reschedule("0 0 1 1 0", op -> {});

        // ## Assert ##
        assertFalse(job.isExecutingNow());
        assertFalse(job.isNonCron());
        assertFalse(job.isUnscheduled());
        assertCron4jNativeTaskExists(job);
        assertEquals("0 0 1 1 0", extractNativeSchedulingExp(job));

        // ## Act ##
        job.reschedule("0 0 2 2 0", op -> {});

        // ## Assert ##
        assertFalse(job.isExecutingNow());
        assertFalse(job.isNonCron());
        assertFalse(job.isUnscheduled());
        assertCron4jNativeTaskExists(job);
        assertEquals("0 0 2 2 0", extractNativeSchedulingExp(job));

        // ## Act ##
        LaunchedProcess process = job.launchNow();
        assertExecutorExists(job);
        job.stopNow();
        LaJobHistory history = process.waitForEnding().get(); // exists
        assertEquals(ExecResultType.SUCCESS, history.getExecResultType()); // no implementation for stop
    }

    // ===================================================================================
    //                                                                          Unschedule
    //                                                                          ==========
    // -----------------------------------------------------
    //                                         to Reschedule
    //                                         -------------
    public void test_unschedule_to_reschedule_to_disappear() {
        // ## Arrange ##
        LaJobUnique jobUnique = LaJobUnique.of("mystic");
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();
        job.reschedule("0 0 1 1 0", op -> {}); // test as valid-cron job
        assertFalse(job.isExecutingNow());
        assertFalse(job.isNonCron());
        assertFalse(job.isUnscheduled());
        assertEquals("0 0 1 1 0", extractNativeSchedulingExp(job));

        // ## Act ##
        job.unschedule();

        // ## Assert ##
        assertTrue(job.isUnscheduled());
        assertTrue(jobManager.findJobByUniqueOf(jobUnique).isPresent());
        assertException(JobAlreadyUnscheduleException.class, () -> job.launchNow());
        assertCron4jNativeTaskNotExists(job);

        // ## Act ##
        job.reschedule("0 0 2 2 0", op -> {});

        // ## Assert ##
        assertFalse(job.isUnscheduled());
        assertCron4jNativeTaskExists(job);
        assertEquals("0 0 2 2 0", extractNativeSchedulingExp(job));
        assertExecutorNotExists(job);
        LaunchedProcess process = job.launchNow();
        assertExecutorExists(job);
        LaJobHistory history = process.waitForEnding().get();
        assertEquals(jobUnique, history.getJobUnique().get());

        // ## Act ##
        job.disappear();

        // ## Assert ##
        assertFalse(job.isUnscheduled());
        assertTrue(job.isDisappeared());
        assertFalse(jobManager.findJobByUniqueOf(jobUnique).isPresent());
        assertException(JobAlreadyDisappearedException.class, () -> job.launchNow());
        assertException(JobAlreadyDisappearedException.class, () -> job.reschedule("* * * * *", op -> {}));
        assertException(JobAlreadyDisappearedException.class, () -> job.becomeNonCron());
        assertException(JobAlreadyDisappearedException.class, () -> job.launchNow());
        assertCron4jNativeTaskNotExists(job);
        assertExecutorNotExists(job);
    }

    // -----------------------------------------------------
    //                                             Triggered
    //                                             ---------
    public void test_unschedule_triggered_to_reschedule_to_trigger() {
        // ## Arrange ##
        LaJobUnique seaUnique = LaJobUnique.of("mystic");
        LaScheduledJob seaJob = jobManager.findJobByUniqueOf(seaUnique).get();
        assertCron4jNativeTaskNotExists(seaJob);
        LaJobUnique landUnique = LaJobUnique.of("oneman");
        LaScheduledJob landJob = jobManager.findJobByUniqueOf(landUnique).get();
        assertCron4jNativeTaskNotExists(landJob);
        landJob.registerNext(seaJob.getJobKey());

        // ## Act ##
        seaJob.unschedule();

        // ## Assert ##
        assertFalse(seaJob.isExecutingNow());
        assertTrue(seaJob.isUnscheduled());
        assertFalse(seaJob.isDisappeared());
        assertCron4jNativeTaskNotExists(seaJob);

        // ## Act ##
        seaJob.reschedule("* * * * *", op -> {});

        // ## Assert ##
        assertCron4jNativeTaskExists(seaJob);
        LaunchedProcess process = landJob.launchNow();
        process.waitForEnding();
        sleep(3000); // to watch log
        List<LaJobHistory> historyList = jobManager.searchJobHistoryList();
        assertTrue(historyList.size() >= 2); // may have previous histories in batch execution of unit test
        LaJobHistory seaHistory = historyList.stream().filter(hist -> hist.getJobUnique().get().equals(seaUnique)).findFirst().get();
        assertEquals(seaUnique, seaHistory.getJobUnique().get());
        assertEquals(ExecResultType.SUCCESS, seaHistory.getExecResultType());
    }

    public void test_unschedule_triggered_to_trigger() {
        // ## Arrange ##
        LaJobUnique seaUnique = LaJobUnique.of("mystic");
        LaScheduledJob seaJob = jobManager.findJobByUniqueOf(seaUnique).get();
        LaJobUnique landUnique = LaJobUnique.of("oneman");
        LaScheduledJob landJob = jobManager.findJobByUniqueOf(landUnique).get();
        landJob.registerNext(seaJob.getJobKey());

        // ## Act ##
        seaJob.unschedule();

        // ## Assert ##
        assertFalse(seaJob.isExecutingNow());
        assertTrue(seaJob.isUnscheduled());
        assertFalse(seaJob.isDisappeared());

        // ## Act ##
        // #thinking jflute what should i do? (2017/09/15)
        LaunchedProcess process = landJob.launchNow();
        process.waitForEnding();
        sleep(3000); // to watch log

        // ## Assert ##
        List<LaJobHistory> historyList = jobManager.searchJobHistoryList();
        assertEquals(1, historyList.size());
    }

    // -----------------------------------------------------
    //                                             Executing
    //                                             ---------
    public void test_unschedule_executing_stop() {
        // ## Arrange ##
        LaJobUnique jobUnique = LaJobUnique.of("mystic");
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();

        // ## Act ##
        job.launchNow(op -> op.param("waitFirst", 2000L));
        sleep(500);
        job.unschedule();

        // ## Assert ##
        assertTrue(job.isExecutingNow());
        assertTrue(job.isUnscheduled());
        assertFalse(job.isDisappeared());
        job.stopNow(); // can be called
        assertException(JobAlreadyUnscheduleException.class, () -> job.launchNow());
        sleep(3000);
        List<LaJobHistory> historyList = jobManager.searchJobHistoryList();
        assertEquals(1, historyList.size());
        LaJobHistory history = historyList.stream().filter(hist -> hist.getJobUnique().get().equals(jobUnique)).findFirst().get();
        assertEquals(ExecResultType.CAUSED_BY_APPLICATION, history.getExecResultType()); // interrupted
    }

    public void test_unschedule_executing_triggering() {
        // ## Arrange ##
        LaJobUnique seaUnique = LaJobUnique.of("mystic");
        LaScheduledJob seaJob = jobManager.findJobByUniqueOf(seaUnique).get();
        LaJobUnique landUnique = LaJobUnique.of("oneman");
        LaScheduledJob landJob = jobManager.findJobByUniqueOf(landUnique).get();
        landJob.registerNext(seaJob.getJobKey());

        // ## Act ##
        landJob.launchNow(op -> op.param("waitFirst", 2000L));
        sleep(500);
        landJob.unschedule();

        // ## Assert ##
        assertTrue(landJob.isExecutingNow());
        assertTrue(landJob.isUnscheduled());
        assertFalse(landJob.isDisappeared());
        assertFalse(seaJob.isExecutingNow());
        assertFalse(seaJob.isUnscheduled());
        assertFalse(seaJob.isDisappeared());
        sleep(5000); // wait for triggered job ending
        List<LaJobHistory> historyList = jobManager.searchJobHistoryList();
        assertEquals(2, historyList.size());
        LaJobHistory seaHistory = historyList.stream().filter(hist -> hist.getJobUnique().get().equals(seaUnique)).findFirst().get();
        assertEquals(seaUnique, seaHistory.getJobUnique().get());
        assertEquals(ExecResultType.SUCCESS, seaHistory.getExecResultType());
        assertException(JobAlreadyUnscheduleException.class, () -> landJob.launchNow());
    }

    // -----------------------------------------------------
    //                                          to Disappear
    //                                          ------------
    public void test_unschedule_to_disappear() {
        // ## Arrange ##
        LaJobUnique jobUnique = LaJobUnique.of("mystic");
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();
        job.reschedule("0 0 1 1 0", op -> {}); // test as valid-cron job

        // ## Act ##
        job.unschedule();

        // ## Assert ##
        assertFalse(job.isExecutingNow());
        assertTrue(job.isUnscheduled());
        assertFalse(job.isDisappeared());

        // ## Act ##
        job.disappear();

        // ## Assert ##
        assertTrue(job.isUnscheduled());
        assertTrue(job.isDisappeared());
        assertFalse(jobManager.findJobByUniqueOf(jobUnique).isPresent());
        assertException(JobAlreadyDisappearedException.class, () -> job.launchNow());
        assertException(JobAlreadyDisappearedException.class, () -> job.reschedule("* * * * *", op -> {}));
        assertException(JobAlreadyDisappearedException.class, () -> job.becomeNonCron());
        assertException(JobAlreadyDisappearedException.class, () -> job.launchNow());
        assertCron4jNativeTaskNotExists(job);
        assertExecutorNotExists(job);
    }

    // ===================================================================================
    //                                                                           Disappear
    //                                                                           =========
    public void test_disappear_executing_stop() {
        // ## Arrange ##
        LaJobUnique jobUnique = LaJobUnique.of("mystic");
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();

        // ## Act ##
        job.launchNow(op -> op.param("waitFirst", 2000L));
        sleep(500);
        job.disappear();

        // ## Assert ##
        assertTrue(job.isExecutingNow());
        assertFalse(job.isUnscheduled());
        assertTrue(job.isDisappeared());
        job.stopNow(); // can be called
        assertException(JobAlreadyDisappearedException.class, () -> job.launchNow());
        sleep(3000);
        List<LaJobHistory> historyList = jobManager.searchJobHistoryList();
        assertEquals(1, historyList.size());
        LaJobHistory history = historyList.stream().filter(hist -> hist.getJobUnique().get().equals(jobUnique)).findFirst().get();
        assertEquals(ExecResultType.CAUSED_BY_APPLICATION, history.getExecResultType()); // interrupted
    }

    // ===================================================================================
    //                                                                             NonCron
    //                                                                             =======
    public void test_becomeNonCron_to_reschedule() {
        // ## Arrange ##
        LaJobUnique jobUnique = LaJobUnique.of("mystic");
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();
        assertCron4jNativeTaskNotExists(job); // as default
        job.reschedule("0 0 1 1 0", op -> {}); // test as valid-cron job
        assertFalse(job.isExecutingNow());
        assertFalse(job.isNonCron());
        assertFalse(job.isUnscheduled());
        assertCron4jNativeTaskExists(job);

        // ## Act ##
        job.becomeNonCron();

        // ## Assert ##
        assertFalse(job.isExecutingNow());
        assertTrue(job.isNonCron());
        assertFalse(job.isUnscheduled());
        assertCron4jNativeTaskNotExists(job);

        // ## Act ##
        job.reschedule("0 0 1 1 0", op -> {});

        // ## Assert ##
        assertFalse(job.isExecutingNow());
        assertFalse(job.isNonCron());
        assertFalse(job.isUnscheduled());
        assertCron4jNativeTaskExists(job);
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    protected void assertCron4jNativeTaskExists(LaScheduledJob job) {
        Cron4jJob cron4jJob = toCron4jJob(job);
        Cron4jScheduler cron4jScheduler = cron4jJob.getCron4jNow().getCron4jScheduler();
        cron4jJob.getCron4jId().ifPresent(id -> {
            Task taskAfterReschedule = cron4jScheduler.getNativeScheduler().getTask(id.value());
            assertNotNull(taskAfterReschedule);
            assertEquals(cron4jJob.getCron4jTask(), taskAfterReschedule);
        });
    }

    protected void assertCron4jNativeTaskNotExists(LaScheduledJob job) {
        Cron4jJob cron4jJob = toCron4jJob(job);
        Cron4jScheduler cron4jScheduler = cron4jJob.getCron4jNow().getCron4jScheduler();
        cron4jJob.getCron4jId().ifPresent(id -> {
            assertNull(cron4jScheduler.getNativeScheduler().getTask(id.value()));
        });
    }

    protected void assertExecutorExists(LaScheduledJob job) {
        Cron4jJob cron4jJob = toCron4jJob(job);
        Cron4jScheduler cron4jScheduler = cron4jJob.getCron4jNow().getCron4jScheduler();
        List<TaskExecutor> executorList = cron4jScheduler.findExecutorList(cron4jJob.getCron4jTask());
        assertHasAnyElement(executorList);
    }

    protected void assertExecutorNotExists(LaScheduledJob job) {
        Cron4jJob cron4jJob = toCron4jJob(job);
        Cron4jScheduler cron4jScheduler = cron4jJob.getCron4jNow().getCron4jScheduler();
        List<TaskExecutor> executorList = cron4jScheduler.findExecutorList(cron4jJob.getCron4jTask());
        assertHasZeroElement(executorList);
    }

    protected String extractNativeSchedulingExp(LaScheduledJob job) {
        Cron4jJob cron4jJob = toCron4jJob(job);
        String nativeId = cron4jJob.getCron4jId().get().value();
        Scheduler nativeScheduler = cron4jJob.getCron4jNow().getCron4jScheduler().getNativeScheduler();
        SchedulingPattern schedulingPattern = nativeScheduler.getSchedulingPattern(nativeId);
        return schedulingPattern.toString();
    }

    protected Cron4jJob toCron4jJob(LaScheduledJob job) {
        return (Cron4jJob) job;
    }
}
