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
     * @return java.util.Map<String, Object>
     */
    protected java.util.Map<String, Object> requestSwagger() {
        return doRequestPost(new ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/swagger/", noMoreUrl(), null, op -> {});
    }

    /**
     * Json.<br>
     * <pre>
     * url: /swagger/json
     * httpMethod: POST
     * </pre>
     * @return java.util.Map<String, Object>
     */
    protected java.util.Map<String, Object> requestJson() {
        return doRequestPost(new ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/swagger/json", noMoreUrl(), null, op -> {});
    }
}
