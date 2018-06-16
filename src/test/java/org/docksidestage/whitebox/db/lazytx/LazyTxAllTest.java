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
import org.dbflute.hook.CallbackContext;
import org.dbflute.optional.OptionalThing;
import org.docksidestage.app.logic.context.AccessContextLogic;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exentity.Member;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.db.dbflute.accesscontext.AccessContextResource;
import org.lastaflute.db.jta.lazytx.LazyTransactionArranger;
import org.lastaflute.db.jta.stage.TransactionStage;

/**
 * @author jflute
 */
public class LazyTxAllTest extends UnitFortressBasicTestCase {

    private static final LazyTransactionArranger lazyTxArranger = new LazyTransactionArranger();

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
    protected boolean isSuppressTestCaseTransaction() {
        return true;
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

    @Override
    protected AccessContext createPreparedAccessContext() {
        AccessContextLogic logic = getComponent(AccessContextLogic.class);
        AccessContextResource resource = new AccessContextResource("unit", getTestMethod());
        return logic.create(resource, () -> OptionalThing.empty(), () -> OptionalThing.empty(), () -> "UT");
    }

    // ===================================================================================
    //                                                                          required()
    //                                                                          ==========
    public void test_lazyTx_required_firstUpdate_secondUpdate() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.required(): readyLazy, realBegun");
        transactionStage.required(tx -> {
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second transactionStage.required(): readyLazy, realBegun");
        transactionStage.required(tx -> { // as requiresNew() because already begun
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
            assertTransactionStatus_Active();
        });
        assertTransactionStatus_NonTx();
    }

    public void test_lazyTx_required_secondUpdate_afterFirstEmpty() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.required(): readyLazy, noTx, skipRollback");
        transactionStage.required(tx -> {
            memberBhv.selectCount(cb -> { // no transaction
                cb.query().setMemberId_Equal(1);
            });
            assertTransactionStatus_NonTx();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second transactionStage.required(): readyLazy, realBegun");
        transactionStage.required(tx -> { // taking over so no access context
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
        });
        // done jflute now no rollback outer transaction (2018/06/15)
    }

    public void test_lazyTx_required_nested_rootAlreadyBegun() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning root transactionStage.required(): readyLazy, realBegun");
        transactionStage.required(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
            assertTransactionStatus_Active();
            showBeginStage("...Beginning nested transactionStage.required(): takeOver");
            transactionStage.required(nestedTx -> { // taking over (no filtering process)
                assertTransactionStatus_Active();
                updateSomething();
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
        });
    }

    public void test_lazyTx_required_nested_rootNotBegun_basic() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first root transactionStage.required(): readyLazy, forcedly(begun, suspended, resumed)");
        transactionStage.required(tx -> {
            assertTransactionStatus_NonTx();
            showBeginStage("...Beginning nested transactionStage.required(): takeOver, realBegun");
            transactionStage.required(nestedTx -> { // taking over (no filtering process)
                assertTransactionStatus_NonTx();
                updateSomething();
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
            updateSomething();
            assertTransactionStatus_Active();
            tx.rollbackOnly();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second root transactionStage.required(): readyLazy, realBegun");
        transactionStage.required(tx -> { // not taking over (because of begun on thread)
            updateSomething();
            tx.rollbackOnly();
        });
    }

    public void test_lazyTx_required_nested_rootNotBegun_nestedRollback() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first root transactionStage.required(): readyLazy, forcedly(begun, suspended, resumed)");
        transactionStage.required(tx -> {
            assertTransactionStatus_NonTx();
            showBeginStage("...Beginning nested transactionStage.required(): takeOver, realBegun");
            transactionStage.required(nestedTx -> { // taking over (no filtering process)
                nestedTx.rollbackOnly();
                assertTransactionStatus_NonTx();
                updateSomething();
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_MarkedRollback();
            updateSomething();
            assertTransactionStatus_MarkedRollback();
            tx.rollbackOnly();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second root transactionStage.required(): readyLazy, realBegun");
        transactionStage.required(tx -> { // not taking over (because of begun on thread)
            updateSomething();
            tx.rollbackOnly();
        });
    }

    public void test_lazyTx_required_nested_requiresNew_afterBegun() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first root transactionStage.required(): readyLazy, realBegun, normally(suspended, resumed)");
        transactionStage.required(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
            showBeginStage("...Beginning nested transactionStage.requiresNew(): normally(begun)");
            transactionStage.requiresNew(nestedTx -> {
                nestedTx.rollbackOnly();
                assertTransactionStatus_Active();
                updateSomethingNext(); // avoid lock
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
            updateSomething();
            assertTransactionStatus_Active();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second root transactionStage.requiresNew(): readyLazy, realBegun");
        transactionStage.required(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
        });
    }

    public void test_lazyTx_required_nested_requiresNew_rootNotBegun() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first root transactionStage.required(): readyLazy, forcedly(begun, suspended, resumed)");
        transactionStage.required(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            assertTransactionStatus_NonTx();
            showBeginStage("...Beginning nested transactionStage.required(): normally(begun)");
            transactionStage.requiresNew(nestedTx -> {
                nestedTx.rollbackOnly();
                assertTransactionStatus_Active();
                updateSomethingNext();
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
            updateSomething();
            assertTransactionStatus_Active();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second root transactionStage.required(): readyLazy, realBegun");
        transactionStage.required(tx -> { // not taking over (because of begun on thread)
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
        });
    }

    // ===================================================================================
    //                                                                       requiresNew()
    //                                                                       =============
    // -----------------------------------------------------
    //                                         Plural Update
    //                                         -------------
    public void test_lazyTx_requiresNew_pluralUpdate() throws Exception {
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.requiresNew(): lazyBegun, realBegun");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
            updateSomething();
            assertTransactionStatus_Active();
        });
        assertTransactionStatus_NonTx();
    }

    // -----------------------------------------------------
    //                                             Flat Call
    //                                             ---------
    public void test_lazyTx_requiresNew_flat_firstUpdate_secondUpdate() throws Exception {
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.requiresNew(): readyLazy, realBegun");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second transactionStage.requiresNew(): readyLazy, realBegun");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
        });
        assertTransactionStatus_NonTx();
    }

    public void test_lazyTx_requiresNew_flat_secondUpdate_afterFirstEmpty() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.requiresNew(): readyLazy, noTx, skipRollback");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            memberBhv.selectCount(cb -> { // no transaction
                cb.query().setMemberId_Equal(1);
            });
            assertTransactionStatus_NonTx();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second transactionStage.requiresNew(): readyLazy, realBegun");
        transactionStage.requiresNew(tx -> { // lazy begun
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
            assertTransactionStatus_Active();
        });
        assertTransactionStatus_NonTx();
    }

    // -----------------------------------------------------
    //                                          Nested World
    //                                          ------------
    public void test_lazyTx_requiresNew_nested_rootAlreadyBegun() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning root transactionStage.requiresNew(): readyLazy, realBegun, normally(suspendded, resumed)");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
            assertTransactionStatus_Active();
            showBeginStage("...Beginning nested transactionStage.requiresNew(): normally(begun)");
            transactionStage.requiresNew(nestedTx -> {
                nestedTx.rollbackOnly();
                assertTransactionStatus_Active();
                updateSomethingNext(); // avoid lock
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
        });
    }

    public void test_lazyTx_requiresNew_nested_required_notBegun() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first root transactionStage.requiresNew(): lazyBegun, realBegun");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            showBeginStage("...Beginning nested transactionStage.required(): takeOver, realBegun");
            transactionStage.required(nestedTx -> { // taking over (no filtering process)
                nestedTx.rollbackOnly();
                assertTransactionStatus_NonTx();
                updateSomethingNext(); // realBegun outer transaction
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_MarkedRollback(); // because of active at required() end
            updateSomething();
            assertTransactionStatus_MarkedRollback();
        });
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning second root transactionStage.requiresNew(): lazyBegun, realBegun");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
        });
    }

