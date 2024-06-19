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
package org.docksidestage.remote.fortress.wx.request.json.body.list;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of POST /wx/request/json/body/list.
 * @author FreeGen
 */
public class RemoteWxRequestJsonBodyListParam {

    /** The property of sea. (NullAllowed) */
    public String sea;

    /** The property of land. (NullAllowed) */
    public Integer land;

    /** The property of piari. (NullAllowed) */
    public java.time.LocalDate piari;

    /** The property of bonvo. (NullAllowed) */
    public java.time.LocalDateTime bonvo;

    /** The property of dstore. (NullAllowed) */
    public Boolean dstore;

    /** The property of amba. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NullAllowed) */
    public String amba;

    /** The property of miraco. (NullAllowed) */
    @javax.validation.Valid
    public MiracoPart miraco;

    /**
     * The part class of MiracoPart.
     * @author FreeGen
     */
    public static class MiracoPart {

        /** The property of sta. (NullAllowed) */
        public String sta;
    }

    /** The property of white. (NullAllowed) */
    @javax.validation.Valid
    public WhitePart white;

    /**
     * The part class of WhitePart.
     * @author FreeGen
     */
    public static class WhitePart {

        /** The property of formatBodying. (enumValue=[SEA, LAN]) ( * `SEA` - Sea, sea. * `LAN` - Land, land. :: fromCls(CDef$WhiteConfusingFormatBodying)) (NullAllowed) */
        public String formatBodying;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
