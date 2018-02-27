/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.remote.maihama.showbase.wx;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.wx.faicli.RemoteWxFaicliParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.body.basic.RemoteWxRemogenBodyBasicParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.body.list.RemoteWxRemogenBodyListParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.basic.RemoteWxRemogenListBasicReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.genebean.RemoteWxRemogenListGenebeanReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.genestring.RemoteWxRemogenListGenestringReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.innergene.RemoteWxRemogenListInnergeneReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.RemoteWxRemogenRoutingReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.amba.RemoteWxRemogenRoutingAmbaReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.amphi.theater.maihama.RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.bonvo.RemoteWxRemogenRoutingBonvoReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.celeb.RemoteWxRemogenRoutingCelebReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.dohotel.RemoteWxRemogenRoutingDohotelFirstSecondParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.dohotel.RemoteWxRemogenRoutingDohotelParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.dohotel.RemoteWxRemogenRoutingDohotelReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.dstore.RemoteWxRemogenRoutingDstoreReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.land.RemoteWxRemogenRoutingLandReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.maihama.RemoteWxRemogenRoutingMaihamaReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.miraco.RemoteWxRemogenRoutingMiracoReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.piari.RemoteWxRemogenRoutingPiariReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.resola.RemoteWxRemogenRoutingResolaReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.sea.RemoteWxRemogenRoutingSeaReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.suffix.allsuffix.RemoteWxRemogenSuffixAllsuffixReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.suffix.nosuffix.RemoteWxRemogenSuffixNosuffixReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.suffix.partonly.RemoteWxRemogenSuffixPartonlyReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.suffix.toponly.RemoteWxRemogenSuffixToponlyReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.nobody.RemoteWxRemogenTrickyNobodyReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.oddprop.RemoteWxRemogenTrickyOddpropReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.recycle.RemoteWxRemogenTrickyRecycleReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.selfref.RemoteWxRemogenTrickySelfrefReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of wx.
 * @author FreeGen
 */
