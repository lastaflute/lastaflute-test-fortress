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
package org.docksidestage.bizfw.thymeleaf;

import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import org.lastaflute.core.json.JsonEngineResource;
import org.lastaflute.core.json.JsonManager;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.json.adapter.StringGsonAdaptable.TypeAdapterString;
import org.lastaflute.core.json.engine.GsonJsonEngine;
import org.lastaflute.core.json.engine.RealJsonEngine;
import org.lastaflute.web.ruts.wrapper.BeanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.standard.serializer.IStandardJavaScriptSerializer;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

/**
 * @author jflute
 */
public class ThymeleafJavaScriptSerializer implements IStandardJavaScriptSerializer {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(ThymeleafJavaScriptSerializer.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final RealJsonEngine engine;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public ThymeleafJavaScriptSerializer(JsonManager jsonManager) {
        logger.debug("#fw_thymeleaf ...Initializing JavaScript serializer of Thymeleaf");
        engine = prepareJsonEngine(jsonManager);
    }

    private RealJsonEngine prepareJsonEngine(JsonManager jsonManager) {
        JsonEngineResource resource = new JsonEngineResource();
        resource.acceptMappingOption(prepraeMappingOption(jsonManager));
        resource.useYourEngineCreator((builderSetupper, optionSetupper) -> {
            return createMyEngine(builderSetupper, optionSetupper);
        });
        return jsonManager.newRuledEngine(resource);
    }

    private JsonMappingOption prepraeMappingOption(JsonManager jsonManager) {
        JsonMappingOption mappingOption = new JsonMappingOption();
        jsonManager.pulloutControlMeta().getMappingControlMeta().ifPresent(existingMeta -> {
            mappingOption.acceptAnother(existingMeta); // inheriting example
        });
        mappingOption.formatLocalDateBy(DateTimeFormatter.ofPattern("yyyy/MM/dd")); // customization example
        return mappingOption;
    }

    private GsonJsonEngine createMyEngine(Consumer<GsonBuilder> builderSetupper, Consumer<JsonMappingOption> optionSetupper) {
        return new GsonJsonEngine(builderSetupper, optionSetupper) {
            @Override
            public TypeAdapterString newTypeAdapterString(JsonMappingOption gsonOption) {
                return createMyTypeAdapterString(gsonOption);
            }
        };
    }

    private TypeAdapterString createMyTypeAdapterString(JsonMappingOption gsonOption) {
        return new TypeAdapterString(gsonOption) {
            @Override
            public void write(JsonWriter out, String value) throws IOException {
                if (value == null) {
                    out.value("(nullnull)"); // customization example
                } else {
                    super.write(out, value);
                }
            }
        };
    }

    // ===================================================================================
    //                                                                           Serialize
    //                                                                           =========
    @Override
    public void serializeValue(Object object, Writer writer) {
        final Object realBean = resolveRealBean(object);
        String json = engine.toJson(realBean);
        logger.debug("#fw_thymeleaf ...Serializing as JSON: type={}, obj={}\n{}", realBean.getClass(), realBean, json);
        try {
            writer.write(json);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write the JSON on thymeleaf: " + object, e);
        }
    }

    private Object resolveRealBean(Object object) {
        final Object realBean;
        if (object instanceof BeanWrapper) { // basically here
            realBean = ((BeanWrapper) object).getBean();
        } else { // e.g. direct String, Integer
            realBean = object;
        }
        return realBean;
    }
}
