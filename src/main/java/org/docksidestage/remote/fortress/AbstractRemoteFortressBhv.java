/*
 * Copyright 2015-2022 the original author or authors.
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
package org.docksidestage.remote.fortress;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class of all remote API behaviors as RemoteApiFortress.
 * <p>
 * You can implement your original methods here.
 * This class remains when re-generating.
 * </p>
 * @author FreeGen
 * @author jflute
 */
public abstract class AbstractRemoteFortressBhv extends org.lastaflute.remoteapi.LastaRemoteBehavior {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public AbstractRemoteFortressBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                          Initialize
    //                                                                          ==========
    @Override
    protected void yourDefaultRule(FlutyRemoteApiRule rule) {
        throw new IllegalStateException("example so no implementation.");
    }

    @Override
    protected String getUrlBase() {
        throw new IllegalStateException("example so no implementation.");
    }
}
