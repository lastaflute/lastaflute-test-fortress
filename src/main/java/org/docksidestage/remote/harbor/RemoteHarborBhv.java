package org.docksidestage.remote.harbor;

import java.util.List;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.mapping.FlVacantRemoteMappingPolicy;
import org.docksidestage.remote.harbor.base.RemotePagingReturn;
import org.docksidestage.remote.harbor.base.RemoteUnifiedFailureResult;
import org.docksidestage.remote.harbor.base.RemoteUnifiedFailureResult.RemoteUnifiedFailureType;
import org.docksidestage.remote.harbor.mypage.RemoteMypageProductReturn;
import org.docksidestage.remote.harbor.product.RemoteProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteProductSearchParam;
import org.docksidestage.remote.harbor.signin.RemoteSigninParam;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.receiver.LaJsonReceiver;
import org.lastaflute.remoteapi.sender.body.LaJsonSender;
import org.lastaflute.remoteapi.sender.query.LaQuerySender;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhv extends LastaRemoteBehavior {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RemoteHarborBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                          Initialize
    //                                                                          ==========
    @Override
    protected void yourDefaultRule(FlutyRemoteApiRule rule) {
        rule.sendQueryBy(new LaQuerySender(new FlVacantRemoteMappingPolicy()));

        JsonMappingOption jsonMappingOption = new JsonMappingOption();
        rule.sendBodyBy(new LaJsonSender(requestManager, jsonMappingOption));
        rule.receiveBodyBy(new LaJsonReceiver(requestManager, jsonMappingOption));

        rule.handleFailureResponseAs(RemoteUnifiedFailureResult.class); // server-managed message way
        rule.translateClientError(resource -> {
            if (resource.getCause().getHttpStatus() == 400) { // controlled client error
                RemoteUnifiedFailureResult result = (RemoteUnifiedFailureResult) resource.getCause().getFailureResponse().get();
                if (RemoteUnifiedFailureType.VALIDATION_ERROR.equals(result.cause)) {
                    UserMessages messages = new UserMessages();
                    result.errors.forEach(error -> {
                        error.messages.forEach(message -> {
                            messages.add(error.field, UserMessage.asDirectMessage(message));
                        });
                    });
                    return resource.asHtmlValidationError(messages);
                }
            }
            return null; // no translation
        });
    }

    @Override
    protected String getUrlBase() {
        return "http://localhost:8090/harbor";
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    public void requestSignin(RemoteSigninParam param) {
        doRequestPost(void.class, "/lido/auth/signin", noMoreUrl(), param, rule -> {});
    }

    public List<RemoteMypageProductReturn> requestMypage() {
        return doRequestGet(new ParameterizedRef<List<RemoteMypageProductReturn>>() {
        }.getType(), "/lido/mypage", noMoreUrl(), noQuery(), rule -> {});
    }

    public RemotePagingReturn<RemoteProductRowReturn> requestProductList(RemoteProductSearchParam param) {
        return doRequestPost(new ParameterizedRef<RemotePagingReturn<RemoteProductRowReturn>>() {
        }.getType(), "/lido/product/list", moreUrl(1), param, rule -> {});
    }
}
