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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.dbflute.optional.OptionalThing;
import org.dbflute.util.Srl;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.web.path.UrlMappingOption;
import org.lastaflute.web.path.UrlMappingResource;
import org.lastaflute.web.path.UrlReverseOption;
import org.lastaflute.web.path.UrlReverseResource;
import org.lastaflute.web.servlet.request.RequestManager;

/**
 * @author jflute
 */
public class PrimitiveRestfulRouter {

    // ===================================================================================
    //                                                                         URL Mapping
    //                                                                         ===========
    public OptionalThing<UrlMappingOption> toRestfulMappingPath(UrlMappingResource resource) {
        final List<String> elementList = splitPath(resource.getRequestPath()); // only for determination

        if (!isRestfulPath(elementList)) { // e.g. /1/products/, /products/purchases/
            // you should log as debug here if RESTful only application
            return OptionalThing.empty(); // no filter
        }
        // comment out because of listGetRequest handling
        //if (elementList.size() <= 2) { // e.g. /products/, /products/1/
        //    return OptionalThing.empty(); // no filter
        //}

        final boolean listGetRequest = isListGetRequest(elementList); // e.g. GET /products/1/purchases/

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
        for (String element : elementList) {
            if (Srl.isNumberHarfAll(element)) { // e.g. 1
                if (index % 2 == 0) { // first, third... e.g. /[1]/products/, /products/1/[2]/purchases
                    return false;
                }
            } else { // e.g. products
                if (index % 2 == 1) { // second, fourth... e.g. /products/[purchases]/, /products/1/purchases/[payments«/
                    return false;
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
            if (isListGetMethodFirst(partsElementList)) {
                partsElementList.removeFirst();
            }
            final List<String> restfulList = new ArrayList<>();
            for (String classElement : classElementList) {
                restfulList.add(classElement);
                if (!partsElementList.isEmpty()) { // avoid returning null
                    restfulList.add(String.valueOf(partsElementList.pollFirst()));
                }
            }
            for (String remainingElement : partsElementList) {
                restfulList.add(remainingElement);
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
        // #thinking jflute how can I do? (2021/05/16)
        //final Method[] methods = actionType.getMethods();
        //final List<Method> executeMethodList = Stream.of(methods).filter(mt -> {
        //    return mt.getAnnotation(Execute.class) != null;
        //}).collect(Collectors.toList());
        //executeMethodList.stream().allMatch(mt -> mt.getName().contains("$"));
        return actionType.getSimpleName().startsWith("Products");
    }

    // ===================================================================================
    //                                                                    List Get Request
    //                                                                    ================
    protected boolean isListGetRequest(List<String> elementList) { // e.g. GET /products/1/purchases/
        return isCurrentRequestGet() && isLastElementString(elementList);
    }

    protected boolean isCurrentRequestGet() {
        final RequestManager requestManager = getRequestManager();
        return requestManager.getHttpMethod().filter(mt -> mt.equalsIgnoreCase("get")).isPresent();
    }

    protected boolean isLastElementString(List<String> elementList) {
        String lastElement = elementList.get(elementList.size() - 1);
        return !Srl.isNumberHarfAll(lastElement);
    }

    protected boolean isListGetMethodFirst(LinkedList<String> partsElementList) {
        return getListGetMethodKeyword().equals(partsElementList.getFirst());
    }

    protected String getListGetMethodKeyword() {
        return "list"; // as default
    }

    // ===================================================================================
    //                                                                           Component
    //                                                                           =========
    protected RequestManager getRequestManager() {
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
}