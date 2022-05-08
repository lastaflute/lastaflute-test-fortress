package jp.piari;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.lastaflute.core.security.PrimaryCipher;
import org.lastaflute.di.util.LdiArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class PiariPark implements Depart {

    private static final Logger logger = LoggerFactory.getLogger(PiariPark.class);

    public void addIntArray(int[] intAry) {
        logger.debug("intAry: " + LdiArrayUtil.toList(intAry));
    }

    public void addStringArray(String[] StrAry) {
        logger.debug("strAry: " + Arrays.asList(StrAry));
    }

    public void addList(List<String> strList) {
        logger.debug("strList: " + strList);
    }

    public void addMap(Map<String, String> strMap) {
        logger.debug("strMap: " + strMap);
    }

    public void addString(String str) {
        logger.debug("str: " + str);
    }

    public void addConfigProp(String configProp) {
        logger.debug("configProp: " + configProp);
    }

    public void addCipher(PrimaryCipher cipher) {
        logger.debug("cipher: " + cipher);
    }
}
