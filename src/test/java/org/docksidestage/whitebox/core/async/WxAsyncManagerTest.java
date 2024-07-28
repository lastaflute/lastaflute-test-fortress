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
package org.docksidestage.whitebox.core.async;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import org.dbflute.exception.AccessContextNotFoundException;
import org.dbflute.exception.EntityAlreadyDeletedException;
import org.dbflute.util.DfTraceViewUtil;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exentity.Member;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.bridge.AsyncStateBridge;
import org.lastaflute.core.magic.async.exception.ConcurrentParallelRunnerException;
import org.lastaflute.core.magic.async.future.YourFuture;
import org.lastaflute.core.magic.async.waiting.WaitingAsyncException;
import org.lastaflute.core.magic.async.waiting.WaitingAsyncResult;
import org.lastaflute.core.magic.destructive.BowgunDestructiveAdjuster;
import org.lastaflute.db.jta.stage.TransactionStage;

import jakarta.annotation.Resource;

/**
 * @author jflute
 */
public class WxAsyncManagerTest extends UnitFortressBasicTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private AsyncManager asyncManager;
    @Resource
    private TransactionStage transactionStage;
    @Resource
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                        Asynchronous
    //                                                                        ============
    // -----------------------------------------------------
    //                                                 Basic
    //                                                 -----
    public void test_async_basic() {
        // ## Arrange ##
        String currentName = Thread.currentThread().getName();

        // ## Act ##
        YourFuture future = asyncManager.async(() -> {
            sleep(300);
            log("...Marking here");
            markHere("called");
            assertNotSame(currentName, Thread.currentThread().getName());
        });

        // ## Assert ##
        log("...Waiting for done");
        future.waitForDone();
        assertMarked("called");
    }

    // -----------------------------------------------------
    //                                             Exception
    //                                             ---------
    public void test_async_exception() {
        // ## Arrange ##

        // ## Act ##
        YourFuture future = asyncManager.async(() -> {
            log("...Marking here");
            markHere("called");
            try {
                memberBhv.selectByPK(99999).get();
            } catch (RuntimeException e) {
                throw new IllegalStateException("sea", e);
            }
        });

        // ## Assert ##
        log("...Waiting for done");
        WaitingAsyncResult result = future.waitForDone();
        assertMarked("called");
        WaitingAsyncException asyncExp = result.getWaitingAsyncException().get(); // is already logged as error in asynchronous process
        assertFalse(asyncExp.getEntryNumber().isPresent());
        assertFalse(asyncExp.getParameter().isPresent());
        log(asyncExp.getMessage());
        assertContains(asyncExp.getMessage(), EntityAlreadyDeletedException.class.getSimpleName());
        assertEquals(IllegalStateException.class, asyncExp.getCause().getClass());
        assertEquals(EntityAlreadyDeletedException.class, asyncExp.getCause().getCause().getClass());
    }

    // -----------------------------------------------------
    //                                           Destructive
    //                                           -----------
    public void test_async_destructive() {
        // ## Arrange ##
        BowgunDestructiveAdjuster.unlock();
        BowgunDestructiveAdjuster.shootBowgunAsyncToNormalSync();
        String currentName = Thread.currentThread().getName();

        try {
            // ## Act ##
            YourFuture future = asyncManager.async(() -> {
                sleep(300);
                log("...Marking here");
                markHere("called");
                assertEquals(currentName, Thread.currentThread().getName());
            });

            // ## Assert ##
            log("...Waiting for done");
            future.waitForDone();
            assertMarked("called");
        } finally {
            BowgunDestructiveAdjuster.unlock();
            BowgunDestructiveAdjuster.restoreBowgunAsyncToNormalSync();
        }
    }

    // ===================================================================================
    //                                                                        Bridge State
    //                                                                        ============
    public void test_bridgeState_basic() throws InterruptedException {
        // ## Arrange ##
        Member member = memberBhv.selectByPK(1).get();
        member.setMemberStatusCode_Provisional();
        CountDownLatch latch = new CountDownLatch(1);

        // ## Act ##
        AsyncStateBridge bridge = asyncManager.bridgeState(op -> {});
        new Thread(() -> {
            transactionStage.requiresNew(tx -> {
                assertException(AccessContextNotFoundException.class, () -> memberBhv.updateNonstrict(member));
            });
            bridge.cross(() -> {
                transactionStage.requiresNew(tx -> {
                    memberBhv.updateNonstrict(member);
                    markHere("called");
                });
            });
            latch.countDown();
        }).start();

        // ## Assert ##
        latch.await();
        assertMarked("called");
    }

    // ===================================================================================
    //                                                                            Parallel
    //                                                                            ========
    // -----------------------------------------------------
    //                                                 Basic
    //                                                 -----
    public void test_parallel_params_basic() {
        // ## Arrange ##
        String currentName = Thread.currentThread().getName();
        List<String> parameterList = Arrays.asList("sea", "land", "piari");

        // ## Act ##
        List<String> actualList = new CopyOnWriteArrayList<>();
        asyncManager.parallel(runner -> {
            String parameter = (String) runner.getParameter().get();
            log(parameter);
            actualList.add(parameter);
            markHere("called");
            assertNotSame(currentName, Thread.currentThread().getName());
        }, op -> {
            op.params(parameterList);
        });

        // ## Assert ##
        log(actualList);
        assertEquals(parameterList.size(), actualList.size());
        assertMarked("called");
    }

    public void test_parallel_params_waitingIntervalMillis() {
        // ## Arrange ##
        String currentName = Thread.currentThread().getName();
        List<String> parameterList = Arrays.asList("sea", "land", "piari");

        // ## Act ##
        List<String> actualList = new CopyOnWriteArrayList<>();
        asyncManager.parallel(runner -> {
            String parameter = (String) runner.getParameter().get();
            log(parameter);
            actualList.add(parameter);
            markHere("called");
            assertNotSame(currentName, Thread.currentThread().getName());
            if (runner.getEntryNumber() == 1) {
                sleep(300);
            }
        }, op -> {
            op.params(parameterList).waitingIntervalMillis(1000L);
        });

        // ## Assert ##
        log(actualList);
        assertEquals(parameterList.size(), actualList.size());
        assertMarked("called");
    }

    // -----------------------------------------------------
    //                                     Concurrency Count
    //                                     -----------------
    public void test_parallel_params_limitConcurrencyCount_basic() {
        // ## Arrange ##
        String currentName = Thread.currentThread().getName();
        List<String> parameterList = Arrays.asList("sea", "land", "piari", "bonvo", "dstore", "amba", "miraco", "dohotel", "broadway",
                "dockside", "hangar", "magiclamp", "orleans", "showbase");

        // ## Act ##
        List<String> currentList = new CopyOnWriteArrayList<>();
        asyncManager.parallel(runner -> {
            String parameter = (String) runner.getParameter().get();
            currentList.add(parameter);
            log(parameter);
            markHere("called");
            assertNotSame(currentName, Thread.currentThread().getName());
            if (runner.getEntryNumber() % 2 == 0) {
                sleep(300);
            }
            assertTrue(currentList.size() <= 2); // also visual check
            currentList.remove(parameter);
        }, op -> {
            op.params(parameterList).limitConcurrencyCount(2);
        });

        // ## Assert ##
        assertMarked("called");
    }

    public void test_parallel_params_limitConcurrencyCount_large() {
        // ## Arrange ##
        String currentName = Thread.currentThread().getName();
        List<String> parameterList = new ArrayList<String>();
        for (int i = 0; i < 2000; i++) {
            parameterList.add("param" + i);
        }

        // ## Act ##
        List<String> currentList = new CopyOnWriteArrayList<>();
        long before = System.currentTimeMillis();
        asyncManager.parallel(runner -> {
            String parameter = (String) runner.getParameter().get();
            currentList.add(parameter);
            log(parameter);
            markHere("called");
            assertNotSame(currentName, Thread.currentThread().getName());
            assertTrue(currentList.size() <= 5);
            currentList.remove(parameter);
        }, op -> {
            op.params(parameterList).limitConcurrencyCount(5);
        });

        // ## Assert ##
        assertMarked("called");
        long after = System.currentTimeMillis();
        log("performance: {}", DfTraceViewUtil.convertToPerformanceView(after - before));
        // [waiting-interval milliseconds performance]
        // when 2000 parameters and light processes
        //   1L  : 00m00s925ms, 00m01s133ms
        //   10L : 00m03s502ms, 00m02s447ms
        //   20L : 00m05s249ms, 00m05s193ms
        //   100L: 00m31s037ms
        // (avoid too late and too short-span) 
    }

    // -----------------------------------------------------
    //                                        without Params 
    //                                        --------------
    public void test_parallel_withoutParams() {
        // ## Arrange ##
        String currentName = Thread.currentThread().getName();

        // ## Act ##
        asyncManager.parallel(runner -> {
            assertFalse(runner.getParameter().isPresent());
            if (runner.getEntryNumber() == 1) {
                synchronized (runner.getLockObj()) {
                    markHere("called1");
                }
            } else if (runner.getEntryNumber() == 2) {
                synchronized (runner.getLockObj()) {
                    markHere("called2");
                }
            }
            log(runner.getThreadId(), runner.getEntryNumber());
            assertNotSame(currentName, Thread.currentThread().getName());
        }, op -> {});

        // ## Assert ##
        assertMarked("called1");
        assertMarked("called2");
    }

    // -----------------------------------------------------
    //                                             Exception
    //                                             ---------
    public void test_parallel_exception_causeList() {
        // ## Arrange ##
        List<String> parameterList = Arrays.asList("sea", "land", "piari", "bonvo");

        // ## Act ##
        // ## Assert ##
        assertException(ConcurrentParallelRunnerException.class, () -> {
            asyncManager.parallel(runner -> {
                String parameter = (String) runner.getParameter().get();
                log(parameter);
                int entryNumber = runner.getEntryNumber();
                if (entryNumber != 1) {
                    sleep(200);
                }
                if (entryNumber == 1) { // so first done
                    throw new IllegalStateException("parameter=" + parameter);
                } else if (entryNumber == 2) {
                    try {
                        memberBhv.selectByPK(99999).get();
                    } catch (RuntimeException e) {
                        throw new IllegalStateException("parameter=" + parameter, e);
                    }
                } else if (entryNumber == 4) {
                    throw new Error("parameter=" + parameter);
                }
            }, op -> {
                op.params(parameterList);
            });
        }).handle(cause -> {
            log("...Asserting exception now");
            String msg = cause.getMessage() + ln() + "..." + ln() + cause.getCause().getMessage();
            log(msg);
            assertContains(msg, EntityAlreadyDeletedException.class.getSimpleName());
            assertContainsAll(msg, "sea", "land", "bonvo");
            assertContains("parameter=sea", cause.getCause().getCause().getMessage());
            log("[Stack Trace]", cause);
        });
    }

    public void test_parallel_exception_subsumeErrorLogging() {
        // ## Arrange ##
        List<String> parameterList = Arrays.asList("sea", "land", "piari", "bonvo");

        // ## Act ##
        // ## Assert ##
        asyncManager.parallel(runner -> { // expects no exception, visual check here
            String parameter = (String) runner.getParameter().get();
            log(parameter);
            int entryNumber = runner.getEntryNumber();
            if (entryNumber == 1) {
                throw new IllegalStateException("parameter=" + parameter);
            } else if (entryNumber == 2) {
                sleep(1000);
                try {
                    memberBhv.selectByPK(99999).get();
                } catch (RuntimeException e) {
                    throw new IllegalStateException("parameter=" + parameter, e);
                }
            } else if (entryNumber == 4) {
                sleep(1000);
                throw new Error("parameter=" + parameter);
            }
        }, op -> {
            op.params(parameterList).subsumeErrorHandling();
        });
    }

    // -----------------------------------------------------
    //                                           Destructive
    //                                           -----------
    public void test_parallel_destructive_params() {
        // ## Arrange ##
        List<String> parameterList = Arrays.asList("sea", "land", "piari");
        BowgunDestructiveAdjuster.unlock();
        BowgunDestructiveAdjuster.shootBowgunAsyncToNormalSync();
        String currentName = Thread.currentThread().getName();

        try {
            // ## Act ##
            List<String> actualList = new CopyOnWriteArrayList<>();
            asyncManager.parallel(runner -> {
                String parameter = (String) runner.getParameter().get();
                actualList.add(parameter);
                log("entryNumber={}, parameter={}", runner.getEntryNumber(), parameter);

                markHere("called");
                assertEquals(currentName, Thread.currentThread().getName());
            }, op -> {
                op.params(parameterList);
            });

            // ## Assert ##
            log(actualList);
            assertEquals(parameterList.size(), actualList.size());
            assertMarked("called");
        } finally {
            BowgunDestructiveAdjuster.unlock();
            BowgunDestructiveAdjuster.restoreBowgunAsyncToNormalSync();
        }
    }

    public void test_parallel_destructive_threadCount() {
        // ## Arrange ##
        BowgunDestructiveAdjuster.unlock();
        BowgunDestructiveAdjuster.shootBowgunAsyncToNormalSync();
        String currentName = Thread.currentThread().getName();

        try {
            // ## Act ##
            List<Integer> actualList = new CopyOnWriteArrayList<>();
            asyncManager.parallel(runner -> {
                int entryNumber = runner.getEntryNumber();
                actualList.add(entryNumber);
                log("entryNumber={}", entryNumber);

                markHere("called");
                assertEquals(currentName, Thread.currentThread().getName());
            }, op -> {});

            // ## Assert ##
            log(actualList);
            assertEquals(5, actualList.size()); // as default
            assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)), new HashSet<>(actualList));
            assertMarked("called");
        } finally {
            BowgunDestructiveAdjuster.unlock();
            BowgunDestructiveAdjuster.restoreBowgunAsyncToNormalSync();
        }
    }
}
