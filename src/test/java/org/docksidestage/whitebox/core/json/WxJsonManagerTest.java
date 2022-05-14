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

import java.util.ArrayList;

import javax.annotation.Resource;

import org.docksidestage.app.web.wx.response.json.WxResponseJsonSwaggerResult;
import org.docksidestage.app.web.wx.response.json.WxResponseJsonSwaggerResult.WhiteStandardPart;
import org.docksidestage.app.web.wx.response.json.WxResponseJsonSwaggerResult.WxResponseJsonObjectGenericPart;
import org.docksidestage.app.web.wx.response.json.WxResponseJsonSwaggerResult.WxResponseJsonRowInnerPart;
import org.docksidestage.dbflute.allcommon.CDef;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.eclipse.collections.api.factory.Lists;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.exception.JsonPropertyClassificationCodeUnknownException;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxJsonManagerTest extends UnitFortressBasicTestCase {

    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                                        Hierarchical
    //                                                                        ============
    public void test_fromJson_hierarchical() { // #hope jflute more test, Json hierarchical (2022/04/18)
        // ## Arrange ##
        WxResponseJsonSwaggerResult result = new WxResponseJsonSwaggerResult();
        result.seaPlain = "plain mystic";
        result.seaRequired = "required mystic";
        result.seaLength = "length mystic";
        result.seaSize = "size mystic";
        result.seaPatternKana = "kana mystic";
        result.seaPatternPhone = "phone mystic";
        result.seaEmail = "email mystic";
        result.seaWhole = "whole mystic";

        result.landPlain = 444;
        result.landRequired = 999;
        result.landWhole = 666;

        result.whiteStandard = new WhiteStandardPart();
        result.whiteStandard.formatBodying = CDef.WhiteConfusingFormatBodying.Sea;

        result.objectGenericInnerPartRowList = new ArrayList<WxResponseJsonObjectGenericPart<WxResponseJsonRowInnerPart>>();
        result.objectGenericInnerPartRowList.add(createGeneritPart());
        String json = jsonManager.toJson(result);
        log("\n" + json);

        // ## Act ##
        WxResponseJsonSwaggerResult fromResult = jsonManager.fromJson(json, WxResponseJsonSwaggerResult.class);

        // ## Assert ##
        log(fromResult);
        assertHasAnyElement(fromResult.objectGenericInnerPartRowList);
    }

    private WxResponseJsonObjectGenericPart<WxResponseJsonRowInnerPart> createGeneritPart() {
        WxResponseJsonObjectGenericPart<WxResponseJsonRowInnerPart> genericPart = new WxResponseJsonObjectGenericPart<>();
        genericPart.sea = "generic mystic";
        genericPart.landList = Lists.immutable.of(createInnerPart());
        return genericPart;
    }

    private WxResponseJsonRowInnerPart createInnerPart() {
        WxResponseJsonRowInnerPart innerPart = new WxResponseJsonRowInnerPart();
        innerPart.sea = "inner mystic";
        innerPart.land = Lists.immutable.of(1, 2, 3);
        return innerPart;
    }

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
