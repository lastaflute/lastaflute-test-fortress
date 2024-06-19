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
package org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.recycle;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /wx/remogen/tricky/recycle.
 * @author FreeGen
 */
public class RemoteWxRemogenTrickyRecycleReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of resortName. */
    @Required
    public String resortName;

    /** The property of firstPark. (NullAllowed) */
    @javax.validation.Valid
    public ResortParkPart firstPark;

    /**
     * The part class of ResortParkPart.
     * @author FreeGen
     */
    public static class ResortParkPart {

        /** The property of parkName. */
        @Required
        public String parkName;

        /** The property of showStages. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<ShowStagePart> showStages;

        /**
         * The part class of ShowStagePart.
         * @author FreeGen
         */
        public static class ShowStagePart {

            /** The property of stageName. */
            @Required
            public String stageName;
        }

        /** The property of themeColor. (NullAllowed) */
        @javax.validation.Valid
        public ThemeColorPart themeColor;

        /**
         * The part class of ThemeColorPart.
         * @author FreeGen
         */
        public static class ThemeColorPart {

            /** The property of colorName. (NullAllowed) */
            public String colorName;

            /** The property of rgb. (NullAllowed) */
            public String rgb;
        }
    }

    /** The property of extendedAreas. (NullAllowed) */
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<ExtendedAreaPart> extendedAreas;

    /**
     * The part class of ExtendedAreaPart.
     * @author FreeGen
     */
    public static class ExtendedAreaPart {

        /** The property of direction. */
        @Required
        public String direction;

        /** The property of nextPark. (NullAllowed) */
        @javax.validation.Valid
        public ResortParkPart nextPark;
    }

    /** The property of departmentStore. (NullAllowed) */
    @javax.validation.Valid
    public DepartmentStorePart departmentStore;

    /**
     * The part class of DepartmentStorePart.
     * @author FreeGen
     */
    public static class DepartmentStorePart {

        /** The property of storeName. */
        @Required
        public String storeName;

        /** The property of shopCount. (NullAllowed) */
        public Integer shopCount;

        /** The property of officialShop. (NullAllowed) */
        @javax.validation.Valid
        public OfficialShopPart officialShop;

        /**
         * The part class of OfficialShopPart.
         * @author FreeGen
         */
        public static class OfficialShopPart {

            /** The property of shopName. */
            @Required
            public String shopName;
        }

        /** The property of showStage. (NullAllowed) */
        @javax.validation.Valid
        public ShowStagePart showStage;

        /**
         * The part class of ShowStagePart.
         * @author FreeGen
         */
        public static class ShowStagePart {

            /** The property of stageName. */
            @Required
            public String stageName;
        }
    }

    /** The property of bigHotels. (NullAllowed) */
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<ThemeHotelPart> bigHotels;

    /**
     * The part class of ThemeHotelPart.
     * @author FreeGen
     */
    public static class ThemeHotelPart {

        /** The property of hotelName. */
        @Required
        public String hotelName;

        /** The property of correspondingPark. (NullAllowed) */
        @javax.validation.Valid
        public ResortParkPart correspondingPark;

        /** The property of officialShop. (NullAllowed) */
        @javax.validation.Valid
        public OfficialShopPart officialShop;

        /**
         * The part class of OfficialShopPart.
         * @author FreeGen
         */
        public static class OfficialShopPart {

            /** The property of shopName. */
            @Required
            public String shopName;
        }

        /** The property of showStage. (NullAllowed) */
        @javax.validation.Valid
        public ShowStagePart showStage;

        /**
         * The part class of ShowStagePart.
         * @author FreeGen
         */
        public static class ShowStagePart {

            /** The property of stageName. */
            @Required
            public String stageName;
        }

        /** The property of themeColor. (NullAllowed) */
        @javax.validation.Valid
        public ThemeColorPart themeColor;

        /**
         * The part class of ThemeColorPart.
         * @author FreeGen
         */
        public static class ThemeColorPart {

            /** The property of colorName. (NullAllowed) */
            public String colorName;

            /** The property of rgb. (NullAllowed) */
            public String rgb;
        }
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
