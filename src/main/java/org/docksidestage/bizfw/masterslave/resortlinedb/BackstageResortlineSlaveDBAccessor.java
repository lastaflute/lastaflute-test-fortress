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
package org.docksidestage.bizfw.masterslave.resortlinedb;

import org.dbflute.util.DfTypeUtil;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessorImpl;

/**
 * Accessor for slave-basis style of master/slave as sub schema. <br>
 * Application business classes don't need to use this. (so "backstage" prefix) <br>
 * This accessor is used only for framework logic.
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
public class BackstageResortlineSlaveDBAccessor extends SlaveDBAccessorImpl { // DI component

    @Override
    protected String mySchemaKeyword() { // needs for sub schema
        return "resola"; // data source name is e.g. masterResolaDataSource
    }

    @Override
    public String toString() { // for logging
        return DfTypeUtil.toClassTitle(this) + ":{" + mySchemaKeyword() + "}";
    }
}
