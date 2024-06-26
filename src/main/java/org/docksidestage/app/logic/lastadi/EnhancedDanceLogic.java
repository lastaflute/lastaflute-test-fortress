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
package org.docksidestage.app.logic.lastadi;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.docksidestage.dbflute.exbhv.MemberBhv;

/**
 * @author jflute
 */
public class EnhancedDanceLogic {

    @Resource
    private MemberBhv memberBhv;

    @Transactional(value = TxType.REQUIRES_NEW)
    public void letsDance() {
        memberBhv.selectList(cb -> {}); // nonsense select for test
    }
}
