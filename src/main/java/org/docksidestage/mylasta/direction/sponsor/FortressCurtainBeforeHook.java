/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.mylasta.direction.sponsor;

import java.util.TimeZone;

import org.dbflute.optional.OptionalThing;
import org.dbflute.system.DBFluteSystem;
import org.dbflute.system.provider.DfFinalTimeZoneProvider;
import org.dbflute.util.DfTypeUtil;
import org.docksidestage.mylasta.action.FortressUserBean;
import org.lastaflute.core.direction.CurtainBeforeHook;
import org.lastaflute.core.direction.FwAssistantDirector;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.web.login.LoginManager;
import org.lastaflute.web.servlet.request.RequestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class FortressCurtainBeforeHook implements CurtainBeforeHook {

    private static final Logger logger = LoggerFactory.getLogger(FortressCurtainBeforeHook.class);

    // ===================================================================================
    //                                                                               Hook
    //                                                                              ======
    public void hook(FwAssistantDirector assistantDirector) {
        processDBFluteSystem();
        whiteboxtest_findLoginManager();
        whiteboxtest_prepareAccessContextForInsert();
    }

    // ===================================================================================
    //                                                                      DBFlute System
    //                                                                      ==============
    protected void processDBFluteSystem() {
        DBFluteSystem.unlock();
        DBFluteSystem.setFinalTimeZoneProvider(createFinalTimeZoneProvider());
        DBFluteSystem.lock();
    }

    protected DfFinalTimeZoneProvider createFinalTimeZoneProvider() {
        return new DfFinalTimeZoneProvider() {
            protected final TimeZone provided = FortressUserTimeZoneProcessProvider.centralTimeZone;

            public TimeZone provide() {
                return provided;
            }

            @Override
            public String toString() {
                return DfTypeUtil.toClassTitle(this) + ":{" + provided.getID() + "}";
            }
        };
    }

    // ===================================================================================
    //                                                                       Whitebox Test
    //                                                                       =============
    protected void whiteboxtest_findLoginManager() {
        OptionalThing<LoginManager> thing = ContainerUtil.getComponent(RequestManager.class).findLoginManager(FortressUserBean.class);
        logger.debug("findLoginManager() when booting: {}", thing);
    }

    protected void whiteboxtest_prepareAccessContextForInsert() {
        // enable this only when test
        //AsyncManager asyncManager = ContainerUtil.getComponent(AsyncManager.class);
        //TransactionStage stage = ContainerUtil.getComponent(TransactionStage.class);
        //PurchasePaymentBhv purchasePaymentBhv = ContainerUtil.getComponent(PurchasePaymentBhv.class);
        //TimeManager timeManager = ContainerUtil.getComponent(TimeManager.class);
        //AccessContext accessContext = new AccessContext();
        //accessContext.setAccessLocalDateTimeProvider(() -> timeManager.currentDateTime());
        //accessContext.setAccessUser("whiteboxtest");
        //PreparedAccessContext.setAccessContextOnThread(accessContext);
        //try {
        //    asyncManager.async(() -> {
        //        stage.requiresNew(tx -> {
        //            PurchasePayment payment = new PurchasePayment();
        //            payment.setPurchaseId(1L);
        //            payment.setPaymentMethodCode_BankTransfer();
        //            payment.setPaymentDatetime(timeManager.currentDateTime());
        //            payment.setPaymentAmount(new BigDecimal(88));
        //            purchasePaymentBhv.insert(payment);
        //        });
        //    });
        //} finally {
        //    PreparedAccessContext.clearAccessContextOnThread();
        //}
    }
}
