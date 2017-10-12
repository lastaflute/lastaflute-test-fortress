package org.docksidestage.app.web.wx.routing.restlike;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingRestlikeAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // -----------------------------------------------------
    //                                                 Index
    //                                                 -----
    // [hit]
    // GET http://localhost:8151/fortress/wx/request/restlike/
    // GET http://localhost:8151/fortress/wx/request/restlike/sea/
    // [not]
    // GET http://localhost:8151/fortress/wx/request/restlike/sea/land
    @Execute // GET /wx/request/restlike/{first}/
    public JsonResponse<MaihamaBean> get$index(OptionalThing<String> first) {
        return asJson(new MaihamaBean("GET get$index()", first.orElse("*no value"), "*no value"));
    }

    // [hit]
    // POST http://localhost:8151/fortress/wx/request/restlike/
    @Execute // POST /wx/request/restlike/{first}/
    public JsonResponse<MaihamaBean> post$index(MaihamaForm form) {
        return asJson(new MaihamaBean("POST post$index()", "*no value", "*no value", form));
    }

    // [hit]
    // PUT http://localhost:8151/fortress/wx/request/restlike/sea/
    @Execute // POST /wx/request/restlike/{first}/
    public JsonResponse<MaihamaBean> put$index(String first, MaihamaForm form) {
        return asJson(new MaihamaBean("PUT put$index()", first, "*no value", form));
    }

    // [hit]
    // DELETE http://localhost:8151/fortress/wx/request/restlike/sea/
    @Execute // POST /wx/request/restlike/{first}/
    public JsonResponse<MaihamaBean> delete$index(String first, MaihamaForm form) {
        return asJson(new MaihamaBean("DELETE delete$index()", first, "*no value", form));
    }

    // -----------------------------------------------------
    //                                             Operation
    //                                             ---------
    // [hit]
    // GET http://localhost:8151/fortress/wx/request/restlike/sea/operation/
    @Execute(urlPattern = "{}/@word") // GET /wx/request/restlike/{first}/operation/
    public JsonResponse<MaihamaBean> get$operation(String first) {
        return asJson(new MaihamaBean("GET get$operation()", first, "*no value"));
    }

    // -----------------------------------------------------
    //                                                 Next
    //                                                ------
    // [hit]
    // GET http://localhost:8151/fortress/wx/request/restlike/sea/next/land/
    @Execute(urlPattern = "{}/@word/{}") // GET /wx/request/restlike/{first}/next/{second}
    public JsonResponse<MaihamaBean> get$next(String first, String second) {
        return asJson(new MaihamaBean("GET", first, second));
    }

    // [hit]
    // POST http://localhost:8151/fortress/wx/request/restlike/sea/next/land/
    @Execute(urlPattern = "{}/@word/{}") // POST /wx/request/restlike/{first}/next/{second}
    public JsonResponse<MaihamaBean> post$next(String first, String second, MaihamaNextForm form) {
        return asJson(new MaihamaBean("POST", first, second, form));
    }

    // #hope now making
    //// -----------------------------------------------------
    ////                                         Last Optional
    ////                                         -------------
    //// GET http://localhost:8151/fortress/wx/request/restlike/sea/lastopt/land/
    //@Execute(urlPattern = "{}/@word/{}") // GET /wx/request/restlike/{first}/lastopt/{second}/
    //public JsonResponse<MaihamaBean> get$lastopt(String first, OptionalThing<String> second) {
    //    return asJson(new MaihamaBean("GET get$lastopt()", first, second.orElse("*empty value")));
    //}

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    public static class MaihamaForm {

        public Integer maihamaId;
    }

    public static class MaihamaNextForm {

        public Integer maihamaId;
    }

    public static class MaihamaBean {

        public String method;
        public String first;
        public String second;
        public Object form;

        public MaihamaBean(String method, String first, String second) {
            this.method = method;
            this.first = first;
            this.second = second;
        }

        public MaihamaBean(String method, String first, String second, Object form) {
            this(method, first, second);
            this.form = form;
        }
    }
}
