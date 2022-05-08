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
package org.docksidestage.remote.fortress.products.sea;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.products.sea.index.RemoteProductsSeaParam;
import org.docksidestage.remote.fortress.products.sea.index.RemoteProductsSeaReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of products.sea.
 * @author FreeGen
 */
public abstract class BsRemoteFortressProductsSeaBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressProductsSeaBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /products/sea/. (auto-generated method)<br>
     * <pre>
     * url: /products/sea/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsSeaParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteProductsSeaReturn> requestGet(Consumer<RemoteProductsSeaParam> paramLambda) {
        return doRequestGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/sea/. (auto-generated method)<br>
     * <pre>
     * url: /products/sea/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsSeaParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteProductsSeaReturn> doRequestGet(Consumer<RemoteProductsSeaParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsSeaParam param = new RemoteProductsSeaParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteProductsSeaReturn>>() {
        }.getType(), "/products/sea/", noMoreUrl(), query(param), rule -> {
            ruleOfGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/sea/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGet(FlutyRemoteApiRule rule) {
    }
}
