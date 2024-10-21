package org.docksidestage.bizfw.whitebox;

import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 */
public class WhiteLoginReferenceClientTest extends UnitFortressBasicTestCase {

    @Override
    protected String prepareConfigFile() {
        return "wx_fess_app.xml";
    }

    public void test_getComponent_basic() {
        // ## Arrange ##

        // ## Act ##
        WhiteLoginReferenceClient client = getComponent(WhiteLoginReferenceClient.class);

        // ## Assert ##
        log("client: {}", client);
    }
}
