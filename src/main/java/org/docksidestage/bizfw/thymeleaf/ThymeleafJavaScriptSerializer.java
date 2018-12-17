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

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;

import org.dbflute.optional.OptionalThing;
import org.dbflute.util.DfReflectionUtil;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.engine.RealJsonEngine;
import org.lastaflute.web.ruts.wrapper.BeanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.standard.serializer.IStandardJavaScriptSerializer;

/**
 * @author jflute
 */
public class ThymeleafJavaScriptSerializer implements IStandardJavaScriptSerializer {

    private static final Logger logger = LoggerFactory.getLogger(ThymeleafJavaScriptSerializer.class);

    protected final RealJsonEngine engine;

    public ThymeleafJavaScriptSerializer(JsonManager jsonManager) {
        logger.debug("#fw_thymeleaf ...Initializing JavaScript serializer of Thymeleaf");
        engine = prepareJsonEngine(jsonManager);
    }

    private RealJsonEngine prepareJsonEngine(JsonManager jsonManager) {
        JsonMappingOption option = createJsonMappingOption();
        return jsonManager.newAnotherEngine(OptionalThing.of(option));
    }

    private JsonMappingOption createJsonMappingOption() {
        JsonMappingOption option = new JsonMappingOption();
        option.asNullToEmptyWriting(); // trial as example
        return option;
    }

    @Override
    public void serializeValue(Object object, Writer writer) {
        final Object realBean = resolveRealBean(object);
        String json = engine.toJson(realBean);
        logger.debug("#fw_thymeleaf ...Serializing as JSON: type={}, obj={}\n{}", object.getClass(), object, json);
        try {
            writer.write(json);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write the JSON on thymeleaf: " + object, e);
        }
    }

    private Object resolveRealBean(Object object) {
        final Object realBean;
        if (object instanceof BeanWrapper) { // basically here
            // no getter so forcedly for now
            Field beanField = DfReflectionUtil.getWholeField(BeanWrapper.class, "bean");
            realBean = DfReflectionUtil.getValueForcedly(beanField, object);
        } else {
            realBean = object;
        }
        return realBean;
    }
}
