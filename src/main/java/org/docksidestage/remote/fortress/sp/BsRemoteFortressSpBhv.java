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
package org.docksidestage.remote.fortress.sp;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.sp.wx.routing.mixworld.RemoteSpWxRoutingMixworldReturn;
import org.docksidestage.remote.fortress.sp.wx.routing.mixworld.maihama.RemoteSpWxRoutingMixworldMaihamaReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of sp.
 * @author FreeGen
 */
public abstract class BsRemoteFortressSpBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressSpBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /sp/wx/routing/mixworld/{first}. (auto-generated method)<br>
     * <pre>
     * url: /sp/wx/routing/mixworld/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteSpWxRoutingMixworldReturn requestWxRoutingMixworld(String first) {
        return doRequestWxRoutingMixworld(first, rule -> {});
    }

    /**
     * Request remote call to /sp/wx/routing/mixworld/{first}. (auto-generated method)<br>
     * <pre>
     * url: /sp/wx/routing/mixworld/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteSpWxRoutingMixworldReturn doRequestWxRoutingMixworld(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteSpWxRoutingMixworldReturn.class, "/sp/wx/routing/mixworld/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfWxRoutingMixworldFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /sp/wx/routing/mixworld/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfWxRoutingMixworldFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /sp/wx/routing/mixworld/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /sp/wx/routing/mixworld/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteSpWxRoutingMixworldReturn requestWxRoutingMixworld(String first, String second) {
        return doRequestWxRoutingMixworld(first, second, rule -> {});
    }

    /**
     * Request remote call to /sp/wx/routing/mixworld/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /sp/wx/routing/mixworld/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteSpWxRoutingMixworldReturn doRequestWxRoutingMixworld(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteSpWxRoutingMixworldReturn.class, "/sp/wx/routing/mixworld/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfWxRoutingMixworldFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /sp/wx/routing/mixworld/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfWxRoutingMixworldFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /sp/wx/routing/mixworld/maihama/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /sp/wx/routing/mixworld/maihama/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteSpWxRoutingMixworldMaihamaReturn requestWxRoutingMixworldMaihama(String first, String second) {
        return doRequestWxRoutingMixworldMaihama(first, second, rule -> {});
    }

    /**
     * Request remote call to /sp/wx/routing/mixworld/maihama/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /sp/wx/routing/mixworld/maihama/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteSpWxRoutingMixworldMaihamaReturn doRequestWxRoutingMixworldMaihama(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteSpWxRoutingMixworldMaihamaReturn.class, "/sp/wx/routing/mixworld/maihama/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfWxRoutingMixworldMaihamaFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /sp/wx/routing/mixworld/maihama/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfWxRoutingMixworldMaihamaFirstSecond(FlutyRemoteApiRule rule) {
    }
}
