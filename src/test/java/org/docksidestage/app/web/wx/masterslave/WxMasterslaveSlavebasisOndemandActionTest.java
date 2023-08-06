package org.docksidestage.app.web.wx.masterslave;

import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 */
public class WxMasterslaveSlavebasisOndemandActionTest extends UnitFortressBasicTestCase {

    public void test_index_basic() {
        // ## Arrange ##
        WxMasterslaveSlavebasisOndemandAction action = new WxMasterslaveSlavebasisOndemandAction();
        inject(action);

        // ## Act ##
        action.index();

        // ## Assert ##
        // visual check
    }
}
