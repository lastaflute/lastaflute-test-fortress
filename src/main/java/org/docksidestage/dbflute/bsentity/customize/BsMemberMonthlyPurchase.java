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
package org.docksidestage.dbflute.bsentity.customize;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.CustomizeEntity;
import org.docksidestage.dbflute.exentity.customize.*;

/**
 * The entity of MemberMonthlyPurchase. <br>
 * <pre>
 * [primary-key]
 *     
 *
 * [column]
 *     MEMBER_ID, MEMBER_NAME, PURCHASE_MONTH, PURCHASE_PRICE_AVG, PURCHASE_PRICE_MAX, PURCHASE_COUNT, SERVICE_POINT_COUNT
 *
 * [sequence]
 *     
 *
 * [identity]
 *     
 *
 * [version-no]
 *     
 *
 * [foreign table]
 *     
 *
 * [referrer table]
 *     
 *
 * [foreign property]
 *     
 *
 * [referrer property]
 *     
 *
 * [get/set template]
 * /= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 * Integer memberId = entity.getMemberId();
 * String memberName = entity.getMemberName();
 * java.time.LocalDate purchaseMonth = entity.getPurchaseMonth();
 * java.math.BigDecimal purchasePriceAvg = entity.getPurchasePriceAvg();
 * Integer purchasePriceMax = entity.getPurchasePriceMax();
 * Integer purchaseCount = entity.getPurchaseCount();
 * Integer servicePointCount = entity.getServicePointCount();
 * entity.setMemberId(memberId);
 * entity.setMemberName(memberName);
 * entity.setPurchaseMonth(purchaseMonth);
 * entity.setPurchasePriceAvg(purchasePriceAvg);
 * entity.setPurchasePriceMax(purchasePriceMax);
 * entity.setPurchaseCount(purchaseCount);
 * entity.setServicePointCount(servicePointCount);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsMemberMonthlyPurchase extends AbstractEntity implements CustomizeEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** (会員ID)MEMBER_ID: {INT(11), refers to member.MEMBER_ID} */
    protected Integer _memberId;

    /** (会員名称)MEMBER_NAME: {VARCHAR(100), refers to member.MEMBER_NAME} */
    protected String _memberName;

    /** PURCHASE_MONTH: {DATE(10)} */
    protected java.time.LocalDate _purchaseMonth;

    /** PURCHASE_PRICE_AVG: {DECIMAL(14, 4)} */
    protected java.math.BigDecimal _purchasePriceAvg;

    /** PURCHASE_PRICE_MAX: {INT(11)} */
    protected Integer _purchasePriceMax;

    /** (購入数量)PURCHASE_COUNT: {INT(11), refers to purchase.PURCHASE_COUNT} */
    protected Integer _purchaseCount;

    /** (サービスポイント数)SERVICE_POINT_COUNT: {INT(11), refers to member_service.SERVICE_POINT_COUNT} */
    protected Integer _servicePointCount;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return org.docksidestage.dbflute.bsentity.customize.dbmeta.MemberMonthlyPurchaseDbm.getInstance();
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "MemberMonthlyPurchase";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        return false;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsMemberMonthlyPurchase) {
            BsMemberMonthlyPurchase other = (BsMemberMonthlyPurchase)obj;
            if (!xSV(_memberId, other._memberId)) { return false; }
            if (!xSV(_memberName, other._memberName)) { return false; }
            if (!xSV(_purchaseMonth, other._purchaseMonth)) { return false; }
            if (!xSV(_purchasePriceAvg, other._purchasePriceAvg)) { return false; }
            if (!xSV(_purchasePriceMax, other._purchasePriceMax)) { return false; }
            if (!xSV(_purchaseCount, other._purchaseCount)) { return false; }
            if (!xSV(_servicePointCount, other._servicePointCount)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _memberId);
        hs = xCH(hs, _memberName);
        hs = xCH(hs, _purchaseMonth);
        hs = xCH(hs, _purchasePriceAvg);
        hs = xCH(hs, _purchasePriceMax);
        hs = xCH(hs, _purchaseCount);
        hs = xCH(hs, _servicePointCount);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        return "";
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_memberId));
        sb.append(dm).append(xfND(_memberName));
        sb.append(dm).append(xfND(_purchaseMonth));
        sb.append(dm).append(xfND(_purchasePriceAvg));
        sb.append(dm).append(xfND(_purchasePriceMax));
        sb.append(dm).append(xfND(_purchaseCount));
        sb.append(dm).append(xfND(_servicePointCount));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        return "";
    }

    @Override
    public MemberMonthlyPurchase clone() {
        return (MemberMonthlyPurchase)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] (会員ID)MEMBER_ID: {INT(11), refers to member.MEMBER_ID} <br>
     * 連番として自動採番される。会員IDだけに限らず採番方法はDBMS次第。<br>
     * // grouping item
     * @return The value of the column 'MEMBER_ID'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getMemberId() {
        checkSpecifiedProperty("memberId");
        return _memberId;
    }

    /**
     * [set] (会員ID)MEMBER_ID: {INT(11), refers to member.MEMBER_ID} <br>
     * 連番として自動採番される。会員IDだけに限らず採番方法はDBMS次第。<br>
     * // grouping item
     * @param memberId The value of the column 'MEMBER_ID'. (NullAllowed: null update allowed for no constraint)
     */
    public void setMemberId(Integer memberId) {
        registerModifiedProperty("memberId");
        _memberId = memberId;
    }

    /**
     * [get] (会員名称)MEMBER_NAME: {VARCHAR(100), refers to member.MEMBER_NAME} <br>
     * 会員のフルネームの名称。<br>
     * 苗字と名前を分けて管理することが多いが、ここでは単純にひとまとめ。<br>
     * // non grouping item (1:1 data) is allowed on MySQL-5.6
     * @return The value of the column 'MEMBER_NAME'. (NullAllowed even if selected: for no constraint)
     */
    public String getMemberName() {
        checkSpecifiedProperty("memberName");
        return convertEmptyToNull(_memberName);
    }

    /**
     * [set] (会員名称)MEMBER_NAME: {VARCHAR(100), refers to member.MEMBER_NAME} <br>
     * 会員のフルネームの名称。<br>
     * 苗字と名前を分けて管理することが多いが、ここでは単純にひとまとめ。<br>
     * // non grouping item (1:1 data) is allowed on MySQL-5.6
     * @param memberName The value of the column 'MEMBER_NAME'. (NullAllowed: null update allowed for no constraint)
     */
    public void setMemberName(String memberName) {
        registerModifiedProperty("memberName");
        _memberName = memberName;
    }

    /**
     * [get] PURCHASE_MONTH: {DATE(10)} <br>
     * // grouping item, depends on DBMS
     * @return The value of the column 'PURCHASE_MONTH'. (NullAllowed even if selected: for no constraint)
     */
    public java.time.LocalDate getPurchaseMonth() {
        checkSpecifiedProperty("purchaseMonth");
        return _purchaseMonth;
    }

    /**
     * [set] PURCHASE_MONTH: {DATE(10)} <br>
     * // grouping item, depends on DBMS
     * @param purchaseMonth The value of the column 'PURCHASE_MONTH'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPurchaseMonth(java.time.LocalDate purchaseMonth) {
        registerModifiedProperty("purchaseMonth");
        _purchaseMonth = purchaseMonth;
    }

    /**
     * [get] PURCHASE_PRICE_AVG: {DECIMAL(14, 4)} <br>
     * // aggregation item
     * @return The value of the column 'PURCHASE_PRICE_AVG'. (NullAllowed even if selected: for no constraint)
     */
    public java.math.BigDecimal getPurchasePriceAvg() {
        checkSpecifiedProperty("purchasePriceAvg");
        return _purchasePriceAvg;
    }

    /**
     * [set] PURCHASE_PRICE_AVG: {DECIMAL(14, 4)} <br>
     * // aggregation item
     * @param purchasePriceAvg The value of the column 'PURCHASE_PRICE_AVG'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPurchasePriceAvg(java.math.BigDecimal purchasePriceAvg) {
        registerModifiedProperty("purchasePriceAvg");
        _purchasePriceAvg = purchasePriceAvg;
    }

    /**
     * [get] PURCHASE_PRICE_MAX: {INT(11)} <br>
     * // me too
     * @return The value of the column 'PURCHASE_PRICE_MAX'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getPurchasePriceMax() {
        checkSpecifiedProperty("purchasePriceMax");
        return _purchasePriceMax;
    }

    /**
     * [set] PURCHASE_PRICE_MAX: {INT(11)} <br>
     * // me too
     * @param purchasePriceMax The value of the column 'PURCHASE_PRICE_MAX'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPurchasePriceMax(Integer purchasePriceMax) {
        registerModifiedProperty("purchasePriceMax");
        _purchasePriceMax = purchasePriceMax;
    }

    /**
     * [get] (購入数量)PURCHASE_COUNT: {INT(11), refers to purchase.PURCHASE_COUNT} <br>
     * 購入した商品の一回の購入における数量。
     * @return The value of the column 'PURCHASE_COUNT'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getPurchaseCount() {
        checkSpecifiedProperty("purchaseCount");
        return _purchaseCount;
    }

    /**
     * [set] (購入数量)PURCHASE_COUNT: {INT(11), refers to purchase.PURCHASE_COUNT} <br>
     * 購入した商品の一回の購入における数量。
     * @param purchaseCount The value of the column 'PURCHASE_COUNT'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPurchaseCount(Integer purchaseCount) {
        registerModifiedProperty("purchaseCount");
        _purchaseCount = purchaseCount;
    }

    /**
     * [get] (サービスポイント数)SERVICE_POINT_COUNT: {INT(11), refers to member_service.SERVICE_POINT_COUNT} <br>
     * 購入したら増えて使ったら減る。<br>
     * // non grouping item (relationship 1:1 data) is allowed on MySQL-5.6
     * @return The value of the column 'SERVICE_POINT_COUNT'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getServicePointCount() {
        checkSpecifiedProperty("servicePointCount");
        return _servicePointCount;
    }

    /**
     * [set] (サービスポイント数)SERVICE_POINT_COUNT: {INT(11), refers to member_service.SERVICE_POINT_COUNT} <br>
     * 購入したら増えて使ったら減る。<br>
     * // non grouping item (relationship 1:1 data) is allowed on MySQL-5.6
     * @param servicePointCount The value of the column 'SERVICE_POINT_COUNT'. (NullAllowed: null update allowed for no constraint)
     */
    public void setServicePointCount(Integer servicePointCount) {
        registerModifiedProperty("servicePointCount");
        _servicePointCount = servicePointCount;
    }
}
