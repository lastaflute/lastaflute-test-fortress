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
package org.docksidestage.app.web.wx.request.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestJsonBodyAction extends FortressBaseAction {

    // http://localhost:8151/fortress/wx/request/json/body/
    @Execute
    public JsonResponse<Map<String, Object>> index(WxRequestJsonBodyBody body) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("body", body);
        return asJson(map);
    }

    // http://localhost:8151/fortress/wx/request/json/body/list
    @Execute
    public JsonResponse<Map<String, Object>> list(List<WxRequestJsonBodyBody> body) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("body", body);
        return asJson(map);
    }

    // http://localhost:8151/fortress/wx/request/json/body/clienterror
    @Execute
    public JsonResponse<Map<String, Object>> clienterror(WxRequestJsonBodyBody body) {
        verifyOrClientError("forcedly client error", false);
        throw new IllegalStateException("no way");
    }

    // http://localhost:8151/fortress/wx/request/json/body/systemerror
    @Execute
    public JsonResponse<Map<String, Object>> systemerror(WxRequestJsonBodyBody body) {
        throw new IllegalStateException("body: " + body);
    }
}