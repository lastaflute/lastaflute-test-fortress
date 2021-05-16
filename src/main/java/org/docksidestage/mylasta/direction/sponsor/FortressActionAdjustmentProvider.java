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

import org.dbflute.util.DfTypeUtil;
import org.docksidestage.mylasta.direction.sponsor.planner.ActionOptionAgent;
import org.docksidestage.mylasta.direction.sponsor.planner.MemorableRestlikeRouter;
import org.docksidestage.mylasta.direction.sponsor.planner.MemorableSmartphoneMapper;
import org.docksidestage.mylasta.direction.sponsor.planner.NumericBasedRestfulRouter;
import org.lastaflute.web.path.ActionAdjustmentProvider;
import org.lastaflute.web.path.FormMappingOption;
import org.lastaflute.web.path.ResponseReflectingOption;
import org.lastaflute.web.path.UrlMappingOption;
import org.lastaflute.web.path.UrlMappingResource;
import org.lastaflute.web.path.UrlReverseOption;
import org.lastaflute.web.path.UrlReverseResource;
import org.lastaflute.web.validation.VaConfigSetupper;

/**
 * @author jflute
 */
public class FortressActionAdjustmentProvider implements ActionAdjustmentProvider {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ActionOptionAgent actionOptionAgent;

    // -----------------------------------------------------
    //                                         Cached Option
    //                                         -------------
    protected final NumericBasedRestfulRouter restfulRouter;
    protected final FormMappingOption formMappingOption;
    protected final VaConfigSetupper vaConfigSetupper;
    protected final ResponseReflectingOption responseReflectingOption;

    protected final MemorableRestlikeRouter memorableRestlikeRouter;
    protected final MemorableSmartphoneMapper memorableSmartphoneMapper;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public FortressActionAdjustmentProvider() {
        actionOptionAgent = createActionOptionAgent();

        restfulRouter = actionOptionAgent.createRestfulRouter();
        formMappingOption = actionOptionAgent.createFormMappingOption();
        vaConfigSetupper = actionOptionAgent.createValidatorConfigSetupper();
        responseReflectingOption = actionOptionAgent.createResponseReflectingOption();

        memorableRestlikeRouter = new MemorableRestlikeRouter();
        memorableSmartphoneMapper = new MemorableSmartphoneMapper();
    }

    protected ActionOptionAgent createActionOptionAgent() {
        return new ActionOptionAgent();
    }

    // ===================================================================================
    //                                                                             Routing
    //                                                                             =======
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
