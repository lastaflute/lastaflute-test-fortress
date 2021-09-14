/*
 * Copyright 2015-2021 the original author or authors.
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

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.engine.RealJsonEngine;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxJsonReadingFilterTest extends UnitFortressBasicTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                                         Simple Text
    //                                                                         ===========
    public void test_filterSimpleTextReading_basic() {
        // ## Arrange ##
        JsonMappingOption option = new JsonMappingOption();
        option.filterSimpleTextReading(text -> {
            if (text.equals("mystic")) {
                return "hangar";
            }
            if (text.equals("83")) {
                return "28";
            }
            return text;
        });
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(option);
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);
        JsonReadingFilterTestBean bean = new JsonReadingFilterTestBean();
        bean.sea = "mystic";
        bean.land = 83;
        String json = ruledEngine.toJson(bean);

        // ## Act ##
        JsonReadingFilterTestBean filtered = ruledEngine.fromJson(json, JsonReadingFilterTestBean.class);

        // ## Assert ##
        log(filtered.sea, filtered.land);
        assertEquals("hangar", filtered.sea);
        assertEquals(28, filtered.land);
    }

    // ===================================================================================
    //                                                                       Typeable Text
    //                                                                       =============
    public void test_filterTypeableTextReading_basic() {
        // ## Arrange ##
        JsonMappingOption option = new JsonMappingOption();
        option.filterTypeableTextReading((adaptingType, text) -> {
            if (String.class.isAssignableFrom(adaptingType)) {
                return "hangar";
            }
            if (Integer.class.isAssignableFrom(adaptingType)) {
                return "28";
            }
            if (LocalDate.class.isAssignableFrom(adaptingType)) {
                return "2000-07-07";
            }
            return text;
        });
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(option);
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);
        JsonReadingFilterTestBean bean = new JsonReadingFilterTestBean();
        bean.sea = "mystic";
        bean.land = 83;
        bean.piari = LocalDate.of(2019, 1, 12);
        String json = ruledEngine.toJson(bean);

        // ## Act ##
        JsonReadingFilterTestBean filtered = ruledEngine.fromJson(json, JsonReadingFilterTestBean.class);

        // ## Assert ##
        log(filtered.sea, filtered.land);
        assertEquals("hangar", filtered.sea);
        assertEquals(28, filtered.land);
        assertEquals(LocalDate.of(2000, 7, 7), filtered.piari);
    }

    // ===================================================================================
    //                                                                        Unified Text
    //                                                                        ============
    public void test_unifiedTextReading_basic() {
        // ## Arrange ##
        JsonMappingOption option = new JsonMappingOption();
        option.filterSimpleTextReading(text -> {
            if (text.equals("mystic")) {
                return text + "|hangar";
            }
            if (text.equals("83")) {
                return text + "28";
            }
            return text;
        });
        option.filterTypeableTextReading((adaptingType, text) -> {
            if (String.class.isAssignableFrom(adaptingType)) {
                return text + "|lost";
            }
            if (Integer.class.isAssignableFrom(adaptingType)) {
                return text + "35";
            }
            return text;
        });
        RealJsonEngine ruledEngine = prepareEngine(option);
        JsonReadingFilterTestBean bean = new JsonReadingFilterTestBean();
        bean.sea = "mystic";
        bean.land = 83;
        String json = ruledEngine.toJson(bean);

        // ## Act ##
        JsonReadingFilterTestBean filtered = ruledEngine.fromJson(json, JsonReadingFilterTestBean.class);

        // ## Assert ##
        log(filtered.sea, filtered.land);
        assertEquals("mystic|hangar|lost", filtered.sea);
        assertEquals(832835, filtered.land);
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private RealJsonEngine prepareEngine(JsonMappingOption option) {
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(option);
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);
        return ruledEngine;
    }

    private static class JsonReadingFilterTestBean {

        @Required
        public String sea;
        @Required
        public Integer land;

        public LocalDate piari;

        @SuppressWarnings("unused")
        public LocalDateTime bonvo;

        @SuppressWarnings("unused")
        public Boolean dstore;

        @SuppressWarnings("unused")
        public JsonReadingFilterTestNestedBean nestedBean;
    }

    private static class JsonReadingFilterTestNestedBean {

        @SuppressWarnings("unused")
        public String dockside;
        @SuppressWarnings("unused")
        public Integer hangar;
    }
}
