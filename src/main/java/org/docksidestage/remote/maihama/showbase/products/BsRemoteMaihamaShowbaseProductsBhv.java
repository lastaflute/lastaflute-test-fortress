/*
 * Copyright 2015-2018 the original author or authors.
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
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.products.detail.RemoteProductsDetailParam;
import org.docksidestage.remote.maihama.showbase.products.detail.RemoteProductsDetailReturn;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsParam;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of products.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseProductsBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseProductsBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /products/. (auto-generated method)<br>
     * <pre>
     * url: /products/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteProductsParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteProductsReturn request(Consumer<RemoteProductsParam> paramLambda) {
        return request(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/. (auto-generated method)<br>
     * <pre>
     * url: /products/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteProductsParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteProductsReturn request(Consumer<RemoteProductsParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsParam param = new RemoteProductsParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteProductsReturn.class, "/products/", noMoreUrl(), param, rule -> {
            ruleOf(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOf(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /products/detail/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /products/detail/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteProductsDetailParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteProductsDetailReturn requestDetail(Integer productId, Consumer<RemoteProductsDetailParam> paramLambda) {
        return requestDetail(productId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/detail/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /products/detail/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteProductsDetailParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteProductsDetailReturn requestDetail(Integer productId, Consumer<RemoteProductsDetailParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsDetailParam param = new RemoteProductsDetailParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteProductsDetailReturn.class, "/products/detail/{productId}", moreUrl(productId), param, rule -> {
            ruleOfDetailProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/detail/{productId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDetailProductId(FlutyRemoteApiRule rule) {
    }
}
