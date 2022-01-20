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
package org.docksidestage.remote.fortress.product;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.product.list.RemoteProductListPagenumberParam;
import org.docksidestage.remote.fortress.product.list.RemoteProductListParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of product.
 * @author FreeGen
 */
public abstract class BsRemoteFortressProductBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressProductBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /product/detail/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /product/detail/{productId}
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     */
    public void requestDetail(Integer productId) {
        doRequestDetail(productId, rule -> {});
    }

    /**
     * Request remote call to /product/detail/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /product/detail/{productId}
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestDetail(Integer productId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/product/detail/{productId}", moreUrl(productId), noQuery(), rule -> {
            ruleOfDetailProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /product/detail/{productId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDetailProductId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /product/list. (auto-generated method)<br>
     * <pre>
     * url: /product/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductListParam. (NotNull)
     */
    public void requestList(Consumer<RemoteProductListParam> paramLambda) {
        doRequestList(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /product/list. (auto-generated method)<br>
     * <pre>
     * url: /product/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteProductListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestList(Consumer<RemoteProductListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductListParam param = new RemoteProductListParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/product/list", noMoreUrl(), query(param), rule -> {
            ruleOfList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /product/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /product/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /product/list/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteProductListPagenumberParam. (NotNull)
     */
    public void requestList(Integer pageNumber, Consumer<RemoteProductListPagenumberParam> paramLambda) {
        doRequestList(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /product/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /product/list/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteProductListPagenumberParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestList(Integer pageNumber, Consumer<RemoteProductListPagenumberParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductListPagenumberParam param = new RemoteProductListPagenumberParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/product/list/{pageNumber}", moreUrl(pageNumber), query(param), rule -> {
            ruleOfListPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /product/list/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfListPageNumber(FlutyRemoteApiRule rule) {
    }
}
