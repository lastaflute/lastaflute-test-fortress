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
package org.docksidestage.bizfw.thymeleaf;

import java.io.Writer;

import org.lastaflute.core.json.JsonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.standard.serializer.IStandardJavaScriptSerializer;

/**
 * @author jflute
 */
public class ThymeleafJavaScriptSerializer implements IStandardJavaScriptSerializer {

    private static final Logger logger = LoggerFactory.getLogger(ThymeleafJavaScriptSerializer.class);

    protected final JsonManager jsonManager;

    public ThymeleafJavaScriptSerializer(JsonManager jsonManager) {
        this.jsonManager = jsonManager;
        logger.debug("#fw_thymeleaf ...Initializing JavaScript serializer of Thymeleaf");
    }

    @Override
    public void serializeValue(Object object, Writer writer) {
        logger.debug("#fw_thymeleaf ...Serializing as JSON: type={}, obj={}", object.getClass(), object);
    }
}
