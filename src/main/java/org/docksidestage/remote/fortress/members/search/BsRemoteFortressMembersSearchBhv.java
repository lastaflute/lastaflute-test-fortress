/*
 * Copyright 2015-2021 the original author or authors.
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
package org.docksidestage.remote.fortress.members.search;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.members.search.index.RemoteMembersSearchParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of members.search.
 * @author FreeGen
 */
public abstract class BsRemoteFortressMembersSearchBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressMembersSearchBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /members/search. (auto-generated method)<br>
     * <pre>
     * url: /members/search
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMembersSearchParam. (NotNull)
     */
    public void requestGet(Consumer<RemoteMembersSearchParam> paramLambda) {
        doRequestGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /members/search. (auto-generated method)<br>
     * <pre>
     * url: /members/search
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteMembersSearchParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestGet(Consumer<RemoteMembersSearchParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteMembersSearchParam param = new RemoteMembersSearchParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/members/search", noMoreUrl(), query(param), rule -> {
            ruleOfGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /members/search.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGet(FlutyRemoteApiRule rule) {
    }
}
