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
package org.docksidestage.remote.fortress.members.index;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as param for remote API of PUT /members/{memberId}.
 * @author FreeGen
 */
public class RemoteMembersPutParam {

    /** The property of memberId. */
    @Required
    public Integer memberId;

    /** The property of memberName. */
    @Required
    public String memberName;

    /** The property of memberAccount. */
    @Required
    public String memberAccount;

    /** The property of birthdate. (NullAllowed) */
    public java.time.LocalDate birthdate;

    /** The property of typeFailureInteger. (NullAllowed) */
    public Integer typeFailureInteger;

    /** The property of memberStatus. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) */
    @Required
    public org.docksidestage.dbflute.allcommon.CDef.MemberStatus memberStatus;

    /** The property of latestLoginDatetime. (NullAllowed) */
    public java.time.LocalDateTime latestLoginDatetime;

    /** The property of updateDatetime. (NullAllowed) */
    public java.time.LocalDateTime updateDatetime;

    /** The property of previousStatus. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) */
    @Required
    public String previousStatus;

    /** The property of versionNo. */
    @Required
    public Long versionNo;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
