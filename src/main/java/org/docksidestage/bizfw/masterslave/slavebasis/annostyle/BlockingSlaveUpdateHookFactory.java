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

import org.dbflute.bhv.core.BehaviorCommandHook;
import org.dbflute.bhv.core.BehaviorCommandMeta;
import org.dbflute.helper.message.ExceptionMessageBuilder;
import org.lastaflute.db.replication.selectable.SelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * The factory to create BehaviorCommand hook blocking update of slave DB.
 * @author jflute
 */
public class BlockingSlaveUpdateHookFactory {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String dbfluteProjectName; // not null
    private final SlaveDBAccessor slaveDBAccessor; // not null
    private final SelectableDataSourceHolder selectableDataSourceHolder; // not null

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BlockingSlaveUpdateHookFactory(String dbfluteProjectName, SlaveDBAccessor slaveDBAccessor,
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
        return new BlockingSlaveUpdateHook(runtime);
    }

    public class BlockingSlaveUpdateHook implements BehaviorCommandHook {

        protected final ActionRuntime runtime;

        public BlockingSlaveUpdateHook(ActionRuntime runtime) {
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
                    throwNonSelectCommandButSlaveDBException(runtime, meta);
                }
            }
        }

        private void throwNonSelectCommandButSlaveDBException(ActionRuntime runtime, BehaviorCommandMeta meta) {
            final ExceptionMessageBuilder br = new ExceptionMessageBuilder();
            br.addNotice("Non-select command but slave DB now.");
            br.addItem("Advice");
            br.addElement("You cannot update slave DB.");
            br.addElement("Don't forget XxxMasterDB annotation.");
            br.addElement("For example:");
            br.addElement("  @XxxMasterDB");
            br.addElement("  public class YourAction extends ... {");
            br.addElement("      ...");
            br.addElement("  }");
            br.addElement("  ");
            br.addElement("   or");
            br.addElement("  ");
            br.addElement("  public class YourAction extends ... {");
            br.addElement("  ");
            br.addElement("      @XxxMasterDB");
            br.addElement("      public JsonResponse... update(...) {");
            br.addElement("          ...");
            br.addElement("      }");
            br.addElement("  }");
            br.addItem("Action");
            br.addElement(runtime);
            br.addItem("Behavior");
            br.addElement(meta);
            throw new NonSelectCommandButSlaveDBException("You cannot update slave DB: " + meta);
        }

        public void hookFinally(BehaviorCommandMeta meta, RuntimeException cause) {
            if (!matchesDBFluteProject(meta)) { // different schema
                return; // determinate just in case even if nothing to do
            }
            // basically do nothing for now
        }

        protected boolean matchesDBFluteProject(BehaviorCommandMeta meta) {
            return dbfluteProjectName.equals(meta.getProjectName());
        }
    }

    public static class NonSelectCommandButSlaveDBException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public NonSelectCommandButSlaveDBException(String msg) {
            super(msg);
        }
    }
}
