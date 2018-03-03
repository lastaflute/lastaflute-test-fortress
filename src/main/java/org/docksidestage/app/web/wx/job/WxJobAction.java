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
package org.docksidestage.app.web.wx.job;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.key.LaJobUnique;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxJobAction extends FortressBaseAction {

    @Resource
    private JobManager jobManager;

    @Resource
    private AsyncManager asyncManager;
    @Resource
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/job/sea/hangar/mystic
    // http://localhost:8151/fortress/wx/job/sea/showbase/oneman
    @Execute
    public JsonResponse<Void> sea(String stage, String jobUnique) {
        jobManager.findJobByUniqueOf(LaJobUnique.of(jobUnique)).alwaysPresent(job -> {
            job.launchNow(op -> op.param("stage", stage));
        });
        return JsonResponse.asEmptyBody();
    }

    // http://localhost:8151/fortress/wx/job/amba/dor
    @Execute
    public JsonResponse<Void> amba(String first) { // has remote api call
        jobManager.findJobByUniqueOf(LaJobUnique.of("amba")).alwaysPresent(amba -> {
            amba.launchNow(op -> op.param("stage", first));
        });
        return JsonResponse.asEmptyBody();
    }
}
