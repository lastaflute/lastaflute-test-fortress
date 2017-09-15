package org.docksidestage.app.web.wx.config;

import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
public class WxConfigActionTest extends UnitFortressWebTestCase {

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
