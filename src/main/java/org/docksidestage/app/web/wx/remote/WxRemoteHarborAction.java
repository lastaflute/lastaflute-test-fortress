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
package org.docksidestage.app.web.wx.remote;

import java.util.List;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.remote.harbor.RemoteHarborBhv;
import org.docksidestage.remote.harbor.base.RemoteSearchPagingReturn;
import org.docksidestage.remote.harbor.mypage.RemoteMypageProductReturn;
import org.docksidestage.remote.harbor.product.RemoteProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteProductSearchParam;
import org.docksidestage.remote.harbor.signin.RemoteSigninParam;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRemoteHarborAction extends FortressBaseAction {

    @Resource
    private RemoteHarborBhv harborBhv;

    // http://localhost:8151/fortress/wx/remote/harbor/signin/sea
    @Execute
    public JsonResponse<Void> signin(String password) { // #simple_for_example
        RemoteSigninParam param = new RemoteSigninParam();
        param.account = "Pixy";
        param.password = password;
        harborBhv.requestSignin(param);
        return JsonResponse.asEmptyBody();
    }

    // http://localhost:8151/fortress/wx/remote/harbor/mypage/
    @Execute
    public JsonResponse<List<RemoteMypageProductReturn>> mypage() {
        List<RemoteMypageProductReturn> retList = harborBhv.requestMypage();
        return asJson(retList);
    }

    // http://localhost:8151/fortress/wx/remote/harbor/products/
    @Execute
    public JsonResponse<RemoteSearchPagingReturn<RemoteProductRowReturn>> products() {
        RemoteProductSearchParam param = new RemoteProductSearchParam();
        param.productName = "S";
        RemoteSearchPagingReturn<RemoteProductRowReturn> ret = harborBhv.requestProductList(param);
        return asJson(ret);
    }
}
