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
package org.docksidestage.remote.fortress.wx.request.form.eccolle;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as return for remote API of GET /wx/request/form/eccolle.
 * @author FreeGen
 */
public class RemoteWxRequestFormEccolleReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of sea. (string immutable list e.g. [dockside,hangar,magiclamp]) (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<String> sea;

    /** The property of land. (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<String> land;

    /** The property of piari. (string util) (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<String> piari;

    /** The property of bonvo. (NullAllowed) */
    public org.eclipse.collections.api.list.ImmutableList<java.util.Map<String, Object>> bonvo;

    /** The property of dstore. (NullAllowed) */
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<MyDStorePart> dstore;

    /**
     * The part class of MyDStorePart.
     * @author FreeGen
     */
    public static class MyDStorePart {

        /** The property of walt. (NullAllowed) */
        public String walt;
    }

    /** The property of amba. (NullAllowed) */
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<MyAmbaPart> amba;

    /**
     * The part class of MyAmbaPart.
     * @author FreeGen
     */
    public static class MyAmbaPart {

        /** The property of chef. (NullAllowed) */
        public String chef;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
