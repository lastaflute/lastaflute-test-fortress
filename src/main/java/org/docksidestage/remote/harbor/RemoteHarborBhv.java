package org.docksidestage.remote.harbor;

import org.dbflute.optional.OptionalThing;
import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.dbflute.remoteapi.mapping.FlVacantRemoteMappingPolicy;
import org.docksidestage.remote.harbor.base.HbSearchPagingResult;
import org.docksidestage.remote.harbor.mypage.HbMypageProductResult;
import org.docksidestage.remote.harbor.product.HbProductRowResult;
import org.docksidestage.remote.harbor.product.HbProductSearchBody;
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
        JsonMappingOption jsonMappingOption = new JsonMappingOption();
        rule.sendBodyBy(new LaJsonSender(requestManager, jsonMappingOption));
        rule.receiveBodyBy(new LaJsonReceiver(requestManager, jsonMappingOption));
        rule.handleFailureResponseAs(FaicliUnifiedFailureResult.class);
    }

    @Override
    protected String getUrlBase() {
        return "http://localhost:8090/harbor";
    }

    public HbMypageProductResult requestMypage() {
        return doRequestGet(HbMypageProductResult.class, "/lido/mypage", moreUrl(), OptionalThing.empty(), rule -> {
            rule.sendQueryBy(new LaQuerySender(new FlVacantRemoteMappingPolicy()));
        });
    }

    public HbSearchPagingResult<HbProductRowResult> requestProductList(HbProductSearchBody body) {
        return doRequestPost(new ParameterizedRef<HbSearchPagingResult<HbProductRowResult>>() {
        }.getType(), "/lido/product/list", moreUrl(1), body, rule -> {});
    }
}
