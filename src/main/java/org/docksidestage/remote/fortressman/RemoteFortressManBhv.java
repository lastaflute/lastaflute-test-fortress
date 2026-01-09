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
package org.docksidestage.remote.fortressman;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.dbflute.helper.beans.DfPropertyDesc;
import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.mapping.FlRemoteMappingPolicy;
import org.dbflute.remoteapi.mapping.FlVacantMappingPolicy;
import org.dbflute.remoteapi.sender.body.RequestBodySender;
import org.docksidestage.remote.fortressman.base.RemoteFrUnifiedFailureResult;
import org.docksidestage.remote.fortressman.base.RemoteFrUnifiedFailureResult.RemoteUnifiedFailureType;
import org.docksidestage.remote.fortressman.wx.multipart.RemoteFrMultipartParam;
import org.docksidestage.remote.fortressman.wx.xml.RemoteXmlParam;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
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
public class RemoteFortressManBhv extends LastaRemoteBehavior {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RemoteFortressManBhv(RequestManager requestManager) {
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

        rule.handleFailureResponseAs(RemoteFrUnifiedFailureResult.class); // server-managed message way
        rule.translateClientError(resource -> {
            RemoteApiHttpClientErrorException clientError = resource.getClientError();
            if (clientError.getHttpStatus() == 400) { // controlled client error
                RemoteFrUnifiedFailureResult result = (RemoteFrUnifiedFailureResult) clientError.getFailureResponse().get();
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
    //                                                                   Request Multipart
    //                                                                   =================
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // *self-call so you cannot request action to action when hot deploy
    // _/_/_/_/_/_/_/_/_/_/
    public void requestWxMultipart(RemoteFrMultipartParam param) {
        doRequestPost(void.class, "/wx/request/multipart/upload", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(new MyMultipartFormSender(new FlVacantMappingPolicy()));
        });
    }

    protected static class MyMultipartFormSender extends LaFormSender {

        public MyMultipartFormSender(FlRemoteMappingPolicy mappingPolicy) {
            super(mappingPolicy);
        }

        @Override
        protected boolean isExceptParameter(Object param, DfPropertyDesc propertyDesc, FlutyRemoteApiRule rule) {
            return MultipartFormFile.class.isAssignableFrom(propertyDesc.getPropertyType());
        }

        @Override
        protected HttpEntity prepareEnclosedHttpEntity(Object param, List<NameValuePair> parameterList, FlutyRemoteApiRule rule) {
            return createMultipartEntity(param, parameterList);
        }

        private HttpEntity createMultipartEntity(Object param, List<NameValuePair> textParameterList) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (NameValuePair pair : textParameterList) {
                builder.addTextBody(pair.getName(), filterTextBodyValue(pair.getValue()));
            }
            setupMultipartFormFile(param, builder);
            return builder.build();
        }

        private String filterTextBodyValue(String value) { // vaue is null allowed
            return value != null ? value : ""; // #thinking cannot set null so... empty string? by jflute (2019/03/28)
        }

        private void setupMultipartFormFile(Object objParam, MultipartEntityBuilder builder) {
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // *depends on one API method here for now
            // _/_/_/_/_/_/_/_/_/_/
            RemoteFrMultipartParam param = (RemoteFrMultipartParam) objParam;
            registerFormFile(builder, "uploadedFile", param.uploadedFile);
        }

        private void registerFormFile(MultipartEntityBuilder builder, String partName, MultipartFormFile formFile) {
            try (InputStream fileStream = formFile.getInputStream()) {
                ContentType contentType = ContentType.create(formFile.getContentType());
                String fileName = formFile.getFileName();
                builder.addBinaryBody(partName, fileStream, contentType, fileName);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to get input stream: formFile" + formFile, e);
            }
        }
    }

    // ===================================================================================
    //                                                                         Request XML
    //                                                                         ===========
    public void requestXmlBody(RemoteXmlParam param) {
        doRequestPost(void.class, "/wx/request/xml/body", noMoreUrl(), param, rule -> {
            rule.sendBodyBy(new RequestBodySender() {
                @Override
                public void prepareEnclosingRequest(HttpEntityEnclosingRequest enclosingRequest, Object param, FlutyRemoteApiRule rule) {
                    RemoteXmlParam xmlParam = (RemoteXmlParam) param;

                    StringBuilder sb = new StringBuilder();
                    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");
                    sb.append("<maihama>").append("\n");
                    sb.append("  <sea>").append(xmlParam.sea).append("</sea>").append("\n");
                    sb.append("  <land>").append(xmlParam.land).append("</land>").append("\n");
                    sb.append("</maihama>").append("\n");
                    String xml = sb.toString();

                    final String charsetName = rule.getRequestBodyCharset().name();
                    final StringEntity entity = new StringEntity(xml, charsetName);
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "text/xml"));
                    enclosingRequest.setEntity(entity);
                }
            });
        });
    }
}
