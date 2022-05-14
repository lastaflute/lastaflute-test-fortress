/*
 * Copyright 2015-2022 the original author or authors.
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
package org.docksidestage.mylasta.di.naming.styling;

import org.dbflute.util.Srl;
import org.lastaflute.di.naming.styling.StylingFreedomInterfaceMapper;

/**
 * The mapper of freedom interface styling your naming convention. <br>
 * This class name is specified in lasta_di.properties.
 * @author jflute
 */
public class FortressStylingFreedomInterfaceMapper implements StylingFreedomInterfaceMapper {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // e.g.
    //  biz.onionarc.domain.repository.OnionarcSeaRepository (interface)
    //  biz.onionarc.infrastructure.OnionarcSeaInfraRepository (implementation)
    // _/_/_/_/_/_/_/_/_/_/
    private static final String ONIONARC_DOMAIN_REPOSITORY = ".biz.onionarc.domain.repository.";
    private static final String ONIONARC_INFRASTRUCTURE = ".biz.onionarc.infrastructure.";

    private static final String REPOSITORY = "Repository";
    private static final String INFRA_REPOSITORY = "InfraRepository";

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // default constructor is required so don't change constructor argument definition

    // ===================================================================================
    //                                                                   to Implementation
    //                                                                   =================
    @Override
    public String toImplementationClassName(String interfaceClassName) {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // e.g.
        //  from: ...biz.onionarc.domain.repository.OnionarcSeaRepository (interface)
        //    to: ...biz.onionarc.infrastructure.OnionarcSeaInfraRepository (implementation)
        // _/_/_/_/_/_/_/_/_/_/
        if (isDomainRepository(interfaceClassName)) {
            return convertToInfraRepository(interfaceClassName);
        }
        return null;
    }

    private boolean isDomainRepository(String interfaceClassName) {
        return determineClassByKeyword(interfaceClassName, ONIONARC_DOMAIN_REPOSITORY, REPOSITORY);
    }

    private String convertToInfraRepository(String name) {
        name = replace(name, ONIONARC_DOMAIN_REPOSITORY, ONIONARC_INFRASTRUCTURE);
        name = replace(name, REPOSITORY, INFRA_REPOSITORY);
        return name;
    }

    // ===================================================================================
    //                                                                        to Interface
    //                                                                        ============
    @Override
    public String toInterfaceClassName(String implementationClassName) {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // e.g.
        //  from: ...biz.onionarc.infrastructure.OnionarcSeaInfraRepository (implementation)
        //    to: ...biz.onionarc.domain.repository.OnionarcSeaRepository (interface)
        // _/_/_/_/_/_/_/_/_/_/
        if (isInfraRepository(implementationClassName)) {
            return convertToDomainRepository(implementationClassName);
        }
        return null;
    }

    private boolean isInfraRepository(String implementationClassName) {
        return determineClassByKeyword(implementationClassName, ONIONARC_INFRASTRUCTURE, INFRA_REPOSITORY);
    }

    private String convertToDomainRepository(String name) {
        name = replace(name, ONIONARC_INFRASTRUCTURE, ONIONARC_DOMAIN_REPOSITORY);
        name = replace(name, INFRA_REPOSITORY, REPOSITORY);
        return name;
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private boolean determineClassByKeyword(String name, String pkgKeyword, String suffix) {
        return name.contains(pkgKeyword) && name.endsWith(suffix);
    }

    private String replace(String str, String fromStr, String toStr) {
        return Srl.replace(str, fromStr, toStr);
    }
}
