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
package org.docksidestage.remote.fortress.wx;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.wx.lastadoc.RemoteWxLastadocParam;
import org.docksidestage.remote.fortress.wx.lastadoc.apiemptybody.RemoteWxLastadocApiemptybodyParam;
import org.docksidestage.remote.fortress.wx.login.surprised.signin.RemoteWxLoginSurprisedSigninParam;
import org.docksidestage.remote.fortress.wx.message.RemoteWxMessagePagenumberParam;
import org.docksidestage.remote.fortress.wx.message.RemoteWxMessageParam;
import org.docksidestage.remote.fortress.wx.namedcls.RemoteWxNamedclsParam;
import org.docksidestage.remote.fortress.wx.namedcls.RemoteWxNamedclsReturn;
import org.docksidestage.remote.fortress.wx.remogen.body.basic.RemoteWxRemogenBodyBasicParam;
import org.docksidestage.remote.fortress.wx.remogen.body.list.RemoteWxRemogenBodyListParam;
import org.docksidestage.remote.fortress.wx.remogen.list.basic.RemoteWxRemogenListBasicReturn;
import org.docksidestage.remote.fortress.wx.remogen.list.genebean.RemoteWxRemogenListGenebeanReturn;
import org.docksidestage.remote.fortress.wx.remogen.list.genestring.RemoteWxRemogenListGenestringReturn;
import org.docksidestage.remote.fortress.wx.remogen.list.innergene.RemoteWxRemogenListInnergeneReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.RemoteWxRemogenMethodDeleteParam;
import org.docksidestage.remote.fortress.wx.remogen.method.RemoteWxRemogenMethodDeleteReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.RemoteWxRemogenMethodGetParam;
import org.docksidestage.remote.fortress.wx.remogen.method.RemoteWxRemogenMethodGetReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.RemoteWxRemogenMethodPostParam;
import org.docksidestage.remote.fortress.wx.remogen.method.RemoteWxRemogenMethodPostReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.noquery.RemoteWxRemogenMethodNoqueryReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.onbodyform.RemoteWxRemogenMethodOnbodyformDeleteParam;
import org.docksidestage.remote.fortress.wx.remogen.method.onbodyform.RemoteWxRemogenMethodOnbodyformDeleteReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.onbodyform.RemoteWxRemogenMethodOnbodyformGetParam;
import org.docksidestage.remote.fortress.wx.remogen.method.onbodyform.RemoteWxRemogenMethodOnbodyformGetReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.onbodyjson.RemoteWxRemogenMethodOnbodyjsonParam;
import org.docksidestage.remote.fortress.wx.remogen.method.onbodyjson.RemoteWxRemogenMethodOnbodyjsonReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.samename.RemoteWxRemogenMethodSamenameGetParam;
import org.docksidestage.remote.fortress.wx.remogen.method.samename.RemoteWxRemogenMethodSamenameGetReturn;
import org.docksidestage.remote.fortress.wx.remogen.method.samename.RemoteWxRemogenMethodSamenamePostParam;
import org.docksidestage.remote.fortress.wx.remogen.method.samename.RemoteWxRemogenMethodSamenamePostReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.RemoteWxRemogenRoutingReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.amba.RemoteWxRemogenRoutingAmbaReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.amphi.theater.maihama.RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.bonvo.RemoteWxRemogenRoutingBonvoReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.celeb.RemoteWxRemogenRoutingCelebReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.dohotel.RemoteWxRemogenRoutingDohotelFirstSecondParam;
import org.docksidestage.remote.fortress.wx.remogen.routing.dohotel.RemoteWxRemogenRoutingDohotelParam;
import org.docksidestage.remote.fortress.wx.remogen.routing.dohotel.RemoteWxRemogenRoutingDohotelReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.dstore.RemoteWxRemogenRoutingDstoreReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.land.RemoteWxRemogenRoutingLandReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.maihama.RemoteWxRemogenRoutingMaihamaReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.miraco.RemoteWxRemogenRoutingMiracoReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.piari.RemoteWxRemogenRoutingPiariReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.resola.RemoteWxRemogenRoutingResolaReturn;
import org.docksidestage.remote.fortress.wx.remogen.routing.sea.RemoteWxRemogenRoutingSeaReturn;
import org.docksidestage.remote.fortress.wx.remogen.suffix.allsuffix.RemoteWxRemogenSuffixAllsuffixReturn;
import org.docksidestage.remote.fortress.wx.remogen.suffix.nosuffix.RemoteWxRemogenSuffixNosuffixReturn;
import org.docksidestage.remote.fortress.wx.remogen.suffix.partonly.RemoteWxRemogenSuffixPartonlyReturn;
import org.docksidestage.remote.fortress.wx.remogen.suffix.toponly.RemoteWxRemogenSuffixToponlyReturn;
import org.docksidestage.remote.fortress.wx.remogen.tricky.nobody.RemoteWxRemogenTrickyNobodyReturn;
import org.docksidestage.remote.fortress.wx.remogen.tricky.oddprop.RemoteWxRemogenTrickyOddpropReturn;
import org.docksidestage.remote.fortress.wx.remogen.tricky.recycle.RemoteWxRemogenTrickyRecycleReturn;
import org.docksidestage.remote.fortress.wx.remogen.tricky.selfref.RemoteWxRemogenTrickySelfrefReturn;
import org.docksidestage.remote.fortress.wx.remogen.tricky.suffixhell.RemoteWxRemogenTrickySuffixhellReturn;
import org.docksidestage.remote.fortress.wx.remoteapi.rmhangar.mypage.RemoteWxRemoteapiRmhangarMypageReturn;
import org.docksidestage.remote.fortress.wx.remoteapi.rmhangar.translate.RemoteWxRemoteapiRmhangarTranslateParam;
import org.docksidestage.remote.fortress.wx.remoteapi.rmharbor.lido.mypage.RemoteWxRemoteapiRmharborLidoMypageReturn;
import org.docksidestage.remote.fortress.wx.remoteapi.rmharbor.lido.signin.RemoteWxRemoteapiRmharborLidoSigninParam;
import org.docksidestage.remote.fortress.wx.remoteapi.rmharbor.serh.product.RemoteWxRemoteapiRmharborSerhProductParam;
import org.docksidestage.remote.fortress.wx.remoteapi.rmharbor.serh.signin.RemoteWxRemoteapiRmharborSerhSigninParam;
import org.docksidestage.remote.fortress.wx.remoteapi.rmharbor.via.mypage.RemoteWxRemoteapiRmharborViaMypageReturn;
import org.docksidestage.remote.fortress.wx.remoteapi.rmshowbase.remogen.resola.RemoteWxRemoteapiRmshowbaseRemogenResolaReturn;
import org.docksidestage.remote.fortress.wx.request.form.RemoteWxRequestFormParam;
import org.docksidestage.remote.fortress.wx.request.form.RemoteWxRequestFormReturn;
import org.docksidestage.remote.fortress.wx.request.form.eccolle.RemoteWxRequestFormEccolleParam;
import org.docksidestage.remote.fortress.wx.request.form.eccolle.RemoteWxRequestFormEccolleReturn;
import org.docksidestage.remote.fortress.wx.request.form.swagger.RemoteWxRequestFormSwaggerParam;
import org.docksidestage.remote.fortress.wx.request.form.swagger.RemoteWxRequestFormSwaggerReturn;
import org.docksidestage.remote.fortress.wx.request.json.body.RemoteWxRequestJsonBodyParam;
import org.docksidestage.remote.fortress.wx.request.json.body.clienterror.RemoteWxRequestJsonBodyClienterrorParam;
import org.docksidestage.remote.fortress.wx.request.json.body.list.RemoteWxRequestJsonBodyListParam;
import org.docksidestage.remote.fortress.wx.request.json.body.swagger.RemoteWxRequestJsonBodySwaggerParam;
import org.docksidestage.remote.fortress.wx.request.json.body.systemerror.RemoteWxRequestJsonBodySystemerrorParam;
import org.docksidestage.remote.fortress.wx.request.json.body.validated.RemoteWxRequestJsonBodyValidatedParam;
import org.docksidestage.remote.fortress.wx.request.json.body.validatedlonely.RemoteWxRequestJsonBodyValidatedlonelyParam;
import org.docksidestage.remote.fortress.wx.request.json.body.validatedlonelylist.RemoteWxRequestJsonBodyValidatedlonelylistParam;
import org.docksidestage.remote.fortress.wx.request.json.prop.RemoteWxRequestJsonPropParam;
import org.docksidestage.remote.fortress.wx.request.json.prop.json.RemoteWxRequestJsonPropJsonParam;
import org.docksidestage.remote.fortress.wx.request.json.prop.json.RemoteWxRequestJsonPropJsonReturn;
import org.docksidestage.remote.fortress.wx.request.multipart.upload.RemoteWxRequestMultipartUploadParam;
import org.docksidestage.remote.fortress.wx.request.pathvar.cls.RemoteWxRequestPathvarClsReturn;
import org.docksidestage.remote.fortress.wx.request.pathvar.nameof.RemoteWxRequestPathvarNameofReturn;
import org.docksidestage.remote.fortress.wx.request.pathvar.optional.onparade.RemoteWxRequestPathvarOptionalOnparadeReturn;
import org.docksidestage.remote.fortress.wx.request.pathvar.string.RemoteWxRequestPathvarStringReturn;
import org.docksidestage.remote.fortress.wx.response.html.add.register.RemoteWxResponseHtmlAddRegisterParam;
import org.docksidestage.remote.fortress.wx.response.html.edit.update.RemoteWxResponseHtmlEditUpdateParam;
import org.docksidestage.remote.fortress.wx.response.html.edit.withdrawal.RemoteWxResponseHtmlEditWithdrawalParam;
import org.docksidestage.remote.fortress.wx.response.html.list.RemoteWxResponseHtmlListPagenumberParam;
import org.docksidestage.remote.fortress.wx.response.html.list.RemoteWxResponseHtmlListParam;
import org.docksidestage.remote.fortress.wx.response.json.RemoteWxResponseJsonReturn;
import org.docksidestage.remote.fortress.wx.response.json.eccolle.list.RemoteWxResponseJsonEccolleListReturn;
import org.docksidestage.remote.fortress.wx.response.json.eccolle.listempty.RemoteWxResponseJsonEccolleListemptyReturn;
import org.docksidestage.remote.fortress.wx.response.json.eccolle.map.RemoteWxResponseJsonEccolleMapReturn;
import org.docksidestage.remote.fortress.wx.response.json.justified.RemoteWxResponseJsonJustifiedReturn;
import org.docksidestage.remote.fortress.wx.response.json.justified.named.RemoteWxResponseJsonJustifiedNamedReturn;
import org.docksidestage.remote.fortress.wx.response.json.switched.engine.RemoteWxResponseJsonSwitchedEngineReturn;
import org.docksidestage.remote.fortress.wx.response.json.switched.mapping.RemoteWxResponseJsonSwitchedMappingReturn;
import org.docksidestage.remote.fortress.wx.response.json.switched.none.RemoteWxResponseJsonSwitchedNoneReturn;
import org.docksidestage.remote.fortress.wx.response.stream.api.validationerror.RemoteWxResponseStreamApiValidationerrorParam;
import org.docksidestage.remote.fortress.wx.response.stream.validationerror.RemoteWxResponseStreamValidationerrorParam;
import org.docksidestage.remote.fortress.wx.rmshowbase.remogen.method.delete.RemoteWxRmshowbaseRemogenMethodDeleteReturn;
import org.docksidestage.remote.fortress.wx.routing.RemoteWxRoutingReturn;
import org.docksidestage.remote.fortress.wx.routing.amba.RemoteWxRoutingAmbaReturn;
import org.docksidestage.remote.fortress.wx.routing.amphi.theater.maihama.RemoteWxRoutingAmphiTheaterMaihamaReturn;
import org.docksidestage.remote.fortress.wx.routing.bonvo.RemoteWxRoutingBonvoReturn;
import org.docksidestage.remote.fortress.wx.routing.celeb.RemoteWxRoutingCelebReturn;
import org.docksidestage.remote.fortress.wx.routing.dohotel.RemoteWxRoutingDohotelFirstSecondParam;
import org.docksidestage.remote.fortress.wx.routing.dohotel.RemoteWxRoutingDohotelParam;
import org.docksidestage.remote.fortress.wx.routing.dohotel.RemoteWxRoutingDohotelReturn;
import org.docksidestage.remote.fortress.wx.routing.dstore.RemoteWxRoutingDstoreReturn;
import org.docksidestage.remote.fortress.wx.routing.land.RemoteWxRoutingLandReturn;
import org.docksidestage.remote.fortress.wx.routing.maihama.RemoteWxRoutingMaihamaReturn;
import org.docksidestage.remote.fortress.wx.routing.miraco.RemoteWxRoutingMiracoReturn;
import org.docksidestage.remote.fortress.wx.routing.mixworld.RemoteWxRoutingMixworldReturn;
import org.docksidestage.remote.fortress.wx.routing.mixworld.maihama.RemoteWxRoutingMixworldMaihamaReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.basic.RemoteWxRoutingNearpathBasicReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.basic.number.RemoteWxRoutingNearpathBasicNumberReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.basic.string.RemoteWxRoutingNearpathBasicStringReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword01.str2num.RemoteWxRoutingNearpathByword01Str2numReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword01.str2num.named.RemoteWxRoutingNearpathByword01Str2numNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword02.num2str.RemoteWxRoutingNearpathByword02Num2strReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword02.num2str.named.RemoteWxRoutingNearpathByword02Num2strNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword05.optstr2num.RemoteWxRoutingNearpathByword05Optstr2numReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword05.optstr2num.named.RemoteWxRoutingNearpathByword05Optstr2numNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword10.optstr4str2num.RemoteWxRoutingNearpathByword10Optstr4str2numReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword10.optstr4str2num.named.RemoteWxRoutingNearpathByword10Optstr4str2numNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword11.optstr4numtostr.RemoteWxRoutingNearpathByword11Optstr4numtostrReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword11.optstr4numtostr.named.RemoteWxRoutingNearpathByword11Optstr4numtostrNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword12.optstr4cls2str.RemoteWxRoutingNearpathByword12Optstr4cls2strReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword12.optstr4cls2str.named.RemoteWxRoutingNearpathByword12Optstr4cls2strNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword13.optstr4cls2num.RemoteWxRoutingNearpathByword13Optstr4cls2numReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword13.optstr4cls2num.named.RemoteWxRoutingNearpathByword13Optstr4cls2numNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword15.optnum4str2num.RemoteWxRoutingNearpathByword15Optnum4str2numReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword15.optnum4str2num.named.RemoteWxRoutingNearpathByword15Optnum4str2numNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword16.optnum4num2str.RemoteWxRoutingNearpathByword16Optnum4num2strReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.byword16.optnum4num2str.named.RemoteWxRoutingNearpathByword16Optnum4num2strNamedReturn;
import org.docksidestage.remote.fortress.wx.routing.nearpath.shadowed.method.RemoteWxRoutingNearpathShadowedMethodReturn;
import org.docksidestage.remote.fortress.wx.routing.piari.RemoteWxRoutingPiariReturn;
import org.docksidestage.remote.fortress.wx.routing.reqparam.RemoteWxRoutingReqparamParam;
import org.docksidestage.remote.fortress.wx.routing.reqparam.conflict.RemoteWxRoutingReqparamConflictParam;
import org.docksidestage.remote.fortress.wx.routing.resola.RemoteWxRoutingResolaReturn;
import org.docksidestage.remote.fortress.wx.routing.sea.RemoteWxRoutingSeaReturn;
import org.docksidestage.remote.fortress.wx.routing.wording.RemoteWxRoutingWordingReturn;
import org.docksidestage.remote.fortress.wx.routing.wording.land.RemoteWxRoutingWordingLandReturn;
import org.docksidestage.remote.fortress.wx.routing.wording.piari.plaza.RemoteWxRoutingWordingPiariPlazaReturn;
import org.docksidestage.remote.fortress.wx.routing.wording.sea.RemoteWxRoutingWordingSeaReturn;
import org.docksidestage.remote.fortress.wx.thymeleaf.parade.RemoteWxThymeleafParadePagenumberParam;
import org.docksidestage.remote.fortress.wx.thymeleaf.parade.RemoteWxThymeleafParadeParam;
import org.docksidestage.remote.fortress.wx.transaction.memories.fail.RemoteWxTransactionMemoriesFailParam;
import org.docksidestage.remote.fortress.wx.transaction.memories.failasync.RemoteWxTransactionMemoriesFailasyncParam;
import org.docksidestage.remote.fortress.wx.validator.RemoteWxValidatorParam;
import org.docksidestage.remote.fortress.wx.validator.custom.RemoteWxValidatorCustomParam;
import org.docksidestage.remote.fortress.wx.validator.genericform.RemoteWxValidatorGenericformParam;
import org.docksidestage.remote.fortress.wx.validator.groupingjson.RemoteWxValidatorGroupingjsonReturn;
import org.docksidestage.remote.fortress.wx.validator.groups.response.dockside.RemoteWxValidatorGroupsResponseDocksideReturn;
import org.docksidestage.remote.fortress.wx.validator.groups.response.hangar.RemoteWxValidatorGroupsResponseHangarReturn;
import org.docksidestage.remote.fortress.wx.validator.groups.response.usedefault.RemoteWxValidatorGroupsResponseUsedefaultReturn;
import org.docksidestage.remote.fortress.wx.validator.listjson.RemoteWxValidatorListjsonParam;
import org.docksidestage.remote.fortress.wx.validator.listjson.RemoteWxValidatorListjsonReturn;
import org.docksidestage.remote.fortress.wx.validator.wholejson.RemoteWxValidatorWholejsonParam;
import org.docksidestage.remote.fortress.wx.validator.wholejson.RemoteWxValidatorWholejsonReturn;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of wx.
 * @author FreeGen
 */
