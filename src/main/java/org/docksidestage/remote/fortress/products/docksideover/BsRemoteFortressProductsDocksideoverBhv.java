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
package org.docksidestage.remote.fortress.products.docksideover;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.products.docksideover.index.RemoteProductsDocksideoverParam;
import org.docksidestage.remote.fortress.products.docksideover.index.RemoteProductsDocksideoverReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of products.docksideover.
 * @author FreeGen
 */
public abstract class BsRemoteFortressProductsDocksideoverBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressProductsDocksideoverBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /products/docksideOver/. (auto-generated method)<br>
     * <pre>
     * url: /products/docksideOver/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsDocksideoverParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteProductsDocksideoverReturn> requestGet(Consumer<RemoteProductsDocksideoverParam> paramLambda) {
        return doRequestGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/docksideOver/. (auto-generated method)<br>
     * <pre>
     * url: /products/docksideOver/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsDocksideoverParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteProductsDocksideoverReturn> doRequestGet(Consumer<RemoteProductsDocksideoverParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsDocksideoverParam param = new RemoteProductsDocksideoverParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteProductsDocksideoverReturn>>() {
        }.getType(), "/products/docksideOver/", noMoreUrl(), query(param), rule -> {
            ruleOfGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/docksideOver/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGet(FlutyRemoteApiRule rule) {
    }
}
