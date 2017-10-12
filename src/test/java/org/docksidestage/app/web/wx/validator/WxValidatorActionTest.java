package org.docksidestage.app.web.wx.validator;

import org.docksidestage.app.web.wx.validator.WxValidatorForm.SeaBean;
import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class WxValidatorActionTest extends UnitFortressWebTestCase {

    public void test_index_validationError_messages() {
        // ## Arrange ##
        WxValidatorAction action = new WxValidatorAction();
        inject(action);
        WxValidatorForm form = new WxValidatorForm();
        form.seaBean = new SeaBean();

        // ## Act ##
        // ## Assert ##
        assertValidationError(() -> action.index(form)).handle(data -> {
            data.requiredMessageOf("seaBean.over", Required.class);
            assertException(AssertionError.class, () -> {
                data.requiredMessageOf("seaBean.over", FortressMessages.CONSTRAINTS_Required_MESSAGE);
            });
            UserMessages messages = data.requiredMessages();
            assertTrue(messages.hasMessageOf("seaBean.over"));
            assertFalse(messages.hasMessageOf("seaBean.over", FortressMessages.CONSTRAINTS_Required_MESSAGE));
        });
    }
}
