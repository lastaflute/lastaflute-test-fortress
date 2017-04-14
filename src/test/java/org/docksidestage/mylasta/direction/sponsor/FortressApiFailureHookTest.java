package org.docksidestage.mylasta.direction.sponsor;

import javax.annotation.Resource;

import org.dbflute.optional.OptionalThing;
import org.docksidestage.mylasta.direction.sponsor.FortressApiFailureHook.UnifiedFailureResult;
import org.docksidestage.mylasta.direction.sponsor.FortressApiFailureHook.UnifiedFailureType;
import org.docksidestage.unit.UnitFortressTestCase;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.api.ApiFailureResource;
import org.lastaflute.web.login.exception.LoginFailureException;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.ruts.process.ActionRuntime;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class FortressApiFailureHookTest extends UnitFortressTestCase {

    @Resource
    private RequestManager requestManager;

    public void test_handleApplicationException_justType() throws Exception {
        // ## Arrange ##
        FortressApiFailureHook hook = new FortressApiFailureHook();
        OptionalThing<UserMessages> messages = OptionalThing.empty();
        ActionRuntime runtime = getMockHtmlRuntime();
        ApiFailureResource resource = new ApiFailureResource(runtime, messages, requestManager);
        LoginFailureException cause = new LoginFailureException("sea");

        // ## Act ##
        @SuppressWarnings("unchecked")
        JsonResponse<UnifiedFailureResult> response = (JsonResponse<UnifiedFailureResult>) hook.handleApplicationException(resource, cause);

        // ## Assert ##
        assertEquals(400, response.getHttpStatus().get());
        UnifiedFailureResult bean = response.getJsonBean();
        assertNotNull(bean.notice);
        assertEquals(UnifiedFailureType.LOGIN_FAILURE, bean.cause);
    }

    public void test_handleApplicationException_subType() throws Exception {
        // ## Arrange ##
        FortressApiFailureHook hook = new FortressApiFailureHook();
        OptionalThing<UserMessages> messages = OptionalThing.empty();
        ActionRuntime runtime = getMockHtmlRuntime();
        ApiFailureResource resource = new ApiFailureResource(runtime, messages, requestManager);
        LoginFailureException cause = new LoginFailureException("sea") {

            private static final long serialVersionUID = 1L;
        }; // sub class

        // ## Act ##
        @SuppressWarnings("unchecked")
        JsonResponse<UnifiedFailureResult> response = (JsonResponse<UnifiedFailureResult>) hook.handleApplicationException(resource, cause);

        // ## Assert ##
        assertEquals(400, response.getHttpStatus().get());
        UnifiedFailureResult bean = response.getJsonBean();
        assertNotNull(bean.notice);
        assertEquals(UnifiedFailureType.LOGIN_FAILURE, bean.cause);
    }
}
