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

import org.lastaflute.web.ruts.process.ActionRuntime;

/**
 * The agent to provide slave basis way.
 * <pre>
 * e.g. maihamadb as slave basis
 * 
 * schema classes:
 *  bizfw.masterslave
 *   |-maihamadb
 *      |-BackstageMaihamaSlaveDBAccessor
 *      |-MaihamaDBMasterSlaveManager
 *      |-MaihamaDBSelectableDataSourceHolder
 *      |-MaihamaMasterDB // if annotation style
 * 
 * call flow:
 *  [App]BaseAction
 *    | (at hookBefore(), hookFinally())
 *    |
 *    |-calls MaihamaDBMasterSlaveManager (DI)
 *              |  |
 *              |  |-accept BackstageMaihamaSlaveDBAccessor (DI)
 *              |  |-accept MaihamaDBSelectableDataSourceHolder (DI)
 *              |                   ^
 *              |                   | uses
 *              |                   |
 *              |-calls SlaveBasisAgent (new)
 * </pre>
 * @author jflute
 */
public interface SlaveBasisAgent { // called by your master/slave manager

    // ===================================================================================
    //                                                                         Hook Before
    //                                                                         ===========
    /**
     * Begin slave basis process in request beginning. <br>
     * Called by hookBefore() of application action (basically [App]BaseAction).
     * @param runtime The runtime object of currently-requested action. (NotNull)
     */
    void beginSlaveBasis(ActionRuntime runtime);

    // ===================================================================================
    //                                                                        Hook Finally
    //                                                                        ============
    /**
     * End slave basis process in request ending. <br>
     * Called by hookFinally() of application action (basically [App]BaseAction).
     * @param runtime The runtime object of currently-requested action. (NotNull)
     */
    void endSlaveBasis(ActionRuntime runtime);
}
