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

import org.docksidestage.mylasta.direction.FortressConfig;

/**
 * @author jflute
 */
public class ThymeleafConfigObject {

    protected final FortressConfig config; // not null

    public ThymeleafConfigObject(FortressConfig config) {
        this.config = config;
    }

    public String get(String propertyKey) { // not null or exception
        return config.get("thymeleaf." + propertyKey);
    }
}
