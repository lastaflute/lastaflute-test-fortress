/*
 * Copyright 2015-2024 the original author or authors.
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
package org.docksidestage.app.web.lido.following;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.lido.following.FollowingSearchResult.FollowerMemberPart;
import org.docksidestage.app.web.lido.following.FollowingSearchResult.FollowingMemberPart;
import org.docksidestage.dbflute.exbhv.MemberFollowingBhv;
import org.docksidestage.dbflute.exentity.MemberFollowing;
import org.lastaflute.core.time.TimeManager;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class LidoFollowingAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private TimeManager timeManager;
    @Resource
    private MemberFollowingBhv followingBhv;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Execute
    public JsonResponse<FollowingSearchResult> list(FollowingSearchForm form) {
        // #for_now detarame by jflute (2021/03/11)
        List<FollowingMemberPart> followings = new ArrayList<>();
        List<FollowerMemberPart> followers = new ArrayList<>();
        FollowingSearchResult result = new FollowingSearchResult(followings, followers);
        return asJson(result);
    }

    @Execute
    public JsonResponse<FollowingUpdateResult> register(FollowingUpdateBody body) {
        validateApi(body, messages -> {});
        insertFollowing(body);
        int followingCount = selectFollowingCount(body);
        return asJson(new FollowingUpdateResult(followingCount));
    }

    @Execute
    public JsonResponse<FollowingUpdateResult> delete(FollowingUpdateBody body) {
        validateApi(body, messages -> {});
        deleteFollowing(body);
        int followingCount = selectFollowingCount(body);
        return asJson(new FollowingUpdateResult(followingCount));
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private int selectFollowingCount(FollowingUpdateBody body) {
        return followingBhv.selectCount(cb -> {
            cb.query().setMyMemberId_Equal(body.myMemberId);
        });
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    private void insertFollowing(FollowingUpdateBody body) {
        MemberFollowing following = new MemberFollowing();
        following.setMyMemberId(body.myMemberId);
        following.setYourMemberId(body.yourMemberId);
        following.setFollowDatetime(timeManager.currentDateTime());
        followingBhv.insert(following);
    }

    private void deleteFollowing(FollowingUpdateBody body) {
        MemberFollowing following = new MemberFollowing();
        following.setMyMemberId(body.myMemberId);
        following.setYourMemberId(body.yourMemberId);
        followingBhv.delete(following);
    }
}
