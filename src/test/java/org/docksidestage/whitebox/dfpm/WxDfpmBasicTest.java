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
package org.docksidestage.whitebox.dfpm;

import java.util.Map;

import javax.annotation.Resource;

import org.dbflute.helper.mapstring.MapListString;
import org.docksidestage.mylasta.template.bean.WxOrelseVariableTemplateBean;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.template.TemplateManager;

/**
 * @author jflute
 */
public class WxDfpmBasicTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private TemplateManager templateManager;

    // ===================================================================================
    //                                                                            orElse()
    //                                                                            ========
    public void test_orElse_variable_hasValue() {
        // ## Arrange ##

        // ## Act ##
        String parsed = WxOrelseVariableTemplateBean.parsedBy(templateManager, bean -> {
            bean.setSea("bigband");
            bean.setLand("oneman");
        });

        // ## Assert ##
        log("parsed: {}", parsed);
        Map<String, Object> map = new MapListString().generateMap(parsed);
        assertEquals("bigband", map.get("sea"));
        assertEquals("oneman", map.get("land"));
    }

    public void test_orElse_variable_noValue() {
        // ## Arrange ##

        // ## Act ##
        String parsed = WxOrelseVariableTemplateBean.parsedBy(templateManager, bean -> {});

        // ## Assert ##
        log("parsed: {}", parsed);
        Map<String, Object> map = new MapListString().generateMap(parsed);
        assertEquals("mystic", map.get("sea"));
        assertNull(map.get("land"));
    }
}
