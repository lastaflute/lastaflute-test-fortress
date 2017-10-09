/*
 * Copyright 2015-2017 the original author or authors.
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

    /** PURCHASE_COUNT: (Derived Referrer). (NullAllowed) */
    public Integer PurchaseCount;

    /** LATEST_LOGIN_DATETIME: (Derived Referrer). (NullAllowed) */
    public java.time.LocalDateTime LatestLoginDatetime;

    /** (会員ID)MEMBER_ID: {PK, ID, NotNull, INT(10), FK to MEMBER_ADDRESS}. (NullAllowed) */
    public Integer MemberId;

    /** (会員名称)MEMBER_NAME: {IX, NotNull, VARCHAR(100)}. (NullAllowed) */
    public String MemberName;

    /** (会員アカウント)MEMBER_ACCOUNT: {UQ, NotNull, VARCHAR(50)}. (NullAllowed) */
    public String MemberAccount;

    /** (会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to member_status, classification=MemberStatus}. (NullAllowed) */
    public String MemberStatusCode;

    /** (正式会員日時)FORMALIZED_DATETIME: {IX, DATETIME(19)}. (NullAllowed) */
    public java.time.LocalDateTime FormalizedDatetime;

    /** (生年月日)BIRTHDATE: {DATE(10)}. (NullAllowed) */
    public java.time.LocalDate Birthdate;

    /** (登録日時)REGISTER_DATETIME: {NotNull, DATETIME(19)}. (NullAllowed) */
    public java.time.LocalDateTime RegisterDatetime;

    /** (登録ユーザー)REGISTER_USER: {NotNull, VARCHAR(200)}. (NullAllowed) */
    public String RegisterUser;

    /** (更新日時)UPDATE_DATETIME: {NotNull, DATETIME(19)}. (NullAllowed) */
    public java.time.LocalDateTime UpdateDatetime;

    /** (更新ユーザー)UPDATE_USER: {NotNull, VARCHAR(200)}. (NullAllowed) */
    public String UpdateUser;

    /** (バージョン番号)VERSION_NO: {NotNull, BIGINT(19)}. (NullAllowed) */
    public Long VersionNo;

    /** (会員ステータス)MEMBER_STATUS by my MEMBER_STATUS_CODE, named 'memberStatus'. (NullAllowed) */
    public Object MemberStatus;

    /** (会員住所情報)MEMBER_ADDRESS by my MEMBER_ID, named 'memberAddressAsValid'. (NullAllowed) */
    public Object MemberAddressAsValid;

    /** (会員ログイン)MEMBER_LOGIN by my MEMBER_ID, named 'memberLoginAsLatest'. (NullAllowed) */
    public Object MemberLoginAsLatest;

    /** (会員セキュリティ)member_security by MEMBER_ID, named 'memberSecurityAsOne'. (NullAllowed) */
    public Object MemberSecurityAsOne;

    /** (会員サービス)member_service by MEMBER_ID, named 'memberServiceAsOne'. (NullAllowed) */
    public Object MemberServiceAsOne;

    /** (会員退会情報)member_withdrawal by MEMBER_ID, named 'memberWithdrawalAsOne'. (NullAllowed) */
    public Object MemberWithdrawalAsOne;

    /** (会員住所情報)MEMBER_ADDRESS by MEMBER_ID, named 'memberAddressList'. (NullAllowed) */
    public java.util.List<Object> MemberAddressList;

    /** (会員フォローイング)MEMBER_FOLLOWING by MY_MEMBER_ID, named 'memberFollowingByMyMemberIdList'. (NullAllowed) */
    public java.util.List<Object> MemberFollowingByMyMemberIdList;

    /** (会員フォローイング)MEMBER_FOLLOWING by YOUR_MEMBER_ID, named 'memberFollowingByYourMemberIdList'. (NullAllowed) */
    public java.util.List<Object> MemberFollowingByYourMemberIdList;

    /** (会員ログイン)MEMBER_LOGIN by MEMBER_ID, named 'memberLoginList'. (NullAllowed) */
    public java.util.List<Object> MemberLoginList;

    /** (購入)PURCHASE by MEMBER_ID, named 'purchaseList'. (NullAllowed) */
    public java.util.List<Object> PurchaseList;

    /** The property of __uniqueDrivenProperties. (NullAllowed) */
    public Object _uniqueDrivenProperties;

    /** The property of __modifiedProperties. (NullAllowed) */
    public Object _modifiedProperties;

    /** The property of __specifiedProperties. (NullAllowed) */
    public Object _specifiedProperties;

    /** The property of __derivedMap. (NullAllowed) */
    public Object _derivedMap;

    /** The property of _undefinedClassificationSelectAllowed. (NullAllowed) */
    public Boolean UndefinedClassificationSelectAllowed;

    /** The property of __createdBySelect. (NullAllowed) */
    public Boolean _createdBySelect;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
