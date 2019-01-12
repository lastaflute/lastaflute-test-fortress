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
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodDeleteParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodDeleteReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodGetParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodGetReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodPostParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodPostReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.enclosing.RemoteWxRemogenMethodEnclosingParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.enclosing.RemoteWxRemogenMethodEnclosingReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.noquery.RemoteWxRemogenMethodNoqueryReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.samename.RemoteWxRemogenMethodSamenameGetParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.samename.RemoteWxRemogenMethodSamenameGetReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.samename.RemoteWxRemogenMethodSamenamePostParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.samename.RemoteWxRemogenMethodSamenamePostReturn;
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
        return doRequestFaicli(paramLambda, rule -> {});
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
    protected Object doRequestFaicli(Consumer<RemoteWxFaicliParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        doRequestRemogenBodyBasic(paramLambda, rule -> {});
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
    protected void doRequestRemogenBodyBasic(Consumer<RemoteWxRemogenBodyBasicParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        doRequestRemogenBodyList(paramLambda, rule -> {});
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
    protected void doRequestRemogenBodyList(Consumer<java.util.List<RemoteWxRemogenBodyListParam>> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenListBasic(rule -> {});
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
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListBasicReturn> doRequestRemogenListBasic(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenListGenebean(rule -> {});
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
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenebeanReturn> doRequestRemogenListGenebean(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenListGenestring(rule -> {});
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
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenestringReturn> doRequestRemogenListGenestring(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenListInnergene(rule -> {});
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
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListInnergeneReturn> doRequestRemogenListInnergene(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
     * Request remote call to /wx/remogen/method/. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodGetParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodGetReturn requestRemogenMethodGet(Consumer<RemoteWxRemogenMethodGetParam> paramLambda) {
        return doRequestRemogenMethodGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodGetParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodGetReturn doRequestRemogenMethodGet(Consumer<RemoteWxRemogenMethodGetParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodGetParam param = new RemoteWxRemogenMethodGetParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRemogenMethodGetReturn.class, "/wx/remogen/method/", noMoreUrl(), query(param), rule -> {
            ruleOfRemogenMethodGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodGet(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodPostParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodPostReturn requestRemogenMethodPost(Consumer<RemoteWxRemogenMethodPostParam> paramLambda) {
        return doRequestRemogenMethodPost(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodPostParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodPostReturn doRequestRemogenMethodPost(Consumer<RemoteWxRemogenMethodPostParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodPostParam param = new RemoteWxRemogenMethodPostParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteWxRemogenMethodPostReturn.class, "/wx/remogen/method/", noMoreUrl(), param, rule -> {
            ruleOfRemogenMethodPost(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodPost(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodDeleteParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodDeleteReturn requestRemogenMethodDelete(Consumer<RemoteWxRemogenMethodDeleteParam> paramLambda) {
        return doRequestRemogenMethodDelete(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodDeleteParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodDeleteReturn doRequestRemogenMethodDelete(Consumer<RemoteWxRemogenMethodDeleteParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodDeleteParam param = new RemoteWxRemogenMethodDeleteParam();
        paramLambda.accept(param);
        return doRequestDelete(RemoteWxRemogenMethodDeleteReturn.class, "/wx/remogen/method/", noMoreUrl(), query(param), rule -> {
            rule.sendBodyBy(
                    new org.lastaflute.remoteapi.sender.body.LaFormSender(new org.dbflute.remoteapi.mapping.FlVacantMappingPolicy()));
            ruleOfRemogenMethodDelete(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodDelete(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/samename. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/samename
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodSamenameGetParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodSamenameGetReturn requestRemogenMethodSamenameGet(Consumer<RemoteWxRemogenMethodSamenameGetParam> paramLambda) {
        return doRequestRemogenMethodSamenameGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/samename. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/samename
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodSamenameGetParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodSamenameGetReturn doRequestRemogenMethodSamenameGet(Consumer<RemoteWxRemogenMethodSamenameGetParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodSamenameGetParam param = new RemoteWxRemogenMethodSamenameGetParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRemogenMethodSamenameGetReturn.class, "/wx/remogen/method/samename", noMoreUrl(), query(param), rule -> {
            ruleOfRemogenMethodSamenameGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/samename.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodSamenameGet(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/samename. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/samename
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodSamenamePostParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodSamenamePostReturn requestRemogenMethodSamenamePost(Consumer<RemoteWxRemogenMethodSamenamePostParam> paramLambda) {
        return doRequestRemogenMethodSamenamePost(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/samename. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/samename
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodSamenamePostParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodSamenamePostReturn doRequestRemogenMethodSamenamePost(Consumer<RemoteWxRemogenMethodSamenamePostParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodSamenamePostParam param = new RemoteWxRemogenMethodSamenamePostParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteWxRemogenMethodSamenamePostReturn.class, "/wx/remogen/method/samename", noMoreUrl(), param, rule -> {
            ruleOfRemogenMethodSamenamePost(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/samename.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodSamenamePost(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/enclosing. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/enclosing
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodEnclosingParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodEnclosingReturn requestRemogenMethodEnclosing(Consumer<RemoteWxRemogenMethodEnclosingParam> paramLambda) {
        return doRequestRemogenMethodEnclosing(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/enclosing. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/enclosing
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodEnclosingParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodEnclosingReturn doRequestRemogenMethodEnclosing(Consumer<RemoteWxRemogenMethodEnclosingParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodEnclosingParam param = new RemoteWxRemogenMethodEnclosingParam();
        paramLambda.accept(param);
        return doRequestDeleteEnclosing(RemoteWxRemogenMethodEnclosingReturn.class, "/wx/remogen/method/enclosing", noMoreUrl(), param, rule -> {
            ruleOfRemogenMethodEnclosing(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/enclosing.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodEnclosing(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/noquery. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/noquery
     * httpMethod: DELETE
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodNoqueryReturn requestRemogenMethodNoquery() {
        return doRequestRemogenMethodNoquery(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/noquery. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/noquery
     * httpMethod: DELETE
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodNoqueryReturn doRequestRemogenMethodNoquery(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestDelete(RemoteWxRemogenMethodNoqueryReturn.class, "/wx/remogen/method/noquery", noMoreUrl(), noQuery(), rule -> {
            ruleOfRemogenMethodNoquery(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/noquery.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodNoquery(FlutyRemoteApiRule rule) {
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
        return doRequestRemogenRouting(first, rule -> {});
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
    protected RemoteWxRemogenRoutingReturn doRequestRemogenRouting(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingMaihama(rule -> {});
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
    protected RemoteWxRemogenRoutingMaihamaReturn doRequestRemogenRoutingMaihama(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingSea(first, rule -> {});
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
    protected RemoteWxRemogenRoutingSeaReturn doRequestRemogenRoutingSea(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingLand(first, second, rule -> {});
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
    protected RemoteWxRemogenRoutingLandReturn doRequestRemogenRoutingLand(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingPiari(rule -> {});
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
    protected RemoteWxRemogenRoutingPiariReturn doRequestRemogenRoutingPiari(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingPiari(first, rule -> {});
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
    protected RemoteWxRemogenRoutingPiariReturn doRequestRemogenRoutingPiari(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingDstore(rule -> {});
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
    protected RemoteWxRemogenRoutingDstoreReturn doRequestRemogenRoutingDstore(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingDstore(first, rule -> {});
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
    protected RemoteWxRemogenRoutingDstoreReturn doRequestRemogenRoutingDstore(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingDstore(first, second, rule -> {});
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
    protected RemoteWxRemogenRoutingDstoreReturn doRequestRemogenRoutingDstore(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingBonvo(first, rule -> {});
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
    protected RemoteWxRemogenRoutingBonvoReturn doRequestRemogenRoutingBonvo(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingBonvo(first, second, rule -> {});
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
    protected RemoteWxRemogenRoutingBonvoReturn doRequestRemogenRoutingBonvo(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingAmba(first, second, rule -> {});
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
    protected RemoteWxRemogenRoutingAmbaReturn doRequestRemogenRoutingAmba(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingAmba(first, second, third, rule -> {});
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
    protected RemoteWxRemogenRoutingAmbaReturn doRequestRemogenRoutingAmba(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingMiraco(first, second, third, rule -> {});
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
    protected RemoteWxRemogenRoutingMiracoReturn doRequestRemogenRoutingMiraco(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingMiraco(first, second, third, fourth, rule -> {});
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
    protected RemoteWxRemogenRoutingMiracoReturn doRequestRemogenRoutingMiraco(String first, String second, String third, String fourth, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingDohotel(first, paramLambda, rule -> {});
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
    protected RemoteWxRemogenRoutingDohotelReturn doRequestRemogenRoutingDohotel(String first, Consumer<RemoteWxRemogenRoutingDohotelParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingDohotel(first, second, paramLambda, rule -> {});
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
    protected RemoteWxRemogenRoutingDohotelReturn doRequestRemogenRoutingDohotel(String first, String second, Consumer<RemoteWxRemogenRoutingDohotelFirstSecondParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingCeleb(first, rule -> {});
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
    protected RemoteWxRemogenRoutingCelebReturn doRequestRemogenRoutingCeleb(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingCeleb(first, second, rule -> {});
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
    protected RemoteWxRemogenRoutingCelebReturn doRequestRemogenRoutingCeleb(Integer first, Long second, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingResola(first, rule -> {});
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
    protected RemoteWxRemogenRoutingResolaReturn doRequestRemogenRoutingResola(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenRoutingAmphiTheaterMaihama(first, second, rule -> {});
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
    protected RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn doRequestRemogenRoutingAmphiTheaterMaihama(Integer first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenSuffixAllsuffix(rule -> {});
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
    protected RemoteWxRemogenSuffixAllsuffixReturn doRequestRemogenSuffixAllsuffix(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenSuffixNosuffix(rule -> {});
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
    protected RemoteWxRemogenSuffixNosuffixReturn doRequestRemogenSuffixNosuffix(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenSuffixPartonly(rule -> {});
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
    protected RemoteWxRemogenSuffixPartonlyReturn doRequestRemogenSuffixPartonly(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenSuffixToponly(rule -> {});
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
    protected RemoteWxRemogenSuffixToponlyReturn doRequestRemogenSuffixToponly(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenTrickyNobody(rule -> {});
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
    protected RemoteWxRemogenTrickyNobodyReturn doRequestRemogenTrickyNobody(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenTrickyOddprop(rule -> {});
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
    protected RemoteWxRemogenTrickyOddpropReturn doRequestRemogenTrickyOddprop(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenTrickyRecycle(rule -> {});
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
    protected RemoteWxRemogenTrickyRecycleReturn doRequestRemogenTrickyRecycle(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
        return doRequestRemogenTrickySelfref(rule -> {});
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
    protected RemoteWxRemogenTrickySelfrefReturn doRequestRemogenTrickySelfref(Consumer<FlutyRemoteApiRule> ruleLambda) {
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
