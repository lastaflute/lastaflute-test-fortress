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
package org.docksidestage.whitebox.mail;

import java.util.Map;

import javax.annotation.Resource;

import org.dbflute.helper.mapstring.MapListString;
import org.dbflute.mail.send.supplement.SMailPostingDiscloser;
import org.docksidestage.mylasta.mail.whitebox.WxOrlseVariablePostcard;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.mail.Postbox;

/**
 * @author jflute
 */
public class WxDfmailBasicTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private Postbox postbox;

    // ===================================================================================
    //                                                                            orElse()
    //                                                                            ========
    public void test_orElse_variable_hasValue() {
        // ## Arrange ##

        // ## Act ##
        SMailPostingDiscloser discloser = WxOrlseVariablePostcard.droppedInto(postbox, postcard -> {
            postcard.setFrom("sea@docksidestage.org", "sea");
            postcard.addTo("land@docksidestage.org");
            postcard.setSea("bigband");
            postcard.setLand("oneman");
            postcard.writeAuthor(this);
        }).getPostingDiscloser().get();

        // ## Assert ##
        String parsed = discloser.getSavedPlainText().get();
        log("parsed: \n{}", parsed);
        Map<String, Object> map = new MapListString().generateMap(parsed);
        assertEquals("bigband", map.get("sea"));
        assertEquals("oneman", map.get("land"));
    }

    public void test_orElse_variable_noValue() {
        // ## Arrange ##

        // ## Act ##
        SMailPostingDiscloser discloser = WxOrlseVariablePostcard.droppedInto(postbox, postcard -> {
            postcard.setFrom("sea@docksidestage.org", "sea");
            postcard.addTo("land@docksidestage.org");
            // no value
            //postcard.setSea(sea);
            //postcard.setLand(land);
            postcard.writeAuthor(this);
        }).getPostingDiscloser().get();

        // ## Assert ##
        String parsed = discloser.getSavedPlainText().get();
        log("parsed: \n{}", parsed);
        Map<String, Object> map = new MapListString().generateMap(parsed);
        assertEquals("mystic", map.get("sea"));
        assertNull(map.get("land"));
    }
}
