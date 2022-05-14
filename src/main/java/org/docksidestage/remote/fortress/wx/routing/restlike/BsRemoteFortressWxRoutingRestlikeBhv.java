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
package org.docksidestage.remote.fortress.wx.routing.restlike;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.wx.routing.restlike.index.RemoteWxRoutingRestlikeDeleteParam;
import org.docksidestage.remote.fortress.wx.routing.restlike.index.RemoteWxRoutingRestlikeDeleteReturn;
import org.docksidestage.remote.fortress.wx.routing.restlike.index.RemoteWxRoutingRestlikeGetReturn;
import org.docksidestage.remote.fortress.wx.routing.restlike.index.RemoteWxRoutingRestlikeParam;
import org.docksidestage.remote.fortress.wx.routing.restlike.index.RemoteWxRoutingRestlikePutParam;
import org.docksidestage.remote.fortress.wx.routing.restlike.index.RemoteWxRoutingRestlikePutReturn;
import org.docksidestage.remote.fortress.wx.routing.restlike.index.RemoteWxRoutingRestlikeReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of wx.routing.restlike.
 * @author FreeGen
 */
public abstract class BsRemoteFortressWxRoutingRestlikeBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressWxRoutingRestlikeBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /wx/routing/restlike. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeReturn requestGet() {
        return doRequestGet(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeReturn doRequestGet(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingRestlikeReturn.class, "/wx/routing/restlike", noMoreUrl(), noQuery(), rule -> {
            ruleOfGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGet(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeGetReturn requestGet(String first) {
        return doRequestGet(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeGetReturn doRequestGet(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingRestlikeGetReturn.class, "/wx/routing/restlike/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfGetFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}
     * httpMethod: PUT
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingRestlikePutParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikePutReturn requestPut(String first, Consumer<RemoteWxRoutingRestlikePutParam> paramLambda) {
        return doRequestPut(first, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}
     * httpMethod: PUT
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingRestlikePutParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikePutReturn doRequestPut(String first, Consumer<RemoteWxRoutingRestlikePutParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingRestlikePutParam param = new RemoteWxRoutingRestlikePutParam();
        paramLambda.accept(param);
        return doRequestPut(RemoteWxRoutingRestlikePutReturn.class, "/wx/routing/restlike/{first}", moreUrl(first), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfPutFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPutFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}
     * httpMethod: DELETE
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingRestlikeDeleteParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeDeleteReturn requestDelete(String first, Consumer<RemoteWxRoutingRestlikeDeleteParam> paramLambda) {
        return doRequestDelete(first, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/{first}
     * httpMethod: DELETE
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingRestlikeDeleteParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeDeleteReturn doRequestDelete(String first, Consumer<RemoteWxRoutingRestlikeDeleteParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingRestlikeDeleteParam param = new RemoteWxRoutingRestlikeDeleteParam();
        paramLambda.accept(param);
        return doRequestDelete(RemoteWxRoutingRestlikeDeleteReturn.class, "/wx/routing/restlike/{first}", moreUrl(first), query(param), rule -> {
            ruleOfDeleteFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDeleteFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/restlike/. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRoutingRestlikeParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeReturn requestPost(Consumer<RemoteWxRoutingRestlikeParam> paramLambda) {
        return doRequestPost(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRoutingRestlikeParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeReturn doRequestPost(Consumer<RemoteWxRoutingRestlikeParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingRestlikeParam param = new RemoteWxRoutingRestlikeParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteWxRoutingRestlikeReturn.class, "/wx/routing/restlike/", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfPost(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPost(FlutyRemoteApiRule rule) {
    }
}
