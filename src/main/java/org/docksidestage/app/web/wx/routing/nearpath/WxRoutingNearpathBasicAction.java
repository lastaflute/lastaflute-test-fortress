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
package org.docksidestage.app.web.wx.routing.nearpath;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.routing.RoutingCheckResult;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.util.LaActionExecuteUtil;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingNearpathBasicAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/routing/nearpath/basic/sea/
    // http://localhost:8151/fortress/wx/routing/nearpath/basic/string/
    // http://localhost:8151/fortress/wx/routing/nearpath/basic/number/
    @Execute
    public JsonResponse<RoutingCheckResult> index(String first) {
        return asJson(new RoutingCheckResult(getExecuteName(), first, "*first only"));
    }

    // http://localhost:8151/fortress/wx/routing/nearpath/basic/string/sea
    @Execute
    public JsonResponse<RoutingCheckResult> string(String first) {
        return asJson(new RoutingCheckResult(getExecuteName(), first, "*first only"));
    }

    // http://localhost:8151/fortress/wx/routing/nearpath/basic/number/1
    @Execute
    public JsonResponse<RoutingCheckResult> number(Integer first) {
        return asJson(new RoutingCheckResult(getExecuteName(), first, "*first only"));
    }

    private String getExecuteName() {
        return LaActionExecuteUtil.getActionExecute().toSimpleMethodExp();
    }
}
