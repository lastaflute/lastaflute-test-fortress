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
package org.docksidestage.whitebox.time;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.annotation.Resource;

import org.dbflute.system.DBFluteSystem;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.time.TimeManager;

/**
 * @author jflute
 */
public class WxTimeManagerTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private TimeManager timeManager;

    // ===================================================================================
    //                                                                       Basic Current
    //                                                                       =============
    public void test_current_basic() { // confirm log for now
        LocalDateTime system = DBFluteSystem.currentLocalDateTime();
        {
            LocalDateTime from = system.minusDays(2);
            LocalDateTime to = system.plusDays(2);
            LocalDate current = timeManager.currentDate();
            log(current);
            assertTrue(current.isAfter(from.toLocalDate()));
            assertTrue(current.isBefore(to.toLocalDate()));
        }
        {
            LocalDateTime from = system.minusSeconds(3);
            LocalDateTime to = system.plusSeconds(3);
            LocalDateTime current = timeManager.currentDateTime();
            log(current);
            assertTrue(current.isAfter(from));
            assertTrue(current.isBefore(to));
        }
        {
            LocalDateTime from = system.minusSeconds(3);
            LocalDateTime to = system.plusSeconds(3);
            Date current = timeManager.currentUtilDate();
            log(current);
            assertTrue(current.after(toUtilDate(from)));
            assertTrue(current.before(toUtilDate(to)));
        }
        {
            LocalDateTime from = system.minusSeconds(3);
            LocalDateTime to = system.plusSeconds(3);
            Timestamp current = timeManager.currentTimestamp();
            log(current);
            assertTrue(current.after(toTimestamp(from)));
            assertTrue(current.before(toTimestamp(to)));
        }
        LocalDateTime before = timeManager.currentDateTime();
        sleep(2000);
        LocalDateTime after = timeManager.currentDateTime();
        assertEquals(before, after); // transaction time
    }

    // ===================================================================================
    //                                                                      Switch Current
    //                                                                      ==============
    public void test_switchCurrentDate_basic() {
        // ## Arrange ##
        LocalDateTime before = timeManager.currentDateTime();
        sleep(2000);
        LocalDateTime after = timeManager.currentDateTime();
        assertEquals(before, after); // transaction time
        log("before: {}", before);
        log("after: {}", after);

        LocalDateTime fixed = toLocalDateTime("2017/06/21 12:34:56.789");
        switchCurrentDate(() -> fixed);

        // ## Act ##
        LocalDateTime current = timeManager.currentDateTime();

        // ## Assert ##
        log("fixed: {}, current: {}", fixed, current);
        assertEquals(fixed, current);
        assertEquals(fixed, DBFluteSystem.currentLocalDateTime());
        assertNotSame(before, current);
        assertNotSame(after, current);
    }
}
