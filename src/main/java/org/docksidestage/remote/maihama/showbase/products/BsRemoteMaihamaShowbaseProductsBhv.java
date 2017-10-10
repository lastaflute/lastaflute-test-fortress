/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage.remote.maihama.showbase.products;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsParam;

/**
 * The base class as generation gap for remote API of products.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseProductsBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseProductsBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /products/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteProductsParam. (NotNull)
     */
    public void request(Integer productId, Consumer<RemoteProductsParam> paramLambda) {
        request(productId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteProductsParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void request(Integer productId, Consumer<RemoteProductsParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsParam param = new RemoteProductsParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/products/{productId}", moreUrl(productId), param, rule -> {
            ruleOfProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/{productId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfProductId(FlutyRemoteApiRule rule) {
    }
}
