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
package org.docksidestage.remote.fortress.products.hangarmystic;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.products.hangarmystic.index.RemoteProductsHangarmysticGetParam;
import org.docksidestage.remote.fortress.products.hangarmystic.index.RemoteProductsHangarmysticGetReturn;
import org.docksidestage.remote.fortress.products.hangarmystic.index.RemoteProductsHangarmysticPostParam;
import org.docksidestage.remote.fortress.products.hangarmystic.index.RemoteProductsHangarmysticPostReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of products.hangarmystic.
 * @author FreeGen
 */
public abstract class BsRemoteFortressProductsHangarmysticBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressProductsHangarmysticBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /products/hangar-mystic/. (auto-generated method)<br>
     * <pre>
     * url: /products/hangar-mystic/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsHangarmysticGetParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteProductsHangarmysticGetReturn> requestGet(Consumer<RemoteProductsHangarmysticGetParam> paramLambda) {
        return doRequestGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/hangar-mystic/. (auto-generated method)<br>
     * <pre>
     * url: /products/hangar-mystic/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsHangarmysticGetParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteProductsHangarmysticGetReturn> doRequestGet(Consumer<RemoteProductsHangarmysticGetParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsHangarmysticGetParam param = new RemoteProductsHangarmysticGetParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteProductsHangarmysticGetReturn>>() {
        }.getType(), "/products/hangar-mystic/", noMoreUrl(), query(param), rule -> {
            ruleOfGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/hangar-mystic/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGet(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /products/hangar-mystic/. (auto-generated method)<br>
     * <pre>
     * url: /products/hangar-mystic/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteProductsHangarmysticPostParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteProductsHangarmysticPostReturn> requestPost(Consumer<RemoteProductsHangarmysticPostParam> paramLambda) {
        return doRequestPost(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/hangar-mystic/. (auto-generated method)<br>
     * <pre>
     * url: /products/hangar-mystic/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteProductsHangarmysticPostParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteProductsHangarmysticPostReturn> doRequestPost(Consumer<RemoteProductsHangarmysticPostParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsHangarmysticPostParam param = new RemoteProductsHangarmysticPostParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteProductsHangarmysticPostReturn>>() {
        }.getType(), "/products/hangar-mystic/", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfPost(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/hangar-mystic/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPost(FlutyRemoteApiRule rule) {
    }
}
