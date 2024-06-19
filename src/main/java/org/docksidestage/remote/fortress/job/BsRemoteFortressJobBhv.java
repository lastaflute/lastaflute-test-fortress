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
package org.docksidestage.remote.fortress.job;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.job.execute.RemoteJobExecuteParam;
import org.docksidestage.remote.fortress.job.execute.RemoteJobExecuteReturn;
import org.docksidestage.remote.fortress.job.execute.nowait.RemoteJobExecuteNowaitParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of job.
 * @author FreeGen
 */
public abstract class BsRemoteFortressJobBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressJobBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /job/execute/{jobCode}. (auto-generated method)<br>
     * <pre>
     * url: /job/execute/{jobCode}
     * httpMethod: POST
     * </pre>
     * @param jobCode The value of path variable for jobCode. (NotNull)
     * @param paramLambda The callback for RemoteJobExecuteParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteJobExecuteReturn requestExecute(String jobCode, Consumer<RemoteJobExecuteParam> paramLambda) {
        return doRequestExecute(jobCode, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /job/execute/{jobCode}. (auto-generated method)<br>
     * <pre>
     * url: /job/execute/{jobCode}
     * httpMethod: POST
     * </pre>
     * @param jobCode The value of path variable for jobCode. (NotNull)
     * @param paramLambda The callback for RemoteJobExecuteParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteJobExecuteReturn doRequestExecute(String jobCode, Consumer<RemoteJobExecuteParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteJobExecuteParam param = new RemoteJobExecuteParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteJobExecuteReturn.class, "/job/execute/{jobCode}", moreUrl(jobCode), param, rule -> {
            ruleOfExecuteJobCode(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /job/execute/{jobCode}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfExecuteJobCode(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /job/execute/nowait/{jobCode}. (auto-generated method)<br>
     * <pre>
     * url: /job/execute/nowait/{jobCode}
     * httpMethod: POST
     * </pre>
     * @param jobCode The value of path variable for jobCode. (NotNull)
     * @param paramLambda The callback for RemoteJobExecuteNowaitParam. (NotNull)
     */
    public void requestExecuteNowait(String jobCode, Consumer<RemoteJobExecuteNowaitParam> paramLambda) {
        doRequestExecuteNowait(jobCode, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /job/execute/nowait/{jobCode}. (auto-generated method)<br>
     * <pre>
     * url: /job/execute/nowait/{jobCode}
     * httpMethod: POST
     * </pre>
     * @param jobCode The value of path variable for jobCode. (NotNull)
     * @param paramLambda The callback for RemoteJobExecuteNowaitParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestExecuteNowait(String jobCode, Consumer<RemoteJobExecuteNowaitParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteJobExecuteNowaitParam param = new RemoteJobExecuteNowaitParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/job/execute/nowait/{jobCode}", moreUrl(jobCode), param, rule -> {
            ruleOfExecuteNowaitJobCode(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /job/execute/nowait/{jobCode}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfExecuteNowaitJobCode(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /job/stop/{jobCode}. (auto-generated method)<br>
     * <pre>
     * url: /job/stop/{jobCode}
     * httpMethod: GET
     * </pre>
     * @param jobCode The value of path variable for jobCode. (NotNull)
     */
    public void requestStop(String jobCode) {
        doRequestStop(jobCode, rule -> {});
    }

    /**
     * Request remote call to /job/stop/{jobCode}. (auto-generated method)<br>
     * <pre>
     * url: /job/stop/{jobCode}
     * httpMethod: GET
     * </pre>
     * @param jobCode The value of path variable for jobCode. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestStop(String jobCode, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/job/stop/{jobCode}", moreUrl(jobCode), noQuery(), rule -> {
            ruleOfStopJobCode(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /job/stop/{jobCode}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfStopJobCode(FlutyRemoteApiRule rule) {
    }
}
