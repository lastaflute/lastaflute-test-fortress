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
package org.docksidestage.remote.fortress.wx.validator.wholejson;

import javax.validation.constraints.NotNull;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as param for remote API of POST /wx/validator/wholejson.
 * @author FreeGen
 */
public class RemoteWxValidatorWholejsonParam {

    /** The property of productName. (NullAllowed) */
    public String productName;

    /** The property of productStatus. (enumValue=[ONS, PST, SST]) ( * `ONS` - OnSaleProduction. * `PST` - ProductionStop. * `SST` - SaleStop. :: fromCls(CDef$ProductStatus)) (NullAllowed) */
    public org.docksidestage.dbflute.allcommon.CDef.ProductStatus productStatus;

    /** The property of purchaseMemberName. (NullAllowed) */
    public String purchaseMemberName;

    /** The property of seaString. (NullAllowed) */
    public String seaString;

    /** The property of seaInteger. (NullAllowed) */
    public Integer seaInteger;

    /** The property of seaLong. (NullAllowed) */
    public Long seaLong;

    /** The property of seaFloat. (NullAllowed) */
    public Float seaFloat;

    /** The property of seaDouble. (NullAllowed) */
    public Double seaDouble;

    /** The property of seaDecimal. (NullAllowed) */
    public Double seaDecimal;

    /** The property of landDate. (NullAllowed) */
    public java.time.LocalDate landDate;

    /** The property of landDateTime. (NullAllowed) */
    public java.time.LocalDateTime landDateTime;

    /** The property of piariPrimBool. (NullAllowed) */
    public Boolean piariPrimBool;

    /** The property of piariWrapBool. (NullAllowed) */
    public Boolean piariWrapBool;

    /** The property of bonvoStatus. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NullAllowed) */
    public String bonvoStatus;

    /** The property of dstoreStringList. (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<String> dstoreStringList;

    /** The property of dstoreIntegerList. (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<Integer> dstoreIntegerList;

    /** The property of dstoreImmutableList. (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<String> dstoreImmutableList;

    /** The property of seaBean. (NullAllowed) */
    @javax.validation.Valid
    public SeaPart seaBean;

    /**
     * The part class of SeaPart.
     * @author FreeGen
     */
    public static class SeaPart {

        /** The property of over. */
        @Required
        public Integer over;

        /** The property of mystic. */
        @Required
        public Boolean mystic;

        /** The property of restaurantList. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<RestaurantPart> restaurantList;

        /**
         * The part class of RestaurantPart.
         * @author FreeGen
         */
        public static class RestaurantPart {

            /** The property of restaurantName. */
            @Required
            public String restaurantName;

            /** The property of genreList. (NullAllowed) */
            public org.eclipse.collections.api.list.ImmutableList<String> genreList;

            /** The property of menuList. (NullAllowed) */
            @javax.validation.Valid
            public org.eclipse.collections.api.list.ImmutableList<MenuPart> menuList;

            /**
             * The part class of MenuPart.
             * @author FreeGen
             */
            public static class MenuPart {

                /** The property of menuName. */
                @Required
                public String menuName;
            }
        }

        /** The property of restaurantImmutableInstanceList. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<RestaurantPart> restaurantImmutableInstanceList;

        /** The property of restaurantImmutableTypeList. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<RestaurantPart> restaurantImmutableTypeList;

        /** The property of restaurantIterableArrayList. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<RestaurantPart> restaurantIterableArrayList;

        /** The property of restaurantIterableImmutableList. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<RestaurantPart> restaurantIterableImmutableList;
    }

    /** The property of seaBeanList. (NullAllowed) */
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<SeaPart> seaBeanList;

    /** The property of landBean. (NullAllowed) */
    @javax.validation.Valid
    public LandPart landBean;

    /**
     * The part class of LandPart.
     * @author FreeGen
     */
    public static class LandPart {

        /** The property of oneman. */
        @Required
        public Integer oneman;

        /** The property of minio. */
        @Required
        public Integer minio;

        /** The property of haunted. */
        @Required
        public java.util.Map<String, Object> haunted;

        /** The property of bonvoBean. */
        @Required
        @javax.validation.Valid
        public BonvoPart bonvoBean;

        /**
         * The part class of BonvoPart.
         * @author FreeGen
         */
        public static class BonvoPart {

            /** The property of yage. */
            @Required
            public Integer yage;
        }
    }

    /** The property of dstoreBean. (NullAllowed) */
    @javax.validation.Valid
    public DstorePart dstoreBean;

    /**
     * The part class of DstorePart.
     * @author FreeGen
     */
    public static class DstorePart {

        /** The property of goods. */
        @Required
        public java.util.Map<String, Object> goods;
    }

    /** The property of ambaBean. (NullAllowed) */
    @javax.validation.Valid
    public AmbaPart ambaBean;

    /**
     * The part class of AmbaPart.
     * @author FreeGen
     */
    public static class AmbaPart {

        /** The property of ssador. */
        @Required
        public Integer ssador;

        /** The property of roomList. */
        @NotNull
        public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> roomList;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
