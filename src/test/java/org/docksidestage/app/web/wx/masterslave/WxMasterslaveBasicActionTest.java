package org.docksidestage.app.web.wx.masterslave;

import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 */
public class WxMasterslaveBasicActionTest extends UnitFortressBasicTestCase {

    public void test_index_basic() {
        // ## Arrange ##
        WxMasterslaveBasicAction action = new WxMasterslaveBasicAction();
        inject(action); // expect no exception

        // ## Act ##
        // ## Assert ##
        action.index(); // expect no exception, visual check
    }
}
