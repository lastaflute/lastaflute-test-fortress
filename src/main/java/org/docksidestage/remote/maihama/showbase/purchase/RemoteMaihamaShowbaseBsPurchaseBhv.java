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
import org.lastaflute.di.helper.misc.ParameterizedRef;
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
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsPurchaseBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Count.<br>
     * <pre>
     * url: /purchase/count
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemotePurchaseCountParam. (NotNull)
     * @return return object. (NotNull)
     */
    public RemotePurchaseCountReturn requestCount(Consumer<RemotePurchaseCountParam> paramLamda) {
        RemotePurchaseCountParam param = new RemotePurchaseCountParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemotePurchaseCountReturn>() {
        }.getType(), "/purchase/count", noMoreUrl(), param, rule -> ruleOfCount(rule));
    }

    /**
     * Rule of Count.<br>
     * <pre>
     * url: /purchase/count
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfCount(FlutyRemoteApiRule rule) {
    }

    /**
     * Contract.<br>
     * <pre>
     * url: /purchase/contract
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemotePurchaseContractParam. (NotNull)
     * @return return object. (NotNull)
     */
    public RemotePurchaseContractReturn requestContract(Consumer<RemotePurchaseContractParam> paramLamda) {
        RemotePurchaseContractParam param = new RemotePurchaseContractParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemotePurchaseContractReturn>() {
        }.getType(), "/purchase/contract", noMoreUrl(), param, rule -> ruleOfContract(rule));
    }

    /**
     * Rule of Contract.<br>
     * <pre>
     * url: /purchase/contract
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfContract(FlutyRemoteApiRule rule) {
    }
}
