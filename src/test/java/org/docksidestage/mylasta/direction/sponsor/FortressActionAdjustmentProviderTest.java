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
package org.docksidestage.mylasta.direction.sponsor;

import javax.annotation.Resource;

import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute at bay maihama
 */
public class FortressActionAdjustmentProviderTest extends UnitFortressBasicTestCase {

    @Resource
    private FortressConfig config;

    public void test_customizeActionMappingRequestPath_lmlike() {
        // ## Arrange ##
        FortressActionAdjustmentProvider provider = new FortressActionAdjustmentProvider(config);

        // ## Act ##
        // ## Assert ##
        assertEquals("/wx/routing/restlike/lmlike/category/FML/1", mapping(provider, "/wx/routing/restlike/lmlike/FML/1"));
        assertEquals("/wx/routing/restlike/lmlike/category/FML/1/", mapping(provider, "/wx/routing/restlike/lmlike/FML/1/"));
        assertNull(mapping(provider, "/wx/routing/restlike/lmlike"));
        assertNull(mapping(provider, "/wx/routing/restlike/lmlike/"));
        assertNull(mapping(provider, "/wx/routing/restlike/lmlike/FML/1/sea"));
        assertNull(mapping(provider, "/wx/routing/restlike/lmlike/1"));
        assertNull(mapping(provider, "/wx/routing/restlike/lmlike/1/"));
        assertNull(mapping(provider, "/wx/routing/restlike/lmlike/1/detail"));
    }

    protected String mapping(FortressActionAdjustmentProvider provider, String requestPath) {
        return provider.customizeActionMappingRequestPath(requestPath);
    }
}