public abstract class BsRemoteFortressWxBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressWxBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /wx/appex/basic/. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/
     * httpMethod: GET
     * </pre>
     */
    public void requestAppexBasic() {
        doRequestAppexBasic(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/basic/. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAppexBasic(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/appex/basic/", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexBasic(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/basic/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexBasic(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/basic/withoutinfo. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/withoutinfo
     * httpMethod: GET
     * </pre>
     */
    public void requestAppexBasicWithoutinfo() {
        doRequestAppexBasicWithoutinfo(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/basic/withoutinfo. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/withoutinfo
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAppexBasicWithoutinfo(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/appex/basic/withoutinfo", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexBasicWithoutinfo(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/basic/withoutinfo.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexBasicWithoutinfo(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/basic/loginfailure. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/loginfailure
     * httpMethod: GET
     * </pre>
     */
    public void requestAppexBasicLoginfailure() {
        doRequestAppexBasicLoginfailure(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/basic/loginfailure. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/loginfailure
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAppexBasicLoginfailure(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/appex/basic/loginfailure", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexBasicLoginfailure(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/basic/loginfailure.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexBasicLoginfailure(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/basic/alreadydeleted. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/alreadydeleted
     * httpMethod: GET
     * </pre>
     */
    public void requestAppexBasicAlreadydeleted() {
        doRequestAppexBasicAlreadydeleted(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/basic/alreadydeleted. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/basic/alreadydeleted
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAppexBasicAlreadydeleted(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/appex/basic/alreadydeleted", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexBasicAlreadydeleted(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/basic/alreadydeleted.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexBasicAlreadydeleted(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/handler/alreadydeleted. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/handler/alreadydeleted
     * httpMethod: GET
     * </pre>
     */
    public void requestAppexHandlerAlreadydeleted() {
        doRequestAppexHandlerAlreadydeleted(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/handler/alreadydeleted. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/handler/alreadydeleted
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAppexHandlerAlreadydeleted(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/appex/handler/alreadydeleted", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexHandlerAlreadydeleted(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/handler/alreadydeleted.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexHandlerAlreadydeleted(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/handler/json. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/handler/json
     * httpMethod: GET
     * </pre>
     */
    public void requestAppexHandlerJson() {
        doRequestAppexHandlerJson(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/handler/json. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/handler/json
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAppexHandlerJson(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/appex/handler/json", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexHandlerJson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/handler/json.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexHandlerJson(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/message/json/. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/message/json/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestAppexMessageJson() {
        return doRequestAppexMessageJson(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/message/json/. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/message/json/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestAppexMessageJson(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/appex/message/json/", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexMessageJson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/message/json/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexMessageJson(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/message/json/direct. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/message/json/direct
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestAppexMessageJsonDirect() {
        return doRequestAppexMessageJsonDirect(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/message/json/direct. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/message/json/direct
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestAppexMessageJsonDirect(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/appex/message/json/direct", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexMessageJsonDirect(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/message/json/direct.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexMessageJsonDirect(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/appex/message/json/novalues. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/message/json/novalues
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestAppexMessageJsonNovalues() {
        return doRequestAppexMessageJsonNovalues(rule -> {});
    }

    /**
     * Request remote call to /wx/appex/message/json/novalues. (auto-generated method)<br>
     * <pre>
     * url: /wx/appex/message/json/novalues
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestAppexMessageJsonNovalues(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/appex/message/json/novalues", noMoreUrl(), noQuery(), rule -> {
            ruleOfAppexMessageJsonNovalues(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/appex/message/json/novalues.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAppexMessageJsonNovalues(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/bizpkg/onionarc/. (auto-generated method)<br>
     * <pre>
     * url: /wx/bizpkg/onionarc/
     * httpMethod: GET
     * </pre>
     */
    public void requestBizpkgOnionarc() {
        doRequestBizpkgOnionarc(rule -> {});
    }

    /**
     * Request remote call to /wx/bizpkg/onionarc/. (auto-generated method)<br>
     * <pre>
     * url: /wx/bizpkg/onionarc/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestBizpkgOnionarc(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/bizpkg/onionarc/", noMoreUrl(), noQuery(), rule -> {
            ruleOfBizpkgOnionarc(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/bizpkg/onionarc/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfBizpkgOnionarc(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/config/. (auto-generated method)<br>
     * <pre>
     * url: /wx/config/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestConfig() {
        return doRequestConfig(rule -> {});
    }

    /**
     * Request remote call to /wx/config/. (auto-generated method)<br>
     * <pre>
     * url: /wx/config/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestConfig(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/config/", noMoreUrl(), noQuery(), rule -> {
            ruleOfConfig(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/config/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfConfig(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/deftest/ninja/sea/. (auto-generated method)<br>
     * <pre>
     * url: /wx/deftest/ninja/sea/
     * httpMethod: GET
     * </pre>
     */
    public void requestDeftestNinjaSea() {
        doRequestDeftestNinjaSea(rule -> {});
    }

    /**
     * Request remote call to /wx/deftest/ninja/sea/. (auto-generated method)<br>
     * <pre>
     * url: /wx/deftest/ninja/sea/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestDeftestNinjaSea(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/deftest/ninja/sea/", noMoreUrl(), noQuery(), rule -> {
            ruleOfDeftestNinjaSea(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/deftest/ninja/sea/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfDeftestNinjaSea(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/job/sea/{stage}/{jobUnique}. (auto-generated method)<br>
     * <pre>
     * url: /wx/job/sea/{stage}/{jobUnique}
     * httpMethod: GET
     * </pre>
     * @param stage The value of path variable for stage. (NotNull)
     * @param jobUnique The value of path variable for jobUnique. (NotNull)
     */
    public void requestJobSea(String stage, String jobUnique) {
        doRequestJobSea(stage, jobUnique, rule -> {});
    }

    /**
     * Request remote call to /wx/job/sea/{stage}/{jobUnique}. (auto-generated method)<br>
     * <pre>
     * url: /wx/job/sea/{stage}/{jobUnique}
     * httpMethod: GET
     * </pre>
     * @param stage The value of path variable for stage. (NotNull)
     * @param jobUnique The value of path variable for jobUnique. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestJobSea(String stage, String jobUnique, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/job/sea/{stage}/{jobUnique}", moreUrl(stage, jobUnique), noQuery(), rule -> {
            ruleOfJobSeaStageJobUnique(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/job/sea/{stage}/{jobUnique}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfJobSeaStageJobUnique(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/job/amba/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/job/amba/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     */
    public void requestJobAmba(String first) {
        doRequestJobAmba(first, rule -> {});
    }

    /**
     * Request remote call to /wx/job/amba/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/job/amba/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestJobAmba(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/job/amba/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfJobAmbaFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/job/amba/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfJobAmbaFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/lastadoc/. (auto-generated method)<br>
     * <pre>
     * url: /wx/lastadoc/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxLastadocParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestLastadoc(Consumer<RemoteWxLastadocParam> paramLambda) {
        return doRequestLastadoc(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/lastadoc/. (auto-generated method)<br>
     * <pre>
     * url: /wx/lastadoc/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxLastadocParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestLastadoc(Consumer<RemoteWxLastadocParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxLastadocParam param = new RemoteWxLastadocParam();
        paramLambda.accept(param);
        return doRequestGet(String.class, "/wx/lastadoc/", noMoreUrl(), query(param), rule -> {
            ruleOfLastadoc(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/lastadoc/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfLastadoc(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/lastadoc/apiEmptyBody. (auto-generated method)<br>
     * <pre>
     * url: /wx/lastadoc/apiEmptyBody
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxLastadocApiemptybodyParam. (NotNull)
     */
    public void requestLastadocApiemptybody(Consumer<RemoteWxLastadocApiemptybodyParam> paramLambda) {
        doRequestLastadocApiemptybody(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/lastadoc/apiEmptyBody. (auto-generated method)<br>
     * <pre>
     * url: /wx/lastadoc/apiEmptyBody
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxLastadocApiemptybodyParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestLastadocApiemptybody(Consumer<RemoteWxLastadocApiemptybodyParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxLastadocApiemptybodyParam param = new RemoteWxLastadocApiemptybodyParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/wx/lastadoc/apiEmptyBody", noMoreUrl(), param, rule -> {
            ruleOfLastadocApiemptybody(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/lastadoc/apiEmptyBody.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfLastadocApiemptybody(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/login/surprised/. (auto-generated method)<br>
     * <pre>
     * url: /wx/login/surprised/
     * httpMethod: GET
     * </pre>
     */
    public void requestLoginSurprised() {
        doRequestLoginSurprised(rule -> {});
    }

    /**
     * Request remote call to /wx/login/surprised/. (auto-generated method)<br>
     * <pre>
     * url: /wx/login/surprised/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestLoginSurprised(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/login/surprised/", noMoreUrl(), noQuery(), rule -> {
            ruleOfLoginSurprised(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/login/surprised/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfLoginSurprised(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/login/surprised/signin. (auto-generated method)<br>
     * <pre>
     * url: /wx/login/surprised/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxLoginSurprisedSigninParam. (NotNull)
     */
    public void requestLoginSurprisedSignin(Consumer<RemoteWxLoginSurprisedSigninParam> paramLambda) {
        doRequestLoginSurprisedSignin(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/login/surprised/signin. (auto-generated method)<br>
     * <pre>
     * url: /wx/login/surprised/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxLoginSurprisedSigninParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestLoginSurprisedSignin(Consumer<RemoteWxLoginSurprisedSigninParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxLoginSurprisedSigninParam param = new RemoteWxLoginSurprisedSigninParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/login/surprised/signin", noMoreUrl(), query(param), rule -> {
            ruleOfLoginSurprisedSignin(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/login/surprised/signin.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfLoginSurprisedSignin(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/login/surprised/required/. (auto-generated method)<br>
     * <pre>
     * url: /wx/login/surprised/required/
     * httpMethod: GET
     * </pre>
     */
    public void requestLoginSurprisedRequired() {
        doRequestLoginSurprisedRequired(rule -> {});
    }

    /**
     * Request remote call to /wx/login/surprised/required/. (auto-generated method)<br>
     * <pre>
     * url: /wx/login/surprised/required/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestLoginSurprisedRequired(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/login/surprised/required/", noMoreUrl(), noQuery(), rule -> {
            ruleOfLoginSurprisedRequired(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/login/surprised/required/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfLoginSurprisedRequired(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/mail/. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestMail() {
        return doRequestMail(rule -> {});
    }

    /**
     * Request remote call to /wx/mail/. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestMail(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/mail/", noMoreUrl(), noQuery(), rule -> {
            ruleOfMail(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/mail/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMail(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/mail/basic. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/basic
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestMailBasic() {
        return doRequestMailBasic(rule -> {});
    }

    /**
     * Request remote call to /wx/mail/basic. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/basic
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestMailBasic(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/mail/basic", noMoreUrl(), noQuery(), rule -> {
            ruleOfMailBasic(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/mail/basic.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMailBasic(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/mail/entity. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/entity
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestMailEntity() {
        return doRequestMailEntity(rule -> {});
    }

    /**
     * Request remote call to /wx/mail/entity. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/entity
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestMailEntity(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/mail/entity", noMoreUrl(), noQuery(), rule -> {
            ruleOfMailEntity(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/mail/entity.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMailEntity(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/mail/entitylist. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/entitylist
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestMailEntitylist() {
        return doRequestMailEntitylist(rule -> {});
    }

    /**
     * Request remote call to /wx/mail/entitylist. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/entitylist
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestMailEntitylist(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/mail/entitylist", noMoreUrl(), noQuery(), rule -> {
            ruleOfMailEntitylist(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/mail/entitylist.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMailEntitylist(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/mail/loopbeans. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/loopbeans
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestMailLoopbeans() {
        return doRequestMailLoopbeans(rule -> {});
    }

    /**
     * Request remote call to /wx/mail/loopbeans. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/loopbeans
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestMailLoopbeans(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/mail/loopbeans", noMoreUrl(), noQuery(), rule -> {
            ruleOfMailLoopbeans(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/mail/loopbeans.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMailLoopbeans(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/mail/remoteapi/. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/remoteapi/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestMailRemoteapi() {
        return doRequestMailRemoteapi(rule -> {});
    }

    /**
     * Request remote call to /wx/mail/remoteapi/. (auto-generated method)<br>
     * <pre>
     * url: /wx/mail/remoteapi/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestMailRemoteapi(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/mail/remoteapi/", noMoreUrl(), noQuery(), rule -> {
            ruleOfMailRemoteapi(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/mail/remoteapi/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMailRemoteapi(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/message. (auto-generated method)<br>
     * <pre>
     * url: /wx/message
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxMessageParam. (NotNull)
     */
    public void requestMessage(Consumer<RemoteWxMessageParam> paramLambda) {
        doRequestMessage(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/message. (auto-generated method)<br>
     * <pre>
     * url: /wx/message
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxMessageParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestMessage(Consumer<RemoteWxMessageParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxMessageParam param = new RemoteWxMessageParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/message", noMoreUrl(), query(param), rule -> {
            ruleOfMessage(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/message.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMessage(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/message/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /wx/message/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteWxMessagePagenumberParam. (NotNull)
     */
    public void requestMessage(Integer pageNumber, Consumer<RemoteWxMessagePagenumberParam> paramLambda) {
        doRequestMessage(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/message/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /wx/message/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteWxMessagePagenumberParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestMessage(Integer pageNumber, Consumer<RemoteWxMessagePagenumberParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxMessagePagenumberParam param = new RemoteWxMessagePagenumberParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/message/{pageNumber}", moreUrl(pageNumber), query(param), rule -> {
            ruleOfMessagePageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/message/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMessagePageNumber(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/namedcls/. (auto-generated method)<br>
     * <pre>
     * url: /wx/namedcls/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxNamedclsParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxNamedclsReturn requestNamedcls(Consumer<RemoteWxNamedclsParam> paramLambda) {
        return doRequestNamedcls(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/namedcls/. (auto-generated method)<br>
     * <pre>
     * url: /wx/namedcls/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxNamedclsParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxNamedclsReturn doRequestNamedcls(Consumer<RemoteWxNamedclsParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxNamedclsParam param = new RemoteWxNamedclsParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxNamedclsReturn.class, "/wx/namedcls/", noMoreUrl(), query(param), rule -> {
            ruleOfNamedcls(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/namedcls/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfNamedcls(FlutyRemoteApiRule rule) {
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
     * @param bodyList The value of path variable for bodyList. (NotNull)
     * @param paramLambda The callback for RemoteWxRemogenBodyListParam. (NotNull)
     */
    public void requestRemogenBodyList(org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> bodyList, Consumer<RemoteWxRemogenBodyListParam> paramLambda) {
        doRequestRemogenBodyList(bodyList, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/body/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/body/list
     * httpMethod: POST
     * </pre>
     * @param bodyList The value of path variable for bodyList. (NotNull)
     * @param paramLambda The callback for RemoteWxRemogenBodyListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRemogenBodyList(org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> bodyList, Consumer<RemoteWxRemogenBodyListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenBodyListParam param = new RemoteWxRemogenBodyListParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/wx/remogen/body/list", moreUrl(bodyList), param, rule -> {
            ruleOfRemogenBodyListBodyList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/body/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenBodyListBodyList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/list/basic. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/list/basic
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListBasicReturn> doRequestRemogenListBasic(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListBasicReturn>>() {
        }.getType(), "/wx/remogen/list/basic", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenebeanReturn> doRequestRemogenListGenebean(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenebeanReturn>>() {
        }.getType(), "/wx/remogen/list/genebean", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenestringReturn> doRequestRemogenListGenestring(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListGenestringReturn>>() {
        }.getType(), "/wx/remogen/list/genestring", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListInnergeneReturn> doRequestRemogenListInnergene(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemogenListInnergeneReturn>>() {
        }.getType(), "/wx/remogen/list/innergene", noMoreUrl(), noQuery(), rule -> {
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
     * Request remote call to /wx/remogen/method/onbodyform. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/onbodyform
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodOnbodyformGetParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodOnbodyformGetReturn requestRemogenMethodOnbodyformGet(Consumer<RemoteWxRemogenMethodOnbodyformGetParam> paramLambda) {
        return doRequestRemogenMethodOnbodyformGet(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/onbodyform. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/onbodyform
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodOnbodyformGetParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodOnbodyformGetReturn doRequestRemogenMethodOnbodyformGet(Consumer<RemoteWxRemogenMethodOnbodyformGetParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodOnbodyformGetParam param = new RemoteWxRemogenMethodOnbodyformGetParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRemogenMethodOnbodyformGetReturn.class, "/wx/remogen/method/onbodyform", noMoreUrl(), query(param), rule -> {
            ruleOfRemogenMethodOnbodyformGet(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/onbodyform.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodOnbodyformGet(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/onbodyform. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/onbodyform
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodOnbodyformDeleteParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodOnbodyformDeleteReturn requestRemogenMethodOnbodyformDelete(Consumer<RemoteWxRemogenMethodOnbodyformDeleteParam> paramLambda) {
        return doRequestRemogenMethodOnbodyformDelete(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/onbodyform. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/onbodyform
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodOnbodyformDeleteParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodOnbodyformDeleteReturn doRequestRemogenMethodOnbodyformDelete(Consumer<RemoteWxRemogenMethodOnbodyformDeleteParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodOnbodyformDeleteParam param = new RemoteWxRemogenMethodOnbodyformDeleteParam();
        paramLambda.accept(param);
        return doRequestDelete(RemoteWxRemogenMethodOnbodyformDeleteReturn.class, "/wx/remogen/method/onbodyform", noMoreUrl(), query(param), rule -> {
            ruleOfRemogenMethodOnbodyformDelete(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/onbodyform.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodOnbodyformDelete(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/method/onbodyjson. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/onbodyjson
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodOnbodyjsonParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenMethodOnbodyjsonReturn requestRemogenMethodOnbodyjson(Consumer<RemoteWxRemogenMethodOnbodyjsonParam> paramLambda) {
        return doRequestRemogenMethodOnbodyjson(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/method/onbodyjson. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/method/onbodyjson
     * httpMethod: DELETE
     * </pre>
     * @param paramLambda The callback for RemoteWxRemogenMethodOnbodyjsonParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenMethodOnbodyjsonReturn doRequestRemogenMethodOnbodyjson(Consumer<RemoteWxRemogenMethodOnbodyjsonParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenMethodOnbodyjsonParam param = new RemoteWxRemogenMethodOnbodyjsonParam();
        paramLambda.accept(param);
        return doRequestDeleteEnclosing(RemoteWxRemogenMethodOnbodyjsonReturn.class, "/wx/remogen/method/onbodyjson", noMoreUrl(), param, rule -> {
            ruleOfRemogenMethodOnbodyjson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/method/onbodyjson.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenMethodOnbodyjson(FlutyRemoteApiRule rule) {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingReturn doRequestRemogenRouting(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingReturn.class, "/wx/remogen/routing/{first}", moreUrl(first), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingMaihamaReturn doRequestRemogenRoutingMaihama(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingMaihamaReturn.class, "/wx/remogen/routing/maihama", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingSeaReturn doRequestRemogenRoutingSea(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingSeaReturn.class, "/wx/remogen/routing/sea/{first}", moreUrl(first), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingLandReturn doRequestRemogenRoutingLand(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingLandReturn.class, "/wx/remogen/routing/land/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingPiariReturn doRequestRemogenRoutingPiari(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingPiariReturn.class, "/wx/remogen/routing/piari", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingPiariReturn doRequestRemogenRoutingPiari(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingPiariReturn.class, "/wx/remogen/routing/piari/{first}", moreUrl(first), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDstoreReturn doRequestRemogenRoutingDstore(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingDstoreReturn.class, "/wx/remogen/routing/dstore", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDstoreReturn doRequestRemogenRoutingDstore(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingDstoreReturn.class, "/wx/remogen/routing/dstore/{first}", moreUrl(first), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDstoreReturn doRequestRemogenRoutingDstore(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingDstoreReturn.class, "/wx/remogen/routing/dstore/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingBonvoReturn doRequestRemogenRoutingBonvo(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingBonvoReturn.class, "/wx/remogen/routing/bonvo/{first}", moreUrl(first), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingBonvoReturn doRequestRemogenRoutingBonvo(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingBonvoReturn.class, "/wx/remogen/routing/bonvo/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingAmbaReturn doRequestRemogenRoutingAmba(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingAmbaReturn.class, "/wx/remogen/routing/amba/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingAmbaReturn doRequestRemogenRoutingAmba(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingAmbaReturn.class, "/wx/remogen/routing/amba/{first}/{second}/{third}", moreUrl(first, second, third), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingMiracoReturn doRequestRemogenRoutingMiraco(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingMiracoReturn.class, "/wx/remogen/routing/miraco/{first}/{second}/{third}", moreUrl(first, second, third), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param fourth The value of path variable for fourth. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingMiracoReturn doRequestRemogenRoutingMiraco(String first, String second, String third, String fourth, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingMiracoReturn.class, "/wx/remogen/routing/miraco/{first}/{second}/{third}/{fourth}", moreUrl(first, second, third, fourth), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRemogenRoutingDohotelParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingDohotelReturn doRequestRemogenRoutingDohotel(String first, Consumer<RemoteWxRemogenRoutingDohotelParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemogenRoutingDohotelParam param = new RemoteWxRemogenRoutingDohotelParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRemogenRoutingDohotelReturn.class, "/wx/remogen/routing/dohotel/{first}", moreUrl(first), query(param), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
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
        return doRequestGet(RemoteWxRemogenRoutingDohotelReturn.class, "/wx/remogen/routing/dohotel/{first}/{second}", moreUrl(first, second), query(param), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingCelebReturn doRequestRemogenRoutingCeleb(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingCelebReturn.class, "/wx/remogen/routing/celeb/{first}", moreUrl(first), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingCelebReturn doRequestRemogenRoutingCeleb(Integer first, Long second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingCelebReturn.class, "/wx/remogen/routing/celeb/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingResolaReturn doRequestRemogenRoutingResola(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingResolaReturn.class, "/wx/remogen/routing/{first}/resola", moreUrl(first), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn doRequestRemogenRoutingAmphiTheaterMaihama(Integer first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenRoutingAmphiTheaterMaihamaReturn.class, "/wx/remogen/routing/amphi/{first}/theater/{second}/maihama", moreUrl(first, second), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixAllsuffixReturn doRequestRemogenSuffixAllsuffix(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenSuffixAllsuffixReturn.class, "/wx/remogen/suffix/allsuffix", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixNosuffixReturn doRequestRemogenSuffixNosuffix(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenSuffixNosuffixReturn.class, "/wx/remogen/suffix/nosuffix", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixPartonlyReturn doRequestRemogenSuffixPartonly(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenSuffixPartonlyReturn.class, "/wx/remogen/suffix/partonly", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenSuffixToponlyReturn doRequestRemogenSuffixToponly(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenSuffixToponlyReturn.class, "/wx/remogen/suffix/toponly", noMoreUrl(), noQuery(), rule -> {
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
     * Request remote call to /wx/remogen/tricky/allnone. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/allnone
     * httpMethod: GET
     * </pre>
     */
    public void requestRemogenTrickyAllnone() {
        doRequestRemogenTrickyAllnone(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/tricky/allnone. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/allnone
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRemogenTrickyAllnone(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/remogen/tricky/allnone", noMoreUrl(), noQuery(), rule -> {
            ruleOfRemogenTrickyAllnone(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/tricky/allnone.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenTrickyAllnone(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remogen/tricky/nobody. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/nobody
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickyNobodyReturn doRequestRemogenTrickyNobody(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenTrickyNobodyReturn.class, "/wx/remogen/tricky/nobody", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickyOddpropReturn doRequestRemogenTrickyOddprop(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenTrickyOddpropReturn.class, "/wx/remogen/tricky/oddprop", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickyRecycleReturn doRequestRemogenTrickyRecycle(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenTrickyRecycleReturn.class, "/wx/remogen/tricky/recycle", noMoreUrl(), noQuery(), rule -> {
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
     * httpMethod: GET
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
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickySelfrefReturn doRequestRemogenTrickySelfref(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenTrickySelfrefReturn.class, "/wx/remogen/tricky/selfref", noMoreUrl(), noQuery(), rule -> {
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

    /**
     * Request remote call to /wx/remogen/tricky/suffixhell. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/suffixhell
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemogenTrickySuffixhellReturn requestRemogenTrickySuffixhell() {
        return doRequestRemogenTrickySuffixhell(rule -> {});
    }

    /**
     * Request remote call to /wx/remogen/tricky/suffixhell. (auto-generated method)<br>
     * <pre>
     * url: /wx/remogen/tricky/suffixhell
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemogenTrickySuffixhellReturn doRequestRemogenTrickySuffixhell(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemogenTrickySuffixhellReturn.class, "/wx/remogen/tricky/suffixhell", noMoreUrl(), noQuery(), rule -> {
            ruleOfRemogenTrickySuffixhell(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remogen/tricky/suffixhell.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemogenTrickySuffixhell(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmhangar/mypage. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmhangar/mypage
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemoteapiRmhangarMypageReturn requestRemoteapiRmhangarMypage() {
        return doRequestRemoteapiRmhangarMypage(rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmhangar/mypage. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmhangar/mypage
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemoteapiRmhangarMypageReturn doRequestRemoteapiRmhangarMypage(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemoteapiRmhangarMypageReturn.class, "/wx/remoteapi/rmhangar/mypage", noMoreUrl(), noQuery(), rule -> {
            ruleOfRemoteapiRmhangarMypage(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmhangar/mypage.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmhangarMypage(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmhangar/translate. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmhangar/translate
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmhangarTranslateParam. (NotNull)
     */
    public void requestRemoteapiRmhangarTranslate(Consumer<RemoteWxRemoteapiRmhangarTranslateParam> paramLambda) {
        doRequestRemoteapiRmhangarTranslate(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmhangar/translate. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmhangar/translate
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmhangarTranslateParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRemoteapiRmhangarTranslate(Consumer<RemoteWxRemoteapiRmhangarTranslateParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemoteapiRmhangarTranslateParam param = new RemoteWxRemoteapiRmhangarTranslateParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/remoteapi/rmhangar/translate", noMoreUrl(), query(param), rule -> {
            ruleOfRemoteapiRmhangarTranslate(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmhangar/translate.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmhangarTranslate(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/lido/signin. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/lido/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmharborLidoSigninParam. (NotNull)
     */
    public void requestRemoteapiRmharborLidoSignin(Consumer<RemoteWxRemoteapiRmharborLidoSigninParam> paramLambda) {
        doRequestRemoteapiRmharborLidoSignin(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/lido/signin. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/lido/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmharborLidoSigninParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRemoteapiRmharborLidoSignin(Consumer<RemoteWxRemoteapiRmharborLidoSigninParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemoteapiRmharborLidoSigninParam param = new RemoteWxRemoteapiRmharborLidoSigninParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/remoteapi/rmharbor/lido/signin", noMoreUrl(), query(param), rule -> {
            ruleOfRemoteapiRmharborLidoSignin(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmharbor/lido/signin.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmharborLidoSignin(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/lido/mypage. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/lido/mypage
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteWxRemoteapiRmharborLidoMypageReturn> requestRemoteapiRmharborLidoMypage() {
        return doRequestRemoteapiRmharborLidoMypage(rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/lido/mypage. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/lido/mypage
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemoteapiRmharborLidoMypageReturn> doRequestRemoteapiRmharborLidoMypage(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemoteapiRmharborLidoMypageReturn>>() {
        }.getType(), "/wx/remoteapi/rmharbor/lido/mypage", noMoreUrl(), noQuery(), rule -> {
            ruleOfRemoteapiRmharborLidoMypage(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmharbor/lido/mypage.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmharborLidoMypage(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/serh/product. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/serh/product
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmharborSerhProductParam. (NotNull)
     */
    public void requestRemoteapiRmharborSerhProduct(Consumer<RemoteWxRemoteapiRmharborSerhProductParam> paramLambda) {
        doRequestRemoteapiRmharborSerhProduct(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/serh/product. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/serh/product
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmharborSerhProductParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRemoteapiRmharborSerhProduct(Consumer<RemoteWxRemoteapiRmharborSerhProductParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemoteapiRmharborSerhProductParam param = new RemoteWxRemoteapiRmharborSerhProductParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/remoteapi/rmharbor/serh/product", noMoreUrl(), query(param), rule -> {
            ruleOfRemoteapiRmharborSerhProduct(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmharbor/serh/product.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmharborSerhProduct(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/serh/signin. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/serh/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmharborSerhSigninParam. (NotNull)
     */
    public void requestRemoteapiRmharborSerhSignin(Consumer<RemoteWxRemoteapiRmharborSerhSigninParam> paramLambda) {
        doRequestRemoteapiRmharborSerhSignin(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/serh/signin. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/serh/signin
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRemoteapiRmharborSerhSigninParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRemoteapiRmharborSerhSignin(Consumer<RemoteWxRemoteapiRmharborSerhSigninParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRemoteapiRmharborSerhSigninParam param = new RemoteWxRemoteapiRmharborSerhSigninParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/remoteapi/rmharbor/serh/signin", noMoreUrl(), query(param), rule -> {
            ruleOfRemoteapiRmharborSerhSignin(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmharbor/serh/signin.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmharborSerhSignin(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/via/mypage. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/via/mypage
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteWxRemoteapiRmharborViaMypageReturn> requestRemoteapiRmharborViaMypage() {
        return doRequestRemoteapiRmharborViaMypage(rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmharbor/via/mypage. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmharbor/via/mypage
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxRemoteapiRmharborViaMypageReturn> doRequestRemoteapiRmharborViaMypage(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxRemoteapiRmharborViaMypageReturn>>() {
        }.getType(), "/wx/remoteapi/rmharbor/via/mypage", noMoreUrl(), noQuery(), rule -> {
            ruleOfRemoteapiRmharborViaMypage(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmharbor/via/mypage.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmharborViaMypage(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/remoteapi/rmshowbase/remogen/resola. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmshowbase/remogen/resola
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRemoteapiRmshowbaseRemogenResolaReturn requestRemoteapiRmshowbaseRemogenResola() {
        return doRequestRemoteapiRmshowbaseRemogenResola(rule -> {});
    }

    /**
     * Request remote call to /wx/remoteapi/rmshowbase/remogen/resola. (auto-generated method)<br>
     * <pre>
     * url: /wx/remoteapi/rmshowbase/remogen/resola
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRemoteapiRmshowbaseRemogenResolaReturn doRequestRemoteapiRmshowbaseRemogenResola(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRemoteapiRmshowbaseRemogenResolaReturn.class, "/wx/remoteapi/rmshowbase/remogen/resola", noMoreUrl(), noQuery(), rule -> {
            ruleOfRemoteapiRmshowbaseRemogenResola(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/remoteapi/rmshowbase/remogen/resola.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRemoteapiRmshowbaseRemogenResola(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/rmshowbase/remogen/method/delete. (auto-generated method)<br>
     * <pre>
     * url: /wx/rmshowbase/remogen/method/delete
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRmshowbaseRemogenMethodDeleteReturn requestRmshowbaseRemogenMethodDelete() {
        return doRequestRmshowbaseRemogenMethodDelete(rule -> {});
    }

    /**
     * Request remote call to /wx/rmshowbase/remogen/method/delete. (auto-generated method)<br>
     * <pre>
     * url: /wx/rmshowbase/remogen/method/delete
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRmshowbaseRemogenMethodDeleteReturn doRequestRmshowbaseRemogenMethodDelete(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRmshowbaseRemogenMethodDeleteReturn.class, "/wx/rmshowbase/remogen/method/delete", noMoreUrl(), noQuery(), rule -> {
            ruleOfRmshowbaseRemogenMethodDelete(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/rmshowbase/remogen/method/delete.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRmshowbaseRemogenMethodDelete(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/form/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/form/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestFormParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestFormReturn requestRequestForm(Consumer<RemoteWxRequestFormParam> paramLambda) {
        return doRequestRequestForm(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/form/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/form/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestFormParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestFormReturn doRequestRequestForm(Consumer<RemoteWxRequestFormParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestFormParam param = new RemoteWxRequestFormParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRequestFormReturn.class, "/wx/request/form/", noMoreUrl(), query(param), rule -> {
            ruleOfRequestForm(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/form/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestForm(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/form/eccolle. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/form/eccolle
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestFormEccolleParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestFormEccolleReturn requestRequestFormEccolle(Consumer<RemoteWxRequestFormEccolleParam> paramLambda) {
        return doRequestRequestFormEccolle(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/form/eccolle. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/form/eccolle
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestFormEccolleParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestFormEccolleReturn doRequestRequestFormEccolle(Consumer<RemoteWxRequestFormEccolleParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestFormEccolleParam param = new RemoteWxRequestFormEccolleParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRequestFormEccolleReturn.class, "/wx/request/form/eccolle", noMoreUrl(), query(param), rule -> {
            ruleOfRequestFormEccolle(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/form/eccolle.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestFormEccolle(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/form/swagger/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/form/swagger/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestFormSwaggerParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestFormSwaggerReturn requestRequestFormSwagger(Consumer<RemoteWxRequestFormSwaggerParam> paramLambda) {
        return doRequestRequestFormSwagger(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/form/swagger/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/form/swagger/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestFormSwaggerParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestFormSwaggerReturn doRequestRequestFormSwagger(Consumer<RemoteWxRequestFormSwaggerParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestFormSwaggerParam param = new RemoteWxRequestFormSwaggerParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRequestFormSwaggerReturn.class, "/wx/request/form/swagger/", noMoreUrl(), query(param), rule -> {
            ruleOfRequestFormSwagger(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/form/swagger/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestFormSwagger(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBody(Consumer<RemoteWxRequestJsonBodyParam> paramLambda) {
        return doRequestRequestJsonBody(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBody(Consumer<RemoteWxRequestJsonBodyParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodyParam param = new RemoteWxRequestJsonBodyParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/", noMoreUrl(), param, rule -> {
            ruleOfRequestJsonBody(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBody(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/list
     * httpMethod: POST
     * </pre>
     * @param body The value of path variable for body. (NotNull)
     * @param paramLambda The callback for RemoteWxRequestJsonBodyListParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBodyList(org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> body, Consumer<RemoteWxRequestJsonBodyListParam> paramLambda) {
        return doRequestRequestJsonBodyList(body, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/list
     * httpMethod: POST
     * </pre>
     * @param body The value of path variable for body. (NotNull)
     * @param paramLambda The callback for RemoteWxRequestJsonBodyListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBodyList(org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> body, Consumer<RemoteWxRequestJsonBodyListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodyListParam param = new RemoteWxRequestJsonBodyListParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/list", moreUrl(body), param, rule -> {
            ruleOfRequestJsonBodyListBody(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBodyListBody(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/clienterror. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/clienterror
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyClienterrorParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBodyClienterror(Consumer<RemoteWxRequestJsonBodyClienterrorParam> paramLambda) {
        return doRequestRequestJsonBodyClienterror(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/clienterror. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/clienterror
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyClienterrorParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBodyClienterror(Consumer<RemoteWxRequestJsonBodyClienterrorParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodyClienterrorParam param = new RemoteWxRequestJsonBodyClienterrorParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/clienterror", noMoreUrl(), param, rule -> {
            ruleOfRequestJsonBodyClienterror(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/clienterror.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBodyClienterror(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/systemerror. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/systemerror
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodySystemerrorParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBodySystemerror(Consumer<RemoteWxRequestJsonBodySystemerrorParam> paramLambda) {
        return doRequestRequestJsonBodySystemerror(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/systemerror. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/systemerror
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodySystemerrorParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBodySystemerror(Consumer<RemoteWxRequestJsonBodySystemerrorParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodySystemerrorParam param = new RemoteWxRequestJsonBodySystemerrorParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/systemerror", noMoreUrl(), param, rule -> {
            ruleOfRequestJsonBodySystemerror(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/systemerror.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBodySystemerror(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/validated. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/validated
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyValidatedParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBodyValidated(Consumer<RemoteWxRequestJsonBodyValidatedParam> paramLambda) {
        return doRequestRequestJsonBodyValidated(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/validated. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/validated
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyValidatedParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBodyValidated(Consumer<RemoteWxRequestJsonBodyValidatedParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodyValidatedParam param = new RemoteWxRequestJsonBodyValidatedParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/validated", noMoreUrl(), param, rule -> {
            ruleOfRequestJsonBodyValidated(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/validated.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBodyValidated(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/validatedlonely. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/validatedlonely
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyValidatedlonelyParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBodyValidatedlonely(Consumer<RemoteWxRequestJsonBodyValidatedlonelyParam> paramLambda) {
        return doRequestRequestJsonBodyValidatedlonely(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/validatedlonely. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/validatedlonely
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodyValidatedlonelyParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBodyValidatedlonely(Consumer<RemoteWxRequestJsonBodyValidatedlonelyParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodyValidatedlonelyParam param = new RemoteWxRequestJsonBodyValidatedlonelyParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/validatedlonely", noMoreUrl(), param, rule -> {
            ruleOfRequestJsonBodyValidatedlonely(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/validatedlonely.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBodyValidatedlonely(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/validatedlonelylist. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/validatedlonelylist
     * httpMethod: POST
     * </pre>
     * @param bodyList The value of path variable for bodyList. (NotNull)
     * @param paramLambda The callback for RemoteWxRequestJsonBodyValidatedlonelylistParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBodyValidatedlonelylist(org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> bodyList, Consumer<RemoteWxRequestJsonBodyValidatedlonelylistParam> paramLambda) {
        return doRequestRequestJsonBodyValidatedlonelylist(bodyList, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/validatedlonelylist. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/validatedlonelylist
     * httpMethod: POST
     * </pre>
     * @param bodyList The value of path variable for bodyList. (NotNull)
     * @param paramLambda The callback for RemoteWxRequestJsonBodyValidatedlonelylistParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBodyValidatedlonelylist(org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> bodyList, Consumer<RemoteWxRequestJsonBodyValidatedlonelylistParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodyValidatedlonelylistParam param = new RemoteWxRequestJsonBodyValidatedlonelylistParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/validatedlonelylist", moreUrl(bodyList), param, rule -> {
            ruleOfRequestJsonBodyValidatedlonelylistBodyList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/validatedlonelylist.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBodyValidatedlonelylistBodyList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/body/swagger/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/swagger/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodySwaggerParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonBodySwagger(Consumer<RemoteWxRequestJsonBodySwaggerParam> paramLambda) {
        return doRequestRequestJsonBodySwagger(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/body/swagger/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/body/swagger/
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonBodySwaggerParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonBodySwagger(Consumer<RemoteWxRequestJsonBodySwaggerParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonBodySwaggerParam param = new RemoteWxRequestJsonBodySwaggerParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/body/swagger/", noMoreUrl(), param, rule -> {
            ruleOfRequestJsonBodySwagger(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/body/swagger/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonBodySwagger(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/prop/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/prop/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonPropParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRequestJsonProp(Consumer<RemoteWxRequestJsonPropParam> paramLambda) {
        return doRequestRequestJsonProp(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/prop/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/prop/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonPropParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRequestJsonProp(Consumer<RemoteWxRequestJsonPropParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonPropParam param = new RemoteWxRequestJsonPropParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/request/json/prop/", noMoreUrl(), query(param), rule -> {
            ruleOfRequestJsonProp(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/prop/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonProp(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/json/prop/json. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/prop/json
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonPropJsonParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestJsonPropJsonReturn requestRequestJsonPropJson(Consumer<RemoteWxRequestJsonPropJsonParam> paramLambda) {
        return doRequestRequestJsonPropJson(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/json/prop/json. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/json/prop/json
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestJsonPropJsonParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestJsonPropJsonReturn doRequestRequestJsonPropJson(Consumer<RemoteWxRequestJsonPropJsonParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestJsonPropJsonParam param = new RemoteWxRequestJsonPropJsonParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRequestJsonPropJsonReturn.class, "/wx/request/json/prop/json", noMoreUrl(), query(param), rule -> {
            ruleOfRequestJsonPropJson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/json/prop/json.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestJsonPropJson(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/multipart/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/multipart/
     * httpMethod: GET
     * </pre>
     */
    public void requestRequestMultipart() {
        doRequestRequestMultipart(rule -> {});
    }

    /**
     * Request remote call to /wx/request/multipart/. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/multipart/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRequestMultipart(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/request/multipart/", noMoreUrl(), noQuery(), rule -> {
            ruleOfRequestMultipart(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/multipart/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestMultipart(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/multipart/upload. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/multipart/upload
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestMultipartUploadParam. (NotNull)
     */
    public void requestRequestMultipartUpload(Consumer<RemoteWxRequestMultipartUploadParam> paramLambda) {
        doRequestRequestMultipartUpload(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/request/multipart/upload. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/multipart/upload
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRequestMultipartUploadParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRequestMultipartUpload(Consumer<RemoteWxRequestMultipartUploadParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRequestMultipartUploadParam param = new RemoteWxRequestMultipartUploadParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/request/multipart/upload", noMoreUrl(), query(param), rule -> {
            ruleOfRequestMultipartUpload(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/multipart/upload.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestMultipartUpload(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/pathvar/string/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/string/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestPathvarStringReturn requestRequestPathvarString(String first) {
        return doRequestRequestPathvarString(first, rule -> {});
    }

    /**
     * Request remote call to /wx/request/pathvar/string/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/string/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestPathvarStringReturn doRequestRequestPathvarString(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRequestPathvarStringReturn.class, "/wx/request/pathvar/string/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRequestPathvarStringFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/pathvar/string/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestPathvarStringFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/pathvar/cls/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/cls/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestPathvarClsReturn requestRequestPathvarCls(String first) {
        return doRequestRequestPathvarCls(first, rule -> {});
    }

    /**
     * Request remote call to /wx/request/pathvar/cls/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/cls/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestPathvarClsReturn doRequestRequestPathvarCls(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRequestPathvarClsReturn.class, "/wx/request/pathvar/cls/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRequestPathvarClsFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/pathvar/cls/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestPathvarClsFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/pathvar/nameof/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/nameof/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[ONE, MIN]) ( * `ONE` - OneMan, ShowBase. * `MIN` - MiniO, Orlean. :: fromCls(AppCDef$AppWxNameOf)) (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestPathvarNameofReturn requestRequestPathvarNameof(String first) {
        return doRequestRequestPathvarNameof(first, rule -> {});
    }

    /**
     * Request remote call to /wx/request/pathvar/nameof/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/nameof/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[ONE, MIN]) ( * `ONE` - OneMan, ShowBase. * `MIN` - MiniO, Orlean. :: fromCls(AppCDef$AppWxNameOf)) (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestPathvarNameofReturn doRequestRequestPathvarNameof(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRequestPathvarNameofReturn.class, "/wx/request/pathvar/nameof/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRequestPathvarNameofFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/pathvar/nameof/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestPathvarNameofFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestPathvarOptionalOnparadeReturn requestRequestPathvarOptionalOnparade() {
        return doRequestRequestPathvarOptionalOnparade(rule -> {});
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestPathvarOptionalOnparadeReturn doRequestRequestPathvarOptionalOnparade(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRequestPathvarOptionalOnparadeReturn.class, "/wx/request/pathvar/optional/onparade", noMoreUrl(), noQuery(), rule -> {
            ruleOfRequestPathvarOptionalOnparade(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/pathvar/optional/onparade.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestPathvarOptionalOnparade(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestPathvarOptionalOnparadeReturn requestRequestPathvarOptionalOnparade(String first) {
        return doRequestRequestPathvarOptionalOnparade(first, rule -> {});
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestPathvarOptionalOnparadeReturn doRequestRequestPathvarOptionalOnparade(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRequestPathvarOptionalOnparadeReturn.class, "/wx/request/pathvar/optional/onparade/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRequestPathvarOptionalOnparadeFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/pathvar/optional/onparade/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestPathvarOptionalOnparadeFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestPathvarOptionalOnparadeReturn requestRequestPathvarOptionalOnparade(String first, String second) {
        return doRequestRequestPathvarOptionalOnparade(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestPathvarOptionalOnparadeReturn doRequestRequestPathvarOptionalOnparade(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRequestPathvarOptionalOnparadeReturn.class, "/wx/request/pathvar/optional/onparade/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRequestPathvarOptionalOnparadeFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/pathvar/optional/onparade/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestPathvarOptionalOnparadeFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade/{first}/{second}/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRequestPathvarOptionalOnparadeReturn requestRequestPathvarOptionalOnparade(String first, String second, String third) {
        return doRequestRequestPathvarOptionalOnparade(first, second, third, rule -> {});
    }

    /**
     * Request remote call to /wx/request/pathvar/optional/onparade/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/request/pathvar/optional/onparade/{first}/{second}/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRequestPathvarOptionalOnparadeReturn doRequestRequestPathvarOptionalOnparade(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRequestPathvarOptionalOnparadeReturn.class, "/wx/request/pathvar/optional/onparade/{first}/{second}/{third}", moreUrl(first, second, third), noQuery(), rule -> {
            ruleOfRequestPathvarOptionalOnparadeFirstSecondThird(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/request/pathvar/optional/onparade/{first}/{second}/{third}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRequestPathvarOptionalOnparadeFirstSecondThird(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/empty/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/empty/
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseHtmlEmpty() {
        doRequestResponseHtmlEmpty(rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/empty/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/empty/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlEmpty(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/html/empty/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseHtmlEmpty(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/empty/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlEmpty(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/empty/committed. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/empty/committed
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseHtmlEmptyCommitted() {
        doRequestResponseHtmlEmptyCommitted(rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/empty/committed. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/empty/committed
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlEmptyCommitted(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/html/empty/committed", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseHtmlEmptyCommitted(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/empty/committed.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlEmptyCommitted(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/add/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/add/
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseHtmlAdd() {
        doRequestResponseHtmlAdd(rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/add/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/add/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlAdd(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/html/add/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseHtmlAdd(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/add/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlAdd(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/add/register. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/add/register
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlAddRegisterParam. (NotNull)
     */
    public void requestResponseHtmlAddRegister(Consumer<RemoteWxResponseHtmlAddRegisterParam> paramLambda) {
        doRequestResponseHtmlAddRegister(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/add/register. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/add/register
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlAddRegisterParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlAddRegister(Consumer<RemoteWxResponseHtmlAddRegisterParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxResponseHtmlAddRegisterParam param = new RemoteWxResponseHtmlAddRegisterParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/response/html/add/register", noMoreUrl(), query(param), rule -> {
            ruleOfResponseHtmlAddRegister(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/add/register.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlAddRegister(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/edit/{memberId}. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/edit/{memberId}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     */
    public void requestResponseHtmlEdit(Integer memberId) {
        doRequestResponseHtmlEdit(memberId, rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/edit/{memberId}. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/edit/{memberId}
     * httpMethod: GET
     * </pre>
     * @param memberId The value of path variable for memberId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlEdit(Integer memberId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/html/edit/{memberId}", moreUrl(memberId), noQuery(), rule -> {
            ruleOfResponseHtmlEditMemberId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/edit/{memberId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlEditMemberId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/edit/update. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/edit/update
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlEditUpdateParam. (NotNull)
     */
    public void requestResponseHtmlEditUpdate(Consumer<RemoteWxResponseHtmlEditUpdateParam> paramLambda) {
        doRequestResponseHtmlEditUpdate(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/edit/update. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/edit/update
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlEditUpdateParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlEditUpdate(Consumer<RemoteWxResponseHtmlEditUpdateParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxResponseHtmlEditUpdateParam param = new RemoteWxResponseHtmlEditUpdateParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/response/html/edit/update", noMoreUrl(), query(param), rule -> {
            ruleOfResponseHtmlEditUpdate(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/edit/update.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlEditUpdate(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/edit/withdrawal. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/edit/withdrawal
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlEditWithdrawalParam. (NotNull)
     */
    public void requestResponseHtmlEditWithdrawal(Consumer<RemoteWxResponseHtmlEditWithdrawalParam> paramLambda) {
        doRequestResponseHtmlEditWithdrawal(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/edit/withdrawal. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/edit/withdrawal
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlEditWithdrawalParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlEditWithdrawal(Consumer<RemoteWxResponseHtmlEditWithdrawalParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxResponseHtmlEditWithdrawalParam param = new RemoteWxResponseHtmlEditWithdrawalParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/response/html/edit/withdrawal", noMoreUrl(), query(param), rule -> {
            ruleOfResponseHtmlEditWithdrawal(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/edit/withdrawal.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlEditWithdrawal(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlListParam. (NotNull)
     */
    public void requestResponseHtmlList(Consumer<RemoteWxResponseHtmlListParam> paramLambda) {
        doRequestResponseHtmlList(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseHtmlListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlList(Consumer<RemoteWxResponseHtmlListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxResponseHtmlListParam param = new RemoteWxResponseHtmlListParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/response/html/list", noMoreUrl(), query(param), rule -> {
            ruleOfResponseHtmlList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/html/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/list/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteWxResponseHtmlListPagenumberParam. (NotNull)
     */
    public void requestResponseHtmlList(Integer pageNumber, Consumer<RemoteWxResponseHtmlListPagenumberParam> paramLambda) {
        doRequestResponseHtmlList(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/response/html/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/html/list/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteWxResponseHtmlListPagenumberParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseHtmlList(Integer pageNumber, Consumer<RemoteWxResponseHtmlListPagenumberParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxResponseHtmlListPagenumberParam param = new RemoteWxResponseHtmlListPagenumberParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/response/html/list/{pageNumber}", moreUrl(pageNumber), query(param), rule -> {
            ruleOfResponseHtmlListPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/html/list/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseHtmlListPageNumber(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonReturn requestResponseJson() {
        return doRequestResponseJson(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonReturn doRequestResponseJson(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonReturn.class, "/wx/response/json/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJson(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/emptybody. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/emptybody
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseJsonEmptybody() {
        doRequestResponseJsonEmptybody(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/emptybody. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/emptybody
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseJsonEmptybody(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/json/emptybody", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonEmptybody(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/emptybody.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonEmptybody(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/strval. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/strval
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestResponseJsonStrval() {
        return doRequestResponseJsonStrval(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/strval. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/strval
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestResponseJsonStrval(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/response/json/strval", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonStrval(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/strval.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonStrval(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/intval. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/intval
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public Integer requestResponseJsonIntval() {
        return doRequestResponseJsonIntval(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/intval. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/intval
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected Integer doRequestResponseJsonIntval(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(Integer.class, "/wx/response/json/intval", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonIntval(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/intval.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonIntval(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/directly/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/directly/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public String requestResponseJsonDirectly() {
        return doRequestResponseJsonDirectly(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/directly/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/directly/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected String doRequestResponseJsonDirectly(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(String.class, "/wx/response/json/directly/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonDirectly(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/directly/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonDirectly(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/eccolle/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/eccolle/list
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonEccolleListReturn requestResponseJsonEccolleList() {
        return doRequestResponseJsonEccolleList(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/eccolle/list. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/eccolle/list
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonEccolleListReturn doRequestResponseJsonEccolleList(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonEccolleListReturn.class, "/wx/response/json/eccolle/list", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonEccolleList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/eccolle/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonEccolleList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/eccolle/listempty. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/eccolle/listempty
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonEccolleListemptyReturn requestResponseJsonEccolleListempty() {
        return doRequestResponseJsonEccolleListempty(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/eccolle/listempty. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/eccolle/listempty
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonEccolleListemptyReturn doRequestResponseJsonEccolleListempty(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonEccolleListemptyReturn.class, "/wx/response/json/eccolle/listempty", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonEccolleListempty(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/eccolle/listempty.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonEccolleListempty(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/eccolle/map. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/eccolle/map
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonEccolleMapReturn requestResponseJsonEccolleMap() {
        return doRequestResponseJsonEccolleMap(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/eccolle/map. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/eccolle/map
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonEccolleMapReturn doRequestResponseJsonEccolleMap(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonEccolleMapReturn.class, "/wx/response/json/eccolle/map", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonEccolleMap(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/eccolle/map.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonEccolleMap(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/justified/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/justified/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonJustifiedReturn requestResponseJsonJustified() {
        return doRequestResponseJsonJustified(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/justified/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/justified/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonJustifiedReturn doRequestResponseJsonJustified(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonJustifiedReturn.class, "/wx/response/json/justified/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonJustified(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/justified/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonJustified(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/justified/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/justified/named
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonJustifiedNamedReturn requestResponseJsonJustifiedNamed() {
        return doRequestResponseJsonJustifiedNamed(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/justified/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/justified/named
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonJustifiedNamedReturn doRequestResponseJsonJustifiedNamed(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonJustifiedNamedReturn.class, "/wx/response/json/justified/named", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonJustifiedNamed(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/justified/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonJustifiedNamed(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/switched/engine. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/switched/engine
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonSwitchedEngineReturn requestResponseJsonSwitchedEngine() {
        return doRequestResponseJsonSwitchedEngine(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/switched/engine. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/switched/engine
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonSwitchedEngineReturn doRequestResponseJsonSwitchedEngine(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonSwitchedEngineReturn.class, "/wx/response/json/switched/engine", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonSwitchedEngine(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/switched/engine.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonSwitchedEngine(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/switched/mapping. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/switched/mapping
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonSwitchedMappingReturn requestResponseJsonSwitchedMapping() {
        return doRequestResponseJsonSwitchedMapping(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/switched/mapping. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/switched/mapping
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonSwitchedMappingReturn doRequestResponseJsonSwitchedMapping(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonSwitchedMappingReturn.class, "/wx/response/json/switched/mapping", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonSwitchedMapping(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/switched/mapping.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonSwitchedMapping(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/json/switched/none. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/switched/none
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxResponseJsonSwitchedNoneReturn requestResponseJsonSwitchedNone() {
        return doRequestResponseJsonSwitchedNone(rule -> {});
    }

    /**
     * Request remote call to /wx/response/json/switched/none. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/json/switched/none
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxResponseJsonSwitchedNoneReturn doRequestResponseJsonSwitchedNone(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxResponseJsonSwitchedNoneReturn.class, "/wx/response/json/switched/none", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseJsonSwitchedNone(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/json/switched/none.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseJsonSwitchedNone(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/small. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/small
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamSmall() {
        doRequestResponseStreamSmall(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/small. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/small
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamSmall(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/small", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamSmall(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/small.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamSmall(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/large. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/large
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamLarge() {
        doRequestResponseStreamLarge(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/large. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/large
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamLarge(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/large", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamLarge(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/large.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamLarge(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/output. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/output
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamOutput() {
        doRequestResponseStreamOutput(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/output. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/output
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamOutput(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/output", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamOutput(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/output.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamOutput(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/cursortsv. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/cursortsv
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamCursortsv() {
        doRequestResponseStreamCursortsv(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/cursortsv. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/cursortsv
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamCursortsv(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/cursortsv", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamCursortsv(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/cursortsv.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamCursortsv(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/updateplain. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/updateplain
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamUpdateplain() {
        doRequestResponseStreamUpdateplain(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/updateplain. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/updateplain
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamUpdateplain(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/updateplain", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamUpdateplain(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/updateplain.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamUpdateplain(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/updatetx. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/updatetx
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamUpdatetx() {
        doRequestResponseStreamUpdatetx(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/updatetx. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/updatetx
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamUpdatetx(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/updatetx", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamUpdatetx(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/updatetx.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamUpdatetx(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/japanese. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/japanese
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamJapanese() {
        doRequestResponseStreamJapanese(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/japanese. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/japanese
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamJapanese(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/japanese", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamJapanese(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/japanese.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamJapanese(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/validationerror. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/validationerror
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseStreamValidationerrorParam. (NotNull)
     */
    public void requestResponseStreamValidationerror(Consumer<RemoteWxResponseStreamValidationerrorParam> paramLambda) {
        doRequestResponseStreamValidationerror(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/validationerror. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/validationerror
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseStreamValidationerrorParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamValidationerror(Consumer<RemoteWxResponseStreamValidationerrorParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxResponseStreamValidationerrorParam param = new RemoteWxResponseStreamValidationerrorParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/response/stream/validationerror", noMoreUrl(), query(param), rule -> {
            ruleOfResponseStreamValidationerror(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/validationerror.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamValidationerror(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/withbizex. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/withbizex
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamWithbizex() {
        doRequestResponseStreamWithbizex(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/withbizex. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/withbizex
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamWithbizex(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/withbizex", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamWithbizex(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/withbizex.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamWithbizex(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/withbizexapi. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/withbizexapi
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamWithbizexapi() {
        doRequestResponseStreamWithbizexapi(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/withbizexapi. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/withbizexapi
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamWithbizexapi(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/withbizexapi", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamWithbizexapi(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/withbizexapi.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamWithbizexapi(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/api/validationerror. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/api/validationerror
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseStreamApiValidationerrorParam. (NotNull)
     */
    public void requestResponseStreamApiValidationerror(Consumer<RemoteWxResponseStreamApiValidationerrorParam> paramLambda) {
        doRequestResponseStreamApiValidationerror(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/api/validationerror. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/api/validationerror
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxResponseStreamApiValidationerrorParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamApiValidationerror(Consumer<RemoteWxResponseStreamApiValidationerrorParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxResponseStreamApiValidationerrorParam param = new RemoteWxResponseStreamApiValidationerrorParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/response/stream/api/validationerror", noMoreUrl(), query(param), rule -> {
            ruleOfResponseStreamApiValidationerror(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/api/validationerror.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamApiValidationerror(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/stream/api/withbizex. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/api/withbizex
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseStreamApiWithbizex() {
        doRequestResponseStreamApiWithbizex(rule -> {});
    }

    /**
     * Request remote call to /wx/response/stream/api/withbizex. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/stream/api/withbizex
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseStreamApiWithbizex(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/stream/api/withbizex", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseStreamApiWithbizex(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/stream/api/withbizex.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseStreamApiWithbizex(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/transition/forward/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/forward/
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseTransitionForward() {
        doRequestResponseTransitionForward(rule -> {});
    }

    /**
     * Request remote call to /wx/response/transition/forward/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/forward/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseTransitionForward(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/transition/forward/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseTransitionForward(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/transition/forward/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseTransitionForward(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/transition/redirect/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseTransitionRedirect() {
        doRequestResponseTransitionRedirect(rule -> {});
    }

    /**
     * Request remote call to /wx/response/transition/redirect/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseTransitionRedirect(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/transition/redirect/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseTransitionRedirect(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/transition/redirect/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseTransitionRedirect(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/transition/redirect/permanently/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/permanently/
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseTransitionRedirectPermanently() {
        doRequestResponseTransitionRedirectPermanently(rule -> {});
    }

    /**
     * Request remote call to /wx/response/transition/redirect/permanently/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/permanently/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseTransitionRedirectPermanently(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/transition/redirect/permanently/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseTransitionRedirectPermanently(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/transition/redirect/permanently/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseTransitionRedirectPermanently(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/transition/redirect/permanently/ssl. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/permanently/ssl
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseTransitionRedirectPermanentlySsl() {
        doRequestResponseTransitionRedirectPermanentlySsl(rule -> {});
    }

    /**
     * Request remote call to /wx/response/transition/redirect/permanently/ssl. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/permanently/ssl
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseTransitionRedirectPermanentlySsl(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/transition/redirect/permanently/ssl", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseTransitionRedirectPermanentlySsl(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/transition/redirect/permanently/ssl.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseTransitionRedirectPermanentlySsl(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/response/transition/redirect/temporary/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/temporary/
     * httpMethod: GET
     * </pre>
     */
    public void requestResponseTransitionRedirectTemporary() {
        doRequestResponseTransitionRedirectTemporary(rule -> {});
    }

    /**
     * Request remote call to /wx/response/transition/redirect/temporary/. (auto-generated method)<br>
     * <pre>
     * url: /wx/response/transition/redirect/temporary/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestResponseTransitionRedirectTemporary(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/response/transition/redirect/temporary/", noMoreUrl(), noQuery(), rule -> {
            ruleOfResponseTransitionRedirectTemporary(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/response/transition/redirect/temporary/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfResponseTransitionRedirectTemporary(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingReturn requestRouting(Integer first) {
        return doRequestRouting(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingReturn doRequestRouting(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingReturn.class, "/wx/routing/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/maihama
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingMaihamaReturn requestRoutingMaihama() {
        return doRequestRoutingMaihama(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/maihama
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingMaihamaReturn doRequestRoutingMaihama(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingMaihamaReturn.class, "/wx/routing/maihama", noMoreUrl(), noQuery(), rule -> {
            ruleOfRoutingMaihama(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/maihama.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingMaihama(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/sea/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/sea/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingSeaReturn requestRoutingSea(String first) {
        return doRequestRoutingSea(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/sea/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/sea/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingSeaReturn doRequestRoutingSea(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingSeaReturn.class, "/wx/routing/sea/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingSeaFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/sea/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingSeaFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/land/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/land/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingLandReturn requestRoutingLand(String first, String second) {
        return doRequestRoutingLand(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/land/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/land/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingLandReturn doRequestRoutingLand(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingLandReturn.class, "/wx/routing/land/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingLandFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/land/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingLandFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/piari. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/piari
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingPiariReturn requestRoutingPiari() {
        return doRequestRoutingPiari(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/piari. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/piari
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingPiariReturn doRequestRoutingPiari(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingPiariReturn.class, "/wx/routing/piari", noMoreUrl(), noQuery(), rule -> {
            ruleOfRoutingPiari(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/piari.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingPiari(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/piari/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/piari/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingPiariReturn requestRoutingPiari(String first) {
        return doRequestRoutingPiari(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/piari/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/piari/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingPiariReturn doRequestRoutingPiari(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingPiariReturn.class, "/wx/routing/piari/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingPiariFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/piari/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingPiariFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/dstore. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dstore
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingDstoreReturn requestRoutingDstore() {
        return doRequestRoutingDstore(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/dstore. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dstore
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingDstoreReturn doRequestRoutingDstore(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingDstoreReturn.class, "/wx/routing/dstore", noMoreUrl(), noQuery(), rule -> {
            ruleOfRoutingDstore(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/dstore.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingDstore(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/dstore/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dstore/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingDstoreReturn requestRoutingDstore(String first) {
        return doRequestRoutingDstore(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/dstore/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dstore/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingDstoreReturn doRequestRoutingDstore(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingDstoreReturn.class, "/wx/routing/dstore/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingDstoreFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/dstore/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingDstoreFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/dstore/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dstore/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingDstoreReturn requestRoutingDstore(String first, String second) {
        return doRequestRoutingDstore(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/dstore/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dstore/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingDstoreReturn doRequestRoutingDstore(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingDstoreReturn.class, "/wx/routing/dstore/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingDstoreFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/dstore/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingDstoreFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/bonvo/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/bonvo/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingBonvoReturn requestRoutingBonvo(String first) {
        return doRequestRoutingBonvo(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/bonvo/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/bonvo/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingBonvoReturn doRequestRoutingBonvo(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingBonvoReturn.class, "/wx/routing/bonvo/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingBonvoFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/bonvo/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingBonvoFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/bonvo/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/bonvo/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingBonvoReturn requestRoutingBonvo(String first, String second) {
        return doRequestRoutingBonvo(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/bonvo/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/bonvo/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingBonvoReturn doRequestRoutingBonvo(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingBonvoReturn.class, "/wx/routing/bonvo/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingBonvoFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/bonvo/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingBonvoFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/amba/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/amba/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingAmbaReturn requestRoutingAmba(String first, String second) {
        return doRequestRoutingAmba(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/amba/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/amba/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingAmbaReturn doRequestRoutingAmba(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingAmbaReturn.class, "/wx/routing/amba/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingAmbaFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/amba/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingAmbaFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/amba/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/amba/{first}/{second}/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingAmbaReturn requestRoutingAmba(String first, String second, String third) {
        return doRequestRoutingAmba(first, second, third, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/amba/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/amba/{first}/{second}/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingAmbaReturn doRequestRoutingAmba(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingAmbaReturn.class, "/wx/routing/amba/{first}/{second}/{third}", moreUrl(first, second, third), noQuery(), rule -> {
            ruleOfRoutingAmbaFirstSecondThird(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/amba/{first}/{second}/{third}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingAmbaFirstSecondThird(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/miraco/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/miraco/{first}/{second}/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingMiracoReturn requestRoutingMiraco(String first, String second, String third) {
        return doRequestRoutingMiraco(first, second, third, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/miraco/{first}/{second}/{third}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/miraco/{first}/{second}/{third}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingMiracoReturn doRequestRoutingMiraco(String first, String second, String third, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingMiracoReturn.class, "/wx/routing/miraco/{first}/{second}/{third}", moreUrl(first, second, third), noQuery(), rule -> {
            ruleOfRoutingMiracoFirstSecondThird(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/miraco/{first}/{second}/{third}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingMiracoFirstSecondThird(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/miraco/{first}/{second}/{third}/{fourth}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/miraco/{first}/{second}/{third}/{fourth}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param fourth The value of path variable for fourth. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingMiracoReturn requestRoutingMiraco(String first, String second, String third, String fourth) {
        return doRequestRoutingMiraco(first, second, third, fourth, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/miraco/{first}/{second}/{third}/{fourth}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/miraco/{first}/{second}/{third}/{fourth}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param third The value of path variable for third. (NotNull)
     * @param fourth The value of path variable for fourth. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingMiracoReturn doRequestRoutingMiraco(String first, String second, String third, String fourth, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingMiracoReturn.class, "/wx/routing/miraco/{first}/{second}/{third}/{fourth}", moreUrl(first, second, third, fourth), noQuery(), rule -> {
            ruleOfRoutingMiracoFirstSecondThirdFourth(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/miraco/{first}/{second}/{third}/{fourth}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingMiracoFirstSecondThirdFourth(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/dohotel/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dohotel/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingDohotelParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingDohotelReturn requestRoutingDohotel(String first, Consumer<RemoteWxRoutingDohotelParam> paramLambda) {
        return doRequestRoutingDohotel(first, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/dohotel/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dohotel/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingDohotelParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingDohotelReturn doRequestRoutingDohotel(String first, Consumer<RemoteWxRoutingDohotelParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingDohotelParam param = new RemoteWxRoutingDohotelParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRoutingDohotelReturn.class, "/wx/routing/dohotel/{first}", moreUrl(first), query(param), rule -> {
            ruleOfRoutingDohotelFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/dohotel/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingDohotelFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/dohotel/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dohotel/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingDohotelFirstSecondParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingDohotelReturn requestRoutingDohotel(String first, String second, Consumer<RemoteWxRoutingDohotelFirstSecondParam> paramLambda) {
        return doRequestRoutingDohotel(first, second, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/dohotel/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/dohotel/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param paramLambda The callback for RemoteWxRoutingDohotelFirstSecondParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingDohotelReturn doRequestRoutingDohotel(String first, String second, Consumer<RemoteWxRoutingDohotelFirstSecondParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingDohotelFirstSecondParam param = new RemoteWxRoutingDohotelFirstSecondParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteWxRoutingDohotelReturn.class, "/wx/routing/dohotel/{first}/{second}", moreUrl(first, second), query(param), rule -> {
            ruleOfRoutingDohotelFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/dohotel/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingDohotelFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/celeb/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/celeb/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingCelebReturn requestRoutingCeleb(Integer first) {
        return doRequestRoutingCeleb(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/celeb/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/celeb/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingCelebReturn doRequestRoutingCeleb(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingCelebReturn.class, "/wx/routing/celeb/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingCelebFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/celeb/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingCelebFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/celeb/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/celeb/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingCelebReturn requestRoutingCeleb(Integer first, Long second) {
        return doRequestRoutingCeleb(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/celeb/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/celeb/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingCelebReturn doRequestRoutingCeleb(Integer first, Long second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingCelebReturn.class, "/wx/routing/celeb/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingCelebFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/celeb/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingCelebFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/{first}/resola. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/{first}/resola
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingResolaReturn requestRoutingResola(Integer first) {
        return doRequestRoutingResola(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/{first}/resola. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/{first}/resola
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingResolaReturn doRequestRoutingResola(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingResolaReturn.class, "/wx/routing/{first}/resola", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingResolaFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/{first}/resola.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingResolaFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/amphi/{first}/theater/{second}/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/amphi/{first}/theater/{second}/maihama
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingAmphiTheaterMaihamaReturn requestRoutingAmphiTheaterMaihama(Integer first, String second) {
        return doRequestRoutingAmphiTheaterMaihama(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/amphi/{first}/theater/{second}/maihama. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/amphi/{first}/theater/{second}/maihama
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingAmphiTheaterMaihamaReturn doRequestRoutingAmphiTheaterMaihama(Integer first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingAmphiTheaterMaihamaReturn.class, "/wx/routing/amphi/{first}/theater/{second}/maihama", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingAmphiTheaterMaihamaFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/amphi/{first}/theater/{second}/maihama.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingAmphiTheaterMaihamaFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/wording/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingWordingReturn requestRoutingWording(Integer first) {
        return doRequestRoutingWording(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/wording/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingWordingReturn doRequestRoutingWording(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingWordingReturn.class, "/wx/routing/wording/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingWordingFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/wording/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingWordingFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/wording/{first}/sea. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/{first}/sea
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingWordingSeaReturn requestRoutingWordingSea(String first) {
        return doRequestRoutingWordingSea(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/wording/{first}/sea. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/{first}/sea
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingWordingSeaReturn doRequestRoutingWordingSea(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingWordingSeaReturn.class, "/wx/routing/wording/{first}/sea", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingWordingSeaFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/wording/{first}/sea.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingWordingSeaFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/wording/land/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/land/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingWordingLandReturn requestRoutingWordingLand(String first) {
        return doRequestRoutingWordingLand(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/wording/land/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/land/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingWordingLandReturn doRequestRoutingWordingLand(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingWordingLandReturn.class, "/wx/routing/wording/land/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingWordingLandFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/wording/land/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingWordingLandFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/wording/piari/plaza. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/piari/plaza
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingWordingPiariPlazaReturn requestRoutingWordingPiariPlaza() {
        return doRequestRoutingWordingPiariPlaza(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/wording/piari/plaza. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/wording/piari/plaza
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingWordingPiariPlazaReturn doRequestRoutingWordingPiariPlaza(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingWordingPiariPlazaReturn.class, "/wx/routing/wording/piari/plaza", noMoreUrl(), noQuery(), rule -> {
            ruleOfRoutingWordingPiariPlaza(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/wording/piari/plaza.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingWordingPiariPlaza(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/mixworld/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/mixworld/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingMixworldReturn requestRoutingMixworld(String first) {
        return doRequestRoutingMixworld(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/mixworld/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/mixworld/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingMixworldReturn doRequestRoutingMixworld(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingMixworldReturn.class, "/wx/routing/mixworld/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingMixworldFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/mixworld/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingMixworldFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/mixworld/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/mixworld/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingMixworldReturn requestRoutingMixworld(String first, String second) {
        return doRequestRoutingMixworld(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/mixworld/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/mixworld/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingMixworldReturn doRequestRoutingMixworld(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingMixworldReturn.class, "/wx/routing/mixworld/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingMixworldFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/mixworld/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingMixworldFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/mixworld/maihama/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/mixworld/maihama/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingMixworldMaihamaReturn requestRoutingMixworldMaihama(String first, String second) {
        return doRequestRoutingMixworldMaihama(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/mixworld/maihama/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/mixworld/maihama/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingMixworldMaihamaReturn doRequestRoutingMixworldMaihama(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingMixworldMaihamaReturn.class, "/wx/routing/mixworld/maihama/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingMixworldMaihamaFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/mixworld/maihama/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingMixworldMaihamaFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/basic/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/basic/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathBasicReturn requestRoutingNearpathBasic(String first) {
        return doRequestRoutingNearpathBasic(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/basic/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/basic/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathBasicReturn doRequestRoutingNearpathBasic(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathBasicReturn.class, "/wx/routing/nearpath/basic/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathBasicFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/basic/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathBasicFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/basic/string/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/basic/string/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathBasicStringReturn requestRoutingNearpathBasicString(String first) {
        return doRequestRoutingNearpathBasicString(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/basic/string/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/basic/string/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathBasicStringReturn doRequestRoutingNearpathBasicString(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathBasicStringReturn.class, "/wx/routing/nearpath/basic/string/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathBasicStringFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/basic/string/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathBasicStringFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/basic/number/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/basic/number/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathBasicNumberReturn requestRoutingNearpathBasicNumber(Integer first) {
        return doRequestRoutingNearpathBasicNumber(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/basic/number/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/basic/number/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathBasicNumberReturn doRequestRoutingNearpathBasicNumber(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathBasicNumberReturn.class, "/wx/routing/nearpath/basic/number/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathBasicNumberFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/basic/number/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathBasicNumberFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword01/str2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword01/str2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword01Str2numReturn requestRoutingNearpathByword01Str2num(String first) {
        return doRequestRoutingNearpathByword01Str2num(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword01/str2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword01/str2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword01Str2numReturn doRequestRoutingNearpathByword01Str2num(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword01Str2numReturn.class, "/wx/routing/nearpath/byword01/str2num/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword01Str2numFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword01/str2num/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword01Str2numFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword01/str2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword01/str2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword01Str2numNamedReturn requestRoutingNearpathByword01Str2numNamed(Integer first) {
        return doRequestRoutingNearpathByword01Str2numNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword01/str2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword01/str2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword01Str2numNamedReturn doRequestRoutingNearpathByword01Str2numNamed(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword01Str2numNamedReturn.class, "/wx/routing/nearpath/byword01/str2num/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword01Str2numNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword01/str2num/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword01Str2numNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword02/num2str/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword02/num2str/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword02Num2strReturn requestRoutingNearpathByword02Num2str(Integer first) {
        return doRequestRoutingNearpathByword02Num2str(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword02/num2str/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword02/num2str/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword02Num2strReturn doRequestRoutingNearpathByword02Num2str(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword02Num2strReturn.class, "/wx/routing/nearpath/byword02/num2str/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword02Num2strFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword02/num2str/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword02Num2strFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword02/num2str/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword02/num2str/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword02Num2strNamedReturn requestRoutingNearpathByword02Num2strNamed(String first) {
        return doRequestRoutingNearpathByword02Num2strNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword02/num2str/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword02/num2str/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword02Num2strNamedReturn doRequestRoutingNearpathByword02Num2strNamed(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword02Num2strNamedReturn.class, "/wx/routing/nearpath/byword02/num2str/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword02Num2strNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword02/num2str/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword02Num2strNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword05/optstr2num. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword05/optstr2num
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword05Optstr2numReturn requestRoutingNearpathByword05Optstr2num() {
        return doRequestRoutingNearpathByword05Optstr2num(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword05/optstr2num. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword05/optstr2num
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword05Optstr2numReturn doRequestRoutingNearpathByword05Optstr2num(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword05Optstr2numReturn.class, "/wx/routing/nearpath/byword05/optstr2num", noMoreUrl(), noQuery(), rule -> {
            ruleOfRoutingNearpathByword05Optstr2num(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword05/optstr2num.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword05Optstr2num(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword05/optstr2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword05/optstr2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword05Optstr2numReturn requestRoutingNearpathByword05Optstr2num(String first) {
        return doRequestRoutingNearpathByword05Optstr2num(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword05/optstr2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword05/optstr2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword05Optstr2numReturn doRequestRoutingNearpathByword05Optstr2num(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword05Optstr2numReturn.class, "/wx/routing/nearpath/byword05/optstr2num/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword05Optstr2numFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword05/optstr2num/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword05Optstr2numFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword05/optstr2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword05/optstr2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword05Optstr2numNamedReturn requestRoutingNearpathByword05Optstr2numNamed(Integer first) {
        return doRequestRoutingNearpathByword05Optstr2numNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword05/optstr2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword05/optstr2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword05Optstr2numNamedReturn doRequestRoutingNearpathByword05Optstr2numNamed(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword05Optstr2numNamedReturn.class, "/wx/routing/nearpath/byword05/optstr2num/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword05Optstr2numNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword05/optstr2num/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword05Optstr2numNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword10/optstr4str2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword10/optstr4str2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword10Optstr4str2numReturn requestRoutingNearpathByword10Optstr4str2num(String first) {
        return doRequestRoutingNearpathByword10Optstr4str2num(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword10/optstr4str2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword10/optstr4str2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword10Optstr4str2numReturn doRequestRoutingNearpathByword10Optstr4str2num(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword10Optstr4str2numReturn.class, "/wx/routing/nearpath/byword10/optstr4str2num/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword10Optstr4str2numFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword10/optstr4str2num/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword10Optstr4str2numFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword10/optstr4str2num/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword10/optstr4str2num/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword10Optstr4str2numReturn requestRoutingNearpathByword10Optstr4str2num(String first, String second) {
        return doRequestRoutingNearpathByword10Optstr4str2num(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword10/optstr4str2num/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword10/optstr4str2num/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword10Optstr4str2numReturn doRequestRoutingNearpathByword10Optstr4str2num(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword10Optstr4str2numReturn.class, "/wx/routing/nearpath/byword10/optstr4str2num/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingNearpathByword10Optstr4str2numFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword10/optstr4str2num/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword10Optstr4str2numFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword10/optstr4str2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword10/optstr4str2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword10Optstr4str2numNamedReturn requestRoutingNearpathByword10Optstr4str2numNamed(Integer first) {
        return doRequestRoutingNearpathByword10Optstr4str2numNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword10/optstr4str2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword10/optstr4str2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword10Optstr4str2numNamedReturn doRequestRoutingNearpathByword10Optstr4str2numNamed(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword10Optstr4str2numNamedReturn.class, "/wx/routing/nearpath/byword10/optstr4str2num/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword10Optstr4str2numNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword10/optstr4str2num/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword10Optstr4str2numNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword11/optstr4numtostr/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword11/optstr4numtostr/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword11Optstr4numtostrReturn requestRoutingNearpathByword11Optstr4numtostr(Integer first) {
        return doRequestRoutingNearpathByword11Optstr4numtostr(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword11/optstr4numtostr/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword11/optstr4numtostr/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword11Optstr4numtostrReturn doRequestRoutingNearpathByword11Optstr4numtostr(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword11Optstr4numtostrReturn.class, "/wx/routing/nearpath/byword11/optstr4numtostr/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword11Optstr4numtostrFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword11/optstr4numtostr/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword11Optstr4numtostrFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword11/optstr4numtostr/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword11/optstr4numtostr/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword11Optstr4numtostrReturn requestRoutingNearpathByword11Optstr4numtostr(Integer first, String second) {
        return doRequestRoutingNearpathByword11Optstr4numtostr(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword11/optstr4numtostr/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword11/optstr4numtostr/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword11Optstr4numtostrReturn doRequestRoutingNearpathByword11Optstr4numtostr(Integer first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword11Optstr4numtostrReturn.class, "/wx/routing/nearpath/byword11/optstr4numtostr/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingNearpathByword11Optstr4numtostrFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword11/optstr4numtostr/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword11Optstr4numtostrFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword11/optstr4numtostr/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword11/optstr4numtostr/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword11Optstr4numtostrNamedReturn requestRoutingNearpathByword11Optstr4numtostrNamed(String first) {
        return doRequestRoutingNearpathByword11Optstr4numtostrNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword11/optstr4numtostr/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword11/optstr4numtostr/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword11Optstr4numtostrNamedReturn doRequestRoutingNearpathByword11Optstr4numtostrNamed(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword11Optstr4numtostrNamedReturn.class, "/wx/routing/nearpath/byword11/optstr4numtostr/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword11Optstr4numtostrNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword11/optstr4numtostr/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword11Optstr4numtostrNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword12/optstr4cls2str/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword12/optstr4cls2str/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword12Optstr4cls2strReturn requestRoutingNearpathByword12Optstr4cls2str(String first) {
        return doRequestRoutingNearpathByword12Optstr4cls2str(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword12/optstr4cls2str/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword12/optstr4cls2str/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword12Optstr4cls2strReturn doRequestRoutingNearpathByword12Optstr4cls2str(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword12Optstr4cls2strReturn.class, "/wx/routing/nearpath/byword12/optstr4cls2str/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword12Optstr4cls2strFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword12/optstr4cls2str/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword12Optstr4cls2strFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword12/optstr4cls2str/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword12/optstr4cls2str/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword12Optstr4cls2strReturn requestRoutingNearpathByword12Optstr4cls2str(String first, String second) {
        return doRequestRoutingNearpathByword12Optstr4cls2str(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword12/optstr4cls2str/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword12/optstr4cls2str/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword12Optstr4cls2strReturn doRequestRoutingNearpathByword12Optstr4cls2str(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword12Optstr4cls2strReturn.class, "/wx/routing/nearpath/byword12/optstr4cls2str/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingNearpathByword12Optstr4cls2strFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword12/optstr4cls2str/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword12Optstr4cls2strFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword12/optstr4cls2str/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword12/optstr4cls2str/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword12Optstr4cls2strNamedReturn requestRoutingNearpathByword12Optstr4cls2strNamed(String first) {
        return doRequestRoutingNearpathByword12Optstr4cls2strNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword12/optstr4cls2str/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword12/optstr4cls2str/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword12Optstr4cls2strNamedReturn doRequestRoutingNearpathByword12Optstr4cls2strNamed(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword12Optstr4cls2strNamedReturn.class, "/wx/routing/nearpath/byword12/optstr4cls2str/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword12Optstr4cls2strNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword12/optstr4cls2str/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword12Optstr4cls2strNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword13/optstr4cls2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword13/optstr4cls2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword13Optstr4cls2numReturn requestRoutingNearpathByword13Optstr4cls2num(String first) {
        return doRequestRoutingNearpathByword13Optstr4cls2num(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword13/optstr4cls2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword13/optstr4cls2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword13Optstr4cls2numReturn doRequestRoutingNearpathByword13Optstr4cls2num(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword13Optstr4cls2numReturn.class, "/wx/routing/nearpath/byword13/optstr4cls2num/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword13Optstr4cls2numFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword13/optstr4cls2num/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword13Optstr4cls2numFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword13/optstr4cls2num/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword13/optstr4cls2num/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword13Optstr4cls2numReturn requestRoutingNearpathByword13Optstr4cls2num(String first, String second) {
        return doRequestRoutingNearpathByword13Optstr4cls2num(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword13/optstr4cls2num/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword13/optstr4cls2num/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword13Optstr4cls2numReturn doRequestRoutingNearpathByword13Optstr4cls2num(String first, String second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword13Optstr4cls2numReturn.class, "/wx/routing/nearpath/byword13/optstr4cls2num/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingNearpathByword13Optstr4cls2numFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword13/optstr4cls2num/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword13Optstr4cls2numFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword13/optstr4cls2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword13/optstr4cls2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword13Optstr4cls2numNamedReturn requestRoutingNearpathByword13Optstr4cls2numNamed(Integer first) {
        return doRequestRoutingNearpathByword13Optstr4cls2numNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword13/optstr4cls2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword13/optstr4cls2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword13Optstr4cls2numNamedReturn doRequestRoutingNearpathByword13Optstr4cls2numNamed(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword13Optstr4cls2numNamedReturn.class, "/wx/routing/nearpath/byword13/optstr4cls2num/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword13Optstr4cls2numNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword13/optstr4cls2num/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword13Optstr4cls2numNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword15/optnum4str2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword15/optnum4str2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword15Optnum4str2numReturn requestRoutingNearpathByword15Optnum4str2num(String first) {
        return doRequestRoutingNearpathByword15Optnum4str2num(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword15/optnum4str2num/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword15/optnum4str2num/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword15Optnum4str2numReturn doRequestRoutingNearpathByword15Optnum4str2num(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword15Optnum4str2numReturn.class, "/wx/routing/nearpath/byword15/optnum4str2num/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword15Optnum4str2numFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword15/optnum4str2num/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword15Optnum4str2numFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword15/optnum4str2num/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword15/optnum4str2num/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword15Optnum4str2numReturn requestRoutingNearpathByword15Optnum4str2num(String first, Integer second) {
        return doRequestRoutingNearpathByword15Optnum4str2num(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword15/optnum4str2num/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword15/optnum4str2num/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword15Optnum4str2numReturn doRequestRoutingNearpathByword15Optnum4str2num(String first, Integer second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword15Optnum4str2numReturn.class, "/wx/routing/nearpath/byword15/optnum4str2num/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingNearpathByword15Optnum4str2numFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword15/optnum4str2num/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword15Optnum4str2numFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword15/optnum4str2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword15/optnum4str2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword15Optnum4str2numNamedReturn requestRoutingNearpathByword15Optnum4str2numNamed(Integer first) {
        return doRequestRoutingNearpathByword15Optnum4str2numNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword15/optnum4str2num/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword15/optnum4str2num/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword15Optnum4str2numNamedReturn doRequestRoutingNearpathByword15Optnum4str2numNamed(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword15Optnum4str2numNamedReturn.class, "/wx/routing/nearpath/byword15/optnum4str2num/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword15Optnum4str2numNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword15/optnum4str2num/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword15Optnum4str2numNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword16/optnum4num2str/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword16/optnum4num2str/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword16Optnum4num2strReturn requestRoutingNearpathByword16Optnum4num2str(Integer first) {
        return doRequestRoutingNearpathByword16Optnum4num2str(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword16/optnum4num2str/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword16/optnum4num2str/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword16Optnum4num2strReturn doRequestRoutingNearpathByword16Optnum4num2str(Integer first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword16Optnum4num2strReturn.class, "/wx/routing/nearpath/byword16/optnum4num2str/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword16Optnum4num2strFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword16/optnum4num2str/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword16Optnum4num2strFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword16/optnum4num2str/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword16/optnum4num2str/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword16Optnum4num2strReturn requestRoutingNearpathByword16Optnum4num2str(Integer first, Integer second) {
        return doRequestRoutingNearpathByword16Optnum4num2str(first, second, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword16/optnum4num2str/{first}/{second}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword16/optnum4num2str/{first}/{second}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param second The value of path variable for second. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword16Optnum4num2strReturn doRequestRoutingNearpathByword16Optnum4num2str(Integer first, Integer second, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword16Optnum4num2strReturn.class, "/wx/routing/nearpath/byword16/optnum4num2str/{first}/{second}", moreUrl(first, second), noQuery(), rule -> {
            ruleOfRoutingNearpathByword16Optnum4num2strFirstSecond(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword16/optnum4num2str/{first}/{second}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword16Optnum4num2strFirstSecond(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword16/optnum4num2str/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword16/optnum4num2str/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathByword16Optnum4num2strNamedReturn requestRoutingNearpathByword16Optnum4num2strNamed(String first) {
        return doRequestRoutingNearpathByword16Optnum4num2strNamed(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/byword16/optnum4num2str/{first}/named. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/byword16/optnum4num2str/{first}/named
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathByword16Optnum4num2strNamedReturn doRequestRoutingNearpathByword16Optnum4num2strNamed(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathByword16Optnum4num2strNamedReturn.class, "/wx/routing/nearpath/byword16/optnum4num2str/{first}/named", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathByword16Optnum4num2strNamedFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/byword16/optnum4num2str/{first}/named.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathByword16Optnum4num2strNamedFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/nearpath/shadowed/method/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/shadowed/method/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxRoutingNearpathShadowedMethodReturn requestRoutingNearpathShadowedMethod(String first) {
        return doRequestRoutingNearpathShadowedMethod(first, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/nearpath/shadowed/method/{first}. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/nearpath/shadowed/method/{first}
     * httpMethod: GET
     * </pre>
     * @param first The value of path variable for first. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxRoutingNearpathShadowedMethodReturn doRequestRoutingNearpathShadowedMethod(String first, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxRoutingNearpathShadowedMethodReturn.class, "/wx/routing/nearpath/shadowed/method/{first}", moreUrl(first), noQuery(), rule -> {
            ruleOfRoutingNearpathShadowedMethodFirst(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/nearpath/shadowed/method/{first}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingNearpathShadowedMethodFirst(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/reqparam/. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRoutingReqparamParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRoutingReqparam(Consumer<RemoteWxRoutingReqparamParam> paramLambda) {
        return doRequestRoutingReqparam(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/reqparam/. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRoutingReqparamParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRoutingReqparam(Consumer<RemoteWxRoutingReqparamParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingReqparamParam param = new RemoteWxRoutingReqparamParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/routing/reqparam/", noMoreUrl(), query(param), rule -> {
            ruleOfRoutingReqparam(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/reqparam/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingReqparam(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/reqparam/sea. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/sea
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRoutingReqparamSea() {
        return doRequestRoutingReqparamSea(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/reqparam/sea. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/sea
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRoutingReqparamSea(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/routing/reqparam/sea", noMoreUrl(), noQuery(), rule -> {
            ruleOfRoutingReqparamSea(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/reqparam/sea.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingReqparamSea(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/reqparam/conflict/. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/conflict/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRoutingReqparamConflictParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRoutingReqparamConflict(Consumer<RemoteWxRoutingReqparamConflictParam> paramLambda) {
        return doRequestRoutingReqparamConflict(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/routing/reqparam/conflict/. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/conflict/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxRoutingReqparamConflictParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRoutingReqparamConflict(Consumer<RemoteWxRoutingReqparamConflictParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxRoutingReqparamConflictParam param = new RemoteWxRoutingReqparamConflictParam();
        paramLambda.accept(param);
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/routing/reqparam/conflict/", noMoreUrl(), query(param), rule -> {
            ruleOfRoutingReqparamConflict(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/reqparam/conflict/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingReqparamConflict(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/reqparam/conflict/sea. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/conflict/sea
     * httpMethod: POST
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public java.util.Map<String, Object> requestRoutingReqparamConflictSea() {
        return doRequestRoutingReqparamConflictSea(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/reqparam/conflict/sea. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/reqparam/conflict/sea
     * httpMethod: POST
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected java.util.Map<String, Object> doRequestRoutingReqparamConflictSea(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/wx/routing/reqparam/conflict/sea", noMoreUrl(), noRequestBody(), rule -> {
            ruleOfRoutingReqparamConflictSea(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/reqparam/conflict/sea.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingReqparamConflictSea(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/routing/restful/dummy. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restful/dummy
     * httpMethod: GET
     * </pre>
     */
    public void requestRoutingRestfulDummy() {
        doRequestRoutingRestfulDummy(rule -> {});
    }

    /**
     * Request remote call to /wx/routing/restful/dummy. (auto-generated method)<br>
     * <pre>
     * url: /wx/routing/restful/dummy
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestRoutingRestfulDummy(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/wx/routing/restful/dummy", noMoreUrl(), noQuery(), rule -> {
            ruleOfRoutingRestfulDummy(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/routing/restful/dummy.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfRoutingRestfulDummy(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/thymeleaf/parade. (auto-generated method)<br>
     * <pre>
     * url: /wx/thymeleaf/parade
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxThymeleafParadeParam. (NotNull)
     */
    public void requestThymeleafParade(Consumer<RemoteWxThymeleafParadeParam> paramLambda) {
        doRequestThymeleafParade(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/thymeleaf/parade. (auto-generated method)<br>
     * <pre>
     * url: /wx/thymeleaf/parade
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxThymeleafParadeParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestThymeleafParade(Consumer<RemoteWxThymeleafParadeParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxThymeleafParadeParam param = new RemoteWxThymeleafParadeParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/thymeleaf/parade", noMoreUrl(), query(param), rule -> {
            ruleOfThymeleafParade(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/thymeleaf/parade.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfThymeleafParade(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/thymeleaf/parade/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /wx/thymeleaf/parade/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteWxThymeleafParadePagenumberParam. (NotNull)
     */
    public void requestThymeleafParade(Integer pageNumber, Consumer<RemoteWxThymeleafParadePagenumberParam> paramLambda) {
        doRequestThymeleafParade(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/thymeleaf/parade/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /wx/thymeleaf/parade/{pageNumber}
     * httpMethod: GET
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteWxThymeleafParadePagenumberParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestThymeleafParade(Integer pageNumber, Consumer<RemoteWxThymeleafParadePagenumberParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxThymeleafParadePagenumberParam param = new RemoteWxThymeleafParadePagenumberParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/thymeleaf/parade/{pageNumber}", moreUrl(pageNumber), query(param), rule -> {
            ruleOfThymeleafParadePageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/thymeleaf/parade/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfThymeleafParadePageNumber(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/transaction/memories/fail. (auto-generated method)<br>
     * <pre>
     * url: /wx/transaction/memories/fail
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxTransactionMemoriesFailParam. (NotNull)
     */
    public void requestTransactionMemoriesFail(Consumer<RemoteWxTransactionMemoriesFailParam> paramLambda) {
        doRequestTransactionMemoriesFail(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/transaction/memories/fail. (auto-generated method)<br>
     * <pre>
     * url: /wx/transaction/memories/fail
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxTransactionMemoriesFailParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestTransactionMemoriesFail(Consumer<RemoteWxTransactionMemoriesFailParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxTransactionMemoriesFailParam param = new RemoteWxTransactionMemoriesFailParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/transaction/memories/fail", noMoreUrl(), query(param), rule -> {
            ruleOfTransactionMemoriesFail(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/transaction/memories/fail.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfTransactionMemoriesFail(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/transaction/memories/failasync. (auto-generated method)<br>
     * <pre>
     * url: /wx/transaction/memories/failasync
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxTransactionMemoriesFailasyncParam. (NotNull)
     */
    public void requestTransactionMemoriesFailasync(Consumer<RemoteWxTransactionMemoriesFailasyncParam> paramLambda) {
        doRequestTransactionMemoriesFailasync(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/transaction/memories/failasync. (auto-generated method)<br>
     * <pre>
     * url: /wx/transaction/memories/failasync
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxTransactionMemoriesFailasyncParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestTransactionMemoriesFailasync(Consumer<RemoteWxTransactionMemoriesFailasyncParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxTransactionMemoriesFailasyncParam param = new RemoteWxTransactionMemoriesFailasyncParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/transaction/memories/failasync", noMoreUrl(), query(param), rule -> {
            ruleOfTransactionMemoriesFailasync(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/transaction/memories/failasync.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfTransactionMemoriesFailasync(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorParam. (NotNull)
     */
    public void requestValidator(Consumer<RemoteWxValidatorParam> paramLambda) {
        doRequestValidator(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/validator/. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestValidator(Consumer<RemoteWxValidatorParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxValidatorParam param = new RemoteWxValidatorParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/validator/", noMoreUrl(), query(param), rule -> {
            ruleOfValidator(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidator(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/genericform. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/genericform
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorGenericformParam. (NotNull)
     */
    public void requestValidatorGenericform(Consumer<RemoteWxValidatorGenericformParam> paramLambda) {
        doRequestValidatorGenericform(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/validator/genericform. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/genericform
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorGenericformParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestValidatorGenericform(Consumer<RemoteWxValidatorGenericformParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxValidatorGenericformParam param = new RemoteWxValidatorGenericformParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/validator/genericform", noMoreUrl(), query(param), rule -> {
            ruleOfValidatorGenericform(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/genericform.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorGenericform(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/groupingjson/{grouping}. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groupingjson/{grouping}
     * httpMethod: GET
     * </pre>
     * @param grouping The value of path variable for grouping. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxValidatorGroupingjsonReturn requestValidatorGroupingjson(Boolean grouping) {
        return doRequestValidatorGroupingjson(grouping, rule -> {});
    }

    /**
     * Request remote call to /wx/validator/groupingjson/{grouping}. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groupingjson/{grouping}
     * httpMethod: GET
     * </pre>
     * @param grouping The value of path variable for grouping. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxValidatorGroupingjsonReturn doRequestValidatorGroupingjson(Boolean grouping, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxValidatorGroupingjsonReturn.class, "/wx/validator/groupingjson/{grouping}", moreUrl(grouping), noQuery(), rule -> {
            ruleOfValidatorGroupingjsonGrouping(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/groupingjson/{grouping}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorGroupingjsonGrouping(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/listjson. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/listjson
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorListjsonParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteWxValidatorListjsonReturn> requestValidatorListjson(Consumer<RemoteWxValidatorListjsonParam> paramLambda) {
        return doRequestValidatorListjson(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/validator/listjson. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/listjson
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorListjsonParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteWxValidatorListjsonReturn> doRequestValidatorListjson(Consumer<RemoteWxValidatorListjsonParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxValidatorListjsonParam param = new RemoteWxValidatorListjsonParam();
        paramLambda.accept(param);
        return doRequestPost(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteWxValidatorListjsonReturn>>() {
        }.getType(), "/wx/validator/listjson", noMoreUrl(), param, rule -> {
            ruleOfValidatorListjson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/listjson.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorListjson(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/wholejson. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/wholejson
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorWholejsonParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxValidatorWholejsonReturn requestValidatorWholejson(Consumer<RemoteWxValidatorWholejsonParam> paramLambda) {
        return doRequestValidatorWholejson(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/validator/wholejson. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/wholejson
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorWholejsonParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxValidatorWholejsonReturn doRequestValidatorWholejson(Consumer<RemoteWxValidatorWholejsonParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxValidatorWholejsonParam param = new RemoteWxValidatorWholejsonParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteWxValidatorWholejsonReturn.class, "/wx/validator/wholejson", noMoreUrl(), param, rule -> {
            ruleOfValidatorWholejson(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/wholejson.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorWholejson(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/custom/. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/custom/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorCustomParam. (NotNull)
     */
    public void requestValidatorCustom(Consumer<RemoteWxValidatorCustomParam> paramLambda) {
        doRequestValidatorCustom(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /wx/validator/custom/. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/custom/
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteWxValidatorCustomParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestValidatorCustom(Consumer<RemoteWxValidatorCustomParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteWxValidatorCustomParam param = new RemoteWxValidatorCustomParam();
        paramLambda.accept(param);
        doRequestGet(void.class, "/wx/validator/custom/", noMoreUrl(), query(param), rule -> {
            ruleOfValidatorCustom(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/custom/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorCustom(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/groups/response/dockside. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groups/response/dockside
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxValidatorGroupsResponseDocksideReturn requestValidatorGroupsResponseDockside() {
        return doRequestValidatorGroupsResponseDockside(rule -> {});
    }

    /**
     * Request remote call to /wx/validator/groups/response/dockside. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groups/response/dockside
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxValidatorGroupsResponseDocksideReturn doRequestValidatorGroupsResponseDockside(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxValidatorGroupsResponseDocksideReturn.class, "/wx/validator/groups/response/dockside", noMoreUrl(), noQuery(), rule -> {
            ruleOfValidatorGroupsResponseDockside(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/groups/response/dockside.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorGroupsResponseDockside(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/groups/response/hangar. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groups/response/hangar
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxValidatorGroupsResponseHangarReturn requestValidatorGroupsResponseHangar() {
        return doRequestValidatorGroupsResponseHangar(rule -> {});
    }

    /**
     * Request remote call to /wx/validator/groups/response/hangar. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groups/response/hangar
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxValidatorGroupsResponseHangarReturn doRequestValidatorGroupsResponseHangar(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxValidatorGroupsResponseHangarReturn.class, "/wx/validator/groups/response/hangar", noMoreUrl(), noQuery(), rule -> {
            ruleOfValidatorGroupsResponseHangar(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/groups/response/hangar.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorGroupsResponseHangar(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /wx/validator/groups/response/useDefault. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groups/response/useDefault
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteWxValidatorGroupsResponseUsedefaultReturn requestValidatorGroupsResponseUsedefault() {
        return doRequestValidatorGroupsResponseUsedefault(rule -> {});
    }

    /**
     * Request remote call to /wx/validator/groups/response/useDefault. (auto-generated method)<br>
     * <pre>
     * url: /wx/validator/groups/response/useDefault
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteWxValidatorGroupsResponseUsedefaultReturn doRequestValidatorGroupsResponseUsedefault(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteWxValidatorGroupsResponseUsedefaultReturn.class, "/wx/validator/groups/response/useDefault", noMoreUrl(), noQuery(), rule -> {
            ruleOfValidatorGroupsResponseUsedefault(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /wx/validator/groups/response/useDefault.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfValidatorGroupsResponseUsedefault(FlutyRemoteApiRule rule) {
    }
}
