/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage.remote.maihama.showbase.signup;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.signup.RemoteSignupParam;

/**
 * RemoteMaihamaShowbaseBsSignupBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsSignupBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsSignupBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * request Signup.<br>
     * <pre>
     * url: /signup/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteSignupParam. (NotNull)
     */
    public void requestSignup(Consumer<RemoteSignupParam> paramLamda) {
        RemoteSignupParam param = new RemoteSignupParam();
        paramLamda.accept(param);
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/signup/", noMoreUrl(), param, rule -> ruleOfSignup(rule));
    }

    /**
     * Rule of Signup.<br>
     * <pre>
     * url: /signup/
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfSignup(FlutyRemoteApiRule rule) {
    }

    /**
     * request Register.<br>
     * <pre>
     * url: /signup/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param account account. (NotNull)
     * @param token token. (NotNull)
     */
    public void requestRegister(String account, String token) {
        doRequestGet(new ParameterizedRef<Void>() {
        }.getType(), "/signup/register/{account}/{token}", moreUrl(account, token), noQuery(), rule -> ruleOfRegister(rule));
    }

    /**
     * Rule of Register.<br>
     * <pre>
     * url: /signup/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfRegister(FlutyRemoteApiRule rule) {
    }
}
