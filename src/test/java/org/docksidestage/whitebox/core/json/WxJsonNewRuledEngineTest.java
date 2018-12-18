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
package org.docksidestage.whitebox.core.json;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.control.JsonControlMeta;
import org.lastaflute.core.json.engine.RealJsonEngine;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxJsonNewRuledEngineTest extends UnitFortressBasicTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                                         DatePattern
    //                                                                         ===========
    public void test_newRuledEngine_default() {
        // ## Arrange ##
        JsonEngineResource resource = new JsonEngineResource();

        // ## Act ##
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);

        // ## Assert ##
        JsonRuledTestBean bean = new JsonRuledTestBean();
        bean.strList = Lists.immutable.of("sea", "land", "piari");
        bean.intList = Lists.immutable.of(1, 2, 3);
        String json = ruledEngine.toJson(bean);
        log(ln() + json);
        assertNotContains(json, "sea"); // cannot use your collection
    }

    public void test_newRuledEngine_inherit() {
        // ## Arrange ##
        JsonMappingOption mappingOption = new JsonMappingOption();
        JsonControlMeta controlMeta = jsonManager.pulloutControlMeta();
        controlMeta.getMappingControlMeta().ifPresent(mappingControlMeta -> {
            mappingOption.acceptAnother(mappingControlMeta);
        });
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(mappingOption);

        // ## Act ##
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);

        // ## Assert ##
        JsonRuledTestBean bean = new JsonRuledTestBean();
        bean.strList = Lists.immutable.of("sea", "land", "piari");
        bean.intList = Lists.immutable.of(1, 2, 3);
        String json = ruledEngine.toJson(bean);
        log(ln() + json);
        assertContains(json, "sea"); // can use your collection
    }

    public static class JsonRuledTestBean {

        @Required
        public ImmutableList<String> strList;
        @Required
        public ImmutableList<Integer> intList;
        public ImmutableList<String> nullList;
    }
}
