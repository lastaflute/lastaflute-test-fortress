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
package org.docksidestage.remote.harbor;

import java.lang.reflect.Type;
import java.util.List;

import org.dbflute.optional.OptionalThing;
import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.receiver.FlBaseReceiver;
import org.docksidestage.remote.harbor.base.RemoteHbPagingReturn;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult.RemoteUnifiedFailureType;
import org.docksidestage.remote.harbor.lido.mypage.RemoteHbLidoMypageProductReturn;
import org.docksidestage.remote.harbor.lido.product.RemoteHbLidoProductRowReturn;
import org.docksidestage.remote.harbor.lido.product.RemoteHbLidoProductSearchParam;
import org.docksidestage.remote.harbor.lido.signin.RemoteHbLidoSigninParam;
import org.docksidestage.remote.harbor.serh.product.RemoteHbSerhProductSearchParam;
import org.docksidestage.remote.harbor.serh.signin.RemoteHbSerhSigninParam;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.mapping.LaVacantMappingPolicy;
import org.lastaflute.remoteapi.receiver.LaJsonReceiver;
import org.lastaflute.remoteapi.sender.body.LaFormSender;
import org.lastaflute.remoteapi.sender.body.LaJsonSender;
import org.lastaflute.remoteapi.sender.query.LaQuerySender;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhv extends LastaRemoteBehavior {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RemoteHarborBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                          Initialize
    //                                                                          ==========
    @Override
    protected void yourDefaultRule(FlutyRemoteApiRule rule) {
        rule.sendQueryBy(new LaQuerySender(new LaVacantMappingPolicy()));

        JsonMappingOption jsonMappingOption = new JsonMappingOption();
        rule.sendBodyBy(new LaJsonSender(requestManager, jsonMappingOption));
        rule.receiveBodyBy(new LaJsonReceiver(requestManager, jsonMappingOption));

        rule.handleFailureResponseAs(RemoteHbUnifiedFailureResult.class); // server-managed message way
        rule.translateClientError(resource -> {
            RemoteApiHttpClientErrorException clientError = resource.getClientError();
            if (clientError.getHttpStatus() == 400) { // controlled client error
                RemoteHbUnifiedFailureResult result = (RemoteHbUnifiedFailureResult) clientError.getFailureResponse().get();
                if (RemoteUnifiedFailureType.VALIDATION_ERROR.equals(result.cause)) {
                    UserMessages messages = new UserMessages();
                    result.errors.forEach(error -> {
                        error.messages.forEach(message -> {
                            messages.add(error.field, UserMessage.asDirectMessage(message));
                        });
                    });
                    return resource.asActionValidationError(messages);
                }
            }
            return null; // no translation
        });
    }

    @Override
    protected String getUrlBase() {
        return "http://localhost:8090/harbor";
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // -----------------------------------------------------
    //                                            Lido Style
    //                                            ----------
    // test of request body and void return as POST
    public void requestLidoAuthSignin(RemoteHbLidoSigninParam param) {
        doRequestPost(void.class, "/lido/auth/signin", noMoreUrl(), param, rule -> {});
    }

    // test of direct List return and no query parameter as GET
    public List<RemoteHbLidoMypageProductReturn> requestLidoMypage() {
        return doRequestGet(new ParameterizedRef<List<RemoteHbLidoMypageProductReturn>>() {
        }.getType(), "/lido/mypage", noMoreUrl(), noQuery(), rule -> {});
    }

    // test of moreUrl() and generics return and as POST
    public RemoteHbPagingReturn<RemoteHbLidoProductRowReturn> requestLidoProductList(RemoteHbLidoProductSearchParam param) {
        return doRequestPost(new ParameterizedRef<RemoteHbPagingReturn<RemoteHbLidoProductRowReturn>>() {
        }.getType(), "/lido/product/list", moreUrl(1), param, rule -> {
            rule.handleResponseHeader(resource -> {
                // xxxxxxPager.setup(resource.getMappedBodyReturn().get());
                //
                // public XxxxxxxxPagingPart pagingPart;
            });
        });
    }

    // -----------------------------------------------------
    //                                      ServerHTML Style
    //                                      ----------------
    // test of query parameter and overriding rule as GET
    public OptionalThing<String> requestSerhProductList(RemoteHbSerhProductSearchParam param) {
        return doRequestGet(new ParameterizedRef<OptionalThing<String>>() {
        }.getType(), "/product/list", moreUrl(1), query(param), rule -> {
            rule.receiveBodyBy(new MyHtmlDirectlyReceiver());
        });
    }

    // HTML is not prepared in framework so needs to make it
    private static class MyHtmlDirectlyReceiver extends FlBaseReceiver {

        public <RETURN> RETURN toResponseReturn(OptionalThing<String> body, Type beanType, FlutyRemoteApiRule rule) {
            @SuppressWarnings("unchecked")
            RETURN ret = (RETURN) body; // body is just HTML strings
            return ret;
        }

        @Override
        protected String getSendReceiveLogResponseBodyType() {
            return "html";
        }
    }

    // test of form parameter, which has bean type property in form
    public void requestSerhSignin(RemoteHbSerhSigninParam param) {
        doRequestPost(void.class, "/signin", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(new LaFormSender(new LaVacantMappingPolicy()));
        });
    }
}
