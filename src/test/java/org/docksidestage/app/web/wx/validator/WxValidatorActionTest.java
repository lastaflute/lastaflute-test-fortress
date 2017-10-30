package org.docksidestage.app.web.wx.validator;

import java.util.List;

import javax.validation.constraints.Max;

import org.docksidestage.app.web.wx.validator.WxValidatorForm.SeaBean;
import org.docksidestage.app.web.wx.validator.WxValidatorForm.SeaBean.RestaurantBean;
import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxValidatorActionTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    // now making...

    // ===================================================================================
    //                                                                       List Elements
    //                                                                       =============
    public void test_validationError_listElements() {
        // ## Arrange ##
        WxValidatorAction action = new WxValidatorAction();
        inject(action);
        WxValidatorForm form = new WxValidatorForm();

        form.dstoreStringList = newArrayList((String) null);
        form.dstoreImmutableList = prepareImmutableTypeList((String) null);
        form.dstoreIntegerList = newArrayList(87, 88, 89);

        form.seaBean = new SeaBean();
        form.seaBean.restaurantList = newArrayList(new RestaurantBean());
        form.seaBean.restaurantImmutableInstanceList = prepareImmutableInstanceList(new RestaurantBean());
        form.seaBean.restaurantImmutableTypeList = prepareImmutableTypeList(new RestaurantBean());
        form.seaBean.restaurantIterableArrayList = newArrayList(new RestaurantBean());
        // HV000219: Unable to get the most specific value extractor for type
        // org.eclipse.collections.impl.list.immutable.ImmutableSingletonList
        // as several most specific value extractors are declared
        //form.seaBean.restaurantIterableImmutableList = prepareImmutableTypeList(new RestaurantBean());
        form.seaBeanList = newArrayList(new SeaBean());

        // ## Act ##
        // ## Assert ##
        assertValidationError(() -> action.index(form)).handle(data -> {
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // flat list property
            // _/_/_/_/_/_/_/_/_/_/
            // removed <list element>, <immutablelist element>
            data.requiredMessageOf("dstoreStringList[0]", Required.class);
            data.requiredMessageOf("dstoreImmutableList[0]", Required.class);
            data.requiredMessageOf("dstoreIntegerList[2]", Max.class);

            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // single nested property
            // _/_/_/_/_/_/_/_/_/_/
            data.requiredMessageOf("seaBean.over", Required.class);
            assertException(AssertionError.class, () -> {
                data.requiredMessageOf("seaBean.over", FortressMessages.CONSTRAINTS_Required_MESSAGE);
            });

            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // list nested property
            // _/_/_/_/_/_/_/_/_/_/
            data.requiredMessageOf("seaBeanList[0].over", Required.class);
            data.requiredMessageOf("seaBean.restaurantList[0].restaurantName", Required.class);
            data.requiredMessageOf("seaBean.restaurantImmutableInstanceList[0].restaurantName", Required.class);

            // needs to configure ValueExtractorForImmutableList for Hibernate Validator
            // or "restaurantImmutableTypeList[]" in Hibernate Validator-6.0.x
            data.requiredMessageOf("seaBean.restaurantImmutableTypeList[0].restaurantName", Required.class);

            // no problem
            data.requiredMessageOf("seaBean.restaurantIterableArrayList[0].restaurantName", Required.class);

            // HV000219: ...
            //data.requiredMessageOf("seaBean.restaurantIterableImmutableList[0].restaurantName", Required.class);

            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // plain UserMessages
            // _/_/_/_/_/_/_/_/_/_/
            UserMessages messages = data.requiredMessages();
            assertTrue(messages.hasMessageOf("seaBean.over"));
            assertFalse(messages.hasMessageOf("seaBean.over", FortressMessages.CONSTRAINTS_Required_MESSAGE));
            assertTrue(messages.hasMessageOf("seaBeanList[0].over"));
        });
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private <BEAN> List<BEAN> prepareImmutableInstanceList(BEAN bean) {
        @SuppressWarnings("unchecked")
        List<BEAN> immuInsList = (List<BEAN>) Lists.immutable.withAll(newArrayList(bean));
        return immuInsList;
    }

    private <BEAN> ImmutableList<BEAN> prepareImmutableTypeList(BEAN bean) {
        @SuppressWarnings("unchecked")
        final List<BEAN> beanList = newArrayList(bean);
        return Lists.immutable.withAll(beanList);
    }
}
