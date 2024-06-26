/*
 * Copyright 2015-2024 the original author or authors.
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

import org.apache.catalina.Host;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.valves.ErrorReportValve;
import org.dbflute.tomcat.TomcatBoot;

/**
 * @author jflute
 */
public class FortressTomcatBoot { // #change_it_first

    public static final String CONTEXT = "/fortress";

    public static void main(String[] args) { // e.g. java -Dlasta.env=production -jar lastaflute-test-fortress.war
        TomcatBoot boot = new TomcatBoot(8152, CONTEXT);
        boot.asDevelopment(isDevelopment());
        boot.useMetaInfoResourceDetect().useWebFragmentsDetect(jarName -> { // both for swagger
            return jarName.contains("swagger-ui"); // meanwhile, restricted by [app]_env.properties
        });
        boot.configure("fortress_config.properties", "fortress_env.properties"); // e.g. URIEncoding
        boot.logging("tomcat_logging.properties", op -> {
            op.replace("tomcat.log.name", "catalina_out");
        }); // uses jdk14logger
        boot.asYouLikeIt(resource -> {
            Host host = resource.getHost();
            if (host instanceof StandardHost) {
                String fqcn = SilentErrorReportValve.class.getName();
                ((StandardHost) host).setErrorReportValveClass(fqcn); // suppress server info
            }
        });
        boot.bootAwait();
    }

    private static boolean isDevelopment() {
        return System.getProperty("lasta.env") == null;
    }

    public static class SilentErrorReportValve extends ErrorReportValve {

        public SilentErrorReportValve() {
            setShowReport(false);
            setShowServerInfo(false);
        }
    }
}
