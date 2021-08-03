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
package org.docksidestage.mylasta.direction.sponsor;

import javax.servlet.http.HttpServletRequest;

import org.dbflute.util.DfTypeUtil;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.direction.sponsor.planner.ActionOptionAgent;
import org.docksidestage.mylasta.direction.sponsor.planner.MemorableRestlikeRouter;
import org.docksidestage.mylasta.direction.sponsor.planner.MemorableSmartphoneMapper;
import org.lastaflute.web.path.ActionAdjustmentProvider;
import org.lastaflute.web.path.FormMappingOption;
import org.lastaflute.web.path.ResponseReflectingOption;
import org.lastaflute.web.path.UrlMappingOption;
import org.lastaflute.web.path.UrlMappingResource;
import org.lastaflute.web.path.UrlReverseOption;
import org.lastaflute.web.path.UrlReverseResource;
import org.lastaflute.web.path.restful.router.RestfulRouter;
import org.lastaflute.web.validation.VaConfigSetupper;

/**
 * @author jflute
 */
public class FortressActionAdjustmentProvider implements ActionAdjustmentProvider {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config;
    protected final ActionOptionAgent actionOptionAgent;

    // -----------------------------------------------------
    //                                         Cached Option
    //                                         -------------
    protected final RestfulRouter restfulRouter;
    protected final FormMappingOption formMappingOption;
    protected final VaConfigSetupper vaConfigSetupper;
    protected final ResponseReflectingOption responseReflectingOption;

    protected final MemorableRestlikeRouter memorableRestlikeRouter;
    protected final MemorableSmartphoneMapper memorableSmartphoneMapper;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public FortressActionAdjustmentProvider(FortressConfig config) {
        this.config = config;
        this.actionOptionAgent = newActionOptionAgent(config);

        this.restfulRouter = actionOptionAgent.createRestfulRouter();
        this.formMappingOption = actionOptionAgent.createFormMappingOption();
        this.vaConfigSetupper = actionOptionAgent.createValidatorConfigSetupper();
        this.responseReflectingOption = actionOptionAgent.createResponseReflectingOption();

        // omoide
        this.memorableRestlikeRouter = new MemorableRestlikeRouter();
        this.memorableSmartphoneMapper = new MemorableSmartphoneMapper();
    }

    protected ActionOptionAgent newActionOptionAgent(FortressConfig config) {
        return new ActionOptionAgent(config);
    }

    // ===================================================================================
    //                                                                             Routing
    //                                                                             =======
    // -----------------------------------------------------
    //                                 Typical Determination
    //                                 ---------------------
    @Override
    public boolean isForced404NotFoundRouting(HttpServletRequest request, String requestPath) {
        if (!config.isSwaggerEnabled() && isSwaggerRequest(requestPath)) { // e.g. swagger's html, css
            return true; // to suppress direct access to swagger resources at e.g. production
        }
        return false;
    }

    private boolean isSwaggerRequest(String requestPath) {
        return requestPath.startsWith("/webjars/swagger-ui") || requestPath.startsWith("/swagger");
    }

    // -----------------------------------------------------
    //                                           URL Mapping
    //                                           -----------
    @Override
    public String customizeActionMappingRequestPath(String requestPath) { // old style method
        return memorableRestlikeRouter.makeRestlike(requestPath);
    }

    @Override
    public UrlMappingOption customizeActionUrlMapping(UrlMappingResource resource) {
        return restfulRouter.toRestfulMappingPath(resource).orElseGet(() -> {
            return memorableSmartphoneMapper.customizeActionUrlMapping(resource).orElse(null);
        });
    }

    @Override
    public UrlReverseOption customizeActionUrlReverse(UrlReverseResource resource) {
        return restfulRouter.toRestfulReversePath(resource).orElseGet(() -> {
            return memorableSmartphoneMapper.customizeActionUrlReverse(resource).orElse(null);
        });
    }

    // ===================================================================================
    //                                                                        Form Mapping
    //                                                                        ============
    @Override
    public FormMappingOption adjustFormMapping() {
        return formMappingOption;
    }

    // ===================================================================================
    //                                                                          Validation
    //                                                                          ==========
    @Override
    public VaConfigSetupper adjustValidatorConfig() {
        return vaConfigSetupper;
    }

    // ===================================================================================
    //                                                                     Action Response
    //                                                                     ===============
    @Override
    public ResponseReflectingOption adjustResponseReflecting() {
        return responseReflectingOption;
    }

    // ===================================================================================
    //                                                                       InOut Logging
    //                                                                       =============
    @Override
    public boolean isUseInOutLogging() {
        return true;
    }

    // example:
    //@Override
    //public InOutLogOption adjustInOutLogging() {
    //    return inOutLogOption;
    //}

    // ===================================================================================
    //                                                                       Error Logging
    //                                                                       =============
    @Override
    public boolean isSuppressServerErrorLogging(Throwable cause) {
        return actionOptionAgent.isSuppressServerErrorLogging(cause);
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return DfTypeUtil.toClassTitle(this) + ":{}";
    }
}
