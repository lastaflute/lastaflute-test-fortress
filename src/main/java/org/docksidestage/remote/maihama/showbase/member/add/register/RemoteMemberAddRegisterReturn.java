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
package org.docksidestage.remote.maihama.showbase.member.add.register;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as return for remote API of POST /member/add/register.
 * @author FreeGen
 */
public class RemoteMemberAddRegisterReturn {

    /** The property of purchaseCount. (PURCHASE_COUNT: (Derived Referrer)) (NullAllowed) */
    public Integer purchaseCount;

    /** The property of latestLoginDatetime. (LATEST_LOGIN_DATETIME: (Derived Referrer)) (NullAllowed) */
    public java.time.LocalDateTime latestLoginDatetime;

    /** The property of memberId. ((会員ID)MEMBER_ID: {PK, ID, NotNull, INT(10), FK to MEMBER_ADDRESS}) (NullAllowed) */
    public Integer memberId;

    /** The property of memberName. ((会員名称)MEMBER_NAME: {IX, NotNull, VARCHAR(100)}) (NullAllowed) */
    public String memberName;

    /** The property of memberAccount. ((会員アカウント)MEMBER_ACCOUNT: {UQ, NotNull, VARCHAR(50)}) (NullAllowed) */
    public String memberAccount;

    /** The property of memberStatusCode. ((会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to member_status, classification=MemberStatus}) (NullAllowed) */
    public String memberStatusCode;

    /** The property of formalizedDatetime. ((正式会員日時)FORMALIZED_DATETIME: {IX, DATETIME(19)}) (NullAllowed) */
    public java.time.LocalDateTime formalizedDatetime;

    /** The property of birthdate. ((生年月日)BIRTHDATE: {DATE(10)}) (NullAllowed) */
    public java.time.LocalDate birthdate;

    /** The property of registerDatetime. ((登録日時)REGISTER_DATETIME: {NotNull, DATETIME(19)}) (NullAllowed) */
    public java.time.LocalDateTime registerDatetime;

    /** The property of registerUser. ((登録ユーザー)REGISTER_USER: {NotNull, VARCHAR(200)}) (NullAllowed) */
    public String registerUser;

    /** The property of updateDatetime. ((更新日時)UPDATE_DATETIME: {NotNull, DATETIME(19)}) (NullAllowed) */
    public java.time.LocalDateTime updateDatetime;

    /** The property of updateUser. ((更新ユーザー)UPDATE_USER: {NotNull, VARCHAR(200)}) (NullAllowed) */
    public String updateUser;

    /** The property of versionNo. ((バージョン番号)VERSION_NO: {NotNull, BIGINT(19)}) (NullAllowed) */
    public Long versionNo;

    /** The property of memberStatus. ((会員ステータス)MEMBER_STATUS by my MEMBER_STATUS_CODE, named 'memberStatus') (NullAllowed) */
    public org.docksidestage.dbflute.allcommon.CDef.MemberStatus memberStatus;

    /** The property of memberAddressAsValid. ((会員住所情報)MEMBER_ADDRESS by my MEMBER_ID, named 'memberAddressAsValid') (NullAllowed) */
    public Object memberAddressAsValid;

    /** The property of memberLoginAsLatest. ((会員ログイン)MEMBER_LOGIN by my MEMBER_ID, named 'memberLoginAsLatest') (NullAllowed) */
    public Object memberLoginAsLatest;

    /** The property of memberSecurityAsOne. ((会員セキュリティ)member_security by MEMBER_ID, named 'memberSecurityAsOne') (NullAllowed) */
    public Object memberSecurityAsOne;

    /** The property of memberServiceAsOne. ((会員サービス)member_service by MEMBER_ID, named 'memberServiceAsOne') (NullAllowed) */
    public Object memberServiceAsOne;

    /** The property of memberWithdrawalAsOne. ((会員退会情報)member_withdrawal by MEMBER_ID, named 'memberWithdrawalAsOne') (NullAllowed) */
    public Object memberWithdrawalAsOne;

    /** The property of memberAddressList. ((会員住所情報)MEMBER_ADDRESS by MEMBER_ID, named 'memberAddressList') (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<Object> memberAddressList;

    /** The property of memberFollowingByMyMemberIdList. ((会員フォローイング)MEMBER_FOLLOWING by MY_MEMBER_ID, named 'memberFollowingByMyMemberIdList') (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<Object> memberFollowingByMyMemberIdList;

    /** The property of memberFollowingByYourMemberIdList. ((会員フォローイング)MEMBER_FOLLOWING by YOUR_MEMBER_ID, named 'memberFollowingByYourMemberIdList') (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<Object> memberFollowingByYourMemberIdList;

    /** The property of memberLoginList. ((会員ログイン)MEMBER_LOGIN by MEMBER_ID, named 'memberLoginList') (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<Object> memberLoginList;

    /** The property of purchaseList. ((購入)PURCHASE by MEMBER_ID, named 'purchaseList') (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<Object> purchaseList;

    /** The property of uniqueDrivenProperties. (NullAllowed) */
    public Object uniqueDrivenProperties;

    /** The property of modifiedProperties. (NullAllowed) */
    public Object modifiedProperties;

    /** The property of specifiedProperties. (NullAllowed) */
    public Object specifiedProperties;

    /** The property of derivedMap. (NullAllowed) */
    public Object derivedMap;

    /** The property of undefinedClassificationSelectAllowed. (NullAllowed) */
    public Boolean undefinedClassificationSelectAllowed;

    /** The property of createdBySelect. (NullAllowed) */
    public Boolean createdBySelect;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
