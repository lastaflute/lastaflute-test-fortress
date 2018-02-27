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
package org.docksidestage.app.web.wx.transaction;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.app.web.base.view.DisplayAssist;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.util.LaStringUtil;
import org.lastaflute.db.jta.stage.TransactionStage;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.servlet.request.ResponseManager;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxTransactionMemoriesAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private AsyncManager asyncManager;
    @Resource
    private TransactionStage transactionStage;
    @Resource
    private ResponseManager responseManager;
    @Resource
    private ProductBhv productBhv;
    @Resource
    private PagingAssist pagingAssist;
    @Resource
    private DisplayAssist displayAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Execute
    public HtmlResponse fail(WxTransactionMemoriesForm form) {
        validate(form, messages -> {}, () -> {
            return asHtml(path_Product_ProductListHtml);
        });
        PagingResultBean<Product> page = selectProductPage(1, form);
        List<WxTransactionMemoriesRowBean> beans = page.mappingList(product -> {
            return mappingToBean(product);
        });
        productBhv.selectByPK(1).alwaysPresent(product -> {
            productBhv.updateNonstrict(product);
            product.uniqueBy(product.getProductHandleCode());
            productBhv.updateNonstrict(product);
        });
        transactionStage.requiresNew(tx -> {
            productBhv.selectCursor(cb -> {}, product -> {});
            productBhv.selectScalar(LocalDate.class).max(cb -> {
                cb.specify().columnRegisterDatetime();
            });
            transactionStage.requiresNew(txx -> {
                productBhv.selectCursor(cb -> {}, product -> {});
                if (true) {
                    throw new IllegalStateException("sea");
                }
            });
        });
        return asHtml(path_Product_ProductListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    @Execute
    public HtmlResponse failasync(WxTransactionMemoriesForm form) {
        validate(form, messages -> {}, () -> {
            return asHtml(path_Product_ProductListHtml);
        });
        PagingResultBean<Product> page = selectProductPage(1, form);
        List<WxTransactionMemoriesRowBean> beans = page.mappingList(product -> {
            return mappingToBean(product);
        });
        productBhv.selectByPK(1).alwaysPresent(product -> {
            productBhv.updateNonstrict(product);
        });
        asyncManager.async(() -> {
            transactionStage.requiresNew(tx -> {
                selectProductPage(1, form);
                productBhv.selectCursor(cb -> {}, product -> {});
                productBhv.selectScalar(LocalDate.class).max(cb -> {
                    cb.specify().columnRegisterDatetime();
                });
                transactionStage.requiresNew(txx -> {
                    productBhv.selectCursor(cb -> {}, product -> {});
                    if (true) {
                        throw new IllegalStateException("sea", new IllegalStateException("land"));
                    }
                });
            });
        });
        return asHtml(path_Product_ProductListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private PagingResultBean<Product> selectProductPage(int pageNumber, WxTransactionMemoriesForm form) {
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
    private WxTransactionMemoriesRowBean mappingToBean(Product product) {
        WxTransactionMemoriesRowBean bean = new WxTransactionMemoriesRowBean();
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
