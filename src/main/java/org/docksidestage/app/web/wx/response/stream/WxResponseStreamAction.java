/*
 * Copyright 2015-2021 the original author or authors.
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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.dbflute.helper.token.file.FileToken;
import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.app.web.base.FortressBaseAction;
import org.docksidestage.dbflute.exbhv.ProductBhv;
import org.docksidestage.mylasta.action.FortressMessages;
import org.lastaflute.core.message.exception.MessagingApplicationException;
import org.lastaflute.web.Execute;
import org.lastaflute.web.login.AllowAnyoneAccess;
import org.lastaflute.web.response.ApiResponse;
import org.lastaflute.web.response.StreamResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
@AllowAnyoneAccess
public class WxResponseStreamAction extends FortressBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(WxResponseStreamAction.class);

    @Resource
    private ProductBhv productBhv;

    // http://localhost:8151/fortress/wx/response/stream/small/
    @Execute
    public StreamResponse small() {
        return asStream("sea.txt").stream(out -> {
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                out.write(ins);
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
                out.write(ins);
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

    // http://localhost:8151/fortress/wx/response/stream/cursortsv/
    @Execute
    public StreamResponse cursortsv() {
        return asStream("sea.csv").stream(out -> {
            FileToken fileToken = new FileToken();
            fileToken.make(out.stream(), writer -> {
                productBhv.selectCursor(cb -> {
                    cb.query().setProductStatusCode_Equal_OnSaleProduction();
                }, product -> {
                    List<String> valueList = DfCollectionUtil.newArrayList(product.getProductId().toString(), product.getProductName());
                    logger.debug("values:{}", valueList);
                    try {
                        writer.writeRow(valueList);
                    } catch (IOException e) {
                        throw new IllegalStateException("Failed to write row: " + valueList, e);
                    }
                });
            }, op -> op.delimitateByTab().encodeAsUTF8());
        });
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    // http://localhost:8151/fortress/wx/response/stream/updateplain/
    //  => exception (AccessContextNotFoundException, ResponseDownloadStreamCallUpdateException)
    @Execute
    public StreamResponse updateplain() {
        return asStream("sea.txt").stream(out -> {
            productBhv.selectByPK(1).alwaysPresent(product -> {
                product.setProductStatusCode_SaleStop();
                productBhv.updateNonstrict(product);
            });
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                out.write(ins);
            }
        });
    }

    // http://localhost:8151/fortress/wx/response/stream/updatetx/
    @Execute
    public StreamResponse updatetx() {
        return asStream("sea.txt").inActionTransaction().stream(out -> {
            productBhv.selectByPK(1).alwaysPresent(product -> {
                product.setProductStatusCode_SaleStop();
                productBhv.updateNonstrict(product);
            });
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                out.write(ins);
            }
        });
    }

    // ===================================================================================
    //                                                                            Japanese
    //                                                                            ========
    // http://localhost:8151/fortress/wx/response/stream/japanese/
    @Execute
    public StreamResponse japanese() {
        return asStream("\u6d77 + \u9678 in \u821e\u6d5c.txt").encodeFileName().stream(out -> {
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                out.write(ins);
            }
        });
    }

    // ===================================================================================
    //                                                                           Exception
    //                                                                           =========
    // http://localhost:8151/fortress/wx/response/stream/validationerror/
    //  => handled by ApiFailureHook
    // http://localhost:8151/fortress/wx/response/stream/validationerror/?land=oneman
    //  => download
    @Execute
    public StreamResponse validationerror(WxResponseStreamForm form) {
        // you can choose error response, HTML? or JSON?
        //validate(form, messages -> {}, () -> {
        //    return asHtml(path_object);
        //});
        validateApi(form, messages -> {}); // as JSON (ApiFailureHook)
        return asStream("sea.txt").stream(out -> {
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                out.write(ins);
            }
        });
    }

    // http://localhost:8151/fortress/wx/response/stream/withbizex/
    //  => exception (404.html in webapp)
    @Execute
    public StreamResponse withbizex() {
        String debugMsg = "Stream Business Exception Test";
        FortressMessages messages = createMessages();
        messages.addErrorsAppIllegalTransition(GLOBAL);
        throw new MessagingApplicationException(debugMsg, messages);
    }

    // http://localhost:8151/fortress/wx/response/stream/withbizexapi/
    //  => handled by ApiFailureHook
    @Execute
    public ApiStreamResponse withbizexapi() {
        if (Boolean.TRUE) { // to avoid suppress warning
            String debugMsg = "Stream Business Exception Test";
            FortressMessages messages = createMessages();
            messages.addErrorsAppIllegalTransition(GLOBAL);
            throw new MessagingApplicationException(debugMsg, messages);
        }
        // dead code here, to check compile
        ApiStreamResponse response = new ApiStreamResponse("sea.txt");
        response.stream(out -> {
            byte[] buf = "download".getBytes("UTF-8");
            try (InputStream ins = new ByteArrayInputStream(buf)) {
                out.write(ins);
            }
        });
        return response;
    }

    public static class ApiStreamResponse extends StreamResponse implements ApiResponse {

        public ApiStreamResponse(String fileName) {
            super(fileName);
        }
    }
}
