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
package org.docksidestage.remote.maihama.showbase.balletdancers;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.balletdancers.index.RemoteBalletdancersGetParam;
import org.docksidestage.remote.maihama.showbase.balletdancers.index.RemoteBalletdancersGetReturn;
import org.docksidestage.remote.maihama.showbase.balletdancers.index.RemoteBalletdancersPostParam;
import org.docksidestage.remote.maihama.showbase.balletdancers.index.RemoteBalletdancersProductidGetReturn;
import org.docksidestage.remote.maihama.showbase.balletdancers.index.RemoteBalletdancersPutParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of balletdancers.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseBalletdancersBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseBalletdancersBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /ballet-dancers/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteBalletdancersGetParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteBalletdancersGetReturn> requestGet(Consumer<RemoteBalletdancersGetParam> paramLambda) {
        return doRequestGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteBalletdancersGetParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteBalletdancersGetReturn> doRequestGet(Consumer<RemoteBalletdancersGetParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteBalletdancersGetParam param = new RemoteBalletdancersGetParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteBalletdancersGetReturn>>() {
        }.getType(), "/ballet-dancers/", noMoreUrl(), query(param), rule -> {
            ruleOfGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGet(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /ballet-dancers/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteBalletdancersPostParam. (NotNull)
     */
    public void requestPost(Consumer<RemoteBalletdancersPostParam> paramLambda) {
        doRequestPost(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteBalletdancersPostParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestPost(Consumer<RemoteBalletdancersPostParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteBalletdancersPostParam param = new RemoteBalletdancersPostParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/ballet-dancers/", noMoreUrl(), param, rule -> {
            ruleOfPost(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPost(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteBalletdancersProductidGetReturn requestGet(Integer productId) {
        return doRequestGet(productId, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteBalletdancersProductidGetReturn doRequestGet(Integer productId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteBalletdancersProductidGetReturn.class, "/ballet-dancers/{productId}/", moreUrl(productId), noQuery(), rule -> {
            ruleOfGetProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/{productId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/
     * httpMethod: PUT
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteBalletdancersPutParam. (NotNull)
     */
    public void requestPut(Integer productId, Consumer<RemoteBalletdancersPutParam> paramLambda) {
        doRequestPut(productId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/
     * httpMethod: PUT
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteBalletdancersPutParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestPut(Integer productId, Consumer<RemoteBalletdancersPutParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteBalletdancersPutParam param = new RemoteBalletdancersPutParam();
        paramLambda.accept(param);
        doRequestPut(void.class, "/ballet-dancers/{productId}/", moreUrl(productId), param, rule -> {
            ruleOfPutProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/{productId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPutProductId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/
     * httpMethod: DELETE
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     */
    public void requestDelete(Integer productId) {
        doRequestDelete(productId, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/
     * httpMethod: DELETE
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestDelete(Integer productId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestDelete(void.class, "/ballet-dancers/{productId}/", moreUrl(productId), noQuery(), rule -> {
            ruleOfDeleteProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/{productId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDeleteProductId(FlutyRemoteApiRule rule) {
    }
}
