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
package org.docksidestage.mylasta.direction.sponsor.planner;

import java.util.Arrays;

import javax.validation.Configuration;
import javax.validation.constraints.Size;
import javax.validation.valueextraction.ValueExtractor;

import org.docksidestage.bizfw.json.RuledJsonEngineKeeper;
import org.docksidestage.bizfw.validation.SizeValidatorForImmutableList;
import org.docksidestage.bizfw.validation.ValueExtractorForImmutableList;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.web.path.FormMappingOption;
import org.lastaflute.web.path.ResponseReflectingOption;
import org.lastaflute.web.path.restful.NumericBasedRestfulRouter;
import org.lastaflute.web.ruts.inoutlogging.InOutLogOption;
import org.lastaflute.web.ruts.process.populate.FormYourCollectionResource;
import org.lastaflute.web.validation.VaConfigSetupper;

/**
 * @author jflute
 */
public class ActionOptionAgent {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public ActionOptionAgent(FortressConfig config) {
        this.config = config;
    }

    // ===================================================================================
    //                                                                             Routing
    //                                                                             =======
    // -----------------------------------------------------
    //                                               Swagger
    //                                               -------
    public boolean isDisabledSwaggerRequest(String requestPath) {
        return !config.isSwaggerEnabled() && isSwaggerRequest(requestPath); // e.g. swagger's html, css
    }

    private boolean isSwaggerRequest(String requestPath) {
        return requestPath.startsWith("/webjars/swagger-ui") || requestPath.startsWith("/swagger");
    }

    // -----------------------------------------------------
    //                                               Restful
    //                                               -------
    public NumericBasedRestfulRouter createRestfulRouter() {
        return new NumericBasedRestfulRouter();
    }

    // ===================================================================================
    //                                                                        Form Mapping
    //                                                                        ============
    public FormMappingOption createFormMappingOption() {
        FormMappingOption option = new FormMappingOption();

        option.filterSimpleTextParameter((parameter, meta) -> {
            return parameter.trim();
        });

        option.yourCollection(new FormYourCollectionResource(ImmutableList.class, mutable -> {
            return Lists.immutable.ofAll(mutable);
        }));
        option.yourCollection(new FormYourCollectionResource(MutableList.class, mutable -> {
            return Lists.mutable.ofAll(mutable);
        }));

        RuledJsonEngineKeeper jsonEngineKeeper = ContainerUtil.getComponent(RuledJsonEngineKeeper.class);
        option.parseJsonBy(jsonEngineKeeper.prepareActionJsonEngine());
        return option;
    }

    // ===================================================================================
    //                                                                          Validation
    //                                                                          ==========
    public VaConfigSetupper createValidatorConfigSetupper() {
        return conf -> prepareImmutableList(conf);
    }

    protected void prepareImmutableList(Configuration<?> conf) {
        ((HibernateValidatorConfiguration) conf).addMapping(createImmutableListConstraintMapping()); // always can cast
        conf.addValueExtractor(createImmutableListValueExtractor());
    }

    protected DefaultConstraintMapping createImmutableListConstraintMapping() {
        DefaultConstraintMapping mapping = new DefaultConstraintMapping();
        mapping.constraintDefinition(Size.class).validatedBy(SizeValidatorForImmutableList.class);
        return mapping;
    }

    protected ValueExtractor<?> createImmutableListValueExtractor() {
        return new ValueExtractorForImmutableList();
    }

    // ===================================================================================
    //                                                                     Action Response
    //                                                                     ===============
    public ResponseReflectingOption createResponseReflectingOption() {
        ResponseReflectingOption option = new ResponseReflectingOption();

        // comment out if you test validation as warning
        //option.warnJsonBeanValidationError();
        // comment out if you test empty body treated as empty object
        //option.treatJsonEmptyBodyAsEmptyObject();

        RuledJsonEngineKeeper jsonEngineKeeper = ContainerUtil.getComponent(RuledJsonEngineKeeper.class);
        option.writeJsonBy(jsonEngineKeeper.prepareActionJsonEngine());
        return option;
    }

    // ===================================================================================
    //                                                                       InOut Logging
    //                                                                       =============
    public InOutLogOption createInOutLogOption() {
        final InOutLogOption option = new InOutLogOption();
        option.showRequestHeader(Arrays.asList("Accept-Encoding", "Accept-Language"));
        option.showResponseHeader(Arrays.asList("DaTe", "detarame", "Cache-Control"));
        return option;
    }

    // ===================================================================================
    //                                                                       Error Logging
    //                                                                       =============
    public boolean isSuppressServerErrorLogging(Throwable cause) {
        final String msg = cause.getMessage();
        return msg != null && msg.contains("Broken pipe"); // also needs IOException determination?
    }
}