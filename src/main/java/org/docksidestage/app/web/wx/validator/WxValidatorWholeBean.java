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

import javax.validation.Valid;

import org.lastaflute.web.validation.Required;

/**
 * @param <DATA> The type of data bean.
 * @author jflute
 */
public class WxValidatorWholeBean<DATA> {

    @Required
    public final Integer seaId;
    @Required
    public final String landName;
    @Valid
    public DATA data;

    public WxValidatorWholeBean(Integer seaId, String landName) {
        this.seaId = seaId;
        this.landName = landName;
    }

    public static class WhiteboxValidatorWholeElementBean {

        @Required
        public final Integer piariId;

        public WhiteboxValidatorWholeElementBean(Integer piariId) {
            this.piariId = piariId;
        }
    }
}
