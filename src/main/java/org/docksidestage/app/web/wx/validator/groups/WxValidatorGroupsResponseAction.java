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
package org.docksidestage.app.web.wx.validator.groups;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.app.web.wx.validator.groups.group.WxValidatorGroupsGroupDockside;
import org.docksidestage.app.web.wx.validator.groups.group.WxValidatorGroupsGroupHangar;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxValidatorGroupsResponseAction extends FortressBaseAction {

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/validator/groups/response/dockside/
    //  => 500: validation error for response :: docksideOnly, bothNamed, withDefault
    @Execute
    public JsonResponse<WxValidatorGroupsResponseResult> dockside() {
        WxValidatorGroupsResponseResult result = new WxValidatorGroupsResponseResult();
        return asJson(result).groupValidator(WxValidatorGroupsGroupDockside.class);
    }

    // http://localhost:8151/fortress/wx/validator/groups/response/hangar/
    //  => 500: validation error for response :: hangarOnly, bothNamed, withDefault
    @Execute
    public JsonResponse<WxValidatorGroupsResponseResult> hangar() {
        WxValidatorGroupsResponseResult result = new WxValidatorGroupsResponseResult();
        return asJson(result).groupValidator(WxValidatorGroupsGroupHangar.class);
    }

    // http://localhost:8151/fortress/wx/validator/groups/response/useDefault/
    //  => 500: validation error for response :: withDefault, noSpecified, defaultOnly
    @Execute
    public JsonResponse<WxValidatorGroupsResponseResult> useDefault() {
        WxValidatorGroupsResponseResult result = new WxValidatorGroupsResponseResult();
        return asJson(result);
    }
}
