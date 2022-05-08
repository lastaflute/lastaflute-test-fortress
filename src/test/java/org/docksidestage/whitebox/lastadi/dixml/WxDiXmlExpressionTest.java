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
package org.docksidestage.whitebox.lastadi.dixml;

import java.lang.reflect.Field;

import org.dbflute.utflute.core.PlainTestCase;
import org.dbflute.util.DfReflectionUtil;
import org.lastaflute.di.core.LastaDiProperties;
import org.lastaflute.di.core.factory.SingletonLaContainerFactory;

/**
 * @author jflute
 */
public class WxDiXmlExpressionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            Trial Di
    //                                                                            ========
    public void test_read_trialDi_nashorn() throws Exception { // cannot work since java15
        forceInternalDebug();
        forceSpecifiedEngine("nashorn"); // name "javascript" is conflicted to rhino so specific name 
        log("...Booting Lasta Di as trial Di using nashorn");
        SingletonLaContainerFactory.setConfigPath("wx_trial_di_app.xml");
        SingletonLaContainerFactory.init(); // expects no exception
    }

    public void test_read_trialDi_rhino() throws Exception {
        forceInternalDebug();
        forceSpecifiedEngine("rhino");
        log("...Booting Lasta Di as trial Di using rhino");
        SingletonLaContainerFactory.setConfigPath("wx_trial_di_app.xml");
        SingletonLaContainerFactory.init(); // expects no exception
    }

    // ===================================================================================
    //                                                                         Test Helper
    //                                                                         ===========
    // if failure by framework change, fix it by jflute (2021/08/31)
    // (want to keep LastaDiProperties protection so did not make customization point)
    private void forceSpecifiedEngine(String engineName) {
        LastaDiProperties prop = LastaDiProperties.getInstance();
        prop.getDiXmlScriptManagedEngineName(); // initialize internal fields
        Field nameKeyField = DfReflectionUtil.getWholeField(prop.getClass(), "diXmlScriptManagedEngineNameKey");
        DfReflectionUtil.setValueForcedly(nameKeyField, prop, engineName);
    }

    private void forceInternalDebug() {
        LastaDiProperties prop = LastaDiProperties.getInstance();
        Field debugField = DfReflectionUtil.getWholeField(prop.getClass(), "internalDebug");
        DfReflectionUtil.setValueForcedly(debugField, prop, true);
    }
}
