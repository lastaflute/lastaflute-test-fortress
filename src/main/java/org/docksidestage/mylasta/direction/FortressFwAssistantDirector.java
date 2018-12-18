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
package org.docksidestage.mylasta.direction;

import javax.annotation.Resource;

import org.docksidestage.bizfw.thymeleaf.ThymeleafConfigProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressActionAdjustmentProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressApiFailureHook;
import org.docksidestage.mylasta.direction.sponsor.FortressCookieResourceProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressCurtainBeforeHook;
import org.docksidestage.mylasta.direction.sponsor.FortressJsonResourceProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressListedClassificationProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressMailDeliveryDepartmentCreator;
import org.docksidestage.mylasta.direction.sponsor.FortressMultipartRequestHandler;
import org.docksidestage.mylasta.direction.sponsor.FortressSecurityResourceProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressTimeResourceProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressUserLocaleProcessProvider;
import org.docksidestage.mylasta.direction.sponsor.FortressUserTimeZoneProcessProvider;
import org.lastaflute.core.direction.CachedFwAssistantDirector;
import org.lastaflute.core.direction.FwAssistDirection;
import org.lastaflute.core.direction.FwCoreDirection;
import org.lastaflute.core.security.InvertibleCryptographer;
import org.lastaflute.core.security.OneWayCryptographer;
import org.lastaflute.db.dbflute.classification.ListedClassificationProvider;
import org.lastaflute.db.direction.FwDbDirection;
import org.lastaflute.thymeleaf.ThymeleafRenderingProvider;
import org.lastaflute.web.direction.FwWebDirection;
import org.lastaflute.web.ruts.multipart.MultipartResourceProvider;
import org.lastaflute.web.ruts.renderer.HtmlRenderingProvider;

/**
 * @author jflute
 */
public class FortressFwAssistantDirector extends CachedFwAssistantDirector {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private FortressConfig config;

    // ===================================================================================
    //                                                                              Assist
    //                                                                              ======
    @Override
    protected void prepareAssistDirection(FwAssistDirection direction) {
        direction.directConfig(nameList -> {
            nameList.add("fortress_config.properties");
            nameList.add("fortress_thymeleaf_config.properties");
        }, "fortress_env.properties");
    }

    // ===================================================================================
    //                                                                                Core
    //                                                                                ====
    @Override
    protected void prepareCoreDirection(FwCoreDirection direction) {
        // this configuration is on fortress_env.properties because this is true only when development
        direction.directDevelopmentHere(config.isDevelopmentHere());

        // titles of the application for logging are from configurations
        direction.directLoggingTitle(config.getDomainTitle(), config.getEnvironmentTitle());

        // this configuration is on sea_env.properties because it has no influence to production
        // even if you set true manually and forget to set false back
        direction.directFrameworkDebug(config.isFrameworkDebug()); // basically false

        // you can add your own process when your application's curtain before
        direction.directCurtainBefore(createCurtainBeforeListener());

        direction.directSecurity(createSecurityResourceProvider());
        direction.directTime(createTimeResourceProvider());
        direction.directJson(createJsonResourceProvider());
        direction.directMail(createMailDeliveryDepartmentCreator().create());
    }

    protected FortressCurtainBeforeHook createCurtainBeforeListener() {
        return new FortressCurtainBeforeHook();
    }

    protected FortressSecurityResourceProvider createSecurityResourceProvider() { // #change_it
        final InvertibleCryptographer inver = InvertibleCryptographer.createAesCipher("fortress12345678");
        final OneWayCryptographer oneWay = OneWayCryptographer.createSha256Cryptographer();
        return new FortressSecurityResourceProvider(inver, oneWay);
    }

    protected FortressTimeResourceProvider createTimeResourceProvider() {
        return new FortressTimeResourceProvider(config);
    }

    protected FortressJsonResourceProvider createJsonResourceProvider() {
        return new FortressJsonResourceProvider();
    }

    protected FortressMailDeliveryDepartmentCreator createMailDeliveryDepartmentCreator() {
        return new FortressMailDeliveryDepartmentCreator(config);
    }

    // ===================================================================================
    //                                                                                 DB
    //                                                                                ====
    @Override
    protected void prepareDbDirection(FwDbDirection direction) {
        direction.directClassification(createListedClassificationProvider());
    }

    protected ListedClassificationProvider createListedClassificationProvider() {
        return new FortressListedClassificationProvider();
    }

    // ===================================================================================
    //                                                                                Web
    //                                                                               =====
    @Override
    protected void prepareWebDirection(FwWebDirection direction) {
        direction.directRequest(createUserLocaleProcessProvider(), createUserTimeZoneProcessProvider());
        direction.directCookie(createCookieResourceProvider());
        direction.directAdjustment(createActionAdjustmentProvider());
        direction.directMessage(nameList -> nameList.add("fortress_message"), "fortress_label");
        direction.directApiCall(createApiFailureHook());
        direction.directHtmlRendering(createHtmlRenderingProvider());
        direction.directMultipart(createMultipartResourceProvider());
    }

    protected FortressUserLocaleProcessProvider createUserLocaleProcessProvider() {
        return new FortressUserLocaleProcessProvider();
    }

    protected FortressUserTimeZoneProcessProvider createUserTimeZoneProcessProvider() {
        return new FortressUserTimeZoneProcessProvider();
    }

    protected FortressCookieResourceProvider createCookieResourceProvider() { // #change_it
        final InvertibleCryptographer cr = InvertibleCryptographer.createAesCipher("12345678fortress");
        return new FortressCookieResourceProvider(config, cr);
    }

    protected FortressActionAdjustmentProvider createActionAdjustmentProvider() {
        return new FortressActionAdjustmentProvider();
    }

    protected FortressApiFailureHook createApiFailureHook() {
        return new FortressApiFailureHook();
    }

    protected HtmlRenderingProvider createHtmlRenderingProvider() {
        return new ThymeleafRenderingProvider().asDevelopment(config.isDevelopmentHere()).additionalExpression(resource -> {
            resource.registerProcessor("config", new ThymeleafConfigProvider(config));
        });
    }

    // -----------------------------------------------------
    //                                             Multipart
    //                                             ---------
    protected MultipartResourceProvider createMultipartResourceProvider() {
        return () -> new FortressMultipartRequestHandler();
    }
}
