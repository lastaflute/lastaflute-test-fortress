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
package org.docksidestage.whitebox.db.dbflute;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbflute.utflute.core.cannonball.CannonballOption;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.docksidestage.dbflute.exentity.Member;
import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 * @since DBFlute-1.2.5 (2021/11/17 Wednesday)
 */
public class MySqlTxIsolationTest extends UnitFortressBasicTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                      Table Snapshot
    //                                                                      ==============
    public void test_RepeatableRead_tableSnapshot() {
        // ## Arrange ##
        Member trigger = memberBhv.selectByPK(1).get(); // snapshot trigger
        Member updated = memberBhv.selectByPK(9).get(); // actually updated
        log(trigger);
        log(updated);
        String newName = "sea";
        assertFalse(newName.equals(updated.getMemberName()));

        // ## Act ##
        // ## Assert ##
        try {
            cannonball(car -> {
                adjustTransactionIsolationLevel_RepeatableRead();

                car.projectA(dragon -> {
                    // make table snapshot at No.1
                    Member member = memberBhv.selectByPK(trigger.getMemberId()).get();
                    log(member.getMemberName());
                }, 1);
                car.projectA(dragon -> {
                    // actually update at No.2
                    performNewTransaction(() -> {
                        Member member = new Member();
                        member.setMemberId(updated.getMemberId());
                        member.setMemberName(newName);
                        memberBhv.updateNonstrict(member);
                        return true; // committed
                    });
                }, 2);

                car.projectA(dragon -> {
                    // table snapshot so old data selected
                    Member member = memberBhv.selectByPK(updated.getMemberId()).get();
                    String memberName = member.getMemberName();
                    log("No.1 ninth: {}", memberName);
                    assertEquals(updated.getMemberName(), memberName);
                }, 1);
                car.projectA(dragon -> {
                    // no snapshot so new data selected
                    Member member = memberBhv.selectByPK(updated.getMemberId()).get();
                    String memberName = member.getMemberName();
                    log("No.3 ninth: {}", memberName);
                    assertEquals(newName, memberName);
                }, 3);
            }, new CannonballOption().threadCount(3));
        } finally { // rollback
            performNewTransaction(() -> {
                Member member = memberBhv.selectByPK(updated.getMemberId()).get();
                member.setMemberName(updated.getMemberName());
                memberBhv.updateNonstrict(member);
                return true; // committed
            });
        }
    }
    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    // not yet used
    //private void adjustTransactionIsolationLevel_ReadCommitted() {
    //    doAdjustTransactionIsolationLevelCommitted("READ COMMITTED");
    //}

    private void adjustTransactionIsolationLevel_RepeatableRead() {
        doAdjustTransactionIsolationLevelCommitted("REPEATABLE READ");
    }

    private void doAdjustTransactionIsolationLevelCommitted(String isolationExp) {
        String sql = "set SESSION transaction isolation level " + isolationExp;
        DataSource dataSource = getDataSource();
        Connection conn = null;
        Statement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to execute the SQL: " + sql, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ignored) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
    }
}
