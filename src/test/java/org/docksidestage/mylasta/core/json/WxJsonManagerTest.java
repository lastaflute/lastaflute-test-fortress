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
package org.docksidestage.mylasta.core.json;

import javax.annotation.Resource;

import org.docksidestage.dbflute.allcommon.CDef;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.exception.JsonPropertyClassificationCodeUnknownException;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxJsonManagerTest extends UnitFortressWebTestCase {

    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                                        Type Failure
    //                                                                        ============
    public void test_fromJson_typeFailure_cdef() {
        assertException(JsonPropertyClassificationCodeUnknownException.class, () -> {
            jsonManager.fromJson("{status: \"detarame\"}", MockMaihama.class);
        }).handle(cause -> {
            log(cause);
        });
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    public static class MockMaihama {

        @Required
        public CDef.MemberStatus status;
    }
}
