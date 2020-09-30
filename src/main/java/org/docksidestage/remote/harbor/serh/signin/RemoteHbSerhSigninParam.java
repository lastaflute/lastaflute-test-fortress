package org.docksidestage.remote.harbor.serh.signin;

import javax.validation.Valid;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class RemoteHbSerhSigninParam {

    @Required
    public String account;
    @Required
    public String password;

    @Valid
    public TestingFormPropHasToPart testingFormPropHasTo;

    public static class TestingFormPropHasToPart {

        @Required
        public String hangar;

        @Override
        public String toString() {
            return Lato.string(this);
        }
    }

    @Valid
    public TestingFormPropNoToPart testingFormPropNoTo;

    public static class TestingFormPropNoToPart {

        @Required
        public String showbase;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}