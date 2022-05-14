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
package org.docksidestage.remote.fortress.lido.product.list;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of POST /lido/product/list/{pageNumber}.
 * @author FreeGen
 */
public class RemoteLidoProductListParam {

    /** The property of productName. (keyword of product name e.g. Stranger) (NullAllowed) */
    public String productName;

    /** The property of productStatus. (enumValue=[ONS, PST, SST]) (status of product e.g. ONS: * `ONS` - OnSaleProduction. * `PST` - ProductionStop. * `SST` - SaleStop. :: fromCls(CDef$ProductStatus)) (NullAllowed) */
    public org.docksidestage.dbflute.allcommon.CDef.ProductStatus productStatus;

    /** The property of purchaseMemberName. (keyword of member name that purchases e.g. S) (NullAllowed) */
    public String purchaseMemberName;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
