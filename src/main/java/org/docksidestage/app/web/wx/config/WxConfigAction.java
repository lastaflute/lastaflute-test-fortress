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
package org.docksidestage.app.web.wx.config;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.direction.MyFortressProp;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxConfigAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxConfigAction.class);

    @Resource
    private FortressConfig fortressConfig;
    @Resource
    private MyFortressProp myFortressProp;

    // http://localhost:8151/fortress/wx/config/
    @Execute
    public JsonResponse<String> index() {
        logger.debug("fortressConfig: {}", fortressConfig.isProduction());
        logger.debug("myFortressProp: {}", myFortressProp.isProduction());
        String json = "{config: " + fortressConfig.isProduction() + ", myprop: " + myFortressProp.isProduction() + "}";
        return JsonResponse.asJsonDirectly(json);
    }
}
