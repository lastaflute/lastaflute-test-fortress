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
package org.docksidestage.bizfw.masterslave.slavebasis.ondestyle;

import org.dbflute.bhv.core.BehaviorCommandHook;
import org.dbflute.hook.CallbackContext;
import org.docksidestage.bizfw.masterslave.slavebasis.SlaveBasisAgent;
import org.lastaflute.db.replication.selectable.SelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * The agent of slave basis as on-demand style.
 * <pre>
 * 1. select :: slave  // are you alright?
 * 2. update :: master // switch automatically here
 * 3. select :: master // continuing
 * 4. update :: master // continuing
 * </pre>
 * @author jflute
 */
public class SlaveBasisOnDemandAgent implements SlaveBasisAgent { // not DI component, new at your manager

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final SlaveDBAccessor slaveDBAccessor; // not null
    private final SelectableDataSourceHolder selectableDataSourceHolder; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public SlaveBasisOnDemandAgent(SlaveDBAccessor slaveDBAccessor, SelectableDataSourceHolder selectableDataSourceHolder) {
        this.slaveDBAccessor = slaveDBAccessor;
        this.selectableDataSourceHolder = selectableDataSourceHolder;
    }

    // ===================================================================================
    //                                                                         Hook Before
    //                                                                         ===========
    public void beginSlaveBasis(ActionRuntime runtime) {
        // set up default data source
        final String dataSourceKey = slaveDBAccessor.prepareSlaveDataSourceKey();
        selectableDataSourceHolder.switchSelectableDataSourceKey(dataSourceKey);

        // switch master on demand by DBFlute hook
        final BehaviorCommandHook hook = createOnDemandHook(runtime);
        CallbackContext.setBehaviorCommandHookOnThread(hook);
    }

    private BehaviorCommandHook createOnDemandHook(ActionRuntime runtime) {
        final OnDemandMasterHookFactory factory = createOnDemandFactory();
        final Class<?> actionType = runtime.getActionType();
        return factory.createHook(actionType);
    }

    private OnDemandMasterHookFactory createOnDemandFactory() {
        return new OnDemandMasterHookFactory(slaveDBAccessor, selectableDataSourceHolder);
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
