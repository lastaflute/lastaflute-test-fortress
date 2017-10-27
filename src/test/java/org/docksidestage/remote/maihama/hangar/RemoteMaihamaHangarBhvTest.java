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
package org.docksidestage.remote.maihama.hangar;

import javax.annotation.Resource;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.maihama.hangar.base.RemoteHgPagingReturn;
import org.docksidestage.remote.maihama.hangar.product.RemoteHgProductRowReturn;
import org.docksidestage.remote.maihama.hangar.product.RemoteHgProductSearchParam;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteMaihamaHangarBhvTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    // ===================================================================================
    //                                                                     for Application
    //                                                                     ===============
    public void test_requestProductList_basic() {
        // ## Arrange ##
        RemoteHgProductSearchParam param = new RemoteHgProductSearchParam();
        param.productName = "S";
        String json = "{pageSize=4, currentPageNumber=1, allRecordCount=20, allPageCount=5, rows=[]}";
        MockHttpClient client = MockHttpClient.create(response -> {
            response.peekRequest(request -> {
                assertContainsAll(request.getBody().get(), "productName", param.productName);
            });
            response.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteMaihamaHangarBhv bhv = new RemoteMaihamaHangarBhv(requestManager);
        inject(bhv);

        // ## Act ##
        RemoteHgPagingReturn<RemoteHgProductRowReturn> ret = bhv.requestProductList(param);

        // ## Assert ##
        assertEquals(4, ret.pageSize);
        assertEquals(5, ret.allPageCount);
        assertEquals(20, ret.allRecordCount);
        assertEquals(5, ret.allPageCount);
        assertEquals(0, ret.rows.size());
    }

    // ===================================================================================
    //                                                                       for Framework
    //                                                                       =============
    public void test_framework_validationError_basic() {
        // ## Arrange ##
        RemoteHgProductSearchParam param = new RemoteHgProductSearchParam();
        String json = "{cause=VALIDATION_ERROR, errors : [{field=productName, code=Required, data={}}]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> true).httpStatus(400);
        });
        registerMock(client);
        RemoteMaihamaHangarBhv bhv = new RemoteMaihamaHangarBhv(requestManager);
        inject(bhv);

        // ## Act ##
        // ## Assert ##
        mockHtmlValidateCall();
        assertValidationError(() -> bhv.requestProductList(param)).handle(data -> {
            assertTrue(data.requiredMessages().hasMessageOf("productName"));
            data.requiredMessageOfDirectly("productName", "is required");
        });
    }
}
