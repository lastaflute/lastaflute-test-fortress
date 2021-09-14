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
package org.docksidestage.mylasta.mail.whitebox;

import org.lastaflute.core.mail.LaTypicalPostcard;
import org.lastaflute.core.mail.MPCall;
import org.lastaflute.core.mail.Postbox;

/**
 * The postcard for MailFlute on LastaFlute.
 * @author FreeGen
 */
public class WxOutsidesqlVariablePostcard extends LaTypicalPostcard implements org.docksidestage.mylasta.mail.PluggedinFortressPostcard {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    public static final String PATH = "whitebox/wx_outsidesql_variable.dfmail";

    // ===================================================================================
    //                                                                         Entry Point
    //                                                                         ===========
    public static WxOutsidesqlVariablePostcard droppedInto(Postbox postbox, MPCall<WxOutsidesqlVariablePostcard> postcardLambda) {
        WxOutsidesqlVariablePostcard postcard = new WxOutsidesqlVariablePostcard();
        postcardLambda.write(postcard);
        postbox.post(postcard);
        return postcard;
    }

    // ===================================================================================
    //                                                                           Meta Data
    //                                                                           =========
    @Override
    protected String getBodyFile() {
        return PATH;
    }

    @Override
    protected String[] getPropertyNames() {
        return new String[] {"basicCls", "fixedCls", "basicClsList", "fixedClsOneList"};
    }

    // ===================================================================================
    //                                                                    Postcard Request
    //                                                                    ================
    // -----------------------------------------------------
    //                                          Mail Address
    //                                          ------------
    public void setFrom(String from, String personal) {
        doSetFrom(from, personal);
    }

    public void addTo(String to) {
        doAddTo(to);
    }

    public void addTo(String to, String personal) {
        doAddTo(to, personal);
    }

    public void addCc(String cc) {
        doAddCc(cc);
    }

    public void addCc(String cc, String personal) {
        doAddCc(cc, personal);
    }

    public void addBcc(String bcc) {
        doAddBcc(bcc);
    }

    public void addBcc(String bcc, String personal) {
        doAddBcc(bcc, personal);
    }

    public void addReplyTo(String replyTo) {
        doAddReplyTo(replyTo);
    }

    public void addReplyTo(String replyTo, String personal) {
        doAddReplyTo(replyTo, personal);
    }

    // -----------------------------------------------------
    //                                  Application Variable
    //                                  --------------------
    /**
     * Set the value of basicCls, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param basicCls The parameter value of basicCls. (NotNull)
     */
    public void setBasicCls(String basicCls) {
        registerVariable("basicCls", basicCls);
    }

    /**
     * Set the value of fixedCls, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param fixedCls The parameter value of fixedCls. (NotNull)
     */
    public void setFixedCls(String fixedCls) {
        registerVariable("fixedCls", fixedCls);
    }

    /**
     * Set the value of basicClsList, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param basicClsList The parameter value of basicClsList. (NotNull)
     */
    public void setBasicClsList(String basicClsList) {
        registerVariable("basicClsList", basicClsList);
    }

    /**
     * Set the value of fixedClsOneList, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param fixedClsOneList The parameter value of fixedClsOneList. (NotNull)
     */
    public void setFixedClsOneList(String fixedClsOneList) {
        registerVariable("fixedClsOneList", fixedClsOneList);
    }
}
