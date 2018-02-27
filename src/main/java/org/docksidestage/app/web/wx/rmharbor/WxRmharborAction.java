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
package org.docksidestage.app.web.wx.rmharbor;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.remoteapi.exception.RemoteApiHttpClientErrorException;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exentity.Product;
import org.docksidestage.remote.harbor.RemoteHarborBhv;
import org.docksidestage.remote.harbor.base.RemoteHbPagingReturn;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult;
import org.docksidestage.remote.harbor.base.RemoteHbUnifiedFailureResult.RemoteUnifiedFailureType;
import org.docksidestage.remote.harbor.mypage.RemoteHbMypageProductReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductSearchParam;
import org.docksidestage.remote.harbor.signin.RemoteHbSigninParam;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.login.exception.LoginFailureException;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRmharborAction extends FortressBaseAction {

    @Resource
    private RemoteHarborBhv harborBhv;
    @Resource
    private MemberBhv memberBhv;
    @Resource
    private PagingAssist pagingAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // -----------------------------------------------------
    //                                                Simple
    //                                                ------
    // http://localhost:8151/fortress/wx/rmharbor/mypage/
    @Execute
    public JsonResponse<List<RemoteHbMypageProductReturn>> mypage() {
        List<RemoteHbMypageProductReturn> retList = harborBhv.requestMypage();
        return asJson(retList);
    }

    // -----------------------------------------------------
    //                                                Signin
    //                                                ------
    // http://localhost:8151/fortress/wx/rmharbor/signin/?account=Pixy&password=sea
    // http://localhost:8151/fortress/wx/rmharbor/signin/?account=Pixy&password=land
    @Execute
    public JsonResponse<Void> signin(WxRmharborSigninForm form) {
        validateApi(form, messages -> {});
        RemoteHbSigninParam param = new RemoteHbSigninParam();
        param.account = form.account;
        param.password = form.password;
        try {
            harborBhv.requestSignin(param); // actually, embed this to login assist
        } catch (RemoteApiHttpClientErrorException e) {
            if (e.getHttpStatus() == 400) {
                RemoteHbUnifiedFailureResult result = (RemoteHbUnifiedFailureResult) e.getFailureResponse().get();
                if (RemoteUnifiedFailureType.LOGIN_FAILURE.equals(result.cause)) {
                    throw new LoginFailureException("Cannot login by the parameter: " + param, e);
                }
            }
        }
        return JsonResponse.asEmptyBody();
    }

    // -----------------------------------------------------
    //                                              Products
    //                                              --------
    // http://localhost:8151/fortress/wx/rmharbor/products/?productName=S
    // http://localhost:8151/fortress/wx/rmharbor/products/?productName=SeaLandPiariBonvo
    @Execute
    public HtmlResponse products(WxRmharborProductSearchForm form) { // can translate validation error automatically
        validate(form, messages -> {}, () -> {
            return asHtml(path_Product_ProductListHtml);
        });
        RemoteHbPagingReturn<RemoteHbProductRowReturn> ret = requestProductList(form);
        PagingResultBean<Product> page = mappingToPage(ret);
        List<WxRmharborProductSearchRowBean> beans = ret.rows.stream().map(row -> {
            return mappingToRowBean(row);
        }).collect(Collectors.toList());
        return asHtml(path_Product_ProductListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    private RemoteHbPagingReturn<RemoteHbProductRowReturn> requestProductList(WxRmharborProductSearchForm form) {
        RemoteHbProductSearchParam param = new RemoteHbProductSearchParam();
        param.productName = form.productName;
        return harborBhv.requestProductList(param);
    }

    private WxRmharborProductSearchRowBean mappingToRowBean(RemoteHbProductRowReturn row) {
        WxRmharborProductSearchRowBean bean = new WxRmharborProductSearchRowBean();
        bean.productId = row.productId;
        bean.productName = row.productName;
        bean.productStatus = row.productStatusName;
        bean.productCategory = "unknown category";
        bean.regularPrice = row.regularPrice;
        return bean;
    }

    private PagingResultBean<Product> mappingToPage(RemoteHbPagingReturn<RemoteHbProductRowReturn> ret) {
        PagingResultBean<Product> page = new PagingResultBean<Product>(); // dummy generics
        page.setAllRecordCount(ret.allRecordCount);
        page.setCurrentPageNumber(ret.currentPageNumber);
        page.setPageSize(ret.pageSize);
        page.add(new Product()); // dummy data
        return page;
    }
}
