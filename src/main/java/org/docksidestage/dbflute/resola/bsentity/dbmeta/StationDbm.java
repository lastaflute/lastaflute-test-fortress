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
package org.docksidestage.dbflute.resola.bsentity.dbmeta;

import java.util.List;
import java.util.Map;

import org.dbflute.Entity;
import org.dbflute.dbmeta.AbstractDBMeta;
import org.dbflute.dbmeta.info.*;
import org.dbflute.dbmeta.name.*;
import org.dbflute.dbmeta.property.PropertyGateway;
import org.dbflute.dbway.DBDef;
import org.docksidestage.dbflute.resola.allcommon.*;
import org.docksidestage.dbflute.resola.exentity.*;

/**
 * The DB meta of station. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class StationDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final StationDbm _instance = new StationDbm();
    private StationDbm() {}
    public static StationDbm getInstance() { return _instance; }

    // ===================================================================================
    //                                                                       Current DBDef
    //                                                                       =============
    public String getProjectName() { return ResolaDBCurrent.getInstance().projectName(); }
    public String getProjectPrefix() { return ResolaDBCurrent.getInstance().projectPrefix(); }
    public String getGenerationGapBasePrefix() { return ResolaDBCurrent.getInstance().generationGapBasePrefix(); }
    public DBDef getCurrentDBDef() { return ResolaDBCurrent.getInstance().currentDBDef(); }

    // ===================================================================================
    //                                                                    Property Gateway
    //                                                                    ================
    // -----------------------------------------------------
    //                                       Column Property
    //                                       ---------------
    protected final Map<String, PropertyGateway> _epgMap = newHashMap();
    { xsetupEpg(); }
    protected void xsetupEpg() {
        setupEpg(_epgMap, et -> ((Station)et).getStationId(), (et, vl) -> ((Station)et).setStationId(cti(vl)), "stationId");
        setupEpg(_epgMap, et -> ((Station)et).getStationName(), (et, vl) -> ((Station)et).setStationName((String)vl), "stationName");
        setupEpg(_epgMap, et -> ((Station)et).getBirthdate(), (et, vl) -> ((Station)et).setBirthdate(ctld(vl)), "birthdate");
        setupEpg(_epgMap, et -> ((Station)et).getHomeCount(), (et, vl) -> ((Station)et).setHomeCount(cti(vl)), "homeCount");
        setupEpg(_epgMap, et -> ((Station)et).getWorkingKilometer(), (et, vl) -> ((Station)et).setWorkingKilometer(ctb(vl)), "workingKilometer");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "station";
    protected final String _tableDispName = "STATION";
    protected final String _tablePropertyName = "station";
    protected final TableSqlName _tableSqlName = new TableSqlName("STATION", _tableDbName);
    { _tableSqlName.xacceptFilter(ResolaDBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnStationId = cci("STATION_ID", "STATION_ID", null, null, Integer.class, "stationId", null, true, false, true, "INT", 10, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnStationName = cci("STATION_NAME", "STATION_NAME", null, null, String.class, "stationName", null, false, false, true, "VARCHAR", 200, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnBirthdate = cci("BIRTHDATE", "BIRTHDATE", null, null, java.time.LocalDate.class, "birthdate", null, false, false, false, "DATE", 10, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnHomeCount = cci("HOME_COUNT", "HOME_COUNT", null, null, Integer.class, "homeCount", null, false, false, false, "INT", 10, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnWorkingKilometer = cci("WORKING_KILOMETER", "WORKING_KILOMETER", null, null, java.math.BigDecimal.class, "workingKilometer", null, false, false, false, "DECIMAL", 4, 2, null, null, false, null, null, null, null, null, false);

    /**
     * STATION_ID: {PK, NotNull, INT(10)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnStationId() { return _columnStationId; }
    /**
     * STATION_NAME: {NotNull, VARCHAR(200)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnStationName() { return _columnStationName; }
    /**
     * BIRTHDATE: {DATE(10)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnBirthdate() { return _columnBirthdate; }
    /**
     * HOME_COUNT: {INT(10)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnHomeCount() { return _columnHomeCount; }
    /**
     * WORKING_KILOMETER: {DECIMAL(4, 2)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnWorkingKilometer() { return _columnWorkingKilometer; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnStationId());
        ls.add(columnStationName());
        ls.add(columnBirthdate());
        ls.add(columnHomeCount());
        ls.add(columnWorkingKilometer());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() { return hpcpui(columnStationId()); }
    public boolean hasPrimaryKey() { return true; }
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
    public String getEntityTypeName() { return "org.docksidestage.dbflute.resola.exentity.Station"; }
    public String getConditionBeanTypeName() { return "org.docksidestage.dbflute.resola.cbean.StationCB"; }
    public String getBehaviorTypeName() { return "org.docksidestage.dbflute.resola.exbhv.StationBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<Station> getEntityType() { return Station.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public Station newEntity() { return new Station(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((Station)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((Station)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
