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
package org.docksidestage.bizfw.json;

import java.time.format.DateTimeFormatter;

import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.engine.RealJsonEngine;

/**
 * @author jflute
 */
public class RuledJsonEngineKeeper {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final RealJsonEngine trialJsonEngine;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RuledJsonEngineKeeper(JsonManager jsonManager) {
        this.trialJsonEngine = createTrialRuledEngine(jsonManager);
    }

    // ===================================================================================
    //                                                                     Create Instance
    //                                                                     ===============
    protected RealJsonEngine createTrialRuledEngine(JsonManager jsonManager) {
        JsonEngineResource resource = new JsonEngineResource();
        JsonMappingOption option = new JsonMappingOption();
        option.asNullToEmptyWriting()
                .formatLocalDateBy(DateTimeFormatter.ofPattern("yyyy%MM%dd"))
                .serializeBooleanBy(boo -> boo ? "Y" : "N");
        resource.acceptMappingOption(option);
        return jsonManager.newRuledEngine(resource);
    }

    // ===================================================================================
    //                                                                            Provider
    //                                                                            ========
    public RealJsonEngine provideTrialJsonEngine() {
        return trialJsonEngine;
    }
}
