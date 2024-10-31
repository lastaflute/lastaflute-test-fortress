package org.docksidestage.bizfw.whitebox.fess;

import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.direction.FwAssistantDirector;
import org.lastaflute.web.LastaFilter;
import org.lastaflute.web.servlet.filter.LastaPrepareFilter;

/**
 * @author jflute
 */
public class WhiteFessSearchEngineClientTest extends UnitFortressBasicTestCase {

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
        WhiteFessSearchEngineClient client = getComponent(WhiteFessSearchEngineClient.class);

        // ## Assert ##
        log("client: {}", client);
    }
}
