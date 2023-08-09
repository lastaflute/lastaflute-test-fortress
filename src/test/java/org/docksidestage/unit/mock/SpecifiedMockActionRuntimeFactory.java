/*
 * Copyright 2015-2022 the original author or authors.
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
package org.docksidestage.unit.mock;

import java.util.Collections;
import java.util.List;

import org.dbflute.utflute.lastaflute.mock.MockRomanticActionCustomizer;
import org.dbflute.util.Srl;
import org.lastaflute.di.core.meta.impl.ComponentDefImpl;
import org.lastaflute.web.ruts.config.ActionExecute;
import org.lastaflute.web.ruts.config.ActionMapping;
import org.lastaflute.web.ruts.process.ActionRuntime;
import org.lastaflute.web.ruts.process.pathparam.RequestPathParam;

/**
 * @author jflute
 */
public class SpecifiedMockActionRuntimeFactory {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ActionMapping _mapping; // not null
    protected final String _testMethodName; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public SpecifiedMockActionRuntimeFactory(Class<?> actionType, String testMethodName) {
        final MockRomanticActionCustomizer customizer = createMockRomanticActionCustomizer();
        final String componentName = Srl.initUncap(actionType.getSimpleName());
        _mapping = customizer.createActionMapping(createComponentDefImpl(actionType, componentName));
        _testMethodName = testMethodName;
    }

    protected MockRomanticActionCustomizer createMockRomanticActionCustomizer() {
        return new MockRomanticActionCustomizer();
    }

    protected ComponentDefImpl createComponentDefImpl(Class<?> componentClass, String componentName) {
        return new ComponentDefImpl(componentClass, componentName);
    }

    // ===================================================================================
    //                                                                             Creator
    //                                                                             =======
    public ActionRuntime createHtmlRuntime() {
        final ActionExecute foundExecute = findActionExecute();
        return new ActionRuntime("/mock/sea/", foundExecute, createRequestPathParam());
    }

    public ActionRuntime createJsonRuntime() {
        final ActionExecute foundExecute = findActionExecute();
        return new ActionRuntime("/mock/land/", foundExecute, createRequestPathParam());
    }

    protected ActionExecute findActionExecute() {
        // #for_now jflute judge HtmlResponse, JsonResponse (2023/08/07)
        final List<ActionExecute> executeList = _mapping.getExecuteList();
        for (ActionExecute execute : executeList) {
            if (_testMethodName.contains(execute.getExecuteMethod().getName())) { // e.g. test_annotated_...()
                return execute; // first priority
            }
        }
        // random search here
        final ActionExecute foundExecute;
        final List<ActionExecute> indexList = _mapping.searchByMethodName("index");
        if (indexList.size() >= 1) {
            foundExecute = indexList.get(0); // simple
        } else {
            if (executeList.size() >= 1) {
                foundExecute = executeList.get(0); // simple
            } else { // no way
                throw new IllegalStateException("Not found the execute method in the action: " + _mapping);
            }
        }
        return foundExecute;
    }

    protected RequestPathParam createRequestPathParam() {
        return new RequestPathParam(Collections.emptyList(), Collections.emptyMap());
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ActionMapping getMapping() {
        return _mapping;
    }
}