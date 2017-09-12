package org.docksidestage.remote.harbor;

import javax.annotation.Resource;

import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.harbor.base.HbSearchPagingResult;
import org.docksidestage.remote.harbor.product.HbProductRowResult;
import org.docksidestage.remote.harbor.product.HbProductSearchBody;
import org.docksidestage.unit.UnitFortressTestCase;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult.FaicliUnifiedFailureType;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhvTest extends UnitFortressTestCase {

    @Resource
    private RequestManager requestManager;

    // #later jflute Failure Response type
    // #later jflute Action test
    // #later jflute asJson(json)? asJsonAll(json)?
    public void test_requestProductList_basic() {
        // ## Arrange ##
        HbProductSearchBody body = new HbProductSearchBody();
        body.productName = "S";
        String json = "{pageSize=4, currentPageNumber=1, allRecordCount=20, allPageCount=5, rows=[]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.peekRequest(request -> {
                assertContainsAll(request.getBody().get(), "productName", body.productName);
            });
            resopnse.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        HbSearchPagingResult<HbProductRowResult> result = bhv.requestProductList(body);

        // ## Assert ##
        assertEquals(4, result.pageSize);
        assertEquals(5, result.allPageCount);
        assertEquals(20, result.allRecordCount);
        assertEquals(5, result.allPageCount);
        assertEquals(0, result.rows.size());
    }

    public void test_validationError_basic() {
        // ## Arrange ##
        HbProductSearchBody body = new HbProductSearchBody();
        body.productName = "S";
        String json = "{cause=VALIDATION_ERROR, errors : [{field=productName, code=LENGTH, data={min:0,max:10}}]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.peekRequest(request -> {
                assertContainsAll(request.getBody().get(), "productName", body.productName);
            });
            resopnse.asJsonDirectly(json, request -> true).httpStatus(400);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        assertException(RemoteApiHttpClientErrorException.class, () -> bhv.requestProductList(body)).handle(cause -> {
            // ## Assert ##
            FaicliUnifiedFailureResult result = (FaicliUnifiedFailureResult) cause.getFailureResponse().get();
            log(result);
            assertEquals(FaicliUnifiedFailureType.VALIDATION_ERROR, result.cause);
        });
    }
}
