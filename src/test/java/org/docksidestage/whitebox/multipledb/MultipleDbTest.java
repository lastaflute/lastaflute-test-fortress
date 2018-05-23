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
package org.docksidestage.whitebox.multipledb;

import javax.annotation.Resource;

import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.resola.exbhv.StationBhv;
import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 */
public class MultipleDbTest extends UnitFortressBasicTestCase {

    @Resource
    private MemberBhv memberBhv;
    @Resource
    private StationBhv stationBhv;

    public void test_multipledb_basci() throws Exception {
        memberBhv.selectList(cb -> {});
        stationBhv.selectList(cb -> {});
    }
}
