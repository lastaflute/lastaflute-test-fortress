package org.docksidestage.remote.harbor;

import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.exception.RemoteApiResponseValidationErrorException;
import org.dbflute.remoteapi.http.SupportedHttpMethod;
import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.harbor.base.RemoteHbPagingReturn;
import org.docksidestage.remote.harbor.lido.product.RemoteHbLidoProductRowReturn;
import org.docksidestage.remote.harbor.lido.product.RemoteHbLidoProductSearchParam;
import org.docksidestage.remote.harbor.serh.product.RemoteHbSerhProductSearchParam;
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
    // -----------------------------------------------------
    //                                            Lido Style
    //                                            ----------
    public void test_requestLidoProductList_basic() {
        // ## Arrange ##
        RemoteHbLidoProductSearchParam param = new RemoteHbLidoProductSearchParam();
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
        RemoteHbPagingReturn<RemoteHbLidoProductRowReturn> ret = bhv.requestLidoProductList(param);

        // ## Assert ##
        assertEquals(4, ret.pageSize);
        assertEquals(5, ret.allPageCount);
        assertEquals(20, ret.allRecordCount);
        assertEquals(5, ret.allPageCount);
        assertEquals(1, ret.rows.size());
    }

    public void test_requestLidoProductList_return_validtionError() {
        // ## Arrange ##
        RemoteHbLidoProductSearchParam param = new RemoteHbLidoProductSearchParam();
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
        assertException(RemoteApiResponseValidationErrorException.class, () -> bhv.requestLidoProductList(param)).handle(cause -> {
            // ## Assert ##
            String errorMsg = cause.getCause().getMessage();
            assertContainsAll(errorMsg, "productStatusName", "regularPrice");
            assertContains(errorMsg, Required.class.getSimpleName());
        });
    }

    // -----------------------------------------------------
    //                                      ServerHTML Style
    //                                      ----------------
    public void test_requestSerhProductList_basic() {
        // ## Arrange ##
        RemoteHbSerhProductSearchParam param = new RemoteHbSerhProductSearchParam();
        param.productName = "S";
        String mockHtml = "<html><body>sea</body></html>";
        MockHttpClient client = MockHttpClient.create(response -> {
            response.asJsonDirectly(mockHtml, request -> true); // asHtmlDirectly() is not prepared
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        OptionalThing<String> optHtml = bhv.requestSerhProductList(param);

        // ## Assert ##
        assertTrue(optHtml.isPresent());
        String actualHtml = optHtml.get();
        log(actualHtml);
        assertEquals(mockHtml, actualHtml);
    }

    // ===================================================================================
    //                                                                       for Framework
    //                                                                       =============
    public void test_framework_clientError_basic() {
        // ## Arrange ##
        RemoteHbLidoProductSearchParam param = new RemoteHbLidoProductSearchParam();
        String json = "{cause=BUSINESS_ERROR, errors : [{field=productName, messages=[\"sea land piari\"]}]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> true).httpStatus(404);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        // ## Assert ##
        assertException(RemoteApiHttpClientErrorException.class, () -> bhv.requestLidoProductList(param)).handle(cause -> {
            assertEquals(SupportedHttpMethod.POST, cause.getHttpMethod());
            assertEquals(404, cause.getHttpStatus());
        });
    }

    public void test_framework_validationError_basic() {
        // ## Arrange ##
        RemoteHbLidoProductSearchParam param = new RemoteHbLidoProductSearchParam();
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
        assertValidationError(() -> bhv.requestLidoProductList(param)).handle(data -> {
            assertTrue(data.requiredMessages().hasMessageOf("productName"));
            data.requiredMessageOfDirectly("productName", "land");
        });
    }
}
