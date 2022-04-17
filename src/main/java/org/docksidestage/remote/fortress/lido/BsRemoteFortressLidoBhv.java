/*
 * Copyright 2015-2021 the original author or authors.
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
package org.docksidestage.remote.fortress.lido;

import java.util.function.Consumer;

import org.dbflute.remoteapi.FlutyRemoteApiRule;
import org.docksidestage.remote.fortress.AbstractRemoteFortressBhv;
import org.docksidestage.remote.fortress.lido.auth.signin.RemoteLidoAuthSigninParam;
import org.docksidestage.remote.fortress.lido.auth.signup.RemoteLidoAuthSignupParam;
import org.docksidestage.remote.fortress.lido.following.delete.RemoteLidoFollowingDeleteParam;
import org.docksidestage.remote.fortress.lido.following.delete.RemoteLidoFollowingDeleteReturn;
import org.docksidestage.remote.fortress.lido.following.list.RemoteLidoFollowingListParam;
import org.docksidestage.remote.fortress.lido.following.list.RemoteLidoFollowingListReturn;
import org.docksidestage.remote.fortress.lido.following.register.RemoteLidoFollowingRegisterParam;
import org.docksidestage.remote.fortress.lido.following.register.RemoteLidoFollowingRegisterReturn;
import org.docksidestage.remote.fortress.lido.mypage.RemoteLidoMypageReturn;
import org.docksidestage.remote.fortress.lido.product.detail.RemoteLidoProductDetailReturn;
import org.docksidestage.remote.fortress.lido.product.list.RemoteLidoProductListParam;
import org.docksidestage.remote.fortress.lido.product.list.RemoteLidoProductListReturn;
import org.docksidestage.remote.fortress.lido.product.price.update.RemoteLidoProductPriceUpdateParam;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * The base class as generation gap for remote API of lido.
 * @author FreeGen
 */
