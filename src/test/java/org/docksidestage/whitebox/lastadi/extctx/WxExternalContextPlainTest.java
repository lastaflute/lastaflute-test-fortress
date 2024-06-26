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

import org.dbflute.utflute.core.PlainTestCase;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.di.core.ExternalContext;
import org.lastaflute.di.core.factory.SingletonLaContainerFactory;
import org.lastaflute.web.util.LaRequestUtil;
import org.lastaflute.web.util.LaResponseUtil;

/**
 * @author jflute
 */
public class WxExternalContextPlainTest extends PlainTestCase {

    private ExternalContext originalContext; // saved, container may be cached

    @Override
    protected void setUp() throws Exception {
        originalContext = SingletonLaContainerFactory.getExternalContext();
        SingletonLaContainerFactory.setExternalContext(null);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        SingletonLaContainerFactory.setExternalContext(originalContext);
    }

    public void test_ContainerUtil() throws Exception {
        assertFalse(ContainerUtil.hasExternalContext());
        assertException(IllegalStateException.class, () -> ContainerUtil.retrieveExternalContext());
    }

    public void test_RequestUtil() throws Exception {
        assertException(IllegalStateException.class, () -> LaRequestUtil.getRequest());
        assertNotNull(LaRequestUtil.getOptionalRequest());
        assertFalse(LaRequestUtil.getOptionalRequest().isPresent());
    }

    public void test_ResponseUtil() throws Exception {
        assertException(IllegalStateException.class, () -> LaResponseUtil.getResponse());
        assertNotNull(LaResponseUtil.getOptionalResponse());
        assertFalse(LaResponseUtil.getOptionalResponse().isPresent());
    }
}
