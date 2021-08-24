/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.mylasta.direction.sponsor.planner.trial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dbflute.util.Srl;
import org.lastaflute.web.path.restful.router.NumericBasedRestfulRouter;
import org.lastaflute.web.path.restful.router.RestfulRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class HyphenatedEventPowerPlayer {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(HyphenatedEventPowerPlayer.class);

    private static final List<String> hyphenatedEventList;
    static {
        final List<String> workingList = new ArrayList<>();
        setupHyphenatedEventList(workingList);
        hyphenatedEventList = Collections.unmodifiableList(workingList);
    }

    private static final List<String> camelizedEventList;
    static {
        camelizedEventList = hyphenatedEventList.stream().map(event -> {
            return Srl.initUncap(Srl.camelize(event, "-"));
        }).collect(Collectors.toList());
    }

    // -----------------------------------------------------
    //                               Developer Setting Point
    //                               -----------------------
    private static void setupHyphenatedEventList(List<String> workingList) {
        workingList.add("hangar-mystic");
    }

    // ===================================================================================
    //                                                                              Router
    //                                                                              ======
    public RestfulRouter createHyphenatedEventRestfulRouter() {
        return new NumericBasedRestfulRouter() {

            @Override
            protected String doConvertToMappingPath(String requestPath) {
                final String filtered = filterForMappingPath(requestPath);
                return super.doConvertToMappingPath(filtered);
            }

            @Override
            protected String convertToRestfulPath(Class<?> actionType, String actionUrl, RestfulPathConvertingParam convertingParam) {
                final String filtered = filterForRestfulPath(actionUrl);
                return super.convertToRestfulPath(actionType, filtered, convertingParam);
            }
        };
    }

    // ===================================================================================
    //                                                                              Filter
    //                                                                              ======
    protected String filterForMappingPath(String requestPath) {
        // #for_now jflute contains for now, endsWith() way is more correct (2021/08/20)
        final Optional<String> foundEvent = hyphenatedEventList.stream().filter(event -> requestPath.contains("/" + event)).findFirst();
        return foundEvent.map(hyphenatedEvent -> {
            final String camelizedEvent = Srl.initUncap(Srl.camelize(hyphenatedEvent, "-"));
            final String filtered = Srl.replace(requestPath, "/" + hyphenatedEvent, "/" + camelizedEvent);
            logger.debug("...Filtering requestPath for hyphenated event-suffix: hyphenated={}", hyphenatedEvent);
            logger.debug("  from: {}", requestPath);
            logger.debug("    to: {}", filtered);
            return filtered;
        }).orElse(requestPath);
    }

    protected String filterForRestfulPath(String actionUrl) {
        // #for_now jflute also here "contains" for now (2021/08/20)
        final Optional<String> foundEvent = camelizedEventList.stream().filter(event -> actionUrl.contains("/" + event)).findFirst();
        return foundEvent.map(camelizedEvent -> {
            final String hyphenatedEvent = Srl.decamelize(camelizedEvent, "-").toLowerCase();
            final String filtered = Srl.replace(actionUrl, "/" + camelizedEvent, "/" + hyphenatedEvent);
            logger.debug("...Filtering actionUrl for hyphenated event-suffix: camelized={}", camelizedEvent);
            logger.debug("  from: {}", actionUrl);
            logger.debug("    to: {}", filtered);
            return filtered;
        }).orElse(actionUrl);
    }
}