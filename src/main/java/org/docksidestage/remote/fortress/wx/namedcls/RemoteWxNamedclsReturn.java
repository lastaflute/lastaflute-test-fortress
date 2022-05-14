/*
 * Copyright 2015-2022 the original author or authors.
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
package org.docksidestage.remote.fortress.wx.namedcls;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /wx/namedcls/.
 * @author FreeGen
 */
public class RemoteWxNamedclsReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of status. (enumValue=[FML, WDL, PRV]) (DB cls: * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) */
    @Required
    public String status;

    /** The property of sea. (enumValue=[FML, WDL, PRV]) (named: * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(LeonardoCDef$DaSea)) */
    @Required
    public String sea;

    /** The property of land. (enumValue=[FML, WDL]) (named: * `FML` - OneMan, ShowBase. * `WDL` - MiniO, Orlean. :: fromCls(VinciCDef$DaLand)) */
    @Required
    public String land;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
