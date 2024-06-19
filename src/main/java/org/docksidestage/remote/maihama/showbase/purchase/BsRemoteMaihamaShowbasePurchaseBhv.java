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
package org.docksidestage.remote.maihama.showbase.purchase;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.purchase.contract.RemotePurchaseContractParam;
import org.docksidestage.remote.maihama.showbase.purchase.contract.RemotePurchaseContractReturn;
import org.docksidestage.remote.maihama.showbase.purchase.count.RemotePurchaseCountParam;
import org.docksidestage.remote.maihama.showbase.purchase.count.RemotePurchaseCountReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of purchase.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbasePurchaseBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbasePurchaseBhv(RequestManager requestManager) {
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
     * @param paramLambda The callback for RemotePurchaseCountParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemotePurchaseCountReturn requestCount(Consumer<RemotePurchaseCountParam> paramLambda) {
        return doRequestCount(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /purchase/count. (auto-generated method)<br>
     * <pre>
     * url: /purchase/count
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemotePurchaseCountParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemotePurchaseCountReturn doRequestCount(Consumer<RemotePurchaseCountParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemotePurchaseCountParam param = new RemotePurchaseCountParam();
        paramLambda.accept(param);
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
     * @param paramLambda The callback for RemotePurchaseContractParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemotePurchaseContractReturn requestContract(Consumer<RemotePurchaseContractParam> paramLambda) {
        return doRequestContract(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /purchase/contract. (auto-generated method)<br>
     * <pre>
     * url: /purchase/contract
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemotePurchaseContractParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemotePurchaseContractReturn doRequestContract(Consumer<RemotePurchaseContractParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemotePurchaseContractParam param = new RemotePurchaseContractParam();
        paramLambda.accept(param);
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
