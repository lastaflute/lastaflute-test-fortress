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

import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.withdrawal.done.RemoteWithdrawalDoneParam;

/**
 * The base class as generation gap for remote API of withdrawal.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseWithdrawalBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseWithdrawalBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /withdrawal/reason. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/reason
     * httpMethod: POST
     * </pre>
     */
    public void requestReason() {
        requestReason(rule -> {});
    }

    /**
     * Request remote call to /withdrawal/reason. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/reason
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestReason(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestPost(void.class, "/withdrawal/reason", noMoreUrl(), null, rule -> {
            ruleOfReason(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /withdrawal/reason.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfReason(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /withdrawal/done. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/done
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWithdrawalDoneParam. (NotNull)
     */
    public void requestDone(Consumer<RemoteWithdrawalDoneParam> paramLambda) {
        requestDone(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /withdrawal/done. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/done
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWithdrawalDoneParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestDone(Consumer<RemoteWithdrawalDoneParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWithdrawalDoneParam param = new RemoteWithdrawalDoneParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/withdrawal/done", noMoreUrl(), param, rule -> {
            ruleOfDone(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /withdrawal/done.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDone(FlutyRemoteApiRule rule) {
    }
}
