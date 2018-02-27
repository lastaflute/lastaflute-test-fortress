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
package org.docksidestage.whitebox.json;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressWebTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxJsonYourCollectionsTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                                         DatePattern
    //                                                                         ===========
    public void test_DatePattern_basic() {
        // ## Arrange ##
        MaihamaDateBean bean = new MaihamaDateBean();
        bean.strList = Lists.immutable.of("sea", "land", "piari");
        bean.intList = Lists.immutable.of(1, 2, 3);

        // ## Act ##
        String json = jsonManager.toJson(bean);
        log(ln() + json);
        MaihamaDateBean reversed = jsonManager.fromJson(json, MaihamaDateBean.class);

        // ## Assert ##
        assertEquals(bean.strList, reversed.strList);
        assertEquals(bean.intList, reversed.intList);
        assertNull(bean.nullList);
    }

    public static class MaihamaDateBean {

        @Required
        public ImmutableList<String> strList;
        @Required
        public ImmutableList<Integer> intList;
        public ImmutableList<String> nullList;
    }
}
