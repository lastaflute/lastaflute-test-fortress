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
package org.docksidestage.app.web.wx.routing.reqparam.conflict;

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
public class WxRoutingReqparamConflictAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxRoutingReqparamConflictAction.class);

    // [hit]
    // GET http://localhost:8151/fortress/wx/routing/reqparam/conflict/?sea=mystic
    //  => OK since 1.2.3
    //  => x until 1.2.2
    @Execute
    public JsonResponse<Map<String, Object>> get$index(WxRoutingReqparamConflictForm form) {
        validateApi(form, messages -> {});
        logger.debug("form.sea = " + form.sea);
        return asJson(DfCollectionUtil.newHashMap("hit", "get$index()", "form.sea", form.sea));
    }

    // [hit]
    // POST http://localhost:8151/fortress/wx/routing/reqparam/conflict/sea/
    @Execute
    public JsonResponse<Map<String, Object>> post$sea() {
        return asJson(DfCollectionUtil.newHashMap("hit", "post$sea()"));
    }
}
