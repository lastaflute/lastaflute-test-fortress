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
package org.docksidestage.mylasta.mail.whitebox;

import org.lastaflute.core.mail.LaTypicalPostcard;
import org.lastaflute.core.mail.MPCall;
import org.lastaflute.core.mail.Postbox;

/**
 * The postcard for MailFlute on LastaFlute.
 * @author FreeGen
 */
public class WxLoopBeansPostcard extends LaTypicalPostcard implements org.docksidestage.mylasta.mail.PluggedinFortressPostcard {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    public static final String PATH = "whitebox/wx_loop_beans.dfmail";

    // ===================================================================================
    //                                                                         Entry Point
    //                                                                         ===========
    public static WxLoopBeansPostcard droppedInto(Postbox postbox, MPCall<WxLoopBeansPostcard> postcardLambda) {
        WxLoopBeansPostcard postcard = new WxLoopBeansPostcard();
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
        return new String[] {"memberName", "beansRowList"};
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
     * Set the value of memberName, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param memberName The parameter value of memberName. (NotNull)
     */
    public void setMemberName(String memberName) {
        registerVariable("memberName", memberName);
    }

    /**
     * Set the value of beansRowList, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param beansRowList The parameter value of beansRowList. (NotNull)
     */
    public void setBeansRowList(java.util.List<WxLoopBeansRowBean> beansRowList) {
        registerVariable("beansRowList", beansRowList);
    }
}
