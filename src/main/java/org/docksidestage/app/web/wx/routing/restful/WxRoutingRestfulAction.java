package org.docksidestage.app.web.wx.routing.restful;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRoutingRestfulAction extends FortressBaseAction {

    // see ProductsAction for RESTful action test
    @Execute
    public JsonResponse<Void> get$dummy() {
        return JsonResponse.asEmptyBody();
    }
}
