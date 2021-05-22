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
package org.docksidestage.app.web;

import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.dbflute.helper.filesystem.FileTextIO;
import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.engine.RealJsonEngine;
import org.lastaflute.meta.SwaggerGenerator;
import org.lastaflute.meta.agent.SwaggerAgent;
import org.lastaflute.meta.diff.SwaggerDiffGenerator;
import org.lastaflute.meta.web.LaActionSwaggerable;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.response.StreamResponse;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The action to show swaggar-ui.
 * @author awaawa
 * @author jflute
 */
@AllowAnyoneAccess
public class SwaggerAction extends FortressBaseAction implements LaActionSwaggerable {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JsonManager jsonManager;
    @Resource
    private RequestManager requestManager;
    @Resource
    private FortressConfig config;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Execute
    public HtmlResponse index() {
        verifySwaggerAllowed();
        String swaggerJsonUrl = toActionUrl(SwaggerAction.class, moreUrl("json")); // is default method
        return new SwaggerAgent(requestManager).prepareSwaggerUiResponse(swaggerJsonUrl);
    }

    @Execute
    public JsonResponse<Map<String, Object>> json() { // using Lasta-presents json
        verifySwaggerAllowed();
        Map<String, Object> swaggerMap = new SwaggerGenerator().generateSwaggerMap(op -> {
            op.addHeaderParameter("hangar", "mystic");
        });
        return asJson(swaggerMap).switchMappingOption(op -> {
        }); // not to depend on application settings
    }

    @Execute
    public JsonResponse<Map<String, Object>> jsonLimitedTaget() {
        verifySwaggerAllowed();
        Map<String, Object> swaggerMap = new SwaggerGenerator().generateSwaggerMap(op -> {
            op.derivedTargetActionDocMeta(actionDocMeta -> {
                return DfCollectionUtil.newArrayList( //
                        "/lido/following/list", //
                        "/lido/following/register", //
                        "/lido/following/delete", //
                        "/lido/product/price/update/{productId}").contains(actionDocMeta.getUrl());
            });
        });
        return asJson(swaggerMap).switchMappingOption(op -> {
        }); // not to depend on application settings
    }

    @Execute
    public JsonResponse<Map<String, Object>> targetJson(SwaggerTargetJsonForm form) {
        verifySwaggerAllowed();
        Map<String, Object> swaggerMap = prepareJson(form.path);
        return asJson(swaggerMap).switchMappingOption(op -> {
        }); // not to depend on application settings
    }

    @Execute
    public StreamResponse diff(SwaggerDiffForm form) {
        verifySwaggerAllowed();
        validateApi(form, messages -> {
        });
        String diff = new SwaggerDiffGenerator().diffFromLocations(form.leftPath, form.rightPath);
        return asStream("").data(diff.getBytes()).contentType("text/markdown").headerContentDispositionInline();
    }

    private void verifySwaggerAllowed() { // also check in ActionAdjustmentProvider
        verifyOrClientError("Swagger is not enabled.", config.isSwaggerEnabled());
    }

    private Map<String, Object> prepareJson(String file) {
        RealJsonEngine simpleEngine = jsonManager.newRuledEngine(new JsonEngineResource());
        InputStream ins = getClass().getClassLoader().getResourceAsStream("/swagger/" + file + ".json");
        verifyOrClientError("Swagger file is not found.", ins != null);
        String json = new FileTextIO().encodeAsUTF8().read(ins);
        @SuppressWarnings("unchecked")
        Map<String, Object> swaggerMap = simpleEngine.fromJson(json, Map.class);
        return swaggerMap;
    }

}
