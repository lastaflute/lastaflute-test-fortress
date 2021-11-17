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
package org.docksidestage.whitebox.core.message;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.mylasta.action.FortressMessages;
import org.docksidestage.unit.UnitFortressBasicTestCase;
import org.lastaflute.core.message.MessageManager;
import org.lastaflute.core.message.UserMessage;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.core.message.resources.MessageNamedParameter;

/**
 * @author jflute
 */
public class WxMessageManagerTest extends UnitFortressBasicTestCase {

    @Resource
    private MessageManager messageManager;

    // ===================================================================================
    //                                                                        getMessage()
    //                                                                        ============
    public void test_getMessage() throws Exception {
        assertEquals("length must be between {min} and {max}", getMessage(FortressMessages.CONSTRAINTS_Length_MESSAGE));
        assertEquals("length must be between 1 and 2", getMessage(FortressMessages.CONSTRAINTS_Length_MESSAGE, 1, 2));

        // sea:{1}, land:{showbase}, piari:{0}, bonvo:{mic}, dstore:{2}
        assertEquals("*[reversed parameter] sea:2, land:5, piari:1, bonvo:4, dstore:3",
                getMessage(FortressMessages.WHITEBOX_MESSAGE_VALUES_REVERSED, 1, 2, 3, 4, 5));
    }

    private String getMessage(String key) {
        Locale locale = Locale.ENGLISH;
        return messageManager.getMessage(locale, key);
    }

    private String getMessage(String key, Object... values) {
        Locale locale = Locale.ENGLISH;
        return messageManager.getMessage(locale, key, values);
    }

    // ===================================================================================
    //                                                                        UserMessages
    //                                                                        ============
    // -----------------------------------------------------
    //                                      Indexed by Index
    //                                      ----------------
    public void test_UserMessages_indexedByIndex() throws Exception {
        // ## Arrange ##
        UserMessages messages = createUserMessage(FortressMessages.WHITEBOX_MESSAGE_VALUES_INDEXED, 1, 2, 3);

        // ## Act ##
        List<String> messageList = toMessageList(messages);

        // ## Assert ##
        assertHasOnlyOneElement(messageList);
        assertEquals("*[indexed parameter] sea:1, land:2, piari:3", messageList.get(0));
    }

    // -----------------------------------------------------
    //                                        Named by Index
    //                                        --------------
    public void test_UserMessages_namedByIndex_basic() throws Exception {
        // ## Arrange ##
        UserMessages messages = createUserMessage(FortressMessages.WHITEBOX_MESSAGE_VALUES_NAMED, 1, 2, 3);

        // ## Act ##
        List<String> messageList = toMessageList(messages);

        // ## Assert ##
        assertHasOnlyOneElement(messageList);

        // ordered so don't resolve named parameters by index, use MessageNamedParameter
        assertEquals("*[named parameter] sea:2, land:3, piari:1", messageList.get(0));
    }

    public void test_UserMessages_namedByIndex_onlyOneVariable() throws Exception {
        // ## Arrange ##
        UserMessages messages = createUserMessage(FortressMessages.CONSTRAINTS_Max_MESSAGE, 1);

        // ## Act ##
        List<String> messageList = toMessageList(messages);

        // ## Assert ##
        assertHasOnlyOneElement(messageList);
        assertEquals("must be less than or equal to 1", messageList.get(0));
    }

    public void test_UserMessages_namedByIndex_special() throws Exception {
        // ## Arrange ##
        UserMessages messages = createUserMessage(FortressMessages.CONSTRAINTS_Length_MESSAGE, 1, 2);

        // ## Act ##
        List<String> messageList = toMessageList(messages);

        // ## Assert ##
        assertHasOnlyOneElement(messageList);
        assertEquals("length must be between 1 and 2", messageList.get(0));
    }

    // -----------------------------------------------------
    //                                        Named by Named
    //                                        --------------
    public void test_UserMessages_namedByNamed_basic() throws Exception {
        // ## Arrange ##
        final Map<String, Object> namedValueMap = DfCollectionUtil.newLinkedHashMap();
        namedValueMap.put("hangar", "mystic");
        namedValueMap.put("showbase", "oneman");
        namedValueMap.put("celeb", "square");
        List<MessageNamedParameter> parameterList = MessageNamedParameter.listOf(namedValueMap);
        UserMessages messages = createUserMessage(FortressMessages.WHITEBOX_MESSAGE_VALUES_NAMED, parameterList.toArray());

        // ## Act ##
        List<String> messageList = toMessageList(messages);

        // ## Assert ##
        assertHasOnlyOneElement(messageList);

        // message resource: *[named parameter] sea:{hangar}, land:{showbase}, piari:{celeb}
        assertEquals("*[named parameter] sea:mystic, land:oneman, piari:square", messageList.get(0));
    }

    public void test_UserMessages_namedByNamed_short() throws Exception {
        // ## Arrange ##
        final Map<String, Object> namedValueMap = DfCollectionUtil.newLinkedHashMap();
        namedValueMap.put("hangar", "mystic");
        namedValueMap.put("celeb", "square");
        List<MessageNamedParameter> parameterList = MessageNamedParameter.listOf(namedValueMap);
        UserMessages messages = createUserMessage(FortressMessages.WHITEBOX_MESSAGE_VALUES_NAMED, parameterList.toArray());

        // ## Act ##
        List<String> messageList = toMessageList(messages);

        // ## Assert ##
        assertHasOnlyOneElement(messageList);

        // message resource: *[named parameter] sea:{hangar}, land:{showbase}, piari:{celeb}
        assertEquals("*[named parameter] sea:mystic, land:{showbase}, piari:square", messageList.get(0));
    }

    // -----------------------------------------------------
    //                                          Assist Logic
    //                                          ------------
    private UserMessages createUserMessage(String key, Object... values) {
        UserMessages messages = new UserMessages();
        messages.add("sea", new UserMessage(key, values));
        return messages;
    }

    private List<String> toMessageList(UserMessages messages) {
        Locale locale = Locale.ENGLISH;
        return messageManager.toMessageList(locale, messages);
    }
}
