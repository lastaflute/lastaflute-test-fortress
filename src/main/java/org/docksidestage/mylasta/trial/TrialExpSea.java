package org.docksidestage.mylasta.trial;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.dbflute.cbean.coption.LikeSearchOption;
import org.lastaflute.core.security.PrimaryCipher;
import org.lastaflute.di.util.LdiArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class TrialExpSea {

    private static final Logger logger = LoggerFactory.getLogger(TrialDiSea.class);

    private String simpleString;
    private Integer simpleInteger;
    private Long simpleLong;
    private Integer nearlyMaxInteger;
    private Long overMaxInteger; // is long

    // ===================================================================================
    //                                                                          Add Method
    //                                                                          ==========
    public void addStringList(List<String> strList) {
        logger.debug("...Adding List<String>: {}", strList);
    }

    public void addStringMap(Map<String, String> strMap) {
        logger.debug("...Adding Map<String, String>: {}", strMap);
    }

    public void addIntArray(int[] intAry) {
        logger.debug("...Adding int[]: {}", LdiArrayUtil.toList(intAry));
    }

    public void addStringArray(String[] StrAry) {
        logger.debug("...Adding String[]: {}", Arrays.asList(StrAry));
    }

    public void addNewInstance(LikeSearchOption option) {
        logger.debug("...Adding new-instance: {}", option);
    }

    public void addType(Class<?> type) {
        logger.debug("...Adding type: {}", type);
    }

    public void addStaticFinal(String str) {
        logger.debug("...Adding static final: {}", str);
    }

    public void addStaticMethod(String str) {
        logger.debug("...Adding static final: {}", str);
    }

    public void addConfigProp(String configProp) {
        logger.debug("...Adding config property: {}", configProp);
    }

    public void addCipher(PrimaryCipher cipher) {
        logger.debug("...Adding cipher: {}", cipher);
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getSimpleString() {
        return simpleString;
    }

    public void setSimpleString(String simpleString) {
        logger.debug("...Setting simple string: {}", simpleString);
        this.simpleString = simpleString;
    }

    public Integer getSimpleInteger() {
        return simpleInteger;
    }

    public void setSimpleInteger(Integer simpleInteger) {
        logger.debug("...Setting simple integer: {}", simpleInteger);
        this.simpleInteger = simpleInteger;
    }

    public Long getSimpleLong() {
        return simpleLong;
    }

    public void setSimpleLong(Long simpleLong) {
        logger.debug("...Setting simple long: {}", simpleLong);
        this.simpleLong = simpleLong;
    }

    public Integer getNearlyMaxInteger() {
        return nearlyMaxInteger;
    }

    public void setNearlyMaxInteger(Integer nearlyMaxInteger) {
        logger.debug("...Setting nearly-max integer: {}", nearlyMaxInteger);
        this.nearlyMaxInteger = nearlyMaxInteger;
    }

    public Long getOverMaxInteger() {
        return overMaxInteger;
    }

    public void setOverMaxInteger(Long overMaxInteger) {
        logger.debug("...Setting nearly-max integer: {}", overMaxInteger);
        this.overMaxInteger = overMaxInteger;
    }
}