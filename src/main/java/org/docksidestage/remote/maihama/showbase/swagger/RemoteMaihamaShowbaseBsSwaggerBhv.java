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
package org.docksidestage.remote.maihama.showbase.swagger;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;

/**
 * RemoteMaihamaShowbaseBsSwaggerBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsSwaggerBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsSwaggerBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to  Swagger. (auto-generated method)<br>
     * <pre>
     * url: /swagger/
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public Object requestSwagger() {
        return requestSwagger(rule -> {});
    }

    /**
     * Set up method-level rule of Swagger. (auto-generated method)<br>
     * <pre>
     * url: /swagger/
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return return object. (NotNull)
     */
    protected Object requestSwagger(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(Object.class, "/swagger/", noMoreUrl(), null, rule -> {
            ruleOfSwagger(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Swagger.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfSwagger(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to  Json. (auto-generated method)<br>
     * <pre>
     * url: /swagger/json
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public Object requestJson() {
        return requestJson(rule -> {});
    }

    /**
     * Set up method-level rule of Json. (auto-generated method)<br>
     * <pre>
     * url: /swagger/json
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return return object. (NotNull)
     */
    protected Object requestJson(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(Object.class, "/swagger/json", noMoreUrl(), null, rule -> {
            ruleOfJson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Json.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfJson(FlutyRemoteApiRule rule) {
    }
}
