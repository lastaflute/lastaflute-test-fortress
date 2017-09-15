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

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressTestCase;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;
import org.lastaflute.job.LaScheduledJob;
import org.lastaflute.job.exception.JobAlreadyDisappearedException;
import org.lastaflute.job.exception.JobAlreadyUnscheduleException;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.job.subsidiary.LaunchedProcess;

/**
 * @author jflute
 */
public class WxJobManagementTest extends UnitFortressTestCase {

    @Resource
    private JobManager jobManager;

    @Override
    protected boolean isUseJobScheduling() {
        return true;
    }

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
