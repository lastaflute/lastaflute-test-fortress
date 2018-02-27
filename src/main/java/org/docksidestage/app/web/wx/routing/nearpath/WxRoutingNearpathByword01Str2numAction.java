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
package org.docksidestage.app.web.wx.routing.nearpath;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.routing.RoutingCheckResult;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingNearpathByword01Str2numAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [hit]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword01/str2num/sea/
    // http://localhost:8151/fortress/wx/routing/nearpath/byword01/str2num/named/
    // [not]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword01/str2num/
    // http://localhost:8151/fortress/wx/routing/nearpath/byword01/str2num/mystic/named/
    @Execute
    public JsonResponse<RoutingCheckResult> index(String first) {
        return asJson(new RoutingCheckResult("index()", first, null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword01/str2num/1/named/
    // [not]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword01/str2num/mystic/named/
    @Execute(urlPattern = "{}/@word")
    public JsonResponse<RoutingCheckResult> named(Integer first) {
        return asJson(new RoutingCheckResult("named()", first, null));
    }
}
