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

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingDefaultAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8097/fortress/whitebox/routing/default/sea/
    @Execute
    public JsonResponse<RoutingCheckResult> index(String first, OptionalThing<String> second) {
        return asJson(new RoutingCheckResult("index()", first, second.orElse("*second") + " :: "));
    }
}