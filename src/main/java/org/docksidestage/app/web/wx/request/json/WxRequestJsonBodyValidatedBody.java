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
package org.docksidestage.app.web.wx.request.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.dbflute.allcommon.CDef;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

import jakarta.validation.Valid;

/**
 * @author jflute
 */
public class WxRequestJsonBodyValidatedBody {

    @Length(min = 3)
    public String sea;

    public Integer land;

    public LocalDate piari;

    public LocalDateTime bonvo;

    public Boolean dstore;

    public CDef.MemberStatus amba;

    @Valid
    public MiracoPart miraco;

    public static class MiracoPart {

        @Required
        public String sta;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
