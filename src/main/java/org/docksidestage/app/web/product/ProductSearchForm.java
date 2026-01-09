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
package org.docksidestage.app.web.product;

import org.docksidestage.dbflute.allcommon.CDef;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.core.util.Lato;

/**
 * 検索条件、商品名など。
 * @author jflute
 */
public class ProductSearchForm {

    /** 商品名のキーワード条件値 (NullAllowed: 非必須) */
    @Length(max = 10) // #simple_for_example just for validtion example
    public String productName;

    /** 商品種別の条件値 (NullAllowed: 非必須) */
    public CDef.ProductStatus productStatus;

    /** その商品を購入した会員の名前のキーワード条件値 (NullAllowed: 非必須) */
    @Length(max = 5) // #simple_for_example just for validtion example
    public String purchaseMemberName;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
