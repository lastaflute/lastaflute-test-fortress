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
package org.docksidestage.mylasta.direction.sponsor.planner.memorable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.dbflute.util.Srl;
import org.lastaflute.core.message.UserMessages;
import org.lastaflute.web.exception.Forced404NotFoundException;

/**
 * @author jflute
 */
public class MemorableRestlikeRouter {

    protected static final Pattern LMLIKE_ENTRY_PATTERN = Pattern.compile("^/wx/routing/restlike/lmlike/[a-zA-Z0-9]+/[0-9]+/?$");
    protected static final Pattern LMLIKE_INTERNAL_PATTERN =
            Pattern.compile("^/wx/routing/restlike/lmlike/category/[a-zA-Z0-9]+/[0-9]+/?$");

    protected final List<RestlikeResource> resourceList;

    public MemorableRestlikeRouter() {
        this.resourceList = prepareRestlikeResourceList();
    }

    protected List<RestlikeResource> prepareRestlikeResourceList() {
        List<RestlikeResource> workingList = new ArrayList<RestlikeResource>();
        workingList.add(new RestlikeResource(LMLIKE_ENTRY_PATTERN, LMLIKE_INTERNAL_PATTERN, "lmlike", "category"));
        return Collections.unmodifiableList(workingList);
    }

    public String makeRestlike(String requestPath) {
        for (RestlikeResource resource : resourceList) {
            Pattern entryPattern = resource.getEntryPattern();
            Pattern internalPattern = resource.getInternalPattern();
            String baseWord = resource.getBaseWord();
            String internalWord = resource.getInternalWord();
            String restlike = doMakeRestlike(requestPath, entryPattern, internalPattern, baseWord, internalWord);
            if (restlike != null) {
                return restlike;
            }
        }
        return null;
    }

    protected String doMakeRestlike(String requestPath, Pattern entryPattern, Pattern internalPattern, String baseWord,
            String internalWord) {
        if (entryPattern.matcher(requestPath).matches()) {
            return Srl.replace(requestPath, baseWord + "/", baseWord + "/" + internalWord + "/");
        } else if (internalPattern.matcher(requestPath).matches()) {
            handleInternalRequestPathDirectAccess(requestPath);
        }
        return null;
    }

    protected void handleInternalRequestPathDirectAccess(String requestPath) {
        throw new Forced404NotFoundException("Cannot access to internal path directly: " + requestPath, UserMessages.empty());
    }

    public static class RestlikeResource {

        protected Pattern entryPattern;
        protected Pattern internalPattern;
        protected String baseWord;
        protected String internalWord;

        public RestlikeResource(Pattern entryPattern, Pattern internalPattern, String baseWord, String internalWord) {
            this.entryPattern = entryPattern;
            this.internalPattern = internalPattern;
            this.baseWord = baseWord;
            this.internalWord = internalWord;
        }

        public Pattern getEntryPattern() {
            return entryPattern;
        }

        public Pattern getInternalPattern() {
            return internalPattern;
        }

        public String getBaseWord() {
            return baseWord;
        }

        public String getInternalWord() {
            return internalWord;
        }
    }
}