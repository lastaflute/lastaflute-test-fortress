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
package org.docksidestage.whitebox.lastadi.extctx;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.web.util.LaRequestUtil;
import org.lastaflute.web.util.LaResponseUtil;

/**
 * @author jflute
 */
public class WxExternalContextWebTest extends UnitFortressBasicTestCase {

    public void test_ContainerUtil() throws Exception {
        assertTrue(ContainerUtil.hasExternalContext());
        assertNotNull(ContainerUtil.retrieveExternalContext());
    }

    public void test_RequestUtil() throws Exception {
        assertNotNull(LaRequestUtil.getRequest());
        assertNotNull(LaRequestUtil.getOptionalRequest());
        assertTrue(LaRequestUtil.getOptionalRequest().isPresent());
    }

    public void test_ResponseUtil() throws Exception {
        assertNotNull(LaResponseUtil.getResponse());
        assertNotNull(LaResponseUtil.getOptionalResponse());
        assertTrue(LaResponseUtil.getOptionalResponse().isPresent());
    }
}
