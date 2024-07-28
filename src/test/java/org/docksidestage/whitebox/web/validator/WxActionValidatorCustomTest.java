package org.docksidestage.whitebox.web.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.hibernate.validator.constraints.Length;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.servlet.request.RequestManager;
import org.lastaflute.web.validation.ActionValidator;
import org.lastaflute.web.validation.exception.ValidationErrorException;

import jakarta.annotation.Resource;

/**
 * @author jflute
 */
public class WxActionValidatorCustomTest extends UnitFortressBasicTestCase {

    @Resource
    private RequestManager requestManager;

    // ===================================================================================
    //                                                                            Messages
    //                                                                            ========
    public void test_custom_messages() {
        // ## Arrange ##
        ActionValidator<FortressMessages> validator = createValidator();
        MaihamaMessageBean maihama = new MaihamaMessageBean();
        maihama.sea = "01234567890123456789"; // length over
        maihama.land = "01234567890123456789";
        maihama.piari = "01234567890123456789";
        maihama.dstore = "01234567890123456789";

        // ## Act ##
        try {
            validator.validateApi(maihama, messages -> {});
            fail();
        } catch (ValidationErrorException e) {
            // ## Assert ##
            UserMessages messages = e.getMessages();
            Map<String, List<String>> messageMap = requestManager.getMessageManager().toPropertyMessageMap(Locale.ENGLISH, messages);
            for (Entry<String, List<String>> entry : messageMap.entrySet()) {
                String propertyName = entry.getKey();
                List<String> messageList = entry.getValue();
                assertHasOnlyOneElement(messageList); // as test logic
                if ("sea".equals(propertyName)) {
                    // custom annotation's message() has NotBlank message
                    // but inner annotation is prior
                    assertContains(messageList.get(0), "length must be");
                    markHere("sea_called");
                } else if ("land".equals(propertyName)) {
                    assertContains(messageList.get(0), "must be true"); // inner explicit message
                    markHere("land_called");
                } else if ("piari".equals(propertyName)) { // using report as single
                    assertContains(messageList.get(0), "may not be empty");
                    markHere("piari_called");
                } else if ("dstore".equals(propertyName)) { // using override attribute
                    assertContains(messageList.get(0), "may not be empty");
                    markHere("dstore_called");
                }
            }
            assertMarked("sea_called");
            assertMarked("land_called");
            assertMarked("piari_called");
            assertMarked("dstore_called");
            assertEquals(4, messageMap.size());
        }
    }

    // -----------------------------------------------------
    //                                          Assist Logic
    //                                          ------------
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // can omit [javax.validator.]constraints.NotBlank.message as ActionValidator
    // _/_/_/_/_/_/_/_/_/_/
    public static class MaihamaMessageBean {

        @SeaWithoutMessage
        public String sea;

        @LandWithMessage
        public String land;

        @PiariReportAsSingleMessage
        public String piari;

        @DstoreOverrideAttributeMessage
        public String dstore;
    }

    // -----------------------------------------------------
    //                                       Default Message
    //                                       ---------------
    @Target({ FIELD })
    @Retention(RUNTIME)
    @Constraint(validatedBy = {}) // needed as mark
    @Length(min = 3, max = 9)
    @Documented
    public @interface SeaWithoutMessage {

        String message() default "{constraints.NotBlank.message}"; // dummy

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ FIELD })
    @Retention(RUNTIME)
    @Constraint(validatedBy = {}) // needed as mark
    @Length(min = 3, max = 9, message = "{constraints.AssertTrue.message}")
    @Documented
    public @interface LandWithMessage {

        String message() default "{constraints.NotBlank.message}"; // dummy

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    // -----------------------------------------------------
    //                              Report-as-Single Message
    //                              ------------------------
    @Target({ FIELD })
    @Retention(RUNTIME)
    @Constraint(validatedBy = {}) // needed as mark
    @Length(min = 3, max = 9)
    @Documented
    @ReportAsSingleViolation
    public @interface PiariReportAsSingleMessage {

        String message() default "{constraints.NotBlank.message}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    // -----------------------------------------------------
    //                            Override-Attribute Message
    //                            --------------------------
    @Target({ FIELD })
    @Retention(RUNTIME)
    @Constraint(validatedBy = {}) // needed as mark
    @Length(min = 3, max = 9)
    @Documented
    public @interface DstoreOverrideAttributeMessage {

        @OverridesAttribute(constraint = Length.class)
        String message() default "{constraints.NotBlank.message}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    protected ActionValidator<FortressMessages> createValidator() {
        return new ActionValidator<>(requestManager, () -> new FortressMessages(), new Class<?>[] {});
    }
}
