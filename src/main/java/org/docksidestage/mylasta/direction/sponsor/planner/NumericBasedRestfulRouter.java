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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.dbflute.optional.OptionalThing;
import org.dbflute.util.Srl;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.web.Execute;
import org.lastaflute.web.path.UrlMappingOption;
import org.lastaflute.web.path.UrlMappingResource;
import org.lastaflute.web.path.UrlReverseOption;
import org.lastaflute.web.path.UrlReverseResource;
import org.lastaflute.web.ruts.config.analyzer.MethodNameAnalyzer;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class NumericBasedRestfulRouter {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #thinking jflute already unneeded? waiting for working of LastaFlute RESTful GET pair (2021/05/16)
    protected boolean virtualListHandling;

    // ===================================================================================
    //                                                                              Option
    //                                                                              ======
    public NumericBasedRestfulRouter enableVirtualListHandling() {
        virtualListHandling = true;
        return this;
    }

    // ===================================================================================
    //                                                                         URL Mapping
    //                                                                         ===========
    public OptionalThing<UrlMappingOption> toRestfulMappingPath(UrlMappingResource resource) {
        final List<String> elementList = splitPath(resource.getRequestPath()); // only for determination

        if (!isRestfulPath(elementList)) { // e.g. /1/products/, /products/purchases/
            // you should log as debug here if RESTful only application
            return OptionalThing.empty(); // no filter
        }
        // comment out because of virtual list handling
        //if (elementList.size() <= 2) { // e.g. /products/, /products/1/
        //    return OptionalThing.empty(); // no filter
        //}

        final boolean listGetRequest = isVirtualListGetRequest(elementList); // e.g. GET /products/1/purchases/

        final UrlMappingOption option = new UrlMappingOption();
        option.filterRequestPath(requestPath -> { // may be filtered by old style method
            return convertToMappingPath(requestPath, listGetRequest);
        });
        return OptionalThing.of(option);
    }

    protected String convertToMappingPath(String requestPath, boolean listGetRequest) {
        // e.g.
        //  /products/1/purchases/
        //  /products/1/purchases/2/
        //  /products/1/purchases/2/payments/
        final List<String> stringList = new ArrayList<>();
        final List<String> numberList = new ArrayList<>();
        final List<String> elementList = splitPath(requestPath);
        for (String element : elementList) {
            if (Srl.isNumberHarfAll(element)) {
                numberList.add(element);
            } else {
                stringList.add(element);
            }
        }

        final List<String> arrangedList = new ArrayList<>();
        arrangedList.addAll(stringList);
        if (listGetRequest) {
            arrangedList.add(getListGetMethodKeyword()); // e.g. get$list()
        }
        arrangedList.addAll(numberList);
        return buildPath(arrangedList);
    }

    // -----------------------------------------------------
    //                                 RESTful Determination
    //                                 ---------------------
    protected boolean isRestfulPath(List<String> elementList) {
        int index = 0;
        boolean numberAppeared = false;
        for (String element : elementList) {
            if (Srl.isNumberHarfAll(element)) { // e.g. 1
                if (index % 2 == 0) { // first, third... e.g. /[1]/products/, /products/1/[2]/purchases
                    return false;
                }
                numberAppeared = true;
            } else { // e.g. products
                if (index % 2 == 1) { // second, fourth... e.g. /products/[purchases]/
                    // allows e.g. /products/1/purchases/[sea]
                    // one crossed number parameter is enough to judge RESTful
                    if (!numberAppeared) {
                        return false;
                    }
                }
            }
            ++index;
        }
        return true;
    }

    // ===================================================================================
    //                                                                         URL Reverse
    //                                                                         ===========
    public OptionalThing<UrlReverseOption> toRestfulReversePath(UrlReverseResource resource) {
        if (!isRestfulAction(resource)) {
            return OptionalThing.empty();
        }
        final int classElementCount = countClassElement(resource);
        final UrlReverseOption option = new UrlReverseOption();
        option.filterActionUrl(actionUrl -> {
            final String withoutHash = Srl.substringLastFront(actionUrl, "#");
            final String actionPath = Srl.substringFirstFront(withoutHash, "?"); // without query parameter
            final List<String> elementList = splitPath(actionPath);
            if (elementList.size() < classElementCount) { // basically no way, at least out of target
                return null; // no filter
            }
            final List<String> classElementList = elementList.subList(0, classElementCount);
            final LinkedList<String> partsElementList = new LinkedList<>(elementList.subList(classElementCount, elementList.size()));
            if (isVirtualListGetNamingFirst(partsElementList)) {
                partsElementList.removeFirst();
            }
            final List<String> restfulList = new ArrayList<>();
            final List<String> methodKeywordList = new ArrayList<>(); // lazy loaded
            boolean numberAppeared = false;
            for (String classElement : classElementList) {
                restfulList.add(classElement);
                while (true) {
                    if (partsElementList.isEmpty()) {
                        break;
                    }
                    final String first = String.valueOf(partsElementList.pollFirst());
                    if (Srl.isNumberHarfAll(first) || "{}".equals(first)) { // number parameter, {} (urlPattern) if Lasta Meta
                        // #for_now jflute thought rare case, dangerous if "{}" is real value for redirect (2021/05/17)
                        numberAppeared = true;
                    }
                    if (numberAppeared) {
                        restfulList.add(first);
                        break;
                    } else { // before number parameter
                        methodKeywordList.add(first); // e.g. sea (method keyword)
                        // no break, continue for next parts element
                    }
                }
            }
            for (String methodKeyword : methodKeywordList) { // e.g. get$sea()
                restfulList.add(methodKeyword); // e.g. /products/{productId}/purchases/sea/
            }
            for (String remainingElement : partsElementList) { // basically empty if pure RESTful
                restfulList.add(remainingElement); // e.g. /products/1/purchases/2/3/4
            }
            return buildPath(restfulList);
        });
        return OptionalThing.of(option);
    }

    protected int countClassElement(UrlReverseResource resource) {
        final Class<?> actionType = resource.getActionType();
        final String actionName = Srl.substringLastFront(actionType.getSimpleName(), "Action");
        final String snakeCaseName = Srl.decamelize(actionName);
        return Srl.count(snakeCaseName, "_") + 1;
    }

    // -----------------------------------------------------
    //                                 RESTful Determination
    //                                 ---------------------
    protected boolean isRestfulAction(UrlReverseResource resource) {
        final Class<?> actionType = resource.getActionType();
        // #thinking jflute how can I do? after all, @RestfulAction? (2021/05/16)
        final Method[] methods = actionType.getMethods();
        final List<Method> executeMethodList =
                Stream.of(methods).filter(mt -> mt.getAnnotation(Execute.class) != null).collect(Collectors.toList());
        if (!executeMethodList.stream().allMatch(mt -> mt.getName().contains(MethodNameAnalyzer.REST_DELIMITER))) {
            return false;
        }
        return actionType.getSimpleName().startsWith("Products");
    }

    // ===================================================================================
    //                                                               Virtual List Handling
    //                                                               =====================
    protected boolean isVirtualListGetRequest(List<String> elementList) { // e.g. GET /products/1/purchases/
        return isVirtualListHandling() && isCurrentRequestGet() && isLastElementString(elementList);
    }

    protected boolean isCurrentRequestGet() {
        final RequestManager requestManager = getRequestManager();
        return requestManager.getHttpMethod().filter(mt -> mt.equalsIgnoreCase("get")).isPresent();
    }

    protected boolean isLastElementString(List<String> elementList) {
        String lastElement = elementList.get(elementList.size() - 1);
        return !Srl.isNumberHarfAll(lastElement);
    }

    protected boolean isVirtualListGetNamingFirst(LinkedList<String> partsElementList) {
        return isVirtualListHandling() && getListGetMethodKeyword().equals(partsElementList.getFirst());
    }

    protected String getListGetMethodKeyword() {
        return "list"; // as default
    }

    // ===================================================================================
    //                                                                           Component
    //                                                                           =========
    protected RequestManager getRequestManager() { // used by virtual list
        return ContainerUtil.getComponent(RequestManager.class);
    }

    // ===================================================================================
    //                                                                         Path Helper
    //                                                                         ===========
    protected String buildPath(List<String> elementList) {
        return Srl.quoteAnything(Srl.connectByDelimiter(elementList, "/"), "/");
    }

    protected List<String> splitPath(String path) {
        return Srl.splitList(path, "/").stream().filter(el -> !el.isEmpty()).collect(Collectors.toList());
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isVirtualListHandling() {
        return virtualListHandling;
    }
}