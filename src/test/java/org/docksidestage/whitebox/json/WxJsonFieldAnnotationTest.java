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

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.annotation.JsonDatePattern;

/**
 * @author jflute
 */
public class WxJsonFieldAnnotationTest extends UnitFortressWebTestCase {

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
        bean.seaDate = LocalDate.of(2016, 10, 21);
        bean.landDate = LocalDate.of(2016, 10, 22);
        bean.piariDate = LocalDateTime.of(2016, 10, 23, 12, 34, 56);
        bean.bonvoDate = LocalDateTime.of(2016, 10, 24, 23, 45, 51);

        // ## Act ##
        String json = jsonManager.toJson(bean);
        log(ln() + json);
        MaihamaDateBean reversed = jsonManager.fromJson(json, MaihamaDateBean.class);

        // ## Assert ##
        assertContains(json, "2016/10/21");
        assertContains(json, "2016-10-22");
        assertContains(json, "2016/10/23 12:34:56");
        assertContains(json, "2016-10-24T23:45:51");
        assertEquals(bean.seaDate, reversed.seaDate);
        assertEquals(bean.landDate, reversed.landDate);
        assertEquals(bean.piariDate, reversed.piariDate);
        assertEquals(bean.bonvoDate, reversed.bonvoDate);
        assertNull(reversed.ambaDate);
    }

    public static class MaihamaDateBean {

        @JsonDatePattern("yyyy/MM/dd")
        public LocalDate seaDate;

        public LocalDate landDate;

        @JsonDatePattern("yyyy/MM/dd HH:mm:ss")
        public LocalDateTime piariDate;

        public LocalDateTime bonvoDate;

        @JsonDatePattern("yyyy/MM/dd")
        public LocalDate ambaDate;
    }
}
