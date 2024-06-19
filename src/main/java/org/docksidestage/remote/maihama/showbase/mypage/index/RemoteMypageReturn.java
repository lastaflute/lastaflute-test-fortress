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
package org.docksidestage.remote.maihama.showbase.mypage.index;

import javax.validation.constraints.NotNull;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /mypage/.
 * @author FreeGen
 */
public class RemoteMypageReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of recentProducts. */
    @NotNull
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<ProductPart> recentProducts;

    /**
     * The part class of ProductPart.
     * @author FreeGen
     */
    public static class ProductPart {

        /** The property of productName. */
        @Required
        public String productName;

        /** The property of regularPrice. */
        @Required
        public Integer regularPrice;
    }

    /** The property of highPriceProducts. */
    @NotNull
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<ProductPart> highPriceProducts;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
