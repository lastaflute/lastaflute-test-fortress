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
package org.docksidestage.remote.fortress.signup;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.signup.signup.RemoteSignupSignupParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of signup.
 * @author FreeGen
 */
public abstract class BsRemoteFortressSignupBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressSignupBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /signup/. (auto-generated method)<br>
     * <pre>
     * url: /signup/
     * httpMethod: GET
     * </pre>
     */
    public void request() {
        doRequest(rule -> {});
    }

    /**
     * Request remote call to /signup/. (auto-generated method)<br>
     * <pre>
     * url: /signup/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequest(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/signup/", noMoreUrl(), noQuery(), rule -> {
            ruleOf(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /signup/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOf(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /signup/signup. (auto-generated method)<br>
     * <pre>
     * url: /signup/signup
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteSignupSignupParam. (NotNull)
     */
    public void requestSignup(Consumer<RemoteSignupSignupParam> paramLambda) {
        doRequestSignup(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /signup/signup. (auto-generated method)<br>
     * <pre>
     * url: /signup/signup
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteSignupSignupParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestSignup(Consumer<RemoteSignupSignupParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteSignupSignupParam param = new RemoteSignupSignupParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/signup/signup", noMoreUrl(), query(param), rule -> {
            ruleOfSignup(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /signup/signup.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfSignup(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /signup/register/{account}/{token}. (auto-generated method)<br>
     * <pre>
     * url: /signup/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param account The value of path variable for account. (NotNull)
     * @param token The value of path variable for token. (NotNull)
     */
    public void requestRegister(String account, String token) {
        doRequestRegister(account, token, rule -> {});
    }

    /**
     * Request remote call to /signup/register/{account}/{token}. (auto-generated method)<br>
     * <pre>
     * url: /signup/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param account The value of path variable for account. (NotNull)
     * @param token The value of path variable for token. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRegister(String account, String token, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/signup/register/{account}/{token}", moreUrl(account, token), noQuery(), rule -> {
            ruleOfRegisterAccountToken(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /signup/register/{account}/{token}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRegisterAccountToken(FlutyRemoteApiRule rule) {
    }
}
