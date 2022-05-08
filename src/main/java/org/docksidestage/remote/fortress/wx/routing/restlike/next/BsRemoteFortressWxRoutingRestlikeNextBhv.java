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
package org.docksidestage.remote.fortress.wx.routing.restlike.next;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.wx.routing.restlike.next.index.RemoteWxRoutingRestlikeNextGetReturn;
import org.docksidestage.remote.fortress.wx.routing.restlike.next.index.RemoteWxRoutingRestlikeNextPostParam;
import org.docksidestage.remote.fortress.wx.routing.restlike.next.index.RemoteWxRoutingRestlikeNextPostReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of wx.routing.restlike.next.
 * @author FreeGen
 */
public abstract class BsRemoteFortressWxRoutingRestlikeNextBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressWxRoutingRestlikeNextBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /wx/routing/restlike/{first}/next/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}/next/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeNextGetReturn requestGet(String first, String second) {
        return doRequestGet(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}/next/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}/next/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeNextGetReturn doRequestGet(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingRestlikeNextGetReturn.class, "/wx/routing/restlike/{first}/next/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfGetFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/{first}/next/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}/next/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}/next/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingRestlikeNextPostParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeNextPostReturn requestPost(String first, String second, Consumer<RemoteWxRoutingRestlikeNextPostParam> paramLambda) {
        return doRequestPost(first, second, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}/next/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}/next/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingRestlikeNextPostParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeNextPostReturn doRequestPost(String first, String second, Consumer<RemoteWxRoutingRestlikeNextPostParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingRestlikeNextPostParam param = new RemoteWxRoutingRestlikeNextPostParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteWxRoutingRestlikeNextPostReturn.class, "/wx/routing/restlike/{first}/next/{second}", moreUrl(first, second), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfPostFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/{first}/next/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPostFirstSecond(FlutyRemoteApiRule rule) {
    }
}
