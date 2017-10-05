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

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.di.helper.misc.ParameterizedRef;
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
     * Swagger.<br>
     * <pre>
     * url: /swagger/
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public Object requestSwagger() {
        return doRequestPost(new ParameterizedRef<Object>() {
        }.getType(), "/swagger/", noMoreUrl(), null, rule -> ruleOfSwagger(rule));
    }

    /**
     * Rule of Swagger.<br>
     * <pre>
     * url: /swagger/
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfSwagger(FlutyRemoteApiRule rule) {
    }

    /**
     * Json.<br>
     * <pre>
     * url: /swagger/json
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public Object requestJson() {
        return doRequestPost(new ParameterizedRef<Object>() {
        }.getType(), "/swagger/json", noMoreUrl(), null, rule -> ruleOfJson(rule));
    }

    /**
     * Rule of Json.<br>
     * <pre>
     * url: /swagger/json
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfJson(FlutyRemoteApiRule rule) {
    }
}
