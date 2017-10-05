/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage.remote.maihama.showbase.root;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;

/**
 * RemoteMaihamaShowbaseBsRootBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsRootBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsRootBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Root.<br>
     * <pre>
     * url: /root/
     * httpMethod: POST
     * </pre>
     * @return return object. (NotNull)
     */
    public Object requestRoot() {
        return doRequestPost(new ParameterizedRef<Object>() {
        }.getType(), "/root/", noMoreUrl(), null, rule -> ruleOfRoot(rule));
    }

    /**
     * Rule of Root.<br>
     * <pre>
     * url: /root/
     * httpMethod: POST
     * </pre>
     * @param rule rule. (NotNull)
     */
    protected void ruleOfRoot(FlutyRemoteApiRule rule) {
    }
}
