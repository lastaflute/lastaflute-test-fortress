package org.docksidestage.mylasta.time;

import javax.annotation.Resource;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.time.TimeManager;

/**
 * @author jflute
 */
public class FortressTimeManagerTest extends UnitFortressBasicTestCase {

    @Resource
    private TimeManager timeManager;

    public void test_component() {
        log(timeManager.currentDate());
        log(timeManager.toString());
        assertTrue(FortressTimeManager.class.isAssignableFrom(timeManager.getClass()));
    }
}
