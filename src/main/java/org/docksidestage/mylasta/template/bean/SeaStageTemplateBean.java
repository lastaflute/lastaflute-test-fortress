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
package org.docksidestage.mylasta.template.bean;

import org.lastaflute.core.template.TemplateManager;
import org.lastaflute.core.template.TemplatePmb;
import org.lastaflute.core.template.TPCall;

/**
 * The Parameter-comMent style template (called PM template) on LastaFlute.
 * @author FreeGen
 */
public class SeaStageTemplateBean implements TemplatePmb {

    public static final String PATH = "bean/SeaStageTemplate.dfpm";

    public static String parsedBy(TemplateManager templateManager, TPCall<SeaStageTemplateBean> oneArgLambda) {
        SeaStageTemplateBean pmb = new SeaStageTemplateBean();
        oneArgLambda.setup(pmb);
        return templateManager.parse(pmb);
    }

    protected String docksidestage;
    protected String magiclamp;

    @Override
    public String getTemplatePath() {
        return PATH;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SeaStageTemplateBean:{");
        sb.append(docksidestage);
        sb.append(", ").append(magiclamp);
        sb.append("}");
        return sb.toString();
    }

    /**
     * Get the value of docksidestage, called by parameter comment.
     * @return The parameter value of docksidestage. (NullAllowed: e.g. when no setting)
     */
    public String getDocksidestage() {
        return docksidestage;
    }

    /**
     * Set the value of docksidestage, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param docksidestage The parameter value for parameter comment. (NullAllowed)
     */
    public void setDocksidestage(String docksidestage) {
        this.docksidestage = docksidestage;
    }

    /**
     * Get the value of magiclamp, called by parameter comment.
     * @return The parameter value of magiclamp. (NullAllowed: e.g. when no setting)
     */
    public String getMagiclamp() {
        return magiclamp;
    }

    /**
     * Set the value of magiclamp, used in parameter comment. <br>
     * Even if empty string, treated as empty plainly. So "IF pmb != null" is false if empty.
     * @param magiclamp The parameter value for parameter comment. (NullAllowed)
     */
    public void setMagiclamp(String magiclamp) {
        this.magiclamp = magiclamp;
    }
}
