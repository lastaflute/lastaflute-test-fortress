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
import org.dbflute.bhv.core.BehaviorCommandMeta;
import org.lastaflute.db.replication.selectable.SelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.ruts.process.ActionRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The factory to create BehaviorCommand hook switching master on demand.
 * @author jflute
 */
public class OnDemandMasterHookFactory {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(OnDemandMasterHookFactory.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String dbfluteProjectName; // not null
    private final SlaveDBAccessor slaveDBAccessor; // not null
    private final SelectableDataSourceHolder selectableDataSourceHolder; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public OnDemandMasterHookFactory(String dbfluteProjectName, SlaveDBAccessor slaveDBAccessor,
            SelectableDataSourceHolder selectableDataSourceHolder) {
        this.dbfluteProjectName = dbfluteProjectName;
        this.slaveDBAccessor = slaveDBAccessor;
        this.selectableDataSourceHolder = selectableDataSourceHolder;
    }

    // ===================================================================================
    //                                                                              Create
    //                                                                              ======
    /**
     * @param runtime The runtime object of currently-requested action for (basically) logging. (NotNull)
     * @return The new-created hook, which is inheritable. (NotNull)
     */
    public BehaviorCommandHook createHook(ActionRuntime runtime) {
        return new OnDemandMasterHook(runtime);
    }

    public class OnDemandMasterHook implements BehaviorCommandHook {

        protected final ActionRuntime runtime;

        public OnDemandMasterHook(ActionRuntime runtime) {
            this.runtime = runtime;
        }

        public void hookBefore(BehaviorCommandMeta meta) {
            if (!matchesDBFluteProject(meta)) { // different schema
                return;
            }
            if (!meta.isSelect()) { // e.g. insert, update
                final String masterKey = slaveDBAccessor.prepareMasterDataSourceKey();
                final String currentKey = selectableDataSourceHolder.getCurrentSelectableDataSourceKey();
                if (!masterKey.equals(currentKey)) { // slave now
                    if (logger.isDebugEnabled()) {
                        logger.debug(buildForcedMasterHookDebugMessage(masterKey, runtime));
                    }
                    selectableDataSourceHolder.switchSelectableDataSourceKey(masterKey);
                }
            }
        }

        protected String buildForcedMasterHookDebugMessage(String masterKey, ActionRuntime runtime) {
            // basically SlaveDBAccessor class name teach us schema
            // but overriding slaveDBAccessor toString() is recommended for this logging
            final String rearExp = " in " + runtime.getActionExecute().toSimpleMethodExp();
            return "...Accessing to MasterDB for " + slaveDBAccessor + " forcedly by the key: " + masterKey + rearExp;
        }

        public void hookFinally(BehaviorCommandMeta meta, RuntimeException cause) {
            if (!matchesDBFluteProject(meta)) { // different schema
                return;
            }
            // no need to restore here
            // because also select after update needs to access master until request end
            // e.g. one Action flow
            //  1. select // slave
            //  2. update // master (forcedly)
            //  3. select // master
            //  (4. update) // master
        }

        protected boolean matchesDBFluteProject(BehaviorCommandMeta meta) {
            return dbfluteProjectName.equals(meta.getProjectName());
        }
    }
}
