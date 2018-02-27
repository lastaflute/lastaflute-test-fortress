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

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.mylasta.mail.whitebox.WxRemoteApiPostcard;
import org.lastaflute.core.mail.Postbox;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxMailRemoteapiAction extends FortressBaseAction {

    @Resource
    private FortressConfig config;
    @Resource
    private Postbox postbox;

    // http://localhost:8151/fortress/wx/mail/remoteapi/
    @Execute
    public JsonResponse<String> index() {
        WxRemoteApiPostcard.droppedInto(postbox, postcard -> {
            postcard.setFromSupport(config);
            postcard.addTo("sea@docksidestage.org");
            postcard.setMemberName("remote_api");
            postcard.pushUlterior("sea", new SeaBean("hangar"));
            postcard.writeAuthor(this);
        });
        return JsonResponse.asJsonDirectly("{\"result\" : \"sent\"}");
    }

    protected static class SeaBean {

        protected final String stageName;

        public SeaBean(String stageName) {
            this.stageName = stageName;
        }
    }
}
