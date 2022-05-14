/*
 * Copyright 2015-2022 the original author or authors.
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

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.request.RequestCheckResult;
import org.docksidestage.dbflute.allcommon.CDef;
import org.docksidestage.mylasta.appcls.AppCDef;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.util.LaActionExecuteUtil;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestPathvarAction extends FortressBaseAction {

    // http://localhost:8151/fortress/wx/request/pathvar/sea
    @Execute
    public JsonResponse<RequestCheckResult> string(String first) {
        return asJson(new RequestCheckResult(getExecuteName(), first, "*first only"));
    }

    // http://localhost:8151/fortress/wx/request/pathvar/cls/FML
    // http://localhost:8151/fortress/wx/request/pathvar/cls/detarame
    //  => 404: Unknown the classification code
    @Execute
    public JsonResponse<RequestCheckResult> cls(CDef.MemberStatus first) {
        return asJson(new RequestCheckResult(getExecuteName(), first, "*first only"));
    }

    // http://localhost:8151/fortress/wx/request/pathvar/nameof/ONE
    // http://localhost:8151/fortress/wx/request/pathvar/nameof/oneman
    // http://localhost:8151/fortress/wx/request/pathvar/nameof/minio
    // http://localhost:8151/fortress/wx/request/pathvar/nameof/detarame
    //  => 404: Unknown the classification code
    @Execute
    public JsonResponse<RequestCheckResult> nameof(AppCDef.AppWxNameOf first) {
        return asJson(new RequestCheckResult(getExecuteName(), first, "*first only"));
    }

    private String getExecuteName() {
        return LaActionExecuteUtil.getActionExecute().toSimpleMethodExp();
    }
}
