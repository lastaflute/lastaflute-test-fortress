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

import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.LaJobHistory;
import org.lastaflute.job.LaScheduledJob;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.job.subsidiary.ExecResultType;
import org.lastaflute.job.subsidiary.LaunchNowOption;
import org.lastaflute.job.subsidiary.LaunchedProcess;

/**
 * @author jflute
 */
public class WxJobConcurrentTest extends UnitFortressBasicTestCase {

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
    //                                                                          Concurrent
    //                                                                          ==========
    // also visual check of serial execution
    public void test_concurrent_wait() {
        // ## Arrange ##
        LaScheduledJob job = findConcurrentJob("mystic-wait");

        // ## Act ##
        LaunchedProcess firstProcess = job.launchNow(op -> setupWaitTime(op));
        LaunchedProcess secondProcess = job.launchNow(op -> setupWaitTime(op));

        // ## Assert ##
        assertExecutingNow(job);
        assertJobHistory(firstProcess.waitForEnding(), ExecResultType.SUCCESS);
        assertJobHistory(secondProcess.waitForEnding(), ExecResultType.SUCCESS);
        assertFalse(job.isExecutingNow());
    }

    public void test_concurrent_quit() {
        // ## Arrange ##
        LaScheduledJob job = findConcurrentJob("mystic-quit");

        // ## Act ##
        LaunchedProcess firstProcess = job.launchNow(op -> setupWaitTime(op));
        LaunchedProcess secondProcess = job.launchNow(op -> setupWaitTime(op));

        // ## Assert ##
        assertExecutingNow(job);
        assertJobHistory(firstProcess.waitForEnding(), ExecResultType.SUCCESS);
        assertJobHistory(secondProcess.waitForEnding(), ExecResultType.QUIT_BY_CONCURRENT);
        assertFalse(job.isExecutingNow());
    }

    public void test_concurrent_error() {
        // ## Arrange ##
        LaScheduledJob job = findConcurrentJob("mystic-error");

        // ## Act ##
        LaunchedProcess firstProcess = job.launchNow(op -> setupWaitTime(op));
        LaunchedProcess secondProcess = job.launchNow(op -> setupWaitTime(op));

        // ## Assert ##
        assertExecutingNow(job);
        assertJobHistory(firstProcess.waitForEnding(), ExecResultType.SUCCESS);
        assertJobHistory(secondProcess.waitForEnding(), ExecResultType.ERROR_BY_CONCURRENT);
        assertFalse(job.isExecutingNow());
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private LaScheduledJob findConcurrentJob(String uniqueCode) {
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
}
