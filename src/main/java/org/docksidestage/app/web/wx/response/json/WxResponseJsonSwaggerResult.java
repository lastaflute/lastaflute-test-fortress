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

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.docksidestage.app.web.wx.response.json.filepart.WxResponseJsonSwaggerFilePart;
import org.docksidestage.app.web.wx.response.json.filepart.WxResponseJsonSwaggerFileResult;
import org.docksidestage.bizfw.validation.custom.LandIntegerCustomOne;
import org.docksidestage.bizfw.validation.custom.LandIntegerCustomTwo;
import org.docksidestage.bizfw.validation.custom.SeaStringCustomOne;
import org.docksidestage.bizfw.validation.custom.SeaStringCustomTwo;
import org.docksidestage.dbflute.allcommon.CDef;
import org.eclipse.collections.api.list.ImmutableList;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxResponseJsonSwaggerResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // -----------------------------------------------------
    //                                                String
    //                                                ------
    public String seaPlain;

    @Required
    public String seaRequired;

    @Length(max = 16)
    public String seaLength;

    @Size(min = 4, max = 32)
    public String seaSize;

    @Pattern(regexp = "[\\p{InKatakana}]*")
    public String seaPatternKana;

    @Pattern(regexp = "(^[0-9]+$)")
    public String seaPatternPhone;

    @Email
    public String seaEmail;

    @SeaStringCustomOne
    public String seaStringCustomOne;

    @SeaStringCustomTwo
    public String seaStringCustomTwo;

    @Required
    @Size(max = 32)
    @Pattern(regexp = ".*hangar$")
    @SeaStringCustomOne
    public String seaWhole;

    // -----------------------------------------------------
    //                                                Number
    //                                                ------
    public Integer landPlain;

    @Required
    public Integer landRequired;

    @Min(4)
    @Max(32)
    public Integer landMinMax;

    @LandIntegerCustomOne
    public String landIntegerCustomOne;

    @LandIntegerCustomTwo
    public String landIntegerCustomTwo;

    @Required
    @Max(32)
    @LandIntegerCustomOne
    public Integer landWhole;

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
    public WxResponseJsonSwaggerFilePart filePartRow;

    // #thinking jflute cannot get fields at swagger in this list case (2021/07/18)
    @Valid
    public List<WxResponseJsonSwaggerFilePart> filePartRowList;

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
    public WxResponseJsonSwaggerFileResult fileResultRow;

    // #thinking jflute also cannot get fields at swagger in this list case (2021/07/18)
    @Valid
    public List<WxResponseJsonSwaggerFileResult> fileResultRowList;

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
