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
package org.docksidestage.app.web.wx.request.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.docksidestage.bizfw.validation.custom.LandIntegerCustomOne;
import org.docksidestage.bizfw.validation.custom.LandIntegerCustomTwo;
import org.docksidestage.bizfw.validation.custom.SeaStringCustomOne;
import org.docksidestage.bizfw.validation.custom.SeaStringCustomTwo;
import org.docksidestage.dbflute.allcommon.CDef;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxRequestJsonBodySwaggerBody {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // -----------------------------------------------------
    //                                                String
    //                                                ------
    public String seaPlain;

    @Required
    public String seaRequired;

    @Length(min = 2, max = 16)
    public String seaLength;

    @Size(min = 4, max = 32)
    public String seaSize;

    @Pattern(regexp = ".*hangar$")
    public String seaPattern;

    @Email
    public String seaEmail;

    @SeaStringCustomOne
    public String seaStringCustomOne;

    @SeaStringCustomTwo
    public String seaStringCustomTwo;

    @Required
    @Size(min = 4, max = 32)
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

    // -----------------------------------------------------
    //                                                 Date
    //                                                ------
    @Required
    public LocalDate piari;

    public LocalDateTime bonvo;

    // -----------------------------------------------------
    //                                               Various
    //                                               -------
    public Boolean dstore;

    public CDef.MemberStatus amba;

    // -----------------------------------------------------
    //                                                Object
    //                                                ------
    public MiracoPart miraco;

    public static class MiracoPart {

        public String sta;
    }

    @Valid
    public WhitePart white;

    public static class WhitePart {

        public CDef.WhiteConfusingFormatBodying formatBodying;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return Lato.string(this);
    }
}
