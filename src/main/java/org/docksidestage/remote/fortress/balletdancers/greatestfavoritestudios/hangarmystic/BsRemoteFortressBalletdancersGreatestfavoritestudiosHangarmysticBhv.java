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
package org.docksidestage.remote.fortress.balletdancers.greatestfavoritestudios.hangarmystic;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.balletdancers.greatestfavoritestudios.hangarmystic.index.RemoteBalletdancersGreatestfavoritestudiosHangarmysticParam;
import org.docksidestage.remote.fortress.balletdancers.greatestfavoritestudios.hangarmystic.index.RemoteBalletdancersGreatestfavoritestudiosHangarmysticReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of balletdancers.greatestfavoritestudios.hangarmystic.
 * @author FreeGen
 */
public abstract class BsRemoteFortressBalletdancersGreatestfavoritestudiosHangarmysticBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressBalletdancersGreatestfavoritestudiosHangarmysticBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/hangar-mystic/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/hangar-mystic/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteBalletdancersGreatestfavoritestudiosHangarmysticParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteBalletdancersGreatestfavoritestudiosHangarmysticReturn requestGet(Integer productId, Consumer<RemoteBalletdancersGreatestfavoritestudiosHangarmysticParam> paramLambda) {
        return doRequestGet(productId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /ballet-dancers/{productId}/greatest-favorite-studios/hangar-mystic/. (auto-generated method)<br>
     * <pre>
     * url: /ballet-dancers/{productId}/greatest-favorite-studios/hangar-mystic/
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteBalletdancersGreatestfavoritestudiosHangarmysticParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteBalletdancersGreatestfavoritestudiosHangarmysticReturn doRequestGet(Integer productId, Consumer<RemoteBalletdancersGreatestfavoritestudiosHangarmysticParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteBalletdancersGreatestfavoritestudiosHangarmysticParam param = new RemoteBalletdancersGreatestfavoritestudiosHangarmysticParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteBalletdancersGreatestfavoritestudiosHangarmysticReturn.class, "/ballet-dancers/{productId}/greatest-favorite-studios/hangar-mystic/", moreUrl(productId), query(param), rule -> {
            ruleOfGetProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /ballet-dancers/{productId}/greatest-favorite-studios/hangar-mystic/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetProductId(FlutyRemoteApiRule rule) {
    }
}
