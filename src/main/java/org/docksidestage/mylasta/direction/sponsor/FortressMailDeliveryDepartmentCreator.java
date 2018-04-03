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

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;

import org.dbflute.mail.CardView;
import org.dbflute.mail.Postcard;
import org.dbflute.mail.send.SMailDeliveryDepartment;
import org.dbflute.mail.send.SMailPostalMotorbike;
import org.dbflute.mail.send.SMailPostalParkingLot;
import org.dbflute.mail.send.SMailPostalPersonnel;
import org.dbflute.mail.send.embedded.personnel.SMailDogmaticPostalPersonnel;
import org.dbflute.mail.send.embedded.postie.SMailHonestPostie;
import org.dbflute.mail.send.embedded.postie.SMailPostingMessage;
import org.dbflute.mail.send.supplement.async.SMailAsyncStrategy;
import org.dbflute.mail.send.supplement.filter.SMailSubjectFilter;
import org.dbflute.mail.send.supplement.label.SMailLabelStrategy;
import org.dbflute.optional.OptionalThing;
import org.dbflute.util.DfStringUtil;
import org.dbflute.util.Srl;
import org.docksidestage.mylasta.direction.FortressConfig;
import org.docksidestage.remote.harbor.RemoteHarborBhv;
import org.docksidestage.remote.harbor.base.RemoteHbPagingReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductRowReturn;
import org.docksidestage.remote.harbor.product.RemoteHbProductSearchParam;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.ConcurrentAsyncCall;
import org.lastaflute.core.message.MessageManager;
import org.lastaflute.core.util.ContainerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class FortressMailDeliveryDepartmentCreator {

    private static final Logger logger = LoggerFactory.getLogger(FortressMailDeliveryDepartmentCreator.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final FortressConfig config;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public FortressMailDeliveryDepartmentCreator(FortressConfig config) {
        this.config = config;
    }

    // ===================================================================================
    //                                                                              Create
    //                                                                              ======
    public SMailDeliveryDepartment create() {
        return new SMailDeliveryDepartment(createPostalParkingLot(), createPostalPersonnel());
    }

    // -----------------------------------------------------
    //                                    Postal Parking Lot
    //                                    ------------------
    protected SMailPostalParkingLot createPostalParkingLot() {
        final SMailPostalParkingLot parkingLot = new SMailPostalParkingLot();
        final SMailPostalMotorbike motorbike = new SMailPostalMotorbike();
        final String hostAndPort = config.getMailSmtpServerMainHostAndPort();
        final List<String> hostPortList = DfStringUtil.splitListTrimmed(hostAndPort, ":");
        motorbike.registerConnectionInfo(hostPortList.get(0), Integer.parseInt(hostPortList.get(1)));
        motorbike.registerReturnPath(config.getMailReturnPath());
        parkingLot.registerMotorbikeAsMain(motorbike);
        return parkingLot;
    }

    // -----------------------------------------------------
    //                                      Postal Personnel
    //                                      ----------------
    protected SMailPostalPersonnel createPostalPersonnel() {
        final SMailDogmaticPostalPersonnel personnel = newMailDogmaticPostalPersonnel();
        return config.isMailSendMock() ? personnel.asTraining() : personnel;
    }

    protected SMailDogmaticPostalPersonnel newMailDogmaticPostalPersonnel() { // #ext_point e.g. from database
        final String testPrefix = config.getMailSubjectTestPrefix();
        final AsyncManager asyncManager = getAsyncManager();
        final MessageManager messageManager = getMessageManager();
        return new SMailDogmaticPostalPersonnel() {
            @Override
            protected OptionalThing<SMailSubjectFilter> createSubjectFilter() {
                return OptionalThing.of((view, subject) -> testPrefix + subject);
            }

            @Override
            protected OptionalThing<SMailAsyncStrategy> createAsyncStrategy() {
                return OptionalThing.of(new SMailAsyncStrategy() {
                    @Override
                    public void async(CardView view, Runnable runnable) {
                        asyncRunnable(asyncManager, runnable);
                    }

                    @Override
                    public boolean alwaysAsync(CardView view) {
                        return true; // as default of LastaFlute example 
                    }
                });
            }

            @Override
            protected OptionalThing<SMailLabelStrategy> createLabelStrategy() {
                return OptionalThing.of((view, locale, label) -> resolveLabelIfNeeds(messageManager, locale, label));
            }

            @Override
            protected SMailHonestPostie newMailHonestPostie(SMailPostalMotorbike motorbike) {
                return myPostie(motorbike);
            }
        };
    }

    protected SMailHonestPostie myPostie(SMailPostalMotorbike motorbike) {
        return new SMailHonestPostie(motorbike) {

            @Override
            protected void stagingSend(Postcard postcard, SMailPostingMessage message) throws MessagingException {
                if (isRemoteApiMail(postcard)) { // test remote api mail
                    requestProductList(postcard.getPushedUlteriorMap());
                } else { // normally here
                    super.stagingSend(postcard, message);
                }
            }

            protected boolean isRemoteApiMail(Postcard postcard) { // logic is too simple
                return postcard.getBodyFile().filter(file -> file.endsWith("remote_api.dfmail")).isPresent();
            }

            protected void requestProductList(Map<String, Object> ulteriorMap) { // mock of remote api mail
                RemoteHarborBhv harborBhv = ContainerUtil.getComponent(RemoteHarborBhv.class);
                RemoteHbProductSearchParam param = new RemoteHbProductSearchParam();
                param.productName = "S";
                RemoteHbPagingReturn<RemoteHbProductRowReturn> ret = harborBhv.requestProductList(param);
                logger.debug("RemoteApi Mail: allRecordCount={} ulterior={}", ret.allRecordCount, ulteriorMap);
            }
        };
    }

    // ===================================================================================
    //                                                                        Asynchronous
    //                                                                        ============
    protected void asyncRunnable(AsyncManager asyncManager, Runnable runnable) {
        asyncManager.async(new ConcurrentAsyncCall() {
            @Override
            public void callback() {
                runnable.run();
            }

            @Override
            public boolean asPrimary() {
                return true; // mail is primary business
            }
        });
    }

    // ===================================================================================
    //                                                                       Resolve Label
    //                                                                       =============
    protected String resolveLabelIfNeeds(MessageManager messageManager, Locale locale, String label) {
        return Srl.startsWith(label, "labels.", "{labels.") ? messageManager.getMessage(locale, label) : label;
    }

    // ===================================================================================
    //                                                                           Component
    //                                                                           =========
    protected MessageManager getMessageManager() {
        return ContainerUtil.getComponent(MessageManager.class);
    }

    protected AsyncManager getAsyncManager() {
        return ContainerUtil.getComponent(AsyncManager.class);
    }
}
