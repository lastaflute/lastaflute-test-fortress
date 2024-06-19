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
package org.docksidestage.remote.maihama.showbase.wx.remogen.routing.land;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as return for remote API of GET /wx/remogen/routing/land/{first}/{second}.
 * @author FreeGen
 */
public class RemoteWxRemogenRoutingLandReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of method. (NullAllowed) */
    public String method;

    /** The property of first. (NullAllowed) */
    public java.util.Map<String, Object> first;

    /** The property of second. (NullAllowed) */
    public java.util.Map<String, Object> second;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
