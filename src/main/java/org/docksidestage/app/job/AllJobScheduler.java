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
package org.docksidestage.app.job;

import javax.annotation.Resource;

import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.app.job.base.JobUserTraceAssist;
import org.docksidestage.app.job.challenge.AmbaJob;
import org.docksidestage.app.job.challenge.BonvoJob;
import org.docksidestage.app.job.challenge.DstoreJob;
import org.docksidestage.app.job.challenge.PiariJob;
import org.docksidestage.app.job.concurrent.MysticConcurrentJob;
import org.docksidestage.app.logic.context.AccessContextLogic;
import org.docksidestage.app.logic.context.AccessContextLogic.ClientInfoSupplier;
import org.docksidestage.app.logic.context.AccessContextLogic.UserInfoSupplier;
import org.docksidestage.app.logic.context.AccessContextLogic.UserTypeSupplier;
import org.lastaflute.job.LaCron;
import org.lastaflute.job.LaJobRunner;
import org.lastaflute.job.LaJobScheduler;

/**
 * @author jflute
 */
public class AllJobScheduler implements LaJobScheduler {

    protected static final String APP_TYPE = "JOB";

    @Resource
    private AccessContextLogic accessContextLogic;
    @Resource
    private JobUserTraceAssist jobUserTraceAssist;

    @Override
    public void schedule(LaCron cron) {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // suppress cron for quick boot, mainly test settings here
        // (scheduling test is at orleans)
        // _/_/_/_/_/_/_/_/_/_/
        // basic example
        cron.registerNonCron(SeaJob.class, waitIfConcurrent(), op -> op.uniqueBy("mystic"));
        cron.registerNonCron(LandJob.class, quitIfConcurrent(), op -> op.uniqueBy("oneman"));

        // various challenge
        cron.registerNonCron(PiariJob.class, quitIfConcurrent(), op -> op.uniqueBy("piari").params(() -> {
            return DfCollectionUtil.newHashMap("celebration", "plaza");
        }));
        cron.registerNonCron(BonvoJob.class, errorIfConcurrent(), op -> op.uniqueBy("bonvo"));
        cron.registerNonCron(DstoreJob.class, errorIfConcurrent(), op -> op.uniqueBy("dstore"));
        cron.registerNonCron(AmbaJob.class, errorIfConcurrent(), op -> op.uniqueBy("amba"));

        // test of concurrent
        cron.registerNonCron(MysticConcurrentJob.class, waitIfConcurrent(), op -> op.uniqueBy("mystic-wait"));
        cron.registerNonCron(MysticConcurrentJob.class, quitIfConcurrent(), op -> op.uniqueBy("mystic-quit"));
        cron.registerNonCron(MysticConcurrentJob.class, errorIfConcurrent(), op -> op.uniqueBy("mystic-error"));
        cron.registerNonCron(MysticConcurrentJob.class, waitIfConcurrent(), op -> {
            op.uniqueBy("mystic-parallel").grantOutlawParallel();
        });
    }

    @Override
    public LaJobRunner createRunner() {
        return new LaJobRunner().useAccessContext(resource -> {
            UserTypeSupplier userTypeSupplier = () -> jobUserTraceAssist.extractUserType(resource);
            UserInfoSupplier userBeanSupplier = () -> jobUserTraceAssist.extractUserId(resource);
            ClientInfoSupplier clientInfoSupplier = () -> jobUserTraceAssist.extractClientInfo(resource);
            return accessContextLogic.create(resource, userTypeSupplier, userBeanSupplier, () -> APP_TYPE, clientInfoSupplier);
        });
    }

    // you can hook and launchNow() at booting
    //@Override
    //public void hookJustAfterBooting(LaSchedulingNow schedulingNow) {
    //    schedulingNow.findJobByUniqueOf(LaJobUnique.of("mystic")).alwaysPresent(job -> {
    //        job.launchNow();
    //    });
    //}
}
