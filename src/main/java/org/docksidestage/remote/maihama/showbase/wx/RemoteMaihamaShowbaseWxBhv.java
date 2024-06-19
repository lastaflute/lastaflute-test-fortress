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
package org.docksidestage.remote.maihama.showbase.wx;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.function.Consumer;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.dbflute.remoteapi.FlutyRemoteApi;
import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.http.SupportedHttpMethod;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodGetParam;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodGetReturn;
import org.lastaflute.remoteapi.LastaRemoteApi;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The behavior for remote API of wx.
 * <p>
 * You can implement your original methods here.
 * This class remains when re-generating.
 * </p>
 * @author FreeGen
 * @author jflute
 */
public class RemoteMaihamaShowbaseWxBhv extends BsRemoteMaihamaShowbaseWxBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public RemoteMaihamaShowbaseWxBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                      Manual Request
    //                                                                      ==============
    // -----------------------------------------------------
    //                                               Remogen
    //                                               -------
    public RemoteWxRemogenMethodGetReturn requestRemogenMethodOnbodyjsonGet(Consumer<RemoteWxRemogenMethodGetParam> paramLambda) {
        RemoteWxRemogenMethodGetParam param = new RemoteWxRemogenMethodGetParam();
        paramLambda.accept(param);
        MaihamaShowbaseRemoteApi api = prepareMyRemoteApi();
        Class<RemoteWxRemogenMethodGetReturn> returnType = RemoteWxRemogenMethodGetReturn.class;
        String urlBase = getUrlBase();
        return api.requestGetEnclosing(returnType, urlBase, "/wx/remogen/method/enclosing", noMoreUrl(), param, rule -> {});
    }

    // ===================================================================================
    //                                                                Remote API Extension
    //                                                                ====================
    private MaihamaShowbaseRemoteApi prepareMyRemoteApi() {
        return (MaihamaShowbaseRemoteApi) remoteApi;
    }

    @Override
    protected FlutyRemoteApi newRemoteApi(Consumer<FlutyRemoteApiRule> ruleSetupper, Object callerExp) {
        return new MaihamaShowbaseRemoteApi(ruleSetupper, callerExp);
    }

    public static class MaihamaShowbaseRemoteApi extends LastaRemoteApi {

        public MaihamaShowbaseRemoteApi(Consumer<FlutyRemoteApiRule> defaultOpLambda, Object callerExp) {
            super(defaultOpLambda, callerExp);
        }

        public <RETURN> RETURN requestGetEnclosing(Type returnType, String urlBase, String actionPath, Object[] pathVariables, Object param,
                Consumer<FlutyRemoteApiRule> ruleLambda) {
            SupportedHttpMethod httpMethod = SupportedHttpMethod.GET;
            return doRequestEnclosing(returnType, urlBase, actionPath, pathVariables, param, ruleLambda, httpMethod, url -> {
                return new HttpGetEnclosing(url);
            });
        }
    }

    public static class HttpGetEnclosing extends HttpEntityEnclosingRequestBase {

        public final static String METHOD_NAME = "GET";

        public HttpGetEnclosing(final String uri) {
            setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }
    }
}
