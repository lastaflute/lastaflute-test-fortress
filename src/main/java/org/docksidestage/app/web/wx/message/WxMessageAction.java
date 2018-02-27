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
package org.docksidestage.app.web.wx.message;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxMessageAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private ProductBhv productBhv;
    @Resource
    private PagingAssist pagingAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/message/
    @Execute
    public HtmlResponse index(OptionalThing<Integer> pageNumber, WxMessageForm form) {
        validate(form, messages -> {
            messages.addConstraintsLengthMessage(GLOBAL, "3", "6");
            messages.addWhiteboxMessageValuesIndexed(GLOBAL, "mystic", "oneman", "angel");
            messages.addWhiteboxMessageValuesNamed(GLOBAL, "angel", "mystic", "oneman");
            messages.addWhiteboxMessageValuesReversed(GLOBAL, "angel", "mystic", "max", "mou", "oneman");
            messages.addWhiteboxMessageValuesSkipped(GLOBAL, "mystic", "oneman");
            messages.addWhiteboxMessageValuesVarious(GLOBAL, "mou", "mystic", "angel", "oneman");
        }, () -> {
            return asHtml(path_WxMessage_WxMessageListHtml);
        });
        PagingResultBean<Product> page = selectProductPage(pageNumber.orElse(1), form);
        List<WxMessageRowBean> beans = page.stream().map(product -> {
            return mappingToBean(product);
        }).collect(Collectors.toList());
        return asHtml(path_WxMessage_WxMessageListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    // #hope showDerived() as JsonResponse by jflute (2016/08/08)

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private PagingResultBean<Product> selectProductPage(int pageNumber, WxMessageForm form) {
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

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private WxMessageRowBean mappingToBean(Product product) {
        WxMessageRowBean bean = new WxMessageRowBean();
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
}
