package org.docksidestage.remote.harbor;

import javax.annotation.Resource;

import org.dbflute.remoteapi.exception.RemoteApiResponseValidationErrorException;
import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.harbor.base.RemoteHbPagingReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductSearchParam;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class RemoteHarborBhvTest extends UnitFortressBasicTestCase {

    @Resource
    private RequestManager requestManager;

    // ===================================================================================
    //                                                                     for Application
    //                                                                     ===============
    public void test_requestProductList_empty() {
        // ## Arrange ##
        RemoteHbProductSearchParam param = new RemoteHbProductSearchParam();
        param.productName = "S";
        StringBuilder sb = new StringBuilder();
        sb.append("{pageSize=4, currentPageNumber=1, allRecordCount=20, allPageCount=5, rows=");
        sb.append("[{productId=7, productName=\"sea\", productStatusName=\"land\", regularPrice=100}]}");
        String json = sb.toString();
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
        assertEquals(1, ret.rows.size());
    }

    public void test_requestProductList_return_validtionError() {
        // ## Arrange ##
        RemoteHbProductSearchParam param = new RemoteHbProductSearchParam();
        param.productName = "S";
        StringBuilder sb = new StringBuilder();
        sb.append("{pageSize=4, currentPageNumber=1, allRecordCount=20, allPageCount=5, rows=");
        sb.append("[{productId=7, productName=\"sea\"}]}");
        String json = sb.toString();
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
        assertException(RemoteApiResponseValidationErrorException.class, () -> bhv.requestProductList(param)).handle(cause -> {
            // ## Assert ##
            String errorMsg = cause.getCause().getMessage();
            assertContainsAll(errorMsg, "productStatusName", "regularPrice");
            assertContains(errorMsg, Required.class.getSimpleName());
        });
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
