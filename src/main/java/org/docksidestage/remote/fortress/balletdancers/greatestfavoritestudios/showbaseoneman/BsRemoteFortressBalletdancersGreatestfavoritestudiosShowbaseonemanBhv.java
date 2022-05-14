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
package org.docksidestage.remote.fortress.balletdancers.greatestfavoritestudios.showbaseoneman;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.balletdancers.greatestfavoritestudios.showbaseoneman.index.RemoteBalletdancersGreatestfavoritestudiosShowbaseonemanReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of balletdancers.greatestfavoritestudios.showbaseoneman.
 * @author FreeGen
 */
public abstract class BsRemoteFortressBalletdancersGreatestfavoritestudiosShowbaseonemanBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressBalletdancersGreatestfavoritestudiosShowbaseonemanBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/showbase-oneman/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/showbase-oneman/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteBalletdancersGreatestfavoritestudiosShowbaseonemanReturn requestGet(Integer productId, Long purchaseId) {
        return doRequestGet(productId, purchaseId, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/showbase-oneman/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/showbase-oneman/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteBalletdancersGreatestfavoritestudiosShowbaseonemanReturn doRequestGet(Integer productId, Long purchaseId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteBalletdancersGreatestfavoritestudiosShowbaseonemanReturn.class, "/ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/showbase-oneman/", moreUrl(productId, purchaseId), noQuery(), rule -> {
            ruleOfGetProductIdPurchaseId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/showbase-oneman/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductIdPurchaseId(FlutyRemoteApiRule rule) {
    }
}
