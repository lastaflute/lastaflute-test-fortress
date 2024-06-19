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
package org.docksidestage.remote.fortress.withdrawal;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.withdrawal.confirm.RemoteWithdrawalConfirmParam;
import org.docksidestage.remote.fortress.withdrawal.done.RemoteWithdrawalDoneParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of withdrawal.
 * @author FreeGen
 */
public abstract class BsRemoteFortressWithdrawalBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressWithdrawalBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /withdrawal/. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/
     * httpMethod: GET
     * </pre>
     */
    public void request() {
        doRequest(rule -> {});
    }

    /**
     * Request remote call to /withdrawal/. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequest(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/withdrawal/", noMoreUrl(), noQuery(), rule -> {
            ruleOf(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /withdrawal/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOf(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /withdrawal/confirm. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/confirm
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWithdrawalConfirmParam. (NotNull)
     */
    public void requestConfirm(Consumer<RemoteWithdrawalConfirmParam> paramLambda) {
        doRequestConfirm(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /withdrawal/confirm. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/confirm
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWithdrawalConfirmParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestConfirm(Consumer<RemoteWithdrawalConfirmParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWithdrawalConfirmParam param = new RemoteWithdrawalConfirmParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/withdrawal/confirm", noMoreUrl(), query(param), rule -> {
            ruleOfConfirm(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /withdrawal/confirm.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfConfirm(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /withdrawal/done. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/done
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWithdrawalDoneParam. (NotNull)
     */
    public void requestDone(Consumer<RemoteWithdrawalDoneParam> paramLambda) {
        doRequestDone(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /withdrawal/done. (auto-generated method)<br>
     * <pre>
     * url: /withdrawal/done
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWithdrawalDoneParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestDone(Consumer<RemoteWithdrawalDoneParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWithdrawalDoneParam param = new RemoteWithdrawalDoneParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/withdrawal/done", noMoreUrl(), query(param), rule -> {
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
