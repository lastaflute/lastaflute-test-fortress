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
package org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.suffixhell;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /wx/remogen/tricky/suffixhell.
 * @author FreeGen
 */
public class RemoteWxRemogenTrickySuffixhellReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of resortName. */
    @Required
    public String resortName;

    /** The property of hellSea. (NullAllowed) */
    @javax.validation.Valid
    public HellSeaPart hellSea;

    /**
     * The part class of HellSeaPart.
     * @author FreeGen
     */
    public static class HellSeaPart {

        /** The property of parkName. */
        @Required
        public String parkName;

        /** The property of hellSeaResult. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<HellSeaResultPart> hellSeaResult;

        /**
         * The part class of HellSeaResultPart.
         * @author FreeGen
         */
        public static class HellSeaResultPart {

            /** The property of stageName. */
            @Required
            public String stageName;
        }
    }

    /** The property of hellLand. (NullAllowed) */
    @javax.validation.Valid
    public HellLandPart hellLand;

    /**
     * The part class of HellLandPart.
     * @author FreeGen
     */
    public static class HellLandPart {

        /** The property of parkName. */
        @Required
        public String parkName;

        /** The property of hellLandPart. (NullAllowed) */
        @javax.validation.Valid
        public HellLandPartPart hellLandPart;

        /**
         * The part class of HellLandPartPart.
         * @author FreeGen
         */
        public static class HellLandPartPart {

            /** The property of stageName. */
            @Required
            public String stageName;
        }
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
