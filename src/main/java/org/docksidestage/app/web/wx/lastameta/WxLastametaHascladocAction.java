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
package org.docksidestage.app.web.wx.lastameta;

import org.dbflute.exception.EntityAlreadyDeletedException;
import org.dbflute.exception.EntityAlreadyExistsException;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * This is the first line of Action class java-doc. <br>
 * And the second line is here.
 * @author jflute
 */
@AllowAnyoneAccess
public class WxLastametaHascladocAction extends FortressBaseAction {

    // http://localhost:8151/fortress/wx/lastameta/hascladoc/
    /**
     * This is the first line of execute method java-doc. <br>
     * And the second line is here.
     * @param body nice body (NotNull)
     * @return same as body (tekito) (NotNull)
     * @throws EntityAlreadyDeletedException When kimagure (404)
     * @throws EntityAlreadyExistsException When kimagure (400)
     */
    @Execute
    public JsonResponse<WxLastametaHascladocBody> index(WxLastametaHascladocBody body) {
        return asJson(body);
    }

    // http://localhost:8151/fortress/wx/lastameta/hascladoc/nomedoc/
    @Execute
    public JsonResponse<WxLastametaHascladocBody> nomedoc(WxLastametaHascladocBody body) { // without javadoc
        return asJson(body);
    }
}
