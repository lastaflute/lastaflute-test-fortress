/*
 * Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.remote.fortress.lido.following.list;

import javax.validation.constraints.NotNull;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /lido/following/list.
 * @author FreeGen
 */
public class RemoteLidoFollowingListReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of followings. */
    @NotNull
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<FollowingMemberPart> followings;

    /**
     * The part class of FollowingMemberPart.
     * @author FreeGen
     */
    public static class FollowingMemberPart {

        /** The property of memberId. */
        @Required
        public Integer memberId;

        /** The property of memberName. */
        @Required
        public String memberName;

        /** The property of memberStatusCode. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) */
        @Required
        public String memberStatusCode;
    }

    /** The property of followers. */
    @NotNull
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<FollowerMemberPart> followers;

    /**
     * The part class of FollowerMemberPart.
     * @author FreeGen
     */
    public static class FollowerMemberPart {

        /** The property of memberId. */
        @Required
        public Integer memberId;

        /** The property of memberName. */
        @Required
        public String memberName;

        /** The property of memberStatusCode. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) */
        @Required
        public String memberStatusCode;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
