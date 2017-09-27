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
package org.docksidestage.remote.maihama.showbase.member.purchase.list;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * RemoteMemberPurchaseListReturn.
 * @author FreeGen
 */
public class RemoteMemberPurchaseListReturn {

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
    public java.util.List<Object> rows;

    public static class Object {

        @Required
        public Integer productId;

        @Required
        public String productName;

        @Required
        public String productStatus;

        @Required
        public String productCategory;

        @Required
        public Integer regularPrice;

        public java.time.LocalDate latestPurchaseDate;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
