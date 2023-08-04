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
package org.docksidestage.bizfw.masterslave.slavebasis;

import java.lang.annotation.Annotation;

import org.dbflute.bhv.core.BehaviorCommandHook;
import org.dbflute.hook.CallbackContext;
import org.lastaflute.db.replication.selectable.SelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * @author jflute
 */
public class SlaveBasisAgent {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Class<? extends Annotation> masterAnnoType; // not null
    private final SlaveDBAccessor slaveDBAccessor; // not null
    private final SelectableDataSourceHolder selectableDataSourceHolder; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public SlaveBasisAgent(Class<? extends Annotation> masterAnnoType, SlaveDBAccessor slaveDBAccessor,
            SelectableDataSourceHolder selectableDataSourceHolder) {
        this.masterAnnoType = masterAnnoType;
        this.slaveDBAccessor = slaveDBAccessor;
        this.selectableDataSourceHolder = selectableDataSourceHolder;
    }

    // ===================================================================================
    //                                                                         Hook Before
    //                                                                         ===========
    public void beginSlaveBasis(Class<?> actionType) {
        // set up default data source
        final String dataSourceKey;
        if (needsMasterBasis(actionType)) {
            dataSourceKey = slaveDBAccessor.prepareMasterDataSourceKey();
        } else { // as default
            dataSourceKey = slaveDBAccessor.prepareSlaveDataSourceKey();
        }
        selectableDataSourceHolder.switchSelectableDataSourceKey(dataSourceKey);

        // block slave update by DBFlute hook
        final BehaviorCommandHook hook = createSlaveUpdateBlockHook(actionType);
        CallbackContext.setBehaviorCommandHookOnThread(hook);
    }

    private boolean needsMasterBasis(Class<?> actionType) {
        return actionType.getAnnotation(masterAnnoType) != null;
    }

    private BehaviorCommandHook createSlaveUpdateBlockHook(Class<?> actionType) {
        final SlaveUpdateBlockFactory factory = createSlaveUpdateBlockFactory();
        return factory.createBlockHook(actionType);
    }

    private SlaveUpdateBlockFactory createSlaveUpdateBlockFactory() {
        return new SlaveUpdateBlockFactory(slaveDBAccessor, selectableDataSourceHolder);
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
