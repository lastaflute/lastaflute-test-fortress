package org.docksidestage.whitebox.web.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Set;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertTrue;
import javax.validation.groups.Default;

import org.dbflute.utflute.core.PlainTestCase;
import org.hibernate.validator.constraints.Length;

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
                assertContains(vio.getMessage(), "length must be");
                assertContains(vio.getMessageTemplate(), Length.class.getName() + ".message");
                markHere("sea_called");
            }
            if ("land".equals(vio.getPropertyPath().toString())) {
                // inner annotation has explicit annotation
                assertContains(vio.getMessage(), "must be true");
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

        String message() default "{javax.validation.constraints.NotBlank.message}"; // dummy

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ FIELD })
    @Retention(RUNTIME)
    @Constraint(validatedBy = {}) // needed as mark
    @Length(min = 3, max = 9, message = "{javax.validation.constraints.AssertTrue.message}")
    @Documented
    public @interface LandWithMessage {

        String message() default "{javax.validation.constraints.NotBlank.message}"; // dummy

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
