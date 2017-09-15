/*
 * Copyright 2015-2017 the original author or authors.
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
import org.lastaflute.job.exception.JobAlreadyDisappearedException;
import org.lastaflute.job.exception.JobAlreadyUnscheduleException;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.job.subsidiary.ExecResultType;
import org.lastaflute.job.subsidiary.LaunchedProcess;

/**
 * @author jflute
 */
public class WxJobManagerTest extends UnitFortressWebTestCase {

    @Resource
    private JobManager jobManager;

    @Override
    protected boolean isUseJobScheduling() {
        return true;
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
        assertFalse(job.isExecutingNow());
        assertTrue(job.isNonCron());
        assertFalse(job.isUnscheduled());

        // ## Act ##
        job.unschedule();

        // ## Assert ##
        assertTrue(job.isUnscheduled());
        assertTrue(jobManager.findJobByUniqueOf(jobUnique).isPresent());
        assertException(JobAlreadyUnscheduleException.class, () -> job.launchNow());

        // ## Act ##
        synchronized (job) {
            job.reschedule("* * * * *", op -> {});
            job.becomeNonCron(); // immediately non-cron (needs rescheduleNonCron()?)
        }

        // ## Assert ##
        assertFalse(job.isUnscheduled());
        LaunchedProcess process = job.launchNow();
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
    }

    // -----------------------------------------------------
    //                                             Triggered
    //                                             ---------
    public void test_unschedule_triggered_to_reschedule_to_trigger() {
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
        seaJob.reschedule("* * * * *", op -> {});

        // ## Assert ##
        LaunchedProcess process = landJob.launchNow();
        process.waitForEnding();
        sleep(3000); // to watch log
        List<LaJobHistory> historyList = jobManager.searchJobHistoryList();
        assertEquals(2, historyList.size());
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
    }
}
