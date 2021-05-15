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
package org.docksidestage.mylasta.direction.sponsor.planner;

import java.util.function.Supplier;

import org.dbflute.util.Srl;
import org.lastaflute.web.path.UrlMappingOption;
import org.lastaflute.web.path.UrlMappingResource;
import org.lastaflute.web.path.UrlReverseOption;
import org.lastaflute.web.path.UrlReverseResource;

/**
 * @author jflute
 */
public class MemorableSmartphoneMapper {

    public UrlMappingOption customizeActionUrlMapping(UrlMappingResource resource, Supplier<UrlMappingOption> nextCustomizer) {
        // for mapping '/sp/product/list/' to ProductListSpAction.class
        // (should also override reverse customization)
        if (resource.getRequestPath().startsWith("/sp/")) { // e.g. /sp/product/list/
            return new UrlMappingOption().filterRequestPath(requestPath -> { // e.g. /sp/product/list/
                return Srl.substringFirstRear(requestPath, "/sp"); // e.g. /product/list/
            }).useActionNameSuffix("Sp"); // e.g. productListSpAction
        }
        return nextCustomizer.get();
    }

    public UrlReverseOption customizeActionUrlReverse(UrlReverseResource resource, Supplier<UrlReverseOption> nextCustomizer) {
        // for reverse ProductListSpAction.class to '/sp/product/list/'
        // (should also override mapping customization)
        if (resource.getActionType().getSimpleName().endsWith("SpAction")) { // e.g. productListSpAction
            return new UrlReverseOption().filterActionName(actionName -> { // e.g. productListSp
                return "sp" + Srl.initCap(Srl.substringLastFront(actionName, "Sp")); // e.g. spProductList
            });
        }
        return nextCustomizer.get();
    }
}