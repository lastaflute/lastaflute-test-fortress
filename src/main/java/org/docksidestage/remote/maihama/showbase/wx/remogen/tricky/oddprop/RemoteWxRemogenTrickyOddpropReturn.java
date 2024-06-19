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
package org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.oddprop;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /wx/remogen/tricky/oddprop.
 * @author FreeGen
 */
public class RemoteWxRemogenTrickyOddpropReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of seaResult. */
    @Required
    public String seaResult;

    /** The property of landBeanCount. (NullAllowed) */
    public Integer landBeanCount;

    /** The property of normalEntry. (NullAllowed) */
    @javax.validation.Valid
    public PlainNormalEntryPart normalEntry;

    /**
     * The part class of PlainNormalEntryPart.
     * @author FreeGen
     */
    public static class PlainNormalEntryPart {
    }

    /** The property of beanEntry. (NullAllowed) */
    @javax.validation.Valid
    public ResultBeanEntryPart beanEntry;

    /**
     * The part class of ResultBeanEntryPart.
     * @author FreeGen
     */
    public static class ResultBeanEntryPart {
    }

    /** The property of plainGenericEntry. (NullAllowed) */
    @javax.validation.Valid
    public PlainGenericParameterPart plainGenericEntry;

    /**
     * The part class of PlainGenericParameterPart.
     * @author FreeGen
     */
    public static class PlainGenericParameterPart {
    }

    /** The property of genericEntry. (NullAllowed) */
    @javax.validation.Valid
    public ResultGenericParameterPart genericEntry;

    /**
     * The part class of ResultGenericParameterPart.
     * @author FreeGen
     */
    public static class ResultGenericParameterPart {
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
