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
package org.docksidestage.app.web.wx.masterslave;

import javax.annotation.Resource;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.bizfw.masterslave.maihamadb.MaihamaSlaveDBAccessor;
import org.docksidestage.bizfw.masterslave.resortlinedb.ResortlineDBSelectableDataSourceHolder;
import org.docksidestage.bizfw.masterslave.resortlinedb.ResortlineSlaveDBAccessor;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.resola.exbhv.StationBhv;
import org.lastaflute.db.replication.selectable.ThreadLocalSelectableDataSourceHolder;
import org.lastaflute.db.replication.slavedb.SlaveDBAccessor;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxMasterslaveBasicAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxMasterslaveBasicAction.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private ProductBhv productBhv;
    @Resource
    private StationBhv stationBhv;

    @Resource
    private SlaveDBAccessor slaveDBAccessor; // ambiguos so random
    @Resource
    private MaihamaSlaveDBAccessor maihamaSlaveDBAccessor;
    @Resource
    private ResortlineSlaveDBAccessor resortlineSlaveDBAccessor;

    @Resource
    private ThreadLocalSelectableDataSourceHolder maihamaDBSelectableDataSourceHolder; // ambiguos so random
    @Resource
    private ResortlineDBSelectableDataSourceHolder resortlineDBSelectableDataSourceHolder;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/masterslave/basic/
    @Execute
    public JsonResponse<Void> index() {
        showVisualCheck();
        return JsonResponse.asEmptyBody();
    }

    private void showVisualCheck() {
        slaveDBAccessor.accessFixedly(() -> {
            // maihamadb here but basically random, it depends on including order
            //  e.g. dbflute.xml and dbflute-resola.xml in app.xml
            logger.debug("#masterslave first // by slaveDBAccessor");
            logger.debug("maihamadb: " + maihamaDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
            logger.debug("resortlinedb: " + resortlineDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
            productBhv.selectByPK(1).get();
            stationBhv.selectByPK(1).get();
            return null;
        });

        logger.debug("#masterslave second // no accessor");
        logger.debug("maihamadb: " + maihamaDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
        logger.debug("resortlinedb: " + resortlineDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());

        maihamaSlaveDBAccessor.accessFixedly(() -> {
            logger.debug("#masterslave third // by maihamaSlaveDBAccessor");
            logger.debug("maihamadb: " + maihamaDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
            logger.debug("resortlinedb: " + resortlineDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
            productBhv.selectByPK(1).get();
            stationBhv.selectByPK(1).get();
            return null;
        });

        logger.debug("#masterslave fourth // no accessor");
        logger.debug("maihamadb: " + maihamaDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
        logger.debug("resortlinedb: " + resortlineDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());

        resortlineSlaveDBAccessor.accessFixedly(() -> {
            logger.debug("#masterslave fifth // by resortlineSlaveDBAccessor");
            logger.debug("maihamadb: " + maihamaDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
            logger.debug("resortlinedb: " + resortlineDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
            productBhv.selectByPK(1).get();
            stationBhv.selectByPK(1).get();
            return null;
        });

        logger.debug("#masterslave sixth // no accessor");
        logger.debug("maihamadb: " + maihamaDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
        logger.debug("resortlinedb: " + resortlineDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
    }
}
