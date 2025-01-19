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
package org.docksidestage.app.job.rabbit.base;

import java.util.Map;

import javax.annotation.Resource;

import org.docksidestage.bizfw.rabbitmq.RabbitJobResource;
import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.engine.RealJsonEngine;
import org.lastaflute.job.LaJob;
import org.lastaflute.job.LaJobRuntime;

/**
 * @param <MESSAGE_BODY> メッセージ文字列をJSONとしてparseしたときの受け取りクラス
 * @author jflute
 */
public abstract class RabbitJobBase<MESSAGE_BODY> implements LaJob { // #rabbit

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private JsonManager jsonManager;

    // ===================================================================================
    //                                                                             Job Run
    //                                                                             =======
    @Override
    public void run(LaJobRuntime runtime) {
        RabbitJobResource jobResource = extractJobResource(runtime);
        MESSAGE_BODY messageBody = prepareMessageBody(jobResource);
        doRun(jobResource, messageBody);
    }

    // -----------------------------------------------------
    //                                          Job Resource
    //                                          ------------
    protected RabbitJobResource extractJobResource(LaJobRuntime runtime) {
        Map<String, Object> parameterMap = runtime.getParameterMap();
        return (RabbitJobResource) parameterMap.get(RabbitJobResource.JOB_PARAMETER_KEY);
    }

    // -----------------------------------------------------
    //                                          Message Body
    //                                          ------------
    protected MESSAGE_BODY prepareMessageBody(RabbitJobResource jobResource) {
        String messageText = jobResource.getMessageText();

        // #genba_fitting 本当は RuledJsonEngineKeeper から作り済みのEngineインスタンスを取得する方が良い by jflute (2025/01/17)
        // (このままだとメッセージを受け取るたびに毎回JsonEngineをnewしてしまう)
        JsonEngineResource engineResource = new JsonEngineResource();
        setupRabbitJsonRule(engineResource);
        RealJsonEngine ruledEngine = jsonManager.newRuledEngine(engineResource);

        Class<MESSAGE_BODY> messageJsonType = getMessageJsonType();
        return ruledEngine.fromJson(messageText, messageJsonType);
    }

    protected void setupRabbitJsonRule(JsonEngineResource engineResource) {
        // #genba_fitting ここで現場のJsonルールを設定する by jflute (2025/01/17)
        JsonMappingOption mappingOption = new JsonMappingOption();
        engineResource.acceptMappingOption(mappingOption);
    }

    // -----------------------------------------------------
    //                                      Concrete Process
    //                                      ----------------
    protected abstract void doRun(RabbitJobResource jobResource, MESSAGE_BODY messageBody);

    protected abstract Class<MESSAGE_BODY> getMessageJsonType();
}
