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
package org.docksidestage.app.web.wx.rmhangar;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.paging.PagingAssist;
import org.docksidestage.dbflute.exentity.Product;
import org.docksidestage.remote.maihama.hangar.RemoteMaihamaHangarBhv;
import org.docksidestage.remote.maihama.hangar.base.RemoteHgPagingReturn;
import org.docksidestage.remote.maihama.hangar.mypage.RemoteHgMypageReturn;
import org.docksidestage.remote.maihama.hangar.product.RemoteHgProductRowReturn;
import org.docksidestage.remote.maihama.hangar.product.RemoteHgProductSearchParam;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.HtmlResponse;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRmhangarAction extends FortressBaseAction {

    @Resource
    private RemoteMaihamaHangarBhv hangarBhv;
    @Resource
    private PagingAssist pagingAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // -----------------------------------------------------
    //                                                Simple
    //                                                ------
    // http://localhost:8151/fortress/wx/rmhangar/mypage/
    @Execute
    public JsonResponse<RemoteHgMypageReturn> mypage() {
        RemoteHgMypageReturn ret = hangarBhv.requestMypage();
        return asJson(ret);
    }

    // -----------------------------------------------------
    //                                             Translate
    //                                             ---------
    // http://localhost:8151/fortress/wx/rmhangar/translate/?productName=S
    // http://localhost:8151/fortress/wx/rmhangar/translate/?productName=SeaLandPiariBonvo
    @Execute
    public HtmlResponse translate(WxRmhangarProductSearchForm form) { // can translate validation error automatically
        validate(form, messages -> {}, () -> {
            return asHtml(path_Product_ProductListHtml);
        });
        RemoteHgPagingReturn<RemoteHgProductRowReturn> ret = requestProductList(form);
        PagingResultBean<Product> page = mappingToPage(ret);
        List<WxRmhangarProductSearchRowBean> beans = ret.rows.stream().map(row -> {
            return mappingToRowBean(row);
        }).collect(Collectors.toList());
        return asHtml(path_Product_ProductListHtml).renderWith(data -> {
            data.register("beans", beans);
            pagingAssist.registerPagingNavi(data, page, form);
        });
    }

    private RemoteHgPagingReturn<RemoteHgProductRowReturn> requestProductList(WxRmhangarProductSearchForm form) {
        RemoteHgProductSearchParam param = new RemoteHgProductSearchParam();
        param.productName = form.productName;
        return hangarBhv.requestProductList(OptionalThing.of(1), param);
    }

    private WxRmhangarProductSearchRowBean mappingToRowBean(RemoteHgProductRowReturn row) {
        WxRmhangarProductSearchRowBean bean = new WxRmhangarProductSearchRowBean();
        bean.productId = row.productId;
        bean.productName = row.productName;
        bean.productStatus = row.productStatus;
        bean.productCategory = "unknown category";
        bean.regularPrice = row.regularPrice;
        return bean;
    }

    private PagingResultBean<Product> mappingToPage(RemoteHgPagingReturn<RemoteHgProductRowReturn> ret) {
        PagingResultBean<Product> page = new PagingResultBean<Product>(); // dummy generics
        page.setAllRecordCount(ret.allRecordCount);
        page.setCurrentPageNumber(ret.currentPageNumber);
        page.setPageSize(ret.pageSize);
        page.add(new Product()); // dummy data
        return page;
    }
}
