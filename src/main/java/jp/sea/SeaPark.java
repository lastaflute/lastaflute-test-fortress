package jp.sea;

import jp.Resort;

/**
 * @author jflute
 */
public class SeaPark implements Resort {

    public static final String STATIC_DEF = "defined static";

    public static String callStatic() {
        return "called static";
    }
}
