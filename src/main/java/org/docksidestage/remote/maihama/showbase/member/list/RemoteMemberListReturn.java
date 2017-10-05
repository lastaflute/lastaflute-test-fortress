/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage.remote.maihama.showbase.member.list;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * RemoteMemberListReturn.
 * @author FreeGen
 */
public class RemoteMemberListReturn {

    /** row count per one page. */
    @Required
    public Integer pageSize;

    /** number of current page. */
    @Required
    public Integer currentPageNumber;

    /** count of all records. */
    @Required
    public Integer allRecordCount;

    /** count of all pages. */
    @Required
    public Integer allPageCount;

    /** paging data for current page. */
    @javax.validation.Valid
    public java.util.List<MemberSearchRowResult> rows;

    public static class MemberSearchRowResult {

        @Required
        public Integer memberId;

        @Required
        public String memberName;

        @Required
        public String memberStatusName;

        public java.time.LocalDate formalizedDate;

        @Required
        public java.time.LocalDateTime updateDatetime;

        @Required
        public Boolean withdrawalMember;

        @Required
        public Integer purchaseCount;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
