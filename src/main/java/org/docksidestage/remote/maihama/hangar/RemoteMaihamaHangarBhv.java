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
package org.docksidestage.remote.maihama.hangar;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.mapping.FlVacantMappingPolicy;
import org.dbflute.util.Srl;
import org.docksidestage.remote.maihama.hangar.base.RemoteHgPagingReturn;
import org.docksidestage.remote.maihama.hangar.mypage.RemoteHgMypageReturn;
import org.docksidestage.remote.maihama.hangar.product.RemoteHgProductRowReturn;
import org.docksidestage.remote.maihama.hangar.product.RemoteHgProductSearchParam;
import org.docksidestage.remote.maihama.hangar.signin.RemoteHgSigninParam;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.message.MessageManager;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.receiver.LaJsonReceiver;
import org.lastaflute.remoteapi.sender.body.LaJsonSender;
import org.lastaflute.remoteapi.sender.query.LaQuerySender;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult.FaicliFailureErrorPart;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult.FaicliUnifiedFailureType;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteMaihamaHangarBhv extends LastaRemoteBehavior {

    @Resource
    private MessageManager messageManager;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RemoteMaihamaHangarBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                          Initialize
    //                                                                          ==========
    @Override
    protected void yourDefaultRule(FlutyRemoteApiRule rule) {
        rule.sendQueryBy(new LaQuerySender(new FlVacantMappingPolicy()));

        JsonMappingOption jsonMappingOption = new JsonMappingOption();
        rule.sendBodyBy(new LaJsonSender(requestManager, jsonMappingOption));
        rule.receiveBodyBy(new LaJsonReceiver(requestManager, jsonMappingOption));

        rule.handleFailureResponseAs(FaicliUnifiedFailureResult.class); // server-managed message way
        rule.translateClientError(resource -> {
            RemoteApiHttpClientErrorException clientError = resource.getClientError();
            if (clientError.getHttpStatus() == 400) { // controlled client error
                FaicliUnifiedFailureResult result = (FaicliUnifiedFailureResult) clientError.getFailureResponse().get();
                if (FaicliUnifiedFailureType.VALIDATION_ERROR.equals(result.cause)) {
                    UserMessages messages = new UserMessages();
                    result.errors.forEach(error -> {
                        messages.add(error.field, toUserMessage(error));
                    });
                    return resource.asActionValidationError(messages);
                }
            }
            return null; // no translation
        });
        rule.showSendReceiveLog(op -> {});
    }

    // [example] how to use client-managed message (translate it here)
    private UserMessage toUserMessage(FaicliFailureErrorPart error) {
        String plainMessage = messageManager.getMessage(Locale.ENGLISH, "constraints." + error.code + ".message");
        Map<String, String> fromToMap = new HashMap<>();
        error.data.forEach((key, value) -> fromToMap.put("{" + key + "}", value.toString()));
        return UserMessage.asDirectMessage(Srl.replaceBy(plainMessage, fromToMap));
    }

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // [example] how to use basic authentication
    //rule.setupNativeHttpClient(builder -> {
    //    String host = ...;
    //    String username = ...;
    //    String password = ...;
    //    if (username != null && !username.isEmpty()) { // as trigger
    //        HttpHost httpHost = new HttpHost(host, -1, "https");
    //        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    //        credentialsProvider.setCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()),
    //                new UsernamePasswordCredentials(username, password));
    //        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
    //    }
    //});
    // _/_/_/_/_/_/_/_/_/_/

    // [example] how to use application user-agent including e.g. class name
    @Override
    protected boolean isUseApplicationalUserAgent() {
        return true;
    }

    @Override
    protected String getUrlBase() {
        return "http://localhost:8092/hangar";
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    public void requestSignin(RemoteHgSigninParam param) {
        doRequestPost(void.class, "/auth/signin", noMoreUrl(), param, rule -> {});
    }

    public RemoteHgMypageReturn requestMypage() {
        return doRequestGet(RemoteHgMypageReturn.class, "/mypage", noMoreUrl(), noQuery(), rule -> {});
    }

    public RemoteHgPagingReturn<RemoteHgProductRowReturn> requestProductList(OptionalThing<Integer> pageNumber,
            RemoteHgProductSearchParam param) {
        return doRequestPost(new ParameterizedRef<RemoteHgPagingReturn<RemoteHgProductRowReturn>>() {
        }.getType(), "/product/list/search/{pageNumber}", moreUrl(pageNumber), param, rule -> {});
    }
}
