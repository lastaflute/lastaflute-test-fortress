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
package org.docksidestage.app.web.wx.remoteapi.rmshowbase;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.remote.maihama.showbase.wx.RemoteMaihamaShowbaseWxBhv;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodDeleteReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.RemoteWxRemogenMethodGetReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.method.onbodyjson.RemoteWxRemogenMethodOnbodyjsonReturn;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRmshowbaseRemogenMethodAction extends FortressBaseAction {

    @Resource
    private RemoteMaihamaShowbaseWxBhv showbaseWxBhv;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // -----------------------------------------------------
    //                                                  GET
    //                                                 -----
    // http://localhost:8151/fortress/wx/remoteapi/rmshowbase/remogen/method/get/onbodyjson
    @Execute(urlPattern = "@word/@word")
    public JsonResponse<RemoteWxRemogenMethodGetReturn> getOnbodyjson() {
        RemoteWxRemogenMethodGetReturn onbodyjsonReturn = showbaseWxBhv.requestRemogenMethodOnbodyjsonGet(param -> {
            param.sea = "mystic";
            param.land = 7;
        });
        return asJson(onbodyjsonReturn);
    }

    // -----------------------------------------------------
    //                                                DELETE
    //                                                ------
    // http://localhost:8151/fortress/wx/remoteapi/rmshowbase/remogen/method/delete
    @Execute
    public JsonResponse<RemoteWxRemogenMethodDeleteReturn> delete() {
        RemoteWxRemogenMethodDeleteReturn deleteReturn = showbaseWxBhv.requestRemogenMethodDelete(param -> {
            param.sea = "mystic";
            param.land = 7;
        });
        return asJson(deleteReturn);
    }

    // http://localhost:8151/fortress/wx/remoteapi/rmshowbase/remogen/method/delete/onbodyjson
    @Execute(urlPattern = "@word/@word")
    public JsonResponse<RemoteWxRemogenMethodOnbodyjsonReturn> deleteOnbodyjson() {
        RemoteWxRemogenMethodOnbodyjsonReturn onbodyjsonReturn = showbaseWxBhv.requestRemogenMethodOnbodyjson(param -> {
            param.sea = "mystic";
            param.land = 7;
        });
        return asJson(onbodyjsonReturn);
    }
}
