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
package org.docksidestage.app.web.wx.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.docksidestage.dbflute.allcommon.CDef;
import org.docksidestage.mylasta.action.FortressMessages;
import org.eclipse.collections.api.list.ImmutableList;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;
import org.lastaflute.web.validation.theme.conversion.ValidateTypeFailure;

/**
 * @author jflute
 */
public class WxValidatorForm {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // -----------------------------------------------------
    //                                               Example
    //                                               -------
    @Length(max = 10)
    public String productName;

    public CDef.ProductStatus productStatus;

    @Length(max = 5)
    public String purchaseMemberName;

    // -----------------------------------------------------
    //                                            Basic Type
    //                                            ----------
    @Length(max = 3)
    public String seaString;

    @ValidateTypeFailure
    @Max(88)
    public Integer seaInteger;

    @ValidateTypeFailure
    @Max(88)
    public Long seaLong;

    @ValidateTypeFailure
    @Max(88)
    public Float seaFloat;

    @ValidateTypeFailure
    @Max(88)
    public Double seaDouble;

    @ValidateTypeFailure
    @Max(88)
    public BigDecimal seaDecimal;

    @ValidateTypeFailure
    public LocalDate landDate;
    @ValidateTypeFailure
    public LocalDateTime landDateTime;

    @ValidateTypeFailure
    public boolean piariPrimBool;
    @ValidateTypeFailure
    public Boolean piariWrapBool;

    @ValidateTypeFailure
    public CDef.MemberStatus bonvoStatus;

    @ValidateTypeFailure
    public List<@Required String> dstoreStringList;
    @ValidateTypeFailure
    public List<@Max(88) Integer> dstoreIntegerList;
    @ValidateTypeFailure
    public ImmutableList<@Required String> dstoreImmutableList;

    // -----------------------------------------------------
    //                                              Sea Bean
    //                                              --------
    @Valid
    public SeaBean seaBean;
    @Valid
    public List<SeaBean> seaBeanList;

    public static class SeaBean {

        @Required
        public Integer over;
        @Required
        public Boolean mystic;

        @Valid
        public List<RestaurantBean> restaurantList;
        @Valid
        public List<RestaurantBean> restaurantImmutableInstanceList;
        @Valid
        public ImmutableList<RestaurantBean> restaurantImmutableTypeList;
        @Valid
        public Iterable<RestaurantBean> restaurantIterableArrayList;
        @Valid
        public Iterable<RestaurantBean> restaurantIterableImmutableList;

        public static class RestaurantBean {

            @Required
            public String restaurantName;

            @Valid
            public List<@Required String> genreList;

            @Valid
            public List<MenuBean> menuList;

            public static class MenuBean {

                @Required
                public String menuName;
            }
        }
    }

    // -----------------------------------------------------
    //                                             Land Bean
    //                                             ---------
    @Valid
    public LandBean<PiariBean> landBean;

    public static class LandBean<HAUNTED> {

        @Required
        public Integer oneman;
        @Required
        public Integer minio;
        @Required
        @Valid
        public HAUNTED haunted;
        @Required
        @Valid
        public BonvoBean bonvoBean;
    }

    public static class PiariBean {
        @Required
        public Integer iks;
        @Length(max = 3)
        public String plaza;
    }

    public static class BonvoBean {
        @Required
        public Integer yage;
    }

    // -----------------------------------------------------
    //                                           Dstore Bean
    //                                           -----------
    @Valid
    public DstoreBean<List<GoodsBean>> dstoreBean;

    public static class DstoreBean<GOODS> {

        @Required
        public GOODS goods;
    }

    public static class GoodsBean {
        @Required
        public Integer goodsId;
    }

    // -----------------------------------------------------
    //                                             Amba Bean
    //                                             ---------
    @Valid
    public AmbaBean<RoomBean> ambaBean;

    public static class AmbaBean<ROOM> {

        @Required
        public Integer ssador;
        @NotNull
        public List<ROOM> roomList; // #hope cannot check it
    }

    public static class RoomBean {

        @Required
        public Integer roomNo;
    }

    // ===================================================================================
    //                                                                          Validation
    //                                                                          ==========
    @AssertTrue(message = FortressMessages.ERRORS_PRODUCT_NAME_THEN_ONSALE)
    public boolean isProductNameToStatus() {
        if (productName == null) {
            return true;
        }
        if (productName.equals("sea")) {
            return CDef.ProductStatus.OnSaleProduction.equals(productStatus);
        } else {
            return true;
        }
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return Lato.string(this);
    }
}
