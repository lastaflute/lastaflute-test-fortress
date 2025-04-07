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
package org.docksidestage.app.web.wx.request.xml;

import java.util.Map;

import javax.annotation.Resource;

import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.servlet.request.RequestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestXmlBodyAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxRequestXmlBodyAction.class);

    @Resource
    private RequestManager requestManager;

    // #hope jflute LastaFlute will support xml request body (2025/04/07)
    //// http://localhost:8151/fortress/wx/request/xml/body/
    //@Execute
    //public JsonResponse<Map<String, Object>> index(WxRequestXmlBodyBody body) {
    //    return asJson(DfCollectionUtil.newHashMap("body", body));
    //}

    // self-parse way
    @Execute
    public JsonResponse<Map<String, Object>> index() {
        String body = requestManager.getRequestBody();
        logger.debug("#xml: \n{}", body);
        return asJson(DfCollectionUtil.newHashMap("body", body));
    }
}