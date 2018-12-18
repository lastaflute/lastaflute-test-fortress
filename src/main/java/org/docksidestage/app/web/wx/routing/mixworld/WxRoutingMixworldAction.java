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
package org.docksidestage.app.web.wx.routing.mixworld;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.util.LaActionExecuteUtil;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingMixworldAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/routing/mixworld/sea
    // http://localhost:8151/fortress/wx/routing/mixworld/sea/land
    @Execute
    public JsonResponse<RoutingMixworldCheckResult> index(String first, OptionalThing<String> second) {
        return asJson(new RoutingMixworldCheckResult(getExecuteName(), first, second.orElse("*second")));
    }

    // http://localhost:8151/fortress/wx/routing/mixworld/maihama/sea/land
    @Execute
    public JsonResponse<RoutingMixworldCheckResult> maihama(String first, String second) {
        return asJson(new RoutingMixworldCheckResult(getExecuteName(), first, second));
    }

    private String getExecuteName() {
        return LaActionExecuteUtil.getActionExecute().toSimpleMethodExp();
    }
}
