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
package org.docksidestage.remote.fortress.lido.following.list;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as param for remote API of GET /lido/following/list.
 * @author FreeGen
 */
public class RemoteLidoFollowingListParam {

    /** The property of limit. */
    @Required
    public Integer limit;

    /** The property of memberPrefix. (NullAllowed) */
    public String memberPrefix;

    /** The property of fromPurchasePrice. (NullAllowed) */
    public Integer fromPurchasePrice;

    /** The property of livesChiba. (NullAllowed) */
    public Boolean livesChiba;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
