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
package org.docksidestage.bizfw.masterslave.maihamadb.basic_example;

import org.lastaflute.db.replication.slavedb.SlaveDBAccessorImpl;

// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// [tips] this example has sub-package but you don't need it
// you can make it like this:
// 
//  bizfw.masterslave
//    |-maihamadb
//       |-...SlaveDBAccessor.java
// _/_/_/_/_/_/_/_/_/_/
/**
 * Accessor for Standard style of master/slave and main schema. <br>
 * Application business classes use this to access slave for e.g. heavy select.
 * <pre>
 * features:
 *  o master basis
 *  o SlaveDBAccessor use
 *  o main schema (no schema suffix)
 * 
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
public class MaihamaSlaveDBAccessor extends SlaveDBAccessorImpl {

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // [tips] this extended class is not required if the schema is the only one DB in the service
    // (injection by SlaveDBAccessor interface becomes ambiguos when multiple DB)
    // _/_/_/_/_/_/_/_/_/_/

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // [tips] no schema suffix as main schema
    // (however you can use explicit suffix even if main schema)
    // _/_/_/_/_/_/_/_/_/_/
    //@Override
    //protected String mySchemaKeyword() {
    //    return "maihamadb"; // data source name is e.g. masterMaihamadbDataSource
    //}
}
