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
package org.docksidestage.app.web.wx.lastadoc;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.docksidestage.dbflute.allcommon.CDef;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxLastadocForm extends WxLastadocExtendsForm {

    @Length(max = 94)
    @Pattern(regexp = "mystic><@\"'bigband")
    public String sea;

    @Valid
    public AmbaPart amba;

    public static class AmbaPart {

        // #hope jflute overridden by miraco#amba for now (2019/01/17)
        /** official full name of amba */
        @Required
        public String fullName;

        /** room count of amba */
        @Required
        public Integer roomCount;
    }

    @Valid
    public MiracoPart miraco;

    public static class MiracoPart {

        /** official full name of miraco */
        @Required
        public String fullName;

        /** room count of habor side */
        @Required
        public Integer harborRoomCount;

        /** room count of venezia side */
        @Required
        public Integer veneziaRoomCount;

        @Valid
        public AmbaPart amba;

        public static class AmbaPart {

            /** official full name of miraco#amba */
            @Required
            public String fullName;

            /** room count of amba */
            @Required
            public Integer roomCount;
        }
    }

    @Valid
    public DohotelPart dohotel;

    public static class DohotelPart {

        /** official full name of dohotel */
        @Required
        public String fullName;

        /** room count of dohotel */
        @Required
        public Integer roomCount;
    }

    @Valid
    public WhitePart white;

    public static class WhitePart {

        public CDef.WhiteConfusingFormatBodying formatBodying;
    }
}
