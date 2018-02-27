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
package org.docksidestage.app.web.wx.validator.custom;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxValidatorCustomAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(WxValidatorCustomAction.class);

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [success]
    // http://localhost:8151/fortress/wx/validator/custom?sea=mystic
    // http://localhost:8151/fortress/wx/validator/custom?land=oneman
    //
    // [validation error]
    // http://localhost:8151/fortress/wx/validator/custom?sea=bbb
    // http://localhost:8151/fortress/wx/validator/custom?sea=americanwaterfront
    // http://localhost:8151/fortress/wx/validator/custom?land=one
    @Execute
    public JsonResponse<Void> index(WxValidatorCustomForm form) {
        logger.debug("form properties: {}", form);
        validateApi(form, messages -> {});
        return JsonResponse.asEmptyBody();
    }
}
