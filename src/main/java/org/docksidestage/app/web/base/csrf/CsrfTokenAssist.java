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
package org.docksidestage.app.web.base.csrf;

import javax.annotation.Resource;

import org.dbflute.util.Srl;
import org.lastaflute.web.ruts.process.ActionRuntime;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.token.CsrfManager;

/**
 * @author jflute
 */
public class CsrfTokenAssist {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private RequestManager requestManager;
    @Resource
    private CsrfManager csrfManager;

    // ===================================================================================
    //                                                                         Hook Before
    //                                                                         ===========
    public void hookBefore(ActionRuntime runtime) {
        if (isVerifyTargetRequest()) {
            // #hope comment out for example, needs thymeleaf automatic support by jflute
            //csrfManager.verifyToken();
        }
        if (isFirstAccess()) { // basically e.g. GET and no session
            csrfManager.beginToken();
        }
    }

    private boolean isFirstAccess() {
        return !csrfManager.getSavedToken().isPresent();
    }

    protected boolean isVerifyTargetRequest() {
        return requestManager.getHttpMethod().map(method -> {
            return Srl.equalsIgnoreCase(method, "POST", "PUT", "PATCH", "DELETE");
        }).orElse(true); // unknown HTTP method is basically no way, strict just in case
    }

    // ===================================================================================
    //                                                                        Hook Finally
    //                                                                        ============
    public void hookFinally(ActionRuntime runtime) {
        // LastaThymeleaf does not support automatic CSRF parameter yet
        // so it needs to register here and needs to define hidden field manually
        if (runtime.isForwardToHtml()) {
            csrfManager.getSavedToken().alwaysPresent(token -> { // for server-side HTML
                runtime.registerData(csrfManager.getTokenParameterName(), token);
            });
        }
    }
}
