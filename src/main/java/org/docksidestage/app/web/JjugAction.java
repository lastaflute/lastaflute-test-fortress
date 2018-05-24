package org.docksidestage.app.web;

import org.lastaflute.web.Execute;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
public class JjugAction {

    @Execute
    public JsonResponse<JjugResult> index() {
        return asJson(bean);
    }
}
