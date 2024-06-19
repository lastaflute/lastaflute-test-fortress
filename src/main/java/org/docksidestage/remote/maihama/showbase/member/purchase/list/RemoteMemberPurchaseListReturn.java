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
package org.docksidestage.remote.maihama.showbase.member.purchase.list;

import javax.validation.constraints.NotNull;

import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * The bean class as return for remote API of GET /member/purchase/list/{memberId}/{pageNumber}.
 * @author FreeGen
 */
public class RemoteMemberPurchaseListReturn extends org.docksidestage.bizfw.remoteapi.AbstractListGetReturn {

    /** The property of pageSize. (row count per one page) */
    @Required
    public Integer pageSize;

    /** The property of currentPageNumber. (number of current page) */
    @Required
    public Integer currentPageNumber;

    /** The property of allRecordCount. (count of all records) */
    @Required
    public Integer allRecordCount;

    /** The property of allPageCount. (count of all pages) */
    @Required
    public Integer allPageCount;

    /** The property of rows. (paging data for current page) */
    @NotNull
    @javax.validation.Valid
    public org.eclipse.collections.api.list.ImmutableList<MemberPurchaseSearchRowPart> rows;

    /**
     * The part class of MemberPurchaseSearchRowPart.
     * @author FreeGen
     */
    public static class MemberPurchaseSearchRowPart {

        /** The property of purchaseId. */
        @Required
        public Long purchaseId;

        /** The property of purchaseDatetime. */
        @Required
        public java.time.LocalDateTime purchaseDatetime;

        /** The property of productName. */
        @Required
        public String productName;

        /** The property of purchasePrice. */
        @Required
        public Integer purchasePrice;

        /** The property of purchaseCount. */
        @Required
        public Integer purchaseCount;

        /** The property of paymentComplete. */
        @Required
        public Boolean paymentComplete;
    }

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
