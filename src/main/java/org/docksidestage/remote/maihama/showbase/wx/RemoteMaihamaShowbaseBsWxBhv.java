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

import org.dbflute.remoteapi.FlutyRemoteApiRule;
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
     * Request remote call to /wx/faicli/. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteWxFaicliParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public Object requestFaicli(Consumer<RemoteWxFaicliParam> paramLamda) {
        return requestFaicli(paramLamda, rule -> {});
    }

    /**
     * Request remote call to /wx/faicli/. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteWxFaicliParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected Object requestFaicli(Consumer<RemoteWxFaicliParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxFaicliParam param = new RemoteWxFaicliParam();
        paramLamda.accept(param);
        return doRequestPost(Object.class, "/wx/faicli/", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfFaicli(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/faicli/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFaicli(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/faicli/unknown. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/unknown
     * httpMethod: POST
     * </pre>
     */
    public void requestFaicliUnknown() {
        requestFaicliUnknown(rule -> {});
    }

    /**
     * Request remote call to /wx/faicli/unknown. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/unknown
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestFaicliUnknown(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestPost(void.class, "/wx/faicli/unknown", noMoreUrl(), null, rule -> {
            ruleOfFaicliUnknown(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/faicli/unknown.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFaicliUnknown(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/faicli/entity/{account}. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/entity/{account}
     * httpMethod: POST
     * </pre>
     * @param account The value of path variable for account. (NotNull)
     */
    public void requestFaicliEntity(String account) {
        requestFaicliEntity(account, rule -> {});
    }

    /**
     * Request remote call to /wx/faicli/entity/{account}. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/entity/{account}
     * httpMethod: POST
     * </pre>
     * @param account The value of path variable for account. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestFaicliEntity(String account, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestPost(void.class, "/wx/faicli/entity/{account}", moreUrl(account), null, rule -> {
            ruleOfFaicliEntityAccount(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/faicli/entity/{account}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFaicliEntityAccount(FlutyRemoteApiRule rule) {
    }
}
