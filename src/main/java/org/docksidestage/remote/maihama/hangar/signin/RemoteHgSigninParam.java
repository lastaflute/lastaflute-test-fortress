package org.docksidestage.remote.maihama.hangar.signin;

import org.lastaflute.web.validation.Required;

/**
 * @author s.tadokoro
 * @author jflute
 */
public class RemoteHgSigninParam {

    @Required
    public String account;
    @Required
    public String password;
}