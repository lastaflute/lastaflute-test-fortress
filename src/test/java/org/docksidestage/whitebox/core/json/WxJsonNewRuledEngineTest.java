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
package org.docksidestage.whitebox.core.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.control.JsonControlMeta;
import org.lastaflute.core.json.engine.GsonJsonEngine;
import org.lastaflute.core.json.engine.RealJsonEngine;
import org.lastaflute.core.json.engine.YourJsonEngineCreator;
import org.lastaflute.web.validation.Required;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

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
    //                                                                      Mapping Option
    //                                                                      ==============
    public void test_newRuledEngine_default() {
        // ## Arrange ##
        JsonEngineResource resource = new JsonEngineResource();

        // ## Act ##
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);

        // ## Assert ##
        JsonRuledTestBean bean = new JsonRuledTestBean();
        bean.seaList = Lists.immutable.of("over", "mystic", "bbb");
        bean.landList = Lists.immutable.of(1, 2, 3);
        bean.bonvoDate = LocalDate.of(2018, 12, 19);
        bean.nestedBean = new JsonRuledTestNestedBean();
        String json = ruledEngine.toJson(bean);
        log(ln() + json);
        assertNotContains(json, "over"); // cannot use your collection
        assertContains(json, "\"seaList\": {}");
        assertContains(json, "2018-12-19"); // as ISO
    }

    public void test_newRuledEngine_inherit_basic() {
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
        bean.seaList = Lists.immutable.of("over", "mystic", "bbb");
        bean.landList = Lists.immutable.of(1, 2, 3);
        bean.bonvoDate = LocalDate.of(2018, 12, 19);
        String json = ruledEngine.toJson(bean);
        log(ln() + json);
        assertContains(json, "over"); // can use your collection
        assertContains(json, "2018-12-19"); // as ISO
    }

    public void test_newRuledEngine_inherit_extension() {
        // ## Arrange ##
        JsonMappingOption mappingOption = new JsonMappingOption();
        JsonControlMeta controlMeta = jsonManager.pulloutControlMeta();
        controlMeta.getMappingControlMeta().ifPresent(mappingControlMeta -> {
            mappingOption.acceptAnother(mappingControlMeta);
        });
        mappingOption.formatLocalDateBy(DateTimeFormatter.ofPattern("yyyy@MM$dd"));
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(mappingOption);

        // ## Act ##
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);

        // ## Assert ##
        JsonRuledTestBean bean = new JsonRuledTestBean();
        bean.seaList = Lists.immutable.of("over", "mystic", "bbb");
        bean.landList = Lists.immutable.of(1, 2, 3);
        bean.bonvoDate = LocalDate.of(2018, 12, 19);
        String json = ruledEngine.toJson(bean);
        log(ln() + json);
        assertContains(json, "over"); // can use your collection
        assertContains(json, "2018@12$19");
    }

    // ===================================================================================
    //                                                                         Your Engine
    //                                                                         ===========
    public void test_newRuledEngine_yourEngine_basic() {
        // ## Arrange ##
        JsonMappingOption mappingOption = new JsonMappingOption();
        JsonControlMeta controlMeta = jsonManager.pulloutControlMeta();
        controlMeta.getMappingControlMeta().ifPresent(mappingControlMeta -> {
            mappingOption.acceptAnother(mappingControlMeta);
        });
        mappingOption.formatLocalDateBy(DateTimeFormatter.ofPattern("yyyy@MM$dd"));
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(mappingOption);
        resource.useYourEngineCreator(new YourJsonEngineCreator() {
            public GsonJsonEngine create(Consumer<GsonBuilder> builderSetupper, Consumer<JsonMappingOption> optionSetupper) {
                return new GsonJsonEngine(builderSetupper, optionSetupper) {
                    @Override
                    public TypeAdapterString newTypeAdapterString(JsonMappingOption option) {
                        return new TypeAdapterString(option) {
                            @Override
                            public void write(JsonWriter out, String value) throws IOException {
                                if (value != null && value.contains("mys")) {
                                    value = "han{" + value + "}gar";
                                    markHere("mystic called");
                                } else if (value != null && value.contains("wave")) {
                                    value = "dock{" + value + "}side";
                                    markHere("overthewaves called");
                                }
                                super.write(out, value);
                            }
                        };
                    }
                };
            }
        });

        // ## Act ##
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);

        // ## Assert ##
        JsonRuledTestBean bean = new JsonRuledTestBean();
        bean.seaList = Lists.immutable.of("over", "mystic", "bbb");
        bean.landList = Lists.immutable.of(1, 2, 3);
        bean.bonvoDate = LocalDate.of(2018, 12, 19);
        bean.nestedBean = new JsonRuledTestNestedBean();
        bean.nestedBean.dockside = "overthewaves";
        String json = ruledEngine.toJson(bean);
        log(ln() + json);
        assertContains(json, "over");
        assertContains(json, "han{mystic}gar");
        assertContains(json, "2018@12$19");
        assertContains(json, "dock{overthewaves}side");
        assertMarked("mystic called");
        assertMarked("overthewaves called");
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private static class JsonRuledTestBean {

        @Required
        public ImmutableList<String> seaList;
        @Required
        public ImmutableList<Integer> landList;
        @SuppressWarnings("unused")
        public ImmutableList<String> piariNullList;

        @SuppressWarnings("unused")
        public LocalDate bonvoDate;

        public JsonRuledTestNestedBean nestedBean;
    }

    private static class JsonRuledTestNestedBean {

        @SuppressWarnings("unused")
        public String dockside;
        @SuppressWarnings("unused")
        public Integer hangar;
    }
}
