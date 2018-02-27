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
package org.docksidestage.whitebox.prop;

import java.util.Locale;

import javax.annotation.Resource;

import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.message.MessageManager;

/**
 * @author jflute
 */
public class WxJapanesePropertiesTest extends UnitFortressWebTestCase {

    @Resource
    private FortressConfig config;
    @Resource
    private MessageManager messageManager;

    public void test_canReadConfig() throws Exception {
        String value = config.get("whitebox.config.japanese.caos");
        String japaneseAiueoQuoted = "\"\u3042\u3044\u3046\u3048\u304a\"";
        String expected = "escaped " + japaneseAiueoQuoted + " and plain " + japaneseAiueoQuoted;
        log("{} = {}", expected, value);
        assertEquals(expected, value);
    }

    public void test_canReadMessage() throws Exception {
        String message = messageManager.getMessage(Locale.ENGLISH, "whitebox.message.japanese.caos");
        String japaneseAiueoQuoted = "\"\u3042\u3044\u3046\u3048\u304a\"";
        String expected = "escaped " + japaneseAiueoQuoted + " and plain " + japaneseAiueoQuoted;
        log("{} = {}", expected, message);
        assertEquals(expected, message);
    }
}
