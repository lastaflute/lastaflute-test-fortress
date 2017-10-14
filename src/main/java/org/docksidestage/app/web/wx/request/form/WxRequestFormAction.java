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
package org.docksidestage.app.web.wx.request.form;

import java.util.List;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestFormAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxRequestFormAction.class);

    // [success]
    // http://localhost:8151/fortress/wx/request/form/eccolle?sea=mystic&sea=bigband
    // http://localhost:8151/fortress/wx/request/form/eccolle?piari=first&piari=second
    // http://localhost:8151/fortress/wx/request/form/eccolle?piari[0]=first&piari[1]=second
    //
    // [client error]
    // http://localhost:8151/fortress/wx/request/form/eccolle?sea[]=mystic&sea[]=bigband
    // http://localhost:8151/fortress/wx/request/form/eccolle?land[]=oneman&land[]=minnie
    // http://localhost:8151/fortress/wx/request/form/eccolle?piari[]=first&piari[]=second
    //
    // [system error]
    // http://localhost:8151/fortress/wx/request/form/eccolle?land=oneman&land=minnie
    // http://localhost:8151/fortress/wx/request/form/eccolle?sea[0]=mystic&sea[1]=bigband
    // http://localhost:8151/fortress/wx/request/form/eccolle?land[0]=oneman&land[1]=minnie
    @Execute
    public JsonResponse<MyEcColleForm> eccolle(MyEcColleForm form) {
        "".charAt(1);
        validateApi(form, messages -> {});
        logger.debug("sea: {}", form.sea);
        logger.debug("land: {}", form.land);
        logger.debug("piari: {}", form.piari);
        logger.debug("bonvo: {}", form.bonvo);
        return asJson(form); // for visual check
    }

    public static class MyEcColleForm {

        public ImmutableList<String> sea;

        public MutableList<String> land;

        public List<String> piari;

        public ImmutableMap<String, String> bonvo;
    }
}
