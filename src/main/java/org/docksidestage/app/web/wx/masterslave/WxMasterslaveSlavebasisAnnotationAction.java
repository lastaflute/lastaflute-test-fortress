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
import org.docksidestage.bizfw.masterslave.maihamadb.mainschema_slavebasis_example.annotation_style_example.MaihamaMasterDB;
import org.docksidestage.bizfw.masterslave.resortlinedb.subschema_slavebasis_example.ResortlineDBSelectableDataSourceHolder;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.dbflute.resola.exbhv.StationBhv;
import org.lastaflute.db.replication.selectable.ThreadLocalSelectableDataSourceHolder;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxMasterslaveSlavebasisAnnotationAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxMasterslaveSlavebasisAnnotationAction.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private ProductBhv productBhv;
    @Resource
    private StationBhv stationBhv;

    @Resource
    private ThreadLocalSelectableDataSourceHolder maihamaDBSelectableDataSourceHolder;
    @Resource
    private ResortlineDBSelectableDataSourceHolder resortlineDBSelectableDataSourceHolder;

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // http://localhost:8151/fortress/wx/masterslave/slavebasis/annotation/
    @MaihamaMasterDB
    @Execute
    public JsonResponse<Void> index() {
        productBhv.selectByPK(1);
        stationBhv.selectByPK(1);

        logger.debug("#masterslave");
        logger.debug("maihamadb: " + maihamaDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());
        logger.debug("resortlinedb: " + resortlineDBSelectableDataSourceHolder.getCurrentSelectableDataSourceKey());

        productBhv.selectByPK(1);
        stationBhv.selectByPK(1);

        return JsonResponse.asEmptyBody();
    }
}
