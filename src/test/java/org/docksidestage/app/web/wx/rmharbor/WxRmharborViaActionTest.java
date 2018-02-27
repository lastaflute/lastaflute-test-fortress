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

import javax.annotation.Resource;

import org.dbflute.remoteapi.exception.RemoteApiIOException;
import org.dbflute.remoteapi.mock.MockHttpClient;
import org.dbflute.utflute.lastaflute.mock.TestingJsonData;
import org.docksidestage.remote.harbor.RemoteHarborBhv;
import org.docksidestage.remote.harbor.mypage.RemoteHbMypageProductReturn;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class WxRmharborViaActionTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    public void test_mypage_mockBehavior() {
        // ## Arrange ##
        String json = "[{productName=\"sea\", regularPrice=100}]";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> request.getUrl().contains("mypage"));
        });
        registerMock(client);
        registerMock(inject(new RemoteHarborBhv(requestManager))); // avoid no reach
        WxRmharborViaAction action = new WxRmharborViaAction();
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

    public void test_mypage_noReach() {
        // ## Arrange ##
        String json = "[{productName=\"sea\", regularPrice=100}]";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> request.getUrl().contains("mypage"));
        });
        registerMock(client);
        WxRmharborViaAction action = new WxRmharborViaAction();
        inject(action);

        // ## Act ##
        // ## Assert ##
        // nested mock headache for now, and this test may fail if harbor is active
        // (while, if harbor is booting, remote request is success so test fails) 
        assertException(RemoteApiIOException.class, () -> action.mypage());
    }
}
