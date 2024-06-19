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
package org.docksidestage.remote.fortress.wx.validator;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of GET /wx/validator/.
 * @author FreeGen
 */
public class RemoteWxValidatorParam {

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
    public String seaBean;

    /** The property of seaBeanList. (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<String> seaBeanList;

    /** The property of landBean. (NullAllowed) */
    public String landBean;

    /** The property of dstoreBean. (NullAllowed) */
    public String dstoreBean;

    /** The property of ambaBean. (NullAllowed) */
    public String ambaBean;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
