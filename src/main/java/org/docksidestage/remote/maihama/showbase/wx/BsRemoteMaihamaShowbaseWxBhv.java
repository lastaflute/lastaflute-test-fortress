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
package org.docksidestage.remote.maihama.showbase.wx;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.AbstractRemoteMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.wx.faicli.RemoteWxFaicliParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.basic.RemoteWxRemogenListBasicReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.genebean.RemoteWxRemogenListGenebeanReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.genestring.RemoteWxRemogenListGenestringReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.list.innergene.RemoteWxRemogenListInnergeneReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.nobody.RemoteWxRemogenTrickyNobodyReturn;

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
}
