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
package org.docksidestage.remote.fortress.wx.request.form.swagger;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as param for remote API of GET /wx/request/form/swagger/.
 * @author FreeGen
 */
public class RemoteWxRequestFormSwaggerParam {

    /** The property of seaPlain. (NullAllowed) */
    public String seaPlain;

    /** The property of seaRequired. */
    @Required
    public String seaRequired;

    /** The property of seaLength. (NullAllowed) */
    public String seaLength;

    /** The property of seaSize. (NullAllowed) */
    public String seaSize;

    /** The property of seaPatternKana. (NullAllowed) */
    public String seaPatternKana;

    /** The property of seaPatternPhone. (NullAllowed) */
    public String seaPatternPhone;

    /** The property of seaEmail. (NullAllowed) */
    public String seaEmail;

    /** The property of seaStringCustomOne. (NullAllowed) */
    public String seaStringCustomOne;

    /** The property of seaStringCustomTwo. (NullAllowed) */
    public String seaStringCustomTwo;

    /** The property of seaWhole. */
    @Required
    public String seaWhole;

    /** The property of landPlain. (NullAllowed) */
    public Integer landPlain;

    /** The property of landRequired. */
    @Required
    public Integer landRequired;

    /** The property of landMinMax. (NullAllowed) */
    public Integer landMinMax;

    /** The property of landIntegerCustomOne. (NullAllowed) */
    public String landIntegerCustomOne;

    /** The property of landIntegerCustomTwo. (NullAllowed) */
    public String landIntegerCustomTwo;

    /** The property of landWhole. */
    @Required
    public Integer landWhole;

    /** The property of piari. */
    @Required
    public java.time.LocalDate piari;

    /** The property of bonvo. (NullAllowed) */
    public java.time.LocalDateTime bonvo;

    /** The property of dstore. (NullAllowed) */
    public Boolean dstore;

    /** The property of amba. (enumValue=[FML, WDL, PRV]) ( * `FML` - Formalized. * `WDL` - Withdrawal. * `PRV` - Provisional. :: fromCls(CDef$MemberStatus)) (NullAllowed) */
    public String amba;

    /** The property of miraco. (NullAllowed) */
    public String miraco;

    /** The property of white. (NullAllowed) */
    public String white;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
