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
package org.docksidestage.app.web.onparade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.dbflute.allcommon.CDef;

/**
 * @author jflute
 */
public class OnparadeSearchForm {

    public String productName;
    public CDef.ProductStatus productStatus;
    public String purchaseMemberName;

    // ?nested.nestedStr=sea
    public NestedForm nested;

    // ?nestedList[0].nestedStr=sea&nestedList[1].nestedStr=land
    public List<NestedForm> nestedList;

    public static class NestedForm {
        public String nestedStr = "nestedDefault";
        public Integer nestedInt;

        @Override
        public String toString() {
            return "nested:{" + nestedStr + ", " + nestedInt + "}";
        }
    }

    // ?keyValues.sea=mystic&keyValues.sea=over
    public Map<String, String[]> keyValues = new HashMap<>();
}
