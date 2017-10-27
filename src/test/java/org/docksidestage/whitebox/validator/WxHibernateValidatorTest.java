package org.docksidestage.whitebox.validator;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.bootstrap.GenericBootstrap;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.ValueExtractor;

import org.docksidestage.bizfw.validation.ValueExtractorForImmutableList;
import org.docksidestage.unit.UnitFortressWebTestCase;
import org.docksidestage.whitebox.validator.WxHibernateValidatorTest.MaihamaListParadeBean.StageBean;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.lastaflute.web.validation.VaValidListBean;

/**
 * @author jflute
 */
public class WxHibernateValidatorTest extends UnitFortressWebTestCase {

    // ===================================================================================
    //                                                                       Property Path
    //                                                                       =============
    public void test_propertyPath_indexed_basic() {
        // ## Arrange ##
        Validator validator = buildValidatorFactory(conf -> {
            conf.addValueExtractor(createImmutableListExtractor());
        }).getValidator();
        MaihamaListParadeBean maihama = new MaihamaListParadeBean();
        maihama.seaStages = newArrayList(new StageBean());
        maihama.landStages = Lists.immutable.withAll(newArrayList(new StageBean()));
        // HV000219: Unable to get the most specific value extractor for type
        // org.eclipse.collections.impl.list.immutable.ImmutableSingletonList
        // as several most specific value extractors are declared
        //maihama.piariStages = Lists.immutable.withAll(newArrayList(new StageBean()));
        maihama.piariStages = newArrayList(new StageBean());

        // ## Act ##
        Set<ConstraintViolation<MaihamaListParadeBean>> vioSet = validator.validate(maihama, Default.class);

        // ## Assert ##
        assertHasAnyElement(vioSet);
        for (ConstraintViolation<MaihamaListParadeBean> vio : vioSet) {
            log(vio);
            String path = vio.getPropertyPath().toString();
            if (path.contains("seaStages")) {
                assertEquals("seaStages[0].stageName", path);
            } else if (path.contains("landStages")) {
                assertEquals("landStages[0].stageName", path);
            } else if (path.contains("piariStages")) {
                assertEquals("piariStages[0].stageName", path);
            } else {
                fail();
            }
        }
    }

    public void test_propertyPath_indexed_root() {
        // ## Arrange ##
        Validator validator = buildValidatorFactory(conf -> {
            conf.addValueExtractor(createImmutableListExtractor());
        }).getValidator();
        MaihamaListParadeBean maihama = new MaihamaListParadeBean();
        maihama.seaStages = newArrayList(new StageBean());
        VaValidListBean<MaihamaListParadeBean> listBean = new VaValidListBean<>(newArrayList(maihama));

        // ## Act ##
        Set<ConstraintViolation<VaValidListBean<MaihamaListParadeBean>>> vioSet = validator.validate(listBean, Default.class);

        // ## Assert ##
        assertHasAnyElement(vioSet);
        for (ConstraintViolation<VaValidListBean<MaihamaListParadeBean>> vio : vioSet) {
            log(vio);
            String path = vio.getPropertyPath().toString();
            assertEquals("list[0].seaStages[0].stageName", path);
        }
    }

    public static class MaihamaListParadeBean {

        @Valid
        public List<StageBean> seaStages;
        @Valid
        public ImmutableList<StageBean> landStages;
        @Valid
        public Iterable<StageBean> piariStages;

        public static class StageBean {

            @NotNull
            public String stageName;
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    protected ValidatorFactory buildValidatorFactory(Consumer<Configuration<?>> confLambda) {
        final Configuration<?> conf = createConfiguration();
        confLambda.accept(conf);
        return conf.buildValidatorFactory();
    }

    protected Configuration<?> createConfiguration() {
        return newGenericBootstrap().configure();
    }

    protected GenericBootstrap newGenericBootstrap() {
        return Validation.byDefaultProvider();
    }

    protected ValueExtractor<ImmutableList<@ExtractedValue ?>> createImmutableListExtractor() {
        return new ValueExtractorForImmutableList();
    }
}
