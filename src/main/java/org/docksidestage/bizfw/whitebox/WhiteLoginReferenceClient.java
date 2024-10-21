package org.docksidestage.bizfw.whitebox;

import javax.annotation.PostConstruct;

import org.docksidestage.app.web.base.login.FortressLoginAssist;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.web.login.PrimaryLoginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class WhiteLoginReferenceClient {

    private static final Logger logger = LoggerFactory.getLogger(WhiteLoginReferenceClient.class);

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

        FortressLoginAssist loginAssist = ContainerUtil.getComponent(FortressLoginAssist.class);
        logger.debug("  getComponent(loginAssist): " + loginAssist);

        // recommended way
        PrimaryLoginManager loginManager = ContainerUtil.getComponent(PrimaryLoginManager.class);
        logger.debug("  getComponent(loginManager): " + loginManager);
    }
}
