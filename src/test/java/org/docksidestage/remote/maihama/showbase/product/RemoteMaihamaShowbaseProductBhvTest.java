package org.docksidestage.remote.maihama.showbase.product;

import javax.annotation.Resource;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.maihama.showbase.product.list.status.RemoteProductListStatusReturn;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteMaihamaShowbaseProductBhvTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    public void test_requestListStatus_returnsList() {
        // ## Arrange ##
        String json = "[{key=sea, value=mystic}, {key=land, value=oneman}]";
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
