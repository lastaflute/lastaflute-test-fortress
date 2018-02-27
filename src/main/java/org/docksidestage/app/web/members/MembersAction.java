/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.app.web.members;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.app.web.base.view.DisplayAssist;
import org.docksidestage.app.web.member.MemberListAction;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exentity.Member;
import org.lastaflute.core.time.TimeManager;
import org.lastaflute.core.util.LaStringUtil;
import org.lastaflute.web.Execute;
import org.lastaflute.web.response.HtmlResponse;

/**
 * @author jflute
 */
public class MembersAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private TimeManager timeManager;
    @Resource
    private MemberBhv memberBhv;
    @Resource
    private PagingAssist pagingAssist;
    @Resource
    private DisplayAssist displayAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Execute // GET /members/{memberId}
    public HtmlResponse get$index(OptionalThing<Integer> memberId) {
        if (memberId.isPresent()) {
            Member member = selectMember(memberId.get());
            return asHtml(path_Member_MemberEditHtml).useForm(MembersEditForm.class, op -> op.setup(form -> {
                mappingToEditForm(member, form);
            }));
        } else {
            return asListHtml(new MembersSearchForm());
        }
    }

    @Execute // GET /members/search/?pageNumber=3&memberName=S
    public HtmlResponse get$search(MembersSearchForm form) { // from list
        validate(form, messages -> {}, () -> {
            return asHtml(path_Member_MemberListHtml);
        });
        return asListHtml(form);
    }

    protected HtmlResponse asListHtml(MembersSearchForm form) {
        PagingResultBean<Member> page = selectMemberPage(form);
        PagingResultBean<MembersSearchRowBean> beans = page.mappingList(member -> {
            return mappingToRowBean(member);
        });
        return asHtml(path_Member_MemberListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    @Execute // POST /members/
    public HtmlResponse post$index(MembersRegisterForm form) { // from list
        validate(form, messages -> {}, () -> {
            return asHtml(path_Member_MemberAddHtml);
        });
        Member member = insertMember(form);
        return redirectById(MembersAction.class, member.getMemberId());
    }

    @Execute // PUT /members/{memberId}
    public HtmlResponse put$index(Integer memberId, MembersEditForm form) { // from edit
        validate(form, messages -> {}, () -> {
            return asHtml(path_Member_MemberEditHtml);
        });
        Member member = updateMember(form);
        return redirectById(MembersAction.class, member.getMemberId());
    }

    @Execute // DELETE /members/{memberId}
    public HtmlResponse delete$index(Integer memberId, MembersEditForm form) { // from edit
        validate(form, messages -> {}, () -> {
            return asHtml(path_Member_MemberEditHtml);
        });
        withdrawMember(form);
        return redirect(MemberListAction.class);
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private Member selectMember(Integer memberId) {
        return memberBhv.selectEntity(cb -> {
            cb.specify().derivedMemberLogin().max(loginCB -> {
                loginCB.specify().columnLoginDatetime();
            }, Member.ALIAS_latestLoginDatetime);
            cb.query().setMemberId_Equal(memberId);
            cb.query().setMemberStatusCode_InScope_ServiceAvailable();
        }).get(); // exclusive control if not found
    }

    private PagingResultBean<Member> selectMemberPage(MembersSearchForm form) {
        return memberBhv.selectPage(cb -> {
            cb.setupSelect_MemberStatus();
            cb.specify().derivedPurchase().count(purchaseCB -> {
                purchaseCB.specify().columnPurchaseId();
            }, Member.ALIAS_purchaseCount);
            if (LaStringUtil.isNotEmpty(form.memberName)) {
                cb.query().setMemberName_LikeSearch(form.memberName, op -> op.likeContain());
            }
            if (LaStringUtil.isNotEmpty(form.purchaseProductName) || form.unpaid) {
                cb.query().existsPurchase(purchaseCB -> {
                    if (LaStringUtil.isNotEmpty(form.purchaseProductName)) {
                        purchaseCB.query().queryProduct().setProductName_LikeSearch(form.purchaseProductName, op -> op.likeContain());
                    }
                    if (form.unpaid) {
                        purchaseCB.query().setPaymentCompleteFlg_Equal_False();
                    }
                });
            }
            if (form.memberStatus != null) {
                cb.query().setMemberStatusCode_Equal_AsMemberStatus(form.memberStatus);
            }
            OptionalThing<LocalDateTime> fromDate = displayAssist.toDateTime(form.formalizedFrom);
            OptionalThing<LocalDateTime> toDate = displayAssist.toDateTime(form.formalizedTo);
            if (fromDate.isPresent() || toDate.isPresent()) {
                cb.query().setFormalizedDatetime_FromTo(fromDate.orElse(null), toDate.orElse(null), op -> {
                    op.compareAsDate().allowOneSide();
                });
            }
            cb.query().addOrderBy_UpdateDatetime_Desc();
            cb.query().addOrderBy_MemberId_Asc();
            cb.paging(4, form.pageNumber);
        });
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    private Member insertMember(MembersRegisterForm form) {
        Member member = new Member();
        member.setMemberName(form.memberName);
        member.setMemberAccount(form.memberAccount);
        member.setBirthdate(form.birthdate);
        member.setMemberStatusCodeAsMemberStatus(form.memberStatus);
        if (member.isMemberStatusCodeFormalized()) {
            member.setFormalizedDatetime(timeManager.currentDateTime());
        }
        memberBhv.insert(member);
        return member;
    }

    private Member updateMember(MembersEditForm form) {
        Member member = new Member();
        member.setMemberId(form.memberId);
        member.setMemberName(form.memberName);
        member.setBirthdate(displayAssist.toDate(form.birthdate).orElse(null));
        member.setMemberStatusCodeAsMemberStatus(form.memberStatus);
        member.setMemberAccount(form.memberAccount);
        if (member.isMemberStatusCodeFormalized()) {
            if (form.previousStatus.isShortOfFormalized()) {
                member.setFormalizedDatetime(timeManager.currentDateTime());
            }
        } else if (member.isMemberStatusCode_ShortOfFormalized()) {
            member.setFormalizedDatetime(null);
        }
        member.setVersionNo(form.versionNo);
        memberBhv.update(member);
        return member;
    }

    private void withdrawMember(MembersEditForm form) {
        Member member = new Member();
        member.setMemberId(form.memberId);
        member.setMemberStatusCode_Withdrawal();
        member.setVersionNo(form.versionNo);
        memberBhv.update(member);
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private void mappingToEditForm(Member member, MembersEditForm form) {
        form.memberId = member.getMemberId();
        form.memberName = member.getMemberName();
        form.memberAccount = member.getMemberAccount();
        form.memberStatus = member.getMemberStatusCodeAsMemberStatus();
        form.birthdate = member.getBirthdate();
        form.latestLoginDatetime = member.getLatestLoginDatetime();
        form.updateDatetime = member.getUpdateDatetime();
        form.previousStatus = member.getMemberStatusCodeAsMemberStatus(); // to determine new formalized member
        form.versionNo = member.getVersionNo();
    }

    private MembersSearchRowBean mappingToRowBean(Member member) {
        MembersSearchRowBean bean = new MembersSearchRowBean();
        bean.memberId = member.getMemberId();
        bean.memberName = member.getMemberName();
        member.getMemberStatus().alwaysPresent(status -> {
            bean.memberStatusName = status.getMemberStatusName();
        });
        bean.formalizedDate = displayAssist.toDate(member.getFormalizedDatetime()).orElse(null);
        bean.updateDatetime = displayAssist.toStringDateTime(member.getUpdateDatetime()).get();
        bean.withdrawalMember = member.isMemberStatusCodeWithdrawal();
        bean.purchaseCount = member.getPurchaseCount();
        return bean;
    }
}
