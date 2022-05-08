/*
 * Copyright 2015-2021 the original author or authors.
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
package org.docksidestage.whitebox.db.dbflute;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.dbflute.helper.filesystem.FileTextIO;
import org.dbflute.helper.message.ExceptionMessageBuilder;
import org.dbflute.utflute.core.PlainTestCase;
import org.dbflute.util.DfReflectionUtil;
import org.dbflute.util.Srl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 * @since DBFlute-1.2.4 (at roppongi japanese)
 */
public class FreeGenGsonJsonEngineTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_fromJson_basic() {
        DfFrgGsonJsonEngine engine = createEngine();

        // ## Act ##
        Object result = engine.fromJson("test request", "test file", "{ \"sea\" = \"mystic\" }");

        // ## Assert ##
        assertTrue(result instanceof Map<?, ?>);
        log(result);
        Map<?, ?> map = (Map<?, ?>) result;
        assertEquals("mystic", map.get("sea"));
    }

    public void test_fromJson_nested() {
        DfFrgGsonJsonEngine engine = createEngine();

        // ## Act ##
        Object result = engine.fromJson("test request", "test file", "{ \"sea\" = [\"mystic\", \"over\"] }");

        // ## Assert ##
        assertTrue(result instanceof Map<?, ?>);
        log(result);
        Map<?, ?> map = (Map<?, ?>) result;
        assertEquals(Arrays.asList("mystic", "over"), map.get("sea"));
    }

    public void test_fromJson_noBrace() {
        // ## Arrange ##
        DfFrgGsonJsonEngine engine = createEngine();

        // ## Act ##
        // ## Assert ##
        assertException(IllegalStateException.class, () -> {
            engine.fromJson("test request", "test file", "\"sea\" = \"mystic\"");
        });
    }

    // ===================================================================================
    //                                                                           Real File
    //                                                                           =========
    public void test_fromJson_realFile_lastadocJson() throws IOException {
        DfFrgGsonJsonEngine engine = createEngine();
        String relativeFilePath = "lastadoc/ut-analyzed-lastadoc.json";
        String json = readTestJson(relativeFilePath);

        // ## Act ##
        Object result = engine.fromJson("test request", relativeFilePath, json);

        // ## Assert ##
        assertTrue(result instanceof Map<?, ?>);
        log(Srl.cut(result.toString(), 300, "..."));
        @SuppressWarnings("unchecked")
        Map<String, ?> lastadocMap = (Map<String, ?>) result;
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> actionDocMetaList = (List<Map<String, Object>>) lastadocMap.get("actionDocMetaList");
        Map<String, Object> firstMap = actionDocMetaList.get(0);
        String url = (String) firstMap.get("url");
        assertEquals("/logout/", url);
    }

    public void test_fromJson_realFile_swaggerJson() throws IOException {
        DfFrgGsonJsonEngine engine = createEngine();
        String relativeFilePath = "lastadoc/ut-swagger.json";
        String json = readTestJson(relativeFilePath);

        // ## Act ##
        Object result = engine.fromJson("test request", relativeFilePath, json);

        // ## Assert ##
        assertTrue(result instanceof Map<?, ?>);
        log(Srl.cut(result.toString(), 300, "..."));
        @SuppressWarnings("unchecked")
        Map<String, ?> lastadocMap = (Map<String, ?>) result;
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> actionDocMetaList = (List<Map<String, Object>>) lastadocMap.get("tags");
        Map<String, Object> firstMap = actionDocMetaList.get(0);
        String name = (String) firstMap.get("name");
        assertEquals("logout", name);
    }

    public void test_fromJson_realFile_remoteapiJson_maihama_showbase() throws IOException {
        DfFrgGsonJsonEngine engine = createEngine();
        String relativeFilePath = "remoteapi/ut-remoteapi_schema_maihama-showbase_swagger.json";
        String json = readTestJson(relativeFilePath);

        // ## Act ##
        Object result = engine.fromJson("test request", relativeFilePath, json);

        // ## Assert ##
        assertTrue(result instanceof Map<?, ?>);
        log(Srl.cut(result.toString(), 300, "..."));
        @SuppressWarnings("unchecked")
        Map<String, ?> remoteapiMap = (Map<String, ?>) result;
        @SuppressWarnings("unchecked")
        Map<String, Object> pathsMap = (Map<String, Object>) remoteapiMap.get("paths");
        @SuppressWarnings("unchecked")
        Map<String, Object> firstMap = (Map<String, Object>) pathsMap.get("/product/list/search");
        assertFalse(firstMap.isEmpty()); // enough for now
    }

    public void test_fromJson_realFile_remoteapiJson_tricky() throws IOException {
        DfFrgGsonJsonEngine engine = createEngine();
        String relativeFilePath = "remoteapi/ut-remoteapi_schema_swagger-tricky_swagger.json";
        String json = readTestJson(relativeFilePath);

        // ## Act ##
        Object result = engine.fromJson("test request", relativeFilePath, json);

        // ## Assert ##
        assertTrue(result instanceof Map<?, ?>);
        log(Srl.cut(result.toString(), 300, "..."));
        @SuppressWarnings("unchecked")
        Map<String, ?> remoteapiMap = (Map<String, ?>) result;
        @SuppressWarnings("unchecked")
        Map<String, Object> pathsMap = (Map<String, Object>) remoteapiMap.get("paths");
        @SuppressWarnings("unchecked")
        Map<String, Object> firstMap = (Map<String, Object>) pathsMap.get("/hellsuffix/reservedsuffixjust/");
        assertFalse(firstMap.isEmpty()); // enough for now
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private DfFrgGsonJsonEngine createEngine() {
        DfFrgGsonJsonEngine engine = new DfFrgGsonJsonEngine();
        assertTrue(engine.determineFromJsonAvailable());
        return engine;
    }

    private String readTestJson(String relativeFilePath) throws IOException {
        String canonicalPath = getTestCaseBuildDir().getCanonicalPath();
        String targetPath = canonicalPath + "/json/" + relativeFilePath;
        return new FileTextIO().encodeAsUTF8().read(targetPath);
    }

    // ===================================================================================
    //                                                                         Test Target
    //                                                                         ===========
    // *copied from DBFlute Engine (make it be static)
    public static class DfFrgGsonJsonEngine {

        // ===================================================================================
        //                                                                          Definition
        //                                                                          ==========
        private static final Logger _log = LoggerFactory.getLogger(DfFrgGsonJsonEngine.class);

        protected static Class<?> _gsonType;
        protected static Object _gsonObj;
        protected static boolean _typeInitialized;

        protected static Method _fromJsonMethod;
        protected static boolean _methodInitialized;

        // ===================================================================================
        //                                                                          Initialize
        //                                                                          ==========
        protected void initializeFromJsonMethodIfNeeds() {
            synchronized (DfFrgGsonJsonEngine.class) {
                if (_methodInitialized || _fromJsonMethod != null) {
                    return;
                }
                initializeGsonTypeIfNeeds();
                if (_gsonType == null) { // means no Gson jar file in 'extlib' directory
                    return;
                }
                _log.info("...Finding method of Gson for FreeGen");

                // might return null if method signature is changed (however basically no way)
                final String methodName = "fromJson";
                final Class<?>[] argTypes = new Class<?>[] { String.class, Class.class };
                _fromJsonMethod = DfReflectionUtil.getPublicMethod(_gsonType, methodName, argTypes);
                if (_fromJsonMethod != null) {
                    _log.info(" -> found the {}() method of Gson: {}", methodName, _fromJsonMethod);
                } else { // basically no way, Gson may be changed
                    _log.warn(" -> *not found the {}() method of Gson in spite of having Gson type: {}", methodName, _gsonType);
                }
                _methodInitialized = true;
            }
        }

        protected void initializeGsonTypeIfNeeds() {
            synchronized (DfFrgGsonJsonEngine.class) {
                if (_typeInitialized || _gsonType != null) {
                    return;
                }
                final String gsonFqcn = "com.google.gson.Gson";
                try {
                    _log.info("...Finding Gson type for FreeGen");

                    // DfReflectionUtil uses context class loader of current thread so cannot find it
                    //_gsonType = DfReflectionUtil.forName(gsonFqcn); // not null
                    _gsonType = Class.forName(gsonFqcn); // not null

                    _log.info(" -> found the Gson type: {}", _gsonType);
                } catch (ClassNotFoundException ignored) { // e.g. no jar file in 'extlib'
                    _log.info(" -> not found the Gson type: {}", gsonFqcn);
                }
                if (_gsonType != null) {
                    try {
                        _gsonObj = DfReflectionUtil.newInstance(_gsonType);
                    } catch (RuntimeException continued) { // basically no way
                        _log.warn(" -> *cannot instantiate the Gson type: {}", _gsonType, continued);
                        _gsonType = null; // treated as not found
                    }
                }
                _typeInitialized = true;
            }
        }

        // ===================================================================================
        //                                                                           from JSON
        //                                                                           =========
        public boolean determineFromJsonAvailable() {
            initializeFromJsonMethodIfNeeds();
            return _fromJsonMethod != null;
        }

        public <RESULT> RESULT fromJson(String requestName, String resourceFile, String json) {
            if (_fromJsonMethod == null) { // basically no way
                throw new IllegalStateException("Not found the fromJsonMethod, call isFromJsonAvailable() before.");
            }
            final Object[] args = new Object[] { json, Map.class };
            try {
                @SuppressWarnings("unchecked")
                final RESULT result = (RESULT) DfReflectionUtil.invoke(_fromJsonMethod, _gsonObj, args);
                return result;
            } catch (RuntimeException e) {
                throwJsonParseFailureException(requestName, resourceFile, json, e);
                return null; // unreachable
            }
        }

        protected void throwJsonParseFailureException(String requestName, String resourceFile, String json, Exception cause) {
            final ExceptionMessageBuilder br = new ExceptionMessageBuilder();
            br.addNotice("Failed to parse the JSON file by Gson for FreeGen.");
            br.addItem("FreeGen Request");
            br.addElement(requestName);
            br.addItem("JSON File");
            br.addElement(resourceFile);
            br.addItem("Gson Method");
            br.addElement(_fromJsonMethod);
            br.addItem("Parsed JSON");
            br.addElement(json);
            final String msg = br.buildExceptionMessage();
            throw new IllegalStateException(msg, cause);
        }
    }
}
