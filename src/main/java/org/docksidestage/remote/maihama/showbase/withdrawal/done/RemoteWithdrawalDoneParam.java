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
package org.docksidestage.remote.maihama.showbase.withdrawal.done;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of POST /withdrawal/done.
 * @author FreeGen
 */
public class RemoteWithdrawalDoneParam {

    /** The property of selectedReason. (enumValue=[SIT, PRD, FRT, OTH]) ( * `SIT` - Sit, SIT. * `PRD` - Prd, PRD. * `FRT` - Frt, FRT. * `OTH` - Oth, OTH. :: fromCls(CDef$WithdrawalReason)) (NullAllowed) */
    public org.docksidestage.dbflute.allcommon.CDef.WithdrawalReason selectedReason;

    /** The property of reasonInput. (NullAllowed) */
    public String reasonInput;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
