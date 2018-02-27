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
package org.docksidestage.whitebox.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;

import org.dbflute.exception.EntityAlreadyDeletedException;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.magic.async.AsyncManager;
import org.lastaflute.core.magic.async.exception.ConcurrentParallelRunnerException;
import org.lastaflute.core.magic.async.future.YourFuture;

/**
 * @author jflute
 */
public class WxAsyncManagerTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private AsyncManager asyncManager;
    @Resource
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                        Asynchronous
    //                                                                        ============
    public void test_async_basic() {
        // ## Arrange ##

        // ## Act ##
        YourFuture future = asyncManager.async(() -> {
            sleep(300);
            log("...Marking here");
            markHere("called");
        });

        // ## Assert ##
        log("...Waiting for done");
        future.waitForDone();
        assertMarked("called");
    }

    public void test_async_exception() {
        // ## Arrange ##

        // ## Act ##
        YourFuture future = asyncManager.async(() -> {
            sleep(300);
            log("...Marking here");
            markHere("called");
            throw new IllegalStateException("sea");
        });

        // ## Assert ##
        log("...Waiting for done");
        future.waitForDone();
        assertMarked("called");
    }

    // ===================================================================================
    //                                                                            Parallel
    //                                                                            ========
    // -----------------------------------------------------
    //                                                 Basic
    //                                                 -----
    public void test_parallel_params() {
        // ## Arrange ##
        List<String> parameterList = Arrays.asList("sea", "land", "piari");

        // ## Act ##
        List<String> actualList = new CopyOnWriteArrayList<>();
        asyncManager.parallel(runner -> {
            String parameter = (String) runner.getParameter().get();
            log(parameter);
            actualList.add(parameter);
        }, op -> {
            op.params(parameterList);
        });

        // ## Assert ##
        log(actualList);
        assertEquals(parameterList.size(), actualList.size());
    }

    public void test_parallel_withoutParams() {
        // ## Arrange ##

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
                if (entryNumber == 1) {
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
            String msg = cause.getMessage() + ln() + "..." + ln() + cause.getCause().getMessage();
            log(msg);
            assertContains(msg, EntityAlreadyDeletedException.class.getSimpleName());
            assertContainsAll(msg, "sea", "land", "bonvo");
            assertNull(cause.getCause().getCause());
        });
    }

    public void test_parallel_exception_firstCause() {
        // ## Arrange ##
        List<String> parameterList = Arrays.asList("sea", "land", "piari", "bonvo");

        // ## Act ##
        // ## Assert ##
        assertException(ConcurrentParallelRunnerException.class, () -> {
            asyncManager.parallel(runner -> {
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
                op.params(parameterList).throwImmediatelyByFirstCause();
            });
        }).handle(cause -> {
            StringBuilder sb = new StringBuilder();
            sb.append(cause.getMessage());
            sb.append(ln()).append("...").append(ln()).append(cause.getCause().getMessage());
            sb.append(ln()).append("...").append(ln()).append(cause.getCause().getCause().getMessage());
            String msg = sb.toString();
            log(msg);
            assertNotContains(msg, EntityAlreadyDeletedException.class.getSimpleName());
            assertContains(msg, "sea");
            assertNotContains(msg, "land");
            assertNotContains(msg, "piari");
            assertNotContains(msg, "bonvo");
        });
    }
}
