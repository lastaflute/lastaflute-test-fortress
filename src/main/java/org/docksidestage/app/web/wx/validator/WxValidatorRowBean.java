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
package org.docksidestage.app.web.wx.validator;

import java.time.LocalDateTime;

import javax.validation.constraints.AssertTrue;

import org.docksidestage.dbflute.allcommon.CDef;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxValidatorRowBean {

    @Required
    public Integer productId;
    @Required
    public String productName;
    @Required
    public String productStatusName;
    @Required
    public Integer regularPrice;
    @Required
    public LocalDateTime registerDatetime;
    @Required
    public String productCategoryName;

    @AssertTrue
    public boolean isProductNameToStatus() {
        if (productName == null) {
            return true;
        }
        if (productName.equals("sea")) {
            return CDef.ProductStatus.OnSaleProduction.name().equals(productStatusName);
        } else {
            return true;
        }
    }
    //
    //    @Override
    //    public String toString() {
    //        return Lato.string(this);
    //    }
}