    public void test_lazyTx_requiresNew_nestedNested_rootAlreadyBegun() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning root transactionStage.requiresNew(): readyLazy, realBegun, normally(suspendded, resumed)");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
            assertTransactionStatus_Active();
            showBeginStage("...Beginning nested transactionStage.requiresNew(): normally(begun), normally(suspendded, resumed)");
            transactionStage.requiresNew(nestedTx -> {
                nestedTx.rollbackOnly();
                assertTransactionStatus_Active();
                updateSomethingNext(); // avoid lock
                assertTransactionStatus_Active();
                showBeginStage("...Beginning first nestedNested transactionStage.requiresNew(): normally(begun)");
                transactionStage.requiresNew(nestedNestedTx -> {
                    nestedNestedTx.rollbackOnly();
                    assertTransactionStatus_Active();
                    updateSomethingNextNext(); // avoid lock
                    assertTransactionStatus_Active();
                });
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
        });
    }

    public void test_lazyTx_requiresNew_nestedOnParade() throws Exception {
        // ## Arrange ##
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning root transactionStage.requiresNew(): readyLazy, realBegun, normally(suspendded, resumed)");
        transactionStage.requiresNew(tx -> {
            tx.rollbackOnly();
            assertTransactionStatus_NonTx();
            CallbackContext.setSqlLogHandlerOnThread(log -> {
                assertTransactionStatus_Active();
                markHere("updated");
            });
            try {
                // ## Act ##
                updateSomething();

                // ## Assert ##
                assertMarked("updated");
            } finally {
                CallbackContext.clearSqlLogHandlerOnThread();
            }
            assertTransactionStatus_Active();
            showBeginStage("...Beginning nested transactionStage.requiresNew(): normally(begun), normally(suspendded, resumed)");
            transactionStage.requiresNew(nestedTx -> {
                nestedTx.rollbackOnly();
                assertTransactionStatus_Active();
                updateSomethingNext(); // avoid lock
                assertTransactionStatus_Active();
                showBeginStage("...Beginning first nestedNested transactionStage.requiresNew(): normally(begun)");
                transactionStage.requiresNew(nestedNestedTx -> {
                    nestedNestedTx.rollbackOnly();
                    assertTransactionStatus_Active();
                    updateSomethingNextNext(); // avoid lock
                    assertTransactionStatus_Active();
                });
                assertTransactionStatus_Active();
                showBeginStage("...Beginning second nestedNested transactionStage.requiresNew(): normally(begun), empty");
                transactionStage.requiresNew(nestedNestedTx -> {
                    assertTransactionStatus_Active();
                });
                showBeginStage("...Beginning third nestedNested transactionStage.requiresNew(): normally(begun)");
                transactionStage.requiresNew(nestedNestedTx -> {
                    nestedNestedTx.rollbackOnly();
                    assertTransactionStatus_Active();
                    updateSomethingNextNext(); // avoid lock
                    assertTransactionStatus_Active();
                });
                assertTransactionStatus_Active();
            });
            assertTransactionStatus_Active();
        });
    }

    // -----------------------------------------------------
    //                                             Exception
    //                                             ---------
    public void test_lazyTx_requiresNew_exception_basic() throws Exception {
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.requiresNew(): lazyBegun, realBegun, rolled-back");
        try {
            transactionStage.requiresNew(tx -> {
                assertTransactionStatus_NonTx();
                updateSomething();
                assertTransactionStatus_Active();
                updateSomething();
                assertTransactionStatus_Active();
                throw new IllegalStateException("first");
            });
        } catch (IllegalStateException e) {
            assertEquals("first", e.getMessage());
        }
        assertTransactionStatus_NonTx();
    }

    public void test_lazyTx_requiresNew_exception_nested_alreadyBegun() throws Exception {
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.requiresNew(): lazyBegun, realBegun, rolled-back");
        transactionStage.requiresNew(tx -> {
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
            try {
                showBeginStage("...Beginning first transactionStage.requiresNew(): inherited, rolled-back");
                transactionStage.required(nestedTx -> {
                    throw new IllegalStateException("nested");
                });
            } catch (IllegalStateException e) {
                assertEquals("nested", e.getMessage());
            }
            assertTransactionStatus_MarkedRollback();
            updateSomething();
            assertTransactionStatus_MarkedRollback();
        });
        assertTransactionStatus_NonTx();
    }

    public void test_lazyTx_requiresNew_exception_nested_noBegun() throws Exception {
        assertTransactionStatus_NonTx();
        showBeginStage("...Beginning first transactionStage.requiresNew(): lazyBegun, realBegun, rolled-back");
        transactionStage.requiresNew(tx -> {
            assertTransactionStatus_NonTx();
            try {
                showBeginStage("...Beginning first transactionStage.requiresNew(): takeOver, no-rollback");
                transactionStage.required(nestedTx -> {
                    throw new IllegalStateException("nested");
                });
            } catch (IllegalStateException e) {
                assertEquals("nested", e.getMessage());
            }
            assertTransactionStatus_NonTx();
            updateSomething();
            assertTransactionStatus_Active();
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

    private void updateSomethingNext() {
        doUpdateSomething(2);
    }

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
