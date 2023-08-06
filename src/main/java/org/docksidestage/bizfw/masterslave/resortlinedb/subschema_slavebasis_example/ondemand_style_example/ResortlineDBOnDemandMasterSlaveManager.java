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
package org.docksidestage.bizfw.masterslave.resortlinedb.subschema_slavebasis_example.ondemand_style_example;

import org.docksidestage.bizfw.masterslave.slavebasis.SlaveBasisAgent;
import org.docksidestage.bizfw.masterslave.slavebasis.ondestyle.SlaveBasisOnDemandAgent;
import org.lastaflute.db.replication.selectable.SelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.ruts.process.ActionRuntime;

// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// [notice as example]
// this example has sub-package but you don't need it
// you can make it like this:
// 
//  bizfw.masterslave
//   |-[schema]
//      |-...MasterSlaveManager.java
// 
// [notice as example]
// this example (reluctantly) needs style name "OnDemand" in class name
// but actually you don't need it like this:
// rename [Schema]OnDemandMasterSlaveManager to [Schema]MasterSlaveManager
// _/_/_/_/_/_/_/_/_/_/
/**
 * The manager of master/slave for the DB schema. <br>
 * this schema uses slave-basis way.
 * <pre>
 * how to set up:
 *  1. register this class to DI xml as DI component.
 *   app.xml
 *    |-dbflute.xml
 *      |-rdb.xml
 *         |-jdbc+.xml // here (if main schema)
 *            |-jdbc-[schema]-master.xml
 *            |-jdbc-[schema]-slave.xml
 *    or
 *   app.xml
 *    |-dbflute-[schema].xml
 *      |-rdb-[schema].xml
 *         |-jdbc-[schema].xml // here (if sub schema)
 *            |-jdbc-[schema]-master.xml
 *            |-jdbc-[schema]-slave.xml
 * 
 *  2. call this class in action hook of BaseAction.
 * 
 *   &#064;Resource
 *   private [Schema]MasterSlaveManager [schema]MasterSlaveManager;
 * 
 *   &#064;Override
 *   public ActionResponse hookBefore(ActionRuntime runtime) {
 *       [schema]MasterSlaveManager.beginSlaveBasis();
 *   }
 * 
 *   &#064;Override
 *   public void hookFinally(ActionRuntime runtime) {
 *       [schema]MasterSlaveManager.endSlaveBasis(runtime);
 *   }
 * </pre>
 * @author jflute
 */
public class ResortlineDBOnDemandMasterSlaveManager { // DI component

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final SlaveBasisAgent agent; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public ResortlineDBOnDemandMasterSlaveManager(SlaveDBAccessor slaveDBAccessor, SelectableDataSourceHolder selectableDataSourceHolder) { // specific point, are injected
        agent = createAgent(slaveDBAccessor, selectableDataSourceHolder);
    }

    // -----------------------------------------------------
    //                                         Agent Factory
    //                                         -------------
    private SlaveBasisAgent createAgent(SlaveDBAccessor slaveDBAccessor, SelectableDataSourceHolder selectableDataSourceHolder) {
        // you can select annotation way or on-demand way or ... here
        return new SlaveBasisOnDemandAgent(slaveDBAccessor, selectableDataSourceHolder);
    }

    // if you use annotation style
    //private Class<? extends Annotation> getAnnotationType() {
    //    return ResortlineMasterDB.class; // specific point
    //}

    // ===================================================================================
    //                                                                         Hook Before
    //                                                                         ===========
    public void beginSlaveBasis(ActionRuntime runtime) {
        agent.beginSlaveBasis(runtime);
    }

    // ===================================================================================
    //                                                                        Hook Finally
    //                                                                        ============
    public void endSlaveBasis(ActionRuntime runtime) {
        agent.endSlaveBasis(runtime);
    }
}