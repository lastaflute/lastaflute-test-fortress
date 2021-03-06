/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.remote.maihama.showbase.swagger;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of swagger.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseSwaggerBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseSwaggerBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /swagger/. (auto-generated method)<br>
     * <pre>
     * url: /swagger/
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> request() {
        return doRequest(rule -> {});
    }

    /**
     * Request remote call to /swagger/. (auto-generated method)<br>
     * <pre>
     * url: /swagger/
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequest(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/swagger/", noMoreUrl(), noRequestBody(), rule -> {
            ruleOf(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /swagger/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOf(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /swagger/json. (auto-generated method)<br>
     * <pre>
     * url: /swagger/json
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestJson() {
        return doRequestJson(rule -> {});
    }

    /**
     * Request remote call to /swagger/json. (auto-generated method)<br>
     * <pre>
     * url: /swagger/json
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestJson(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/swagger/json", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfJson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /swagger/json.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfJson(FlutyRemoteApiRule rule) {
    }
}
