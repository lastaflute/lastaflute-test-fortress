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
package org.docksidestage.remote.maihama.showbase.products.purchases.payments;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.products.purchases.payments.index.RemoteProductsProductidPurchasesPurchaseidPaymentsPaymentidReturn;
import org.docksidestage.remote.maihama.showbase.products.purchases.payments.index.RemoteProductsPurchasesPaymentsParam;
import org.docksidestage.remote.maihama.showbase.products.purchases.payments.index.RemoteProductsPurchasesPaymentsReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of products.purchases.payments.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseProductsPurchasesPaymentsBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseProductsPurchasesPaymentsBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /products/{productId}/purchases/{purchaseId}/payments/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/purchases/{purchaseId}/payments/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param paramLambda The callback for RemoteProductsPurchasesPaymentsParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteProductsPurchasesPaymentsReturn> requestGet(Integer productId, Long purchaseId, Consumer<RemoteProductsPurchasesPaymentsParam> paramLambda) {
        return doRequestGet(productId, purchaseId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /products/{productId}/purchases/{purchaseId}/payments/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/purchases/{purchaseId}/payments/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param paramLambda The callback for RemoteProductsPurchasesPaymentsParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteProductsPurchasesPaymentsReturn> doRequestGet(Integer productId, Long purchaseId, Consumer<RemoteProductsPurchasesPaymentsParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteProductsPurchasesPaymentsParam param = new RemoteProductsPurchasesPaymentsParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteProductsPurchasesPaymentsReturn>>() {
        }.getType(), "/products/{productId}/purchases/{purchaseId}/payments/", moreUrl(productId, purchaseId), query(param), rule -> {
            ruleOfGetProductIdPurchaseId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/{productId}/purchases/{purchaseId}/payments/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductIdPurchaseId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /products/{productId}/purchases/{purchaseId}/payments/{paymentId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/purchases/{purchaseId}/payments/{paymentId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param paymentId The value of path variable for paymentId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteProductsProductidPurchasesPurchaseidPaymentsPaymentidReturn requestGet(Integer productId, Long purchaseId, Long paymentId) {
        return doRequestGet(productId, purchaseId, paymentId, rule -> {});
    }

    /**
     * Request remote call to /products/{productId}/purchases/{purchaseId}/payments/{paymentId}/. (auto-generated method)<br>
     * <pre>
     * url: /products/{productId}/purchases/{purchaseId}/payments/{paymentId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param paymentId The value of path variable for paymentId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteProductsProductidPurchasesPurchaseidPaymentsPaymentidReturn doRequestGet(Integer productId, Long purchaseId, Long paymentId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteProductsProductidPurchasesPurchaseidPaymentsPaymentidReturn.class, "/products/{productId}/purchases/{purchaseId}/payments/{paymentId}/", moreUrl(productId, purchaseId, paymentId), noQuery(), rule -> {
            ruleOfGetProductIdPurchaseIdPaymentId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /products/{productId}/purchases/{purchaseId}/payments/{paymentId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductIdPurchaseIdPaymentId(FlutyRemoteApiRule rule) {
    }
}
