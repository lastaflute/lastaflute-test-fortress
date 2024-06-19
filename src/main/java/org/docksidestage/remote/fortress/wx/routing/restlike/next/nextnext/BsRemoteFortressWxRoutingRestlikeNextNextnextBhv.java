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
package org.docksidestage.remote.fortress.wx.routing.restlike.next.nextnext;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.wx.routing.restlike.next.nextnext.index.RemoteWxRoutingRestlikeNextNextnextReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of wx.routing.restlike.next.nextnext.
 * @author FreeGen
 */
public abstract class BsRemoteFortressWxRoutingRestlikeNextNextnextBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressWxRoutingRestlikeNextNextnextBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /wx/routing/restlike/{first}/next/{second}/nextnext/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}/next/{second}/nextnext/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeNextNextnextReturn requestGet(String first, String second, String third) {
        return doRequestGet(first, second, third, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}/next/{second}/nextnext/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}/next/{second}/nextnext/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeNextNextnextReturn doRequestGet(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingRestlikeNextNextnextReturn.class, "/wx/routing/restlike/{first}/next/{second}/nextnext/{third}", moreUrl(first, second, third), noQuery(), rule -> {
            ruleOfGetFirstSecondThird(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/{first}/next/{second}/nextnext/{third}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetFirstSecondThird(FlutyRemoteApiRule rule) {
    }
}
