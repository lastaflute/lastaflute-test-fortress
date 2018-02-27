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
package org.docksidestage.app.web.wx.rmharbor;

import java.util.List;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.dbflute.utflute.lastaflute.mock.TestingJsonData;
import org.docksidestage.remote.harbor.mypage.RemoteHbMypageProductReturn;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.login.exception.LoginFailureException;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
public class WxRmharborActionTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                              Simple
    //                                                                              ======
    public void test_mypage_basic() {
        // ## Arrange ##
        String json = "[{productName=\"sea\", regularPrice=100}]";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> request.getUrl().contains("mypage"));
        });
        registerMock(client);
        WxRmharborAction action = new WxRmharborAction();
        inject(action);

        // ## Act ##
        JsonResponse<List<RemoteHbMypageProductReturn>> response = action.mypage();

        // ## Assert ##
        TestingJsonData<List<RemoteHbMypageProductReturn>> jsonData = validateJsonData(response);
        List<RemoteHbMypageProductReturn> productList = jsonData.getJsonResult();
        assertHasOnlyOneElement(productList);
        RemoteHbMypageProductReturn product = productList.get(0);
        assertEquals("sea", product.productName);
        assertEquals(100, product.regularPrice);
    }

    // ===================================================================================
    //                                                                          via Assist
    //                                                                          ==========
    public void test_signin_basic() {
        // ## Arrange ##
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly("(*want to no content)", request -> request.getUrl().contains("signin"));
        });
        registerMock(client);
        WxRmharborAction action = new WxRmharborAction();
        inject(action);
        WxRmharborSigninForm form = new WxRmharborSigninForm();
        form.account = "Pixy";
        form.password = "sea";

        // ## Act ##
        // ## Assert ##
        action.signin(form); // expects no exception
    }

    public void test_signin_loginFailure() {
        // ## Arrange ##
        String json = "{cause=LOGIN_FAILURE, errors : []}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> request.getUrl().contains("signin")).httpStatus(400);
        });
        registerMock(client);
        WxRmharborAction action = new WxRmharborAction();
        inject(action);
        WxRmharborSigninForm form = new WxRmharborSigninForm();
        form.account = "Pixy";
        form.password = "land";

        // ## Act ##
        // ## Assert ##
        assertException(LoginFailureException.class, () -> action.signin(form));
    }
}
