/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.remote.swagger.tricky.fieldcase.noconv;

import org.lastaflute.core.util.Lato;

/**
 * The bean class as return for remote API of GET /fieldcase/noconv/.
 * @author FreeGen
 */
public class RemoteFieldcaseNoconvReturn {

    /** The property of hangar_id. (NullAllowed) */
    public Integer hangar_id;

    /** The property of onemanName. (NullAllowed) */
    public String onemanName;

    /** The property of piari. (NullAllowed) */
    public String piari;

    /** The property of BONVO. (NullAllowed) */
    public String BONVO;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}