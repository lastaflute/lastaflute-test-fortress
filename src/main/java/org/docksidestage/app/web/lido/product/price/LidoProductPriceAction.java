/*
 * Copyright 2015-2022 the original author or authors.
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
package org.docksidestage.app.web.lido.product.price;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class LidoProductPriceAction extends FortressBaseAction {

    @Resource
    private ProductBhv productBhv;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Execute
    public JsonResponse<Void> update(Integer productId, ProductPriceUpdateBody body) {
        validateApi(body, messages -> {});
        updateRegularPrice(productId, body);
        return JsonResponse.asEmptyBody();
    }

    private void updateRegularPrice(Integer productId, ProductPriceUpdateBody body) {
        Product product = new Product();
        product.setProductId(productId);
        product.setRegularPrice(body.regularPrice);
        productBhv.updateNonstrict(product);
    }
}
