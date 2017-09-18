package org.docksidestage.remote.harbor;

import javax.annotation.Resource;

import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.harbor.base.RemoteSearchPagingReturn;
import org.docksidestage.remote.harbor.product.RemoteProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteProductSearchParam;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult.FaicliFailureErrorPart;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult.FaicliUnifiedFailureType;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhvTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    // #later jflute Action test
    // #later jflute asJson(json)? asJsonAll(json)?
    public void test_requestProductList_basic() {
        // ## Arrange ##
        RemoteProductSearchParam param = new RemoteProductSearchParam();
        param.productName = "S";
        String json = "{pageSize=4, currentPageNumber=1, allRecordCount=20, allPageCount=5, rows=[]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.peekRequest(request -> {
                assertContainsAll(request.getBody().get(), "productName", param.productName);
            });
            resopnse.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        RemoteSearchPagingReturn<RemoteProductRowReturn> ret = bhv.requestProductList(param);

        // ## Assert ##
        assertEquals(4, ret.pageSize);
        assertEquals(5, ret.allPageCount);
        assertEquals(20, ret.allRecordCount);
        assertEquals(5, ret.allPageCount);
        assertEquals(0, ret.rows.size());
    }

    public void test_validationError_basic() {
        // ## Arrange ##
        RemoteProductSearchParam param = new RemoteProductSearchParam();
        String json = "{cause=VALIDATION_ERROR, errors : [{field=productName, code=LENGTH, data={min:0,max:10}}]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> true).httpStatus(400);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        assertException(RemoteApiHttpClientErrorException.class, () -> bhv.requestProductList(param)).handle(cause -> {
            // ## Assert ##
            FaicliUnifiedFailureResult result = (FaicliUnifiedFailureResult) cause.getFailureResponse().get();
            log(result);
            assertEquals(FaicliUnifiedFailureType.VALIDATION_ERROR, result.cause);
            assertHasOnlyOneElement(result.errors);
            FaicliFailureErrorPart errorPart = result.errors.get(0);
            assertEquals("productName", errorPart.field);
            assertEquals("LENGTH", errorPart.code);
            assertEquals(0, toInteger(errorPart.data.get("min"))); // because it may be decimal type
            assertEquals(10, toInteger(errorPart.data.get("max"))); // me too
        });
    }
}
