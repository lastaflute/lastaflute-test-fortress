/*
 * Copyright 2015-2024 the original author or authors.
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
import org.lastaflute.web.Execute.HttpStatus;
import org.lastaflute.web.RestfulAction;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.util.LaActionRuntimeUtil;

/**
 * @author jflute
 */
@AllowAnyoneAccess
@RestfulAction(allowEventSuffix = true, eventSuffixHyphenate = { "hangar-mystic", "showbase-oneman" })
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
    // http://localhost:8151/fortress/products/?productName=R
    // _/_/_/_/_/_/_/_/_/_/
    @Execute
    public JsonResponse<List<ProductsRowResult>> get$index(ProductsSearchForm form) {
        validateApi(form, messages -> {});
        List<Product> productList = productsCrudAssist.selectProductList(form);
        List<ProductsRowResult> listResult = productsMappingAssist.mappingToListResult(productList);
        return asJson(listResult);
    }

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // /products/1
    //
    // http://localhost:8151/fortress/products/1/
    // _/_/_/_/_/_/_/_/_/_/
    @Execute
    public JsonResponse<ProductsResult> get$index(Integer productId) {
        Product product = productsCrudAssist.selectProductById(productId);
        ProductsResult singleResult = productsMappingAssist.mappingToSingleResult(product);
        return asJson(singleResult);
    }

    @Execute
    public JsonResponse<Void> post$index(ProductsPostBody body) {
        validateApi(body, messages -> {});
        return JsonResponse.asEmptyBody(); // dummy implementation
    }

    @Execute
    public JsonResponse<Void> put$index(Integer productId, ProductsPutBody body) {
        validateApi(body, messages -> {});
        return JsonResponse.asEmptyBody(); // dummy implementation
    }

    @Execute(successHttpStatus = @HttpStatus(value = 299, desc = "sea")) // test for explicit status
    public JsonResponse<Void> delete$index(Integer productId) {
        return JsonResponse.asEmptyBody(); // dummy implementation
    }

    // -----------------------------------------------------
    //                                   Test of EventSuffix
    //                                   -------------------
    // (problem "Restful action but non-restful mapping" until 1.2.1, fixed @since 1.2.2)
    // http://localhost:8151/fortress/products/sea?productName=S
    @Execute
    public JsonResponse<List<ProductsRowResult>> get$sea(ProductsSearchForm form) {
        validateApi(form, messages -> {});
        List<Product> productList = productsCrudAssist.selectProductList(form);
        List<ProductsRowResult> listResult = productsMappingAssist.mappingToListResult(productList);
        listResult.forEach(row -> row.productName = currentMethodName() + " called");
        return asJson(listResult);
    }

    // http://localhost:8151/fortress/products/docksideOver?productName=S
    // cannot use hyphen because of no setting in annotation
    // x http://localhost:8151/fortress/products/dockside-over?productName=S
    @Execute
    public JsonResponse<List<ProductsRowResult>> get$docksideOver(ProductsSearchForm form) {
        validateApi(form, messages -> {});
        List<Product> productList = productsCrudAssist.selectProductList(form);
        List<ProductsRowResult> listResult = productsMappingAssist.mappingToListResult(productList);
        listResult.forEach(row -> row.productName = currentMethodName() + " called");
        return asJson(listResult);
    }

    // http://localhost:8151/fortress/products/hangar-mystic?productName=S
    // also direct camel-case is mapped for now
    // v http://localhost:8151/fortress/products/hangarMystic?productName=S
    @Execute
    public JsonResponse<List<ProductsRowResult>> get$hangarMystic(ProductsSearchForm form) {
        validateApi(form, messages -> {});
        List<Product> productList = productsCrudAssist.selectProductList(form);
        List<ProductsRowResult> listResult = productsMappingAssist.mappingToListResult(productList);
        listResult.forEach(row -> row.productName = currentMethodName() + " called");
        return asJson(listResult);
    }

    // POST
    @Execute
    public JsonResponse<List<ProductsRowResult>> post$hangarMystic(ProductsSearchForm form) {
        validateApi(form, messages -> {});
        List<Product> productList = productsCrudAssist.selectProductList(form);
        List<ProductsRowResult> listResult = productsMappingAssist.mappingToListResult(productList);
        listResult.forEach(row -> row.productName = currentMethodName() + " called");
        return asJson(listResult);
    }

    // http://localhost:8151/fortress/products/1/land
    @Execute
    public JsonResponse<ProductsResult> get$land(Integer productId) {
        Product product = productsCrudAssist.selectProductById(productId);
        ProductsResult singleResult = productsMappingAssist.mappingToSingleResult(product);
        singleResult.productName = currentMethodName() + " called";
        return asJson(singleResult);
    }

    // http://localhost:8151/fortress/products/1/showbase-oneman
    // also direct camel-case is mapped for now
    // v http://localhost:8151/fortress/products/1/showbaseOneman
    @Execute
    public JsonResponse<ProductsResult> get$showbaseOneman(Integer productId) {
        Product product = productsCrudAssist.selectProductById(productId);
        ProductsResult singleResult = productsMappingAssist.mappingToSingleResult(product);
        singleResult.productName = currentMethodName() + " called";
        return asJson(singleResult);
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private String currentMethodName() {
        return LaActionRuntimeUtil.getActionRuntime().getActionExecute().getExecuteMethod().getName() + "()";
    }
}
