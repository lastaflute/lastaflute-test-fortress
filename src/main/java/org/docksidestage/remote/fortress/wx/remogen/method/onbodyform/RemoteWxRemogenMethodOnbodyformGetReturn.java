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
package org.docksidestage.remote.fortress.wx.remogen.method.onbodyform;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as return for remote API of GET /wx/remogen/method/onbodyform.
 * @author FreeGen
 */
public class RemoteWxRemogenMethodOnbodyformGetReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of sea. (NullAllowed) */
    public String sea;

    /** The property of land. (NullAllowed) */
    public Integer land;

    /** The property of iamResult. (NullAllowed) */
    public String iamResult;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
