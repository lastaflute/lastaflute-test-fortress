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
package org.docksidestage.app.web.wx.remoteapi.rmharbor;

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
import org.docksidestage.remote.harbor.lido.mypage.RemoteHbLidoMypageProductReturn;
import org.docksidestage.remote.harbor.lido.product.RemoteHbLidoProductRowReturn;
import org.docksidestage.remote.harbor.lido.product.RemoteHbLidoProductSearchParam;
import org.docksidestage.remote.harbor.lido.signin.RemoteHbLidoSigninParam;
import org.docksidestage.remote.harbor.serh.signin.RemoteHbSerhSigninParam;
import org.docksidestage.remote.harbor.serh.signin.RemoteHbSerhSigninParam.TestingFormPropHasToPart;
import org.docksidestage.remote.harbor.serh.signin.RemoteHbSerhSigninParam.TestingFormPropNoToPart;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.login.exception.LoginFailureException;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRemoteapiRmharborAction extends FortressBaseAction {

    @Resource
    private RemoteHarborBhv harborBhv;
    @Resource
    private MemberBhv memberBhv;
    @Resource
    private PagingAssist pagingAssist;

    // ===================================================================================
    //                                                                        Lido Execute
    //                                                                        ============
    // -----------------------------------------------------
    //                                           Lido Signin
    //                                           -----------
    // http://localhost:8151/fortress/wx/remoteapi/rmharbor/lido/signin/?account=Pixy&password=sea
    // http://localhost:8151/fortress/wx/remoteapi/rmharbor/lido/signin/?account=Pixy&password=land
    @Execute(urlPattern = "@word/@word")
    public JsonResponse<Void> lidoSignin(WxRmharborSigninForm form) {
        validateApi(form, messages -> {});
        RemoteHbLidoSigninParam param = new RemoteHbLidoSigninParam();
        param.account = form.account;
        param.password = form.password;
        try {
            harborBhv.requestLidoAuthSignin(param); // actually, embed this to login assist
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
    //                                           Lido MyPage
    //                                           -----------
    // http://localhost:8151/fortress/wx/remoteapi/rmharbor/lido/mypage/
    @Execute(urlPattern = "@word/@word")
    public JsonResponse<List<RemoteHbLidoMypageProductReturn>> lidoMypage() {
        List<RemoteHbLidoMypageProductReturn> retList = harborBhv.requestLidoMypage();
        return asJson(retList);
    }

    // ===================================================================================
    //                                                                  ServerHTML Execute
    //                                                                  ==================
    // -----------------------------------------------------
    //                                    ServerHTML Product
    //                                    ------------------
    // http://localhost:8151/fortress/wx/remoteapi/rmharbor/serh/product/?productName=S
    // http://localhost:8151/fortress/wx/remoteapi/rmharbor/serh/product/?productName=SeaLandPiariBonvo
    @Execute(urlPattern = "@word/@word")
    public HtmlResponse serhProduct(WxRmharborProductSearchForm form) { // can translate validation error automatically
        validate(form, messages -> {}, () -> {
            return asHtml(path_Product_ProductListHtml);
        });
        RemoteHbPagingReturn<RemoteHbLidoProductRowReturn> ret = requestProductList(form);
        PagingResultBean<Product> page = mappingToPage(ret);
        List<WxRmharborProductSearchRowBean> beans = ret.rows.stream().map(row -> {
            return mappingToRowBean(row);
        }).collect(Collectors.toList());
        return asHtml(path_Product_ProductListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    private RemoteHbPagingReturn<RemoteHbLidoProductRowReturn> requestProductList(WxRmharborProductSearchForm form) {
        RemoteHbLidoProductSearchParam param = new RemoteHbLidoProductSearchParam();
        param.productName = form.productName;
        return harborBhv.requestLidoProductList(param);
    }

    private WxRmharborProductSearchRowBean mappingToRowBean(RemoteHbLidoProductRowReturn row) {
        WxRmharborProductSearchRowBean bean = new WxRmharborProductSearchRowBean();
        bean.productId = row.productId;
        bean.productName = row.productName;
        bean.productStatus = row.productStatusName;
        bean.productCategory = "unknown category";
        bean.regularPrice = row.regularPrice;
        return bean;
    }

    private PagingResultBean<Product> mappingToPage(RemoteHbPagingReturn<RemoteHbLidoProductRowReturn> ret) {
        PagingResultBean<Product> page = new PagingResultBean<Product>(); // dummy generics
        page.setAllRecordCount(ret.allRecordCount);
        page.setCurrentPageNumber(ret.currentPageNumber);
        page.setPageSize(ret.pageSize);
        page.add(new Product()); // dummy data
        return page;
    }

    // -----------------------------------------------------
    //                                     ServerHTML Signin
    //                                     -----------------
    // http://localhost:8151/fortress/wx/remoteapi/rmharbor/serh/signin/?account=Pixy&password=sea
    // http://localhost:8151/fortress/wx/remoteapi/rmharbor/serh/signin/?account=Pixy&password=land
    @Execute(urlPattern = "@word/@word")
    public JsonResponse<Void> serhSignin(WxRmharborSigninForm form) {
        validateApi(form, messages -> {});
        RemoteHbSerhSigninParam param = new RemoteHbSerhSigninParam();
        param.account = form.account;
        param.password = form.password;

        TestingFormPropHasToPart hasToPart = new TestingFormPropHasToPart();
        hasToPart.hangar = "mystic";
        param.testingFormPropHasTo = hasToPart;

        TestingFormPropNoToPart noToPart = new TestingFormPropNoToPart();
        noToPart.showbase = "oneman";
        param.testingFormPropNoTo = noToPart;

        harborBhv.requestSerhSignin(param); // actually, embed this to login assist
        return JsonResponse.asEmptyBody();
    }
}
