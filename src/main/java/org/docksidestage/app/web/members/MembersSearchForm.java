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
package org.docksidestage.app.web.members;

import java.time.LocalDate;

import org.docksidestage.dbflute.allcommon.CDef;

/**
 * @author jflute
 */
public class MembersSearchForm {

    public String memberName;
    public CDef.MemberStatus memberStatus;
    public String purchaseProductName;
    public boolean unpaid;
    public LocalDate formalizedFrom;
    public LocalDate formalizedTo;

    public Integer pageNumber = 1; // default when first list
}
