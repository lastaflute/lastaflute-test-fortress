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
import org.lastaflute.core.magic.ThreadCacheContext;
import org.lastaflute.web.login.UserBean;

/**
 * @author jflute
 */
public class CrossLoginBridge {

    private static final String KEY_CROSS_LOGIN = "app:crossLogin";

    public void transfer(String appType, OptionalThing<? extends UserBean<? extends Number>> userBean, String userType) { // called by e.g. hookBefore()
        OptionalThing<? extends Number> userId = userBean.map(bean -> bean.getUserId());
        CrossLoginBean crossLoginBean = new CrossLoginBean(appType, userId, userType);
        ThreadCacheContext.setObject(KEY_CROSS_LOGIN, crossLoginBean);
    }

    public OptionalThing<CrossLoginBean> retrieve() { // called by e.g. RemoteApi's default rule
        String key = KEY_CROSS_LOGIN;
        CrossLoginBean crossLoginBean = ThreadCacheContext.getObject(key);
        return OptionalThing.ofNullable(crossLoginBean, () -> {
            throw new IllegalStateException("Not found the cross login bean: " + key);
        });
    }
}
