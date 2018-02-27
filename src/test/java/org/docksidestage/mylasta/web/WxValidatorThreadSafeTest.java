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
package org.docksidestage.mylasta.web;

import java.util.Locale;

import javax.annotation.Resource;

import org.dbflute.utflute.core.cannonball.CannonballCar;
import org.dbflute.utflute.core.cannonball.CannonballOption;
import org.dbflute.utflute.core.cannonball.CannonballRun;
import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.message.MessageManager;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.validation.ActionValidator;
import org.lastaflute.web.validation.Required;
import org.lastaflute.web.validation.VaConfigSetupper;

/**
 * @author jflute
 */
public class WxValidatorThreadSafeTest extends UnitFortressWebTestCase {

    @Resource
    private MessageManager messageManager;
    @Resource
    private RequestManager requestManager;

    public void test_basic() {
        // ## Arrange ##
        Locale locale = requestManager.getUserLocale();
        cannonball(new CannonballRun() {
            public void drive(CannonballCar car) {
                xdoPrepareWebMockContext(); // for web environment
                ActionValidator<FortressMessages> validator = createValidator(locale, conf -> {});
                MockMaihama maihama = new MockMaihama(null);

                // ## Act ##
                assertValidationError(() -> validator.validateApi(maihama, messages -> {
                    messages.saveSuccessAttribute("mystic", "hangar");
                })).handle(data -> {
                    // ## Assert ##
                    log(ln() + data.getCause().getMessages().toDisp());
                    assertEquals("hangar", data.requiredSuccessAttribute("mystic", String.class));
                    assertException(AssertionError.class, () -> data.requiredSuccessAttribute("bbb", String.class));
                });
            }
        }, new CannonballOption());
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private ActionValidator<FortressMessages> createValidator(Locale locale, VaConfigSetupper confLambda) {
        return new ActionValidator<FortressMessages>(requestManager, () -> new FortressMessages());
    }

    public static class MockMaihama {

        @Required
        public String sea;

        public MockMaihama(String sea) {
            this.sea = sea;
        }
    }
}
