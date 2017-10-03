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
     * /member/info.
     */
    protected RemoteMemberInfoReturn info() {
        return doRequestPost(new ParameterizedRef<RemoteMemberInfoReturn>() {
        }.getType(), "/member/info", noMoreUrl(), null, op -> {});
    }

    /**
     * /member/status.
     */
    protected void status() {
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/member/status", noMoreUrl(), null, op -> {});
    }

    /**
     * /member/add/register.
     */
    protected RemoteMemberAddRegisterReturn addRegister(Consumer<RemoteMemberAddRegisterParam> paramLamda) {
        RemoteMemberAddRegisterParam param = new RemoteMemberAddRegisterParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemoteMemberAddRegisterReturn>() {
        }.getType(), "/member/add/register", noMoreUrl(), param, op -> {});
    }

    /**
     * /member/edit/{memberId}.
     */
    protected RemoteMemberEditReturn edit() {
        return doRequestPost(new ParameterizedRef<RemoteMemberEditReturn>() {
        }.getType(), "/member/edit/{memberId}", noMoreUrl(), null, op -> {});
    }

    /**
     * /member/edit/update.
     */
    protected void editUpdate(Consumer<RemoteMemberEditUpdateParam> paramLamda) {
        RemoteMemberEditUpdateParam param = new RemoteMemberEditUpdateParam();
        paramLamda.accept(param);
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/member/edit/update", noMoreUrl(), param, op -> {});
    }

    /**
     * /member/list/{pageNumber}.
     */
    protected RemoteMemberListReturn list(Consumer<RemoteMemberListParam> paramLamda) {
        RemoteMemberListParam param = new RemoteMemberListParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemoteMemberListReturn>() {
        }.getType(), "/member/list/{pageNumber}", noMoreUrl(), param, op -> {});
    }

    /**
     * /member/purchase/list/{memberId}/{pageNumber}.
     */
    protected RemoteMemberPurchaseListReturn purchaseList() {
        return doRequestPost(new ParameterizedRef<RemoteMemberPurchaseListReturn>() {
        }.getType(), "/member/purchase/list/{memberId}/{pageNumber}", noMoreUrl(), null, op -> {});
    }

    /**
     * /member/purchase/list/delete/{purchaseId}.
     */
    protected void purchaseListDelete() {
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/member/purchase/list/delete/{purchaseId}", noMoreUrl(), null, op -> {});
    }
}
