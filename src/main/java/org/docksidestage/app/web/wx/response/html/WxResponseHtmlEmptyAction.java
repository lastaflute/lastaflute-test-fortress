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
package org.docksidestage.app.web.wx.response.html;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.servlet.request.ResponseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseHtmlEmptyAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxResponseHtmlEmptyAction.class);

    @Resource
    private ResponseManager responseManager;

    // http://localhost:8151/fortress/wx/response/html/empty/
    @Execute
    public HtmlResponse index() {
        return HtmlResponse.asEmptyBody();
    }

    // http://localhost:8151/fortress/wx/response/html/empty/committed/
    @Execute
    public HtmlResponse committed() {
        HttpServletResponse response = responseManager.getResponse();
        try {
            ServletOutputStream ous = response.getOutputStream();
            ous.write(777);
            ous.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write response.", e);
        }
        response.setStatus(299);
        logger.debug("committed: {}", response.isCommitted());
        logger.debug("status: {}", response.getStatus());
        return HtmlResponse.asEmptyBody();
    }
}
