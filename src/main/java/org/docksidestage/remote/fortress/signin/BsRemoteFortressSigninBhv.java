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
package org.docksidestage.remote.fortress.signin;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.signin.signin.RemoteSigninSigninParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of signin.
 * @author FreeGen
 */
public abstract class BsRemoteFortressSigninBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressSigninBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /signin/. (auto-generated method)<br>
     * <pre>
     * url: /signin/
     * httpMethod: GET
     * </pre>
     */
    public void request() {
        doRequest(rule -> {});
    }

    /**
     * Request remote call to /signin/. (auto-generated method)<br>
     * <pre>
     * url: /signin/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequest(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/signin/", noMoreUrl(), noQuery(), rule -> {
            ruleOf(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /signin/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOf(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /signin/signin. (auto-generated method)<br>
     * <pre>
     * url: /signin/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteSigninSigninParam. (NotNull)
     */
    public void requestSignin(Consumer<RemoteSigninSigninParam> paramLambda) {
        doRequestSignin(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /signin/signin. (auto-generated method)<br>
     * <pre>
     * url: /signin/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteSigninSigninParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestSignin(Consumer<RemoteSigninSigninParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteSigninSigninParam param = new RemoteSigninSigninParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/signin/signin", noMoreUrl(), query(param), rule -> {
            ruleOfSignin(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /signin/signin.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfSignin(FlutyRemoteApiRule rule) {
    }
}
