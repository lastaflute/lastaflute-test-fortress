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
package org.docksidestage.app.web;

import java.util.Map;

import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.meta.SwaggerGenerator;
import org.lastaflute.meta.agent.SwaggerAgent;
import org.lastaflute.meta.swagger.diff.SwaggerDiff;
import org.lastaflute.meta.swagger.web.LaActionSwaggerable;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.response.StreamResponse;
import org.lastaflute.web.servlet.request.RequestManager;

import jakarta.annotation.Resource;

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
    // -----------------------------------------------------
    //                                              Standard
    //                                              --------
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
            op.addHeaderParameter("hangar", "mystic"); // test for header

            // test for HTTP status
            //op.derivedSuccessHttpStatus(meta -> {
            //    TypicalStructuredSuccessHttpStatusHandler statusHandler = new TypicalStructuredSuccessHttpStatusHandler();
            //    return statusHandler.deriveSuccessStatus(meta.getActionExecute()).orElse(null);
            //});
            //op.derivedFailureHttpStatus(meta -> {
            //    SwaggerFailureHttpStatusResource resource = new SwaggerFailureHttpStatusResource();
            //    resource.addMapping(404, EntityAlreadyDeletedException.class);
            //    resource.addMapping(404, EntityAlreadyExistsException.class);
            //    resource.addMapping(409, IllegalStateException.class);
            //    return resource;
            //});
            //op.suppressDefaultFailureHttpStatus();
        });
        return asJson(swaggerMap).switchMappingOption(op -> {}); // not to depend on application settings
    }

    // -----------------------------------------------------
    //                                           Application
    //                                           -----------
    @Execute
    public JsonResponse<Map<String, Object>> appjson() { // using application json
        verifySwaggerAllowed();
        Map<String, Object> swaggerMap = readResourceJson(jsonManager, "/swagger/fortress_openapi3_example.json");
        return asJson(swaggerMap).switchMappingOption(op -> {}); // not to depend on application settings
    }

    // -----------------------------------------------------
    //                                          Swagger Diff
    //                                          ------------
    @Execute
    public StreamResponse diff(SwaggerDiffForm form) {
        verifySwaggerAllowed();
        validateApi(form, messages -> {});
        String diff = new SwaggerDiff().diffFromLocations(form.leftPath, form.rightPath);
        return asStream("").data(diff.getBytes()).contentType("text/markdown").headerContentDispositionInline();
    }

    // -----------------------------------------------------
    //                                             Targeting
    //                                             ---------
    @Execute
    public JsonResponse<Map<String, Object>> targetJson(SwaggerTargetJsonForm form) { // dynamic for various case
        verifySwaggerAllowed();
        Map<String, Object> swaggerMap = readResourceJson(jsonManager, "/swagger/" + form.path + ".json");
        return asJson(swaggerMap).switchMappingOption(op -> {}); // not to depend on application settings
    }

    @Execute
    public JsonResponse<Map<String, Object>> limitedTargetJson() { // for small test
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
        return asJson(swaggerMap).switchMappingOption(op -> {}); // not to depend on application settings
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private void verifySwaggerAllowed() { // also check in ActionAdjustmentProvider
        verifyOrClientError("Swagger is not enabled.", config.isSwaggerEnabled());
    }
}
