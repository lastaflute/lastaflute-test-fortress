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
package org.docksidestage.app.web.ballet.dancers.greatest.favorite.studios;

import java.util.List;

import org.docksidestage.app.web.ballet.dancers.greatest.favorite.studios.assist.GreatestFavoriteStudiosCrudAssist;
import org.docksidestage.app.web.ballet.dancers.greatest.favorite.studios.assist.GreatestFavoriteStudiosMappingAssist;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.exentity.Purchase;
import org.lastaflute.web.Execute;
import org.lastaflute.web.RestfulAction;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

import jakarta.annotation.Resource;

/**
 * @author jflute
 */
@AllowAnyoneAccess
@RestfulAction(hyphenate = { "ballet-dancers", "greatest-favorite-studios" } //
        , allowEventSuffix = true, eventSuffixHyphenate = { "hangar-mystic", "showbase-oneman" })
public class BalletDancersGreatestFavoriteStudiosAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private GreatestFavoriteStudiosCrudAssist greatestFavoriteStudiosCrudAssist;
    @Resource
    private GreatestFavoriteStudiosMappingAssist greatestFavoriteStudiosMappingAssist;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // /ballet-dancers/1/greatest-favorite-studios/
    //
    // http://localhost:8151/fortress/ballet-dancers/1/greatest-favorite-studios/?productName=S
    // _/_/_/_/_/_/_/_/_/_/
    @Execute
    public JsonResponse<GreatestFavoriteStudiosListResult> get$index(Integer productId, GreatestFavoriteStudiosSearchForm form) {
        validateApi(form, messages -> {});
        List<Purchase> purchaseList = greatestFavoriteStudiosCrudAssist.selectPurchaseList(productId, form);
        GreatestFavoriteStudiosListResult result = greatestFavoriteStudiosMappingAssist.mappingToListResult(purchaseList);
        return asJson(result);
    }

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // /ballet-dancers/1/greatest-favorite-studios/2/
    //
    // http://localhost:8151/fortress/ballet-dancers/1/greatest-favorite-studios/16/
    // _/_/_/_/_/_/_/_/_/_/
    @Execute
    public JsonResponse<GreatestFavoriteStudiosOneResult> get$index(Integer productId, Long purchaseId) {
        Purchase purchase = greatestFavoriteStudiosCrudAssist.selectPurchaseById(productId, purchaseId);
        GreatestFavoriteStudiosOneResult result = greatestFavoriteStudiosMappingAssist.mappingToOneResult(purchase);
        return asJson(result);
    }

    // -----------------------------------------------------
    //                                   Test of EventSuffix
    //                                   -------------------
    // http://localhost:8151/fortress/ballet-dancers/1/greatest-favorite-studios/hangar-mystic
    @Execute
    public JsonResponse<GreatestFavoriteStudiosListResult> get$hangarMystic(Integer productId, GreatestFavoriteStudiosSearchForm form) {
        validateApi(form, messages -> {});
        List<Purchase> purchaseList = greatestFavoriteStudiosCrudAssist.selectPurchaseList(productId, form);
        GreatestFavoriteStudiosListResult result = greatestFavoriteStudiosMappingAssist.mappingToListResult(purchaseList);
        return asJson(result);
    }

    // http://localhost:8151/fortress/ballet-dancers/1/greatest-favorite-studios/16/showbase-oneman
    @Execute
    public JsonResponse<GreatestFavoriteStudiosOneResult> get$showbaseOneman(Integer productId, Long purchaseId) {
        Purchase purchase = greatestFavoriteStudiosCrudAssist.selectPurchaseById(productId, purchaseId);
        GreatestFavoriteStudiosOneResult result = greatestFavoriteStudiosMappingAssist.mappingToOneResult(purchase);
        return asJson(result);
    }
}
