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
package org.docksidestage.app.web.wx.response.json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.bizfw.json.RuledJsonEngineKeeper;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseJsonSwitchedAction extends FortressBaseAction {

    @Resource
    private RuledJsonEngineKeeper ruledJsonEngineKeeper;

    // http://localhost:8151/fortress/wx/response/json/switched/engine
    @Execute
    public JsonResponse<MyBasicJsonResult> engine() {
        return asJson(createResult()).switchJsonEngine(() -> {
            return ruledJsonEngineKeeper.provideSeaJsonEngine();
        });
    }

    // http://localhost:8151/fortress/wx/response/json/switched/mapping
    @Execute
    public JsonResponse<MyBasicJsonResult> mapping() {
        return asJson(createResult()).switchMappingOption(op -> {
            op.asNullToEmptyWriting()
                    .formatLocalDateBy(DateTimeFormatter.ofPattern("yyyy@MM@dd"))
                    .serializeBooleanBy(boo -> boo ? "1" : "0");
        });
    }

    // http://localhost:8151/fortress/wx/response/json/switched/none
    @Execute
    public JsonResponse<MyBasicJsonResult> none() {
        return asJson(createResult());
    }

    private MyBasicJsonResult createResult() {
        return new MyBasicJsonResult(1, null, LocalDate.of(2018, 10, 6), true);
    }

    protected static class MyBasicJsonResult {

        @Required
        public final Integer memberId;
        public final String memberName;
        public final LocalDate birthdate;
        @Required
        public Boolean showbaseNow;

        public MyBasicJsonResult(Integer memberId, String memberName, LocalDate birthdate, Boolean showbaseNow) {
            this.memberId = memberId;
            this.memberName = memberName;
            this.birthdate = birthdate;
            this.showbaseNow = showbaseNow;
        }

        @Override
        public String toString() {
            return Lato.string(this);
        }
    }
}
