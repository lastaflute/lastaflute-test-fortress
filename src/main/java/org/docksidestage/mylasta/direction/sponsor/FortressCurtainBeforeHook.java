/*
 * Copyright 2015-2024 the original author or authors.
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

import java.util.Collection;
import java.util.TimeZone;

import org.dbflute.bhv.BehaviorReadable;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.optional.OptionalThing;
import org.dbflute.system.DBFluteSystem;
import org.dbflute.system.provider.DfFinalTimeZoneProvider;
import org.dbflute.util.DfTraceViewUtil;
import org.dbflute.util.DfTypeUtil;
import org.docksidestage.dbflute.allcommon.DBMetaInstanceHandler;
import org.docksidestage.dbflute.allcommon.ImplementedBehaviorSelector;
import org.docksidestage.mylasta.action.FortressUserBean;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.lastaflute.core.direction.CurtainBeforeHook;
import org.lastaflute.core.direction.FwAssistantDirector;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.core.util.ContainerUtil;
import org.lastaflute.web.login.LoginManager;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.validation.ActionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class FortressCurtainBeforeHook implements CurtainBeforeHook {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(FortressCurtainBeforeHook.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public FortressCurtainBeforeHook(FortressConfig config) {
        this.config = config;
    }

    // ===================================================================================
    //                                                                               Hook
    //                                                                              ======
    public void hook(FwAssistantDirector assistantDirector) {
        processDBFluteSystem();
        whiteboxtest_findLoginManager();
        whiteboxtest_prepareAccessContextForInsert();
        whiteboxtest_initializeMetaIfNeeds();
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

    protected void whiteboxtest_initializeMetaIfNeeds() { // for first access performance
        if (config.isDevelopmentHere()) {
            return; // because this process is needed in production only
        }
        long before = System.currentTimeMillis();
        initializeMeta_maihamadb(before);
        initializeMeta_hibernateValidator(before);
    }

    protected void initializeMeta_maihamadb(long before) {
        ImplementedBehaviorSelector selector = ContainerUtil.getComponent(ImplementedBehaviorSelector.class);
        selector.initializeConditionBeanMetaData(); // including behavior's warmUpCommand() of all tables
        logger.debug("Loaded DBMeta and warming up Behavior (maihamadb): {}", preparePerformanceView(before));

        // under DBFlute-1.2.3, ConditionQuery(CQ) classes are not loaded in warmUpCommand()
        // so explicitly load them here if you use the version
        Collection<DBMeta> dbmetaList = DBMetaInstanceHandler.getUnmodifiableDBMetaMap().values();
        for (DBMeta dbmeta : dbmetaList) {
            BehaviorReadable readable = selector.byName(dbmeta.getTableDbName());
            readable.newConditionBean().localCQ(); // same as query(), creating CQ instance
        }
        logger.debug("Loaded ConditionQuery classes (maihamadb): {}", preparePerformanceView(before));
    }

    protected void initializeMeta_hibernateValidator(long before) {
        RequestManager requestManager = ContainerUtil.getComponent(RequestManager.class);
        new ActionValidator<UserMessages>(requestManager, () -> new UserMessages(), ActionValidator.DEFAULT_GROUPS);
        logger.debug("Loaded Hibernate Validator classes: {}", preparePerformanceView(before));
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    protected String preparePerformanceView(long before) {
        return DfTraceViewUtil.convertToPerformanceView(System.currentTimeMillis() - before);
    }
}
