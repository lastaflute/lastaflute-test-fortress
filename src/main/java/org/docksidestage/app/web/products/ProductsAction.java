/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.app.web.products;

import java.util.List;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.products.assist.ProductsCrudAssist;
import org.docksidestage.app.web.products.assist.ProductsMappingAssist;
import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class ProductsAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private ProductsCrudAssist productsCrudAssist;
    @Resource
    private ProductsMappingAssist productsMappingAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // /products/
    //
    // *it needs "list" in method, which is resolved by ActionAdjustmentProvider
    // http://localhost:8151/fortress/products/?productName=R
    // _/_/_/_/_/_/_/_/_/_/
    @Execute
    public JsonResponse<ProductsListResult> get$list(ProductsListForm form) {
        validateApi(form, messages -> {});
        List<Product> productList = selectProductList(form);
        ProductsListResult result = mappingToListResult(productList);
        return asJson(result);
    }

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // /products/1
    //
    // http://localhost:8151/fortress/products/1/
    // _/_/_/_/_/_/_/_/_/_/
    @Execute
    public JsonResponse<ProductsOneResult> get$index(Integer productId) {
        Product product = selectProductById(productId);
        ProductsOneResult result = mappingToOneResult(product);
        return asJson(result);
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private List<Product> selectProductList(ProductsListForm form) {
        return productsCrudAssist.selectProductList(form);
    }

    private Product selectProductById(Integer productId) {
        return productsCrudAssist.selectProductById(productId);
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private ProductsListResult mappingToListResult(List<Product> productList) {
        return productsMappingAssist.mappingToListResult(productList);
    }

    private ProductsOneResult mappingToOneResult(Product product) {
        return productsMappingAssist.mappingToOneResult(product);
    }
}
