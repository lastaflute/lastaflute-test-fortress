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
package org.docksidestage.bizfw.masterslave.maihamadb;

import org.docksidestage.bizfw.masterslave.slavebasis.SlaveBasisAgent;
import org.lastaflute.db.replication.selectable.SelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * @author jflute
 */
public class MaihamaDBMasterSlaveManager {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final SlaveBasisAgent agent;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public MaihamaDBMasterSlaveManager(SlaveDBAccessor slaveDBAccessor, SelectableDataSourceHolder selectableDataSourceHolder) {
        agent = createAgent(slaveDBAccessor, selectableDataSourceHolder);
    }

    private SlaveBasisAgent createAgent(SlaveDBAccessor slaveDBAccessor, SelectableDataSourceHolder selectableDataSourceHolder) {
        return new SlaveBasisAgent(MaihamaMasterDB.class, slaveDBAccessor, selectableDataSourceHolder);
    }

    // ===================================================================================
    //                                                                         Hook Before
    //                                                                         ===========
    public void beginSlaveBasis(Class<?> actionType) {
        agent.beginSlaveBasis(actionType);
    }

    // ===================================================================================
    //                                                                        Hook Finally
    //                                                                        ============
    public void endSlaveBasis(ActionRuntime runtime) {
        agent.endSlaveBasis(runtime);
    }
}
