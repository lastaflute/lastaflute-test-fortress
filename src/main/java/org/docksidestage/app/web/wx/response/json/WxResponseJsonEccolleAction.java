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
package org.docksidestage.app.web.wx.response.json;

import java.util.Map;

import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.map.immutable.ImmutableUnifiedMap;
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

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/response/json/eccolle/list
    @Execute
    public JsonResponse<MyEcColleImmutableListResult> list() {
        ImmutableList<String> list = Lists.immutable.of("sea", "land", "piari");
        if (logger.isDebugEnabled()) {
            list.forEach(el -> logger.debug(el));
        }
        return asJson(new MyEcColleImmutableListResult(list));
    }

    // http://localhost:8151/fortress/wx/response/json/eccolle/emptyerror
    @Execute
    public JsonResponse<MyEcColleImmutableListResult> listempty() {
        return asJson(new MyEcColleImmutableListResult(Lists.immutable.empty()));
    }

    // http://localhost:8151/fortress/wx/response/json/eccolle/map
    @Execute
    public JsonResponse<MyEcColleImmutableMapResult> map() {
        Map<String, String> map = DfCollectionUtil.newHashMap("sea", "mystic", "land", "oneman");
        return asJson(new MyEcColleImmutableMapResult(new ImmutableUnifiedMap<>(map)));
    }

    // ===================================================================================
    //                                                                        Result Class
    //                                                                        ============
    public static class MyEcColleImmutableListResult {

        @Required
        public final ImmutableList<String> list;

        public MyEcColleImmutableListResult(ImmutableList<String> list) {
            this.list = list;
        }
    }

    public static class MyEcColleImmutableMapResult {

        @Required
        public final ImmutableMap<String, String> map;

        public MyEcColleImmutableMapResult(ImmutableMap<String, String> map) {
            this.map = map;
        }
    }
}
