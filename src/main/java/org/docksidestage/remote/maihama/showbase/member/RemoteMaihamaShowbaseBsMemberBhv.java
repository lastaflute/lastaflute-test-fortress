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
     * @return RemoteMemberInfoReturn
     */
    protected RemoteMemberInfoReturn requestInfo() {
        return doRequestPost(new ParameterizedRef<RemoteMemberInfoReturn>() {
        }.getType(), "/member/info", noMoreUrl(), null, op -> {});
    }

    /**
     * Status.<br>
     * <pre>
     * url: /member/status
     * httpMethod: POST
     * </pre>
     * @return java.util.List<java.util.Map<String, Object>>
     */
    protected java.util.List<java.util.Map<String, Object>> requestStatus() {
        return doRequestPost(new ParameterizedRef<java.util.List<java.util.Map<String, Object>>>() {
        }.getType(), "/member/status", noMoreUrl(), null, op -> {});
    }

    /**
     * AddRegister.<br>
     * <pre>
     * url: /member/add/register
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberAddRegisterParam
     * @return RemoteMemberAddRegisterReturn
     */
    protected RemoteMemberAddRegisterReturn requestAddRegister(Consumer<RemoteMemberAddRegisterParam> paramLamda) {
        RemoteMemberAddRegisterParam param = new RemoteMemberAddRegisterParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemoteMemberAddRegisterReturn>() {
        }.getType(), "/member/add/register", noMoreUrl(), param, op -> {});
    }

    /**
     * Edit.<br>
     * <pre>
     * url: /member/edit/{memberId}
     * httpMethod: POST
     * </pre>
     * @param memberId memberId
     * @return RemoteMemberEditReturn
     */
    protected RemoteMemberEditReturn requestEdit(Integer memberId) {
        return doRequestPost(new ParameterizedRef<RemoteMemberEditReturn>() {
        }.getType(), "/member/edit/{memberId}", moreUrl(memberId), null, op -> {});
    }

    /**
     * EditUpdate.<br>
     * <pre>
     * url: /member/edit/update
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteMemberEditUpdateParam
     */
    protected void requestEditUpdate(Consumer<RemoteMemberEditUpdateParam> paramLamda) {
        RemoteMemberEditUpdateParam param = new RemoteMemberEditUpdateParam();
        paramLamda.accept(param);
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/member/edit/update", noMoreUrl(), param, op -> {});
    }

    /**
     * List.<br>
     * <pre>
     * url: /member/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber pageNumber
     * @param paramLamda The callback for RemoteMemberListParam
     * @return RemoteMemberListReturn
     */
    protected RemoteMemberListReturn requestList(Integer pageNumber, Consumer<RemoteMemberListParam> paramLamda) {
        RemoteMemberListParam param = new RemoteMemberListParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemoteMemberListReturn>() {
        }.getType(), "/member/list/{pageNumber}", moreUrl(pageNumber), param, op -> {});
    }

    /**
     * PurchaseList.<br>
     * <pre>
     * url: /member/purchase/list/{memberId}/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param memberId memberId
     * @param pageNumber pageNumber
     * @return RemoteMemberPurchaseListReturn
     */
    protected RemoteMemberPurchaseListReturn requestPurchaseList(Integer memberId, Integer pageNumber) {
        return doRequestPost(new ParameterizedRef<RemoteMemberPurchaseListReturn>() {
        }.getType(), "/member/purchase/list/{memberId}/{pageNumber}", moreUrl(memberId, pageNumber), null, op -> {});
    }

    /**
     * PurchaseListDelete.<br>
     * <pre>
     * url: /member/purchase/list/delete/{purchaseId}
     * httpMethod: POST
     * </pre>
     * @param purchaseId purchaseId
     */
    protected void requestPurchaseListDelete(Long purchaseId) {
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/member/purchase/list/delete/{purchaseId}", moreUrl(purchaseId), null, op -> {});
    }
}
