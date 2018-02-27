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
package org.docksidestage.app.web.wx.hook;

import javax.annotation.Resource;

import org.dbflute.exception.EntityAlreadyDeletedException;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.lastaflute.web.Execute;
import org.lastaflute.web.hook.ApplicationExceptionHandler;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxHookAppexHandlerAction extends FortressBaseAction {

    @Resource
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                               Hook
    //                                                                              ======
    @Override
    protected void handleApplicationException(ActionRuntime runtime, ApplicationExceptionHandler handler) {
        super.handleApplicationException(runtime, handler);
        handler.handle(EntityAlreadyDeletedException.class, createMessages().addErrorsAppIllegalTransition(GLOBAL), cause -> {
            if (runtime.isApiExecute()) {
                return JsonResponse.asJsonDirectly("{sea: \"mystic\"");
            } else {
                return asHtml(path_Mypage_MypageHtml);
            }
        });
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/hook/appex/handler/alreadydeleted/
    @Execute
    public HtmlResponse alreadydeleted() {
        memberBhv.selectByPK(99999).get();
        throw new IllegalStateException("no way");
    }

    // http://localhost:8151/fortress/wx/hook/appex/handler/json/
    @Execute
    public JsonResponse<Void> json() {
        memberBhv.selectByPK(99999).get();
        throw new IllegalStateException("no way");
    }
}
