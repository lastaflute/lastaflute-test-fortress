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
package org.docksidestage.app.job.concurrent;

import org.lastaflute.job.LaJob;
import org.lastaflute.job.LaJobRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class MysticConcurrentJob implements LaJob {

    private static final Logger logger = LoggerFactory.getLogger(MysticConcurrentJob.class);

    // ===================================================================================
    //                                                                             Job Run
    //                                                                             =======
    @Override
    public void run(LaJobRuntime runtime) {
        Thread currentThread = Thread.currentThread();
        logger.debug("currentThread: {}, {}", currentThread.getName(), currentThread.hashCode());

        waitFirstIfNeeds(runtime);
        runtime.stopIfNeeds();
    }

    private void waitFirstIfNeeds(LaJobRuntime runtime) {
        Long waitFirst = (Long) runtime.getParameterMap().get("waitTime");
        if (waitFirst != null) {
            logger.debug("...Waiting ({}): job={}", waitFirst, getClass().getSimpleName());
            try {
                Thread.sleep(waitFirst);
            } catch (InterruptedException e) {
                throw new IllegalStateException("Failed to sleep.", e);
            }
        }
    }
}
