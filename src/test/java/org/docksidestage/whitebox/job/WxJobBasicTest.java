/*
 * Copyright 2015-2022 the original author or authors.
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

import org.dbflute.optional.OptionalThing;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;
import org.lastaflute.job.LaScheduledJob;
import org.lastaflute.job.cron4j.Cron4jJob;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.job.subsidiary.ExecResultType;
import org.lastaflute.job.subsidiary.LaunchNowOption;
import org.lastaflute.job.subsidiary.LaunchedProcess;

import it.sauronsoftware.cron4j.TaskExecutor;

/**
 * @author jflute
 */
public class WxJobBasicTest extends UnitFortressBasicTestCase {

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
    //                                                                        Basic Launch
    //                                                                        ============
    public void test_launchNow_basic() {
        // ## Arrange ##
        LaScheduledJob job = findBasicJob("mystic");

        // ## Act ##
        LaunchedProcess process = job.launchNow(op -> setupWaitTime(op));

        // ## Assert ##
        assertExecutingNow(job);
        assertJobHistory(process.waitForEnding(), ExecResultType.SUCCESS);
        assertFalse(job.isExecutingNow());
        assertExecutorListEmpty(job);
    }

    // ===================================================================================
    //                                                                            Stop Now
    //                                                                            ========
    public void test_launchNow_stopNow() {
        // ## Arrange ##
        LaScheduledJob job = findBasicJob("mystic");

        // ## Act ##
        LaunchedProcess process = job.launchNow(op -> setupWaitTime(op));

        // ## Assert ##
        job.stopNow();
        assertExecutingNow(job);
        assertJobHistory(process.waitForEnding(), ExecResultType.CAUSED_BY_APPLICATION);
        assertFalse(job.isExecutingNow());
        assertExecutorListEmpty(job);
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private LaScheduledJob findBasicJob(String uniqueCode) {
        LaJobUnique jobUnique = LaJobUnique.of(uniqueCode);
        LaScheduledJob job = jobManager.findJobByUniqueOf(jobUnique).get();
        return job;
    }

    private LaunchNowOption setupWaitTime(LaunchNowOption op) {
        return op.param("waitTime", 100L);
    }

    private void assertExecutingNow(LaScheduledJob job) {
        boolean executingNow = false;
        for (int i = 0; i < 10; i++) {
            if (job.isExecutingNow()) {
                executingNow = true;
            }
            sleep(50);
        }
        assertTrue(executingNow);
    }

    private void assertJobHistory(OptionalThing<LaJobHistory> optHistory, ExecResultType execResultType) {
        LaJobHistory history = optHistory.get();
        log(history);
        assertEquals(execResultType, history.getExecResultType());
    }

    private void assertExecutorListEmpty(LaScheduledJob job) {
        List<TaskExecutor> executorList = ((Cron4jJob) job).getCron4jNow().getCron4jScheduler().getExecutorList();
        assertEquals(0, executorList.size());
    }
}
