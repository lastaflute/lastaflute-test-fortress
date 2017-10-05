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
package org.docksidestage.remote.maihama.showbase.wx;

import java.util.function.Consumer;

import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.wx.faicli.RemoteWxFaicliParam;

/**
 * RemoteMaihamaShowbaseBsWxBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsWxBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsWxBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Faicli.<br>
     * <pre>
     * url: /wx/faicli/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteWxFaicliParam. (NotNull)
     * @return Object. (NotNull)
     */
    protected Object requestFaicli(Consumer<RemoteWxFaicliParam> paramLamda) {
        RemoteWxFaicliParam param = new RemoteWxFaicliParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<Object>() {
        }.getType(), "/wx/faicli/", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantRemoteMappingPolicy()));
        });
    }

    /**
     * FaicliUnknown.<br>
     * <pre>
     * url: /wx/faicli/unknown
     * httpMethod: POST
     * </pre>
     */
    protected void requestFaicliUnknown() {
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/wx/faicli/unknown", noMoreUrl(), null, rule -> {});
    }

    /**
     * FaicliEntity.<br>
     * <pre>
     * url: /wx/faicli/entity/{account}
     * httpMethod: POST
     * </pre>
     * @param account account. (NotNull)
     */
    protected void requestFaicliEntity(String account) {
        doRequestPost(new ParameterizedRef<Void>() {
        }.getType(), "/wx/faicli/entity/{account}", moreUrl(account), null, rule -> {});
    }
}
