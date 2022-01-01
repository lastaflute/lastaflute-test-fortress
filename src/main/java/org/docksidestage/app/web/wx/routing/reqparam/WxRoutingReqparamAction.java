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
package org.docksidestage.app.web.wx.routing.reqparam;

import java.util.Map;

import org.dbflute.util.DfCollectionUtil;
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
public class WxRoutingReqparamAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxRoutingReqparamAction.class);

    // [hit]
    // http://localhost:8151/fortress/wx/routing/reqparam/?land=oneman
    @Execute
    public JsonResponse<Map<String, Object>> index(WxRoutingReqparamForm form) {
        validateApi(form, messages -> {});
        logger.debug("form.land = " + form.land);
        return asJson(DfCollectionUtil.newHashMap("hit", "index()", "form.land", form.land));
    }

    // [hit]
    // http://localhost:8151/fortress/wx/routing/reqparam/sea/
    // http://localhost:8151/fortress/wx/routing/reqparam/?sea=mystic
    @Execute
    public JsonResponse<Map<String, Object>> sea() {
        return asJson(DfCollectionUtil.newHashMap("hit", "sea()"));
    }
}
