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
package org.docksidestage.bizfw.validation.custom;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;

import org.docksidestage.mylasta.action.FortressMessages;

/**
 * @author jflute
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {}) // needed as mark
@Min(2)
@Documented
public @interface LandIntegerCustomOne {

    String message() default FortressMessages.ERRORS_APP_ILLEGAL_TRANSITION; // dummy

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
