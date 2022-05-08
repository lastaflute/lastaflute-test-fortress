package org.docksidestage.app.web.wx.bizpkg;

import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 */
public class WxBizpkgOnionarcActionTest extends UnitFortressBasicTestCase {

    public void test_basic() {
        // ## Arrange ##
        WxBizpkgOnionarcAction action = new WxBizpkgOnionarcAction();
        inject(action);

        // ## Act ##
        // ## Assert ##
        action.index(); // expects no exception
    }
}
