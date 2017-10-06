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
package org.docksidestage.remote.maihama.showbase.signin;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.signin.RemoteSigninParam;
import org.docksidestage.remote.maihama.showbase.signin.RemoteSigninReturn;

/**
 * RemoteMaihamaShowbaseBsSigninBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsSigninBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsSigninBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to  Signin. (auto-generated method)<br>
     * <pre>
     * url: /signin/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteSigninParam. (NotNull)
     * @return return object. (NotNull)
     */
    public RemoteSigninReturn requestSignin(Consumer<RemoteSigninParam> paramLamda) {
        return requestSignin(paramLamda, rule -> {});
    }

    /**
     * Set up method-level rule of Signin. (auto-generated method)<br>
     * <pre>
     * url: /signin/
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteSigninParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return return object. (NotNull)
     */
    protected RemoteSigninReturn requestSignin(Consumer<RemoteSigninParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteSigninParam param = new RemoteSigninParam();
        paramLamda.accept(param);
        return doRequestPost(RemoteSigninReturn.class, "/signin/", noMoreUrl(), param, rule -> {
            ruleOfSignin(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Signin.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfSignin(FlutyRemoteApiRule rule) {
    }
}
