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

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Configuration;
import javax.validation.UnexpectedTypeException;
import javax.validation.constraints.Size;

import org.docksidestage.bizfw.validation.SizeValidatorForImmutableList;
import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.lastaflute.core.message.MessageManager;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.validation.ActionValidator;
import org.lastaflute.web.validation.VaConfigSetupper;
import org.lastaflute.web.validation.exception.ValidationStoppedException;

/**
 * @author jflute
 */
public class WxValidatorYourCollectionTest extends UnitFortressWebTestCase {

    @Resource
    private MessageManager messageManager;
    @Resource
    private RequestManager requestManager;

    public void test_immutableList_basic() {
        // ## Arrange ##
        Locale locale = requestManager.getUserLocale();
        ActionValidator<FortressMessages> validator = createValidator(locale, conf -> prepareImmutableList(conf));
        List<Integer> seaList = newArrayList(1, 2);
        ImmutableList<Integer> landList = Lists.immutable.withAll(newArrayList(3, 4));
        MockMaihama maihama = new MockMaihama(seaList, landList, "dstore");

        // ## Act ##
        assertValidationError(() -> validator.validateApi(maihama, unused -> {})).handle(data -> {
            // ## Assert ##
            UserMessages messages = data.getCause().getMessages();
            log(ln() + messages.toDisp());
            data.requiredMessageOf("seaList", Size.class);
            data.requiredMessageOf("landList", Size.class);
            data.requiredMessageOf("piari", Size.class);
            assertTrue(messages.hasMessageOf("seaList"));
            assertTrue(messages.hasMessageOf("landList"));
            assertTrue(messages.hasMessageOf("piari"));
        });
    }

    public void test_immutableList_failure() {
        // ## Arrange ##
        Locale locale = requestManager.getUserLocale();
        ActionValidator<FortressMessages> validator = createValidator(locale, conf -> {});
        List<Integer> seaList = newArrayList(1, 2);
        ImmutableList<Integer> landList = Lists.immutable.withAll(newArrayList(3, 4));
        MockMaihama maihama = new MockMaihama(seaList, landList, "dstore");

        // ## Act ##
        // ## Assert ##
        assertException(ValidationStoppedException.class, () -> validator.validateApi(maihama, unused -> {})).handle(cause -> {
            assertEquals(UnexpectedTypeException.class, cause.getCause().getClass());
        });
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private ActionValidator<FortressMessages> createValidator(Locale locale, VaConfigSetupper confLambda) {
        return new ActionValidator<FortressMessages>(requestManager, () -> new FortressMessages()) {
            @Override
            protected VaConfigSetupper adjustValidatorConfig() {
                return confLambda;
            }

            @Override
            protected boolean isSuppressHibernateValidatorCache() {
                return true;
            }
        };
    }

    public static class MockMaihama {

        @Size(max = 1)
        public List<Integer> seaList;

        @Size(max = 1)
        public ImmutableList<Integer> landList;

        @Size(max = 2)
        public String piari;

        public MockMaihama(List<Integer> seaList, ImmutableList<Integer> landList, String piari) {
            this.seaList = seaList;
            this.landList = landList;
            this.piari = piari;
        }
    }

    private void prepareImmutableList(Configuration<?> configuration) {
        if (configuration instanceof HibernateValidatorConfiguration) {
            DefaultConstraintMapping mapping = new DefaultConstraintMapping();
            mapping.constraintDefinition(Size.class).validatedBy(SizeValidatorForImmutableList.class);
            ((HibernateValidatorConfiguration) configuration).addMapping(mapping);
        }
    }
}
