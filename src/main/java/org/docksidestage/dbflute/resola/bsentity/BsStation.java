package org.docksidestage.dbflute.resola.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.docksidestage.dbflute.resola.allcommon.RsoDBMetaInstanceHandler;
import org.docksidestage.dbflute.resola.exentity.*;

/**
 * The entity of station as TABLE. <br>
 * <pre>
 * [primary-key]
 *     STATION_ID
 *
 * [column]
 *     STATION_ID, STATION_NAME, BIRTHDATE, HOME_COUNT, WORKING_KILOMETER
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
 * Integer stationId = entity.getStationId();
 * String stationName = entity.getStationName();
 * java.time.LocalDate birthdate = entity.getBirthdate();
 * Integer homeCount = entity.getHomeCount();
 * java.math.BigDecimal workingKilometer = entity.getWorkingKilometer();
 * entity.setStationId(stationId);
 * entity.setStationName(stationName);
 * entity.setBirthdate(birthdate);
 * entity.setHomeCount(homeCount);
 * entity.setWorkingKilometer(workingKilometer);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsStation extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** STATION_ID: {PK, NotNull, INT(10)} */
    protected Integer _stationId;

    /** STATION_NAME: {NotNull, VARCHAR(200)} */
    protected String _stationName;

    /** BIRTHDATE: {DATE(10)} */
    protected java.time.LocalDate _birthdate;

    /** HOME_COUNT: {INT(10)} */
    protected Integer _homeCount;

    /** WORKING_KILOMETER: {DECIMAL(4, 2)} */
    protected java.math.BigDecimal _workingKilometer;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return RsoDBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "station";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_stationId == null) { return false; }
        return true;
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
        if (obj instanceof BsStation) {
            BsStation other = (BsStation)obj;
            if (!xSV(_stationId, other._stationId)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _stationId);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        return "";
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_stationId));
        sb.append(dm).append(xfND(_stationName));
        sb.append(dm).append(xfND(_birthdate));
        sb.append(dm).append(xfND(_homeCount));
        sb.append(dm).append(xfND(_workingKilometer));
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
    public Station clone() {
        return (Station)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] STATION_ID: {PK, NotNull, INT(10)} <br>
     * @return The value of the column 'STATION_ID'. (basically NotNull if selected: for the constraint)
     */
    public Integer getStationId() {
        checkSpecifiedProperty("stationId");
        return _stationId;
    }

    /**
     * [set] STATION_ID: {PK, NotNull, INT(10)} <br>
     * @param stationId The value of the column 'STATION_ID'. (basically NotNull if update: for the constraint)
     */
    public void setStationId(Integer stationId) {
        registerModifiedProperty("stationId");
        _stationId = stationId;
    }

    /**
     * [get] STATION_NAME: {NotNull, VARCHAR(200)} <br>
     * @return The value of the column 'STATION_NAME'. (basically NotNull if selected: for the constraint)
     */
    public String getStationName() {
        checkSpecifiedProperty("stationName");
        return _stationName;
    }

    /**
     * [set] STATION_NAME: {NotNull, VARCHAR(200)} <br>
     * @param stationName The value of the column 'STATION_NAME'. (basically NotNull if update: for the constraint)
     */
    public void setStationName(String stationName) {
        registerModifiedProperty("stationName");
        _stationName = stationName;
    }

    /**
     * [get] BIRTHDATE: {DATE(10)} <br>
     * @return The value of the column 'BIRTHDATE'. (NullAllowed even if selected: for no constraint)
     */
    public java.time.LocalDate getBirthdate() {
        checkSpecifiedProperty("birthdate");
        return _birthdate;
    }

    /**
     * [set] BIRTHDATE: {DATE(10)} <br>
     * @param birthdate The value of the column 'BIRTHDATE'. (NullAllowed: null update allowed for no constraint)
     */
    public void setBirthdate(java.time.LocalDate birthdate) {
        registerModifiedProperty("birthdate");
        _birthdate = birthdate;
    }

    /**
     * [get] HOME_COUNT: {INT(10)} <br>
     * @return The value of the column 'HOME_COUNT'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getHomeCount() {
        checkSpecifiedProperty("homeCount");
        return _homeCount;
    }

    /**
     * [set] HOME_COUNT: {INT(10)} <br>
     * @param homeCount The value of the column 'HOME_COUNT'. (NullAllowed: null update allowed for no constraint)
     */
    public void setHomeCount(Integer homeCount) {
        registerModifiedProperty("homeCount");
        _homeCount = homeCount;
    }

    /**
     * [get] WORKING_KILOMETER: {DECIMAL(4, 2)} <br>
     * @return The value of the column 'WORKING_KILOMETER'. (NullAllowed even if selected: for no constraint)
     */
    public java.math.BigDecimal getWorkingKilometer() {
        checkSpecifiedProperty("workingKilometer");
        return _workingKilometer;
    }

    /**
     * [set] WORKING_KILOMETER: {DECIMAL(4, 2)} <br>
     * @param workingKilometer The value of the column 'WORKING_KILOMETER'. (NullAllowed: null update allowed for no constraint)
     */
    public void setWorkingKilometer(java.math.BigDecimal workingKilometer) {
        registerModifiedProperty("workingKilometer");
        _workingKilometer = workingKilometer;
    }
}