public abstract class BsRemoteMaihamaShowbaseWxBhv extends AbstractRemoteMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteMaihamaShowbaseWxBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /wx/faicli/. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxFaicliParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public Object requestFaicli(Consumer<RemoteWxFaicliParam> paramLambda) {
        return requestFaicli(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/faicli/. (auto-generated method)<br>
     * <pre>
     * url: /wx/faicli/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxFaicliParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected Object requestFaicli(Consumer<RemoteWxFaicliParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxFaicliParam param = new RemoteWxFaicliParam();
        paramLambda.accept(param);
        return doRequestPost(Object.class, "/wx/faicli/", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfFaicli(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/faicli/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFaicli(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/body/basic. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/body/basic
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenBodyBasicParam. (NotNull)
     */
    public void requestRemogenBodyBasic(Consumer<RemoteWxRemogenBodyBasicParam> paramLambda) {
        requestRemogenBodyBasic(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/body/basic. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/body/basic
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenBodyBasicParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestRemogenBodyBasic(Consumer<RemoteWxRemogenBodyBasicParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenBodyBasicParam param = new RemoteWxRemogenBodyBasicParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/wx/remogen/body/basic", noMoreUrl(), param, rule -> {
            ruleOfRemogenBodyBasic(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/body/basic.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenBodyBasic(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/body/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/body/list
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for java.util.List<RemoteWxRemogenBodyListParam>. (NotNull)
     */
    public void requestRemogenBodyList(Consumer<java.util.List<RemoteWxRemogenBodyListParam>> paramLambda) {
        requestRemogenBodyList(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/body/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/body/list
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for java.util.List<RemoteWxRemogenBodyListParam>. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void requestRemogenBodyList(Consumer<java.util.List<RemoteWxRemogenBodyListParam>> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        java.util.List<RemoteWxRemogenBodyListParam> param = new java.util.ArrayList<RemoteWxRemogenBodyListParam>();
        paramLambda.accept(param);
        doRequestPost(void.class, "/wx/remogen/body/list", noMoreUrl(), param, rule -> {
            ruleOfRemogenBodyList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/body/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenBodyList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/list/basic. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/basic
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListBasicReturn> requestRemogenListBasic() {
        return requestRemogenListBasic(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/list/basic. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/basic
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListBasicReturn> requestRemogenListBasic(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListBasicReturn>>() {
        }.getType(), "/wx/remogen/list/basic", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenListBasic(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/list/basic.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenListBasic(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/list/genebean. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/genebean
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenebeanReturn> requestRemogenListGenebean() {
        return requestRemogenListGenebean(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/list/genebean. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/genebean
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenebeanReturn> requestRemogenListGenebean(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenebeanReturn>>() {
        }.getType(), "/wx/remogen/list/genebean", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenListGenebean(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/list/genebean.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenListGenebean(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/list/genestring. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/genestring
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenestringReturn> requestRemogenListGenestring() {
        return requestRemogenListGenestring(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/list/genestring. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/genestring
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenestringReturn> requestRemogenListGenestring(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenestringReturn>>() {
        }.getType(), "/wx/remogen/list/genestring", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenListGenestring(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/list/genestring.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenListGenestring(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/list/innergene. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/innergene
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListInnergeneReturn> requestRemogenListInnergene() {
        return requestRemogenListInnergene(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/list/innergene. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/innergene
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListInnergeneReturn> requestRemogenListInnergene(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListInnergeneReturn>>() {
        }.getType(), "/wx/remogen/list/innergene", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenListInnergene(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/list/innergene.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenListInnergene(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingReturn requestRemogenRouting(Integer first) {
        return requestRemogenRouting(first, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingReturn requestRemogenRouting(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingReturn.class, "/wx/remogen/routing/{first}", moreUrl(first), noRequestBody(), rule -> {
            ruleOfRemogenRoutingFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/maihama
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingMaihamaReturn requestRemogenRoutingMaihama() {
        return requestRemogenRoutingMaihama(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/maihama
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingMaihamaReturn requestRemogenRoutingMaihama(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingMaihamaReturn.class, "/wx/remogen/routing/maihama", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenRoutingMaihama(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/maihama.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingMaihama(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/sea/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/sea/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingSeaReturn requestRemogenRoutingSea(String first) {
        return requestRemogenRoutingSea(first, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/sea/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/sea/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingSeaReturn requestRemogenRoutingSea(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingSeaReturn.class, "/wx/remogen/routing/sea/{first}", moreUrl(first), noRequestBody(), rule -> {
            ruleOfRemogenRoutingSeaFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/sea/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingSeaFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/land/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/land/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingLandReturn requestRemogenRoutingLand(String first, String second) {
        return requestRemogenRoutingLand(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/land/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/land/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingLandReturn requestRemogenRoutingLand(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingLandReturn.class, "/wx/remogen/routing/land/{first}/{second}", moreUrl(first, second), noRequestBody(), rule -> {
            ruleOfRemogenRoutingLandFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/land/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingLandFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/piari. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/piari
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingPiariReturn requestRemogenRoutingPiari() {
        return requestRemogenRoutingPiari(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/piari. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/piari
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingPiariReturn requestRemogenRoutingPiari(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingPiariReturn.class, "/wx/remogen/routing/piari", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenRoutingPiari(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/piari.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingPiari(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/piari/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/piari/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingPiariReturn requestRemogenRoutingPiari(String first) {
        return requestRemogenRoutingPiari(first, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/piari/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/piari/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingPiariReturn requestRemogenRoutingPiari(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingPiariReturn.class, "/wx/remogen/routing/piari/{first}", moreUrl(first), noRequestBody(), rule -> {
            ruleOfRemogenRoutingPiariFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/piari/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingPiariFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/dstore. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dstore
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingDstoreReturn requestRemogenRoutingDstore() {
        return requestRemogenRoutingDstore(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/dstore. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dstore
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDstoreReturn requestRemogenRoutingDstore(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingDstoreReturn.class, "/wx/remogen/routing/dstore", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenRoutingDstore(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/dstore.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingDstore(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/dstore/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dstore/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingDstoreReturn requestRemogenRoutingDstore(String first) {
        return requestRemogenRoutingDstore(first, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/dstore/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dstore/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDstoreReturn requestRemogenRoutingDstore(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingDstoreReturn.class, "/wx/remogen/routing/dstore/{first}", moreUrl(first), noRequestBody(), rule -> {
            ruleOfRemogenRoutingDstoreFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/dstore/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingDstoreFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/dstore/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dstore/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingDstoreReturn requestRemogenRoutingDstore(String first, String second) {
        return requestRemogenRoutingDstore(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/dstore/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dstore/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDstoreReturn requestRemogenRoutingDstore(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingDstoreReturn.class, "/wx/remogen/routing/dstore/{first}/{second}", moreUrl(first, second), noRequestBody(), rule -> {
            ruleOfRemogenRoutingDstoreFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/dstore/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingDstoreFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/bonvo/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/bonvo/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingBonvoReturn requestRemogenRoutingBonvo(String first) {
        return requestRemogenRoutingBonvo(first, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/bonvo/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/bonvo/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingBonvoReturn requestRemogenRoutingBonvo(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingBonvoReturn.class, "/wx/remogen/routing/bonvo/{first}", moreUrl(first), noRequestBody(), rule -> {
            ruleOfRemogenRoutingBonvoFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/bonvo/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingBonvoFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/bonvo/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/bonvo/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingBonvoReturn requestRemogenRoutingBonvo(String first, String second) {
        return requestRemogenRoutingBonvo(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/bonvo/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/bonvo/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingBonvoReturn requestRemogenRoutingBonvo(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingBonvoReturn.class, "/wx/remogen/routing/bonvo/{first}/{second}", moreUrl(first, second), noRequestBody(), rule -> {
            ruleOfRemogenRoutingBonvoFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/bonvo/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingBonvoFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/amba/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/amba/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingAmbaReturn requestRemogenRoutingAmba(String first, String second) {
        return requestRemogenRoutingAmba(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/amba/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/amba/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingAmbaReturn requestRemogenRoutingAmba(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingAmbaReturn.class, "/wx/remogen/routing/amba/{first}/{second}", moreUrl(first, second), noRequestBody(), rule -> {
            ruleOfRemogenRoutingAmbaFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/amba/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingAmbaFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/amba/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/amba/{first}/{second}/{third}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingAmbaReturn requestRemogenRoutingAmba(String first, String second, String third) {
        return requestRemogenRoutingAmba(first, second, third, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/amba/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/amba/{first}/{second}/{third}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingAmbaReturn requestRemogenRoutingAmba(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingAmbaReturn.class, "/wx/remogen/routing/amba/{first}/{second}/{third}", moreUrl(first, second, third), noRequestBody(), rule -> {
            ruleOfRemogenRoutingAmbaFirstSecondThird(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/amba/{first}/{second}/{third}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingAmbaFirstSecondThird(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/miraco/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/miraco/{first}/{second}/{third}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingMiracoReturn requestRemogenRoutingMiraco(String first, String second, String third) {
        return requestRemogenRoutingMiraco(first, second, third, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/miraco/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/miraco/{first}/{second}/{third}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingMiracoReturn requestRemogenRoutingMiraco(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingMiracoReturn.class, "/wx/remogen/routing/miraco/{first}/{second}/{third}", moreUrl(first, second, third), noRequestBody(), rule -> {
            ruleOfRemogenRoutingMiracoFirstSecondThird(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/miraco/{first}/{second}/{third}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingMiracoFirstSecondThird(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/miraco/{first}/{second}/{third}/{fourth}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/miraco/{first}/{second}/{third}/{fourth}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param fourth The value of path variable for fourth. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingMiracoReturn requestRemogenRoutingMiraco(String first, String second, String third, String fourth) {
        return requestRemogenRoutingMiraco(first, second, third, fourth, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/miraco/{first}/{second}/{third}/{fourth}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/miraco/{first}/{second}/{third}/{fourth}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param fourth The value of path variable for fourth. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingMiracoReturn requestRemogenRoutingMiraco(String first, String second, String third, String fourth, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingMiracoReturn.class, "/wx/remogen/routing/miraco/{first}/{second}/{third}/{fourth}", moreUrl(first, second, third, fourth), noRequestBody(), rule -> {
            ruleOfRemogenRoutingMiracoFirstSecondThirdFourth(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/miraco/{first}/{second}/{third}/{fourth}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingMiracoFirstSecondThirdFourth(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/dohotel/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dohotel/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRemogenRoutingDohotelParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingDohotelReturn requestRemogenRoutingDohotel(String first, Consumer<RemoteWxRemogenRoutingDohotelParam> paramLambda) {
        return requestRemogenRoutingDohotel(first, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/dohotel/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dohotel/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRemogenRoutingDohotelParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDohotelReturn requestRemogenRoutingDohotel(String first, Consumer<RemoteWxRemogenRoutingDohotelParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenRoutingDohotelParam param = new RemoteWxRemogenRoutingDohotelParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteWxRemogenRoutingDohotelReturn.class, "/wx/remogen/routing/dohotel/{first}", moreUrl(first), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfRemogenRoutingDohotelFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/dohotel/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingDohotelFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/dohotel/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dohotel/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param paramLambda The callback for RemoteWxRemogenRoutingDohotelFirstSecondParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingDohotelReturn requestRemogenRoutingDohotel(String first, String second, Consumer<RemoteWxRemogenRoutingDohotelFirstSecondParam> paramLambda) {
        return requestRemogenRoutingDohotel(first, second, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/dohotel/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/dohotel/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param paramLambda The callback for RemoteWxRemogenRoutingDohotelFirstSecondParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDohotelReturn requestRemogenRoutingDohotel(String first, String second, Consumer<RemoteWxRemogenRoutingDohotelFirstSecondParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenRoutingDohotelFirstSecondParam param = new RemoteWxRemogenRoutingDohotelFirstSecondParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteWxRemogenRoutingDohotelReturn.class, "/wx/remogen/routing/dohotel/{first}/{second}", moreUrl(first, second), param, rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfRemogenRoutingDohotelFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/dohotel/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingDohotelFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/celeb/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/celeb/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingCelebReturn requestRemogenRoutingCeleb(Integer first) {
        return requestRemogenRoutingCeleb(first, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/celeb/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/celeb/{first}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingCelebReturn requestRemogenRoutingCeleb(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingCelebReturn.class, "/wx/remogen/routing/celeb/{first}", moreUrl(first), noRequestBody(), rule -> {
            ruleOfRemogenRoutingCelebFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/celeb/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingCelebFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/celeb/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/celeb/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingCelebReturn requestRemogenRoutingCeleb(Integer first, Long second) {
        return requestRemogenRoutingCeleb(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/celeb/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/celeb/{first}/{second}
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingCelebReturn requestRemogenRoutingCeleb(Integer first, Long second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingCelebReturn.class, "/wx/remogen/routing/celeb/{first}/{second}", moreUrl(first, second), noRequestBody(), rule -> {
            ruleOfRemogenRoutingCelebFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/celeb/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingCelebFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/{first}/resola. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/{first}/resola
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingResolaReturn requestRemogenRoutingResola(Integer first) {
        return requestRemogenRoutingResola(first, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/{first}/resola. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/{first}/resola
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingResolaReturn requestRemogenRoutingResola(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingResolaReturn.class, "/wx/remogen/routing/{first}/resola", moreUrl(first), noRequestBody(), rule -> {
            ruleOfRemogenRoutingResolaFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/{first}/resola.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingResolaFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/routing/amphi/{first}/theater/{second}/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/amphi/{first}/theater/{second}/maihama
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn requestRemogenRoutingAmphiTheaterMaihama(Integer first, String second) {
        return requestRemogenRoutingAmphiTheaterMaihama(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/routing/amphi/{first}/theater/{second}/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/routing/amphi/{first}/theater/{second}/maihama
     * httpMethod: POST
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn requestRemogenRoutingAmphiTheaterMaihama(Integer first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn.class, "/wx/remogen/routing/amphi/{first}/theater/{second}/maihama", moreUrl(first, second), noRequestBody(), rule -> {
            ruleOfRemogenRoutingAmphiTheaterMaihamaFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/routing/amphi/{first}/theater/{second}/maihama.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenRoutingAmphiTheaterMaihamaFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/suffix/allsuffix. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/allsuffix
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenSuffixAllsuffixReturn requestRemogenSuffixAllsuffix() {
        return requestRemogenSuffixAllsuffix(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/suffix/allsuffix. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/allsuffix
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixAllsuffixReturn requestRemogenSuffixAllsuffix(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenSuffixAllsuffixReturn.class, "/wx/remogen/suffix/allsuffix", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenSuffixAllsuffix(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/suffix/allsuffix.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenSuffixAllsuffix(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/suffix/nosuffix. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/nosuffix
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenSuffixNosuffixReturn requestRemogenSuffixNosuffix() {
        return requestRemogenSuffixNosuffix(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/suffix/nosuffix. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/nosuffix
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixNosuffixReturn requestRemogenSuffixNosuffix(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenSuffixNosuffixReturn.class, "/wx/remogen/suffix/nosuffix", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenSuffixNosuffix(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/suffix/nosuffix.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenSuffixNosuffix(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/suffix/partonly. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/partonly
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenSuffixPartonlyReturn requestRemogenSuffixPartonly() {
        return requestRemogenSuffixPartonly(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/suffix/partonly. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/partonly
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixPartonlyReturn requestRemogenSuffixPartonly(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenSuffixPartonlyReturn.class, "/wx/remogen/suffix/partonly", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenSuffixPartonly(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/suffix/partonly.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenSuffixPartonly(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/suffix/toponly. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/toponly
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenSuffixToponlyReturn requestRemogenSuffixToponly() {
        return requestRemogenSuffixToponly(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/suffix/toponly. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/suffix/toponly
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixToponlyReturn requestRemogenSuffixToponly(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenSuffixToponlyReturn.class, "/wx/remogen/suffix/toponly", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenSuffixToponly(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/suffix/toponly.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenSuffixToponly(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/tricky/nobody. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/nobody
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenTrickyNobodyReturn requestRemogenTrickyNobody() {
        return requestRemogenTrickyNobody(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/tricky/nobody. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/nobody
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickyNobodyReturn requestRemogenTrickyNobody(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenTrickyNobodyReturn.class, "/wx/remogen/tricky/nobody", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenTrickyNobody(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/tricky/nobody.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenTrickyNobody(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/tricky/oddprop. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/oddprop
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenTrickyOddpropReturn requestRemogenTrickyOddprop() {
        return requestRemogenTrickyOddprop(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/tricky/oddprop. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/oddprop
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickyOddpropReturn requestRemogenTrickyOddprop(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenTrickyOddpropReturn.class, "/wx/remogen/tricky/oddprop", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenTrickyOddprop(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/tricky/oddprop.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenTrickyOddprop(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/tricky/recycle. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/recycle
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenTrickyRecycleReturn requestRemogenTrickyRecycle() {
        return requestRemogenTrickyRecycle(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/tricky/recycle. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/recycle
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickyRecycleReturn requestRemogenTrickyRecycle(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenTrickyRecycleReturn.class, "/wx/remogen/tricky/recycle", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenTrickyRecycle(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/tricky/recycle.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenTrickyRecycle(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/tricky/selfref. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/selfref
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenTrickySelfrefReturn requestRemogenTrickySelfref() {
        return requestRemogenTrickySelfref(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/tricky/selfref. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/selfref
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickySelfrefReturn requestRemogenTrickySelfref(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(RemoteWxRemogenTrickySelfrefReturn.class, "/wx/remogen/tricky/selfref", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRemogenTrickySelfref(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/tricky/selfref.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenTrickySelfref(FlutyRemoteApiRule rule) {
    }
}
