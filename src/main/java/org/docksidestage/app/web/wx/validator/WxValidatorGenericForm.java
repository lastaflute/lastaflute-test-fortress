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
package org.docksidestage.app.web.wx.validator;

import org.lastaflute.web.validation.Required;

/**
 * @param <DATA> The type of data form.
 * @author jflute
 */
public class WxValidatorGenericForm<DATA> {

    @Required
    public Integer seaId;
    @Required
    public Boolean landNow;

    public DATA data; // cannot check #for_now

    public static class WhiteboxValidatorGenericElementBean {

        @Required
        public final int piariId; // cannot check #for_now: WxValidatorGenericForm<WxValidatorGenericElementBean>

        public WhiteboxValidatorGenericElementBean(Integer piariId) {
            this.piariId = piariId;
        }
    }
}
