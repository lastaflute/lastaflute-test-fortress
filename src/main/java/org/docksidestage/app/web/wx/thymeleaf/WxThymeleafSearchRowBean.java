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
package org.docksidestage.app.web.wx.thymeleaf;

import java.time.LocalDate;

import javax.validation.Valid;

import org.docksidestage.dbflute.allcommon.CDef;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxThymeleafSearchRowBean {

    @Required
    public Integer productId;
    @Required
    public String productName;
    @Required
    public String productStatus;
    @Required
    public String productCategory;
    @Required
    public Integer regularPrice;
    /** null if no purchase */
    public LocalDate latestPurchaseDate;

    // test of thymeleaf3 json serializer
    public String nullableString;
    public Integer nullableInteger;
    public CDef.MemberStatus nullableCDef;

    @Valid
    public WxThymeleafSearchRowNestedBean nestedBean;

    public static class WxThymeleafSearchRowNestedBean {

        public String nestedNullableString;
        public Integer nestedNullableInteger;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
