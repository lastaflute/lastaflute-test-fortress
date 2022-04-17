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
        // TODO you #change_it set your common default rule here
        // e.g. When you want to convert the field naming from CAMEL to LOWER_SNAKE.
        // FlSelectedMappingPolicy selectedMappingPolicy = new LaSelectedMappingPolicy().fieldNaming(FormFieldNaming.CAMEL_TO_LOWER_SNAKE);
        // JsonMappingOption jsonMappingOption = new JsonMappingOption().asFieldNaming(JsonFieldNaming.CAMEL_TO_LOWER_SNAKE);
        // rule.sendQueryBy(new LaQuerySender(selectedMappingPolicy));
        // rule.sendBodyBy(new LaJsonSender(requestManager, jsonMappingOption)); or rule.sendBodyBy(new LaFormSender(selectedMappingPolicy));
        // rule.receiveBodyBy(new LaJsonReceiver(requestManager, jsonMappingOption));
        throw new IllegalStateException("set your common default rule here.");
    }

    @Override
    protected String getUrlBase() {
        // TODO you #change_it set base URL for the remote api here
        throw new IllegalStateException("set url base here.");
    }
}
