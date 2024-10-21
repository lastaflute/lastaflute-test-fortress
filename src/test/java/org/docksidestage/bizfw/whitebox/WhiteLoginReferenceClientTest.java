package org.docksidestage.bizfw.whitebox;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.direction.FwAssistantDirector;
import org.lastaflute.web.LastaFilter;
import org.lastaflute.web.servlet.filter.LastaPrepareFilter;

/**
 * @author jflute
 */
public class WhiteLoginReferenceClientTest extends UnitFortressBasicTestCase {

    @Override
    protected String prepareConfigFile() {
        return "wx_fess_app.xml";
    }

    @Override
    protected LastaFilter xcreateLastaFilter() {
        return new LastaFilter() {
            @Override
            protected LastaPrepareFilter createLastaPrepareFilter() {
                return new LastaPrepareFilter() {
                    @Override
                    protected void hookCurtainBefore(FwAssistantDirector assistantDirector) {
                        // to suppress curtain before hook (using LoginManager)
                    }
                };
            }
        };
    }

    @Override
    protected void initializeAssistantDirector() {
        // to suppress curtain before hook (using LoginManager)
    }

    public void test_getComponent_basic() {
        // ## Arrange ##

        // ## Act ##
        WhiteLoginReferenceClient client = getComponent(WhiteLoginReferenceClient.class);

        // ## Assert ##
        log("client: {}", client);
    }
}
