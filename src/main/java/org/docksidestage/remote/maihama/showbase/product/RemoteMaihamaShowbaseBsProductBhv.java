/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.remote.maihama.showbase.product;

import java.util.function.Consumer;

import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.product.detail.RemoteProductDetailReturn;
import org.docksidestage.remote.maihama.showbase.product.list.search.RemoteProductListSearchParam;
import org.docksidestage.remote.maihama.showbase.product.list.search.RemoteProductListSearchReturn;

/**
 * RemoteMaihamaShowbaseBsProductBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsProductBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /***
     * @param requestManager requestManager. (NotNull)
     */
    public RemoteMaihamaShowbaseBsProductBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Detail.<br>
     * <pre>
     * url: /product/detail/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId productId. (NotNull)
     * @return RemoteProductDetailReturn. (NotNull)
     */
    protected RemoteProductDetailReturn requestDetail(Integer productId) {
        return doRequestPost(new ParameterizedRef<RemoteProductDetailReturn>() {
        }.getType(), "/product/detail/{productId}", moreUrl(productId), null, rule -> {});
    }

    /**
     * ListSearch.<br>
     * <pre>
     * url: /product/list/search/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber pageNumber. (NotNull)
     * @param paramLamda The callback for RemoteProductListSearchParam. (NotNull)
     * @return RemoteProductListSearchReturn. (NotNull)
     */
    protected RemoteProductListSearchReturn requestListSearch(Integer pageNumber, Consumer<RemoteProductListSearchParam> paramLamda) {
        RemoteProductListSearchParam param = new RemoteProductListSearchParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<RemoteProductListSearchReturn>() {
        }.getType(), "/product/list/search/{pageNumber}", moreUrl(pageNumber), param, rule -> {});
    }

    /**
     * ListStatus.<br>
     * <pre>
     * url: /product/list/status
     * httpMethod: POST
     * </pre>
     * @return java.util.List<Object>. (NotNull)
     */
    protected java.util.List<Object> requestListStatus() {
        return doRequestPost(new ParameterizedRef<java.util.List<Object>>() {
        }.getType(), "/product/list/status", noMoreUrl(), null, rule -> {});
    }
}
