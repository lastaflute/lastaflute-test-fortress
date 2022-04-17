package org.docksidestage.app.web.wx.remogen;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.remogen.routing.RemogenRoutingCheckForm;
import org.docksidestage.app.web.wx.remogen.routing.RemogenRoutingCheckResult;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRemogenRoutingAction extends FortressBaseAction {

    // *refers to WxRoutingAction of fortress project
    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/1
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> index(Integer first) {
        return asJson(new RemogenRoutingCheckResult("index()", "specified: " + first, null));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/maihama
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/
    // http://localhost:8098/showbase/wx/remogen/routing/maihama/dockside
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> maihama() {
        return asJson(new RemogenRoutingCheckResult("maihama()", null, null));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/sea/dockside
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/sea/dockside/hangar
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> sea(String first) {
        return asJson(new RemogenRoutingCheckResult("sea()", first, null));
    }

    // Cannot define overload method of action execute
    //@Execute
    //public JsonResponse<RoutingCheckResult> sea(String first, String second) {
    //    return asJson(new RoutingCheckResult("sea()", first, second));
    //}

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/land/dockside/hangar
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/land/dockside
    // http://localhost:8098/showbase/wx/remogen/routing/land/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> land(String first, String second) {
        return asJson(new RemogenRoutingCheckResult("land()", first, second));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/piari
    // http://localhost:8098/showbase/wx/remogen/routing/piari/dockside
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/piari/dockside/hangar
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> piari(OptionalThing<String> first) {
        return asJson(new RemogenRoutingCheckResult("piari()", first.orElse("*first"), null));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/dstore
    // http://localhost:8098/showbase/wx/remogen/routing/dstore/dockside
    // http://localhost:8098/showbase/wx/remogen/routing/dstore/dockside/hangar
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/dstore/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> dstore(OptionalThing<String> first, OptionalThing<String> second) {
        return asJson(new RemogenRoutingCheckResult("dstore()", first.orElse("*first"), second.orElse("*second")));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/bonvo/dockside
    // http://localhost:8098/showbase/wx/remogen/routing/bonvo/dockside/hangar
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/bonvo
    // http://localhost:8098/showbase/wx/remogen/routing/bonvo/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> bonvo(String first, OptionalThing<String> second) {
        return asJson(new RemogenRoutingCheckResult("bonvo()", first, second.orElse("*second")));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/amba/dockside/hangar
    // http://localhost:8098/showbase/wx/remogen/routing/amba/dockside/hangar/magiclamp
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/amba/dockside
    // http://localhost:8098/showbase/wx/remogen/routing/amba/dockside/hangar/magiclamp/orleans
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> amba(String first, String second, OptionalThing<String> third) {
        return asJson(new RemogenRoutingCheckResult("amba()", first, second + " :: " + third.orElse("*third")));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/miraco/dockside/hangar/magiclamp
    // http://localhost:8098/showbase/wx/remogen/routing/miraco/dockside/hangar/magiclamp/orleans
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/miraco/dockside
    // http://localhost:8098/showbase/wx/remogen/routing/miraco/dockside/hangar
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> miraco(String first, String second, String third, OptionalThing<String> fourth) {
        return asJson(new RemogenRoutingCheckResult("miraco()", first, second + " :: " + third + " :: " + fourth.orElse("*fourth")));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/dohotel/dockside
    // http://localhost:8098/showbase/wx/remogen/routing/dohotel/dockside/hangar
    // http://localhost:8098/showbase/wx/remogen/routing/dohotel/dockside/hangar?param=magiclamp
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/dohotel/dockside/hangar/magiclamp
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> dohotel(String first, OptionalThing<String> second, RemogenRoutingCheckForm form) {
        return asJson(new RemogenRoutingCheckResult("dohotel()", first, second.orElse("*second") + " :: " + form));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/celeb/1
    // http://localhost:8098/showbase/wx/remogen/routing/celeb/1/2
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/celeb/dockside
    // http://localhost:8098/showbase/wx/remogen/routing/celeb/1/2/3
    @Execute
    public JsonResponse<RemogenRoutingCheckResult> celeb(Integer first, OptionalThing<Long> second) {
        return asJson(new RemogenRoutingCheckResult("celeb()", first, second.orElse(-99999L)));
    }

    // [hit]
    // http://localhost:8151/showbase/wx/remogen/routing/1/resola
    // [not]
    // http://localhost:8151/showbase/wx/remogen/routing/resola/1
    // http://localhost:8151/showbase/wx/remogen/routing/sea/resola
    @Execute(urlPattern = "{}/@word")
    public JsonResponse<RemogenRoutingCheckResult> resola(Integer first) {
        return asJson(new RemogenRoutingCheckResult("resola()", first, null));
    }

    // [hit]
    // http://localhost:8098/showbase/wx/remogen/routing/amphi/1/theater/zed/maihama
    // [not]
    // http://localhost:8098/showbase/wx/remogen/routing/amphi/1/2
    // http://localhost:8098/showbase/wx/remogen/routing/amphi/1/theater/zed
    @Execute(urlPattern = "@word/{}/@word/{}/@word")
    public JsonResponse<RemogenRoutingCheckResult> amphiTheaterMaihama(Integer first, String second) {
        return asJson(new RemogenRoutingCheckResult("amphiTheaterMaihama()", first, second));
    }
}
