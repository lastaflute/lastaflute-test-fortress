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
package org.docksidestage.app.web.wx.response.stream.api;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.mylasta.action.FortressMessages;
import org.lastaflute.core.message.exception.MessagingApplicationException;
import org.lastaflute.web.Execute;
import org.lastaflute.web.api.ApiAction;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.StreamResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseStreamApiAction extends FortressBaseAction implements ApiAction {

    // http://localhost:8151/fortress/wx/response/stream/api/validationerror/
    //  => handled by ApiFailureHook
    // http://localhost:8151/fortress/wx/response/stream/api/validationerror/?land=oneman
    //  => download
    @Execute
    public StreamResponse validationerror(WxResponseStreamErrorForm form) {
        validateApi(form, messages -> {}); // as JSON (ApiFailureHook)
        return asStream("sea.txt").stream(out -> {
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                out.write(ins);
            }
        });
    }

    // http://localhost:8151/fortress/wx/response/stream/api/withbizex/
    //  => handled by ApiFailureHook
    @Execute
    public StreamResponse withbizex() {
        String debugMsg = "Stream Business Exception Test";
        FortressMessages messages = createMessages();
        messages.addErrorsAppIllegalTransition(GLOBAL);
        throw new MessagingApplicationException(debugMsg, messages);
    }
}
