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
package org.docksidestage.bizfw.crosslogin;

import org.dbflute.optional.OptionalThing;

/**
 * @author jflute
 */
public class CrossLoginBean {

    private final String appType;
    private final OptionalThing<Number> userId;
    private final String userType;

    public CrossLoginBean(String appType, OptionalThing<? extends Number> userId, String userType) {
        this.appType = appType;
        this.userId = resolveWildcard(userId);
        this.userType = userType;
    }

    private OptionalThing<Number> resolveWildcard(OptionalThing<? extends Number> userId) {
        @SuppressWarnings("unchecked")
        OptionalThing<Number> castUserId = (OptionalThing<Number>) userId;
        return castUserId;
    }

    public String getAppType() {
        return appType;
    }

    public OptionalThing<Number> getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }
}
