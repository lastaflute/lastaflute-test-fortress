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
package org.docksidestage.bizfw.masterslave.resortlinedb.subschema_basic_example;

import org.dbflute.util.DfTypeUtil;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessorImpl;

// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// [notice as example]
// this example has sub-package but you don't need it
// you can make it like this:
// 
//  bizfw.masterslave
//    |-[schema]
//       |-...SlaveDBAccessor.java
// _/_/_/_/_/_/_/_/_/_/
/**
 * Accessor for slave-basis style of master/slave as sub schema. <br>
 * Application business classes use this to access slave for e.g. heavy select.
 * <pre>
 * features:
 *  o slave basis
 *  o no SlaveDBAccessor use
 *  o sub schema (needs schema suffix)
 *  o master switch way: annotation
 * 
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
 * </pre> 
 * @author jflute
 */
public class ResortlineSlaveDBAccessor extends SlaveDBAccessorImpl { // DI component

    @Override
    protected String mySchemaKeyword() { // needs for sub schema
        return "resortlinedb"; // data source name is e.g. masterResortlinedbDataSource
    }

    @Override
    public String toString() { // for logging
        return DfTypeUtil.toClassTitle(this) + ":{" + mySchemaKeyword() + "}";
    }
}
