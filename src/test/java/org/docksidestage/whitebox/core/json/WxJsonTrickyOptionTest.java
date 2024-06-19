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
package org.docksidestage.whitebox.core.json;

import javax.annotation.Resource;

import org.docksidestage.app.web.product.ProductSearchForm;
import org.docksidestage.app.web.product.ProductSearchRowBean;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.engine.RealJsonEngine;

/**
 * @author jflute
 */
public class WxJsonTrickyOptionTest extends UnitFortressBasicTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                              asEmptyToNullReading()
    //                                                              ======================
    public void test_asEmptyToNullReading_typeable() {
        // ## Arrange ##
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(new JsonMappingOption().asEmptyToNullReading(tp -> {
            return String.class.isAssignableFrom(tp);
        }));
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);

        {
            // ## Act ##
            String json = "{ \"productId\": null, \"productName\": \"\" }";
            ProductSearchRowBean bean = ruledEngine.fromJson(json, ProductSearchRowBean.class);

            // ## Assert ##
            log(bean);
            assertNull(bean.productId);
            assertNull(bean.productName);
        }
        {
            // ## Act ##
            String json = "{ \"productName\": null, \"productStatus\": \"\" }";
            ProductSearchForm form = ruledEngine.fromJson(json, ProductSearchForm.class);

            // ## Assert ##
            log(form);
            assertNull(form.productName);
            assertNull(form.productStatus);
        }
    }

    // ===================================================================================
    //                                                              asNullToEmptyWriting()
    //                                                              ======================
    public void test_asNullToEmptyWriting_typeable() {
        // ## Arrange ##
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(new JsonMappingOption().asNullToEmptyWriting(tp -> {
            return String.class.isAssignableFrom(tp);
        }));
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(resource);

        {
            // ## Act ##
            String json = ruledEngine.toJson(new ProductSearchRowBean());

            // ## Assert ##
            log(json);
            assertContains(json, "\"productId\": null");
            assertContains(json, "\"productName\": \"\"");
        }
        {
            // ## Act ##
            String json = ruledEngine.toJson(new ProductSearchForm());

            // ## Assert ##
            log(json);
            assertContains(json, "\"productName\": \"\"");
            assertContains(json, "\"productStatus\": null");
        }
    }
}
