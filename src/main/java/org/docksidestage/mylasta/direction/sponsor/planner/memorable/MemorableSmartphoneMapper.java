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
package org.docksidestage.mylasta.direction.sponsor.planner.memorable;

import org.dbflute.optional.OptionalThing;
import org.dbflute.util.Srl;
import org.lastaflute.web.path.UrlMappingOption;
import org.lastaflute.web.path.UrlMappingResource;
import org.lastaflute.web.path.UrlReverseOption;
import org.lastaflute.web.path.UrlReverseResource;

/**
 * @author jflute
 */
public class MemorableSmartphoneMapper {

    public OptionalThing<UrlMappingOption> customizeActionUrlMapping(UrlMappingResource resource) {
        return OptionalThing.ofNullable(doCustomizeActionUrlMapping(resource), () -> {
            throw new IllegalStateException("No operation for the requestPath: " + resource.getPureRequestPath());
        });
    }

    protected UrlMappingOption doCustomizeActionUrlMapping(UrlMappingResource resource) {
        // for mapping '/sp/product/list/' to ProductListSpAction.class
        // (should also override reverse customization)
        if (resource.getPureRequestPath().startsWith("/sp/")) { // e.g. /sp/product/list/
            return new UrlMappingOption().filterRequestPath(requestPath -> { // e.g. /sp/product/list/
                return Srl.substringFirstRear(requestPath, "/sp"); // e.g. /product/list/
            }).useActionNameSuffix("Sp"); // e.g. productListSpAction
        }
        return null;
    }

    public OptionalThing<UrlReverseOption> customizeActionUrlReverse(UrlReverseResource resource) {
        return OptionalThing.ofNullable(doCustomizeActionUrlReverse(resource), () -> {
            throw new IllegalStateException("No operation for the actionType: " + resource.getActionType());
        });
    }

    protected UrlReverseOption doCustomizeActionUrlReverse(UrlReverseResource resource) {
        // for reverse ProductListSpAction.class to '/sp/product/list/'
        // (should also override mapping customization)
        if (resource.getActionType().getSimpleName().endsWith("SpAction")) { // e.g. productListSpAction
            return new UrlReverseOption().filterActionName(actionName -> { // e.g. productListSp
                return "sp" + Srl.initCap(Srl.substringLastFront(actionName, "Sp")); // e.g. spProductList
            });
        }
        return null;
    }
}