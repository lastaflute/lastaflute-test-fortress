/*
 * Copyright 2015-2024 the original author or authors.
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
package org.docksidestage.app.web.wx.response.transition.redirect;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.product.ProductListAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.servlet.request.ResponseManager;

import jakarta.annotation.Resource;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseTransitionRedirectTemporaryAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private RequestManager requestManager;
    @Resource
    private ResponseManager responseManager;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/response/transition/redirect/temporary/
    @Execute
    public HtmlResponse index() {
        // no super method (as rare case) so use responseManager here (2021/11/15)
        responseManager.temporaryRedirect(redirect(ProductListAction.class));
        return HtmlResponse.asEmptyBody();
    }
}
