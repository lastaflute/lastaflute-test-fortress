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
package org.docksidestage.remote.maihama.showbase.member;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.member.info.RemoteMemberInfoReturn;
import org.docksidestage.remote.maihama.showbase.member.add.register.RemoteMemberAddRegisterParam;
import org.docksidestage.remote.maihama.showbase.member.add.register.RemoteMemberAddRegisterReturn;
import org.docksidestage.remote.maihama.showbase.member.edit.RemoteMemberEditReturn;
import org.docksidestage.remote.maihama.showbase.member.edit.update.RemoteMemberEditUpdateParam;
import org.docksidestage.remote.maihama.showbase.member.list.RemoteMemberListParam;
import org.docksidestage.remote.maihama.showbase.member.list.RemoteMemberListReturn;
import org.docksidestage.remote.maihama.showbase.member.purchase.list.RemoteMemberPurchaseListReturn;

/**
 * RemoteMaihamaShowbaseBsMemberBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsMemberBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsMemberBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to  Info. (auto-generated method)<br>
     * <pre>
     * url: /member/info
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberInfoReturn requestInfo() {
        return requestInfo(rule -> {});
    }

    /**
     * Request remote call to  Info. (auto-generated method)<br>
     * <pre>
     * url: /member/info
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberInfoReturn requestInfo(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteMemberInfoReturn.class, "/member/info", noMoreUrl(), null, rule -> {
            ruleOfInfo(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Info.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfInfo(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  Status. (auto-generated method)<br>
     * <pre>
     * url: /member/status
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.List<Object> requestStatus() {
        return requestStatus(rule -> {});
    }

    /**
     * Request remote call to  Status. (auto-generated method)<br>
     * <pre>
     * url: /member/status
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.List<Object> requestStatus(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.List<Object>>() {
        }.getType(), "/member/status", noMoreUrl(), null, rule -> {
            ruleOfStatus(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Status.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfStatus(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  AddRegister. (auto-generated method)<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberAddRegisterParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberAddRegisterReturn requestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLamda) {
        return requestAddRegister(paramLamda, rule -> {});
    }

    /**
     * Request remote call to  AddRegister. (auto-generated method)<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberAddRegisterParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberAddRegisterReturn requestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberAddRegisterParam param = new RemoteMemberAddRegisterParam();
        paramLamda.accept(param);
        return doRequestPost(RemoteMemberAddRegisterReturn.class, "/member/add/register", noMoreUrl(), param, rule -> {
            ruleOfAddRegister(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of AddRegister.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAddRegister(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  Edit. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: POST
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberEditReturn requestEdit(Integer memberId) {
        return requestEdit(memberId, rule -> {});
    }

    /**
     * Request remote call to  Edit. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: POST
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberEditReturn requestEdit(Integer memberId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteMemberEditReturn.class, "/member/edit/{memberId}", moreUrl(memberId), null, rule -> {
            ruleOfEditMemberId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Edit.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfEditMemberId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  EditUpdate. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/update
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberEditUpdateParam. (NotNull)
     */
    public void requestEditUpdate(Consumer<RemoteMemberEditUpdateParam> paramLamda) {
        requestEditUpdate(paramLamda, rule -> {});
    }

    /**
     * Request remote call to  EditUpdate. (auto-generated method)<br>
     * <pre>
     * url: /member/edit/update
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberEditUpdateParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestEditUpdate(Consumer<RemoteMemberEditUpdateParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberEditUpdateParam param = new RemoteMemberEditUpdateParam();
        paramLamda.accept(param);
        doRequestPost(void.class, "/member/edit/update", noMoreUrl(), param, rule -> {
            ruleOfEditUpdate(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of EditUpdate.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfEditUpdate(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  List. (auto-generated method)<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLamda The callback for RemoteMemberListParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberListReturn requestList(Integer pageNumber, Consumer<RemoteMemberListParam> paramLamda) {
        return requestList(pageNumber, paramLamda, rule -> {});
    }

    /**
     * Request remote call to  List. (auto-generated method)<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLamda The callback for RemoteMemberListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberListReturn requestList(Integer pageNumber, Consumer<RemoteMemberListParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMemberListParam param = new RemoteMemberListParam();
        paramLamda.accept(param);
        return doRequestPost(RemoteMemberListReturn.class, "/member/list/{pageNumber}", moreUrl(pageNumber), param, rule -> {
            ruleOfListPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of List.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfListPageNumber(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  PurchaseList. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/{memberId}/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteMemberPurchaseListReturn requestPurchaseList(Integer memberId, Integer pageNumber) {
        return requestPurchaseList(memberId, pageNumber, rule -> {});
    }

    /**
     * Request remote call to  PurchaseList. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/{memberId}/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteMemberPurchaseListReturn requestPurchaseList(Integer memberId, Integer pageNumber, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteMemberPurchaseListReturn.class, "/member/purchase/list/{memberId}/{pageNumber}", moreUrl(memberId, pageNumber), null, rule -> {
            ruleOfPurchaseListMemberIdPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of PurchaseList.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPurchaseListMemberIdPageNumber(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  PurchaseListDelete. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/delete/{purchaseId}
     * httpMethod: POST
     * </pre>
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     */
    public void requestPurchaseListDelete(Long purchaseId) {
        requestPurchaseListDelete(purchaseId, rule -> {});
    }

    /**
     * Request remote call to  PurchaseListDelete. (auto-generated method)<br>
     * <pre>
     * url: /member/purchase/list/delete/{purchaseId}
     * httpMethod: POST
     * </pre>
     * @param purchaseId The value of path variable for purchaseId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestPurchaseListDelete(Long purchaseId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestPost(void.class, "/member/purchase/list/delete/{purchaseId}", moreUrl(purchaseId), null, rule -> {
            ruleOfPurchaseListDeletePurchaseId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of PurchaseListDelete.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfPurchaseListDeletePurchaseId(FlutyRemoteApiRule rule) {
    }
}
