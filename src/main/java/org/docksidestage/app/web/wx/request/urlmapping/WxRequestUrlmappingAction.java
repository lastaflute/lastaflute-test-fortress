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
package org.docksidestage.app.web.wx.request.urlmapping;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestUrlmappingAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // cannot use both index() and named execute use URL parameter in same action
    //@Execute
    //public JsonResponse<CheckJsonBean> index(String first) {
    //    return asJson(new CheckJsonBean("index()", first, null));
    //}

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/maihama
    // [not]
    // http://localhost:8151/fortress/wx/mapping
    // http://localhost:8151/fortress/wx/mapping/maihama/dockside
    @Execute
    public JsonResponse<CheckJsonBean> maihama() {
        return asJson(new CheckJsonBean("maihama()", null, null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/sea/dockside
    // [not]
    // http://localhost:8151/fortress/wx/mapping/sea/dockside/hangar
    @Execute
    public JsonResponse<CheckJsonBean> sea(String first) {
        return asJson(new CheckJsonBean("sea()", first, null));
    }

    // Cannot define overload method of action execute
    //@Execute
    //public JsonResponse<CheckJsonBean> sea(String first, String second) {
    //    return asJson(new CheckJsonBean("sea()", first, second));
    //}

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/land/dockside/hangar
    // [not]
    // http://localhost:8151/fortress/wx/mapping/land/dockside
    // http://localhost:8151/fortress/wx/mapping/land/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<CheckJsonBean> land(String first, String second) {
        return asJson(new CheckJsonBean("land()", first, second));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/piari
    // http://localhost:8151/fortress/wx/mapping/piari/dockside
    // [not]
    // http://localhost:8151/fortress/wx/mapping/piari/dockside/hangar
    @Execute
    public JsonResponse<CheckJsonBean> piari(OptionalThing<String> first) {
        return asJson(new CheckJsonBean("piari()", first.orElse("*first"), null));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/dstore
    // http://localhost:8151/fortress/wx/mapping/dstore/dockside
    // http://localhost:8151/fortress/wx/mapping/dstore/dockside/hangar
    // [not]
    // http://localhost:8151/fortress/wx/mapping/dstore/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<CheckJsonBean> dstore(OptionalThing<String> first, OptionalThing<String> second) {
        return asJson(new CheckJsonBean("dstore()", first.orElse("*first"), second.orElse("*second")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/bonvo/dockside
    // http://localhost:8151/fortress/wx/mapping/bonvo/dockside/hangar
    // [not]
    // http://localhost:8151/fortress/wx/mapping/bonvo
    // http://localhost:8151/fortress/wx/mapping/bonvo/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<CheckJsonBean> bonvo(String first, OptionalThing<String> second) {
        return asJson(new CheckJsonBean("bonvo()", first, second.orElse("*second")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/amba/dockside/hangar
    // http://localhost:8151/fortress/wx/mapping/amba/dockside/hangar/magiclamp
    // [not]
    // http://localhost:8151/fortress/wx/mapping/amba/dockside
    // http://localhost:8151/fortress/wx/mapping/amba/dockside/hangar/magiclamp/orleans
    @Execute
    public JsonResponse<CheckJsonBean> amba(String first, String second, OptionalThing<String> third) {
        return asJson(new CheckJsonBean("amba()", first, second + " :: " + third.orElse("*third")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/miraco/dockside/hangar/magiclamp
    // http://localhost:8151/fortress/wx/mapping/miraco/dockside/hangar/magiclamp/orleans
    // [not]
    // http://localhost:8151/fortress/wx/mapping/miraco/dockside
    // http://localhost:8151/fortress/wx/mapping/miraco/dockside/hangar
    @Execute
    public JsonResponse<CheckJsonBean> miraco(String first, String second, String third, OptionalThing<String> fourth) {
        return asJson(new CheckJsonBean("miraco()", first, second + " :: " + third + " :: " + fourth.orElse("*fourth")));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/dohotel/dockside
    // http://localhost:8151/fortress/wx/mapping/dohotel/dockside/hangar
    // http://localhost:8151/fortress/wx/mapping/dohotel/dockside/hangar?param=magiclamp
    // [not]
    // http://localhost:8151/fortress/wx/mapping/dohotel/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<CheckJsonBean> dohotel(String first, OptionalThing<String> second, CheckForm form) {
        return asJson(new CheckJsonBean("dohotel()", first, second.orElse("*second") + " :: " + form));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/celeb/1
    // http://localhost:8151/fortress/wx/mapping/celeb/1/2
    // [not]
    // http://localhost:8151/fortress/wx/mapping/celeb/dockside
    // http://localhost:8151/fortress/wx/mapping/celeb/1/2/3
    @Execute
    public JsonResponse<CheckJsonBean> celeb(Integer first, OptionalThing<Long> second) {
        return asJson(new CheckJsonBean("celeb()", String.valueOf(first), String.valueOf(second.orElse(-99999L))));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/mapping/amphi/1/theater
    // [not]
    // http://localhost:8151/fortress/wx/mapping/amphi/1/2
    // http://localhost:8151/fortress/wx/mapping/amphi/1/dockside
    @Execute(urlPattern = "@word/{}/@word")
    public JsonResponse<CheckJsonBean> amphiTheater(Integer first) {
        return asJson(new CheckJsonBean("amphiTheater()", String.valueOf(first), null));
    }

    protected static class CheckForm {

        public String param;

        @Override
        public String toString() {
            return "form:{" + param + "}";
        }
    }

    protected static class CheckJsonBean {

        public final String method;
        public final String first;
        public final String second;

        public CheckJsonBean(String method, String first, String second) {
            this.method = WxRequestUrlmappingAction.class.getSimpleName() + "@" + method;
            this.first = first;
            this.second = second;
        }
    }
}
