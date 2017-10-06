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
package org.docksidestage.remote.maihama.showbase.withdrawal;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.withdrawal.done.RemoteWithdrawalDoneParam;

/**
 * RemoteMaihamaShowbaseBsWithdrawalBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsWithdrawalBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsWithdrawalBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to  Reason. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/reason
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public java.util.List<Object> requestReason() {
        return requestReason(rule -> {});
    }

    /**
     * Set up method-level rule of Reason. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/reason
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return return object. (NotNull)
     */
    protected java.util.List<Object> requestReason(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.List<Object>>() {
        }.getType(), "/withdrawal/reason", noMoreUrl(), null, rule -> {
            ruleOfReason(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Reason.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfReason(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  Done. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/done
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteWithdrawalDoneParam. (NotNull)
     * @return return object. (NotNull)
     */
    public Integer requestDone(Consumer<RemoteWithdrawalDoneParam> paramLamda) {
        return requestDone(paramLamda, rule -> {});
    }

    /**
     * Set up method-level rule of Done. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/done
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteWithdrawalDoneParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return return object. (NotNull)
     */
    protected Integer requestDone(Consumer<RemoteWithdrawalDoneParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWithdrawalDoneParam param = new RemoteWithdrawalDoneParam();
        paramLamda.accept(param);
        return doRequestPost(Integer.class, "/withdrawal/done", noMoreUrl(), param, rule -> {
            ruleOfDone(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Done.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDone(FlutyRemoteApiRule rule) {
    }
}
