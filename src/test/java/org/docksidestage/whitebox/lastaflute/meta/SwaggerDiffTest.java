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
package org.docksidestage.whitebox.lastaflute.meta;

import org.dbflute.utflute.core.PlainTestCase;
import org.lastaflute.meta.diff.SwaggerDiffGenerator;

/**
 * @author p1us2er0
 */
public class SwaggerDiffTest extends PlainTestCase {

    public void test_fortressswaggar2_openapi3_compare() {
        String swaggerJsonOldPath = "/swagger/fortress_lasta_presents_example.json";
        String swaggerJsonNewPath = "/swagger/fortress_openapi3_example.json";

        SwaggerDiffGenerator swaggerDiffGenerator = new SwaggerDiffGenerator();
        String value = swaggerDiffGenerator.diffFromLocations(swaggerJsonOldPath, swaggerJsonNewPath);

        System.out.println(value.isEmpty() ? "### No Change" : value);
        assertTrue("".equals(value));
    }

    public void test_petstore_swaggar2_openapi3_compare() {
        String swaggerJsonOldPath = "/swagger/petstore_swagger.json";
        String swaggerJsonNewPath = "/swagger/petstore_openapi.json";
        SwaggerDiffGenerator swaggerDiffGenerator = new SwaggerDiffGenerator();
        String value = swaggerDiffGenerator.diffFromLocations(swaggerJsonOldPath, swaggerJsonNewPath);

        System.out.println(value.isEmpty() ? "### No Change" : value);
        assertFalse("".equals(value));
    }

    // yml is not yet supported.
    //    public void test_petstore_openapi3_json_openapi3_yml_compare() {
    //        String swaggerJsonOldPath = "/json/swagger/petstore_openapi.json";
    //        String swaggerJsonNewPath = "/json/swagger/petstore_openapi.yaml";
    //        SwaggerDiffGenerator swaggerDiffGenerator = new SwaggerDiffGenerator();
    //        String value = swaggerDiffGenerator.diffFromLocations(swaggerJsonOldPath, swaggerJsonNewPath);
    //
    //        System.out.println(value.isEmpty() ? "### No Change" : value);
    //    }
}
