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
package org.docksidestage.app.web.wx.security;

import java.time.LocalDate;
import java.util.List;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

import jakarta.validation.Valid;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxSecurityRequestJsonbodyAction extends FortressBaseAction {

    // request by swagger
    // http://localhost:8151/fortress/wx/security/request/jsonbody/
    @Execute
    public JsonResponse<BasicItemBody> index(BasicItemBody body) {
        validateApi(body, messages -> {});
        return asJson(body);
    }

    // request by swagger
    // http://localhost:8151/fortress/wx/security/request/jsonbody/
    @Execute
    public JsonResponse<BasicItemBody> error(BasicItemBody body) {
        validateApi(body, messages -> {});
        throw new IllegalStateException("forcedly error");
    }

    // mask settings are on web.xml
    public static class BasicItemBody {

        /** e.g. mystic */
        public String secureSea;

        /** e.g. 83 */
        public Integer secureLand;

        /** e.g. 2019-04-07 */
        public LocalDate openPiari;

        /** e.g. [harborSide, riverSide] */
        public List<String> secureMiraco;

        @Valid
        public MapPart secureAmba;

        @Valid
        public MapPart ssador;

        public static class MapPart {

            /** e.g. ambahotel */
            public String dstore;

            /** e.g. shop */
            public String secureBonvo;

            public NestedMapPart dohotel;

            public NestedMapPart secureDodohotel;

            public List<String> secureDododohotelList;
        }

        public static class NestedMapPart {

            /** e.g. oneman */
            public String secureLando;

            /** e.g. oneoneman */
            public String landodo;
        }
    }
}
