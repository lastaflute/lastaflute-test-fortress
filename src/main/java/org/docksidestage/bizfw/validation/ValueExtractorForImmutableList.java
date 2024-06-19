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
package org.docksidestage.bizfw.validation;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.ValueExtractor;

import org.eclipse.collections.api.list.ImmutableList;

/**
 * Extension to avoid "sea[].land", to be "sea[0].land" since Hibernate Validator-6.0.x.
 * @author jflute
 */
public class ValueExtractorForImmutableList implements ValueExtractor<ImmutableList<@ExtractedValue ?>> {

    @Override
    public void extractValues(ImmutableList<@ExtractedValue ?> originalValue, ValueReceiver receiver) {
        for (int i = 0; i < originalValue.size(); i++) {
            receiver.indexedValue("<immutablelist element>", i, originalValue.get(i));
        }
    }
}
