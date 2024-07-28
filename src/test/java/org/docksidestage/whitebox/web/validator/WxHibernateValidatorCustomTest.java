package org.docksidestage.whitebox.web.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Set;

import org.dbflute.utflute.core.PlainTestCase;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Payload;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.groups.Default;

/**
 * @author jflute
 */
public class WxHibernateValidatorCustomTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            Messages
    //                                                                            ========
    public void test_custom_messages_inner_outer() {
        // ## Arrange ##
        Validator validator = buildValidatorFactory().getValidator();
        MaihamaMessageBean maihama = new MaihamaMessageBean();
        maihama.sea = "01234567890123456789"; // length over
        maihama.land = "01234567890123456789"; // length over

        // ## Act ##
        Set<ConstraintViolation<MaihamaMessageBean>> vioSet = validator.validate(maihama, Default.class);

        // ## Assert ##
        assertHasAnyElement(vioSet);
        for (ConstraintViolation<MaihamaMessageBean> vio : vioSet) {
            log("vio: {}", vio);
            if ("sea".equals(vio.getPropertyPath().toString())) {
                // custom annotation's message() has NotBlank message but inner annotation is prior
                assertContainsAny(vio.getMessage(), "length", "長さ");
                assertContains(vio.getMessageTemplate(), Length.class.getName() + ".message");
                markHere("sea_called");
            }
            if ("land".equals(vio.getPropertyPath().toString())) {
                // inner annotation has explicit annotation
                assertContainsAny(vio.getMessage(), "true");
                assertContains(vio.getMessageTemplate(), AssertTrue.class.getName() + ".message");
                markHere("land_called");
            }
        }
        assertMarked("sea_called");
        assertMarked("land_called");
        assertEquals(2, vioSet.size());
    }

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // *@ReportAsSingleViolation test is implemenated at ActionValidator's test
    // (also @OverrideAttribute)
    // _/_/_/_/_/_/_/_/_/_/

    // -----------------------------------------------------
    //                                          Assist Logic
    //                                          ------------
    public static class MaihamaMessageBean {

        @SeaWithoutMessage
        public String sea;

        @LandWithMessage
        public String land;
    }

    @Target({ FIELD })
    @Retention(RUNTIME)
    @Constraint(validatedBy = {}) // needed as mark
    @Length(min = 3, max = 9)
    @Documented
    public @interface SeaWithoutMessage {

        String message() default "{jakarta.validation.constraints.NotBlank.message}"; // dummy

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ FIELD })
    @Retention(RUNTIME)
    @Constraint(validatedBy = {}) // needed as mark
    @Length(min = 3, max = 9, message = "{jakarta.validation.constraints.AssertTrue.message}")
    @Documented
    public @interface LandWithMessage {

        String message() default "{jakarta.validation.constraints.NotBlank.message}"; // dummy

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    protected ValidatorFactory buildValidatorFactory() {
        return Validation.byDefaultProvider().configure().buildValidatorFactory();
    }
}
