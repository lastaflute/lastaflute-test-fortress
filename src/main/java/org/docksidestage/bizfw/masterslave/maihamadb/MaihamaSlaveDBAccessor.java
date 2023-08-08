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

import org.dbflute.util.DfTypeUtil;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessorImpl;

/**
 * Accessor for slave-basis style of master/slave as main schema.
 * <pre>
 * features:
 *  o slave basis
 *  o no SlaveDBAccessor use
 *  o main schema (not need schema suffix)
 *  o master switch way: on-demand
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
public class MaihamaSlaveDBAccessor extends SlaveDBAccessorImpl { // DI component

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // [tips] basically main schema does not need this override
    // (if data source component names on DI xml are e.g. masterDataSource, slaveDataSource)
    // _/_/_/_/_/_/_/_/_/_/
    //@Override
    //protected String mySchemaKeyword() { // needs for sub schema
    //    return "maihamadb"; // data source name is e.g. masterMaihamadbDataSource
    //}

    @Override
    public String toString() { // for logging
        return DfTypeUtil.toClassTitle(this) + ":{schemaKeyword=" + mySchemaKeyword() + "}";
    }
}
