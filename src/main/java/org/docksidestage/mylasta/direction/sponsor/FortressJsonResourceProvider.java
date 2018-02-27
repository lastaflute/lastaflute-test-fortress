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
package org.docksidestage.mylasta.direction.sponsor;

import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.JsonResourceProvider;
import org.lastaflute.core.json.bind.JsonYourCollectionResource;

/**
 * @author jflute
 */
public class FortressJsonResourceProvider implements JsonResourceProvider {

    @Override
    public JsonMappingOption provideMappingOption() {
        return new JsonMappingOption().yourCollections(prepareYourCollections());
    }

    private List<JsonYourCollectionResource> prepareYourCollections() {
        return Arrays.asList(new JsonYourCollectionResource(ImmutableList.class, mutableList -> {
            return Lists.immutable.withAll(mutableList);
        }));
    }
}
