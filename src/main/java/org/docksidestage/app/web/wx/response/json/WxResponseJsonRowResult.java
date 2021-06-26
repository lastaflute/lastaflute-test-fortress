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
package org.docksidestage.app.web.wx.response.json;

import java.time.LocalDate;

import javax.validation.Valid;

import org.docksidestage.app.web.wx.response.json.filepart.WxResponseJsonRowFilePart;
import org.docksidestage.dbflute.allcommon.CDef;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * @author jflute
 */
public class WxResponseJsonRowResult {

    public Integer productId;
    public String productName;
    public String productStatus;
    public String productCategory;
    public Integer regularPrice;
    public LocalDate latestPurchaseDate;

    // -----------------------------------------------------
    //                                             Part Test
    //                                             ---------
    @Valid
    public WhitePart white;

    public static class WhitePart {

        public CDef.WhiteConfusingFormatBodying formatBodying;
    }

    // -----------------------------------------------------
    //                                       File/Inner Test
    //                                       ---------------
    // #thinking jflute cannot get fields at swagger, treated as "type": "object" (2021/06/25)
    @Valid
    public WxResponseJsonRowFilePart filePart;

    @Valid
    public WxResponseJsonRowInnerPart innerPart;

    public static class WxResponseJsonRowInnerPart { // same fields as filePart

        public String sea;

        public ImmutableList<Integer> land;
    }
}
