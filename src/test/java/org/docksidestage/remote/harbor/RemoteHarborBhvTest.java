package org.docksidestage.remote.harbor;

import java.util.Iterator;

import javax.annotation.Resource;

import org.dbflute.remoteapi.mock.MockHttpClient;
import org.docksidestage.remote.harbor.base.RemotePagingReturn;
import org.docksidestage.remote.harbor.product.RemoteProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteProductSearchParam;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.magic.ThreadCacheContext;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.validation.exception.ValidationErrorException;

/**
 * @author jflute
 */
public class RemoteHarborBhvTest extends UnitFortressWebTestCase {

    @Resource
    private RequestManager requestManager;

    // ===================================================================================
    //                                                                     for Application
    //                                                                     ===============
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
        RemotePagingReturn<RemoteProductRowReturn> ret = bhv.requestProductList(param);

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
        RemoteProductSearchParam param = new RemoteProductSearchParam();
        String json = "{cause=VALIDATION_ERROR, errors : [{field=productName, messages=[\"sea\"]}]}";
        MockHttpClient client = MockHttpClient.create(resopnse -> {
            resopnse.asJsonDirectly(json, request -> true).httpStatus(400);
        });
        registerMock(client);
        RemoteHarborBhv bhv = new RemoteHarborBhv(requestManager);
        inject(bhv);

        // ## Act ##
        // ## Assert ##
        mockHtmlValidate();
        assertException(ValidationErrorException.class, () -> bhv.requestProductList(param)).handle(cause -> {
            UserMessages messages = cause.getMessages();
            Iterator<UserMessage> ite = messages.silentAccessByIteratorOf("productName");
            while (ite.hasNext()) {
                UserMessage message = ite.next();
                String messageKey = message.getMessageKey();
                assertEquals("sea", messageKey);
                markHere("exists");
            }
            assertMarked("exists");
        });
    }

    private void mockHtmlValidate() {
        ThreadCacheContext.registerValidatorErrorHook(() -> null); // dummy
    }
}
