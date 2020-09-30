package org.docksidestage.remote.harbor.lido.mypage;

import org.docksidestage.dbflute.exentity.Product;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class RemoteHbLidoMypageProductReturn {

    @Required
    public final String productName;
    @Required
    public final Integer regularPrice;

    public RemoteHbLidoMypageProductReturn(Product product) {
        this.productName = product.getProductName();
        this.regularPrice = product.getRegularPrice();
    }

    @Override
    public String toString() {
        return "{" + productName + ", " + regularPrice + "}";
    }
}
