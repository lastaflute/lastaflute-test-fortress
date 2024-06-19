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
package org.docksidestage.whitebox.lastaflute.meta;

import org.dbflute.utflute.core.PlainTestCase;
import org.lastaflute.meta.swagger.diff.SwaggerDiff;

/**
 * @author p1us2er0
 * @author jflute
 */
public class SwaggerDiffTest extends PlainTestCase {

    // ===================================================================================
    //                                                                    fortress example
    //                                                                    ================
    public void test_fortressswaggar2_openapi3_compare_lastaAsOld() {
        // ## Arrange ##
        String oldPath = "/swagger/fortress_lasta_presents_example.json";
        String newPath = "/swagger/fortress_openapi3_example.json";

        // ## Act ##
        String result = new SwaggerDiff().diffFromLocations(oldPath, newPath);

        // ## Assert ##
        log(result.isEmpty() ? "*no change" : result);
        // #thinking jflute maybe 'array' comparison does not work (2021/07/11)
        //assertEquals("", result);
    }

    public void test_fortressswaggar2_openapi3_compare_lastaAsNew() {
        // ## Arrange ##
        // #thinking jflute in case of OpenAPI3 is old, following exception (2021/07/11)
        // Caused by: java.lang.ClassCastException: io.swagger.v3.oas.models.media.Schema cannot be cast to io.swagger.v3.oas.models.media.ArraySchema
        //     at org.openapitools.openapidiff.core.compare.schemadiffresult.ArraySchemaDiffResult.diff(ArraySchemaDiffResult.java:26)
        //String oldPath = "/swagger/fortress_openapi3_example.json";
        //String newPath = "/swagger/fortress_lasta_presents_example.json";
        String oldPath = "/swagger/fortress_lasta_presents_example.json";
        String newPath = "/swagger/fortress_openapi3_example.json";

        // ## Act ##
        String result = new SwaggerDiff().diffFromLocations(oldPath, newPath);

        // ## Assert ##
        log(result.isEmpty() ? "*no change" : result);
        // #thinking jflute maybe 'array' comparison does not work (2021/07/11)
        //assertEquals("", result);
    }

    // ===================================================================================
    //                                                                            petstore
    //                                                                            ========
    public void test_petstore_swaggar2_openapi3_compare() {
        // ## Arrange ##
        String oldPath = "/swagger/petstore_swagger.json";
        String newPath = "/swagger/petstore_openapi.json";

        // ## Act ##
        String result = new SwaggerDiff().diffFromLocations(oldPath, newPath);

        // ## Assert ##
        log(result.isEmpty() ? "### No Change" : result);
        assertFalse("".equals(result));
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
