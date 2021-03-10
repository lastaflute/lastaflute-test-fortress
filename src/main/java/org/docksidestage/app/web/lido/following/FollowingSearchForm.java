package org.docksidestage.app.web.lido.following;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.lastaflute.core.util.Lato;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class FollowingSearchForm {

    @Required
    @Min(0)
    public Integer limit;

    @Length(max = 7)
    public String memberPrefix;

    @Min(100)
    public Integer fromPurchasePrice;

    public Boolean livesChiba;

    @Override
    public String toString() {
        return Lato.string(this);
    }
}
