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
package org.docksidestage.remote.maihama.showbase.products;

import java.util.function.Consumer;

import org.lastaflute.di.helper.misc.ParameterizedRef;
import org.lastaflute.web.servlet.request.RequestManager;

import org.docksidestage.remote.maihama.showbase.RemoteAbstractMaihamaShowbaseBhv;
import org.docksidestage.remote.maihama.showbase.products.RemoteProductsParam;

/**
 * RemoteMaihamaShowbaseBsProductsBhv.
 * @author FreeGen
 */
public abstract class RemoteMaihamaShowbaseBsProductsBhv extends RemoteAbstractMaihamaShowbaseBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public RemoteMaihamaShowbaseBsProductsBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Products.<br>
     * <pre>
     * url: /products/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId productId
     * @param paramLamda The callback for RemoteProductsParam
     * @return java.util.Map<String, Object>
     */
    protected java.util.Map<String, Object> requestProducts(Integer productId, Consumer<RemoteProductsParam> paramLamda) {
        RemoteProductsParam param = new RemoteProductsParam();
        paramLamda.accept(param);
        return doRequestPost(new ParameterizedRef<java.util.Map<String, Object>>() {
        }.getType(), "/products/{productId}", moreUrl(productId), param, op -> {});
    }
}
