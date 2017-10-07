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
package org.docksidestage.remote.maihama.showbase.following;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.following.follow.RemoteFollowingFollowParam;

/**
 * RemoteMaihamaShowbaseBsFollowingBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsFollowingBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public RemoteMaihamaShowbaseBsFollowingBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /following/follow. (auto-generated method)<br>
     * <pre>
     * url: /following/follow
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteFollowingFollowParam. (NotNull)
     */
    public void requestFollow(Consumer<RemoteFollowingFollowParam> paramLamda) {
        requestFollow(paramLamda, rule -> {});
    }

    /**
     * Request remote call to /following/follow. (auto-generated method)<br>
     * <pre>
     * url: /following/follow
     * httpMethod: POST
     * </pre>
     * @param paramLamda The callback for RemoteFollowingFollowParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestFollow(Consumer<RemoteFollowingFollowParam> paramLamda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteFollowingFollowParam param = new RemoteFollowingFollowParam();
        paramLamda.accept(param);
        doRequestPost(void.class, "/following/follow", noMoreUrl(), param, rule -> {
            ruleOfFollow(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /following/follow.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFollow(FlutyRemoteApiRule rule) {
    }
}
