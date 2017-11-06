package org.docksidestage.remote.maihama.showbase.wx;

import javax.annotation.Resource;

import org.dbflute.remoteapi.exception.RemoteApiPathVariableNullElementException;
import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.piari.RemoteWxRemogenRoutingPiariReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.routing.resola.RemoteWxRemogenRoutingResolaReturn;
import org.docksidestage.remote.maihama.showbase.wx.remogen.tricky.nobody.RemoteWxRemogenTrickyNobodyReturn;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteMaihamaShowbaseWxBhvTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    // ===================================================================================
    //                                                                              Tricky
    //                                                                              ======
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

    // ===================================================================================
    //                                                                             Routing
    //                                                                             =======
    // -----------------------------------------------------
    //                                    Optional Parameter
    //                                    ------------------
    public void test_requestRemogenRoutingPiari_optionalParameter_basic() {
        // ## Arrange ##
        String json = "{method=resola}";
        MockHttpClient client = MockHttpClient.create(response -> {
            response.peekRequest(request -> {
                log(request);
                assertContains(request.getUrl(), "piari/plaza");
                assertFalse(request.getBody().isPresent());
            });
            response.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteMaihamaShowbaseWxBhv bhv = new RemoteMaihamaShowbaseWxBhv(requestManager);
        inject(bhv);

        // ## Act ##
        RemoteWxRemogenRoutingPiariReturn ret = bhv.requestRemogenRoutingPiari("plaza");

        // ## Assert ##
        log(ret);
    }

    public void test_requestRemogenRoutingPiari_optionalParameter_empty() {
        // ## Arrange ##
        String json = "{method=resola}";
        MockHttpClient client = MockHttpClient.create(response -> {
            response.peekRequest(request -> {
                log(request);
                assertContains(request.getUrl(), "piari");
                assertNotContains(request.getUrl(), "piari/plaza");
                assertFalse(request.getBody().isPresent());
            });
            response.asJsonDirectly(json, request -> true);
        });
        registerMock(client);
        RemoteMaihamaShowbaseWxBhv bhv = new RemoteMaihamaShowbaseWxBhv(requestManager);
        inject(bhv);

        // ## Act ##
        // ## Assert ##
        assertException(RemoteApiPathVariableNullElementException.class, () -> { // not supported for now
            bhv.requestRemogenRoutingPiari((String) null);
        });
    }

    // -----------------------------------------------------
    //                                               Wording
    //                                               -------
    public void test_requestRemogenRoutingResola_wording_basic() {
        // ## Arrange ##
        String json = "{method=resola}";
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
        RemoteWxRemogenRoutingResolaReturn ret = bhv.requestRemogenRoutingResola(1);

        // ## Assert ##
        log(ret);
    }
}
