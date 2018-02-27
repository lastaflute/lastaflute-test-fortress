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
package org.docksidestage.mylasta.webcls;

import java.util.*;

import org.dbflute.exception.ClassificationNotFoundException;
import org.dbflute.jdbc.Classification;
import org.dbflute.jdbc.ClassificationCodeType;
import org.dbflute.jdbc.ClassificationMeta;
import org.dbflute.jdbc.ClassificationUndefinedHandlingType;
import org.dbflute.optional.OptionalThing;
import static org.dbflute.util.DfTypeUtil.emptyStrings;
import org.docksidestage.dbflute.allcommon.*;

/**
 * The definition of web classification.
 * @author FreeGen
 */
public interface WebCDef extends Classification {

    /**
     * Sea Cls
     */
    public enum WebSea implements WebCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ;
        private static final Map<String, WebSea> _codeClsMap = new HashMap<String, WebSea>();
        private static final Map<String, WebSea> _nameClsMap = new HashMap<String, WebSea>();
        static {
            for (WebSea value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private WebSea(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return WebCDef.DefMeta.WebSea; }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return Formalized.equals(this) || Provisional.equals(this);
        }

        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() {
            return Provisional.equals(this);
        }

        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return isShortOfFormalized(); }
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebSea> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof WebSea) { return OptionalThing.of((WebSea)code); }
            if (code instanceof OptionalThing<?>) { return of(((OptionalThing<?>)code).orElse(null)); }
            return OptionalThing.ofNullable(_codeClsMap.get(code.toString().toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification code: " + code);
            });
        }

        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebSea> byName(String name) {
            if (name == null) { throw new IllegalArgumentException("The argument 'name' should not be null."); }
            return OptionalThing.ofNullable(_nameClsMap.get(name.toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification name: " + name);
            });
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span> <br>
         * Get the classification by the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         * @deprecated use of()
         */
        public static WebSea codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof WebSea) { return (WebSea)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static WebSea nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<WebSea> listAll() {
            return new ArrayList<WebSea>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<WebSea> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: WebSea." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<WebSea> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<WebSea> clsList = new ArrayList<WebSea>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<WebSea> listOfServiceAvailable() {
            return new ArrayList<WebSea>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<WebSea> listOfShortOfFormalized() {
            return new ArrayList<WebSea>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<WebSea> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<WebSea>(4);
        }

        /**
         * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<WebSea> fromDBCls(CDef.MemberStatus dbCls) {
            String dbCode = dbCls != null ? dbCls.code() : null;
            return OptionalThing.ofNullable(codeOf(dbCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to WebSea by the DB code: " + dbCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert WebSea to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * Land Cls
     */
    public enum WebLand implements WebCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, WebLand> _codeClsMap = new HashMap<String, WebLand>();
        private static final Map<String, WebLand> _nameClsMap = new HashMap<String, WebLand>();
        static {
            for (WebLand value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private static final Map<String, Map<String, Object>> _subItemMapMap = new HashMap<String, Map<String, Object>>();
        static {
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("keyword", "shining");
                _subItemMapMap.put(OneMan.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("keyword", "party");
                _subItemMapMap.put(MiniO.code(), Collections.unmodifiableMap(subItemMap));
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private WebLand(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return WebCDef.DefMeta.WebLand; }

        public String keyword() {
            return (String)subItemMap().get("keyword");
        }

        public boolean inGroup(String groupName) {
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebLand> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof WebLand) { return OptionalThing.of((WebLand)code); }
            if (code instanceof OptionalThing<?>) { return of(((OptionalThing<?>)code).orElse(null)); }
            return OptionalThing.ofNullable(_codeClsMap.get(code.toString().toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification code: " + code);
            });
        }

        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebLand> byName(String name) {
            if (name == null) { throw new IllegalArgumentException("The argument 'name' should not be null."); }
            return OptionalThing.ofNullable(_nameClsMap.get(name.toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification name: " + name);
            });
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span> <br>
         * Get the classification by the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         * @deprecated use of()
         */
        public static WebLand codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof WebLand) { return (WebLand)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static WebLand nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<WebLand> listAll() {
            return new ArrayList<WebLand>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<WebLand> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: WebLand." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<WebLand> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<WebLand> clsList = new ArrayList<WebLand>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<WebLand> groupOf(String groupName) {
            return new ArrayList<WebLand>(4);
        }

        /**
         * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<WebLand> fromDBCls(CDef.MemberStatus dbCls) {
            String dbCode = dbCls != null ? dbCls.code() : null;
            return OptionalThing.ofNullable(codeOf(dbCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to WebLand by the DB code: " + dbCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert WebLand to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * Piari Cls
     */
    public enum WebPiari implements WebCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Dstore: Provisional */
        Dstore("PRV", "Dstore", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, WebPiari> _codeClsMap = new HashMap<String, WebPiari>();
        private static final Map<String, WebPiari> _nameClsMap = new HashMap<String, WebPiari>();
        static {
            for (WebPiari value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private WebPiari(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return WebCDef.DefMeta.WebPiari; }

        public boolean inGroup(String groupName) {
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebPiari> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof WebPiari) { return OptionalThing.of((WebPiari)code); }
            if (code instanceof OptionalThing<?>) { return of(((OptionalThing<?>)code).orElse(null)); }
            return OptionalThing.ofNullable(_codeClsMap.get(code.toString().toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification code: " + code);
            });
        }

        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebPiari> byName(String name) {
            if (name == null) { throw new IllegalArgumentException("The argument 'name' should not be null."); }
            return OptionalThing.ofNullable(_nameClsMap.get(name.toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification name: " + name);
            });
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span> <br>
         * Get the classification by the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         * @deprecated use of()
         */
        public static WebPiari codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof WebPiari) { return (WebPiari)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static WebPiari nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<WebPiari> listAll() {
            return new ArrayList<WebPiari>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<WebPiari> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: WebPiari." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<WebPiari> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<WebPiari> clsList = new ArrayList<WebPiari>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<WebPiari> groupOf(String groupName) {
            return new ArrayList<WebPiari>(4);
        }

        /**
         * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<WebPiari> fromDBCls(CDef.MemberStatus dbCls) {
            String dbCode = dbCls != null ? dbCls.code() : null;
            return OptionalThing.ofNullable(codeOf(dbCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to WebPiari by the DB code: " + dbCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert WebPiari to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * Piari Cls
     */
    public enum WebBonvo implements WebCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Dstore: Provisional */
        Dstore("PRV", "Dstore", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, WebBonvo> _codeClsMap = new HashMap<String, WebBonvo>();
        private static final Map<String, WebBonvo> _nameClsMap = new HashMap<String, WebBonvo>();
        static {
            for (WebBonvo value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private WebBonvo(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return WebCDef.DefMeta.WebBonvo; }

        /**
         * Is the classification in the group? <br>
         * can login <br>
         * The group elements:[OneMan, Dstore]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this) || Dstore.equals(this);
        }

        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return isServiceAvailable(); }
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebBonvo> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof WebBonvo) { return OptionalThing.of((WebBonvo)code); }
            if (code instanceof OptionalThing<?>) { return of(((OptionalThing<?>)code).orElse(null)); }
            return OptionalThing.ofNullable(_codeClsMap.get(code.toString().toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification code: " + code);
            });
        }

        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<WebBonvo> byName(String name) {
            if (name == null) { throw new IllegalArgumentException("The argument 'name' should not be null."); }
            return OptionalThing.ofNullable(_nameClsMap.get(name.toLowerCase()), () ->{
                throw new ClassificationNotFoundException("Unknown classification name: " + name);
            });
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span> <br>
         * Get the classification by the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         * @deprecated use of()
         */
        public static WebBonvo codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof WebBonvo) { return (WebBonvo)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static WebBonvo nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<WebBonvo> listAll() {
            return new ArrayList<WebBonvo>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<WebBonvo> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: WebBonvo." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<WebBonvo> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<WebBonvo> clsList = new ArrayList<WebBonvo>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * can login <br>
         * The group elements:[OneMan, Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<WebBonvo> listOfServiceAvailable() {
            return new ArrayList<WebBonvo>(Arrays.asList(OneMan, Dstore));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<WebBonvo> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<WebBonvo>(4);
        }

        @Override public String toString() { return code(); }
    }

    public enum DefMeta implements ClassificationMeta {
        /** Sea Cls */
        WebSea
        ,
        /** Land Cls */
        WebLand
        ,
        /** Piari Cls */
        WebPiari
        ,
        /** Piari Cls */
        WebBonvo
        ;
        public String classificationName() {
            return name(); // same as definition name
        }

        public OptionalThing<? extends Classification> of(Object code) {
            if (WebSea.name().equals(name())) { return WebCDef.WebSea.of(code); }
            if (WebLand.name().equals(name())) { return WebCDef.WebLand.of(code); }
            if (WebPiari.name().equals(name())) { return WebCDef.WebPiari.of(code); }
            if (WebBonvo.name().equals(name())) { return WebCDef.WebBonvo.of(code); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public OptionalThing<? extends Classification> byName(String name) {
            if (WebSea.name().equals(name())) { return WebCDef.WebSea.byName(name); }
            if (WebLand.name().equals(name())) { return WebCDef.WebLand.byName(name); }
            if (WebPiari.name().equals(name())) { return WebCDef.WebPiari.byName(name); }
            if (WebBonvo.name().equals(name())) { return WebCDef.WebBonvo.byName(name); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public Classification codeOf(Object code) { // null if not found, old style so use classificationOf(code)
            if (WebSea.name().equals(name())) { return WebCDef.WebSea.codeOf(code); }
            if (WebLand.name().equals(name())) { return WebCDef.WebLand.codeOf(code); }
            if (WebPiari.name().equals(name())) { return WebCDef.WebPiari.codeOf(code); }
            if (WebBonvo.name().equals(name())) { return WebCDef.WebBonvo.codeOf(code); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public Classification nameOf(String name) { // null if not found, old style so use classificationByName(name)
            if (WebSea.name().equals(name())) { return WebCDef.WebSea.valueOf(name); }
            if (WebLand.name().equals(name())) { return WebCDef.WebLand.valueOf(name); }
            if (WebPiari.name().equals(name())) { return WebCDef.WebPiari.valueOf(name); }
            if (WebBonvo.name().equals(name())) { return WebCDef.WebBonvo.valueOf(name); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> listAll() {
            if (WebSea.name().equals(name())) { return toClsList(WebCDef.WebSea.listAll()); }
            if (WebLand.name().equals(name())) { return toClsList(WebCDef.WebLand.listAll()); }
            if (WebPiari.name().equals(name())) { return toClsList(WebCDef.WebPiari.listAll()); }
            if (WebBonvo.name().equals(name())) { return toClsList(WebCDef.WebBonvo.listAll()); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> listByGroup(String groupName) { // exception if not found
            if (WebSea.name().equals(name())) { return toClsList(WebCDef.WebSea.listByGroup(groupName)); }
            if (WebLand.name().equals(name())) { return toClsList(WebCDef.WebLand.listByGroup(groupName)); }
            if (WebPiari.name().equals(name())) { return toClsList(WebCDef.WebPiari.listByGroup(groupName)); }
            if (WebBonvo.name().equals(name())) { return toClsList(WebCDef.WebBonvo.listByGroup(groupName)); }
            throw new IllegalStateException("Unknown groupName: " + groupName + ", " + this); // basically unreachable
        }

        public List<Classification> listOf(Collection<String> codeList) {
            if (WebSea.name().equals(name())) { return toClsList(WebCDef.WebSea.listOf(codeList)); }
            if (WebLand.name().equals(name())) { return toClsList(WebCDef.WebLand.listOf(codeList)); }
            if (WebPiari.name().equals(name())) { return toClsList(WebCDef.WebPiari.listOf(codeList)); }
            if (WebBonvo.name().equals(name())) { return toClsList(WebCDef.WebBonvo.listOf(codeList)); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> groupOf(String groupName) { // old style
            if (WebSea.name().equals(name())) { return toClsList(WebCDef.WebSea.groupOf(groupName)); }
            if (WebLand.name().equals(name())) { return toClsList(WebCDef.WebLand.groupOf(groupName)); }
            if (WebPiari.name().equals(name())) { return toClsList(WebCDef.WebPiari.groupOf(groupName)); }
            if (WebBonvo.name().equals(name())) { return toClsList(WebCDef.WebBonvo.groupOf(groupName)); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        @SuppressWarnings("unchecked")
        private List<Classification> toClsList(List<?> clsList) {
            return (List<Classification>)clsList;
        }

        public ClassificationCodeType codeType() {
            if (WebSea.name().equals(name())) { return ClassificationCodeType.String; }
            if (WebLand.name().equals(name())) { return ClassificationCodeType.String; }
            if (WebPiari.name().equals(name())) { return ClassificationCodeType.String; }
            if (WebBonvo.name().equals(name())) { return ClassificationCodeType.String; }
            return ClassificationCodeType.String; // as default
        }

        public ClassificationUndefinedHandlingType undefinedHandlingType() {
            if (WebSea.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (WebLand.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (WebPiari.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (WebBonvo.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            return ClassificationUndefinedHandlingType.LOGGING; // as default
        }

        public static OptionalThing<WebCDef.DefMeta> find(String classificationName) { // instead of valueOf()
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            if (WebSea.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(WebCDef.DefMeta.WebSea); }
            if (WebLand.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(WebCDef.DefMeta.WebLand); }
            if (WebPiari.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(WebCDef.DefMeta.WebPiari); }
            if (WebBonvo.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(WebCDef.DefMeta.WebBonvo); }
            return OptionalThing.ofNullable(null, () -> {
                throw new ClassificationNotFoundException("Unknown classification: " + classificationName);
            });
        }

        public static WebCDef.DefMeta meta(String classificationName) { // old style so use find(name)
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            if (WebSea.name().equalsIgnoreCase(classificationName)) { return WebCDef.DefMeta.WebSea; }
            if (WebLand.name().equalsIgnoreCase(classificationName)) { return WebCDef.DefMeta.WebLand; }
            if (WebPiari.name().equalsIgnoreCase(classificationName)) { return WebCDef.DefMeta.WebPiari; }
            if (WebBonvo.name().equalsIgnoreCase(classificationName)) { return WebCDef.DefMeta.WebBonvo; }
            throw new IllegalStateException("Unknown classification: " + classificationName);
        }

        @SuppressWarnings("unused")
        private String[] xinternalEmptyString() {
            return emptyStrings(); // to suppress 'unused' warning of import statement
        }
    }
}
