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
package org.docksidestage.remote.maihama.showbase.member;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.member.add.register.RemoteMemberAddRegisterParam;
import org.docksidestage.remote.maihama.showbase.member.add.register.RemoteMemberAddRegisterReturn;
import org.docksidestage.remote.maihama.showbase.member.edit.RemoteMemberEditReturn;
import org.docksidestage.remote.maihama.showbase.member.edit.update.RemoteMemberEditUpdateParam;
import org.docksidestage.remote.maihama.showbase.member.info.RemoteMemberInfoReturn;
import org.docksidestage.remote.maihama.showbase.member.list.RemoteMemberListParam;
import org.docksidestage.remote.maihama.showbase.member.list.RemoteMemberListReturn;
import org.docksidestage.remote.maihama.showbase.member.purchase.list.RemoteMemberPurchaseListReturn;
import org.docksidestage.remote.maihama.showbase.member.status.RemoteMemberStatusReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of member.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseMemberBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseMemberBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /member/info. (auto-generated method)<br>
     * <pre>
     * url: /member/info
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberInfoReturn requestInfo() {
        return doRequestInfo(rule -> {});
    }

    /**
     * Request remote call to /member/info. (auto-generated method)<br>
     * <pre>
     * url: /member/info
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberInfoReturn doRequestInfo(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteMemberInfoReturn.class, "/member/info", noMoreUrl(), noQuery(), rule -> {
            ruleOfInfo(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/info.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfInfo(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/status. (auto-generated method)<br>
     * <pre>
     * url: /member/status
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteMemberStatusReturn> requestStatus() {
        return doRequestStatus(rule -> {});
    }

    /**
     * Request remote call to /member/status. (auto-generated method)<br>
     * <pre>
     * url: /member/status
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteMemberStatusReturn> doRequestStatus(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteMemberStatusReturn>>() {
        }.getType(), "/member/status", noMoreUrl(), noQuery(), rule -> {
            ruleOfStatus(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/status.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfStatus(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/add/register. (auto-generated method)<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteMemberAddRegisterParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberAddRegisterReturn requestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLambda) {
        return doRequestAddRegister(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/add/register. (auto-generated method)<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteMemberAddRegisterParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberAddRegisterReturn doRequestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberAddRegisterParam param = new RemoteMemberAddRegisterParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteMemberAddRegisterReturn.class, "/member/add/register", noMoreUrl(), param, rule -> {
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
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberEditReturn requestEdit(Integer memberId) {
        return doRequestEdit(memberId, rule -> {});
    }

    /**
     * Request remote call to /member/edit/{memberId}. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberEditReturn doRequestEdit(Integer memberId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteMemberEditReturn.class, "/member/edit/{memberId}", moreUrl(memberId), noQuery(), rule -> {
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
     * httpMethod: POST
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
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteMemberEditUpdateParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestEditUpdate(Consumer<RemoteMemberEditUpdateParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberEditUpdateParam param = new RemoteMemberEditUpdateParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/member/edit/update", noMoreUrl(), param, rule -> {
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
     * Request remote call to /member/list. (auto-generated method)<br>
     * <pre>
     * url: /member/list
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteMemberListParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberListReturn requestList(Consumer<RemoteMemberListParam> paramLambda) {
        return doRequestList(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/list. (auto-generated method)<br>
     * <pre>
     * url: /member/list
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteMemberListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberListReturn doRequestList(Consumer<RemoteMemberListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberListParam param = new RemoteMemberListParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteMemberListReturn.class, "/member/list", noMoreUrl(), param, rule -> {
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
     * httpMethod: POST
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteMemberListParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberListReturn requestList(Integer pageNumber, Consumer<RemoteMemberListParam> paramLambda) {
        return doRequestList(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /member/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteMemberListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberListReturn doRequestList(Integer pageNumber, Consumer<RemoteMemberListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberListParam param = new RemoteMemberListParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteMemberListReturn.class, "/member/list/{pageNumber}", moreUrl(pageNumber), param, rule -> {
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

    /**
     * Request remote call to /member/purchase/list/{memberId}. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/{memberId}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberPurchaseListReturn requestPurchaseList(Integer memberId) {
        return doRequestPurchaseList(memberId, rule -> {});
    }

    /**
     * Request remote call to /member/purchase/list/{memberId}. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/{memberId}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberPurchaseListReturn doRequestPurchaseList(Integer memberId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteMemberPurchaseListReturn.class, "/member/purchase/list/{memberId}", moreUrl(memberId), noQuery(), rule -> {
            ruleOfPurchaseListMemberId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/purchase/list/{memberId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPurchaseListMemberId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/purchase/list/{memberId}/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/{memberId}/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberPurchaseListReturn requestPurchaseList(Integer memberId, Integer pageNumber) {
        return doRequestPurchaseList(memberId, pageNumber, rule -> {});
    }

    /**
     * Request remote call to /member/purchase/list/{memberId}/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/{memberId}/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberPurchaseListReturn doRequestPurchaseList(Integer memberId, Integer pageNumber, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteMemberPurchaseListReturn.class, "/member/purchase/list/{memberId}/{pageNumber}", moreUrl(memberId, pageNumber), noQuery(), rule -> {
            ruleOfPurchaseListMemberIdPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/purchase/list/{memberId}/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPurchaseListMemberIdPageNumber(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /member/purchase/list/delete/{purchaseId}. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/delete/{purchaseId}
     * httpMethod: GET
     * </pre>
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     */
    public void requestPurchaseListDelete(Long purchaseId) {
        doRequestPurchaseListDelete(purchaseId, rule -> {});
    }

    /**
     * Request remote call to /member/purchase/list/delete/{purchaseId}. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/delete/{purchaseId}
     * httpMethod: GET
     * </pre>
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestPurchaseListDelete(Long purchaseId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/member/purchase/list/delete/{purchaseId}", moreUrl(purchaseId), noQuery(), rule -> {
            ruleOfPurchaseListDeletePurchaseId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /member/purchase/list/delete/{purchaseId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPurchaseListDeletePurchaseId(FlutyRemoteApiRule rule) {
    }
}
