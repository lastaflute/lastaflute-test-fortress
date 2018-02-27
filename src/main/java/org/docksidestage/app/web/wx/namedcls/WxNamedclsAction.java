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
package org.docksidestage.app.web.wx.namedcls;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.allcommon.CDef;
import org.docksidestage.mylasta.namedcls.LeonardoCDef;
import org.docksidestage.mylasta.namedcls.VinciCDef;
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
public class WxNamedclsAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxNamedclsAction.class);

    // http://localhost:8151/fortress/wx/namedcls/?status=FML&sea=FML&land=WDL
    @Execute
    public JsonResponse<MyNamedClsForm> index(MyNamedClsForm form) {
        validateApi(form, messages -> {});
        logger.debug("status: {}", form.status);
        logger.debug("sea: {}", form.sea);
        logger.debug("land: {}", form.land);
        return asJson(form); // for visual check
    }

    public static class MyNamedClsForm {

        /** DB cls */
        @Required
        public CDef.MemberStatus status;

        /** named */
        @Required
        public LeonardoCDef.DaSea sea;

        /** named */
        @Required
        public VinciCDef.DaLand land;
    }
}
