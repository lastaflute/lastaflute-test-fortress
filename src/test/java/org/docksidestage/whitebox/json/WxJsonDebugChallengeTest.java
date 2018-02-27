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
package org.docksidestage.whitebox.json;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.annotation.Resource;

import org.docksidestage.dbflute.allcommon.CDef;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.web.ruts.process.debugchallenge.JsonDebugChallenge;

/**
 * @author jflute
 */
public class WxJsonDebugChallengeTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                                             Integer
    //                                                                             =======
    public void test_JsonDebugChallenge_Integer_zeroDecimal() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(Integer.class, "1.0");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("x Integer sea = (String) \"1.0\"", disp.trim());
    }

    public void test_JsonDebugChallenge_Integer_zeroDouble() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(Integer.class, (Double) 1.0);

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("o Integer sea = (Integer) \"1\"", disp.trim());
    }

    // ===================================================================================
    //                                                                               Long
    //                                                                              ======
    public void test_JsonDebugChallenge_Long_zeroDecimal() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(Long.class, "1.0");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("x Long sea = (String) \"1.0\"", disp.trim());
    }

    public void test_JsonDebugChallenge_Long_zeroDouble() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(Long.class, (Double) 1.0);

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("o Long sea = (Long) \"1\"", disp.trim());
    }

    // ===================================================================================
    //                                                                               Date
    //                                                                              ======
    // -----------------------------------------------------
    //                                             LocalDate
    //                                             ---------
    public void test_JsonDebugChallenge_Date_LocalDate_basic() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(LocalDate.class, "2017-08-24");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("o LocalDate sea = (LocalDate) \"2017-08-24\"", disp.trim());
    }

    public void test_JsonDebugChallenge_Date_LocalDate_cannot() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(LocalDate.class, "2017/08/24");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("x LocalDate sea = (String) \"2017/08/24\"", disp.trim());
    }

    // -----------------------------------------------------
    //                                         LocalDateTime
    //                                         -------------
    public void test_JsonDebugChallenge_Date_LocalDateTime_basic() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(LocalDateTime.class, "2017-08-24T12:34:56");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("o LocalDateTime sea = (LocalDateTime) \"2017-08-24T12:34:56\"", disp.trim());
    }

    public void test_JsonDebugChallenge_Date_LocalDateTime_cannot() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(LocalDateTime.class, "2017/08/24 12:34:56");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("x LocalDateTime sea = (String) \"2017/08/24 12:34:56\"", disp.trim());
    }

    // -----------------------------------------------------
    //                                             LocalTime
    //                                             ---------
    public void test_JsonDebugChallenge_Date_LocalTime_basic() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(LocalTime.class, "12:34:56");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("o LocalTime sea = (LocalTime) \"12:34:56\"", disp.trim());
    }

    // ===================================================================================
    //                                                                      Classification
    //                                                                      ==============
    public void test_JsonDebugChallenge_Classification_basic() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(CDef.MemberStatus.class, "FML");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("o MemberStatus sea = (MemberStatus) \"FML\"", disp.trim());
    }

    public void test_JsonDebugChallenge_Classification_cannot() throws Exception {
        // ## Arrange ##
        JsonDebugChallenge challenge = createChallenge(CDef.MemberStatus.class, "XYZ");

        // ## Act ##
        String disp = challenge.toChallengeDisp();

        // ## Assert ##
        log(disp);
        assertEquals("x MemberStatus sea = (String) \"XYZ\"", disp.trim());
    }

    // ===================================================================================
    //                                                                         Test Helper
    //                                                                         ===========
    protected JsonDebugChallenge createChallenge(Class<?> propertyType, Object mappedValue) {
        return new JsonDebugChallenge(jsonManager, "sea", propertyType, mappedValue, 0);
    }
}
