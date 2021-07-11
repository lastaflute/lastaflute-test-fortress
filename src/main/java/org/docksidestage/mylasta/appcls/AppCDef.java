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
import org.docksidestage.dbflute.allcommon.CDef;
import org.docksidestage.mylasta.namedcls.LeonardoCDef;

/**
 * The definition of application classification.
 * @author FreeGen
 */
public interface AppCDef extends Classification {

    /**
     * test of no theme refCls, expects DB cls
     */
    public enum AppMaihama implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ;
        private static final Map<String, AppMaihama> _codeClsMap = new HashMap<String, AppMaihama>();
        private static final Map<String, AppMaihama> _nameClsMap = new HashMap<String, AppMaihama>();
        static {
            for (AppMaihama value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppMaihama(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppMaihama; }

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
        public static OptionalThing<AppMaihama> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppMaihama) { return OptionalThing.of((AppMaihama)code); }
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
        public static OptionalThing<AppMaihama> byName(String name) {
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
         */
        public static AppMaihama codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppMaihama) { return (AppMaihama)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppMaihama nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppMaihama> listAll() {
            return new ArrayList<AppMaihama>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppMaihama> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppMaihama." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppMaihama> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppMaihama> clsList = new ArrayList<AppMaihama>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMaihama> listOfServiceAvailable() {
            return new ArrayList<AppMaihama>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMaihama> listOfShortOfFormalized() {
            return new ArrayList<AppMaihama>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppMaihama> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppMaihama>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppMaihama> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppMaihama by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppMaihama to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of included, expects grouping, sub-item, sisters
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
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppSea> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppSea by the referred code: " + refCode);
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
     * test of exists, expects no grouping, no sub-item, sisters
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
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppLand(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppLand; }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this);
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
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
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
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppLand> listOfServiceAvailable() {
            return new ArrayList<AppLand>(Arrays.asList(OneMan));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppLand> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<AppLand>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppLand> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppLand by the referred code: " + refCode);
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
     * test of matches, expects no grouping, no sub-item, sisters
     */
    public enum AppPiari implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Castle: Provisional */
        Parade("PRV", "Castle", emptyStrings())
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

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Parade]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this) || Parade.equals(this);
        }

        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Parade]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() {
            return Parade.equals(this);
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
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
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
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppPiari> listOfServiceAvailable() {
            return new ArrayList<AppPiari>(Arrays.asList(OneMan, Parade));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppPiari> listOfShortOfFormalized() {
            return new ArrayList<AppPiari>(Arrays.asList(Parade));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppPiari> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppPiari>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppPiari> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppPiari by the referred code: " + refCode);
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
     * test of manual grouping map using refCls as included, expects overridden, added
     */
    public enum AppBonvo implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ,
        /** Hangar: Rhythms */
        Mystic("MYS", "Hangar", emptyStrings())
        ;
        private static final Map<String, AppBonvo> _codeClsMap = new HashMap<String, AppBonvo>();
        private static final Map<String, AppBonvo> _nameClsMap = new HashMap<String, AppBonvo>();
        static {
            for (AppBonvo value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private static final Map<String, Map<String, Object>> _subItemMapMap = new HashMap<String, Map<String, Object>>();
        static {
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(Formalized.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(Withdrawal.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(Provisional.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "88");
                _subItemMapMap.put(Mystic.code(), Collections.unmodifiableMap(subItemMap));
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppBonvo(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppBonvo; }

        /**
         * Is the classification in the group? <br>
         * new group name by literal <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The determination, true or false.
         */
        public boolean isAppNewLiteralAvailable() {
            return Formalized.equals(this) || Provisional.equals(this) || Mystic.equals(this);
        }

        /**
         * Is the classification in the group? <br>
         * new group name referring existing group <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The determination, true or false.
         */
        public boolean isAppNewRefExistingGroupAvailable() {
            return Formalized.equals(this) || Provisional.equals(this) || Mystic.equals(this);
        }

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
            if ("appNewLiteralAvailable".equals(groupName)) { return isAppNewLiteralAvailable(); }
            if ("appNewRefExistingGroupAvailable".equals(groupName)) { return isAppNewRefExistingGroupAvailable(); }
            if ("serviceAvailable".equals(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return isShortOfFormalized(); }
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
            if ("appNewLiteralAvailable".equalsIgnoreCase(groupName)) { return listOfAppNewLiteralAvailable(); }
            if ("appNewRefExistingGroupAvailable".equalsIgnoreCase(groupName)) { return listOfAppNewRefExistingGroupAvailable(); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
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
         * new group name by literal <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfAppNewLiteralAvailable() {
            return new ArrayList<AppBonvo>(Arrays.asList(Formalized, Provisional, Mystic));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * new group name referring existing group <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfAppNewRefExistingGroupAvailable() {
            return new ArrayList<AppBonvo>(Arrays.asList(Formalized, Provisional, Mystic));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfServiceAvailable() {
            return new ArrayList<AppBonvo>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfShortOfFormalized() {
            return new ArrayList<AppBonvo>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppBonvo> groupOf(String groupName) {
            if ("appNewLiteralAvailable".equals(groupName)) { return listOfAppNewLiteralAvailable(); }
            if ("appNewRefExistingGroupAvailable".equals(groupName)) { return listOfAppNewRefExistingGroupAvailable(); }
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppBonvo>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppBonvo> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppBonvo by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppBonvo to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of manual grouping map using refCls as exists, expects new grouping is available
     */
    public enum AppDstore implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, AppDstore> _codeClsMap = new HashMap<String, AppDstore>();
        private static final Map<String, AppDstore> _nameClsMap = new HashMap<String, AppDstore>();
        static {
            for (AppDstore value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppDstore(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppDstore; }

        /**
         * Is the classification in the group? <br>
         * new group name as app classfication <br>
         * The group elements:[OneMan, MiniO]
         * @return The determination, true or false.
         */
        public boolean isAppNewAvailable() {
            return OneMan.equals(this) || MiniO.equals(this);
        }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this);
        }

        public boolean inGroup(String groupName) {
            if ("appNewAvailable".equals(groupName)) { return isAppNewAvailable(); }
            if ("serviceAvailable".equals(groupName)) { return isServiceAvailable(); }
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppDstore> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppDstore) { return OptionalThing.of((AppDstore)code); }
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
        public static OptionalThing<AppDstore> byName(String name) {
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
         */
        public static AppDstore codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppDstore) { return (AppDstore)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppDstore nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppDstore> listAll() {
            return new ArrayList<AppDstore>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppDstore> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("appNewAvailable".equalsIgnoreCase(groupName)) { return listOfAppNewAvailable(); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppDstore." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppDstore> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppDstore> clsList = new ArrayList<AppDstore>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * new group name as app classfication <br>
         * The group elements:[OneMan, MiniO]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDstore> listOfAppNewAvailable() {
            return new ArrayList<AppDstore>(Arrays.asList(OneMan, MiniO));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDstore> listOfServiceAvailable() {
            return new ArrayList<AppDstore>(Arrays.asList(OneMan));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppDstore> groupOf(String groupName) {
            if ("appNewAvailable".equals(groupName)) { return listOfAppNewAvailable(); }
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<AppDstore>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppDstore> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppDstore by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppDstore to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of manual sub-item and sisters as included, expects merged, only order() exists
     */
    public enum AppAmba implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ,
        /** Hangar: Rhythms */
        Mystic("MYS", "Hangar", new String[] {"Choucho"})
        ;
        private static final Map<String, AppAmba> _codeClsMap = new HashMap<String, AppAmba>();
        private static final Map<String, AppAmba> _nameClsMap = new HashMap<String, AppAmba>();
        static {
            for (AppAmba value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private static final Map<String, Map<String, Object>> _subItemMapMap = new HashMap<String, Map<String, Object>>();
        static {
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(Formalized.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(Withdrawal.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(Provisional.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "1");
                subItemMap.put("newKeyword", "shining");
                _subItemMapMap.put(Mystic.code(), Collections.unmodifiableMap(subItemMap));
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppAmba(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppAmba; }

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
        public static OptionalThing<AppAmba> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppAmba) { return OptionalThing.of((AppAmba)code); }
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
        public static OptionalThing<AppAmba> byName(String name) {
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
         */
        public static AppAmba codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppAmba) { return (AppAmba)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppAmba nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppAmba> listAll() {
            return new ArrayList<AppAmba>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppAmba> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppAmba." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppAmba> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppAmba> clsList = new ArrayList<AppAmba>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmba> listOfServiceAvailable() {
            return new ArrayList<AppAmba>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmba> listOfShortOfFormalized() {
            return new ArrayList<AppAmba>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppAmba> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppAmba>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppAmba> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppAmba by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppAmba to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of manual sub-item and sisters as exists, expects new only here
     */
    public enum AppMiraco implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", new String[] {"ONE"})
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", new String[] {"MIN"})
        ;
        private static final Map<String, AppMiraco> _codeClsMap = new HashMap<String, AppMiraco>();
        private static final Map<String, AppMiraco> _nameClsMap = new HashMap<String, AppMiraco>();
        static {
            for (AppMiraco value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private static final Map<String, Map<String, Object>> _subItemMapMap = new HashMap<String, Map<String, Object>>();
        static {
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "1");
                subItemMap.put("newKeyword", "shining");
                _subItemMapMap.put(OneMan.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "8");
                subItemMap.put("newKeyword", "party");
                _subItemMapMap.put(MiniO.code(), Collections.unmodifiableMap(subItemMap));
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppMiraco(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppMiraco; }

        public String order() {
            return (String)subItemMap().get("order");
        }

        public String newKeyword() {
            return (String)subItemMap().get("newKeyword");
        }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this);
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
        public static OptionalThing<AppMiraco> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppMiraco) { return OptionalThing.of((AppMiraco)code); }
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
        public static OptionalThing<AppMiraco> byName(String name) {
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
         */
        public static AppMiraco codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppMiraco) { return (AppMiraco)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppMiraco nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppMiraco> listAll() {
            return new ArrayList<AppMiraco>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppMiraco> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppMiraco." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppMiraco> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppMiraco> clsList = new ArrayList<AppMiraco>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMiraco> listOfServiceAvailable() {
            return new ArrayList<AppMiraco>(Arrays.asList(OneMan));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppMiraco> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<AppMiraco>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppMiraco> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppMiraco by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppMiraco to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of referring group, expects grouped elements only and sub-item, sisters exist
     */
    public enum AppDohotel implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ;
        private static final Map<String, AppDohotel> _codeClsMap = new HashMap<String, AppDohotel>();
        private static final Map<String, AppDohotel> _nameClsMap = new HashMap<String, AppDohotel>();
        static {
            for (AppDohotel value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppDohotel(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppDohotel; }

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
        public static OptionalThing<AppDohotel> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppDohotel) { return OptionalThing.of((AppDohotel)code); }
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
        public static OptionalThing<AppDohotel> byName(String name) {
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
         */
        public static AppDohotel codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppDohotel) { return (AppDohotel)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppDohotel nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppDohotel> listAll() {
            return new ArrayList<AppDohotel>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppDohotel> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppDohotel." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppDohotel> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppDohotel> clsList = new ArrayList<AppDohotel>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDohotel> listOfServiceAvailable() {
            return new ArrayList<AppDohotel>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDohotel> listOfShortOfFormalized() {
            return new ArrayList<AppDohotel>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppDohotel> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppDohotel>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppDohotel> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppDohotel by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppDohotel to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of included with overriding, expected merged
     */
    public enum AppAmphi implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        OneMan("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Castle: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Castle", new String[] {"Route"})
        ;
        private static final Map<String, AppAmphi> _codeClsMap = new HashMap<String, AppAmphi>();
        private static final Map<String, AppAmphi> _nameClsMap = new HashMap<String, AppAmphi>();
        static {
            for (AppAmphi value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private static final Map<String, Map<String, Object>> _subItemMapMap = new HashMap<String, Map<String, Object>>();
        static {
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(OneMan.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(Withdrawal.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "9");
                _subItemMapMap.put(Provisional.code(), Collections.unmodifiableMap(subItemMap));
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppAmphi(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppAmphi; }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this) || Provisional.equals(this);
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
        public static OptionalThing<AppAmphi> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppAmphi) { return OptionalThing.of((AppAmphi)code); }
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
        public static OptionalThing<AppAmphi> byName(String name) {
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
         */
        public static AppAmphi codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppAmphi) { return (AppAmphi)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppAmphi nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppAmphi> listAll() {
            return new ArrayList<AppAmphi>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppAmphi> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppAmphi." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppAmphi> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppAmphi> clsList = new ArrayList<AppAmphi>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmphi> listOfServiceAvailable() {
            return new ArrayList<AppAmphi>(Arrays.asList(OneMan, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmphi> listOfShortOfFormalized() {
            return new ArrayList<AppAmphi>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppAmphi> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppAmphi>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppAmphi> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppAmphi by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppAmphi to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of reference to namedcls, case1
     */
    public enum AppDockside implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ;
        private static final Map<String, AppDockside> _codeClsMap = new HashMap<String, AppDockside>();
        private static final Map<String, AppDockside> _nameClsMap = new HashMap<String, AppDockside>();
        static {
            for (AppDockside value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppDockside(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppDockside; }

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
        public static OptionalThing<AppDockside> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppDockside) { return OptionalThing.of((AppDockside)code); }
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
        public static OptionalThing<AppDockside> byName(String name) {
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
         */
        public static AppDockside codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppDockside) { return (AppDockside)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppDockside nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppDockside> listAll() {
            return new ArrayList<AppDockside>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppDockside> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppDockside." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppDockside> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppDockside> clsList = new ArrayList<AppDockside>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDockside> listOfServiceAvailable() {
            return new ArrayList<AppDockside>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDockside> listOfShortOfFormalized() {
            return new ArrayList<AppDockside>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppDockside> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppDockside>(4);
        }

        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppDockside> fromRefCls(LeonardoCDef.DaSea refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaSea to AppDockside by the referred code: " + refCode);
            });
        }

        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaSea> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaSea.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppDockside to DaSea by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of reference to namedcls, case2
     */
    public enum AppHangar implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, AppHangar> _codeClsMap = new HashMap<String, AppHangar>();
        private static final Map<String, AppHangar> _nameClsMap = new HashMap<String, AppHangar>();
        static {
            for (AppHangar value : values()) {
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
        private AppHangar(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppHangar; }

        public String keyword() {
            return (String)subItemMap().get("keyword");
        }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this);
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
        public static OptionalThing<AppHangar> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppHangar) { return OptionalThing.of((AppHangar)code); }
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
        public static OptionalThing<AppHangar> byName(String name) {
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
         */
        public static AppHangar codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppHangar) { return (AppHangar)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppHangar nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppHangar> listAll() {
            return new ArrayList<AppHangar>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppHangar> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppHangar." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppHangar> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppHangar> clsList = new ArrayList<AppHangar>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppHangar> listOfServiceAvailable() {
            return new ArrayList<AppHangar>(Arrays.asList(OneMan));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppHangar> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<AppHangar>(4);
        }

        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppHangar> fromRefCls(LeonardoCDef.DaLand refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaLand to AppHangar by the referred code: " + refCode);
            });
        }

        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaLand> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaLand.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppHangar to DaLand by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of reference to namedcls, exists
     */
    public enum AppMagiclamp implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, AppMagiclamp> _codeClsMap = new HashMap<String, AppMagiclamp>();
        private static final Map<String, AppMagiclamp> _nameClsMap = new HashMap<String, AppMagiclamp>();
        static {
            for (AppMagiclamp value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppMagiclamp(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppMagiclamp; }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this);
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
        public static OptionalThing<AppMagiclamp> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppMagiclamp) { return OptionalThing.of((AppMagiclamp)code); }
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
        public static OptionalThing<AppMagiclamp> byName(String name) {
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
         */
        public static AppMagiclamp codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppMagiclamp) { return (AppMagiclamp)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppMagiclamp nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppMagiclamp> listAll() {
            return new ArrayList<AppMagiclamp>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppMagiclamp> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppMagiclamp." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppMagiclamp> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppMagiclamp> clsList = new ArrayList<AppMagiclamp>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMagiclamp> listOfServiceAvailable() {
            return new ArrayList<AppMagiclamp>(Arrays.asList(OneMan));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppMagiclamp> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<AppMagiclamp>(4);
        }

        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppMagiclamp> fromRefCls(LeonardoCDef.DaSea refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaSea to AppMagiclamp by the referred code: " + refCode);
            });
        }

        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaSea> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaSea.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppMagiclamp to DaSea by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of reference to namedcls, matches
     */
    public enum AppBroadway implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Dstore: Provisional */
        Dstore("PRV", "Dstore", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, AppBroadway> _codeClsMap = new HashMap<String, AppBroadway>();
        private static final Map<String, AppBroadway> _nameClsMap = new HashMap<String, AppBroadway>();
        static {
            for (AppBroadway value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppBroadway(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppBroadway; }

        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Dstore]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this) || Dstore.equals(this);
        }

        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Dstore]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() {
            return Dstore.equals(this);
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
        public static OptionalThing<AppBroadway> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppBroadway) { return OptionalThing.of((AppBroadway)code); }
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
        public static OptionalThing<AppBroadway> byName(String name) {
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
         */
        public static AppBroadway codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppBroadway) { return (AppBroadway)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppBroadway nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppBroadway> listAll() {
            return new ArrayList<AppBroadway>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppBroadway> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppBroadway." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppBroadway> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppBroadway> clsList = new ArrayList<AppBroadway>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBroadway> listOfServiceAvailable() {
            return new ArrayList<AppBroadway>(Arrays.asList(OneMan, Dstore));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBroadway> listOfShortOfFormalized() {
            return new ArrayList<AppBroadway>(Arrays.asList(Dstore));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppBroadway> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<AppBroadway>(4);
        }

        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppBroadway> fromRefCls(LeonardoCDef.DaPiari refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaPiari to AppBroadway by the referred code: " + refCode);
            });
        }

        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaPiari> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaPiari.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppBroadway to DaPiari by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of including sub-item and sisters when implicit classification
     */
    public enum AppFlg implements AppCDef {
        /** Checked: means yes */
        True("1", "Checked", new String[] {"true"})
        ,
        /** Unchecked: means no */
        False("0", "Unchecked", new String[] {"false"})
        ;
        private static final Map<String, AppFlg> _codeClsMap = new HashMap<String, AppFlg>();
        private static final Map<String, AppFlg> _nameClsMap = new HashMap<String, AppFlg>();
        static {
            for (AppFlg value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppFlg(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppFlg; }

        public boolean inGroup(String groupName) {
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppFlg> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppFlg) { return OptionalThing.of((AppFlg)code); }
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
        public static OptionalThing<AppFlg> byName(String name) {
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
         */
        public static AppFlg codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppFlg) { return (AppFlg)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppFlg nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppFlg> listAll() {
            return new ArrayList<AppFlg>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppFlg> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: AppFlg." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppFlg> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppFlg> clsList = new ArrayList<AppFlg>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppFlg> groupOf(String groupName) {
            return new ArrayList<AppFlg>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppFlg> fromDBCls(CDef.Flg refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.Flg to AppFlg by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.Flg> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.Flg.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppFlg to Flg by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of including grouping map when implicit classification
     */
    public enum AppPaymentMethod implements AppCDef {
        /** by hand: payment by hand, face-to-face */
        ByHand("HAN", "by hand", emptyStrings())
        ,
        /** bank transfer: bank transfer payment */
        BankTransfer("BAK", "bank transfer", emptyStrings())
        ,
        /** credit card: credit card payment */
        CreditCard("CRC", "credit card", emptyStrings())
        ;
        private static final Map<String, AppPaymentMethod> _codeClsMap = new HashMap<String, AppPaymentMethod>();
        private static final Map<String, AppPaymentMethod> _nameClsMap = new HashMap<String, AppPaymentMethod>();
        static {
            for (AppPaymentMethod value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppPaymentMethod(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppPaymentMethod; }

        /**
         * Is the classification in the group? <br>
         * the most recommended method <br>
         * The group elements:[ByHand]
         * @return The determination, true or false.
         */
        public boolean isRecommended() {
            return ByHand.equals(this);
        }

        public boolean inGroup(String groupName) {
            if ("recommended".equals(groupName)) { return isRecommended(); }
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppPaymentMethod> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppPaymentMethod) { return OptionalThing.of((AppPaymentMethod)code); }
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
        public static OptionalThing<AppPaymentMethod> byName(String name) {
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
         */
        public static AppPaymentMethod codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppPaymentMethod) { return (AppPaymentMethod)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppPaymentMethod nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppPaymentMethod> listAll() {
            return new ArrayList<AppPaymentMethod>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppPaymentMethod> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("recommended".equalsIgnoreCase(groupName)) { return listOfRecommended(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppPaymentMethod." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppPaymentMethod> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppPaymentMethod> clsList = new ArrayList<AppPaymentMethod>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * the most recommended method <br>
         * The group elements:[ByHand]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppPaymentMethod> listOfRecommended() {
            return new ArrayList<AppPaymentMethod>(Arrays.asList(ByHand));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppPaymentMethod> groupOf(String groupName) {
            if ("recommended".equals(groupName)) { return listOfRecommended(); }
            return new ArrayList<AppPaymentMethod>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppPaymentMethod> fromDBCls(CDef.PaymentMethod refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.PaymentMethod to AppPaymentMethod by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.PaymentMethod> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.PaymentMethod.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert AppPaymentMethod to PaymentMethod by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of groupingMap when literal only
     */
    public enum DeepWxLiteralGrouping implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase", emptyStrings())
        ,
        /** Castle: Provisional */
        Parade("PRV", "Castle", emptyStrings())
        ,
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", emptyStrings())
        ;
        private static final Map<String, DeepWxLiteralGrouping> _codeClsMap = new HashMap<String, DeepWxLiteralGrouping>();
        private static final Map<String, DeepWxLiteralGrouping> _nameClsMap = new HashMap<String, DeepWxLiteralGrouping>();
        static {
            for (DeepWxLiteralGrouping value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private DeepWxLiteralGrouping(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.DeepWxLiteralGrouping; }

        /**
         * Is the classification in the group? <br>
         * can login <br>
         * The group elements:[OneMan, Parade]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() {
            return OneMan.equals(this) || Parade.equals(this);
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
        public static OptionalThing<DeepWxLiteralGrouping> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof DeepWxLiteralGrouping) { return OptionalThing.of((DeepWxLiteralGrouping)code); }
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
        public static OptionalThing<DeepWxLiteralGrouping> byName(String name) {
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
         */
        public static DeepWxLiteralGrouping codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof DeepWxLiteralGrouping) { return (DeepWxLiteralGrouping)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static DeepWxLiteralGrouping nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DeepWxLiteralGrouping> listAll() {
            return new ArrayList<DeepWxLiteralGrouping>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<DeepWxLiteralGrouping> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: DeepWxLiteralGrouping." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<DeepWxLiteralGrouping> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<DeepWxLiteralGrouping> clsList = new ArrayList<DeepWxLiteralGrouping>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * can login <br>
         * The group elements:[OneMan, Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxLiteralGrouping> listOfServiceAvailable() {
            return new ArrayList<DeepWxLiteralGrouping>(Arrays.asList(OneMan, Parade));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<DeepWxLiteralGrouping> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<DeepWxLiteralGrouping>(4);
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of deprecated classification top
     */
    public enum DeepWxDeprecatedCls implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings())
        ,
        /** All Statuses: without status filter */
        All("ALL", "All Statuses", emptyStrings())
        ;
        private static final Map<String, DeepWxDeprecatedCls> _codeClsMap = new HashMap<String, DeepWxDeprecatedCls>();
        private static final Map<String, DeepWxDeprecatedCls> _nameClsMap = new HashMap<String, DeepWxDeprecatedCls>();
        static {
            for (DeepWxDeprecatedCls value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private DeepWxDeprecatedCls(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.DeepWxDeprecatedCls; }

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
        public static OptionalThing<DeepWxDeprecatedCls> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof DeepWxDeprecatedCls) { return OptionalThing.of((DeepWxDeprecatedCls)code); }
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
        public static OptionalThing<DeepWxDeprecatedCls> byName(String name) {
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
         */
        public static DeepWxDeprecatedCls codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof DeepWxDeprecatedCls) { return (DeepWxDeprecatedCls)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static DeepWxDeprecatedCls nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DeepWxDeprecatedCls> listAll() {
            return new ArrayList<DeepWxDeprecatedCls>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<DeepWxDeprecatedCls> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: DeepWxDeprecatedCls." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<DeepWxDeprecatedCls> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<DeepWxDeprecatedCls> clsList = new ArrayList<DeepWxDeprecatedCls>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedCls> listOfServiceAvailable() {
            return new ArrayList<DeepWxDeprecatedCls>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedCls> listOfShortOfFormalized() {
            return new ArrayList<DeepWxDeprecatedCls>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<DeepWxDeprecatedCls> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<DeepWxDeprecatedCls>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<DeepWxDeprecatedCls> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to DeepWxDeprecatedCls by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert DeepWxDeprecatedCls to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of deprecatedMap
     */
    public enum DeepWxDeprecatedElement implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized", emptyStrings())
        ,
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings())
        ,
        /** Provisional: first status after entry, allowed to use only part of service (deprecated: test of deprecated) */
        @Deprecated
        Provisional("PRV", "Provisional", emptyStrings())
        ,
        /** All Statuses: without status filter (deprecated: and also test of deprecated) */
        @Deprecated
        All("ALL", "All Statuses", emptyStrings())
        ;
        private static final Map<String, DeepWxDeprecatedElement> _codeClsMap = new HashMap<String, DeepWxDeprecatedElement>();
        private static final Map<String, DeepWxDeprecatedElement> _nameClsMap = new HashMap<String, DeepWxDeprecatedElement>();
        static {
            for (DeepWxDeprecatedElement value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private DeepWxDeprecatedElement(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.DeepWxDeprecatedElement; }

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
        public static OptionalThing<DeepWxDeprecatedElement> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof DeepWxDeprecatedElement) { return OptionalThing.of((DeepWxDeprecatedElement)code); }
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
        public static OptionalThing<DeepWxDeprecatedElement> byName(String name) {
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
         */
        public static DeepWxDeprecatedElement codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof DeepWxDeprecatedElement) { return (DeepWxDeprecatedElement)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static DeepWxDeprecatedElement nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DeepWxDeprecatedElement> listAll() {
            return new ArrayList<DeepWxDeprecatedElement>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<DeepWxDeprecatedElement> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: DeepWxDeprecatedElement." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<DeepWxDeprecatedElement> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<DeepWxDeprecatedElement> clsList = new ArrayList<DeepWxDeprecatedElement>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedElement> listOfServiceAvailable() {
            return new ArrayList<DeepWxDeprecatedElement>(Arrays.asList(Formalized, Provisional));
        }

        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedElement> listOfShortOfFormalized() {
            return new ArrayList<DeepWxDeprecatedElement>(Arrays.asList(Provisional));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<DeepWxDeprecatedElement> groupOf(String groupName) {
            if ("serviceAvailable".equals(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equals(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<DeepWxDeprecatedElement>(4);
        }

        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<DeepWxDeprecatedElement> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(codeOf(refCode), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to DeepWxDeprecatedElement by the referred code: " + refCode);
            });
        }

        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.codeOf(appCode), () -> {
                throw new IllegalStateException("Cannot convert DeepWxDeprecatedElement to MemberStatus by the app code: " + appCode);
            });
        }

        @Override public String toString() { return code(); }
    }

    /**
     * test of path parameter by nameof() in LastaFlute action
     */
    public enum AppWxNameOf implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("ONE", "ShowBase", new String[] {"oneman"})
        ,
        /** Orlean: Withdrawal */
        MiniO("MIN", "Orlean", new String[] {"minio"})
        ;
        private static final Map<String, AppWxNameOf> _codeClsMap = new HashMap<String, AppWxNameOf>();
        private static final Map<String, AppWxNameOf> _nameClsMap = new HashMap<String, AppWxNameOf>();
        static {
            for (AppWxNameOf value : values()) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) { _codeClsMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppWxNameOf(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters))); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppWxNameOf; }

        public boolean inGroup(String groupName) {
            return false;
        }

        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppWxNameOf> of(Object code) {
            if (code == null) { return OptionalThing.ofNullable(null, () -> { throw new ClassificationNotFoundException("null code specified"); }); }
            if (code instanceof AppWxNameOf) { return OptionalThing.of((AppWxNameOf)code); }
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
        public static OptionalThing<AppWxNameOf> byName(String name) {
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
         */
        public static AppWxNameOf codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof AppWxNameOf) { return (AppWxNameOf)code; }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span> <br>
         * Get the classification by the name (also called 'value' in ENUM world).
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         */
        public static AppWxNameOf nameOf(String name) {
            if (name == null) { return null; }
            try { return valueOf(name); } catch (RuntimeException ignored) { return null; }
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppWxNameOf> listAll() {
            return new ArrayList<AppWxNameOf>(Arrays.asList(values()));
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if not found, throws exception)
         */
        public static List<AppWxNameOf> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: AppWxNameOf." + groupName);
        }

        /**
         * Get the list of classification elements corresponding to the specified codes. (returns new copied list) <br>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         */
        public static List<AppWxNameOf> listOf(Collection<String> codeList) {
            if (codeList == null) { throw new IllegalArgumentException("The argument 'codeList' should not be null."); }
            List<AppWxNameOf> clsList = new ArrayList<AppWxNameOf>(codeList.size());
            for (String code : codeList) { clsList.add(of(code).get()); }
            return clsList;
        }

        /**
         * Get the list of classification elements in the specified group. (returns new copied list) <br>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         */
        public static List<AppWxNameOf> groupOf(String groupName) {
            return new ArrayList<AppWxNameOf>(4);
        }

        @Override public String toString() { return code(); }
    }

    public enum DefMeta implements ClassificationMeta {
        /** test of no theme refCls, expects DB cls */
        AppMaihama
        ,
        /** test of included, expects grouping, sub-item, sisters */
        AppSea
        ,
        /** test of exists, expects no grouping, no sub-item, sisters */
        AppLand
        ,
        /** test of matches, expects no grouping, no sub-item, sisters */
        AppPiari
        ,
        /** test of manual grouping map using refCls as included, expects overridden, added */
        AppBonvo
        ,
        /** test of manual grouping map using refCls as exists, expects new grouping is available */
        AppDstore
        ,
        /** test of manual sub-item and sisters as included, expects merged, only order() exists */
        AppAmba
        ,
        /** test of manual sub-item and sisters as exists, expects new only here */
        AppMiraco
        ,
        /** test of referring group, expects grouped elements only and sub-item, sisters exist */
        AppDohotel
        ,
        /** test of included with overriding, expected merged */
        AppAmphi
        ,
        /** test of reference to namedcls, case1 */
        AppDockside
        ,
        /** test of reference to namedcls, case2 */
        AppHangar
        ,
        /** test of reference to namedcls, exists */
        AppMagiclamp
        ,
        /** test of reference to namedcls, matches */
        AppBroadway
        ,
        /** test of including sub-item and sisters when implicit classification */
        AppFlg
        ,
        /** test of including grouping map when implicit classification */
        AppPaymentMethod
        ,
        /** test of groupingMap when literal only */
        DeepWxLiteralGrouping
        ,
        /** test of deprecated classification top */
        DeepWxDeprecatedCls
        ,
        /** test of deprecatedMap */
        DeepWxDeprecatedElement
        ,
        /** test of path parameter by nameof() in LastaFlute action */
        AppWxNameOf
        ;
        public String classificationName() {
            return name(); // same as definition name
        }

        public OptionalThing<? extends Classification> of(Object code) {
            if (AppMaihama.name().equals(name())) { return AppCDef.AppMaihama.of(code); }
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.of(code); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.of(code); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.of(code); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.of(code); }
            if (AppDstore.name().equals(name())) { return AppCDef.AppDstore.of(code); }
            if (AppAmba.name().equals(name())) { return AppCDef.AppAmba.of(code); }
            if (AppMiraco.name().equals(name())) { return AppCDef.AppMiraco.of(code); }
            if (AppDohotel.name().equals(name())) { return AppCDef.AppDohotel.of(code); }
            if (AppAmphi.name().equals(name())) { return AppCDef.AppAmphi.of(code); }
            if (AppDockside.name().equals(name())) { return AppCDef.AppDockside.of(code); }
            if (AppHangar.name().equals(name())) { return AppCDef.AppHangar.of(code); }
            if (AppMagiclamp.name().equals(name())) { return AppCDef.AppMagiclamp.of(code); }
            if (AppBroadway.name().equals(name())) { return AppCDef.AppBroadway.of(code); }
            if (AppFlg.name().equals(name())) { return AppCDef.AppFlg.of(code); }
            if (AppPaymentMethod.name().equals(name())) { return AppCDef.AppPaymentMethod.of(code); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return AppCDef.DeepWxLiteralGrouping.of(code); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return AppCDef.DeepWxDeprecatedCls.of(code); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return AppCDef.DeepWxDeprecatedElement.of(code); }
            if (AppWxNameOf.name().equals(name())) { return AppCDef.AppWxNameOf.of(code); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public OptionalThing<? extends Classification> byName(String name) {
            if (AppMaihama.name().equals(name())) { return AppCDef.AppMaihama.byName(name); }
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.byName(name); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.byName(name); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.byName(name); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.byName(name); }
            if (AppDstore.name().equals(name())) { return AppCDef.AppDstore.byName(name); }
            if (AppAmba.name().equals(name())) { return AppCDef.AppAmba.byName(name); }
            if (AppMiraco.name().equals(name())) { return AppCDef.AppMiraco.byName(name); }
            if (AppDohotel.name().equals(name())) { return AppCDef.AppDohotel.byName(name); }
            if (AppAmphi.name().equals(name())) { return AppCDef.AppAmphi.byName(name); }
            if (AppDockside.name().equals(name())) { return AppCDef.AppDockside.byName(name); }
            if (AppHangar.name().equals(name())) { return AppCDef.AppHangar.byName(name); }
            if (AppMagiclamp.name().equals(name())) { return AppCDef.AppMagiclamp.byName(name); }
            if (AppBroadway.name().equals(name())) { return AppCDef.AppBroadway.byName(name); }
            if (AppFlg.name().equals(name())) { return AppCDef.AppFlg.byName(name); }
            if (AppPaymentMethod.name().equals(name())) { return AppCDef.AppPaymentMethod.byName(name); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return AppCDef.DeepWxLiteralGrouping.byName(name); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return AppCDef.DeepWxDeprecatedCls.byName(name); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return AppCDef.DeepWxDeprecatedElement.byName(name); }
            if (AppWxNameOf.name().equals(name())) { return AppCDef.AppWxNameOf.byName(name); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public Classification codeOf(Object code) { // null if not found, old style so use of(code)
            if (AppMaihama.name().equals(name())) { return AppCDef.AppMaihama.codeOf(code); }
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.codeOf(code); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.codeOf(code); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.codeOf(code); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.codeOf(code); }
            if (AppDstore.name().equals(name())) { return AppCDef.AppDstore.codeOf(code); }
            if (AppAmba.name().equals(name())) { return AppCDef.AppAmba.codeOf(code); }
            if (AppMiraco.name().equals(name())) { return AppCDef.AppMiraco.codeOf(code); }
            if (AppDohotel.name().equals(name())) { return AppCDef.AppDohotel.codeOf(code); }
            if (AppAmphi.name().equals(name())) { return AppCDef.AppAmphi.codeOf(code); }
            if (AppDockside.name().equals(name())) { return AppCDef.AppDockside.codeOf(code); }
            if (AppHangar.name().equals(name())) { return AppCDef.AppHangar.codeOf(code); }
            if (AppMagiclamp.name().equals(name())) { return AppCDef.AppMagiclamp.codeOf(code); }
            if (AppBroadway.name().equals(name())) { return AppCDef.AppBroadway.codeOf(code); }
            if (AppFlg.name().equals(name())) { return AppCDef.AppFlg.codeOf(code); }
            if (AppPaymentMethod.name().equals(name())) { return AppCDef.AppPaymentMethod.codeOf(code); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return AppCDef.DeepWxLiteralGrouping.codeOf(code); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return AppCDef.DeepWxDeprecatedCls.codeOf(code); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return AppCDef.DeepWxDeprecatedElement.codeOf(code); }
            if (AppWxNameOf.name().equals(name())) { return AppCDef.AppWxNameOf.codeOf(code); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public Classification nameOf(String name) { // null if not found, old style so use byName(name)
            if (AppMaihama.name().equals(name())) { return AppCDef.AppMaihama.valueOf(name); }
            if (AppSea.name().equals(name())) { return AppCDef.AppSea.valueOf(name); }
            if (AppLand.name().equals(name())) { return AppCDef.AppLand.valueOf(name); }
            if (AppPiari.name().equals(name())) { return AppCDef.AppPiari.valueOf(name); }
            if (AppBonvo.name().equals(name())) { return AppCDef.AppBonvo.valueOf(name); }
            if (AppDstore.name().equals(name())) { return AppCDef.AppDstore.valueOf(name); }
            if (AppAmba.name().equals(name())) { return AppCDef.AppAmba.valueOf(name); }
            if (AppMiraco.name().equals(name())) { return AppCDef.AppMiraco.valueOf(name); }
            if (AppDohotel.name().equals(name())) { return AppCDef.AppDohotel.valueOf(name); }
            if (AppAmphi.name().equals(name())) { return AppCDef.AppAmphi.valueOf(name); }
            if (AppDockside.name().equals(name())) { return AppCDef.AppDockside.valueOf(name); }
            if (AppHangar.name().equals(name())) { return AppCDef.AppHangar.valueOf(name); }
            if (AppMagiclamp.name().equals(name())) { return AppCDef.AppMagiclamp.valueOf(name); }
            if (AppBroadway.name().equals(name())) { return AppCDef.AppBroadway.valueOf(name); }
            if (AppFlg.name().equals(name())) { return AppCDef.AppFlg.valueOf(name); }
            if (AppPaymentMethod.name().equals(name())) { return AppCDef.AppPaymentMethod.valueOf(name); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return AppCDef.DeepWxLiteralGrouping.valueOf(name); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return AppCDef.DeepWxDeprecatedCls.valueOf(name); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return AppCDef.DeepWxDeprecatedElement.valueOf(name); }
            if (AppWxNameOf.name().equals(name())) { return AppCDef.AppWxNameOf.valueOf(name); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> listAll() {
            if (AppMaihama.name().equals(name())) { return toClsList(AppCDef.AppMaihama.listAll()); }
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.listAll()); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.listAll()); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.listAll()); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.listAll()); }
            if (AppDstore.name().equals(name())) { return toClsList(AppCDef.AppDstore.listAll()); }
            if (AppAmba.name().equals(name())) { return toClsList(AppCDef.AppAmba.listAll()); }
            if (AppMiraco.name().equals(name())) { return toClsList(AppCDef.AppMiraco.listAll()); }
            if (AppDohotel.name().equals(name())) { return toClsList(AppCDef.AppDohotel.listAll()); }
            if (AppAmphi.name().equals(name())) { return toClsList(AppCDef.AppAmphi.listAll()); }
            if (AppDockside.name().equals(name())) { return toClsList(AppCDef.AppDockside.listAll()); }
            if (AppHangar.name().equals(name())) { return toClsList(AppCDef.AppHangar.listAll()); }
            if (AppMagiclamp.name().equals(name())) { return toClsList(AppCDef.AppMagiclamp.listAll()); }
            if (AppBroadway.name().equals(name())) { return toClsList(AppCDef.AppBroadway.listAll()); }
            if (AppFlg.name().equals(name())) { return toClsList(AppCDef.AppFlg.listAll()); }
            if (AppPaymentMethod.name().equals(name())) { return toClsList(AppCDef.AppPaymentMethod.listAll()); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return toClsList(AppCDef.DeepWxLiteralGrouping.listAll()); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedCls.listAll()); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedElement.listAll()); }
            if (AppWxNameOf.name().equals(name())) { return toClsList(AppCDef.AppWxNameOf.listAll()); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> listByGroup(String groupName) { // exception if not found
            if (AppMaihama.name().equals(name())) { return toClsList(AppCDef.AppMaihama.listByGroup(groupName)); }
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.listByGroup(groupName)); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.listByGroup(groupName)); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.listByGroup(groupName)); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.listByGroup(groupName)); }
            if (AppDstore.name().equals(name())) { return toClsList(AppCDef.AppDstore.listByGroup(groupName)); }
            if (AppAmba.name().equals(name())) { return toClsList(AppCDef.AppAmba.listByGroup(groupName)); }
            if (AppMiraco.name().equals(name())) { return toClsList(AppCDef.AppMiraco.listByGroup(groupName)); }
            if (AppDohotel.name().equals(name())) { return toClsList(AppCDef.AppDohotel.listByGroup(groupName)); }
            if (AppAmphi.name().equals(name())) { return toClsList(AppCDef.AppAmphi.listByGroup(groupName)); }
            if (AppDockside.name().equals(name())) { return toClsList(AppCDef.AppDockside.listByGroup(groupName)); }
            if (AppHangar.name().equals(name())) { return toClsList(AppCDef.AppHangar.listByGroup(groupName)); }
            if (AppMagiclamp.name().equals(name())) { return toClsList(AppCDef.AppMagiclamp.listByGroup(groupName)); }
            if (AppBroadway.name().equals(name())) { return toClsList(AppCDef.AppBroadway.listByGroup(groupName)); }
            if (AppFlg.name().equals(name())) { return toClsList(AppCDef.AppFlg.listByGroup(groupName)); }
            if (AppPaymentMethod.name().equals(name())) { return toClsList(AppCDef.AppPaymentMethod.listByGroup(groupName)); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return toClsList(AppCDef.DeepWxLiteralGrouping.listByGroup(groupName)); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedCls.listByGroup(groupName)); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedElement.listByGroup(groupName)); }
            if (AppWxNameOf.name().equals(name())) { return toClsList(AppCDef.AppWxNameOf.listByGroup(groupName)); }
            throw new IllegalStateException("Unknown groupName: " + groupName + ", " + this); // basically unreachable
        }

        public List<Classification> listOf(Collection<String> codeList) {
            if (AppMaihama.name().equals(name())) { return toClsList(AppCDef.AppMaihama.listOf(codeList)); }
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.listOf(codeList)); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.listOf(codeList)); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.listOf(codeList)); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.listOf(codeList)); }
            if (AppDstore.name().equals(name())) { return toClsList(AppCDef.AppDstore.listOf(codeList)); }
            if (AppAmba.name().equals(name())) { return toClsList(AppCDef.AppAmba.listOf(codeList)); }
            if (AppMiraco.name().equals(name())) { return toClsList(AppCDef.AppMiraco.listOf(codeList)); }
            if (AppDohotel.name().equals(name())) { return toClsList(AppCDef.AppDohotel.listOf(codeList)); }
            if (AppAmphi.name().equals(name())) { return toClsList(AppCDef.AppAmphi.listOf(codeList)); }
            if (AppDockside.name().equals(name())) { return toClsList(AppCDef.AppDockside.listOf(codeList)); }
            if (AppHangar.name().equals(name())) { return toClsList(AppCDef.AppHangar.listOf(codeList)); }
            if (AppMagiclamp.name().equals(name())) { return toClsList(AppCDef.AppMagiclamp.listOf(codeList)); }
            if (AppBroadway.name().equals(name())) { return toClsList(AppCDef.AppBroadway.listOf(codeList)); }
            if (AppFlg.name().equals(name())) { return toClsList(AppCDef.AppFlg.listOf(codeList)); }
            if (AppPaymentMethod.name().equals(name())) { return toClsList(AppCDef.AppPaymentMethod.listOf(codeList)); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return toClsList(AppCDef.DeepWxLiteralGrouping.listOf(codeList)); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedCls.listOf(codeList)); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedElement.listOf(codeList)); }
            if (AppWxNameOf.name().equals(name())) { return toClsList(AppCDef.AppWxNameOf.listOf(codeList)); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> groupOf(String groupName) { // old style
            if (AppMaihama.name().equals(name())) { return toClsList(AppCDef.AppMaihama.groupOf(groupName)); }
            if (AppSea.name().equals(name())) { return toClsList(AppCDef.AppSea.groupOf(groupName)); }
            if (AppLand.name().equals(name())) { return toClsList(AppCDef.AppLand.groupOf(groupName)); }
            if (AppPiari.name().equals(name())) { return toClsList(AppCDef.AppPiari.groupOf(groupName)); }
            if (AppBonvo.name().equals(name())) { return toClsList(AppCDef.AppBonvo.groupOf(groupName)); }
            if (AppDstore.name().equals(name())) { return toClsList(AppCDef.AppDstore.groupOf(groupName)); }
            if (AppAmba.name().equals(name())) { return toClsList(AppCDef.AppAmba.groupOf(groupName)); }
            if (AppMiraco.name().equals(name())) { return toClsList(AppCDef.AppMiraco.groupOf(groupName)); }
            if (AppDohotel.name().equals(name())) { return toClsList(AppCDef.AppDohotel.groupOf(groupName)); }
            if (AppAmphi.name().equals(name())) { return toClsList(AppCDef.AppAmphi.groupOf(groupName)); }
            if (AppDockside.name().equals(name())) { return toClsList(AppCDef.AppDockside.groupOf(groupName)); }
            if (AppHangar.name().equals(name())) { return toClsList(AppCDef.AppHangar.groupOf(groupName)); }
            if (AppMagiclamp.name().equals(name())) { return toClsList(AppCDef.AppMagiclamp.groupOf(groupName)); }
            if (AppBroadway.name().equals(name())) { return toClsList(AppCDef.AppBroadway.groupOf(groupName)); }
            if (AppFlg.name().equals(name())) { return toClsList(AppCDef.AppFlg.groupOf(groupName)); }
            if (AppPaymentMethod.name().equals(name())) { return toClsList(AppCDef.AppPaymentMethod.groupOf(groupName)); }
            if (DeepWxLiteralGrouping.name().equals(name())) { return toClsList(AppCDef.DeepWxLiteralGrouping.groupOf(groupName)); }
            if (DeepWxDeprecatedCls.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedCls.groupOf(groupName)); }
            if (DeepWxDeprecatedElement.name().equals(name())) { return toClsList(AppCDef.DeepWxDeprecatedElement.groupOf(groupName)); }
            if (AppWxNameOf.name().equals(name())) { return toClsList(AppCDef.AppWxNameOf.groupOf(groupName)); }
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        @SuppressWarnings("unchecked")
        private List<Classification> toClsList(List<?> clsList) {
            return (List<Classification>)clsList;
        }

        public ClassificationCodeType codeType() {
            if (AppMaihama.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppSea.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppLand.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppPiari.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppBonvo.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppDstore.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppAmba.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppMiraco.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppDohotel.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppAmphi.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppDockside.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppHangar.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppMagiclamp.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppBroadway.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppFlg.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppPaymentMethod.name().equals(name())) { return ClassificationCodeType.String; }
            if (DeepWxLiteralGrouping.name().equals(name())) { return ClassificationCodeType.String; }
            if (DeepWxDeprecatedCls.name().equals(name())) { return ClassificationCodeType.String; }
            if (DeepWxDeprecatedElement.name().equals(name())) { return ClassificationCodeType.String; }
            if (AppWxNameOf.name().equals(name())) { return ClassificationCodeType.String; }
            return ClassificationCodeType.String; // as default
        }

        public ClassificationUndefinedHandlingType undefinedHandlingType() {
            if (AppMaihama.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppSea.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppLand.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppPiari.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppBonvo.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppDstore.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppAmba.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppMiraco.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppDohotel.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppAmphi.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppDockside.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppHangar.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppMagiclamp.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppBroadway.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppFlg.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppPaymentMethod.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (DeepWxLiteralGrouping.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (DeepWxDeprecatedCls.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (DeepWxDeprecatedElement.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            if (AppWxNameOf.name().equals(name())) { return ClassificationUndefinedHandlingType.LOGGING; }
            return ClassificationUndefinedHandlingType.LOGGING; // as default
        }

        public static OptionalThing<AppCDef.DefMeta> find(String classificationName) { // instead of valueOf()
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            if (AppMaihama.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppMaihama); }
            if (AppSea.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppSea); }
            if (AppLand.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppLand); }
            if (AppPiari.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppPiari); }
            if (AppBonvo.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppBonvo); }
            if (AppDstore.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppDstore); }
            if (AppAmba.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppAmba); }
            if (AppMiraco.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppMiraco); }
            if (AppDohotel.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppDohotel); }
            if (AppAmphi.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppAmphi); }
            if (AppDockside.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppDockside); }
            if (AppHangar.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppHangar); }
            if (AppMagiclamp.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppMagiclamp); }
            if (AppBroadway.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppBroadway); }
            if (AppFlg.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppFlg); }
            if (AppPaymentMethod.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppPaymentMethod); }
            if (DeepWxLiteralGrouping.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.DeepWxLiteralGrouping); }
            if (DeepWxDeprecatedCls.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.DeepWxDeprecatedCls); }
            if (DeepWxDeprecatedElement.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.DeepWxDeprecatedElement); }
            if (AppWxNameOf.name().equalsIgnoreCase(classificationName)) { return OptionalThing.of(AppCDef.DefMeta.AppWxNameOf); }
            return OptionalThing.ofNullable(null, () -> {
                throw new ClassificationNotFoundException("Unknown classification: " + classificationName);
            });
        }

        public static AppCDef.DefMeta meta(String classificationName) { // old style so use find(name)
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            if (AppMaihama.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppMaihama; }
            if (AppSea.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppSea; }
            if (AppLand.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppLand; }
            if (AppPiari.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppPiari; }
            if (AppBonvo.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppBonvo; }
            if (AppDstore.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppDstore; }
            if (AppAmba.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppAmba; }
            if (AppMiraco.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppMiraco; }
            if (AppDohotel.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppDohotel; }
            if (AppAmphi.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppAmphi; }
            if (AppDockside.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppDockside; }
            if (AppHangar.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppHangar; }
            if (AppMagiclamp.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppMagiclamp; }
            if (AppBroadway.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppBroadway; }
            if (AppFlg.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppFlg; }
            if (AppPaymentMethod.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppPaymentMethod; }
            if (DeepWxLiteralGrouping.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.DeepWxLiteralGrouping; }
            if (DeepWxDeprecatedCls.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.DeepWxDeprecatedCls; }
            if (DeepWxDeprecatedElement.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.DeepWxDeprecatedElement; }
            if (AppWxNameOf.name().equalsIgnoreCase(classificationName)) { return AppCDef.DefMeta.AppWxNameOf; }
            throw new IllegalStateException("Unknown classification: " + classificationName);
        }

        @SuppressWarnings("unused")
        private String[] xinternalEmptyString() {
            return emptyStrings(); // to suppress 'unused' warning of import statement
        }
    }
}
