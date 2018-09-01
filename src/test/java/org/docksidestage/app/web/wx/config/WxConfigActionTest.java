package org.docksidestage.app.web.wx.config;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
public class WxConfigActionTest extends UnitFortressBasicTestCase {

    public void test_index() {
        // ## Arrange ##
        WxConfigAction action = new WxConfigAction();
        inject(action);

        // ## Act ##
        JsonResponse<String> response = action.index();

        // ## Assert ##
        String json = response.getDirectJson().get();
        assertContains(json, "config: false");
        assertContains(json, "myprop: false");
    }
}
