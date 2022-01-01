package org.docksidestage.app.web.wx.remogen;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.remogen.bean.simple.SuperSimpleBody;
import org.docksidestage.app.web.wx.remogen.bean.simple.SuperSimpleForm;
import org.docksidestage.app.web.wx.remogen.bean.simple.SuperSimpleResult;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRemogenMethodAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxRemogenMethodAction.class);

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // -----------------------------------------------------
    //                                                  GET
    //                                                 -----
    // GET http://localhost:8098/showbase/wx/remogen/method?sea=mystic&land=7
    @Execute
    public JsonResponse<SuperSimpleResult> get$index(SuperSimpleForm form) {
        logger.debug("form: {}", form);
        return asJson(new SuperSimpleResult(form.sea, form.land));
    }

    // GET http://localhost:8098/showbase/wx/remogen/method/onbodyform
    @Execute
    public JsonResponse<SuperSimpleResult> get$onbodyform(SuperSimpleForm form) {
        logger.debug("form: {}", form);
        return asJson(new SuperSimpleResult(form.sea, form.land));
    }

    // GET http://localhost:8098/showbase/wx/remogen/method/onbodyjson
    @Execute
    public JsonResponse<SuperSimpleResult> get$onbodyjson(SuperSimpleBody body) {
        logger.debug("form: {}", body);
        return asJson(new SuperSimpleResult(body.sea, body.land));
    }

    // GET http://localhost:8098/showbase/wx/remogen/method/samename?sea=mystic&land=7
    @Execute
    public JsonResponse<SuperSimpleResult> get$samename(SuperSimpleForm form) {
        logger.debug("form: {}", form);
        return asJson(new SuperSimpleResult(form.sea, form.land));
    }

    // -----------------------------------------------------
    //                                                 POST
    //                                                ------
    // POST http://localhost:8098/showbase/wx/remogen/method
    @Execute
    public JsonResponse<SuperSimpleResult> post$index(SuperSimpleBody body) {
        logger.debug("body: {}", body);
        return asJson(new SuperSimpleResult(body.sea, body.land));
    }

    // POST http://localhost:8098/showbase/wx/remogen/method/samename
    @Execute
    public JsonResponse<SuperSimpleResult> post$samename(SuperSimpleBody body) {
        logger.debug("body: {}", body);
        return asJson(new SuperSimpleResult(body.sea, body.land));
    }

    // -----------------------------------------------------
    //                                                DELETE
    //                                                ------
    // DELETE http://localhost:8098/showbase/wx/remogen/method
    @Execute
    public JsonResponse<SuperSimpleResult> delete$index(SuperSimpleForm form) {
        logger.debug("form: {}", form);
        return asJson(new SuperSimpleResult(form.sea, form.land));
    }

    // DELETE http://localhost:8098/showbase/wx/remogen/method/onbodyform
    @Execute
    public JsonResponse<SuperSimpleResult> delete$onbodyform(SuperSimpleForm form) {
        logger.debug("body: {}", form);
        return asJson(new SuperSimpleResult(form.sea, form.land));
    }

    // DELETE http://localhost:8098/showbase/wx/remogen/method/onbodyjson
    @Execute
    public JsonResponse<SuperSimpleResult> delete$onbodyjson(SuperSimpleBody body) {
        logger.debug("body: {}", body);
        return asJson(new SuperSimpleResult(body.sea, body.land));
    }

    // DELETE http://localhost:8098/showbase/wx/remogen/method/noquery
    @Execute
    public JsonResponse<SuperSimpleResult> delete$noquery() {
        return asJson(new SuperSimpleResult("no query", -1));
    }
}
