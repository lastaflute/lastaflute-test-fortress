package org.docksidestage.remote.harbor;

import javax.annotation.Resource;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.harbor.base.RemoteHbPagingReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductSearchParam;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhvTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    // ===================================================================================
    //                                                                     for Application
    //                                                                     ===============
    public void test_requestProductList_basic() {
        // ## Arrange ##
        RemoteHbProductSearchParam param = new RemoteHbProductSearchParam();
        param.productName = "S";
        String json = "{pageSize=4, currentPageNumber=1, allRecordCount=20, allPageCount=5, rows=[]}";
        MockHttpClient client = MockHttpClient.create(response -> {
            response.peekRequest(request -> {
                assertContainsAll(request.getBody().get(), "productName", param.productName);
            });
            response.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        RemoteHbPagingReturn<RemoteHbProductRowReturn> ret = bhv.requestProductList(param);

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
        RemoteHbProductSearchParam param = new RemoteHbProductSearchParam();
        String json = "{cause=VALIDATION_ERROR, errors : [{field=productName, messages=[\"sea land piari\"]}]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> true).httpStatus(400);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        // ## Assert ##
        mockHtmlValidateCall();
        assertValidationError(() -> bhv.requestProductList(param)).handle(data -> {
            assertTrue(data.requiredMessages().hasMessageOf("productName"));
            data.requiredMessageOfDirectly("productName", "land");
        });
    }
}
