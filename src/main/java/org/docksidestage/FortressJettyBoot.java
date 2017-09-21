/*
 * Copyright 2015-2017 the original author or authors.
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
package org.docksidestage;

import org.dbflute.jetty.JettyBoot;

/**
 * @author jflute
 */
public class FortressJettyBoot { // #change_it_first

    public static void main(String[] args) { // e.g. java -Dlasta.env=production -jar fortress.war
        JettyBoot boot = new JettyBoot(8151, "/fortress");
        boot.asDevelopment(isDevelopment());
        // cannot use swagger in jetty for now,
        // jetty searches only in WEB-INF/lib? not searches classpath resources?
        // (and needs to remove metadata-complete="true")
        //boot.useMetaInfoResourceDetect().useWebFragmentsDetect();
        boot.bootAwait();
    }

    private static boolean isDevelopment() {
        return System.getProperty("lasta.env") == null;
    }
}
