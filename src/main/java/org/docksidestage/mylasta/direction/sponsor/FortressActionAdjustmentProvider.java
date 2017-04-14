/*
 * Copyright 2015-2017 the original author or authors.
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

import javax.validation.Configuration;
import javax.validation.constraints.Size;

import org.dbflute.util.DfTypeUtil;
import org.docksidestage.bizfw.validation.SizeValidatorForImmutableList;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.lastaflute.web.path.ActionAdjustmentProvider;
import org.lastaflute.web.path.FormMappingOption;
import org.lastaflute.web.path.ResponseReflectingOption;
import org.lastaflute.web.ruts.process.populate.FormYourCollectionResource;

/**
 * @author jflute
 */
public class FortressActionAdjustmentProvider implements ActionAdjustmentProvider {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final FormMappingOption formMappingOption = new FormMappingOption().filterSimpleTextParameter((parameter, meta) -> {
        return parameter.trim();
    }).yourCollection(new FormYourCollectionResource(ImmutableList.class, mutable -> {
        return Lists.immutable.ofAll(mutable);
    }));
    private static final ResponseReflectingOption responseReflectingOption = new ResponseReflectingOption().warnJsonBeanValidationError();

    // ===================================================================================
    //                                                                     Option Override
    //                                                                     ===============
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // you can adjust your actions by overriding
    // default methods defined at the interface
    // _/_/_/_/_/_/_/_/_/_/
    @Override
    public FormMappingOption adjustFormMapping() {
        return formMappingOption;
    }

    @Override
    public ResponseReflectingOption adjustResponseReflecting() {
        return responseReflectingOption;
    }

    // ===================================================================================
    //                                                                          Validation
    //                                                                          ==========
    // example:
    //@Override
    //public VaConfigSetupper adjustValidatorConfig() {
    //    return conf -> prepareImmutableList(conf);
    //}

    protected void prepareImmutableList(Configuration<?> conf) {
        DefaultConstraintMapping mapping = new DefaultConstraintMapping();
        mapping.constraintDefinition(Size.class).validatedBy(SizeValidatorForImmutableList.class);
        ((HibernateValidatorConfiguration) conf).addMapping(mapping); // always can cast
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return DfTypeUtil.toClassTitle(this) + ":{}";
    }
}
