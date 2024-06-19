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
package org.docksidestage.remote.maihama.showbase.signup.index;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as param for remote API of POST /signup/.
 * @author FreeGen
 */
public class RemoteSignupParam {

    /** The property of memberName. */
    @Required
    public String memberName;

    /** The property of memberAccount. */
    @Required
    public String memberAccount;

    /** The property of password. */
    @Required
    public String password;

    /** The property of reminderQuestion. */
    @Required
    public String reminderQuestion;

    /** The property of reminderAnswer. */
    @Required
    public String reminderAnswer;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
