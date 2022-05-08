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
package org.docksidestage.remote.fortress.wx.remogen.body.list;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of POST /wx/remogen/body/list.
 * @author FreeGen
 */
public class RemoteWxRemogenBodyListParam {

    /** The property of sea. (NullAllowed) */
    public String sea;

    /** The property of land. (NullAllowed) */
    public Integer land;

    /** The property of piari. (NullAllowed) */
    public java.time.LocalDate piari;

    /** The property of bonvo. (NullAllowed) */
    public java.time.LocalDateTime bonvo;

    /** The property of dstore. (NullAllowed) */
    public String dstore;

    /** The property of iamBody. (NullAllowed) */
    public String iamBody;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
