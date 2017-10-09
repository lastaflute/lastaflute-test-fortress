/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage.remote.maihama.showbase.products.index;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of POST /products/{productId}.
 * @author FreeGen
 */
public class RemoteProductsParam {

    /** The property of productName. (NullAllowed) */
    public String productName;

    /** The property of productStatus. (NullAllowed) */
    public ProductStatus productStatus;

    /**
     * The enumeration of ProductStatus.
     * @author FreeGen
     * @deprecated auto-generating enumeration is unsupported yet so use beanPropertyManualMappingClass()
     */
    public enum ProductStatus {
        // TODO you auto-generating enumeration is unsupported yet so use beanPropertyManualMappingClass()
        //ONS
        //PST
        //SST
    }

    /** The property of purchaseMemberName. (NullAllowed) */
    public String purchaseMemberName;

    /** The property of pageNumber. (NullAllowed) */
    public Integer pageNumber;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}