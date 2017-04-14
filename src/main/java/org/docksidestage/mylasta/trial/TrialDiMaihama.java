package org.docksidestage.mylasta.trial;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class TrialDiMaihama {

    private static final Logger logger = LoggerFactory.getLogger(TrialDiMaihama.class);
    public static final String STATIC_DEF = "defined static";

    protected List<Object> parkList;

    public List<Object> getParkList() {
        return parkList;
    }

    public void setParkList(List<Object> parkList) {
        logger.debug("parkList => {}", parkList);
        for (Object park : parkList) {
            if (park instanceof TrialDiSea) {
                logger.debug("  : sea -> {}", park);
            } else if (park instanceof TrialDiLand) {
                logger.debug("  : land -> {}", park);
            } else {
                throw new IllegalStateException("Unknown park: " + park);
            }
        }
        this.parkList = parkList;
    }

    public static String callStatic() {
        return "called static";
    }
}
