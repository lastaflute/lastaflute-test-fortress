package org.docksidestage.whitebox.db.dbflute;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.dbflute.cbean.result.ListResultBean;
import org.docksidestage.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.dbflute.exbhv.pmbean.MemberMonthlyPurchasePmb;
import org.docksidestage.dbflute.exentity.customize.MemberMonthlyPurchase;
import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 * @since 1.2.7 (2023/07/19 Wednesday at ichihara)
 */
public class WxOutsideSqlGroupByTest extends UnitFortressBasicTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private PurchaseBhv purchaseBhv;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_outsideSql_groupBy_allRecords() {
        // ## Arrange ##
        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();

        // ## Act ##
        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);

        // ## Assert ##
        assertHasAnyElement(purchaseList);
        for (MemberMonthlyPurchase purchase : purchaseList) {
            showPurchase(purchase);
        }
    }

    // ===================================================================================
    //                                                                  where Range Search
    //                                                                  ==================
    public void test_outsideSql_groupBy_where_purchaseDatetimeFrom() {
        // ## Arrange ##
        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
        pmb.setPurchaseDatetimeFrom(LocalDateTime.of(2005, 04, 1, 0, 0, 0));

        // ## Act ##
        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);

        // ## Assert ##
        assertHasAnyElement(purchaseList);
        for (MemberMonthlyPurchase purchase : purchaseList) {
            showPurchase(purchase);
        }
    }

    public void test_outsideSql_groupBy_where_monthFromBad() {
        // ## Arrange ##
        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
        pmb.setMonthFromBad("2005-04"); // grouping item

        // ## Act ##
        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);

        // ## Assert ##
        assertHasAnyElement(purchaseList);
        for (MemberMonthlyPurchase purchase : purchaseList) {
            showPurchase(purchase);
        }
    }

    // ===================================================================================
    //                                                                 having Range Search
    //                                                                 ===================
    public void test_outsideSql_groupBy_having_monthToHaving() {
        // ## Arrange ##
        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
        pmb.setMonthToHaving("2167-09"); // grouping item

        // ## Act ##
        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);

        // ## Assert ##
        assertHasAnyElement(purchaseList);
        for (MemberMonthlyPurchase purchase : purchaseList) {
            showPurchase(purchase);
        }
    }

    public void test_outsideSql_groupBy_having_priceMaxFrom() {
        // ## Arrange ##
        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
        pmb.setPriceMaxFrom(100); // aggregation item

        // ## Act ##
        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);

        // ## Assert ##
        assertHasAnyElement(purchaseList);
        for (MemberMonthlyPurchase purchase : purchaseList) {
            showPurchase(purchase);
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private void showPurchase(MemberMonthlyPurchase purchase) {
        log(purchase.getMemberId(), purchase.getMemberName(), purchase.getPurchaseMonth(), purchase.getPurchasePriceAvg(),
                purchase.getPurchasePriceMax(), purchase.getPurchaseCount());
    }
}
