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
package org.docksidestage.app.web.wx.lastameta;

import org.dbflute.exception.EntityAlreadyDeletedException;
import org.dbflute.exception.EntityAlreadyExistsException;
import org.dbflute.exception.EntityAlreadyUpdatedException;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

// without javadoc
@AllowAnyoneAccess
public class WxLastametaNocladocAction extends FortressBaseAction {

    // http://localhost:8151/fortress/wx/lastameta/nocladoc/
    /**
     * This is the first line of execute method java-doc. <br>
     * And the second line is here.
     * @param body Oh yeah nice body (NotNull)
     * @return same as body (tekito) (NotNull)
     * @throws EntityAlreadyDeletedException When the resource is not found (404)
     * @throws EntityAlreadyUpdatedException When the resource already has been updated (400)
     * @throws EntityAlreadyExistsException When the resource already exists (400)
     */
    @Execute
    public JsonResponse<WxLastametaHascladocBody> index(WxLastametaHascladocBody body) {
        return asJson(body);
    }

    // http://localhost:8151/fortress/wx/lastameta/nocladoc/nomedoc/
    @Execute
    public JsonResponse<WxLastametaHascladocBody> nomedoc(WxLastametaHascladocBody body) { // without javadoc
        return asJson(body);
    }
}
