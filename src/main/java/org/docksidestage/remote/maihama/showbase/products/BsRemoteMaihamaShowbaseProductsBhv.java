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
package org.docksidestage.remote.maihama.showbase.products;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsGetParam;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsGetReturn;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsPostParam;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsProductidGetReturn;
import org.docksidestage.remote.maihama.showbase.products.index.RemoteProductsPutParam;
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
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsGetParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteProductsGetReturn> requestGet(Consumer<RemoteProductsGetParam> paramLambda) {
        return doRequestGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/. (auto-generated method)<br>
     * <pre>
     * url: /products/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductsGetParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteProductsGetReturn> doRequestGet(Consumer<RemoteProductsGetParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsGetParam param = new RemoteProductsGetParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteProductsGetReturn>>() {
        }.getType(), "/products/", noMoreUrl(), query(param), rule -> {
            ruleOfGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGet(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /products/. (auto-generated method)<br>
     * <pre>
     * url: /products/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteProductsPostParam. (NotNull)
     */
    public void requestPost(Consumer<RemoteProductsPostParam> paramLambda) {
        doRequestPost(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/. (auto-generated method)<br>
     * <pre>
     * url: /products/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteProductsPostParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestPost(Consumer<RemoteProductsPostParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsPostParam param = new RemoteProductsPostParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/products/", noMoreUrl(), param, rule -> {
            ruleOfPost(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPost(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /products/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteProductsProductidGetReturn requestGet(Integer productId) {
        return doRequestGet(productId, rule -> {});
    }

    /**
     * Request remote call to /products/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteProductsProductidGetReturn doRequestGet(Integer productId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteProductsProductidGetReturn.class, "/products/{productId}/", moreUrl(productId), noQuery(), rule -> {
            ruleOfGetProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/{productId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /products/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/
     * httpMethod: PUT
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteProductsPutParam. (NotNull)
     */
    public void requestPut(Integer productId, Consumer<RemoteProductsPutParam> paramLambda) {
        doRequestPut(productId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/
     * httpMethod: PUT
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteProductsPutParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestPut(Integer productId, Consumer<RemoteProductsPutParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsPutParam param = new RemoteProductsPutParam();
        paramLambda.accept(param);
        doRequestPut(void.class, "/products/{productId}/", moreUrl(productId), param, rule -> {
            ruleOfPutProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/{productId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPutProductId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /products/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/
     * httpMethod: DELETE
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     */
    public void requestDelete(Integer productId) {
        doRequestDelete(productId, rule -> {});
    }

    /**
     * Request remote call to /products/{productId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/
     * httpMethod: DELETE
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestDelete(Integer productId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestDelete(void.class, "/products/{productId}/", moreUrl(productId), noQuery(), rule -> {
            ruleOfDeleteProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/{productId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDeleteProductId(FlutyRemoteApiRule rule) {
    }
}
