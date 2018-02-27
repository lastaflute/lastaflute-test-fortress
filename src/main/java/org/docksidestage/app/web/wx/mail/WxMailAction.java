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
package org.docksidestage.app.web.wx.mail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.exentity.Member;
import org.docksidestage.dbflute.exentity.Product;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.mail.member.WelcomeMemberPostcard;
import org.docksidestage.mylasta.mail.whitebox.WxLoopBeansPostcard;
import org.docksidestage.mylasta.mail.whitebox.WxLoopBeansRowBean;
import org.docksidestage.mylasta.mail.whitebox.WxNoVariablePostcard;
import org.docksidestage.mylasta.mail.whitebox.WxUsingEntityVariablePostcard;
import org.lastaflute.core.mail.Postbox;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxMailAction extends FortressBaseAction {

    @Resource
    private Postbox postbox;
    @Resource
    private FortressConfig fortressConfig;
    @Resource
    private MemberBhv memberBhv;
    @Resource
    private ProductBhv productBhv;

    // http://localhost:8151/fortress/wx/mail/
    @Execute
    public JsonResponse<String> index() {
        WxNoVariablePostcard.droppedInto(postbox, postcard -> {
            postcard.setFromSupport(fortressConfig);
            postcard.addTo("sea@docksidestage.org");
        });
        return JsonResponse.asJsonDirectly("{\"result\" : \"sent\"}");
    }

    // http://localhost:8151/fortress/wx/mail/basic/
    @Execute
    public JsonResponse<String> basic() {
        WelcomeMemberPostcard.droppedInto(postbox, postcard -> {
            postcard.setFromSupport(fortressConfig);
            postcard.addTo("sea@docksidestage.org");
            postcard.setDomain(fortressConfig.getServerDomain());
            // has defalut
            //postcard.setMemberName("sea");
            postcard.setAccount("land");
            postcard.setToken("piary");
        });
        return JsonResponse.asJsonDirectly("{\"result\" : \"sent\"}");
    }

    @Execute
    public JsonResponse<String> entity() {
        WxUsingEntityVariablePostcard.droppedInto(postbox, postcard -> {
            postcard.setFromSupport(fortressConfig);
            postcard.addTo("sea@docksidestage.org");
            Member member = memberBhv.selectByPK(1).get();
            postcard.setMemberName(member.getMemberName());
            postcard.setMember(member); // not allowed
        });
        return JsonResponse.asJsonDirectly("{\"result\" : \"sent\"}");
    }

    @Execute
    public JsonResponse<String> entitylist() {
        WxUsingEntityVariablePostcard.droppedInto(postbox, postcard -> {
            postcard.setFromSupport(fortressConfig);
            postcard.addTo("sea@docksidestage.org");
            Member member = memberBhv.selectByPK(1).get();
            postcard.setMemberName(member.getMemberName());
            postcard.setMember(member);
            List<Product> productList = productBhv.selectList(cb -> {
                cb.fetchFirst(4);
            });
            postcard.setProductList(productList); // not allowed
        });
        return JsonResponse.asJsonDirectly("{\"result\" : \"sent\"}");
    }

    @Execute
    public JsonResponse<String> loopbeans() {
        List<WxLoopBeansRowBean> beansRowList = new ArrayList<>();
        beansRowList.add(new WxLoopBeansRowBean("mystic", "hangar"));
        beansRowList.add(new WxLoopBeansRowBean("oneman", "showbase"));
        WxLoopBeansPostcard.droppedInto(postbox, postcard -> {
            postcard.setFromSupport(fortressConfig);
            postcard.addTo("sea@docksidestage.org");
            Member member = memberBhv.selectByPK(1).get();
            postcard.setMemberName(member.getMemberName());
            postcard.setBeansRowList(beansRowList);
            postcard.async();
        });
        return JsonResponse.asJsonDirectly("{\"result\" : \"sent\"}");
    }
}
