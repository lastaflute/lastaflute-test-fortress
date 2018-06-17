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

import org.dbflute.hook.AccessContext;
import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.logic.context.AccessContextLogic;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exentity.Member;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.db.dbflute.accesscontext.AccessContextResource;
import org.lastaflute.db.jta.stage.TransactionStage;
import org.lastaflute.jta.exception.LjtIllegalStateException;

/**
 * @author jflute
 */
public class LazyTxComparingTest extends UnitFortressBasicTestCase {

    @Resource
    private TransactionManager transactionManager;
    @Resource
    private TransactionStage transactionStage;
    @Resource
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    @Override
    protected boolean isSuppressTestCaseTransaction() {
        return true;
    }

    @Override
    protected AccessContext createPreparedAccessContext() {
        AccessContextLogic logic = getComponent(AccessContextLogic.class);
        AccessContextResource resource = new AccessContextResource("unit", getTestMethod());
        return logic.create(resource, () -> OptionalThing.empty(), () -> OptionalThing.empty(), () -> "UT", () -> OptionalThing.empty());
    }

    // ===================================================================================
    //                                                                          required()
    //                                                                          ==========
    public void test_lazyTx_required_nested_rootNotBegun_basic() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first root transactionStage.required()");
        transactionStage.required(tx -> {
            assertTransactionStatus_Active();
            showBeginStage("...Beginning nested transactionStage.required()");
            transactionStage.required(nestedTx -> {
                assertTransactionStatus_Active();
                updateSomething();
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
            updateSomething();
            assertTransactionStatus_Active();
            tx.rollbackOnly();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second root transactionStage.required()");
        transactionStage.required(tx -> { // not taking over (because of begun on thread)
            updateSomething();
            tx.rollbackOnly();
        });
    }

    // ===================================================================================
    //                                                                       requiresNew()
    //                                                                       =============
    public void test_lazyTx_requiresNew_exception_nested_noBegun() throws Exception {
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.requiresNew()");
        transactionStage.requiresNew(tx -> {
            assertTransactionStatus_Active();
            try {
                showBeginStage("...Beginning first transactionStage.requiresNew()");
                transactionStage.required(nestedTx -> {
                    throw new IllegalStateException("nested");
                });
            } catch (IllegalStateException e) {
                assertEquals("nested", e.getMessage());
            }
            assertTransactionStatus_MarkedRollback();
            assertException(LjtIllegalStateException.class, () -> updateSomething());
            assertTransactionStatus_MarkedRollback();
            tx.rollbackOnly(); // needed or committed
        });
        assertTransactionStatus_NonTx();
    }

    // ===================================================================================
    //                                                                         Test Helper
    //                                                                         ===========
    private void updateSomething() {
        doUpdateSomething(1);
    }

    @SuppressWarnings("unused")
    private void updateSomethingNext() {
        doUpdateSomething(2);
    }

    @SuppressWarnings("unused")
    private void updateSomethingNextNext() {
        doUpdateSomething(3);
    }

    private void doUpdateSomething(Integer memberId) {
        Member member = new Member();
        member.setMemberId(memberId);
        member.setMemberStatusCode_Withdrawal();
        memberBhv.updateNonstrict(member);
    }

    protected void showBeginStage(String msg) {
        log("_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/");
        log(msg);
        log("_/_/_/_/_/_/_/_/_/_/");
    }

    // ===================================================================================
    //                                                                       Assert Helper
    //                                                                       =============
    private void assertTransactionStatus_Active() {
        doAssertTransactionStatus(Status.STATUS_ACTIVE);
    }

    private void assertTransactionStatus_MarkedRollback() {
        doAssertTransactionStatus(Status.STATUS_MARKED_ROLLBACK);
    }

    private void assertTransactionStatus_NonTx() {
        doAssertTransactionStatus(Status.STATUS_NO_TRANSACTION);
    }

    private void doAssertTransactionStatus(int expected) {
        try {
            assertEquals(expected, transactionManager.getStatus());
        } catch (SystemException e) {
            throw new IllegalStateException("Failed to get status by transaction manager.", e);
        }
    }
}
