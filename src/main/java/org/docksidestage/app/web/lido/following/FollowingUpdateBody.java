package org.docksidestage.app.web.lido.following;

import javax.validation.constraints.Min;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class FollowingUpdateBody {

    @Required
    @Min(1)
    public Integer myMemberId;

    @Required
    @Min(1)
    public Integer yourMemberId;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
