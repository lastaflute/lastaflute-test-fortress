package org.docksidestage.remote.harbor.mypage;

import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class HbMypageProductResult {

    @Required
    public final String productName;
    @Required
    public final Integer regularPrice;

    public HbMypageProductResult(Product product) {
        this.productName = product.getProductName();
        this.regularPrice = product.getRegularPrice();
    }

    @Override
    public String toString() {
        return "{" + productName + ", " + regularPrice + "}";
    }
}
