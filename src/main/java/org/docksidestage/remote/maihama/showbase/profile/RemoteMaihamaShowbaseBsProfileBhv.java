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
package org.docksidestage.remote.maihama.showbase.profile;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.profile.RemoteProfileReturn;

/**
 * RemoteMaihamaShowbaseBsProfileBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsProfileBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsProfileBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to  Profile. (auto-generated method)<br>
     * <pre>
     * url: /profile/
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public RemoteProfileReturn requestProfile() {
        return requestProfile(rule -> {});
    }

    /**
     * Set up method-level rule of Profile. (auto-generated method)<br>
     * <pre>
     * url: /profile/
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return return object. (NotNull)
     */
    protected RemoteProfileReturn requestProfile(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteProfileReturn.class, "/profile/", noMoreUrl(), null, rule -> {
            ruleOfProfile(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of Profile.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfProfile(FlutyRemoteApiRule rule) {
    }
}
