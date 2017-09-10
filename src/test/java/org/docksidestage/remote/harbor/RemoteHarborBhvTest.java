package org.docksidestage.remote.harbor;

import javax.annotation.Resource;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.dbflute.remoteapi.mock.MockHttpClientBuilder;
import org.docksidestage.remote.harbor.base.HbSearchPagingResult;
import org.docksidestage.remote.harbor.product.HbProductRowResult;
import org.docksidestage.remote.harbor.product.HbProductSearchBody;
import org.docksidestage.unit.UnitFortressTestCase;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhvTest extends UnitFortressTestCase {

    @Resource
    private RequestManager requestManager;

    public void test_requestProductList() {
        // ## Arrange ##
        String json = "{pageSize=4, currentPageNumber=1, allRecordCount=20, allPageCount=5, rows=[]}";
        MockHttpClient client = MockHttpClientBuilder.create().assertRequest(request -> {
            log(request);
            assertContains(request.getUrl(), "/lido/product/list/1");
            request.getBody().alwaysPresent(body -> {
                assertContains(body, "\"S\"");
            });
        }).responseJsonDirectly(json).build();
        RemoteHarborBhv bhv = mockClient(new RemoteHarborBhv(requestManager), client);
        HbProductSearchBody body = new HbProductSearchBody();
        body.productName = "S";

        // ## Act ##
        HbSearchPagingResult<HbProductRowResult> result = bhv.requestProductList(body);

        // ## Assert ##
        log(ln() + Lato.string(result));
        assertEquals(4, result.pageSize);
        assertEquals(5, result.allPageCount);
        assertEquals(20, result.allRecordCount);
        assertEquals(5, result.allPageCount);
        assertEquals(0, result.rows.size());
    }
}
