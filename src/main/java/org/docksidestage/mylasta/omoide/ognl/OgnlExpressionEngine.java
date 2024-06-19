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
package org.docksidestage.mylasta.omoide.ognl;

import java.util.Map;

import javax.script.ScriptEngineManager;

import ognl.OgnlRuntime;

import org.lastaflute.di.core.LaContainer;
import org.lastaflute.di.core.expression.engine.ExpressionEngine;

/**
 * @author jflute
 */
public class OgnlExpressionEngine implements ExpressionEngine {

    static {
        OgnlRuntime.setPropertyAccessor(LaContainer.class, new LaContainerPropertyAccessor());
    }

    protected final ScriptEngineManager SCRIPT_ENGINE_MANAGER = newScriptEngineManager();

    protected ScriptEngineManager newScriptEngineManager() {
        return new ScriptEngineManager();
    }

    @Override
    public Object parseExpression(String source) {
        return OgnlUtil.parseExpression(source);
    }

    @Override
    public Object evaluate(Object exp, Map<String, ? extends Object> contextMap, LaContainer container,
            Class<?> conversionType) {
        return OgnlUtil.getValue(exp, contextMap, container);
    }

    @Override
    public String resolveStaticMethodReference(Class<?> refType, String methodName) {
        return "@" + refType.getName() + "@" + methodName;
    }
}
