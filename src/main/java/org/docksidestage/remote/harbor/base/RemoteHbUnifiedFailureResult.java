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
package org.docksidestage.remote.harbor.base;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class RemoteHbUnifiedFailureResult {

    @Required
    public final RemoteUnifiedFailureType cause;

    public static enum RemoteUnifiedFailureType {
        VALIDATION_ERROR // special type
        , LOGIN_FAILURE, LOGIN_REQUIRED // specific type of application exception
        // you can add your application exception type if you need it
        //, ALREADY_DELETED, ALREADY_UPDATED
        , BUSINESS_ERROR // default type of application exception
        , CLIENT_ERROR // e.g. 404 not found
    }

    @NotNull
    @Valid
    public List<RemoteFailureErrorPart> errors;

    public static class RemoteFailureErrorPart {

        @Required
        public final String field;

        @NotNull
        public final List<String> messages;

        public RemoteFailureErrorPart(String field, List<String> messages) {
            this.field = field;
            this.messages = messages;
        }
    }

    public RemoteHbUnifiedFailureResult(RemoteUnifiedFailureType cause, List<RemoteFailureErrorPart> errors) {
        this.cause = cause;
        this.errors = errors;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
