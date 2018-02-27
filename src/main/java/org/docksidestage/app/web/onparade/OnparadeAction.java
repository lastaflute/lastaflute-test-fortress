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
package org.docksidestage.app.web.onparade;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exbhv.ProductStatusBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.mail.member.WelcomeMemberPostcard;
import org.docksidestage.mylasta.mail.whitebox.WxFaxAttachment;
import org.docksidestage.mylasta.template.bean.WxBasicTemplateBean;
import org.lastaflute.core.mail.Postbox;
import org.lastaflute.core.template.TemplateManager;
import org.lastaflute.core.util.LaStringUtil;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class OnparadeAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(OnparadeAction.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private Postbox postbox;
    @Resource
    private TemplateManager templateManager;
    @Resource
    private FortressConfig fortressConfig;
    @Resource
    private ProductBhv productBhv;
    @Resource
    private ProductStatusBhv productStatusBhv;
    @Resource
    private PagingAssist pagingAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // /product/list/3?sea=land&aaa=bbb
    @Execute
    public HtmlResponse index(OptionalThing<Integer> pageNumber, OnparadeSearchForm form) {
        logger.debug("#form");
        logger.debug(" form.nested: {}", form.nested);
        logger.debug(" form.nestedList: {}", form.nestedList);
        logger.debug(" form.fields: {}", Arrays.asList(form.fields.get("label")));
        // test for now by jflute
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // requiresNew(tx -> {
        // tx.returns(memberList);
        // });
        productBhv.selectList(cb -> {
            cb.query().setProductName_LikeSearch("S", op -> op.likePrefix());
            cb.query().addOrderBy_ProductCategoryCode_Desc();
        });
        WelcomeMemberPostcard.droppedInto(postbox, postcard -> {
            postcard.setFrom("sea@example.com", FortressMessages.LABELS_ADD);
            postcard.addTo("land@example.com");
            postcard.setMemberName("sea");
            postcard.pushLogging("aaa", "bbb");
            postcard.addReplyTo("rep@example.com");
        });

        String fax = WxFaxAttachment.parsedBy(templateManager, bean -> {
            bean.setSea("aa");
            bean.setLand("");
            bean.setIks(7);
        });
        System.out.println("fax: " + fax);
        String nested = WxBasicTemplateBean.parsedBy(templateManager, bean -> {
            bean.setSea("aa");
            bean.setLand("");
        });
        System.out.println("nested: " + nested);
        // _/_/_/_/_/_/_/_/_/_/

        validate(form, messages -> {}, () -> {
            return asHtml(path_Onparade_OnparadeHtml);
        });
        PagingResultBean<Product> page = selectProductPage(pageNumber.orElse(1), form);
        List<OnparadeSearchRowBean> beans = page.mappingList(product -> {
            return mappingToBean(product);
        });
        return asHtml(path_Onparade_OnparadeHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
        // return asHtml(path_Product_ProductListJsp).renderWith(data -> {
        // data.register("beans", beans);
        // registerPagingNavi(data, page, form);
        // });
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private PagingResultBean<Product> selectProductPage(int pageNumber, OnparadeSearchForm form) {
        verifyOrClientError("The pageNumber should be positive number: " + pageNumber, pageNumber > 0);
        return productBhv.selectPage(cb -> {
            cb.ignoreNullOrEmptyQuery();
            cb.setupSelect_ProductStatus();
            cb.setupSelect_ProductCategory();
            cb.specify().derivedPurchase().count(purchaseCB -> {
                purchaseCB.specify().columnPurchaseId();
            }, Product.ALIAS_purchaseCount);
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
    private OnparadeSearchRowBean mappingToBean(Product product) {
        OnparadeSearchRowBean bean = new OnparadeSearchRowBean();
        bean.productId = product.getProductId();
        bean.productName = product.getProductName();
        product.getProductStatus().alwaysPresent(status -> {
            bean.productStatusName = status.getProductStatusName();
        });
        bean.regularPrice = product.getRegularPrice();
        return bean;
    }
}
