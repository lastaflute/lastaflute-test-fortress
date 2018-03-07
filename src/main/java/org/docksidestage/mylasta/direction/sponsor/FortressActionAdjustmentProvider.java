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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Configuration;
import javax.validation.constraints.Size;
import javax.validation.valueextraction.ValueExtractor;

import org.dbflute.util.DfTypeUtil;
import org.dbflute.util.Srl;
import org.docksidestage.bizfw.validation.SizeValidatorForImmutableList;
import org.docksidestage.bizfw.validation.ValueExtractorForImmutableList;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.exception.Forced404NotFoundException;
import org.lastaflute.web.path.ActionAdjustmentProvider;
import org.lastaflute.web.path.FormMappingOption;
import org.lastaflute.web.path.ResponseReflectingOption;
import org.lastaflute.web.ruts.process.populate.FormYourCollectionResource;
import org.lastaflute.web.validation.VaConfigSetupper;

/**
 * @author jflute
 */
public class FortressActionAdjustmentProvider implements ActionAdjustmentProvider {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // -----------------------------------------------------
    //                                     REST-like Routing
    //                                     -----------------
    protected static final Pattern PRODUCTS_ENTRY_PATTERN = Pattern.compile("^/products/[0-9]+/?$");
    protected static final Pattern PRODUCTS_INTERNAL_PATTERN = Pattern.compile("^/products/detail/[0-9]+/?$");
    protected static final Pattern LMLIKE_ENTRY_PATTERN = Pattern.compile("^/wx/routing/restlike/lmlike/[a-zA-Z0-9]+/[0-9]+/?$");
    protected static final Pattern LMLIKE_INTERNAL_PATTERN =
            Pattern.compile("^/wx/routing/restlike/lmlike/category/[a-zA-Z0-9]+/[0-9]+/?$");
    protected static final List<RestlikeResource> restlikeResourceList;
    static {
        List<RestlikeResource> workingList = new ArrayList<RestlikeResource>();
        workingList.add(new RestlikeResource(PRODUCTS_ENTRY_PATTERN, PRODUCTS_INTERNAL_PATTERN, "products", "detail"));
        workingList.add(new RestlikeResource(LMLIKE_ENTRY_PATTERN, LMLIKE_INTERNAL_PATTERN, "lmlike", "category"));
        restlikeResourceList = Collections.unmodifiableList(workingList);
    }
    protected static final RestlikeRouter restlikeRouter = new RestlikeRouter();

    protected static class RestlikeResource {

        protected Pattern entryPattern;
        protected Pattern internalPattern;
        protected String baseWord;
        protected String internalWord;

        public RestlikeResource(Pattern entryPattern, Pattern internalPattern, String baseWord, String internalWord) {
            this.entryPattern = entryPattern;
            this.internalPattern = internalPattern;
            this.baseWord = baseWord;
            this.internalWord = internalWord;
        }

        public Pattern getEntryPattern() {
            return entryPattern;
        }

        public Pattern getInternalPattern() {
            return internalPattern;
        }

        public String getBaseWord() {
            return baseWord;
        }

        public String getInternalWord() {
            return internalWord;
        }
    }

    protected static class RestlikeRouter {

        public String makeRestlike(String requestPath, List<RestlikeResource> resourceList) {
            for (RestlikeResource resource : resourceList) {
                Pattern entryPattern = resource.getEntryPattern();
                Pattern internalPattern = resource.getInternalPattern();
                String baseWord = resource.getBaseWord();
                String internalWord = resource.getInternalWord();
                String restlike = doMakeRestlike(requestPath, entryPattern, internalPattern, baseWord, internalWord);
                if (restlike != null) {
                    return restlike;
                }
            }
            return null;
        }

        protected String doMakeRestlike(String requestPath, Pattern entryPattern, Pattern internalPattern, String baseWord,
                String internalWord) {
            if (entryPattern.matcher(requestPath).matches()) {
                return Srl.replace(requestPath, baseWord + "/", baseWord + "/" + internalWord + "/");
            } else if (internalPattern.matcher(requestPath).matches()) {
                handleInternalRequestPathDirectAccess(requestPath);
            }
            return null;
        }

        protected void handleInternalRequestPathDirectAccess(String requestPath) {
            throw new Forced404NotFoundException("Cannot access to internal path directly: " + requestPath, UserMessages.empty());
        }
    }

    // -----------------------------------------------------
    //                                          Form Mapping
    //                                          ------------
    protected static final FormMappingOption formMappingOption = new FormMappingOption().filterSimpleTextParameter((parameter, meta) -> {
        return parameter.trim();
    }).yourCollection(new FormYourCollectionResource(ImmutableList.class, mutable -> {
        return Lists.immutable.ofAll(mutable);
    })).yourCollection(new FormYourCollectionResource(MutableList.class, mutable -> {
        return Lists.mutable.ofAll(mutable);
    }));

    // -----------------------------------------------------
    //                                       Action Response
    //                                       ---------------
    protected static final ResponseReflectingOption responseReflectingOption = new ResponseReflectingOption().warnJsonBeanValidationError();

    // ===================================================================================
    //                                                                             Routing
    //                                                                             =======
    @Override
    public String customizeActionMappingRequestPath(String requestPath) {
        String restlike = restlikeRouter.makeRestlike(requestPath, restlikeResourceList);
        if (restlike != null) {
            return restlike;
        }
        return ActionAdjustmentProvider.super.customizeActionMappingRequestPath(requestPath);
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
    //    return new InOutLogOption().async();
    //}

    // ===================================================================================
    //                                                                       Error Logging
    //                                                                       =============
    @Override
    public boolean isSuppressServerErrorLogging(Throwable cause) {
        final String msg = cause.getMessage();
        return msg != null && msg.contains("Broken pipe"); // also needs IOException determination?
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return DfTypeUtil.toClassTitle(this) + ":{}";
    }
}
