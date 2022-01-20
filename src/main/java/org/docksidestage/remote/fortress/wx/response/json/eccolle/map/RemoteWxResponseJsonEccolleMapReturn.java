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
package org.docksidestage.remote.fortress.wx.response.json.eccolle.map;

import javax.validation.constraints.NotNull;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as return for remote API of GET /wx/response/json/eccolle/map.
 * @author FreeGen
 */
public class RemoteWxResponseJsonEccolleMapReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of map. */
    @NotNull
    public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> map;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
