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

import org.lastaflute.di.helper.misc.ParameterizedRef;
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
     * Reason.<br>
     * <pre>
     * url: /withdrawal/reason
     * httpMethod: POST
     * </pre>
     * @return java.util.List<Object>. (NotNull)
     */
    protected java.util.List<Object> requestReason() {
        return doRequestPost(new ParameterizedRef<java.util.List<Object>>() {
        }.getType(), "/withdrawal/reason", noMoreUrl(), null, rule -> {});
    }

    /**
     * Done.<br>
     * <pre>
     * url: /withdrawal/done
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteWithdrawalDoneParam. (NotNull)
     * @return Integer. (NotNull)
     */
    protected Integer requestDone(Consumer<RemoteWithdrawalDoneParam> paramLamda) {
        RemoteWithdrawalDoneParam param = new RemoteWithdrawalDoneParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<Integer>() {
        }.getType(), "/withdrawal/done", noMoreUrl(), param, rule -> {});
    }
}
