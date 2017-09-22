package org.docksidestage.remote.maihama.hangar;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.mapping.FlVacantRemoteMappingPolicy;
import org.dbflute.util.Srl;
import org.docksidestage.remote.harbor.base.RemotePagingReturn;
import org.docksidestage.remote.harbor.base.RemoteUnifiedFailureResult;
import org.docksidestage.remote.harbor.mypage.RemoteMypageProductReturn;
import org.docksidestage.remote.harbor.product.RemoteProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteProductSearchParam;
import org.docksidestage.remote.harbor.signin.RemoteSigninParam;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.core.message.MessageManager;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.receiver.LaJsonReceiver;
import org.lastaflute.remoteapi.sender.body.LaJsonSender;
import org.lastaflute.remoteapi.sender.query.LaQuerySender;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult.FaicliFailureErrorPart;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult.FaicliUnifiedFailureType;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteMaihamaHangarBhv extends LastaRemoteBehavior {

    @Resource
    private MessageManager messageManager;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RemoteMaihamaHangarBhv(RequestManager requestManager) {
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
                FaicliUnifiedFailureResult result = (FaicliUnifiedFailureResult) resource.getCause().getFailureResponse().get();
                if (FaicliUnifiedFailureType.VALIDATION_ERROR.equals(result.cause)) {
                    UserMessages messages = new UserMessages();
                    result.errors.forEach(error -> {
                        String completeMessage = convertErrorToMessage(error);
                        messages.add(error.field, UserMessage.asDirectMessage(completeMessage));
                    });
                    return resource.asHtmlValidationError(messages);
                }
            }
            return null; // no translation
        });
    }

    private String convertErrorToMessage(FaicliFailureErrorPart error) {
        String messageKey = "constraints." + error.code + ".messsage";
        String plainMessage = messageManager.getMessage(Locale.ENGLISH, messageKey);
        Map<String, String> fromToMap = new HashMap<>();
        error.data.forEach((key, value) -> fromToMap.put("{" + key + "}", value.toString()));
        return Srl.replaceBy(plainMessage, fromToMap);
    }

    @Override
    protected String getUrlBase() {
        return "http://localhost:8090/harbor";
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    public void requestSignin(RemoteSigninParam param) {
        doRequestPost(void.class, "/auth/signin", noMoreUrl(), param, rule -> {});
    }

    public List<RemoteMypageProductReturn> requestMypage() {
        return doRequestGet(new ParameterizedRef<List<RemoteMypageProductReturn>>() {
        }.getType(), "/mypage", noMoreUrl(), noQuery(), rule -> {});
    }

    public RemotePagingReturn<RemoteProductRowReturn> requestProductList(RemoteProductSearchParam param) {
        return doRequestPost(new ParameterizedRef<RemotePagingReturn<RemoteProductRowReturn>>() {
        }.getType(), "/product/list", moreUrl(1), param, rule -> {});
    }
}
