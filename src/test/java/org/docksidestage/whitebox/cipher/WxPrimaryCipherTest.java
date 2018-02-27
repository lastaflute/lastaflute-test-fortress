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
package org.docksidestage.whitebox.cipher;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.security.PrimaryCipher;

/**
 * @author jflute
 */
public class WxPrimaryCipherTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private PrimaryCipher primaryCipher;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_invertible_basic() {
        // ## Arrange ##
        String key = "sea";

        // ## Act ##
        String encrypted = primaryCipher.encrypt(key);
        String decrypted = primaryCipher.decrypt(encrypted);

        // ## Assert ##
        assertEquals(key, decrypted);
    }

    // ===================================================================================
    //                                                                           encrypt()
    //                                                                           =========
    public void test_encrypt_basic() {
        assertException(IllegalArgumentException.class, () -> primaryCipher.encrypt(null));
        String emptyCrypted = primaryCipher.encrypt("");
        log("emptyCrypted: {}", emptyCrypted);
        assertNotNull(emptyCrypted);
        assertNotNull(primaryCipher.encrypt("sea"));
    }

    // ===================================================================================
    //                                                                           decrypt()
    //                                                                           =========
    public void test_decrypt_basic() {
        assertException(IllegalArgumentException.class, () -> primaryCipher.decrypt(null));
        String emptyEncrypted = primaryCipher.encrypt("");
        String emptyDecrypted = primaryCipher.decrypt(emptyEncrypted);
        log("emptyCrypted: {}", emptyDecrypted);
        assertEquals("", emptyDecrypted);
    }
}
