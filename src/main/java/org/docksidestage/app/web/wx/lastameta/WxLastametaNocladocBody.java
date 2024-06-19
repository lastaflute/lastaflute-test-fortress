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
package org.docksidestage.app.web.wx.lastameta;

import java.util.List;
import java.util.Map;

import org.docksidestage.dbflute.allcommon.CDef;
import org.eclipse.collections.api.list.ImmutableList;
import org.lastaflute.web.validation.Required;

// without javadoc
public class WxLastametaNocladocBody {

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // description can be identified for debug log (unknown variableName case)
    //
    // attention: when you use also // comment, cannot get javadoc
    // e.g.
    //  /** Sea String Basic e.g. SeaOfDreams */
    //  public String seaStringBasic; // you don't comment here
    // _/_/_/_/_/_/_/_/_/_/

    // ===================================================================================
    //                                                                     String Property
    //                                                                     ===============
    /** Sea String basic e.g. SeaOfDreams */
    public String seaStringBasic;

    /** Sea String quoted e.g. "Sea of Dreams" */
    public String seaStringQuoted;

    /**Sea String non space e.g. "Sea of Dreams"*/
    public String seaStringNonSpace;

    // ===================================================================================
    //                                                                     Number Property
    //                                                                     ===============
    // test of mismatched type for integer
    ///** Sea Integer Broken e.g. "over" */
    //public Integer seaIntegerBroken;

    // ===================================================================================
    //                                                                       List Property
    //                                                                       =============
    /** Sea List basic e.g. [dockside,hangar] */
    public List<String> seaListBasic;

    /** Sea ImmutableList basic e.g. [dockside,hangar] */
    public ImmutableList<String> seaImmutableListBasic;

    // ===================================================================================
    //                                                                        Map Property
    //                                                                        ============
    /** Sea Map basic e.g. {dockside:over,hangar:mystic} */
    public Map<String, String> seaMapBasic;

    /** Sea Map quoted e.g. {"dockside":"over","hangar":"mystic"} */
    public Map<String, String> seaMapQuoted;

    /** Sea Map space delimiter e.g. {dockside : over , hangar : mystic} */
    public Map<String, String> seaMapSpaceDelimiter;

    /** Sea Map space value e.g. {dock side:over the waves,han gar:mys tic} */
    public Map<String, String> seaMapSpaceValue;

    /** Sea Map non generic e.g. {dockside:over,hangar:mystic} */
    @SuppressWarnings("rawtypes")
    public Map seaMapNonGeneric;

    /** Sea Map non typed e.g. {dockside:over,hangar:mystic} */
    public Map<?, ?> seaMapNonTyped;

    // test of broken expression for map
    ///** Sea Map e.g. {dockside=over,hangar=mystic} */
    //public Map<String, String> seaMapBrokenEqual;

    /** Sea Map Integer e.g. {dockside:1,hangar:2} */
    public Map<String, Integer> seaMapInteger;

    /** Sea Map Integer Mismatched e.g. {dockside:over,hangar:mystic} */
    public Map<String, Integer> seaMapIntegerMismatched;

    /** Sea Map nest bean e.g. {dockside:over,hangar:mystic} */
    public Map<String, MapNestPart> seaMapNestBean;

    /**
     * @author jflute
     */
    public static class MapNestPart {

        @Required
        public String waterfrontName;
    }

    // ===================================================================================
    //                                                                       Enum Property
    //                                                                       =============
    /** Sea Classification basic e.g. FML */
    public CDef.MemberStatus seaClsBasic;
}
