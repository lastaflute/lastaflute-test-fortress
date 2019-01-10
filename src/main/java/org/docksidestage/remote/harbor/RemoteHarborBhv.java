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
package org.docksidestage.remote.harbor;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.function.Consumer;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.dbflute.optional.OptionalThing;
import org.dbflute.remoteapi.FlutyRemoteApi;
import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.http.SupportedHttpMethod;
import org.docksidestage.app.web.product.ProductSearchForm;
import org.docksidestage.remote.harbor.base.RemoteHbPagingReturn;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult.RemoteUnifiedFailureType;
import org.docksidestage.remote.harbor.mypage.RemoteHbMypageProductReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductSearchParam;
import org.docksidestage.remote.harbor.signin.RemoteHbSigninParam;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.remoteapi.LastaRemoteApi;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.mapping.LaVacantMappingPolicy;
import org.lastaflute.remoteapi.receiver.LaJsonReceiver;
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

    @Override
    protected FlutyRemoteApi newRemoteApi(Consumer<FlutyRemoteApiRule> ruleSetupper, Object callerExp) {
        return new LastaRemoteApi(ruleSetupper, callerExp) {
            @Override
            public <RETURN> RETURN requestDelete(Type returnType, String urlBase, String actionPath, Object[] pathVariables,
                    OptionalThing<? extends Object> param, Consumer<FlutyRemoteApiRule> ruleLambda) {
                return doRequestEnclosing(returnType, urlBase, actionPath, pathVariables, param.get(), ruleLambda,
                        SupportedHttpMethod.DELETE, url -> {
                            return new HttpEnclosingDelete(url);
                        });
            }
        };
    }

    @NotThreadSafe
    public static class HttpEnclosingDelete extends HttpEntityEnclosingRequestBase {

        public final static String METHOD_NAME = "DELETE";

        public HttpEnclosingDelete() {
            super();
        }

        public HttpEnclosingDelete(final URI uri) {
            super();
            setURI(uri);
        }

        public HttpEnclosingDelete(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    public void requestSignin(RemoteHbSigninParam param) {
        doRequestPost(void.class, "/lido/auth/signin", noMoreUrl(), param, rule -> {});
    }

    public List<RemoteHbMypageProductReturn> requestMypage() {
        ProductSearchForm form = new ProductSearchForm();
        form.productName = "sea";
        return doRequestDelete(new ParameterizedRef<List<RemoteHbMypageProductReturn>>() {
        }.getType(), "/lido/mypage", noMoreUrl(), OptionalThing.of(form), rule -> {});
    }

    public RemoteHbPagingReturn<RemoteHbProductRowReturn> requestProductList(RemoteHbProductSearchParam param) {
        return doRequestPost(new ParameterizedRef<RemoteHbPagingReturn<RemoteHbProductRowReturn>>() {
        }.getType(), "/lido/product/list", moreUrl(1), param, rule -> {});
    }
}
