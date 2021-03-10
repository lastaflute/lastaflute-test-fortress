package org.docksidestage.app.web.lido.following;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class FollowingUpdateResult {

    @Required
    public final Integer followingCount;

    public FollowingUpdateResult(Integer followingCount) {
        this.followingCount = followingCount;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
