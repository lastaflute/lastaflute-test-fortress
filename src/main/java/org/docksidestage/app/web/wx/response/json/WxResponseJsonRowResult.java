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
import java.util.List;

import javax.validation.Valid;

import org.docksidestage.app.web.wx.response.json.filepart.WxResponseJsonRowFilePart;
import org.docksidestage.app.web.wx.response.json.filepart.WxResponseJsonRowFileResult;
import org.docksidestage.dbflute.allcommon.CDef;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * @author jflute
 */
public class WxResponseJsonRowResult {

    // ===================================================================================
    //                                                                     Value Attribute
    //                                                                     ===============O
    public Integer productId;
    public String productName;
    public String productStatus;
    public String productCategory;
    public Integer regularPrice;
    public LocalDate latestPurchaseDate;

    // ===================================================================================
    //                                                                  Standard Part Test
    //                                                                  ==================
    @Valid
    public WhiteStandardPart whiteStandard;

    public static class WhiteStandardPart {

        public CDef.WhiteConfusingFormatBodying formatBodying;
    }

    // ===================================================================================
    //                                                                     File/Inner Test
    //                                                                     ===============
    // -----------------------------------------------------
    //                                            Part Class
    //                                            ----------
    // done (2021/07/18) jflute cannot get fields at swagger, treated as "type": "object" (2021/06/25)
    @Valid
    public WxResponseJsonRowFilePart filePartRow;

    // #thinking jflute cannot get fields at swagger in this list case (2021/07/18)
    @Valid
    public List<WxResponseJsonRowFilePart> filePartRowList;

    @Valid
    public WxResponseJsonRowInnerPart innerPartRow;

    @Valid
    public List<WxResponseJsonRowInnerPart> innerPartRowList;

    public static class WxResponseJsonRowInnerPart { // same fields as filePart

        public String sea;

        public ImmutableList<Integer> land;
    }

    // -----------------------------------------------------
    //                                   Nested Result Class
    //                                   -------------------
    @Valid
    public WxResponseJsonRowFileResult fileResultRow;

    // #thinking jflute also cannot get fields at swagger in this list case (2021/07/18)
    @Valid
    public List<WxResponseJsonRowFileResult> fileResultRowList;

    @Valid
    public WxResponseJsonRowInnerResult innerResultRow;

    @Valid
    public List<WxResponseJsonRowInnerResult> innerResultRowList;

    public static class WxResponseJsonRowInnerResult { // also same fields as filePart

        public String sea;

        public ImmutableList<Integer> land;
    }

    // -----------------------------------------------------
    //                                 NonList Generic Class
    //                                 ---------------------
    @Valid
    public WxResponseJsonObjectGenericPart<WxResponseJsonRowInnerPart> objectGenericInnerPartRow;

    @Valid
    public List<WxResponseJsonObjectGenericPart<WxResponseJsonRowInnerPart>> objectGenericInnerPartRowList;

    public static class WxResponseJsonObjectGenericPart<NEST> {

        public String sea;

        public ImmutableList<NEST> landList;
    }
}
