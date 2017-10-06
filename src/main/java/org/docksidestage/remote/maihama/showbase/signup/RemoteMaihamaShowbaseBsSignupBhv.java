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
     * Request remote call to  Signup. (auto-generated method)<br>
     * <pre>
     * url: /signup/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteSignupParam. (NotNull)
     */
    public void requestSignup(Consumer<RemoteSignupParam> paramLamda) {
        requestSignup(paramLamda, rule -> {});
    }

    /**
     * Set up method-level rule of Signup. (auto-generated method)<br>
     * <pre>
     * url: /signup/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteSignupParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestSignup(Consumer<RemoteSignupParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteSignupParam param = new RemoteSignupParam();
        paramLamda.accept(param);
        doRequestPost(Void.class, "/signup/", noMoreUrl(), param, rule -> {
            ruleOfSignup(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Signup.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfSignup(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  Register. (auto-generated method)<br>
     * <pre>
     * url: /signup/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param account account. (NotNull)
     * @param token token. (NotNull)
     */
    public void requestRegister(String account, String token) {
        requestRegister(account, token, rule -> {});
    }

    /**
     * Set up method-level rule of Register. (auto-generated method)<br>
     * <pre>
     * url: /signup/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param account account. (NotNull)
     * @param token token. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestRegister(String account, String token, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(Void.class, "/signup/register/{account}/{token}", moreUrl(account, token), noQuery(), rule -> {
            ruleOfRegisterAccountToken(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Register.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRegisterAccountToken(FlutyRemoteApiRule rule) {
    }
}
