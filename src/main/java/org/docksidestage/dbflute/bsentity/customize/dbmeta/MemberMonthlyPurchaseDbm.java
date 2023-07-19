/*
 * Copyright 2015-2022 the original author or authors.
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
package org.docksidestage.dbflute.bsentity.customize.dbmeta;

import java.util.List;
import java.util.Map;

import org.dbflute.Entity;
import org.dbflute.dbmeta.AbstractDBMeta;
import org.dbflute.dbmeta.info.*;
import org.dbflute.dbmeta.name.*;
import org.dbflute.dbmeta.property.PropertyGateway;
import org.dbflute.dbway.DBDef;
import org.docksidestage.dbflute.allcommon.*;
import org.docksidestage.dbflute.exentity.customize.*;

/**
 * The DB meta of MemberMonthlyPurchase. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class MemberMonthlyPurchaseDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final MemberMonthlyPurchaseDbm _instance = new MemberMonthlyPurchaseDbm();
    private MemberMonthlyPurchaseDbm() {}
    public static MemberMonthlyPurchaseDbm getInstance() { return _instance; }

    // ===================================================================================
    //                                                                       Current DBDef
    //                                                                       =============
    public String getProjectName() { return DBCurrent.getInstance().projectName(); }
    public String getProjectPrefix() { return DBCurrent.getInstance().projectPrefix(); }
    public String getGenerationGapBasePrefix() { return DBCurrent.getInstance().generationGapBasePrefix(); }
    public DBDef getCurrentDBDef() { return DBCurrent.getInstance().currentDBDef(); }

    // ===================================================================================
    //                                                                    Property Gateway
    //                                                                    ================
    // -----------------------------------------------------
    //                                       Column Property
    //                                       ---------------
    protected final Map<String, PropertyGateway> _epgMap = newHashMap();
    { xsetupEpg(); }
    protected void xsetupEpg() {
        setupEpg(_epgMap, et -> ((MemberMonthlyPurchase)et).getMemberId(), (et, vl) -> ((MemberMonthlyPurchase)et).setMemberId(cti(vl)), "memberId");
        setupEpg(_epgMap, et -> ((MemberMonthlyPurchase)et).getMemberName(), (et, vl) -> ((MemberMonthlyPurchase)et).setMemberName((String)vl), "memberName");
        setupEpg(_epgMap, et -> ((MemberMonthlyPurchase)et).getPurchaseMonth(), (et, vl) -> ((MemberMonthlyPurchase)et).setPurchaseMonth(ctld(vl)), "purchaseMonth");
        setupEpg(_epgMap, et -> ((MemberMonthlyPurchase)et).getPurchasePriceAvg(), (et, vl) -> ((MemberMonthlyPurchase)et).setPurchasePriceAvg(ctb(vl)), "purchasePriceAvg");
        setupEpg(_epgMap, et -> ((MemberMonthlyPurchase)et).getPurchasePriceMax(), (et, vl) -> ((MemberMonthlyPurchase)et).setPurchasePriceMax(cti(vl)), "purchasePriceMax");
        setupEpg(_epgMap, et -> ((MemberMonthlyPurchase)et).getPurchaseCount(), (et, vl) -> ((MemberMonthlyPurchase)et).setPurchaseCount(cti(vl)), "purchaseCount");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "MemberMonthlyPurchase";
    protected final String _tableDispName = "MemberMonthlyPurchase";
    protected final String _tablePropertyName = "memberMonthlyPurchase";
    protected final TableSqlName _tableSqlName = new TableSqlName("MemberMonthlyPurchase", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnMemberId = cci("MEMBER_ID", "MEMBER_ID", null, "会員ID", Integer.class, "memberId", null, false, false, false, "INT", 11, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnMemberName = cci("MEMBER_NAME", "MEMBER_NAME", null, "会員名称", String.class, "memberName", null, false, false, false, "VARCHAR", 100, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPurchaseMonth = cci("PURCHASE_MONTH", "PURCHASE_MONTH", null, null, java.time.LocalDate.class, "purchaseMonth", null, false, false, false, "DATE", 10, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPurchasePriceAvg = cci("PURCHASE_PRICE_AVG", "PURCHASE_PRICE_AVG", null, null, java.math.BigDecimal.class, "purchasePriceAvg", null, false, false, false, "DECIMAL", 14, 4, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPurchasePriceMax = cci("PURCHASE_PRICE_MAX", "PURCHASE_PRICE_MAX", null, null, Integer.class, "purchasePriceMax", null, false, false, false, "INT", 11, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPurchaseCount = cci("PURCHASE_COUNT", "PURCHASE_COUNT", null, "購入数量", Integer.class, "purchaseCount", null, false, false, false, "INT", 11, 0, null, null, false, null, null, null, null, null, false);

    /**
     * (会員ID)MEMBER_ID: {INT(11), refers to member.MEMBER_ID}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnMemberId() { return _columnMemberId; }
    /**
     * (会員名称)MEMBER_NAME: {VARCHAR(100), refers to member.MEMBER_NAME}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnMemberName() { return _columnMemberName; }
    /**
     * PURCHASE_MONTH: {DATE(10)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPurchaseMonth() { return _columnPurchaseMonth; }
    /**
     * PURCHASE_PRICE_AVG: {DECIMAL(14, 4)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPurchasePriceAvg() { return _columnPurchasePriceAvg; }
    /**
     * PURCHASE_PRICE_MAX: {INT(11)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPurchasePriceMax() { return _columnPurchasePriceMax; }
    /**
     * (購入数量)PURCHASE_COUNT: {INT(11), refers to purchase.PURCHASE_COUNT}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPurchaseCount() { return _columnPurchaseCount; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnMemberId());
        ls.add(columnMemberName());
        ls.add(columnPurchaseMonth());
        ls.add(columnPurchasePriceAvg());
        ls.add(columnPurchasePriceMax());
        ls.add(columnPurchaseCount());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() {
        throw new UnsupportedOperationException("The table does not have primary key: " + getTableDbName());
    }
    public boolean hasPrimaryKey() { return false; }
    public boolean hasCompoundPrimaryKey() { return false; }

    // ===================================================================================
    //                                                                       Relation Info
    //                                                                       =============
    // cannot cache because it uses related DB meta instance while booting
    // (instead, cached by super's collection)
    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------

    // -----------------------------------------------------
    //                                     Referrer Property
    //                                     -----------------

    // ===================================================================================
    //                                                                        Various Info
    //                                                                        ============

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "org.docksidestage.dbflute.exentity.customize.MemberMonthlyPurchase"; }
    public String getConditionBeanTypeName() { return null; }
    public String getBehaviorTypeName() { return null; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<MemberMonthlyPurchase> getEntityType() { return MemberMonthlyPurchase.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public MemberMonthlyPurchase newEntity() { return new MemberMonthlyPurchase(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((MemberMonthlyPurchase)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((MemberMonthlyPurchase)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
