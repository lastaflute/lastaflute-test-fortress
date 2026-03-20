/*
 * Copyright 2015-2026 the original author or authors.
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

import com.google.gson.annotations.SerializedName;

/**
 * The bean class as return for remote API of POST /member/add/register.
 * @author FreeGen
 */
public class RemoteMemberAddRegisterReturn {

    /** The property of purchaseCount. (PURCHASE_COUNT: (Derived Referrer)) (NullAllowed) */
    @SerializedName("_purchaseCount")
    public Integer purchaseCount;

    /** The property of latestLoginDatetime. (LATEST_LOGIN_DATETIME: (Derived Referrer)) (NullAllowed) */
    @SerializedName("_latestLoginDatetime")
    public java.time.LocalDateTime latestLoginDatetime;

    /** The property of memberId. ((会員ID)MEMBER_ID: {PK, ID, NotNull, INT(10), FK to MEMBER_ADDRESS}) (NullAllowed) */
    @SerializedName("_memberId")
    public Integer memberId;

    /** The property of memberName. ((会員名称)MEMBER_NAME: {IX, NotNull, VARCHAR(100)}) (NullAllowed) */
    @SerializedName("_memberName")
    public String memberName;

    /** The property of memberAccount. ((会員アカウント)MEMBER_ACCOUNT: {UQ, NotNull, VARCHAR(50)}) (NullAllowed) */
    @SerializedName("_memberAccount")
    public String memberAccount;

    /** The property of memberStatusCode. ((会員ステータスコード)MEMBER_STATUS_CODE: {IX, NotNull, CHAR(3), FK to member_status, classification=MemberStatus}) (NullAllowed) */
    @SerializedName("_memberStatusCode")
    public String memberStatusCode;

    /** The property of formalizedDatetime. ((正式会員日時)FORMALIZED_DATETIME: {IX, DATETIME(19)}) (NullAllowed) */
    @SerializedName("_formalizedDatetime")
    public java.time.LocalDateTime formalizedDatetime;

    /** The property of birthdate. ((生年月日)BIRTHDATE: {DATE(10)}) (NullAllowed) */
    @SerializedName("_birthdate")
    public java.time.LocalDate birthdate;

    /** The property of registerDatetime. ((登録日時)REGISTER_DATETIME: {NotNull, DATETIME(19)}) (NullAllowed) */
    @SerializedName("_registerDatetime")
    public java.time.LocalDateTime registerDatetime;

    /** The property of registerUser. ((登録ユーザー)REGISTER_USER: {NotNull, VARCHAR(200)}) (NullAllowed) */
    @SerializedName("_registerUser")
    public String registerUser;

    /** The property of updateDatetime. ((更新日時)UPDATE_DATETIME: {NotNull, DATETIME(19)}) (NullAllowed) */
    @SerializedName("_updateDatetime")
    public java.time.LocalDateTime updateDatetime;

    /** The property of updateUser. ((更新ユーザー)UPDATE_USER: {NotNull, VARCHAR(200)}) (NullAllowed) */
    @SerializedName("_updateUser")
    public String updateUser;

    /** The property of versionNo. ((バージョン番号)VERSION_NO: {NotNull, BIGINT(19)}) (NullAllowed) */
    @SerializedName("_versionNo")
    public Long versionNo;

    /** The property of memberStatus. (NullAllowed) */
    @SerializedName("_memberStatus")
    public org.docksidestage.dbflute.allcommon.CDef.MemberStatus memberStatus;

    /** The property of memberAddressAsValid. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("_memberAddressAsValid")
    public MemberAddressPart memberAddressAsValid;

    /**
     * The part class of MemberAddressPart.
     * @author FreeGen
     */
    public static class MemberAddressPart {
    }

    /** The property of memberLoginAsLatest. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("_memberLoginAsLatest")
    public MemberLoginPart memberLoginAsLatest;

    /**
     * The part class of MemberLoginPart.
     * @author FreeGen
     */
    public static class MemberLoginPart {
    }

    /** The property of memberSecurityAsOne. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("_memberSecurityAsOne")
    public MemberSecurityPart memberSecurityAsOne;

    /**
     * The part class of MemberSecurityPart.
     * @author FreeGen
     */
    public static class MemberSecurityPart {
    }

    /** The property of memberServiceAsOne. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("_memberServiceAsOne")
    public MemberServicePart memberServiceAsOne;

    /**
     * The part class of MemberServicePart.
     * @author FreeGen
     */
    public static class MemberServicePart {
    }

    /** The property of memberWithdrawalAsOne. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("_memberWithdrawalAsOne")
    public MemberWithdrawalPart memberWithdrawalAsOne;

    /**
     * The part class of MemberWithdrawalPart.
     * @author FreeGen
     */
    public static class MemberWithdrawalPart {
    }

    /** The property of memberAddressList. ((会員住所情報)MEMBER_ADDRESS by MEMBER_ID, named 'memberAddressList') (NullAllowed) */
    @SerializedName("_memberAddressList")
    public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> memberAddressList;

    /** The property of memberFollowingByMyMemberIdList. ((会員フォローイング)MEMBER_FOLLOWING by MY_MEMBER_ID, named 'memberFollowingByMyMemberIdList') (NullAllowed) */
    @SerializedName("_memberFollowingByMyMemberIdList")
    public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> memberFollowingByMyMemberIdList;

    /** The property of memberFollowingByYourMemberIdList. ((会員フォローイング)MEMBER_FOLLOWING by YOUR_MEMBER_ID, named 'memberFollowingByYourMemberIdList') (NullAllowed) */
    @SerializedName("_memberFollowingByYourMemberIdList")
    public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> memberFollowingByYourMemberIdList;

    /** The property of memberLoginList. ((会員ログイン)MEMBER_LOGIN by MEMBER_ID, named 'memberLoginList') (NullAllowed) */
    @SerializedName("_memberLoginList")
    public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> memberLoginList;

    /** The property of purchaseList. ((購入)PURCHASE by MEMBER_ID, named 'purchaseList') (NullAllowed) */
    @SerializedName("_purchaseList")
    public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> purchaseList;

    /** The property of uniqueDrivenProperties. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("__uniqueDrivenProperties")
    public EntityUniqueDrivenPropertiesPart uniqueDrivenProperties;

    /**
     * The part class of EntityUniqueDrivenPropertiesPart.
     * @author FreeGen
     */
    public static class EntityUniqueDrivenPropertiesPart {
    }

    /** The property of modifiedProperties. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("__modifiedProperties")
    public EntityModifiedPropertiesPart modifiedProperties;

    /**
     * The part class of EntityModifiedPropertiesPart.
     * @author FreeGen
     */
    public static class EntityModifiedPropertiesPart {
    }

    /** The property of specifiedProperties. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("__specifiedProperties")
    public EntityModifiedPropertiesPart specifiedProperties;

    /** The property of derivedMap. (NullAllowed) */
    @jakarta.validation.Valid
    @SerializedName("__derivedMap")
    public EntityDerivedMapPart derivedMap;

    /**
     * The part class of EntityDerivedMapPart.
     * @author FreeGen
     */
    public static class EntityDerivedMapPart {
    }

    /** The property of undefinedClassificationSelectAllowed. (NullAllowed) */
    @SerializedName("_undefinedClassificationSelectAllowed")
    public Boolean undefinedClassificationSelectAllowed;

    /** The property of createdBySelect. (NullAllowed) */
    @SerializedName("__createdBySelect")
    public Boolean createdBySelect;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
