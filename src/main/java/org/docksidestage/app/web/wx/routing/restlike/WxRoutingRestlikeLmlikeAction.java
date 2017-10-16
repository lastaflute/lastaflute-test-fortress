package org.docksidestage.app.web.wx.routing.restlike;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.allcommon.CDef;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingRestlikeLmlikeAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [hit]
    // http://localhost:8151/fortress/wx/routing/restlike/lmlike/FML/
    @Execute
    public JsonResponse<MaihamaBean> index(CDef.MemberStatus memberStatus) {
        return asJson(new MaihamaBean("index()", memberStatus, "*no value"));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/restlike/lmlike/FML/1
    //  => http://localhost:8151/fortress/wx/routing/restlike/lmlike/category/FML/1
    @Execute
    public JsonResponse<MaihamaBean> category(CDef.MemberStatus memberStatus, Integer memberId) {
        return asJson(new MaihamaBean("category()", memberStatus, memberId));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/restlike/lmlike/1/detail
    @Execute(urlPattern = "{}/@word")
    public JsonResponse<MaihamaBean> detail(Integer productId) {
        return asJson(new MaihamaBean("detail()", productId, "*no value"));
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    public static class MaihamaBean {

        public String method;
        public Object first;
        public Object second;

        public MaihamaBean(String method, Object first, Object second) {
            this.method = method;
            this.first = first;
            this.second = second;
        }
    }
}
