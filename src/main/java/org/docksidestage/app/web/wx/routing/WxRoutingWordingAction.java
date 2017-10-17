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
package org.docksidestage.app.web.wx.routing;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingWordingAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [hit]
    // http://localhost:8151/fortress/wx/routing/wording/3
    // [not]
    // http://localhost:8151/fortress/wx/routing/wording/
    // http://localhost:8151/fortress/wx/routing/wording/dockside
    @Execute
    public JsonResponse<RoutingCheckResult> index(Integer wordingId) {
        return asJson(new RoutingCheckResult("index()", wordingId, null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/wording/mystic/sea
    // [not]
    // http://localhost:8151/fortress/wx/routing/wording/sea
    // http://localhost:8151/fortress/wx/routing/wording/sea/3
    @Execute(urlPattern = "{}/@word")
    public JsonResponse<RoutingCheckResult> sea(String wordingId) {
        return asJson(new RoutingCheckResult("sea()", wordingId, null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/wording/land/oneman/3
    // [not]
    // http://localhost:8151/fortress/wx/routing/wording/land/oneman/piari
    @Execute
    public JsonResponse<RoutingCheckResult> land(String wordingId, Integer piariId) {
        return asJson(new RoutingCheckResult("sea()", wordingId, piariId));
    }
}
