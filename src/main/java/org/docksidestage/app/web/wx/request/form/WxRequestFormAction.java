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
package org.docksidestage.app.web.wx.request.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxRequestFormAction extends FortressBaseAction {

    // http://localhost:8151/fortress/wx/request/form?sea=mystic&land=83&&piari=2019/04/07&dstore=true
    @Execute
    public JsonResponse<BasicItemForm> index(BasicItemForm form) {
        validateApi(form, messages -> {});
        return asJson(form);
    }

    public static class BasicItemForm {

        // #thinking jflute confusing "e.g. default value", set as MyEcColleForm's one (2022/02/03)
        public String sea;

        public Integer land;

        /** as date e.g. 2022-02-03 */
        public LocalDate piari;

        public LocalDateTime bonvo;

        public LocalTime dstore;

        public Boolean amba;

        /** string list e.g. [harborSide,riverSide] */
        public List<String> miraco;
    }

    // [success]
    // http://localhost:8151/fortress/wx/request/form/eccolle?sea=mystic&sea=bigband
    // http://localhost:8151/fortress/wx/request/form/eccolle?sea[0]=mystic&sea[1]=bigband
    //  => since 1.0.3
    //  => Cannot call add() on ImmutableEmptyList since 1.0.3-RC1
    //  => The indexed property was not list or array until 1.0.2
    //
    // http://localhost:8151/fortress/wx/request/form/eccolle?land=oneman&land=minnie
    //  => since 1.0.3
    //  => Can not set org.eclipse.collections.api.list.MutableList field
    // http://localhost:8151/fortress/wx/request/form/eccolle?land[0]=oneman&land[1]=minnie
    //  => since 1.0.3
    // http://localhost:8151/fortress/wx/request/form/eccolle?piari=first&piari=second
    // http://localhost:8151/fortress/wx/request/form/eccolle?piari[0]=first&piari[1]=second
    //
    // http://localhost:8151/fortress/wx/request/form/eccolle?dstore[0].walt=first&dstore[1].walt=second
    //  => since 1.0.3
    //  => Cannot call add() on ImmutableEmptyList since 1.0.3-RC1
    //  => The indexed property was not list or array until 1.0.2
    //
    // http://localhost:8151/fortress/wx/request/form/eccolle?amba[0].chef=first&amba[1].chef=second
    //  => since 1.0.3
    //  => Can not set org.eclipse.collections.api.list.MutableList field until 1.0.2
    //
    // [no-error failure]
    // http://localhost:8151/fortress/wx/request/form/eccolle?dstore.walt=first&dstore.walt=second
    // http://localhost:8151/fortress/wx/request/form/eccolle?amba.chef=first&amba.chef=second
    //
    // [client error]
    // http://localhost:8151/fortress/wx/request/form/eccolle?sea[]=mystic&sea[]=bigband
    // http://localhost:8151/fortress/wx/request/form/eccolle?land[]=oneman&land[]=minnie
    // http://localhost:8151/fortress/wx/request/form/eccolle?piari[]=first&piari[]=second
    //
    // [system error]
    @Execute
    public JsonResponse<MyEcColleForm> eccolle(MyEcColleForm form) {
        validateApi(form, messages -> {});
        return asJson(form); // for visual check
    }

    public static class MyEcColleForm {

        /** string immutable list e.g. [dockside,hangar,magiclamp] */
        public ImmutableList<String> sea;

        public MutableList<String> land;

        /** string util.list e.g. [celeb,plaza] */
        public List<String> piari;

        public ImmutableMap<String, String> bonvo;

        public ImmutableList<MyDStorePart> dstore;

        public static class MyDStorePart {

            public String walt;
        }

        public MutableList<MyAmbaPart> amba;

        public static class MyAmbaPart {

            public String chef;

            // #hope jflute fix DfCollectionUtil.orderAccordingTo(), duplicate ID problem (2021/06/08)
            // first, nested lato is unneeded, it's recursive
            // while nested lato causes following exception from LastaMeta:
            // The id was duplicated: id=toString() orderedUniqueIdList=[index(BasicItemForm), eccolle(MyEcColleForm), toString(), toString()]
            //   at org.dbflute.util.DfCollectionUtil.orderAccordingTo(DfCollectionUtil.java:499)
            //   at org.lastaflute.meta.reflector.javaparser.parsing.JavaparserSourceMethodHandler.orderMethodListBySource(JavaparserSourceMethodHandler.java:70)
            //@Override
            //public String toString() {
            //    return Lato.string(this);
            //}
        }

        @Override
        public String toString() {
            return Lato.string(this);
        }
    }
}
