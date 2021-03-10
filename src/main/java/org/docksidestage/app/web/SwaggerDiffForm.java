package org.docksidestage.app.web;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class SwaggerDiffForm {

    @Required
    public String leftPath;

    @Required
    public String rightPath;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
