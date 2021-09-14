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
package org.docksidestage.app.web.wx.login;

import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.login.FortressSurprisedLoginAssist;
import org.docksidestage.app.web.mypage.MypageAction;
import org.docksidestage.mylasta.action.FortressMessages;
import org.lastaflute.core.util.LaStringUtil;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.login.LoginManager;
import org.lastaflute.web.login.credential.UserPasswordCredential;
import org.lastaflute.web.response.HtmlResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxLoginSurprisedAction extends FortressBaseAction {

    @Resource
    private FortressSurprisedLoginAssist loginAssist;

    // ===================================================================================
    //                                                                           User Info
    //                                                                           =========
    @Override
    protected OptionalThing<LoginManager> myLoginManager() {
        return OptionalThing.of(loginAssist);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/login/surprised/
    @Execute
    public HtmlResponse index() {
        if (getUserBean().isPresent()) {
            return redirect(MypageAction.class);
        }
        return asHtml(path_WxLogin_LoginSurprisedHtml);
    }

    @Execute
    public HtmlResponse signin(WxLoginSurprisedForm form) {
        validate(form, messages -> moreValidate(form, messages), () -> {
            form.clearSecurityInfo();
            return asHtml(path_WxLogin_LoginSurprisedHtml);
        });
        return loginAssist.loginRedirect(createCredential(form), op -> op.rememberMe(form.rememberMe), () -> {
            return redirect(MypageAction.class);
        });
    }

    private void moreValidate(WxLoginSurprisedForm form, FortressMessages messages) {
        if (LaStringUtil.isNotEmpty(form.account) && LaStringUtil.isNotEmpty(form.password)) {
            if (!loginAssist.checkUserLoginable(createCredential(form))) {
                messages.addErrorsLoginFailure("account");
            }
        }
    }

    private UserPasswordCredential createCredential(WxLoginSurprisedForm form) {
        return new UserPasswordCredential(form.account, form.password);
    }
}
