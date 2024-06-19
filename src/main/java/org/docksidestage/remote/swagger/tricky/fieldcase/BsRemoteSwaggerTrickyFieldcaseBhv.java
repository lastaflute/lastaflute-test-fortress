/*
 * Copyright 2015-2024 the original author or authors.
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
package org.docksidestage.remote.swagger.tricky.fieldcase;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.swagger.tricky.AbstractRemoteSwaggerTrickyBhv;
import org.docksidestage.remote.swagger.tricky.fieldcase.noconv.RemoteFieldcaseNoconvParam;
import org.docksidestage.remote.swagger.tricky.fieldcase.noconv.RemoteFieldcaseNoconvReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of fieldcase.
 * @author FreeGen
 */
public abstract class BsRemoteSwaggerTrickyFieldcaseBhv extends AbstractRemoteSwaggerTrickyBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteSwaggerTrickyFieldcaseBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /fieldcase/noconv/. (auto-generated method)<br>
     * <pre>
     * url: /fieldcase/noconv/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteFieldcaseNoconvParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteFieldcaseNoconvReturn requestNoconv(Consumer<RemoteFieldcaseNoconvParam> paramLambda) {
        return doRequestNoconv(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /fieldcase/noconv/. (auto-generated method)<br>
     * <pre>
     * url: /fieldcase/noconv/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteFieldcaseNoconvParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteFieldcaseNoconvReturn doRequestNoconv(Consumer<RemoteFieldcaseNoconvParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteFieldcaseNoconvParam param = new RemoteFieldcaseNoconvParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteFieldcaseNoconvReturn.class, "/fieldcase/noconv/", noMoreUrl(), query(param), rule -> {
            ruleOfNoconv(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /fieldcase/noconv/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfNoconv(FlutyRemoteApiRule rule) {
    }
}
