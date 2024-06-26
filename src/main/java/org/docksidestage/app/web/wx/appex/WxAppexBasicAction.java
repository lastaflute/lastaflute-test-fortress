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
package org.docksidestage.app.web.wx.appex;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.lastaflute.core.message.exception.MessagingApplicationException;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.login.exception.LoginFailureException;
import org.lastaflute.web.response.HtmlResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxAppexBasicAction extends FortressBaseAction {

    @Resource
    private MemberBhv memberBhv;

    // http://localhost:8151/fortress/wx/appex/basic/
    @Execute
    public HtmlResponse index() {
        throw newApplicationException();
    }

    // http://localhost:8151/fortress/wx/appex/basic/withoutinfo/
    @Execute
    public HtmlResponse withoutinfo() {
        throw newApplicationException().withoutInfo();
    }

    private MessagingApplicationException newApplicationException() {
        return new MessagingApplicationException("sea", createMessages().addErrorsAppIllegalTransition(GLOBAL));
    }

    // http://localhost:8151/fortress/wx/appex/basic/loginfailure/
    @Execute
    public HtmlResponse loginfailure() {
        throw new LoginFailureException("sea");
    }

    // http://localhost:8151/fortress/wx/appex/basic/alreadydeleted/
    @Execute
    public HtmlResponse alreadydeleted() {
        memberBhv.selectByPK(99999).get();
        throw new IllegalStateException("no way");
    }
}
