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
package org.docksidestage.bizfw.masterslave.slavebasis.annostyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.dbflute.bhv.core.BehaviorCommandHook;
import org.dbflute.hook.CallbackContext;
import org.docksidestage.bizfw.masterslave.slavebasis.SlaveBasisAgent;
import org.lastaflute.db.replication.selectable.SelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * @author jflute
 */
public class SlaveBasisAnnotationAgent implements SlaveBasisAgent { // not DI component, new at your manager

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String dbfluteProjectName; // not null
    private final Class<? extends Annotation> masterAnnoType; // not null
    private final SlaveDBAccessor slaveDBAccessor; // not null
    private final SelectableDataSourceHolder selectableDataSourceHolder; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public SlaveBasisAnnotationAgent(String dbfluteProjectName, Class<? extends Annotation> masterAnnoType, SlaveDBAccessor slaveDBAccessor,
            SelectableDataSourceHolder selectableDataSourceHolder) {
        this.dbfluteProjectName = dbfluteProjectName;
        this.masterAnnoType = masterAnnoType;
        this.slaveDBAccessor = slaveDBAccessor;
        this.selectableDataSourceHolder = selectableDataSourceHolder;
    }

    // ===================================================================================
    //                                                                         Hook Before
    //                                                                         ===========
    public void beginSlaveBasis(ActionRuntime runtime) {
        // set up default data source
        final String dataSourceKey;
        if (needsMasterBasis(runtime)) {
            dataSourceKey = slaveDBAccessor.prepareMasterDataSourceKey();
        } else { // as default
            dataSourceKey = slaveDBAccessor.prepareSlaveDataSourceKey();
        }
        selectableDataSourceHolder.switchSelectableDataSourceKey(dataSourceKey);

        // block slave update by DBFlute hook
        final BehaviorCommandHook hook = createBlockHook(runtime);
        CallbackContext.setBehaviorCommandHookOnThread(hook);
    }

    private boolean needsMasterBasis(ActionRuntime runtime) {
        final Class<?> actionType = runtime.getActionType();
        if (actionType.getAnnotation(masterAnnoType) != null) {
            return true;
        }
        final Method executeMethod = runtime.getActionExecute().getExecuteMethod();
        if (executeMethod.getAnnotation(masterAnnoType) != null) {
            return true;
        }
        return false;
    }

    private BehaviorCommandHook createBlockHook(ActionRuntime runtime) {
        final BlockingSlaveUpdateHookFactory factory = createBlockHookFactory();
        return factory.createHook(runtime);
    }

    private BlockingSlaveUpdateHookFactory createBlockHookFactory() {
        return new BlockingSlaveUpdateHookFactory(dbfluteProjectName, slaveDBAccessor, selectableDataSourceHolder);
    }

    // ===================================================================================
    //                                                                        Hook Finally
    //                                                                        ============
    public void endSlaveBasis(ActionRuntime runtime) {
        // Action自身が再帰的に実行されることはないはずなので、元の値がなんだったか気にせずclearする
        selectableDataSourceHolder.switchSelectableDataSourceKey(null); // no selection

        // LastaFluteは、Action終わりに必ずCallbackContextをclearするので、ここではしなくてOK
        // (というか、ここでclearしてしまうとinheritした他のhookも消えてしまうのでclearしない方が良い)
    }
}
