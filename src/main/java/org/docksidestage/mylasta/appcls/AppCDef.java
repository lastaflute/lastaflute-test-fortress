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
package org.docksidestage.mylasta.appcls;

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
 * The definition of application classification.
 * @author FreeGen
 */
public interface AppCDef extends Classification {

    /**
     * Sea Cls
     */
    public enum AppSea implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ;
        private static final Map<String, AppSea> _codeClsMap = new HashMap<String, AppSea>();
        private static final Map<String, AppSea> _nameClsMap = new HashMap<String, AppSea>();
        static {
            for (AppSea value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppSea(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppSea; }

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
        public static OptionalThing<AppSea> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppSea) { return OptionalThing.of((AppSea)code); }
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
        public static OptionalThing<AppSea> byName(String name) {
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
        public static AppSea codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppSea) { return (AppSea)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppSea nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppSea> listAll() {
            return new ArrayList<AppSea>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppSea> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppSea." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppSea> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppSea> clsList = new ArrayList<AppSea>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppSea> listOfServiceAvailable() {
            return new ArrayList<AppSea>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppSea> listOfShortOfFormalized() {
            return new ArrayList<AppSea>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppSea> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppSea>(4);
        }

        /**
         * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppSea> fromDBCls(CDef.MemberStatus dbCls) {
            String dbCode = dbCls != null ? dbCls.code() : null;
            return OptionalThing.ofNullable(codeOf(dbCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppSea by the DB code: " + dbCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppSea to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * Land Cls
     */
    public enum AppLand implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, AppLand> _codeClsMap = new HashMap<String, AppLand>();
        private static final Map<String, AppLand> _nameClsMap = new HashMap<String, AppLand>();
        static {
            for (AppLand value : values()) {
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
        private AppLand(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppLand; }

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
        public static OptionalThing<AppLand> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppLand) { return OptionalThing.of((AppLand)code); }
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
        public static OptionalThing<AppLand> byName(String name) {
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
        public static AppLand codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppLand) { return (AppLand)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppLand nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppLand> listAll() {
            return new ArrayList<AppLand>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppLand> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: AppLand." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppLand> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppLand> clsList = new ArrayList<AppLand>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppLand> groupOf(String groupName) {
            return new ArrayList<AppLand>(4);
        }

        /**
         * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppLand> fromDBCls(CDef.MemberStatus dbCls) {
            String dbCode = dbCls != null ? dbCls.code() : null;
            return OptionalThing.ofNullable(codeOf(dbCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppLand by the DB code: " + dbCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppLand to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * Piari Cls
     */
    public enum AppPiari implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Dstore: Provisional */
        Dstore("PRV", "Dstore", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, AppPiari> _codeClsMap = new HashMap<String, AppPiari>();
        private static final Map<String, AppPiari> _nameClsMap = new HashMap<String, AppPiari>();
        static {
            for (AppPiari value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppPiari(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppPiari; }

        public boolean inGroup(String groupName) {
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppPiari> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppPiari) { return OptionalThing.of((AppPiari)code); }
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
        public static OptionalThing<AppPiari> byName(String name) {
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
        public static AppPiari codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppPiari) { return (AppPiari)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppPiari nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppPiari> listAll() {
            return new ArrayList<AppPiari>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppPiari> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: AppPiari." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppPiari> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppPiari> clsList = new ArrayList<AppPiari>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppPiari> groupOf(String groupName) {
            return new ArrayList<AppPiari>(4);
        }

        /**
         * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppPiari> fromDBCls(CDef.MemberStatus dbCls) {
            String dbCode = dbCls != null ? dbCls.code() : null;
            return OptionalThing.ofNullable(codeOf(dbCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppPiari by the DB code: " + dbCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppPiari to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * Piari Cls
     */
    public enum AppBonvo implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Dstore: Provisional */
        Dstore("PRV", "Dstore", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, AppBonvo> _codeClsMap = new HashMap<String, AppBonvo>();
        private static final Map<String, AppBonvo> _nameClsMap = new HashMap<String, AppBonvo>();
        static {
            for (AppBonvo value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppBonvo(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppBonvo; }

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
        public static OptionalThing<AppBonvo> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppBonvo) { return OptionalThing.of((AppBonvo)code); }
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
        public static OptionalThing<AppBonvo> byName(String name) {
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
        public static AppBonvo codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppBonvo) { return (AppBonvo)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppBonvo nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppBonvo> listAll() {
            return new ArrayList<AppBonvo>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppBonvo> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppBonvo." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppBonvo> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppBonvo> clsList = new ArrayList<AppBonvo>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * can login <br>
         * The group elements:[OneMan, Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfServiceAvailable() {
            return new ArrayList<AppBonvo>(Arrays.asList(OneMan, Dstore));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppBonvo> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<AppBonvo>(4);
        }

        @Override public String toString() { return code(); }
    }

    public enum DefMeta implements ClassificationMeta {
        /** Sea Cls */
        AppSea
        ,
        /** Land Cls */
        AppLand
        ,
        /** Piari Cls */
        AppPiari
        ,
        /** Piari Cls */
        AppBonvo
        ;
        public String classificationName() {
            return name(); // same as definition name
        }

        public OptionalThing<? extends Classification> of(Object code) {
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.of(code); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.of(code); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.of(code); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.of(code); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public OptionalThing<? extends Classification> byName(String name) {
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.byName(name); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.byName(name); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.byName(name); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.byName(name); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public Classification codeOf(Object code) { // null if not found, old style so use classificationOf(code)
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.codeOf(code); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.codeOf(code); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.codeOf(code); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.codeOf(code); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public Classification nameOf(String name) { // null if not found, old style so use classificationByName(name)
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.valueOf(name); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.valueOf(name); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.valueOf(name); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.valueOf(name); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> listAll() {
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.listAll()); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.listAll()); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.listAll()); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.listAll()); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> listByGroup(String groupName) { // exception if not found
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.listByGroup(groupName)); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.listByGroup(groupName)); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.listByGroup(groupName)); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.listByGroup(groupName)); }
            throw new IllegalStateException("Unknown groupName: " + groupName + ", " + this); // basically unreachable
        }

        public List<Classification> listOf(Collection<String> codeList) {
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.listOf(codeList)); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.listOf(codeList)); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.listOf(codeList)); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.listOf(codeList)); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> groupOf(String groupName) { // old style
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.groupOf(groupName)); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.groupOf(groupName)); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.groupOf(groupName)); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.groupOf(groupName)); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        @SuppressWarnings("unchecked")
        private List<Classification> toClsList(List<?> clsList) {
            return (List<Classification>)clsList;
        }

        public ClassificationCodeType codeType() {
            if (AppSea.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppLand.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppPiari.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppBonvo.name().equals(name())) { return ClassificationCodeType.String; }
            return ClassificationCodeType.String; // as default
        }

        public ClassificationUndefinedHandlingType undefinedHandlingType() {
            if (AppSea.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppLand.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppPiari.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppBonvo.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            return ClassificationUndefinedHandlingType.LOGGING; // as default
        }

        public static OptionalThing<AppCDef.DefMeta> find(String classificationName) { // instead of valueOf()
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            if (AppSea.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppSea); }
            if (AppLand.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppLand); }
            if (AppPiari.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppPiari); }
            if (AppBonvo.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppBonvo); }
            return OptionalThing.ofNullable(null, () -> {
                throw new ClassificationNotFoundException("Unknown classification: " + classificationName);
            });
        }

        public static AppCDef.DefMeta meta(String classificationName) { // old style so use find(name)
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            if (AppSea.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppSea; }
            if (AppLand.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppLand; }
            if (AppPiari.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppPiari; }
            if (AppBonvo.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppBonvo; }
            throw new IllegalStateException("Unknown classification: " + classificationName);
        }

        @SuppressWarnings("unused")
        private String[] xinternalEmptyString() {
            return emptyStrings(); // to suppress 'unused' warning of import statement
        }
    }
}
