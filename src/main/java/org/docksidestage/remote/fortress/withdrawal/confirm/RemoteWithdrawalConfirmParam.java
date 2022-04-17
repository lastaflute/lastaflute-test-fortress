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
package org.docksidestage.remote.fortress.withdrawal.confirm;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of GET /withdrawal/confirm.
 * @author FreeGen
 */
public class RemoteWithdrawalConfirmParam {

    /** The property of selectedReason. (enumValue=[SIT, PRD, FRT, OTH]) ( * `SIT` - Sit, SIT. * `PRD` - Prd, PRD. * `FRT` - Frt, FRT. * `OTH` - Oth, OTH. :: fromCls(CDef$WithdrawalReason)) (NullAllowed) */
    public String selectedReason;

    /** The property of reasonInput. (NullAllowed) */
    public String reasonInput;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}