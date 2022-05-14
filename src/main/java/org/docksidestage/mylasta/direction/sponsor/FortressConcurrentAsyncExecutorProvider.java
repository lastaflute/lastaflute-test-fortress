/*
 * Copyright 2015-2022 the original author or authors.
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

import org.lastaflute.core.magic.async.ConcurrentAsyncExecutorProvider;
import org.lastaflute.core.magic.async.ConcurrentAsyncOption;

/**
 * @author jflute
 */
public class FortressConcurrentAsyncExecutorProvider implements ConcurrentAsyncExecutorProvider {

    @Override
    public ConcurrentAsyncOption provideDefaultOption() {
        return null; // means no default
    }

    @Override
    public Integer provideMaxPoolSize() {
        return 30; // // #for_now jflute getting from config is better (2021/11/09)
    }
}
