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
import org.lastaflute.di.helper.misc.ParameterizedRef;
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
     * Info.<br>
     * <pre>
     * url: /member/info
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public RemoteMemberInfoReturn requestInfo() {
        return doRequestPost(new ParameterizedRef<RemoteMemberInfoReturn>() {
        }.getType(), "/member/info", noMoreUrl(), null, rule -> ruleOfInfo(rule));
    }

    /**
     * Rule of Info.<br>
     * <pre>
     * url: /member/info
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfInfo(FlutyRemoteApiRule rule) {
    }

    /**
     * Status.<br>
     * <pre>
     * url: /member/status
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public java.util.List<Object> requestStatus() {
        return doRequestPost(new ParameterizedRef<java.util.List<Object>>() {
        }.getType(), "/member/status", noMoreUrl(), null, rule -> ruleOfStatus(rule));
    }

    /**
     * Rule of Status.<br>
     * <pre>
     * url: /member/status
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfStatus(FlutyRemoteApiRule rule) {
    }

    /**
     * AddRegister.<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberAddRegisterParam. (NotNull)
     * @return return object. (NotNull)
     */
    public RemoteMemberAddRegisterReturn requestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLamda) {
        RemoteMemberAddRegisterParam param = new RemoteMemberAddRegisterParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemoteMemberAddRegisterReturn>() {
        }.getType(), "/member/add/register", noMoreUrl(), param, rule -> ruleOfAddRegister(rule));
    }

    /**
     * Rule of AddRegister.<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfAddRegister(FlutyRemoteApiRule rule) {
    }

    /**
     * Edit.<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: POST
     * </pre>
     * @param memberId memberId. (NotNull)
     * @return return object. (NotNull)
     */
    public RemoteMemberEditReturn requestEdit(Integer memberId) {
        return doRequestPost(new ParameterizedRef<RemoteMemberEditReturn>() {
        }.getType(), "/member/edit/{memberId}", moreUrl(memberId), null, rule -> ruleOfEdit(rule));
    }

    /**
     * Rule of Edit.<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfEdit(FlutyRemoteApiRule rule) {
    }

    /**
     * EditUpdate.<br>
     * <pre>
     * url: /member/edit/update
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberEditUpdateParam. (NotNull)
     */
    public void requestEditUpdate(Consumer<RemoteMemberEditUpdateParam> paramLamda) {
        RemoteMemberEditUpdateParam param = new RemoteMemberEditUpdateParam();
        paramLamda.accept(param);
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/member/edit/update", noMoreUrl(), param, rule -> ruleOfEditUpdate(rule));
    }

    /**
     * Rule of EditUpdate.<br>
     * <pre>
     * url: /member/edit/update
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfEditUpdate(FlutyRemoteApiRule rule) {
    }

    /**
     * List.<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber pageNumber. (NotNull)
     * @param paramLamda The callback for RemoteMemberListParam. (NotNull)
     * @return return object. (NotNull)
     */
    public RemoteMemberListReturn requestList(Integer pageNumber, Consumer<RemoteMemberListParam> paramLamda) {
        RemoteMemberListParam param = new RemoteMemberListParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemoteMemberListReturn>() {
        }.getType(), "/member/list/{pageNumber}", moreUrl(pageNumber), param, rule -> ruleOfList(rule));
    }

    /**
     * Rule of List.<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfList(FlutyRemoteApiRule rule) {
    }

    /**
     * PurchaseList.<br>
     * <pre>
     * url: /member/purchase/list/{memberId}/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param memberId memberId. (NotNull)
     * @param pageNumber pageNumber. (NotNull)
     * @return return object. (NotNull)
     */
    public RemoteMemberPurchaseListReturn requestPurchaseList(Integer memberId, Integer pageNumber) {
        return doRequestPost(new ParameterizedRef<RemoteMemberPurchaseListReturn>() {
        }.getType(), "/member/purchase/list/{memberId}/{pageNumber}", moreUrl(memberId, pageNumber), null, rule -> ruleOfPurchaseList(rule));
    }

    /**
     * Rule of PurchaseList.<br>
     * <pre>
     * url: /member/purchase/list/{memberId}/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfPurchaseList(FlutyRemoteApiRule rule) {
    }

    /**
     * PurchaseListDelete.<br>
     * <pre>
     * url: /member/purchase/list/delete/{purchaseId}
     * httpMethod: POST
     * </pre>
     * @param purchaseId purchaseId. (NotNull)
     */
    public void requestPurchaseListDelete(Long purchaseId) {
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/member/purchase/list/delete/{purchaseId}", moreUrl(purchaseId), null, rule -> ruleOfPurchaseListDelete(rule));
    }

    /**
     * Rule of PurchaseListDelete.<br>
     * <pre>
     * url: /member/purchase/list/delete/{purchaseId}
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfPurchaseListDelete(FlutyRemoteApiRule rule) {
    }
}
