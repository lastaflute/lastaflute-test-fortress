/*
 * Copyright 2015-2021 the original author or authors.
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
package org.docksidestage.app.web.wx.bizpkg;

import javax.annotation.Resource;

import org.docksidestage.app.biz.onionarc.application.OnionarcSeaAppService;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxBizpkgOnionarcAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private OnionarcSeaAppService seaAppService;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // [success]
    // http://localhost:8151/fortress/wx/bizpkg/onionarc/
    //
    @Execute
    public JsonResponse<Void> index() {
        seaAppService.save();
        return JsonResponse.asEmptyBody();
    }
}
