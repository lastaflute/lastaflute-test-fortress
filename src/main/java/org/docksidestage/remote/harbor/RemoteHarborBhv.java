package org.docksidestage.remote.harbor;

import org.dbflute.remoteapi.FlutyRemoteApiOption;
import org.docksidestage.remote.harbor.base.HbSearchPagingResult;
import org.docksidestage.remote.harbor.product.HbProductRowResult;
import org.docksidestage.remote.harbor.product.HbProductSearchBody;
import org.lastaflute.core.json.JsonMappingOption;
import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.remoteapi.LastaRemoteBehavior;
import org.lastaflute.remoteapi.converter.LaJsonRequestConverter;
import org.lastaflute.remoteapi.converter.LaJsonResponseConverter;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class RemoteHarborBhv extends LastaRemoteBehavior {

    public RemoteHarborBhv(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    protected void yourOption(FlutyRemoteApiOption option) {
        JsonMappingOption jsonMappingOption = new JsonMappingOption();
        option.setRequestConverter(new LaJsonRequestConverter(requestManager, jsonMappingOption));
        option.setResponseConverter(new LaJsonResponseConverter(requestManager, jsonMappingOption));
    }

    @Override
    protected String getUrlBase() {
        return "http://localhost:8090/harbor";
    }

    public HbSearchPagingResult<HbProductRowResult> requestProductList(HbProductSearchBody body) {
        return doRequestPost(new ParameterizedRef<HbSearchPagingResult<HbProductRowResult>>() {
        }.getType(), "/lido/product/list", new Object[] { 1 }, body, op -> {});
    }
}