public abstract class BsRemoteFortressLidoBhv extends AbstractRemoteFortressBhv {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * @param requestManager The manager of request, LastaFlute component. (NotNull)
     */
    public BsRemoteFortressLidoBhv(RequestManager requestManager) {
        super(requestManager);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    /**
     * Request remote call to /lido/auth/signin. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/signin
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoAuthSigninParam. (NotNull)
     */
    public void requestAuthSignin(Consumer<RemoteLidoAuthSigninParam> paramLambda) {
        doRequestAuthSignin(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/auth/signin. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/signin
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoAuthSigninParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAuthSignin(Consumer<RemoteLidoAuthSigninParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoAuthSigninParam param = new RemoteLidoAuthSigninParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/lido/auth/signin", noMoreUrl(), param, rule -> {
            ruleOfAuthSignin(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/auth/signin.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAuthSignin(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/auth/signout. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/signout
     * httpMethod: GET
     * </pre>
     */
    public void requestAuthSignout() {
        doRequestAuthSignout(rule -> {});
    }

    /**
     * Request remote call to /lido/auth/signout. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/signout
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAuthSignout(Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/lido/auth/signout", noMoreUrl(), noQuery(), rule -> {
            ruleOfAuthSignout(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/auth/signout.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAuthSignout(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/auth/signup. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/signup
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoAuthSignupParam. (NotNull)
     */
    public void requestAuthSignup(Consumer<RemoteLidoAuthSignupParam> paramLambda) {
        doRequestAuthSignup(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/auth/signup. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/signup
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoAuthSignupParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAuthSignup(Consumer<RemoteLidoAuthSignupParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoAuthSignupParam param = new RemoteLidoAuthSignupParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/lido/auth/signup", noMoreUrl(), param, rule -> {
            ruleOfAuthSignup(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/auth/signup.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAuthSignup(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/auth/register/{account}/{token}. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param account The value of path variable for account. (NotNull)
     * @param token The value of path variable for token. (NotNull)
     */
    public void requestAuthRegister(String account, String token) {
        doRequestAuthRegister(account, token, rule -> {});
    }

    /**
     * Request remote call to /lido/auth/register/{account}/{token}. (auto-generated method)<br>
     * <pre>
     * url: /lido/auth/register/{account}/{token}
     * httpMethod: GET
     * </pre>
     * @param account The value of path variable for account. (NotNull)
     * @param token The value of path variable for token. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestAuthRegister(String account, String token, Consumer<FlutyRemoteApiRule> ruleLambda) {
        doRequestGet(void.class, "/lido/auth/register/{account}/{token}", moreUrl(account, token), noQuery(), rule -> {
            ruleOfAuthRegisterAccountToken(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/auth/register/{account}/{token}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfAuthRegisterAccountToken(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/following/list. (auto-generated method)<br>
     * <pre>
     * url: /lido/following/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteLidoFollowingListParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteLidoFollowingListReturn requestFollowingList(Consumer<RemoteLidoFollowingListParam> paramLambda) {
        return doRequestFollowingList(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/following/list. (auto-generated method)<br>
     * <pre>
     * url: /lido/following/list
     * httpMethod: GET
     * </pre>
     * @param paramLambda The callback for RemoteLidoFollowingListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteLidoFollowingListReturn doRequestFollowingList(Consumer<RemoteLidoFollowingListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoFollowingListParam param = new RemoteLidoFollowingListParam();
        paramLambda.accept(param);
        return doRequestGet(RemoteLidoFollowingListReturn.class, "/lido/following/list", noMoreUrl(), query(param), rule -> {
            ruleOfFollowingList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/following/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFollowingList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/following/register. (auto-generated method)<br>
     * <pre>
     * url: /lido/following/register
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoFollowingRegisterParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteLidoFollowingRegisterReturn requestFollowingRegister(Consumer<RemoteLidoFollowingRegisterParam> paramLambda) {
        return doRequestFollowingRegister(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/following/register. (auto-generated method)<br>
     * <pre>
     * url: /lido/following/register
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoFollowingRegisterParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteLidoFollowingRegisterReturn doRequestFollowingRegister(Consumer<RemoteLidoFollowingRegisterParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoFollowingRegisterParam param = new RemoteLidoFollowingRegisterParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteLidoFollowingRegisterReturn.class, "/lido/following/register", noMoreUrl(), param, rule -> {
            ruleOfFollowingRegister(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/following/register.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFollowingRegister(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/following/delete. (auto-generated method)<br>
     * <pre>
     * url: /lido/following/delete
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoFollowingDeleteParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteLidoFollowingDeleteReturn requestFollowingDelete(Consumer<RemoteLidoFollowingDeleteParam> paramLambda) {
        return doRequestFollowingDelete(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/following/delete. (auto-generated method)<br>
     * <pre>
     * url: /lido/following/delete
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoFollowingDeleteParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteLidoFollowingDeleteReturn doRequestFollowingDelete(Consumer<RemoteLidoFollowingDeleteParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoFollowingDeleteParam param = new RemoteLidoFollowingDeleteParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteLidoFollowingDeleteReturn.class, "/lido/following/delete", noMoreUrl(), param, rule -> {
            ruleOfFollowingDelete(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/following/delete.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfFollowingDelete(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/mypage/. (auto-generated method)<br>
     * <pre>
     * url: /lido/mypage/
     * httpMethod: GET
     * </pre>
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public org.eclipse.collections.api.list.ImmutableList<RemoteLidoMypageReturn> requestMypage() {
        return doRequestMypage(rule -> {});
    }

    /**
     * Request remote call to /lido/mypage/. (auto-generated method)<br>
     * <pre>
     * url: /lido/mypage/
     * httpMethod: GET
     * </pre>
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected org.eclipse.collections.api.list.ImmutableList<RemoteLidoMypageReturn> doRequestMypage(Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(new org.lastaflute.di.helper.misc.ParameterizedRef<org.eclipse.collections.api.list.ImmutableList<RemoteLidoMypageReturn>>() {
        }.getType(), "/lido/mypage/", noMoreUrl(), noQuery(), rule -> {
            ruleOfMypage(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/mypage/.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfMypage(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/product/detail/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/detail/{productId}
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteLidoProductDetailReturn requestProductDetail(Integer productId) {
        return doRequestProductDetail(productId, rule -> {});
    }

    /**
     * Request remote call to /lido/product/detail/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/detail/{productId}
     * httpMethod: GET
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteLidoProductDetailReturn doRequestProductDetail(Integer productId, Consumer<FlutyRemoteApiRule> ruleLambda) {
        return doRequestGet(RemoteLidoProductDetailReturn.class, "/lido/product/detail/{productId}", moreUrl(productId), noQuery(), rule -> {
            ruleOfProductDetailProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/product/detail/{productId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfProductDetailProductId(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/product/list. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/list
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoProductListParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteLidoProductListReturn requestProductList(Consumer<RemoteLidoProductListParam> paramLambda) {
        return doRequestProductList(paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/product/list. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/list
     * httpMethod: POST
     * </pre>
     * @param paramLambda The callback for RemoteLidoProductListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteLidoProductListReturn doRequestProductList(Consumer<RemoteLidoProductListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoProductListParam param = new RemoteLidoProductListParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteLidoProductListReturn.class, "/lido/product/list", noMoreUrl(), param, rule -> {
            ruleOfProductList(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/product/list.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfProductList(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/product/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteLidoProductListParam. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    public RemoteLidoProductListReturn requestProductList(Integer pageNumber, Consumer<RemoteLidoProductListParam> paramLambda) {
        return doRequestProductList(pageNumber, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/product/list/{pageNumber}. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/list/{pageNumber}
     * httpMethod: POST
     * </pre>
     * @param pageNumber The value of path variable for pageNumber. (NotNull)
     * @param paramLambda The callback for RemoteLidoProductListParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     * @return The bean object as return type, receiving response body. (NotNull)
     */
    protected RemoteLidoProductListReturn doRequestProductList(Integer pageNumber, Consumer<RemoteLidoProductListParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoProductListParam param = new RemoteLidoProductListParam();
        paramLambda.accept(param);
        return doRequestPost(RemoteLidoProductListReturn.class, "/lido/product/list/{pageNumber}", moreUrl(pageNumber), param, rule -> {
            ruleOfProductListPageNumber(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/product/list/{pageNumber}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfProductListPageNumber(FlutyRemoteApiRule rule) {
    }

    /**
     * Request remote call to /lido/product/price/update/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/price/update/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteLidoProductPriceUpdateParam. (NotNull)
     */
    public void requestProductPriceUpdate(Integer productId, Consumer<RemoteLidoProductPriceUpdateParam> paramLambda) {
        doRequestProductPriceUpdate(productId, paramLambda, rule -> {});
    }

    /**
     * Request remote call to /lido/product/price/update/{productId}. (auto-generated method)<br>
     * <pre>
     * url: /lido/product/price/update/{productId}
     * httpMethod: POST
     * </pre>
     * @param productId The value of path variable for productId. (NotNull)
     * @param paramLambda The callback for RemoteLidoProductPriceUpdateParam. (NotNull)
     * @param ruleLambda The callback for setting rule as dynamic requirement. (NotNull)
     */
    protected void doRequestProductPriceUpdate(Integer productId, Consumer<RemoteLidoProductPriceUpdateParam> paramLambda, Consumer<FlutyRemoteApiRule> ruleLambda) {
        RemoteLidoProductPriceUpdateParam param = new RemoteLidoProductPriceUpdateParam();
        paramLambda.accept(param);
        doRequestPost(void.class, "/lido/product/price/update/{productId}", moreUrl(productId), param, rule -> {
            ruleOfProductPriceUpdateProductId(rule);
            ruleLambda.accept(rule);
        });
    }

    /**
     * Set up method-level rule of /lido/product/price/update/{productId}.<br>
     * @param rule The rule that class default rule is already set. (NotNull)
     */
    protected void ruleOfProductPriceUpdateProductId(FlutyRemoteApiRule rule) {
    }
}
