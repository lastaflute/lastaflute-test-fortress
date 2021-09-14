/*
 * Copyright 2015-2021 the original author or authors.
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
package org.docksidestage.whitebox.lastadi.naming;

import org.docksidestage.app.application.ballet.BalletPlieService;
import org.docksidestage.app.application.jazz.JazzStepService;
import org.docksidestage.app.service.ballet.BalletChangementService;
import org.docksidestage.app.service.jazz.JazzWalkService;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.di.core.exception.ComponentNotFoundException;

/**
 * @author jflute
 */
public class WxAppServiceCreatorTest extends UnitFortressBasicTestCase {

    public void test_application_service_canbe_component() throws Exception {
        getComponent(BalletPlieService.class).letsPlie();
        getComponent(JazzStepService.class).letsStep();
    }

    public void test_default_service_canbe_component() throws Exception {
        assertException(ComponentNotFoundException.class, () -> {
            getComponent(BalletChangementService.class).letsChangement();
        });
        assertException(ComponentNotFoundException.class, () -> {
            getComponent(JazzWalkService.class).letsWalk();
        });
    }
}
