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
package org.docksidestage.app.web.wx.security;

import java.time.LocalDate;
import java.util.List;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxSecurityRequestParameterAction extends FortressBaseAction {

    // http://localhost:8151/fortress/wx/security/request/parameter/?secureSea=mystic&secureLand=83&&openPiari=2019/04/07&secureMiraco=harborSide&&secureMiraco=riverSide
    @Execute
    public JsonResponse<BasicItemForm> index(BasicItemForm form) {
        validateApi(form, messages -> {});
        return asJson(form);
    }

    // http://localhost:8151/fortress/wx/security/request/parameter/error?secureSea=mystic&secureLand=83&&openPiari=2019/04/07&secureMiraco=harborSide&&secureMiraco=riverSide
    @Execute
    public JsonResponse<BasicItemForm> error(BasicItemForm form) {
        validateApi(form, messages -> {});
        throw new IllegalStateException("forcedly error");
    }

    // mask settings are on web.xml
    public static class BasicItemForm {

        public String secureSea;

        public Integer secureLand;

        public LocalDate openPiari;

        public List<String> secureMiraco;
    }
}
