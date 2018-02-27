/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.app.web.wx.response.stream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.docksidestage.app.web.base.FortressBaseAction;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.StreamResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseStreamAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxResponseStreamAction.class);

    // http://localhost:8151/fortress/wx/response/stream/small/
    @Execute
    public StreamResponse small() {
        return asStream("sea.txt").stream(out -> {
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                logger.debug("#download begin writing");
                out.write(ins);
                logger.debug("#download end writing");
            }
        });
    }

    // http://localhost:8151/fortress/wx/response/stream/large/
    @Execute
    public StreamResponse large() {
        return asStream("sea.txt").stream(out -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000000; i++) {
                sb.append("1234567890");
            }
            byte[] buf = sb.toString().getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                logger.debug("#download begin writing");
                out.write(ins);
                logger.debug("#download end writing");
            }
        });
    }

    // http://localhost:8151/fortress/wx/response/stream/output/
    @Execute
    public StreamResponse output() {
        return asStream("sea.txt").stream(out -> {
            OutputStream ous = out.stream();
            ous.write(904);
        });
    }
}
