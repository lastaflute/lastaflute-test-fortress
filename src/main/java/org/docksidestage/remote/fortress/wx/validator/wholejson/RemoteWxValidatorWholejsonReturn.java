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
package org.docksidestage.remote.fortress.wx.validator.wholejson;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of POST /wx/validator/wholejson.
 * @author FreeGen
 */
public class RemoteWxValidatorWholejsonReturn {

    /** The property of seaId. */
    @Required
    public Integer seaId;

    /** The property of landName. */
    @Required
    public String landName;

    /** The property of data. (NullAllowed) */
    public java.util.Map<String, Object> data;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
