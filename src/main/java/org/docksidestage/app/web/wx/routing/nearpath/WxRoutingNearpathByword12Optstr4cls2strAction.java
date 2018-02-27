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
import org.docksidestage.dbflute.allcommon.CDef;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingNearpathByword12Optstr4cls2strAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [hit]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword12/optstr4cls2str/FML/
    // http://localhost:8151/fortress/wx/routing/nearpath/byword12/optstr4cls2str/FML/mystic/
    // http://localhost:8151/fortress/wx/routing/nearpath/byword12/optstr4cls2str/FML/named/
    // [not]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword12/optstr4cls2str/
    // http://localhost:8151/fortress/wx/routing/nearpath/byword12/optstr4cls2str/named/FML/
    @Execute
    public JsonResponse<RoutingCheckResult> index(CDef.MemberStatus first, OptionalThing<String> second) {
        return asJson(new RoutingCheckResult("index()", first, second.orElse("*none")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/nearpath/byword12/optstr4cls2str/1/named/
    // before LastaFlute-1.0.2
    //  => routing to index() for now
    // http://localhost:8151/fortress/wx/routing/nearpath/byword12/optstr4cls2str/mystic/named/
    // before LastaFlute-1.0.2
    //  => not found (index() but parse error) for now
    // [not]
    @Execute(urlPattern = "{}/@word")
    public JsonResponse<RoutingCheckResult> named(String first) {
        return asJson(new RoutingCheckResult("named()", first, null));
    }
}
