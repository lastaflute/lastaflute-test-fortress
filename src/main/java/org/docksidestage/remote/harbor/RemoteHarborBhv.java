package org.docksidestage.remote.harbor;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.mapping.FlVacantRemoteMappingPolicy;
import org.docksidestage.remote.harbor.base.RemoteSearchPagingRet;
import org.docksidestage.remote.harbor.mypage.RemoteMypageProductRet;
import org.docksidestage.remote.harbor.product.RemoteProductRowRet;
import org.docksidestage.remote.harbor.product.RemoteProductSearchParam;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.receiver.LaJsonReceiver;
import org.lastaflute.remoteapi.sender.body.LaJsonSender;
import org.lastaflute.remoteapi.sender.query.LaQuerySender;
import org.lastaflute.web.api.theme.FaicliUnifiedFailureResult;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhv extends LastaRemoteBehavior {

    public RemoteHarborBhv(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    protected void yourDefaultRule(FlutyRemoteApiRule rule) {
        rule.sendQueryBy(new LaQuerySender(new FlVacantRemoteMappingPolicy()));

        JsonMappingOption jsonMappingOption = new JsonMappingOption();
        rule.sendBodyBy(new LaJsonSender(requestManager, jsonMappingOption));
        rule.receiveBodyBy(new LaJsonReceiver(requestManager, jsonMappingOption));

        rule.handleFailureResponseAs(FaicliUnifiedFailureResult.class);
    }

    @Override
    protected String getUrlBase() {
        return "http://localhost:8090/harbor";
    }

    public RemoteMypageProductRet requestMypage() {
        return doRequestGet(RemoteMypageProductRet.class, "/lido/mypage", noMoreUrl(), noQuery(), rule -> {});
    }

    public RemoteSearchPagingRet<RemoteProductRowRet> requestProductList(RemoteProductSearchParam param) {
        return doRequestPost(new ParameterizedRef<RemoteSearchPagingRet<RemoteProductRowRet>>() {
        }.getType(), "/lido/product/list", moreUrl(1), param, rule -> {});
    }
}
