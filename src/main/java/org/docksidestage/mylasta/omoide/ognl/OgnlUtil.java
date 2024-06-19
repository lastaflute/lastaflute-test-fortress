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

import ognl.ClassResolver;
import ognl.Ognl;

import org.lastaflute.di.core.LaContainer;
import org.lastaflute.di.exception.OgnlRuntimeException;
import org.lastaflute.di.helper.misc.LdiExceptionMessageBuilder;

/**
 * @author modified by jflute (originated in Seasar)
 */
public class OgnlUtil {

    protected OgnlUtil() {
    }

    public static Object parseExpression(String expression) throws OgnlRuntimeException {
        try {
            return Ognl.parseExpression(expression);
        } catch (Exception ex) {
            throw new OgnlRuntimeException(ex);
        }
    }

    public static Object getValue(Object exp, Map<String, ? extends Object> ctx, Object root) throws OgnlRuntimeException {
        try {
            final Map<?, ?> newCtx = addClassResolverIfNecessary(ctx, root);
            if (newCtx != null) {
                return Ognl.getValue(exp, newCtx, root);
            } else {
                return Ognl.getValue(exp, root);
            }
        } catch (Exception e) {
            throwOgnlGetValueFailureException(exp, ctx, root, e);
            return null; // unreachable
        }
    }

    protected static void throwOgnlGetValueFailureException(Object exp, Map<String, ? extends Object> ctx, Object root, Exception cause) {
        final LdiExceptionMessageBuilder br = new LdiExceptionMessageBuilder();
        br.addNotice("Failed to get value by the OGNL expression.");
        br.addItem("OGNL Expression");
        br.addElement(exp);
        br.addItem("Context Map");
        br.addElement(ctx);
        br.addItem("Root");
        br.addElement(root);
        final String msg = br.buildExceptionMessage();
        throw new IllegalStateException(msg, cause);
    }

    @SuppressWarnings("unchecked")
    static Map<?, ?> addClassResolverIfNecessary(Map<String, ? extends Object> ctx, Object root) {
        if (root instanceof LaContainer) {
            final LaContainer container = (LaContainer) root;
            final ClassLoader classLoader = container.getClassLoader();
            if (classLoader != null) {
                final ClassResolverImpl classResolver = new ClassResolverImpl(classLoader);
                if (ctx == null) {
                    ctx = Ognl.createDefaultContext(root, classResolver);
                } else {
                    ctx = Ognl.addDefaultContext(root, classResolver, ctx);
                }
            }
        }
        return ctx;
    }

    public static class ClassResolverImpl implements ClassResolver {

        private final ClassLoader classLoader;

        public ClassResolverImpl(ClassLoader classLoader) {
            this.classLoader = classLoader;
        }

        public Class<?> classForName(String className, @SuppressWarnings("rawtypes") Map ctx) throws ClassNotFoundException {
            try {
                return classLoader.loadClass(className);
            } catch (ClassNotFoundException ex) {
                int dot = className.indexOf('.');
                if (dot < 0) {
                    return classLoader.loadClass("java.lang." + className);
                } else {
                    throw ex;
                }
            }
        }
    }
}
