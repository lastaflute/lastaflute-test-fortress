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
package org.docksidestage.app.web.wx.lastadi;

import org.docksidestage.app.logic.lastadi.EnhancedDanceLogic;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

import jakarta.annotation.Resource;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxLastadiAction extends FortressBaseAction {

    @Resource
    private EnhancedDanceLogic enhancedDanceLogic;

    // http://localhost:8151/fortress/wx/lastadi/enhance/
    @Execute
    public JsonResponse<String> enhance() {
        enhancedDanceLogic.letsDance(); // also calls interceptor, confirm transaction log
        return JsonResponse.asJsonDirectly("{result: ok}");
    }
}
