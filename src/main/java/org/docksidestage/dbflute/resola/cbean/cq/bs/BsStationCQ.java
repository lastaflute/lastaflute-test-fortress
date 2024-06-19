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
package org.docksidestage.dbflute.resola.cbean.cq.bs;

import java.util.Map;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.docksidestage.dbflute.resola.cbean.cq.ciq.*;
import org.docksidestage.dbflute.resola.cbean.*;
import org.docksidestage.dbflute.resola.cbean.cq.*;

/**
 * The base condition-query of station.
 * @author DBFlute(AutoGenerator)
 */
public class BsStationCQ extends AbstractBsStationCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected StationCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsStationCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from station) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public StationCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected StationCIQ xcreateCIQ() {
        StationCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected StationCIQ xnewCIQ() {
        return new StationCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join station on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public StationCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        StationCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _stationId;
    public ConditionValue xdfgetStationId()
    { if (_stationId == null) { _stationId = nCV(); }
      return _stationId; }
    protected ConditionValue xgetCValueStationId() { return xdfgetStationId(); }

    /**
     * Add order-by as ascend. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_StationId_Asc() { regOBA("STATION_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_StationId_Desc() { regOBD("STATION_ID"); return this; }

    protected ConditionValue _stationName;
    public ConditionValue xdfgetStationName()
    { if (_stationName == null) { _stationName = nCV(); }
      return _stationName; }
    protected ConditionValue xgetCValueStationName() { return xdfgetStationName(); }

    /**
     * Add order-by as ascend. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_StationName_Asc() { regOBA("STATION_NAME"); return this; }

    /**
     * Add order-by as descend. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_StationName_Desc() { regOBD("STATION_NAME"); return this; }

    protected ConditionValue _birthdate;
    public ConditionValue xdfgetBirthdate()
    { if (_birthdate == null) { _birthdate = nCV(); }
      return _birthdate; }
    protected ConditionValue xgetCValueBirthdate() { return xdfgetBirthdate(); }

    /**
     * Add order-by as ascend. <br>
     * BIRTHDATE: {DATE(10)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_Birthdate_Asc() { regOBA("BIRTHDATE"); return this; }

    /**
     * Add order-by as descend. <br>
     * BIRTHDATE: {DATE(10)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_Birthdate_Desc() { regOBD("BIRTHDATE"); return this; }

    protected ConditionValue _homeCount;
    public ConditionValue xdfgetHomeCount()
    { if (_homeCount == null) { _homeCount = nCV(); }
      return _homeCount; }
    protected ConditionValue xgetCValueHomeCount() { return xdfgetHomeCount(); }

    /**
     * Add order-by as ascend. <br>
     * HOME_COUNT: {INT(10)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_HomeCount_Asc() { regOBA("HOME_COUNT"); return this; }

    /**
     * Add order-by as descend. <br>
     * HOME_COUNT: {INT(10)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_HomeCount_Desc() { regOBD("HOME_COUNT"); return this; }

    protected ConditionValue _workingKilometer;
    public ConditionValue xdfgetWorkingKilometer()
    { if (_workingKilometer == null) { _workingKilometer = nCV(); }
      return _workingKilometer; }
    protected ConditionValue xgetCValueWorkingKilometer() { return xdfgetWorkingKilometer(); }

    /**
     * Add order-by as ascend. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_WorkingKilometer_Asc() { regOBA("WORKING_KILOMETER"); return this; }

    /**
     * Add order-by as descend. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @return this. (NotNull)
     */
    public BsStationCQ addOrderBy_WorkingKilometer_Desc() { regOBD("WORKING_KILOMETER"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsStationCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsStationCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, StationCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(StationCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public Map<String, StationCQ> xdfgetSpecifyMyselfDerived() { return xgetSQueMap("specifyMyselfDerived"); }
    public String keepSpecifyMyselfDerived(StationCQ sq) { return xkeepSQue("specifyMyselfDerived", sq); }

    public Map<String, StationCQ> xdfgetQueryMyselfDerived() { return xgetSQueMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerived(StationCQ sq) { return xkeepSQue("queryMyselfDerived", sq); }
    public Map<String, Object> xdfgetQueryMyselfDerivedParameter() { return xgetSQuePmMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerivedParameter(Object pm) { return xkeepSQuePm("queryMyselfDerived", pm); }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, StationCQ> _myselfExistsMap;
    public Map<String, StationCQ> xdfgetMyselfExists() { return xgetSQueMap("myselfExists"); }
    public String keepMyselfExists(StationCQ sq) { return xkeepSQue("myselfExists", sq); }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    public Map<String, StationCQ> xdfgetMyselfInScope() { return xgetSQueMap("myselfInScope"); }
    public String keepMyselfInScope(StationCQ sq) { return xkeepSQue("myselfInScope", sq); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return StationCB.class.getName(); }
    protected String xCQ() { return StationCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
