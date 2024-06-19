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
package org.docksidestage.bizfw.logback;

import org.dbflute.util.DfStringUtil;
import org.lastaflute.job.LaCron;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.sift.AbstractDiscriminator;

/**
 * The discriminator by job unique for e.g. SiftingAppender of Logback. <br>
 * Basically job unique should be set in AllJobScheduler like this:
 * <pre>
 * cron.registerNonCron(SeaJob.class, waitIfConcurrent(), op -&gt; op.uniqueBy("mystic"));
 * </pre>
 * @author jflute
 */
public class LogbackJobUniqueDiscriminator extends AbstractDiscriminator<ILoggingEvent> {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    protected static final String KEY = "job.unique"; // use ${job.unique} in logback.xml
    protected static final String PREFIX = LaCron.THREAD_NAME_PREFIX;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected String defaultValue = "various"; // as default of default

    // ===================================================================================
    //                                                                      Discriminating
    //                                                                      ==============
    @Override
    public String getDiscriminatingValue(ILoggingEvent e) {
        final String threadName = Thread.currentThread().getName();
        if (threadName.startsWith(PREFIX)) {
            return DfStringUtil.substringFirstRear(threadName, PREFIX);
        } else {
            return defaultValue;
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public String getKey() {
        return KEY;
    }

    public void setKey(String key) {
        throw new UnsupportedOperationException("cannot be set, fixedly key is " + getKey());
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
