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
import org.docksidestage.bizfw.masterslave.maihamadb.backstage.MaihamaDBMasterSlaveManager;
import org.docksidestage.bizfw.masterslave.resortlinedb.backstage.ResortlineDBMasterSlaveManager;
import org.docksidestage.unit.mock.SpecifiedMockActionRuntimeFactory;
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
    private MaihamaDBMasterSlaveManager maihamaDBAnnotationMasterSlaveManager;
    @Resource
    private ResortlineDBMasterSlaveManager resortlineDBAnnotationMasterSlaveManager;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    public void setUp() throws Exception {
        super.setUp();
        ActionRuntime runtime = getMockJsonRuntime(); // only for logging so either (json or html)
        maihamaDBAnnotationMasterSlaveManager.beginSlaveBasis(runtime);
        resortlineDBAnnotationMasterSlaveManager.beginSlaveBasis(runtime);
    }

    @Override
    public void tearDown() throws Exception {
        ActionRuntime runtime = getMockJsonRuntime(); // me too
        maihamaDBAnnotationMasterSlaveManager.endSlaveBasis(runtime);
        resortlineDBAnnotationMasterSlaveManager.endSlaveBasis(runtime);
        super.tearDown();
    }

    // #needs_fix jflute make mock runtime by original action type (2023/08/06)
    @Override
    protected ActionRuntime getMockJsonRuntime() {
        Class<?> actionType = getMockActionType();
        if (actionType != null) {
            return new SpecifiedMockActionRuntimeFactory(actionType, getName()).createJsonRuntime();
        } else {
            return super.getMockJsonRuntime();
        }
    }

    protected Class<?> getMockActionType() { // you can override
        return null; // as default
    }
}
