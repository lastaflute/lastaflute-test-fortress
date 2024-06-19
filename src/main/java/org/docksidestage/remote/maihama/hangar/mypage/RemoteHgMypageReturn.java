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
package org.docksidestage.remote.maihama.hangar.mypage;

import java.util.List;

import javax.validation.Valid;

import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class RemoteHgMypageReturn {

    @Required
    @Valid
    public List<ProductPart> recentProducts;
    @Required
    @Valid
    public List<ProductPart> highPriceProducts;

    static public class ProductPart {

        @Required
        public final String productName;
        @Required
        public final Integer regularPrice;

        public ProductPart(Product product) {
            this.productName = product.getProductName();
            this.regularPrice = product.getRegularPrice();
        }

        @Override
        public String toString() {
            return "{" + productName + ", " + regularPrice + "}";
        }
    }
}
