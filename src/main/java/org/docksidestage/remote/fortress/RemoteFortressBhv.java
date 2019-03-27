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
package org.docksidestage.remote.fortress;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.dbflute.helper.beans.DfBeanDesc;
import org.dbflute.helper.beans.DfPropertyDesc;
import org.dbflute.helper.beans.factory.DfBeanDescFactory;
import org.dbflute.optional.OptionalThing;
import org.dbflute.remoteapi.FlutyRemoteApi;
import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.http.SupportedHttpMethod;
import org.dbflute.remoteapi.mapping.FlRemoteMappingPolicy;
import org.dbflute.remoteapi.mapping.FlVacantMappingPolicy;
import org.docksidestage.remote.fortress.wx.multipart.RemoteFrMultipartParam;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult.RemoteUnifiedFailureType;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.remoteapi.LastaRemoteApi;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.mapping.LaVacantMappingPolicy;
import org.lastaflute.remoteapi.receiver.LaJsonReceiver;
import org.lastaflute.remoteapi.sender.body.LaFormSender;
import org.lastaflute.remoteapi.sender.body.LaJsonSender;
import org.lastaflute.remoteapi.sender.query.LaQuerySender;
import org.lastaflute.web.ruts.multipart.MultipartFormFile;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteFortressBhv extends LastaRemoteBehavior {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RemoteFortressBhv(RequestManager requestManager) {
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
        return "http://localhost:8151/fortress"; // for JettyBoot
    }

    // ===================================================================================
    //                                                                           Extension
    //                                                                           =========
    // for the test of DELETE with body
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
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // *self-call so you cannot request action to action when hot deploy
    // _/_/_/_/_/_/_/_/_/_/
    public void requestWxMultipart(RemoteFrMultipartParam param) {
        doRequestPost(void.class, "/wx/request/multipart/upload", noMoreUrl(), param, rule -> {
            // when normal form sender
            // {sea=mystic, land=1, uploadedFile={inputStream=null, fileName=null, fileData={}, fileSize=0, contentType=null}}
            //rule.sendBodyBy(new LaFormSender(new LaVacantMappingPolicy()));

            rule.sendBodyBy(new MyMultipartFormSender(new FlVacantMappingPolicy()));
        });
    }

    protected static class MyMultipartFormSender extends LaFormSender {

        public MyMultipartFormSender(FlRemoteMappingPolicy mappingPolicy) {
            super(mappingPolicy);
        }

        @Override
        public void prepareEnclosingRequest(HttpEntityEnclosingRequest enclosingRequest, Object param, FlutyRemoteApiRule rule) {
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // almost copy from LaFormSender, should be unified after refactoring
            final DfBeanDesc beanDesc = DfBeanDescFactory.getBeanDesc(param.getClass());
            final List<NameValuePair> parameters = new ArrayList<>();
            beanDesc.getProppertyNameList().stream().forEach(proppertyName -> {
                final DfPropertyDesc propertyDesc = beanDesc.getPropertyDesc(proppertyName);

                // *for multi-part, is needed because file properties are handled later
                if (MultipartFormFile.class.isAssignableFrom(propertyDesc.getPropertyType())) {
                    return;
                }

                final String serializedParameterName = asSerializedParameterName(propertyDesc);
                final Object plainValue = beanDesc.getPropertyDesc(proppertyName).getValue(param);
                if (plainValue != null && Iterable.class.isAssignableFrom(plainValue.getClass())) {
                    final Iterable<?> plainValueIterable = (Iterable<?>) plainValue;
                    plainValueIterable.forEach(value -> {
                        parameters.add(createBasicNameValuePair(serializedParameterName, asSerializedParameterValue(value)));
                    });
                } else {
                    parameters.add(createBasicNameValuePair(serializedParameterName, asSerializedParameterValue(plainValue)));
                }
            });
            // _/_/_/_/_/_/_/_/_/_/

            // *for multi-part, use multi-part entity
            enclosingRequest.setEntity(createMultipartEntity(param, parameters));

            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // completely copy from LaFormSender, should be unified after refactoring
            readySendReceiveLogIfNeeds(rule, param, parameters);
            // _/_/_/_/_/_/_/_/_/_/
        }

        private HttpEntity createMultipartEntity(Object objParam, List<NameValuePair> textParameters) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (NameValuePair pair : textParameters) {
                String value = pair.getValue();
                builder.addTextBody(pair.getName(), value != null ? value : ""); // #thinking empty string OK?
            }
            setupMultipartFormFile(objParam, builder);
            return builder.build();
        }

        private void setupMultipartFormFile(Object objParam, MultipartEntityBuilder builder) {
            // depends on one API here for now
            RemoteFrMultipartParam param = (RemoteFrMultipartParam) objParam;
            MultipartFormFile uploadedFile = param.uploadedFile;
            InputStream fileStream;
            try {
                fileStream = uploadedFile.getInputStream();
            } catch (IOException e) {
                throw new IllegalStateException("Failed to get input stream: " + uploadedFile, e);
            }
            ContentType contentType = ContentType.create(uploadedFile.getContentType());
            String fileName = uploadedFile.getFileName();
            builder.addBinaryBody("uploadedFile", fileStream, contentType, fileName);
        }
    }
}
