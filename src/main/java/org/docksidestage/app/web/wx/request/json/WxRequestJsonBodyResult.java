/*
 * Copyright 2015-2026 the original author or authors.
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
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.docksidestage.dbflute.allcommon.CDef;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxRequestJsonBodyResult {

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // test of targetField() for RemoteApiGen (2026/03/14)
    // _/_/_/_/_/_/_/_/

    public String sea;

    @NotNull
    public String seaDockside;

    @NotNull
    public List<String> seaHangar;

    @Required
    public List<String> seaMagiclamp;

    @NotNull
    public Integer land;

    public LocalDate piari;

    public LocalDateTime bonvo;

    @Required
    public Boolean dstore;

    @NotNull
    public CDef.MemberStatus amba;

    @Valid
    public MiracoPart miraco;

    public static class MiracoPart {

        @Required
        @Valid
        public ToscanaPart toscana;

        @NotNull
        @Valid
        public List<VeneziaPart> veneziaList;
    }

    public static class ToscanaPart {

        @Required
        public String parkEntranceView;

        public String hotelEntranceView;

        @NotNull
        public String nannimoView;
    }

    public static class VeneziaPart {

        public String canalView;

        @Required
        public String riverView;
    }

    @Valid
    public DohotelPart dohotel;

    public static class DohotelPart {

        public CDef.WhiteConfusingFormatBodying formatBodying;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
