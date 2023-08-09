package org.docksidestage.app.web.wx.masterslave;

import org.docksidestage.bizfw.masterslave.slavebasis.annostyle.BlockingSlaveUpdateHookFactory.NonSelectCommandButSlaveDBException;
import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 */
public class WxMasterslaveSlavebasisAnnotationActionTest extends UnitFortressBasicTestCase {

    @Override
    protected Class<?> getMockActionType() {
        return WxMasterslaveSlavebasisAnnotationAction.class;
    }

    public void test_annotated_basic() {
        // ## Arrange ##
        WxMasterslaveSlavebasisAnnotationAction action = new WxMasterslaveSlavebasisAnnotationAction();
        inject(action);

        // ## Act ##
        action.annotated();

        // ## Assert ##
        // visual check
    }

    public void test_none_basic() {
        // ## Arrange ##
        WxMasterslaveSlavebasisAnnotationAction action = new WxMasterslaveSlavebasisAnnotationAction();
        inject(action);

        // ## Act ##
        // ## Assert ##
        assertException(NonSelectCommandButSlaveDBException.class, () -> {
            action.none();
        });
    }
}
