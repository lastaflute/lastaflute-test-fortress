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

import java.util.*;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.ckey.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.ordering.*;
import org.dbflute.cbean.scoping.*;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.dbmeta.DBMetaProvider;
import org.docksidestage.dbflute.resola.allcommon.*;
import org.docksidestage.dbflute.resola.cbean.*;
import org.docksidestage.dbflute.resola.cbean.cq.*;

/**
 * The abstract condition-query of station.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsStationCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsStationCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return ResolaDBMetaInstanceHandler.getProvider();
    }

    public String asTableDbName() {
        return "station";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationId The value of stationId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setStationId_Equal(Integer stationId) {
        doSetStationId_Equal(stationId);
    }

    protected void doSetStationId_Equal(Integer stationId) {
        regStationId(CK_EQ, stationId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationId The value of stationId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setStationId_NotEqual(Integer stationId) {
        doSetStationId_NotEqual(stationId);
    }

    protected void doSetStationId_NotEqual(Integer stationId) {
        regStationId(CK_NES, stationId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationId The value of stationId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setStationId_GreaterThan(Integer stationId) {
        regStationId(CK_GT, stationId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationId The value of stationId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setStationId_LessThan(Integer stationId) {
        regStationId(CK_LT, stationId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationId The value of stationId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setStationId_GreaterEqual(Integer stationId) {
        regStationId(CK_GE, stationId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationId The value of stationId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setStationId_LessEqual(Integer stationId) {
        regStationId(CK_LE, stationId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param minNumber The min number of stationId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of stationId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setStationId_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setStationId_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param minNumber The min number of stationId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of stationId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setStationId_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueStationId(), "STATION_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationIdList The collection of stationId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationId_InScope(Collection<Integer> stationIdList) {
        doSetStationId_InScope(stationIdList);
    }

    protected void doSetStationId_InScope(Collection<Integer> stationIdList) {
        regINS(CK_INS, cTL(stationIdList), xgetCValueStationId(), "STATION_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     * @param stationIdList The collection of stationId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationId_NotInScope(Collection<Integer> stationIdList) {
        doSetStationId_NotInScope(stationIdList);
    }

    protected void doSetStationId_NotInScope(Collection<Integer> stationIdList) {
        regINS(CK_NINS, cTL(stationIdList), xgetCValueStationId(), "STATION_ID");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     */
    public void setStationId_IsNull() { regStationId(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * STATION_ID: {PK, NotNull, INT(10)}
     */
    public void setStationId_IsNotNull() { regStationId(CK_ISNN, DOBJ); }

    protected void regStationId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueStationId(), "STATION_ID"); }
    protected abstract ConditionValue xgetCValueStationId();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_Equal(String stationName) {
        doSetStationName_Equal(fRES(stationName));
    }

    protected void doSetStationName_Equal(String stationName) {
        regStationName(CK_EQ, stationName);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_NotEqual(String stationName) {
        doSetStationName_NotEqual(fRES(stationName));
    }

    protected void doSetStationName_NotEqual(String stationName) {
        regStationName(CK_NES, stationName);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_GreaterThan(String stationName) {
        regStationName(CK_GT, fRES(stationName));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_LessThan(String stationName) {
        regStationName(CK_LT, fRES(stationName));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_GreaterEqual(String stationName) {
        regStationName(CK_GE, fRES(stationName));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_LessEqual(String stationName) {
        regStationName(CK_LE, fRES(stationName));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationNameList The collection of stationName as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_InScope(Collection<String> stationNameList) {
        doSetStationName_InScope(stationNameList);
    }

    protected void doSetStationName_InScope(Collection<String> stationNameList) {
        regINS(CK_INS, cTL(stationNameList), xgetCValueStationName(), "STATION_NAME");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationNameList The collection of stationName as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStationName_NotInScope(Collection<String> stationNameList) {
        doSetStationName_NotInScope(stationNameList);
    }

    protected void doSetStationName_NotInScope(Collection<String> stationNameList) {
        regINS(CK_NINS, cTL(stationNameList), xgetCValueStationName(), "STATION_NAME");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)} <br>
     * <pre>e.g. setStationName_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param stationName The value of stationName as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setStationName_LikeSearch(String stationName, ConditionOptionCall<LikeSearchOption> opLambda) {
        setStationName_LikeSearch(stationName, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)} <br>
     * <pre>e.g. setStationName_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param stationName The value of stationName as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setStationName_LikeSearch(String stationName, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(stationName), xgetCValueStationName(), "STATION_NAME", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setStationName_NotLikeSearch(String stationName, ConditionOptionCall<LikeSearchOption> opLambda) {
        setStationName_NotLikeSearch(stationName, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @param stationName The value of stationName as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setStationName_NotLikeSearch(String stationName, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(stationName), xgetCValueStationName(), "STATION_NAME", likeSearchOption);
    }

    protected void regStationName(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueStationName(), "STATION_NAME"); }
    protected abstract ConditionValue xgetCValueStationName();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     * @param birthdate The value of birthdate as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setBirthdate_Equal(java.time.LocalDate birthdate) {
        regBirthdate(CK_EQ,  birthdate);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     * @param birthdate The value of birthdate as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setBirthdate_GreaterThan(java.time.LocalDate birthdate) {
        regBirthdate(CK_GT,  birthdate);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     * @param birthdate The value of birthdate as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setBirthdate_LessThan(java.time.LocalDate birthdate) {
        regBirthdate(CK_LT,  birthdate);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     * @param birthdate The value of birthdate as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setBirthdate_GreaterEqual(java.time.LocalDate birthdate) {
        regBirthdate(CK_GE,  birthdate);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     * @param birthdate The value of birthdate as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setBirthdate_LessEqual(java.time.LocalDate birthdate) {
        regBirthdate(CK_LE, birthdate);
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     * <pre>e.g. setBirthdate_FromTo(fromDate, toDate, op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">compareAsDate()</span>);</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of birthdate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of birthdate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of from-to. (NotNull)
     */
    public void setBirthdate_FromTo(java.time.LocalDate fromDatetime, java.time.LocalDate toDatetime, ConditionOptionCall<FromToOption> opLambda) {
        setBirthdate_FromTo(fromDatetime, toDatetime, xcFTOP(opLambda));
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     * <pre>e.g. setBirthdate_FromTo(fromDate, toDate, new <span style="color: #CC4747">FromToOption</span>().compareAsDate());</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of birthdate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of birthdate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param fromToOption The option of from-to. (NotNull)
     */
    protected void setBirthdate_FromTo(java.time.LocalDate fromDatetime, java.time.LocalDate toDatetime, FromToOption fromToOption) {
        String nm = "BIRTHDATE"; FromToOption op = fromToOption;
        regFTQ(xfFTHD(fromDatetime, nm, op), xfFTHD(toDatetime, nm, op), xgetCValueBirthdate(), nm, op);
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     */
    public void setBirthdate_IsNull() { regBirthdate(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * BIRTHDATE: {DATE(10)}
     */
    public void setBirthdate_IsNotNull() { regBirthdate(CK_ISNN, DOBJ); }

    protected void regBirthdate(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueBirthdate(), "BIRTHDATE"); }
    protected abstract ConditionValue xgetCValueBirthdate();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCount The value of homeCount as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setHomeCount_Equal(Integer homeCount) {
        doSetHomeCount_Equal(homeCount);
    }

    protected void doSetHomeCount_Equal(Integer homeCount) {
        regHomeCount(CK_EQ, homeCount);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCount The value of homeCount as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setHomeCount_NotEqual(Integer homeCount) {
        doSetHomeCount_NotEqual(homeCount);
    }

    protected void doSetHomeCount_NotEqual(Integer homeCount) {
        regHomeCount(CK_NES, homeCount);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCount The value of homeCount as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setHomeCount_GreaterThan(Integer homeCount) {
        regHomeCount(CK_GT, homeCount);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCount The value of homeCount as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setHomeCount_LessThan(Integer homeCount) {
        regHomeCount(CK_LT, homeCount);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCount The value of homeCount as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setHomeCount_GreaterEqual(Integer homeCount) {
        regHomeCount(CK_GE, homeCount);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCount The value of homeCount as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setHomeCount_LessEqual(Integer homeCount) {
        regHomeCount(CK_LE, homeCount);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param minNumber The min number of homeCount. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of homeCount. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setHomeCount_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setHomeCount_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param minNumber The min number of homeCount. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of homeCount. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setHomeCount_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueHomeCount(), "HOME_COUNT", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCountList The collection of homeCount as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setHomeCount_InScope(Collection<Integer> homeCountList) {
        doSetHomeCount_InScope(homeCountList);
    }

    protected void doSetHomeCount_InScope(Collection<Integer> homeCountList) {
        regINS(CK_INS, cTL(homeCountList), xgetCValueHomeCount(), "HOME_COUNT");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * HOME_COUNT: {INT(10)}
     * @param homeCountList The collection of homeCount as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setHomeCount_NotInScope(Collection<Integer> homeCountList) {
        doSetHomeCount_NotInScope(homeCountList);
    }

    protected void doSetHomeCount_NotInScope(Collection<Integer> homeCountList) {
        regINS(CK_NINS, cTL(homeCountList), xgetCValueHomeCount(), "HOME_COUNT");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     */
    public void setHomeCount_IsNull() { regHomeCount(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * HOME_COUNT: {INT(10)}
     */
    public void setHomeCount_IsNotNull() { regHomeCount(CK_ISNN, DOBJ); }

    protected void regHomeCount(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueHomeCount(), "HOME_COUNT"); }
    protected abstract ConditionValue xgetCValueHomeCount();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometer The value of workingKilometer as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setWorkingKilometer_Equal(java.math.BigDecimal workingKilometer) {
        doSetWorkingKilometer_Equal(workingKilometer);
    }

    protected void doSetWorkingKilometer_Equal(java.math.BigDecimal workingKilometer) {
        regWorkingKilometer(CK_EQ, workingKilometer);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometer The value of workingKilometer as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setWorkingKilometer_NotEqual(java.math.BigDecimal workingKilometer) {
        doSetWorkingKilometer_NotEqual(workingKilometer);
    }

    protected void doSetWorkingKilometer_NotEqual(java.math.BigDecimal workingKilometer) {
        regWorkingKilometer(CK_NES, workingKilometer);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometer The value of workingKilometer as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setWorkingKilometer_GreaterThan(java.math.BigDecimal workingKilometer) {
        regWorkingKilometer(CK_GT, workingKilometer);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometer The value of workingKilometer as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setWorkingKilometer_LessThan(java.math.BigDecimal workingKilometer) {
        regWorkingKilometer(CK_LT, workingKilometer);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometer The value of workingKilometer as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setWorkingKilometer_GreaterEqual(java.math.BigDecimal workingKilometer) {
        regWorkingKilometer(CK_GE, workingKilometer);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometer The value of workingKilometer as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setWorkingKilometer_LessEqual(java.math.BigDecimal workingKilometer) {
        regWorkingKilometer(CK_LE, workingKilometer);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param minNumber The min number of workingKilometer. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of workingKilometer. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setWorkingKilometer_RangeOf(java.math.BigDecimal minNumber, java.math.BigDecimal maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setWorkingKilometer_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param minNumber The min number of workingKilometer. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of workingKilometer. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setWorkingKilometer_RangeOf(java.math.BigDecimal minNumber, java.math.BigDecimal maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueWorkingKilometer(), "WORKING_KILOMETER", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometerList The collection of workingKilometer as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setWorkingKilometer_InScope(Collection<java.math.BigDecimal> workingKilometerList) {
        doSetWorkingKilometer_InScope(workingKilometerList);
    }

    protected void doSetWorkingKilometer_InScope(Collection<java.math.BigDecimal> workingKilometerList) {
        regINS(CK_INS, cTL(workingKilometerList), xgetCValueWorkingKilometer(), "WORKING_KILOMETER");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @param workingKilometerList The collection of workingKilometer as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setWorkingKilometer_NotInScope(Collection<java.math.BigDecimal> workingKilometerList) {
        doSetWorkingKilometer_NotInScope(workingKilometerList);
    }

    protected void doSetWorkingKilometer_NotInScope(Collection<java.math.BigDecimal> workingKilometerList) {
        regINS(CK_NINS, cTL(workingKilometerList), xgetCValueWorkingKilometer(), "WORKING_KILOMETER");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     */
    public void setWorkingKilometer_IsNull() { regWorkingKilometer(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     */
    public void setWorkingKilometer_IsNotNull() { regWorkingKilometer(CK_ISNN, DOBJ); }

    protected void regWorkingKilometer(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueWorkingKilometer(), "WORKING_KILOMETER"); }
    protected abstract ConditionValue xgetCValueWorkingKilometer();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO = (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<StationCB> scalar_Equal() {
        return xcreateSLCFunction(CK_EQ, StationCB.class);
    }

    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO &lt;&gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<StationCB> scalar_NotEqual() {
        return xcreateSLCFunction(CK_NES, StationCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br>
     * {where FOO &gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<StationCB> scalar_GreaterThan() {
        return xcreateSLCFunction(CK_GT, StationCB.class);
    }

    /**
     * Prepare ScalarCondition as lessThan. <br>
     * {where FOO &lt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<StationCB> scalar_LessThan() {
        return xcreateSLCFunction(CK_LT, StationCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br>
     * {where FOO &gt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<StationCB> scalar_GreaterEqual() {
        return xcreateSLCFunction(CK_GE, StationCB.class);
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br>
     * {where FOO &lt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().<span style="color: #CC4747">scalar_LessEqual()</span>.max(new SubQuery&lt;StationCB&gt;() {
     *     public void query(StationCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<StationCB> scalar_LessEqual() {
        return xcreateSLCFunction(CK_LE, StationCB.class);
    }

    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xscalarCondition(String fn, SubQuery<CB> sq, String rd, HpSLCCustomized<CB> cs, ScalarConditionOption op) {
        assertObjectNotNull("subQuery", sq);
        StationCB cb = xcreateScalarConditionCB(); sq.query((CB)cb);
        String pp = keepScalarCondition(cb.query()); // for saving query-value
        cs.setPartitionByCBean((CB)xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(fn, cb.query(), pp, rd, cs, op);
    }
    public abstract String keepScalarCondition(StationCQ sq);

    protected StationCB xcreateScalarConditionCB() {
        StationCB cb = newMyCB(); cb.xsetupForScalarCondition(this); return cb;
    }

    protected StationCB xcreateScalarConditionPartitionByCB() {
        StationCB cb = newMyCB(); cb.xsetupForScalarConditionPartitionBy(this); return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String fn, SubQuery<StationCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        StationCB cb = new StationCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepSpecifyMyselfDerived(cb.query()); String pk = "STATION_ID";
        registerSpecifyMyselfDerived(fn, cb.query(), pk, pk, pp, "myselfDerived", al, op);
    }
    public abstract String keepSpecifyMyselfDerived(StationCQ sq);

    /**
     * Prepare for (Query)MyselfDerived (correlated sub-query).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<StationCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived(StationCB.class);
    }
    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xqderiveMyselfDerived(String fn, SubQuery<CB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        StationCB cb = new StationCB(); cb.xsetupForDerivedReferrer(this); sq.query((CB)cb);
        String pk = "STATION_ID";
        String sqpp = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String prpp = keepQueryMyselfDerivedParameter(vl);
        registerQueryMyselfDerived(fn, cb.query(), pk, pk, sqpp, "myselfDerived", rd, vl, prpp, op);
    }
    public abstract String keepQueryMyselfDerived(StationCQ sq);
    public abstract String keepQueryMyselfDerivedParameter(Object vl);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (correlated sub-query).
     * @param subCBLambda The implementation of sub-query. (NotNull)
     */
    public void myselfExists(SubQuery<StationCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        StationCB cb = new StationCB(); cb.xsetupForMyselfExists(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepMyselfExists(cb.query());
        registerMyselfExists(cb.query(), pp);
    }
    public abstract String keepMyselfExists(StationCQ sq);

    // ===================================================================================
    //                                                                        Manual Order
    //                                                                        ============
    /**
     * Order along manual ordering information.
     * <pre>
     * cb.query().addOrderBy_Birthdate_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_GreaterEqual</span>(priorityDate); <span style="color: #3F7E5E">// e.g. 2000/01/01</span>
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when BIRTHDATE &gt;= '2000/01/01' then 0</span>
     * <span style="color: #3F7E5E">//     else 1</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     *
     * cb.query().addOrderBy_MemberStatusCode_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Withdrawal);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Formalized);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Provisional);
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'WDL' then 0</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'FML' then 1</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'PRV' then 2</span>
     * <span style="color: #3F7E5E">//     else 3</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     * </pre>
     * <p>This function with Union is unsupported!</p>
     * <p>The order values are bound (treated as bind parameter).</p>
     * @param opLambda The callback for option of manual-order containing order values. (NotNull)
     */
    public void withManualOrder(ManualOrderOptionCall opLambda) { // is user public!
        xdoWithManualOrder(cMOO(opLambda));
    }

    // ===================================================================================
    //                                                                    Small Adjustment
    //                                                                    ================
    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    protected StationCB newMyCB() {
        return new StationCB();
    }
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabUDT() { return Date.class.getName(); }
    protected String xabCQ() { return StationCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSLCS() { return HpSLCSetupper.class.getName(); }
    protected String xabSCP() { return SubQuery.class.getName(); }
}
