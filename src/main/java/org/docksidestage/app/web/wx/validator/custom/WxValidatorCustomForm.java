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
package org.docksidestage.app.web.wx.validator.custom;

import javax.validation.constraints.Size;

import org.docksidestage.bizfw.validation.fess.CustomSize;
import org.lastaflute.core.util.Lato;

/**
 * @author jflute
 */
public class WxValidatorCustomForm {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @CustomSize(minKey = "seaMin", maxKey = "seaMax")
    public String sea;

    @Size(min = 4, max = 15)
    public String land; // as normal annotation

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    public String toString() {
        return Lato.string(this);
    }
}
