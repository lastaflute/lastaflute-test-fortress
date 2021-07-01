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
package org.docksidestage.mylasta;

import java.util.Set;

import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.FortressTomcatBoot;
import org.docksidestage.app.web.SwaggerAction;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.meta.agent.yourswagger.YourSwaggerSyncOption;

/**
 * @author jflute
 */
public class FortressLastaDocTest extends UnitFortressBasicTestCase {

    @Override
    protected String prepareMockContextPath() {
        return FortressTomcatBoot.CONTEXT; // basically for swagger
    }

    public void test_document() throws Exception {
        saveLastaDocMeta();
    }

    public void test_swaggerJson() throws Exception {
        saveSwaggerMeta(new SwaggerAction());

        // same json so no diff
        //verifyYourSwaggerSync("./target/lastadoc/swagger.json", op -> {});

        // having a little changed diff (with many deleted)
        //verifyYourSwaggerSync("/swagger/fortress_openapi3_example.json", op -> customizeDiff(op));
    }

    protected void customizeDiff(YourSwaggerSyncOption op) { // test for customization
        // you can select items for differences
        // almost ignore "Changed" differences
        Set<String> exceptSet = DfCollectionUtil.newHashSet(); // e.g. summary is except by default
        exceptSet.add("format");
        exceptSet.add("required");
        exceptSet.add("parameters");
        exceptSet.add("requestBody");
        exceptSet.add("responses");
        op.deriveTargetNodeAnd((path, name) -> { // default and your determination
            return !exceptSet.contains(name);
        });

        op.ignorePathTrailingSlash(); // if your swagger.json doesn't have trailing slash
        op.asLoggingIfNewOnly(); // if new only case is normal situation in development 
    }
}
