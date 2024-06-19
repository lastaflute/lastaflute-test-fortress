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
package org.docksidestage.remote.fortress.wx.routing.restlike.lmlike;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.wx.routing.restlike.lmlike.index.RemoteWxRoutingRestlikeLmlikeReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of wx.routing.restlike.lmlike.
 * @author FreeGen
 */
public abstract class BsRemoteFortressWxRoutingRestlikeLmlikeBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressWxRoutingRestlikeLmlikeBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /wx/routing/restlike/lmlike/{memberStatus}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/lmlike/{memberStatus}
     * httpMethod: GET
     * </pre>
     * @param memberStatus The value of path variable for memberStatus. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingRestlikeLmlikeReturn requestGet(org.docksidestage.dbflute.allcommon.CDef.MemberStatus memberStatus) {
        return doRequestGet(memberStatus, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restlike/lmlike/{memberStatus}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restlike/lmlike/{memberStatus}
     * httpMethod: GET
     * </pre>
     * @param memberStatus The value of path variable for memberStatus. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingRestlikeLmlikeReturn doRequestGet(org.docksidestage.dbflute.allcommon.CDef.MemberStatus memberStatus, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingRestlikeLmlikeReturn.class, "/wx/routing/restlike/lmlike/{memberStatus}", moreUrl(memberStatus), noQuery(), rule -> {
            ruleOfGetMemberStatus(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restlike/lmlike/{memberStatus}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfGetMemberStatus(FlutyRemoteApiRule rule) {
    }
}
