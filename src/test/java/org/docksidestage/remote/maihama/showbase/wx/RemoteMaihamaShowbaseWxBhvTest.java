package org.docksidestage.remote.maihama.showbase.wx;

import javax.annotation.Resource;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.nobody.RemoteWxRemogenTrickyNobodyReturn;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteMaihamaShowbaseWxBhvTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    public void test_requestRemogenTrickyNobody_bodyNotPresent() {
        // ## Arrange ##
        String json = "{key=sea, value=mystic}";
        MockHttpClient client = MockHttpClient.create(response -> {
            response.peekRequest(request -> {
                log(request);
                assertFalse(request.getBody().isPresent());
            });
            response.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteMaihamaShowbaseWxBhv bhv = new RemoteMaihamaShowbaseWxBhv(requestManager);
        inject(bhv);

        // ## Act ##
        RemoteWxRemogenTrickyNobodyReturn ret = bhv.requestRemogenTrickyNobody();

        // ## Assert ##
        assertEquals("sea", ret.key);
        assertEquals("mystic", ret.value);
    }
}
