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
package org.docksidestage.remote.fortress.wx.validator.groups.response.hangar;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /wx/validator/groups/response/hangar.
 * @author FreeGen
 */
public class RemoteWxValidatorGroupsResponseHangarReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of docksideOnly. */
    @Required
    public String docksideOnly;

    /** The property of hangarOnly. */
    @Required
    public String hangarOnly;

    /** The property of bothNamed. */
    @Required
    public String bothNamed;

    /** The property of withDefault. */
    @Required
    public String withDefault;

    /** The property of noSpecified. */
    @Required
    public String noSpecified;

    /** The property of defaultOnly. */
    @Required
    public String defaultOnly;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
