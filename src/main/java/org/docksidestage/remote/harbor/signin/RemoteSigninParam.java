package org.docksidestage.remote.harbor.signin;

import org.lastaflute.web.validation.Required;

/**
 * @author s.tadokoro
 * @author jflute
 */
public class RemoteSigninParam {

    @Required
    public String account;
    @Required
    public String password;
}