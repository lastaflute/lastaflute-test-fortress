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
package org.docksidestage.whitebox.db.lazytx;

import javax.annotation.Resource;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.dbflute.hook.CallbackContext;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exentity.Member;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.db.jta.lazytx.LazyTransactionArranger;
import org.lastaflute.db.jta.stage.TransactionStage;

/**
 * @author jflute
 */
public class LazyTxBasicTest extends UnitFortressBasicTestCase {

    private static final LazyTransactionArranger lazyTxArranger = new LazyTransactionArranger();

    @Resource
    private MemberBhv memberBhv;
    @Resource
    private TransactionManager transactionManager;
    @Resource
    private TransactionStage transactionStage;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    public void setUp() throws Exception {
        new LazyTxUnitSetupper(getTestCaseBuildDir(), getProjectDir()).copyProtoToBuildDir();
        super.setUp();
    }

    @Override
    protected boolean isUseOneTimeContainer() {
        return true;
    }

    @Override
    protected void xsetupBeforeTestCaseTransaction() {
        super.xsetupBeforeTestCaseTransaction();
        lazyTxArranger.readyLazyTransaction(hook -> {
            CallbackContext.setBehaviorCommandHookOnThread(hook);
        });
    }

    @Override
    public void tearDown() throws Exception {
        try {
            lazyTxArranger.closeLazyTransaction();
            super.tearDown();
        } finally {
            new LazyTxUnitSetupper(getTestCaseBuildDir(), getProjectDir()).removeCopiedProto();
        }
    }

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_lazyTx_basic() throws Exception {
        CallbackContext.setSqlLogHandlerOnThread(log -> {
            assertTransactionStatus(Status.STATUS_NO_TRANSACTION);
            markHere("selected");
        });
        try {
            memberBhv.selectCount(cb -> { // no transaction
                cb.query().setMemberId_Equal(1);
            });
            assertMarked("selected");
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
        CallbackContext.setSqlLogHandlerOnThread(log -> {
            assertTransactionStatus(Status.STATUS_ACTIVE);
            markHere("updated");
        });
        try {
            Member member = new Member();
            member.setMemberId(1);
            member.setMemberStatusCode_Withdrawal();
            memberBhv.updateNonstrict(member); // begin transaction lazily
            assertMarked("updated");
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    // ===================================================================================
    //                                                                       Assert Helper
    //                                                                       =============
    private void assertTransactionStatus(int expected) {
        try {
            assertEquals(expected, transactionManager.getStatus());
        } catch (SystemException e) {
            throw new IllegalStateException("Failed to get status by transaction manager.", e);
        }
    }
}
