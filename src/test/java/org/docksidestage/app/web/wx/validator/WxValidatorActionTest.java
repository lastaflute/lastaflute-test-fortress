package org.docksidestage.app.web.wx.validator;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;

import org.docksidestage.app.web.wx.validator.WxValidatorForm.LandBean;
import org.docksidestage.app.web.wx.validator.WxValidatorForm.PiariBean;
import org.docksidestage.app.web.wx.validator.WxValidatorForm.SeaBean;
import org.docksidestage.app.web.wx.validator.WxValidatorForm.SeaBean.RestaurantBean;
import org.docksidestage.app.web.wx.validator.WxValidatorForm.SeaBean.RestaurantBean.MenuBean;
import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxValidatorActionTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_validationError_basic() {
        // ## Arrange ##
        WxValidatorAction action = new WxValidatorAction();
        inject(action);
        WxValidatorForm form = new WxValidatorForm();
        form.seaString = "mystic"; // over
        form.seaInteger = 100; // over
        form.seaFloat = 89F; // over (cannot be 88.1F)
        form.seaDecimal = new BigDecimal(88.1); // over (cannot be 88.1F)

        // ## Act ##
        // ## Assert ##
        assertValidationError(() -> action.index(form)).handle(data -> {
            data.requiredMessageOf("seaString", Length.class);
            data.requiredMessageOf("seaInteger", Max.class);
            data.requiredMessageOf("seaFloat", Max.class);
            data.requiredMessageOf("seaDecimal", Max.class);
            assertEquals(4, data.requiredMessages().size());
        });
    }

    // ===================================================================================
    //                                                                       List Elements
    //                                                                       =============
    public void test_validationError_listElements() {
        // ## Arrange ##
        WxValidatorAction action = new WxValidatorAction();
        inject(action);
        WxValidatorForm form = new WxValidatorForm();

        form.dstoreStringList = newArrayList((String) null); // required
        form.dstoreImmutableList = prepareImmutableTypeList((String) null); // required
        form.dstoreIntegerList = newArrayList(87, 88, 89); // over

        form.seaBean = new SeaBean();
        form.seaBean.restaurantList = newArrayList(new RestaurantBean());
        form.seaBean.restaurantImmutableInstanceList = prepareImmutableInstanceList(new RestaurantBean());
        form.seaBean.restaurantImmutableTypeList = prepareImmutableTypeList(new RestaurantBean());
        form.seaBean.restaurantIterableArrayList = newArrayList(new RestaurantBean());
        // HV000219: Unable to get the most specific value extractor for type
        // org.eclipse.collections.impl.list.immutable.ImmutableSingletonList
        // as several most specific value extractors are declared
        //form.seaBean.restaurantIterableImmutableList = prepareImmutableTypeList(new RestaurantBean());
        {
            SeaBean elementSeaBean = new SeaBean();
            RestaurantBean nestedRestaurantBean = new RestaurantBean();
            nestedRestaurantBean.genreList = newArrayList("mexico", (String) null); // required
            nestedRestaurantBean.menuList = newArrayList(new MenuBean()); // required
            elementSeaBean.restaurantList = newArrayList(nestedRestaurantBean);
            form.seaBeanList = newArrayList(elementSeaBean);
        }

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

            // more nested
            data.requiredMessageOf("seaBeanList[0].restaurantList[0].genreList[1]", Required.class);
            data.requiredMessageOf("seaBeanList[0].restaurantList[0].menuList[0].menuName", Required.class);

            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // plain UserMessages
            // _/_/_/_/_/_/_/_/_/_/
            UserMessages messages = data.requiredMessages();
            assertTrue(messages.hasMessageOf("seaBean.over"));
            assertFalse(messages.hasMessageOf("seaBean.over", FortressMessages.CONSTRAINTS_Required_MESSAGE));
            assertTrue(messages.hasMessageOf("seaBeanList[0].over"));

            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // whole count
            // _/_/_/_/_/_/_/_/_/_/
            // hard to count...
            //assertEquals(4, data.requiredMessages().size());
        });
    }

    // ===================================================================================
    //                                                                        Generic Type
    //                                                                        ============
    public void test_validationError_generic_basic() {
        // ## Arrange ##
        WxValidatorAction action = new WxValidatorAction();
        inject(action);
        WxValidatorForm form = new WxValidatorForm();
        form.landBean = new LandBean<PiariBean>();
        form.landBean.haunted = new PiariBean(); // required
        form.landBean.haunted.plaza = "celebration"; // over

        // ## Act ##
        // ## Assert ##
        assertValidationError(() -> action.index(form)).handle(data -> {
            data.requiredMessageOf("landBean.oneman", Required.class);
            data.requiredMessageOf("landBean.minio", Required.class);
            data.requiredMessageOf("landBean.haunted.iks", Required.class);
            data.requiredMessageOf("landBean.haunted.plaza", Length.class);
            data.requiredMessageOf("landBean.bonvoBean", Required.class);
            assertEquals(5, data.requiredMessages().size());
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
