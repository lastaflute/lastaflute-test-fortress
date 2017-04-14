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
package org.docksidestage.app.web.products;

import java.util.List;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.app.web.base.view.DisplayAssist;
import org.docksidestage.app.web.product.ProductDetailBean;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.core.util.LaStringUtil;
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
    private PagingAssist pagingAssist;
    @Resource
    private DisplayAssist displayAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Execute // #making
    public HtmlResponse get$index(OptionalThing<Integer> productId, ProductsSearchForm form) {
        if (productId.isPresent()) {
            Product product = selectProduct(productId.get());
            return asHtml(path_Product_ProductDetailHtml).renderWith(data -> {
                data.register("product", mappingToDetailBean(product));
            });
        } else {
            validate(form, messages -> {}, () -> {
                return asHtml(path_Product_ProductListHtml);
            });
            PagingResultBean<Product> page = selectProductPage(form.pageNumber != null ? form.pageNumber : 1, form);
            List<ProductsSearchRowBean> beans = page.mappingList(product -> {
                return mappingToRowBean(product);
            });
            return asHtml(path_Product_ProductListHtml).renderWith(data -> {
                data.register("beans", beans);
                pagingAssist.registerPagingNavi(data, page, form);
            });
        }
    }

    @Execute(urlPattern = "{}/@word")
    public HtmlResponse get$purchase(Integer productId) {
        return asHtml(path_Product_ProductListHtml); // #making
    }

    @Execute(urlPattern = "{}/@word/@word")
    public HtmlResponse post$purchaseConfirm(Integer productId) {
        return asHtml(path_Product_ProductListHtml); // #making
    }

    @Execute(urlPattern = "{}/@word/@word")
    public HtmlResponse post$purchaseDone(Integer productId) {
        return asHtml(path_Product_ProductListHtml); // #making
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private Product selectProduct(int productId) {
        return productBhv.selectEntity(cb -> {
            cb.setupSelect_ProductCategory();
            cb.query().setProductId_Equal(productId);
        }).get();
    }

    private PagingResultBean<Product> selectProductPage(int pageNumber, ProductsSearchForm form) {
        verifyOrClientError("The pageNumber should be positive number: " + pageNumber, pageNumber > 0);
        return productBhv.selectPage(cb -> {
            cb.ignoreNullOrEmptyQuery();
            cb.setupSelect_ProductStatus();
            cb.setupSelect_ProductCategory();
            cb.specify().derivedPurchase().max(purchaseCB -> {
                purchaseCB.specify().columnPurchaseDatetime();
            }, Product.ALIAS_latestPurchaseDate);
            cb.query().setProductName_LikeSearch(form.productName, op -> op.likeContain());
            final String purchaseMemberName = form.purchaseMemberName;
            if (LaStringUtil.isNotEmpty(purchaseMemberName)) {
                cb.query().existsPurchase(purchaseCB -> {
                    purchaseCB.query().queryMember().setMemberName_LikeSearch(purchaseMemberName, op -> op.likeContain());
                });
            }
            cb.query().setProductStatusCode_Equal_AsProductStatus(form.productStatus);
            cb.query().addOrderBy_ProductName_Asc();
            cb.query().addOrderBy_ProductId_Asc();
            cb.paging(4, pageNumber);
        });
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private ProductDetailBean mappingToDetailBean(Product product) {
        ProductDetailBean bean = new ProductDetailBean();
        bean.productId = product.getProductId();
        bean.productName = product.getProductName();
        bean.regularPrice = product.getRegularPrice();
        bean.productHandleCode = product.getProductHandleCode();
        product.getProductCategory().alwaysPresent(category -> {
            bean.categoryName = category.getProductCategoryName();
        });
        return bean;
    }

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
        bean.latestPurchaseDate = displayAssist.toDate(product.getLatestPurchaseDate()).orElse(null);
        return bean;
    }
}
