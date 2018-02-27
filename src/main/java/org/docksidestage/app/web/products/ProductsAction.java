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
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exbhv.ProductStatusBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class ProductsAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private ProductBhv productBhv;
    @Resource
    private ProductStatusBhv productStatusBhv;
    @Resource
    private PagingAssist pagingAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // /products/
    @Execute
    public HtmlResponse get$index(ProductsSearchForm form) {
        validate(form, messages -> {}, () -> {
            return asHtml(path_Product_ProductListHtml);
        });
        PagingResultBean<Product> page = selectProductPage(form);
        List<ProductsSearchRowBean> beans = page.stream().map(product -> {
            return mappingToRowBean(product);
        }).collect(Collectors.toList());
        return asHtml(path_Product_ProductListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    // /products/1 (mapping to /products/detail/1 by ActionAdjustmentProvider)
    @Execute
    public HtmlResponse get$detail(Integer productId) {
        Product product = selectProduct(productId);
        ProductsDetailBean bean = mappingToDetailBean(product);
        return asHtml(path_Product_ProductDetailHtml).renderWith(data -> {
            data.register("product", bean);
        });
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private PagingResultBean<Product> selectProductPage(ProductsSearchForm form) {
        int pageNumber = form.pageNumber != null ? form.pageNumber : 1;
        verifyOrClientError("The pageNumber should be positive number: " + pageNumber, pageNumber > 0);
        return productBhv.selectPage(cb -> {
            cb.setupSelect_ProductStatus();
            cb.setupSelect_ProductCategory();
            cb.specify().derivedPurchase().max(purchaseCB -> {
                purchaseCB.specify().columnPurchaseDatetime();
            }, Product.ALIAS_latestPurchaseDate);
            if (form.productName != null) {
                cb.query().setProductName_LikeSearch(form.productName, op -> op.likeContain());
            }
            if (form.purchaseMemberName != null) {
                cb.query().existsPurchase(purchaseCB -> {
                    purchaseCB.query().queryMember().setMemberName_LikeSearch(form.purchaseMemberName, op -> op.likeContain());
                });
            }
            if (form.productStatus != null) {
                cb.query().setProductStatusCode_Equal_AsProductStatus(form.productStatus);
            }
            cb.query().addOrderBy_ProductName_Asc();
            cb.query().addOrderBy_ProductId_Asc();
            cb.paging(4, pageNumber);
        });
    }

    private Product selectProduct(int productId) {
        return productBhv.selectEntity(cb -> {
            cb.setupSelect_ProductCategory();
            cb.query().setProductId_Equal(productId);
        }).get();
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private ProductsSearchRowBean mappingToRowBean(Product product) {
        ProductsSearchRowBean bean = new ProductsSearchRowBean();
        bean.productId = product.getProductId();
        bean.productName = product.getProductName();
        product.getProductStatus().alwaysPresent(status -> {
            bean.productStatus = status.getProductStatusName();
        });
        product.getProductCategory().alwaysPresent(category -> {
            bean.productCategory = category.getProductCategoryName();
        });
        bean.regularPrice = product.getRegularPrice();
        bean.latestPurchaseDate = product.getLatestPurchaseDate();
        return bean;
    }

    private ProductsDetailBean mappingToDetailBean(Product product) {
        ProductsDetailBean bean = new ProductsDetailBean();
        bean.productId = product.getProductId();
        bean.productName = product.getProductName();
        bean.regularPrice = product.getRegularPrice();
        bean.productHandleCode = product.getProductHandleCode();
        product.getProductCategory().alwaysPresent(category -> {
            bean.categoryName = category.getProductCategoryName();
        });
        return bean;
    }
}
