package org.docksidestage.remote.maihama.showbase.product;

import javax.annotation.Resource;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.maihama.showbase.product.list.status.RemoteProductListStatusReturn;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteMaihamaShowbaseProductBhvTest extends UnitFortressBasicTestCase {

    @Resource
    private RequestManager requestManager;

    public void test_requestListStatus_returnsList() {
        // ## Arrange ##
        // "type: object" is treated as map by RemoteApiGen
        String json = "[{key={sea=harbor}, value={hangar=mystic}}, {key={land=castle}, value={showbase=oneman}}]";
        MockHttpClient client = MockHttpClient.create(response -> {
            response.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteMaihamaShowbaseProductBhv productBhv = new RemoteMaihamaShowbaseProductBhv(requestManager);
        inject(productBhv);

        // ## Act ##
        ImmutableList<RemoteProductListStatusReturn> statusList = productBhv.requestListStatus();

        // ## Assert ##
        for (RemoteProductListStatusReturn status : statusList) {
            log(status);
        }
    }
}
