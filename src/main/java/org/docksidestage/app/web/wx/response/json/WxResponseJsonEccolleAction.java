/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage.app.web.wx.response.json;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.view.DisplayAssist;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.validation.Required;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseJsonEccolleAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxResponseJsonEccolleAction.class);

    @Resource
    private DisplayAssist displayAssist;

    @Execute
    public JsonResponse<MyEcColleJsonBean> index() {
        ImmutableList<String> strList = Lists.immutable.of("sea", "land", "piari");
        if (logger.isDebugEnabled()) {
            strList.forEach(el -> logger.debug(el));
        }
        return asJson(new MyEcColleJsonBean(strList));
    }

    @Execute
    public JsonResponse<MyEcColleJsonBean> emptyerror() {
        ImmutableList<String> strList = Lists.immutable.empty();
        if (logger.isDebugEnabled()) {
            strList.forEach(el -> logger.debug(el));
        }
        return asJson(new MyEcColleJsonBean(strList));
    }

    public static class MyEcColleJsonBean {

        @Required
        public final ImmutableList<String> strList;

        public MyEcColleJsonBean(ImmutableList<String> strList) {
            this.strList = strList;
        }
    }
}
