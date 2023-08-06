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
package org.docksidestage.bizfw.masterslave.maihamadb.slavebasis_example;

import org.lastaflute.db.replication.selectable.ThreadLocalSelectableDataSourceHolder;

/**
 * The holder of selectable data source for the DB schema. <br>
 * Using specific thread local instance so this extends the framework class.
 * <pre>
 * how to set up:
 *  1. register this class to DI xml as DI component.
 *   app.xml
 *    |-rdb-[schema].xml
 *       |-jdbc-[schema].xml // here
 *          |-jdbc-[schema]-master.xml
 *          |-jdbc-[schema]-slave.xml
 * </pre>
 * @author jflute
 */
public class MaihamaDBSelectableDataSourceHolder extends ThreadLocalSelectableDataSourceHolder {

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // do nothing here
    // the thread local object of super class is managed as instance variable
    // so creating holder instance per schema is enough
    // _/_/_/_/_/_/_/_/_/_/
}
