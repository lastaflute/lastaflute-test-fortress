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
package org.docksidestage.remote.fortress.onparade;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.onparade.index.RemoteOnparadePagenumberParam;
import org.docksidestage.remote.fortress.onparade.index.RemoteOnparadeParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of onparade.
 * @author FreeGen
 */
public abstract class BsRemoteFortressOnparadeBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressOnparadeBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /onparade. (auto-generated method)<br>
     * <pre>
     * url: /onparade
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteOnparadeParam. (NotNull)
     */
    public void request(Consumer<RemoteOnparadeParam> paramLambda) {
        doRequest(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /onparade. (auto-generated method)<br>
     * <pre>
     * url: /onparade
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteOnparadeParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequest(Consumer<RemoteOnparadeParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteOnparadeParam param = new RemoteOnparadeParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/onparade", noMoreUrl(), query(param), rule -> {
            ruleOf(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /onparade.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOf(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /onparade/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /onparade/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteOnparadePagenumberParam. (NotNull)
     */
    public void request(Integer pageNumber, Consumer<RemoteOnparadePagenumberParam> paramLambda) {
        doRequest(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /onparade/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /onparade/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteOnparadePagenumberParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequest(Integer pageNumber, Consumer<RemoteOnparadePagenumberParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteOnparadePagenumberParam param = new RemoteOnparadePagenumberParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/onparade/{pageNumber}", moreUrl(pageNumber), query(param), rule -> {
            ruleOfPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /onparade/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPageNumber(FlutyRemoteApiRule rule) {
    }
}
