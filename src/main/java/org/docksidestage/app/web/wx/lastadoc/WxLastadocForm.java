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
package org.docksidestage.app.web.wx.lastadoc;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxLastadocForm {

    @Length(max = 94)
    @Pattern(regexp = "mystic><@\"'bigband")
    public String sea;

    @Valid
    public AmbaPart amba;

    public static class AmbaPart {

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
    }
}
