/*
 * Copyright 2015-2017 the original author or authors.
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
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.docksidestage.dbflute.allcommon.CDef;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.web.validation.Required;
import org.lastaflute.web.validation.theme.conversion.ValidateTypeFailure;

/**
 * @author jflute
 */
public class WxValidatorForm {

    @Length(max = 10)
    public String productName;

    public CDef.ProductStatus productStatus;

    @Length(max = 5)
    public String purchaseMemberName;

    @ValidateTypeFailure
    public Integer seaInteger;
    @ValidateTypeFailure
    public Long seaLong;
    @ValidateTypeFailure
    public Float seaFloat;
    @ValidateTypeFailure
    public Double seaDouble;
    @ValidateTypeFailure
    public BigDecimal seaDecimal;
    @ValidateTypeFailure
    public LocalDate landDate;
    @ValidateTypeFailure
    public boolean iksMode;
    @ValidateTypeFailure
    public CDef.MemberStatus bonvoStatus;

    @Valid
    public SeaBean seaBean;
    @Valid
    public List<SeaBean> seaBeanList;
    @Valid
    public LandBean<PiariBean> landBean;
    @Valid
    public DstoreBean<List<GoodsBean>> dstoreBean;
    @Valid
    public AmbaBean<RoomBean> ambaBean;

    public static class SeaBean {
        @Required
        public Integer over;
        @Required
        public Boolean mystic;
    }

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
    }

    public static class BonvoBean {
        @Required
        public Integer yage;
    }

    public static class DstoreBean<GOODS> {
        @Required
        public GOODS goods;
    }

    public static class GoodsBean {
        @Required
        public Integer goodsId;
    }

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

    @Override
    public String toString() {
        return "{" + productName + ", " + productStatus + ", " + purchaseMemberName //
                + ", sea={" + seaInteger + ", " + seaLong + ", " + seaFloat + ", " + seaDouble + ", " + seaDecimal //
                + "}, land={" + landDate + "}, iks={" + iksMode + "}, bonvo={" + bonvoStatus + "}}";
    }
}
