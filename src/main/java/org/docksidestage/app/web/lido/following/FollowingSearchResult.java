package org.docksidestage.app.web.lido.following;

import java.util.List;

import javax.validation.Valid;

import org.docksidestage.dbflute.allcommon.CDef;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class FollowingSearchResult {

    @Required
    @Valid
    public final List<FollowingMemberPart> followings;

    public static class FollowingMemberPart {

        @Required
        public Integer memberId;
        @Required
        public String memberName;
        @Required
        public CDef.MemberStatus memberStatusCode;
    }

    @Required
    @Valid
    public final List<FollowerMemberPart> followers;

    public static class FollowerMemberPart {

        @Required
        public Integer memberId;
        @Required
        public String memberName;
        @Required
        public CDef.MemberStatus memberStatusCode;
    }

    public FollowingSearchResult(List<FollowingMemberPart> followings, List<FollowerMemberPart> followers) {
        this.followings = followings;
        this.followers = followers;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
