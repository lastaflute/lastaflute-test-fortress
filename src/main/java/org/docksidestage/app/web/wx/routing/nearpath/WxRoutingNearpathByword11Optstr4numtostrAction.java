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

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.routing.RoutingCheckResult;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingNearpathByword11Optstr4numtostrAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [hit]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword11/optstr4numtostr/1/
    // http://localhost:8151/fortress/wx/routing/nearpath/byword11/optstr4numtostr/1/mystic
    // [not]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword11/optstr4numtostr/mystic/
    @Execute
    public JsonResponse<RoutingCheckResult> index(Integer first, OptionalThing<String> second) {
        return asJson(new RoutingCheckResult("index()", first, second.orElse("*none")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword11/optstr4numtostr/mystic/named/
    // before LastaFlute-1.0.2
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Cannot convert the parameter to argument type.
    
    [Cause]
    For input string: "mystic"
    * * * * * * * * * */
    @Execute(urlPattern = "{}/@word")
    public JsonResponse<RoutingCheckResult> named(String first) {
        return asJson(new RoutingCheckResult("named()", first, null));
    }
}
