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
package org.docksidestage.remote.maihama.showbase.wx.remogen.suffix.partonly;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /wx/remogen/suffix/partonly.
 * @author FreeGen
 */
public class RemoteWxRemogenSuffixPartonlyReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of resortName. */
    @Required
    public String resortName;

    /** The property of resortPark. (NullAllowed) */
    @javax.validation.Valid
    public ResortParkPart resortPark;

    /**
     * The part class of ResortParkPart.
     * @author FreeGen
     */
    public static class ResortParkPart {

        /** The property of parkName. */
        @Required
        public String parkName;

        /** The property of showStages. (NullAllowed) */
        @javax.validation.Valid
        public org.eclipse.collections.api.list.ImmutableList<ShowStagePart> showStages;

        /**
         * The part class of ShowStagePart.
         * @author FreeGen
         */
        public static class ShowStagePart {

            /** The property of stageName. */
            @Required
            public String stageName;
        }
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
