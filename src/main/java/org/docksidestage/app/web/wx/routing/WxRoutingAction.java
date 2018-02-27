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
public class WxRoutingAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [hit]
    // http://localhost:8151/fortress/wx/routing/1
    // [not]
    // http://localhost:8151/fortress/wx/routing/
    @Execute
    public JsonResponse<RoutingCheckResult> index(Integer first) {
        return asJson(new RoutingCheckResult("index()", "specified: " + first, null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/maihama
    // [not]
    // http://localhost:8151/fortress/wx/routing/
    // http://localhost:8151/fortress/wx/routing/maihama/dockside
    @Execute
    public JsonResponse<RoutingCheckResult> maihama() {
        return asJson(new RoutingCheckResult("maihama()", null, null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/sea/dockside
    // [not]
    // http://localhost:8151/fortress/wx/routing/sea/dockside/hangar
    @Execute
    public JsonResponse<RoutingCheckResult> sea(String first) {
        return asJson(new RoutingCheckResult("sea()", first, null));
    }

    // Cannot define overload method of action execute
    //@Execute
    //public JsonResponse<RoutingCheckResult> sea(String first, String second) {
    //    return asJson(new RoutingCheckResult("sea()", first, second));
    //}

    // [hit]
    // http://localhost:8151/fortress/wx/routing/land/dockside/hangar
    // [not]
    // http://localhost:8151/fortress/wx/routing/land/dockside
    // http://localhost:8151/fortress/wx/routing/land/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RoutingCheckResult> land(String first, String second) {
        return asJson(new RoutingCheckResult("land()", first, second));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/piari
    // http://localhost:8151/fortress/wx/routing/piari/dockside
    // [not]
    // http://localhost:8151/fortress/wx/routing/piari/dockside/hangar
    @Execute
    public JsonResponse<RoutingCheckResult> piari(OptionalThing<String> first) {
        return asJson(new RoutingCheckResult("piari()", first.orElse("*first"), null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/dstore
    // http://localhost:8151/fortress/wx/routing/dstore/dockside
    // http://localhost:8151/fortress/wx/routing/dstore/dockside/hangar
    // [not]
    // http://localhost:8151/fortress/wx/routing/dstore/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RoutingCheckResult> dstore(OptionalThing<String> first, OptionalThing<String> second) {
        return asJson(new RoutingCheckResult("dstore()", first.orElse("*first"), second.orElse("*second")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/bonvo/dockside
    // http://localhost:8151/fortress/wx/routing/bonvo/dockside/hangar
    // [not]
    // http://localhost:8151/fortress/wx/routing/bonvo
    // http://localhost:8151/fortress/wx/routing/bonvo/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RoutingCheckResult> bonvo(String first, OptionalThing<String> second) {
        return asJson(new RoutingCheckResult("bonvo()", first, second.orElse("*second")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/amba/dockside/hangar
    // http://localhost:8151/fortress/wx/routing/amba/dockside/hangar/magiclamp
    // [not]
    // http://localhost:8151/fortress/wx/routing/amba/dockside
    // http://localhost:8151/fortress/wx/routing/amba/dockside/hangar/magiclamp/orleans
    @Execute
    public JsonResponse<RoutingCheckResult> amba(String first, String second, OptionalThing<String> third) {
        return asJson(new RoutingCheckResult("amba()", first, second + " :: " + third.orElse("*third")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/miraco/dockside/hangar/magiclamp
    // http://localhost:8151/fortress/wx/routing/miraco/dockside/hangar/magiclamp/orleans
    // [not]
    // http://localhost:8151/fortress/wx/routing/miraco/dockside
    // http://localhost:8151/fortress/wx/routing/miraco/dockside/hangar
    @Execute
    public JsonResponse<RoutingCheckResult> miraco(String first, String second, String third, OptionalThing<String> fourth) {
        return asJson(new RoutingCheckResult("miraco()", first, second + " :: " + third + " :: " + fourth.orElse("*fourth")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/dohotel/dockside
    // http://localhost:8151/fortress/wx/routing/dohotel/dockside/hangar
    // http://localhost:8151/fortress/wx/routing/dohotel/dockside/hangar?param=magiclamp
    // [not]
    // http://localhost:8151/fortress/wx/routing/dohotel/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RoutingCheckResult> dohotel(String first, OptionalThing<String> second, RoutingCheckForm form) {
        return asJson(new RoutingCheckResult("dohotel()", first, second.orElse("*second") + " :: " + form));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/celeb/1
    // http://localhost:8151/fortress/wx/routing/celeb/1/2
    // [not]
    // http://localhost:8151/fortress/wx/routing/celeb/dockside
    // http://localhost:8151/fortress/wx/routing/celeb/1/2/3
    @Execute
    public JsonResponse<RoutingCheckResult> celeb(Integer first, OptionalThing<Long> second) {
        return asJson(new RoutingCheckResult("celeb()", first, second.orElse(-99999L)));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/1/resola
    // [not]
    // http://localhost:8151/fortress/wx/routing/resola/1
    // http://localhost:8151/fortress/wx/routing/sea/resola
    @Execute(urlPattern = "{}/@word")
    public JsonResponse<RoutingCheckResult> resola(Integer first) {
        return asJson(new RoutingCheckResult("resola()", first, null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/amphi/1/theater/zed/maihama
    // [not]
    // http://localhost:8151/fortress/wx/routing/amphi/1/2
    // http://localhost:8151/fortress/wx/routing/amphi/1/theater/zed
    @Execute(urlPattern = "@word/{}/@word/{}/@word")
    public JsonResponse<RoutingCheckResult> amphiTheaterMaihama(Integer first, String second) {
        return asJson(new RoutingCheckResult("amphiTheaterMaihama()", first, second));
    }
}
