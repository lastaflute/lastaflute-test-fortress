package org.docksidestage.remote.harbor.lido.signin;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class RemoteHbLidoSigninParam {

    @Required
    public String account;
    @Required
    public String password;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}