package org.docksidestage.whitebox.db.dbflute;

import javax.annotation.Resource;

import org.dbflute.exception.SQLFailureException;
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
        // ## Assert ##
        // #for_now jflute migrated to MySQL-8.0.x now so change it (2025/03/31)
        assertException(SQLFailureException.class, () -> {
            purchaseBhv.outsideSql().selectList(pmb);
        });
        //ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);
        //assertHasAnyElement(purchaseList);
        //for (MemberMonthlyPurchase purchase : purchaseList) {
        //    showPurchase(purchase);
        //}
    }

    // #for_now jflute migrated to MySQL-8.0.x now so comment it out (2025/03/31)
    //    // ===================================================================================
    //    //                                                                  where Range Search
    //    //                                                                  ==================
    //    public void test_outsideSql_groupBy_where_purchaseDatetimeFrom() {
    //        // ## Arrange ##
    //        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
    //        pmb.setPurchaseDatetimeFrom(LocalDateTime.of(2005, 04, 1, 0, 0, 0));
    //    
    //        // ## Act ##
    //        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);
    //    
    //        // ## Assert ##
    //        assertHasAnyElement(purchaseList);
    //        for (MemberMonthlyPurchase purchase : purchaseList) {
    //            showPurchase(purchase);
    //        }
    //    }
    //    
    //    public void test_outsideSql_groupBy_where_monthFromBad() {
    //        // ## Arrange ##
    //        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
    //        pmb.setMonthFromBad(LocalDate.of(2005, 4, 1)); // grouping item
    //    
    //        // ## Act ##
    //        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);
    //    
    //        // ## Assert ##
    //        assertHasAnyElement(purchaseList);
    //        for (MemberMonthlyPurchase purchase : purchaseList) {
    //            showPurchase(purchase);
    //        }
    //    }
    //    
    //    // ===================================================================================
    //    //                                                                 having Range Search
    //    //                                                                 ===================
    //    public void test_outsideSql_groupBy_having_monthToHaving() {
    //        // ## Arrange ##
    //        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
    //        pmb.setMonthToHaving(LocalDate.of(2167, 9, 1)); // grouping item
    //    
    //        // ## Act ##
    //        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);
    //    
    //        // ## Assert ##
    //        assertHasAnyElement(purchaseList);
    //        for (MemberMonthlyPurchase purchase : purchaseList) {
    //            showPurchase(purchase);
    //        }
    //    }
    //    
    //    public void test_outsideSql_groupBy_having_priceMaxFrom() {
    //        // ## Arrange ##
    //        MemberMonthlyPurchasePmb pmb = new MemberMonthlyPurchasePmb();
    //        pmb.setPriceMaxFrom(100); // aggregation item
    //    
    //        // ## Act ##
    //        ListResultBean<MemberMonthlyPurchase> purchaseList = purchaseBhv.outsideSql().selectList(pmb);
    //    
    //        // ## Assert ##
    //        assertHasAnyElement(purchaseList);
    //        for (MemberMonthlyPurchase purchase : purchaseList) {
    //            showPurchase(purchase);
    //        }
    //    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private void showPurchase(MemberMonthlyPurchase purchase) {
        log(purchase.getMemberId(), purchase.getMemberName(), purchase.getPurchaseMonth(), purchase.getPurchasePriceAvg(),
                purchase.getPurchasePriceMax(), purchase.getPurchaseCount(), purchase.getServicePointCount());
    }
}
