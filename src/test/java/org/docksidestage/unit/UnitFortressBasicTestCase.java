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
package org.docksidestage.unit;

import javax.annotation.Resource;

import org.dbflute.utflute.lastaflute.WebContainerTestCase;
import org.docksidestage.bizfw.masterslave.maihamadb.mainschema_slavebasis_example.ondemand_style_example.MaihamaDBOnDemandMasterSlaveManager;
import org.docksidestage.bizfw.masterslave.resortlinedb.subschema_slavebasis_example.ondemand_style_example.ResortlineDBOnDemandMasterSlaveManager;
import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * Use like this:
 * <pre>
 * YourTest extends {@link UnitFortressBasicTestCase} {
 * 
 *     public void test_yourMethod() {
 *         <span style="color: #3F7E5E">// ## Arrange ##</span>
 *         YourAction action = new YourAction();
 *         <span style="color: #FD4747">inject</span>(action);
 * 
 *         <span style="color: #3F7E5E">// ## Act ##</span>
 *         action.submit();
 * 
 *         <span style="color: #3F7E5E">// ## Assert ##</span>
 *         assertTrue(action...);
 *     }
 * }
 * </pre>
 * @author jflute
 */
public abstract class UnitFortressBasicTestCase extends WebContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private MaihamaDBOnDemandMasterSlaveManager maihamaDBOnDemandMasterSlaveManager;
    @Resource
    private ResortlineDBOnDemandMasterSlaveManager resortlineDBOnDemandMasterSlaveManager;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    public void setUp() throws Exception {
        super.setUp();
        ActionRuntime runtime = getMockJsonRuntime(); // only for logging so either (json or html)
        maihamaDBOnDemandMasterSlaveManager.beginSlaveBasis(runtime);
        resortlineDBOnDemandMasterSlaveManager.beginSlaveBasis(runtime);
    }

    @Override
    public void tearDown() throws Exception {
        ActionRuntime runtime = getMockJsonRuntime(); // me too
        maihamaDBOnDemandMasterSlaveManager.endSlaveBasis(runtime);
        resortlineDBOnDemandMasterSlaveManager.endSlaveBasis(runtime);
        super.tearDown();
    }
}
