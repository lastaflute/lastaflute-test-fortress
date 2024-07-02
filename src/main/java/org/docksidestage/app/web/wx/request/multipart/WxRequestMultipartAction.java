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
package org.docksidestage.app.web.wx.request.multipart;

import java.io.IOException;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.mylasta.direction.sponsor.FortressMultipartRequestHandler;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.ruts.multipart.MultipartFormFile;
import org.lastaflute.web.ruts.multipart.exception.MultipartExceededException;
import org.lastaflute.web.servlet.request.RequestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestMultipartAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(WxRequestMultipartAction.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private RequestManager requestManager;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/request/multipart/
    @Execute
    public HtmlResponse index() {
        System.gc();
        return asHtml(path_WxMultipart_WxMultipartHtml);
    }

    @Execute
    public HtmlResponse upload(WxRequestMultipartForm form) {
        validate(form, messages -> {}, () -> {
            // the uploadedFile is required property so it comes here when exceeded
            handleExceededException(form);
            return asHtml(path_WxMultipart_WxMultipartHtml);
        });
        showUploadedFile(form);
        return asHtml(path_WxMultipart_WxMultipartHtml);
    }

    private void handleExceededException(WxRequestMultipartForm form) {
        String key = FortressMultipartRequestHandler.MAX_LENGTH_EXCEEDED_KEY;
        requestManager.getAttribute(key, MultipartExceededException.class).ifPresent(cause -> {
            logger.debug("*Found the exceeded file: " + form, cause); // logging only here
        });
    }

    private void showUploadedFile(WxRequestMultipartForm form) {
        MultipartFormFile uploadedFile = form.uploadedFile;
        if (uploadedFile == null) { // for temporary test
            return;
        }
        logger.debug("/- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        try {
            String fileName = uploadedFile.getFileName();
            String fileData = new String(uploadedFile.getFileData(), "UTF-8");
            logger.debug("#upload: {}, {}, {}={}", form.sea, form.land, fileName, fileData.length());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to show file data: " + uploadedFile, e);
        }
        logger.debug("- - - - - - - - - -/");
    }
}
