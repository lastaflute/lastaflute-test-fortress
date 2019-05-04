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
import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.JsonMappingOption.JsonFieldNaming;
import org.lastaflute.core.json.bind.JsonYourCollectionResource;
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
                .yourCollections(prepareYourCollections()) // Eclipse Collections
                .asFieldNaming(JsonFieldNaming.CAMEL_TO_LOWER_SNAKE) // SNAKE_CASE
                .formatLocalDateBy(DateTimeFormatter.ofPattern("yyyy%MM%dd"))
                .serializeBooleanBy(boo -> boo ? "Y" : "N")
                .filterSimpleTextReading(text -> text.replace("sea", "mystic"));
        resource.acceptMappingOption(option);
        return jsonManager.newRuledEngine(resource);
    }

    private List<JsonYourCollectionResource> prepareYourCollections() {
        return Arrays.asList(new JsonYourCollectionResource(ImmutableList.class, mutableList -> {
            return Lists.immutable.withAll(mutableList);
        }));
    }

    // ===================================================================================
    //                                                                            Provider
    //                                                                            ========
    public RealJsonEngine provideTrialJsonEngine() {
        return trialJsonEngine;
    }
}
