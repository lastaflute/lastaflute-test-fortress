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
package org.docksidestage.mylasta.action;

import org.lastaflute.web.response.next.HtmlNext;

/**
 * The path definition of HTML.
 * @author FreeGen
 */
public interface FortressHtmlPath {

    /** The path of the HTML: /error/show_errors.html */
    HtmlNext path_Error_ShowErrorsHtml = new HtmlNext("/error/show_errors.html");

    /** The path of the HTML: /member/member_add.html */
    HtmlNext path_Member_MemberAddHtml = new HtmlNext("/member/member_add.html");

    /** The path of the HTML: /member/member_edit.html */
    HtmlNext path_Member_MemberEditHtml = new HtmlNext("/member/member_edit.html");

    /** The path of the HTML: /member/member_list.html */
    HtmlNext path_Member_MemberListHtml = new HtmlNext("/member/member_list.html");

    /** The path of the HTML: /mypage/mypage.html */
    HtmlNext path_Mypage_MypageHtml = new HtmlNext("/mypage/mypage.html");

    /** The path of the HTML: /onparade/onparade.html */
    HtmlNext path_Onparade_OnparadeHtml = new HtmlNext("/onparade/onparade.html");

    /** The path of the HTML: /product/product_detail.html */
    HtmlNext path_Product_ProductDetailHtml = new HtmlNext("/product/product_detail.html");

    /** The path of the HTML: /product/product_list.html */
    HtmlNext path_Product_ProductListHtml = new HtmlNext("/product/product_list.html");

    /** The path of the HTML: /profile/profile.html */
    HtmlNext path_Profile_ProfileHtml = new HtmlNext("/profile/profile.html");

    /** The path of the HTML: /signin/signin.html */
    HtmlNext path_Signin_SigninHtml = new HtmlNext("/signin/signin.html");

    /** The path of the HTML: /signup/signup.html */
    HtmlNext path_Signup_SignupHtml = new HtmlNext("/signup/signup.html");

    /** The path of the HTML: /withdrawal/withdrawal_confirm.html */
    HtmlNext path_Withdrawal_WithdrawalConfirmHtml = new HtmlNext("/withdrawal/withdrawal_confirm.html");

    /** The path of the HTML: /withdrawal/withdrawal_entry.html */
    HtmlNext path_Withdrawal_WithdrawalEntryHtml = new HtmlNext("/withdrawal/withdrawal_entry.html");

    /** The path of the HTML: /wx/message/wx_message_list.html */
    HtmlNext path_WxMessage_WxMessageListHtml = new HtmlNext("/wx/message/wx_message_list.html");

    /** The path of the HTML: /wx/multipart/wx_multipart.html */
    HtmlNext path_WxMultipart_WxMultipartHtml = new HtmlNext("/wx/multipart/wx_multipart.html");

    /** The path of the HTML: /wx/thymeleaf/wx_thymeleaf_add.html */
    HtmlNext path_WxThymeleaf_WxThymeleafAddHtml = new HtmlNext("/wx/thymeleaf/wx_thymeleaf_add.html");

    /** The path of the HTML: /wx/thymeleaf/wx_thymeleaf_edit.html */
    HtmlNext path_WxThymeleaf_WxThymeleafEditHtml = new HtmlNext("/wx/thymeleaf/wx_thymeleaf_edit.html");

    /** The path of the HTML: /wx/thymeleaf/wx_thymeleaf_list.html */
    HtmlNext path_WxThymeleaf_WxThymeleafListHtml = new HtmlNext("/wx/thymeleaf/wx_thymeleaf_list.html");

    /** The path of the HTML: /wx/validator/wx_validator_list.html */
    HtmlNext path_WxValidator_WxValidatorListHtml = new HtmlNext("/wx/validator/wx_validator_list.html");
}
