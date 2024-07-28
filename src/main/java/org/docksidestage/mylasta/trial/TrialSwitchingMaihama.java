package org.docksidestage.mylasta.trial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

/**
 * @author jflute
 */
public class TrialSwitchingMaihama {

    private static final Logger logger = LoggerFactory.getLogger(TrialSwitchingMaihama.class);

    @Resource
    private TrialSwitchedPark park;

    @PostConstruct
    public void showPark() {
        logger.debug("...Showing switching maihama: park={}", park.toString());
    }
}