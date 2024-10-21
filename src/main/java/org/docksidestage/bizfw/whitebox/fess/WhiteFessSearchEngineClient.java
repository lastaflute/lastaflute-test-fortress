package org.docksidestage.bizfw.whitebox.fess;

import javax.annotation.PostConstruct;

import org.docksidestage.app.web.base.login.fess.TrialFessLoginAssist;
import org.lastaflute.core.util.ContainerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class WhiteFessSearchEngineClient {

    private static final Logger logger = LoggerFactory.getLogger(WhiteFessSearchEngineClient.class);

    @PostConstruct
    public void initialize() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // test of fess way (2024/10/21)
        // SearchEngineClient (rich component) refers to FessLoginAssist (quick component) in @PostConstruct method
        // but...why?
        //  lastaflute 2 and warm: ok
        //  lastaflute 2 and cool: fail
        //  lastaflute 1 and warm: ok
        //  lastaflute 1 and coll: ok
        //
        // however rich component should not refer to quick component as concrete type originally
        // to avoid hotdeploy break so use PrimaryLoginManager interface
        // _/_/_/_/_/_/_/_/_/_/
        logger.debug("#fess getComponent() test here:");

        TrialFessLoginAssist loginAssist = ContainerUtil.getComponent(TrialFessLoginAssist.class);
        logger.debug("  getComponent(loginAssist): " + loginAssist);

        // recommended way
        TrialFessLoginManager loginManager = ContainerUtil.getComponent(TrialFessLoginManager.class);
        logger.debug("  getComponent(loginManager): " + loginManager);
    }
}
