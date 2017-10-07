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
package org.docksidestage.remote.maihama.showbase.purchase;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.purchase.count.RemotePurchaseCountParam;
import org.docksidestage.remote.maihama.showbase.purchase.count.RemotePurchaseCountReturn;
import org.docksidestage.remote.maihama.showbase.purchase.contract.RemotePurchaseContractParam;
import org.docksidestage.remote.maihama.showbase.purchase.contract.RemotePurchaseContractReturn;

/**
 * RemoteMaihamaShowbaseBsPurchaseBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsPurchaseBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public RemoteMaihamaShowbaseBsPurchaseBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /purchase/count. (auto-generated method)<br>
     * <pre>
     * url: /purchase/count
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemotePurchaseCountParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemotePurchaseCountReturn requestCount(Consumer<RemotePurchaseCountParam> paramLamda) {
        return requestCount(paramLamda, rule -> {});
    }

    /**
     * Request remote call to /purchase/count. (auto-generated method)<br>
     * <pre>
     * url: /purchase/count
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemotePurchaseCountParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemotePurchaseCountReturn requestCount(Consumer<RemotePurchaseCountParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemotePurchaseCountParam param = new RemotePurchaseCountParam();
        paramLamda.accept(param);
        return doRequestPost(RemotePurchaseCountReturn.class, "/purchase/count", noMoreUrl(), param, rule -> {
            ruleOfCount(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /purchase/count.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfCount(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /purchase/contract. (auto-generated method)<br>
     * <pre>
     * url: /purchase/contract
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemotePurchaseContractParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemotePurchaseContractReturn requestContract(Consumer<RemotePurchaseContractParam> paramLamda) {
        return requestContract(paramLamda, rule -> {});
    }

    /**
     * Request remote call to /purchase/contract. (auto-generated method)<br>
     * <pre>
     * url: /purchase/contract
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemotePurchaseContractParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemotePurchaseContractReturn requestContract(Consumer<RemotePurchaseContractParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemotePurchaseContractParam param = new RemotePurchaseContractParam();
        paramLamda.accept(param);
        return doRequestPost(RemotePurchaseContractReturn.class, "/purchase/contract", noMoreUrl(), param, rule -> {
            ruleOfContract(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /purchase/contract.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfContract(FlutyRemoteApiRule rule) {
    }
}
