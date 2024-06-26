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
package org.docksidestage.app.web.wx.request.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.docksidestage.dbflute.allcommon.CDef;
import org.lastaflute.core.util.Lato;

/**
 * @author jflute
 */
public class WxRequestJsonBodyBody {

    public String sea;

    public Integer land;

    public LocalDate piari;

    public LocalDateTime bonvo;

    public Boolean dstore;

    public CDef.MemberStatus amba;

    public MiracoPart miraco;

    public static class MiracoPart {

        public String sta;
    }

    @Valid
    public WhitePart white;

    public static class WhitePart {

        public CDef.WhiteConfusingFormatBodying formatBodying;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
