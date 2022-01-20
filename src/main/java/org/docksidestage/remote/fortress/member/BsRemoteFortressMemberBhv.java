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
package org.docksidestage.remote.fortress.member;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.member.add.register.RemoteMemberAddRegisterParam;
import org.docksidestage.remote.fortress.member.edit.update.RemoteMemberEditUpdateParam;
import org.docksidestage.remote.fortress.member.edit.withdrawal.RemoteMemberEditWithdrawalParam;
import org.docksidestage.remote.fortress.member.list.RemoteMemberListPagenumberParam;
import org.docksidestage.remote.fortress.member.list.RemoteMemberListParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of member.
 * @author FreeGen
 */
public abstract class BsRemoteFortressMemberBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressMemberBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /member/add/. (auto-generated method)<br>
     * <pre>
     * url: /member/add/
     * httpMethod: GET
     * </pre>
     */
    public void requestAdd() {
        doRequestAdd(rule -> {});
    }

    /**
     * Request remote call to /member/add/. (auto-generated method)<br>
     * <pre>
     * url: /member/add/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAdd(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/member/add/", noMoreUrl(), noQuery(), rule -> {
            ruleOfAdd(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/add/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAdd(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/add/register. (auto-generated method)<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberAddRegisterParam. (NotNull)
     */
    public void requestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLambda) {
        doRequestAddRegister(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/add/register. (auto-generated method)<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberAddRegisterParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberAddRegisterParam param = new RemoteMemberAddRegisterParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/member/add/register", noMoreUrl(), query(param), rule -> {
            ruleOfAddRegister(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/add/register.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAddRegister(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/edit/{memberId}. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     */
    public void requestEdit(Integer memberId) {
        doRequestEdit(memberId, rule -> {});
    }

    /**
     * Request remote call to /member/edit/{memberId}. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestEdit(Integer memberId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/member/edit/{memberId}", moreUrl(memberId), noQuery(), rule -> {
            ruleOfEditMemberId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/edit/{memberId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfEditMemberId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/edit/update. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/update
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberEditUpdateParam. (NotNull)
     */
    public void requestEditUpdate(Consumer<RemoteMemberEditUpdateParam> paramLambda) {
        doRequestEditUpdate(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/edit/update. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/update
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberEditUpdateParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestEditUpdate(Consumer<RemoteMemberEditUpdateParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberEditUpdateParam param = new RemoteMemberEditUpdateParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/member/edit/update", noMoreUrl(), query(param), rule -> {
            ruleOfEditUpdate(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/edit/update.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfEditUpdate(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/edit/withdrawal. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/withdrawal
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberEditWithdrawalParam. (NotNull)
     */
    public void requestEditWithdrawal(Consumer<RemoteMemberEditWithdrawalParam> paramLambda) {
        doRequestEditWithdrawal(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/edit/withdrawal. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/withdrawal
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberEditWithdrawalParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestEditWithdrawal(Consumer<RemoteMemberEditWithdrawalParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberEditWithdrawalParam param = new RemoteMemberEditWithdrawalParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/member/edit/withdrawal", noMoreUrl(), query(param), rule -> {
            ruleOfEditWithdrawal(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/edit/withdrawal.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfEditWithdrawal(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/list. (auto-generated method)<br>
     * <pre>
     * url: /member/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberListParam. (NotNull)
     */
    public void requestList(Consumer<RemoteMemberListParam> paramLambda) {
        doRequestList(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/list. (auto-generated method)<br>
     * <pre>
     * url: /member/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMemberListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestList(Consumer<RemoteMemberListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberListParam param = new RemoteMemberListParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/member/list", noMoreUrl(), query(param), rule -> {
            ruleOfList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteMemberListPagenumberParam. (NotNull)
     */
    public void requestList(Integer pageNumber, Consumer<RemoteMemberListPagenumberParam> paramLambda) {
        doRequestList(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteMemberListPagenumberParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestList(Integer pageNumber, Consumer<RemoteMemberListPagenumberParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberListPagenumberParam param = new RemoteMemberListPagenumberParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/member/list/{pageNumber}", moreUrl(pageNumber), query(param), rule -> {
            ruleOfListPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/list/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfListPageNumber(FlutyRemoteApiRule rule) {
    }
}
