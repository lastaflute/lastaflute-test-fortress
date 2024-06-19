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
package org.docksidestage.remote.fortress.products.showbaseoneman;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.products.showbaseoneman.index.RemoteProductsShowbaseonemanReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of products.showbaseoneman.
 * @author FreeGen
 */
public abstract class BsRemoteFortressProductsShowbaseonemanBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressProductsShowbaseonemanBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /products/{productId}/showbase-oneman/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/showbase-oneman/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteProductsShowbaseonemanReturn requestGet(Integer productId) {
        return doRequestGet(productId, rule -> {});
    }

    /**
     * Request remote call to /products/{productId}/showbase-oneman/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/showbase-oneman/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteProductsShowbaseonemanReturn doRequestGet(Integer productId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteProductsShowbaseonemanReturn.class, "/products/{productId}/showbase-oneman/", moreUrl(productId), noQuery(), rule -> {
            ruleOfGetProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/{productId}/showbase-oneman/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductId(FlutyRemoteApiRule rule) {
    }
}
