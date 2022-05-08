package org.docksidestage.app.web.wx.remogen;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.remogen.bean.oddprop.SuperOddProperties;
import org.docksidestage.app.web.wx.remogen.bean.recycle.KeyValueResult;
import org.docksidestage.app.web.wx.remogen.bean.recycle.RecycleParadeResult;
import org.docksidestage.app.web.wx.remogen.bean.selfref.SelfReferenceResult;
import org.docksidestage.app.web.wx.remogen.bean.suffix.hell.HellSeaResult;
import org.lastaflute.web.Execute;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
public class WxRemogenTrickyAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Execute
    public JsonResponse<Void> allnone() {
        return JsonResponse.asEmptyBody();
    }

    @Execute
    public JsonResponse<KeyValueResult> nobody() {
        return asJson(new KeyValueResult("sea", "mystic"));
    }

    @Execute
    public JsonResponse<SuperOddProperties> oddprop() {
        return asJson(new SuperOddProperties("maihama"));
    }

    @Execute
    public JsonResponse<RecycleParadeResult> recycle() {
        return asJson(new RecycleParadeResult("maihama"));
    }

    @Execute
    public JsonResponse<SelfReferenceResult> selfref() {
        return asJson(new SelfReferenceResult("maihama"));
    }

    @Execute
    public JsonResponse<HellSeaResult> suffixhell() {
        return asJson(new HellSeaResult("maihama"));
    }
}
