/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage.app.web.wx.response.json;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.base.view.DisplayAssist;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseJsonAction extends FortressBaseAction {

    @Resource
    private DisplayAssist displayAssist;

    @Execute
    public JsonResponse<MyBasicJsonBean> index() {
        return asJson(new MyBasicJsonBean(3, "sea", displayAssist.toDate("2001-09-06").get()));
    }

    public static class MyBasicJsonBean {

        @Required
        public final Integer memberId;
        @Required
        public final String memberName;
        public final LocalDate birthdate;

        public MyBasicJsonBean(Integer memberId, String memberName, LocalDate birthdate) {
            this.memberId = memberId;
            this.memberName = memberName;
            this.birthdate = birthdate;
        }
    }
}
