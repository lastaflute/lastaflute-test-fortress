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
package org.docksidestage.remote.maihama.showbase.products.purchases.payments.index;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of GET /products/{productId}/purchases/{purchaseId}/payments/.
 * @author FreeGen
 */
public class RemoteProductsPurchasesPaymentsParam {

    /** The property of productName. (prefix for name of product e.g. R) (NullAllowed) */
    public String productName;

    /** The property of productStatus. (enumValue=[ONS, PST, SST]) (status of product e.g. ONS: * `ONS` - OnSaleProduction, OnSaleProduction. * `PST` - ProductionStop, ProductionStop. * `SST` - SaleStop, SaleStop.) (NullAllowed) */
    public String productStatus;

    /** The property of purchaseMemberName. (prefix for member name who purchases the product e.g. S) (NullAllowed) */
    public String purchaseMemberName;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
