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
package org.docksidestage.app.web.wx.validator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.app.web.wx.validator.WxValidatorGenericForm.WhiteboxValidatorGenericElementBean;
import org.docksidestage.app.web.wx.validator.WxValidatorGroupingBean.LandValidationGroup;
import org.docksidestage.app.web.wx.validator.WxValidatorWholeBean.WhiteboxValidatorWholeElementBean;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exbhv.ProductStatusBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.core.util.LaStringUtil;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxValidatorAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(WxValidatorAction.class);

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
    // -----------------------------------------------------
    //                                                 HTML
    //                                                ------
    // [success]
    // http://localhost:8151/fortress/wx/validator/?seaInteger=1&seaFloat=2.3&landDate=2015-12-12&piariPrimBool=true&bonvoStatus=FML
    //
    // [validation error]
    // http://localhost:8151/fortress/wx/validator/?seaInteger=a&seaFloat=a.b&landDate=2015-12-1a&piariPrimBool=a&bonvoStatus=ABC
    // http://localhost:8151/fortress/wx/validator/?seaInteger=mystic
    //
    // [not yet]
    // http://localhost:8151/fortress/wx/validator/?dstoreIntegerList=1&dstoreIntegerList=mystic
    //  => 400 bad request
    @Execute
    public HtmlResponse index(WxValidatorForm form) {
        logger.debug("#type_failure Properties: {}", form);
        validate(form, messages -> {}, () -> {
            return asHtml(path_WxValidator_WxValidatorListHtml);
        });
        PagingResultBean<Product> page = selectProductPage(1, form);
        List<WxValidatorRowBean> beans = page.mappingList(product -> {
            return mappingToBean(product);
        });
        return asHtml(path_WxValidator_WxValidatorListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    @Execute
    public HtmlResponse genericform(WxValidatorGenericForm<WhiteboxValidatorGenericElementBean> form) {
        return asHtml(path_WxValidator_WxValidatorListHtml);
    }

    // -----------------------------------------------------
    //                                                 JSON
    //                                                ------
    @Execute
    public JsonResponse<WxValidatorGroupingBean> groupingjson(boolean grouping) {
        WxValidatorGroupingBean bean = new WxValidatorGroupingBean(grouping);
        return asJson(bean).groupValidator(grouping ? LandValidationGroup.class : null);
    }

    @Execute
    public JsonResponse<List<WxValidatorListElementBean>> listjson(ValidatedJsonBody body) {
        validateApi(body, messages -> {});
        List<WxValidatorListElementBean> beans = new ArrayList<WxValidatorListElementBean>();
        beans.add(new WxValidatorListElementBean(1, "oneman"));
        return asJson(beans);
    }

    @Execute
    public JsonResponse<WxValidatorWholeBean<WhiteboxValidatorWholeElementBean>> wholejson(ValidatedJsonBody body) {
        validateApi(body, messages -> {});
        WxValidatorWholeBean<WhiteboxValidatorWholeElementBean> whole = new WxValidatorWholeBean<>(1, null);
        whole.data = new WhiteboxValidatorWholeElementBean(4);
        return asJson(whole);
    }

    public static class ValidatedJsonBody extends WxValidatorForm {
    }

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    private PagingResultBean<Product> selectProductPage(int pageNumber, WxValidatorForm form) {
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
    private WxValidatorRowBean mappingToBean(Product product) {
        WxValidatorRowBean bean = new WxValidatorRowBean();
        bean.productId = product.getProductId();
        bean.productName = product.getProductName();
        bean.regularPrice = product.getRegularPrice();
        bean.registerDatetime = product.getRegisterDatetime();
        product.getProductStatus().alwaysPresent(status -> {
            bean.productStatusName = status.getProductStatusName();
        });
        product.getProductCategory().alwaysPresent(category -> {
            bean.productCategoryName = category.getProductCategoryName();
        });
        return bean;
    }
}
