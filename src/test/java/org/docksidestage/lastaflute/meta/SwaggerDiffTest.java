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
package org.docksidestage.lastaflute.meta;

import org.dbflute.utflute.core.PlainTestCase;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

/**
 * @author p1us2er0
 */
public class SwaggerDiffTest extends PlainTestCase {

    public void test_fortress_openapi3_compare() {
        OpenAPIParser openAPIParser = new OpenAPIParser();

        String swaggerJsonLasta = "./src/main/resources/swagger/fortress_lasta_presents_example_minimum.json";
        SwaggerParseResult resultLasta = openAPIParser.readLocation(swaggerJsonLasta, null, null);

        String swaggerJsonOpenApi3 = "./src/main/resources/swagger/fortress_openapi3_example_minimum.json";
        SwaggerParseResult resultOpenApi3 = openAPIParser.readLocation(swaggerJsonOpenApi3, null, null);

        assertHasZeroElement(resultLasta.getMessages());
        assertHasZeroElement(resultOpenApi3.getMessages());

        Javers javers = JaversBuilder.javers().build();
        System.out.println(javers.compare(resultLasta.getOpenAPI().getPaths(), resultOpenApi3.getOpenAPI().getPaths()));
        //        System.out.println(javers.compare(resultLasta.getOpenAPI().getComponents(), resultOpenApi3.getOpenAPI().getComponents()));
    }
}
