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

import org.docksidestage.mylasta.direction.FortressConfig;
import org.lastaflute.core.direction.PropertyFilter;

/**
 * @author jflute
 */
public class FortressPropertyFilter implements PropertyFilter {

    @Override
    public String filter(String propertyKey, String propertyValue) {
        if (propertyKey.equals(FortressConfig.JDBC_URL) && "$$env$$".equals(propertyValue)) {
            // e.g. [app]_env.properties
            //  jdbc.url = $$env$$
            String envKey = "JDBC_URL";
            String envValue = System.getenv(envKey);
            if (envValue == null) {
                throw new IllegalStateException("Not found the environment value: " + envKey);
            }
            return envValue;
        }
        return propertyValue;
    }
}
