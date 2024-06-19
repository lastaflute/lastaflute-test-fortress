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
package org.docksidestage.remote.fortress.job.execute;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of POST /job/execute/{jobCode}.
 * @author FreeGen
 */
public class RemoteJobExecuteReturn {

    /** The property of jobUnique. */
    @Required
    public String jobUnique;

    /** The property of jobTypeFqcn. */
    @Required
    public String jobTypeFqcn;

    /** The property of activationTime. */
    @Required
    public java.time.LocalDateTime activationTime;

    /** The property of beginTime. (NullAllowed) */
    public java.time.LocalDateTime beginTime;

    /** The property of endTime. (NullAllowed) */
    public java.time.LocalDateTime endTime;

    /** The property of endTitleRoll. */
    @Required
    public java.util.Map<String, Object> endTitleRoll;

    /** The property of execResultType. (enumValue=[SUCCESS, QUIT_BY_CONCURRENT, ERROR_BY_CONCURRENT, CAUSED_BY_APPLICATION, CAUSED_BY_FRAMEWORK, UNKNOWN]) ( * `SUCCESS` - SUCCESS, . * `QUIT_BY_CONCURRENT` - QUIT_BY_CONCURRENT, . * `ERROR_BY_CONCURRENT` - ERROR_BY_CONCURRENT, . * `CAUSED_BY_APPLICATION` - CAUSED_BY_APPLICATION, . * `CAUSED_BY_FRAMEWORK` - CAUSED_BY_FRAMEWORK, . * `UNKNOWN` - UNKNOWN, . :: fromCls(JobExecuteResult$ExecResultTypePart)) */
    @Required
    public String execResultType;

    /** The property of errorEnding. */
    @Required
    public Boolean errorEnding;

    /** The property of errorMessage. (NullAllowed) */
    public String errorMessage;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
