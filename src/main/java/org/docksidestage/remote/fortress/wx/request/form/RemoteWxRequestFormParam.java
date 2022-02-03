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
package org.docksidestage.remote.fortress.wx.request.form;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as param for remote API of GET /wx/request/form/.
 * @author FreeGen
 */
public class RemoteWxRequestFormParam {

    /** The property of sea. (string immutable list e.g. [dockside,hangar,magiclamp]) (NullAllowed) */
    public String sea;

    /** The property of land. (NullAllowed) */
    public Integer land;

    /** The property of piari. (as date e.g. 2022-02-03) (NullAllowed) */
    public java.time.LocalDate piari;

    /** The property of bonvo. (NullAllowed) */
    public java.time.LocalDateTime bonvo;

    /** The property of dstore. (NullAllowed) */
    public String dstore;

    /** The property of amba. (NullAllowed) */
    public Boolean amba;

    /** The property of miraco. (string list e.g. [harborSide,riverSide]) (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<String> miraco;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
