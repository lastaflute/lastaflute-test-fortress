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
package org.docksidestage.remote.maihama.showbase.balletdancers.greatestfavoritestudios;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.balletdancers.greatestfavoritestudios.index.RemoteBalletdancersGreatestfavoritestudiosParam;
import org.docksidestage.remote.maihama.showbase.balletdancers.greatestfavoritestudios.index.RemoteBalletdancersGreatestfavoritestudiosReturn;
import org.docksidestage.remote.maihama.showbase.balletdancers.greatestfavoritestudios.index.RemoteBalletdancersProductidGreatestfavoritestudiosPurchaseidReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of balletdancers.greatestfavoritestudios.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseBalletdancersGreatestfavoritestudiosBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseBalletdancersGreatestfavoritestudiosBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteBalletdancersGreatestfavoritestudiosParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteBalletdancersGreatestfavoritestudiosReturn requestGet(Integer productId, Consumer<RemoteBalletdancersGreatestfavoritestudiosParam> paramLambda) {
        return doRequestGet(productId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteBalletdancersGreatestfavoritestudiosParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteBalletdancersGreatestfavoritestudiosReturn doRequestGet(Integer productId, Consumer<RemoteBalletdancersGreatestfavoritestudiosParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteBalletdancersGreatestfavoritestudiosParam param = new RemoteBalletdancersGreatestfavoritestudiosParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteBalletdancersGreatestfavoritestudiosReturn.class, "/ballet-dancers/{productId}/greatest-favorite-studios/", moreUrl(productId), query(param), rule -> {
            ruleOfGetProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/{productId}/greatest-favorite-studios/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteBalletdancersProductidGreatestfavoritestudiosPurchaseidReturn requestGet(Integer productId, Long purchaseId) {
        return doRequestGet(productId, purchaseId, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteBalletdancersProductidGreatestfavoritestudiosPurchaseidReturn doRequestGet(Integer productId, Long purchaseId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteBalletdancersProductidGreatestfavoritestudiosPurchaseidReturn.class, "/ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/", moreUrl(productId, purchaseId), noQuery(), rule -> {
            ruleOfGetProductIdPurchaseId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/{productId}/greatest-favorite-studios/{purchaseId}/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductIdPurchaseId(FlutyRemoteApiRule rule) {
    }
}
