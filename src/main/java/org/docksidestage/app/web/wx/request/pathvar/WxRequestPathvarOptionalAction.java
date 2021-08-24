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
package org.docksidestage.app.web.wx.request.pathvar;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.request.RequestCheckResult;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.util.LaActionExecuteUtil;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestPathvarOptionalAction extends FortressBaseAction {

    // http://localhost:8151/fortress/wx/request/pathvar/optional/onparade
    // http://localhost:8151/fortress/wx/request/pathvar/optional/onparade/sea
    // http://localhost:8151/fortress/wx/request/pathvar/optional/onparade/sea/land
    // http://localhost:8151/fortress/wx/request/pathvar/optional/onparade/sea/land/piari
    @Execute
    public JsonResponse<RequestCheckResult> onparade(OptionalThing<String> first, OptionalThing<String> second,
            OptionalThing<String> third) {
        return asJson(new RequestCheckResult(getExecuteName(), first, second, third));
    }

    private String getExecuteName() {
        return LaActionExecuteUtil.getActionExecute().toSimpleMethodExp();
    }
}
