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
package org.docksidestage.mylasta.appcls;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import org.dbflute.exception.ClassificationNotFoundException;
import org.dbflute.jdbc.Classification;
import org.dbflute.jdbc.ClassificationCodeType;
import org.dbflute.jdbc.ClassificationMeta;
import org.dbflute.jdbc.ClassificationUndefinedHandlingType;
import org.dbflute.optional.OptionalThing;
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
        Formalized("FML", "Formalized"),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal"),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional");
        private static ZzzoneSlimmer<AppMaihama> _slimmer = new ZzzoneSlimmer<>(AppMaihama.class, values());
        private String _code; private String _alias;
        private AppMaihama(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppMaihama; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppMaihama> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppMaihama> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppMaihama codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppMaihama nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppMaihama> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppMaihama> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppMaihama." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppMaihama> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMaihama> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMaihama> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppMaihama> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppMaihama> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppMaihama by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
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
        Formalized("FML", "Formalized"),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal"),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional");
        private static ZzzoneSlimmer<AppSea> _slimmer = new ZzzoneSlimmer<>(AppSea.class, values());
        private String _code; private String _alias;
        private AppSea(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppSea; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppSea> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppSea> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppSea codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppSea nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppSea> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppSea> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppSea." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppSea> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppSea> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppSea> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppSea> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppSea> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppSea by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppSea to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of exists, expects minimum grouping, no sub-item, sisters
     */
    public enum AppLand implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<AppLand> _slimmer = new ZzzoneSlimmer<>(AppLand.class, values());
        private String _code; private String _alias;
        private AppLand(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppLand; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppLand> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppLand> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppLand codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppLand nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppLand> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppLand> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppLand." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppLand> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppLand> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppLand> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppLand> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppLand by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppLand to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of matches, expects minimum grouping, no sub-item, sisters
     */
    public enum AppPiari implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase"),
        /** Castle: Provisional */
        Parade("PRV", "Castle"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<AppPiari> _slimmer = new ZzzoneSlimmer<>(AppPiari.class, values());
        private String _code; private String _alias;
        private AppPiari(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppPiari; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Parade]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this) || Parade.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Parade]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Parade.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppPiari> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppPiari> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppPiari codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppPiari nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppPiari> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppPiari> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppPiari." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppPiari> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppPiari> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, Parade));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppPiari> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Parade));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppPiari> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppPiari> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppPiari by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
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
        Formalized("FML", "Formalized"),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal"),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional"),
        /** Hangar: Rhythms */
        Mystic("MYS", "Hangar");
        private static ZzzoneSlimmer<AppBonvo> _slimmer = new ZzzoneSlimmer<>(AppBonvo.class, values());
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
        private String _code; private String _alias;
        private AppBonvo(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppBonvo; }
        /**
         * Is the classification in the group? <br>
         * new group name by literal <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The determination, true or false.
         */
        public boolean isAppNewLiteralAvailable() { return Formalized.equals(this) || Provisional.equals(this) || Mystic.equals(this); }
        /**
         * Is the classification in the group? <br>
         * new group name referring existing group <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The determination, true or false.
         */
        public boolean isAppNewRefExistingGroupAvailable() { return Formalized.equals(this) || Provisional.equals(this) || Mystic.equals(this); }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("appNewLiteralAvailable".equalsIgnoreCase(groupName)) { return isAppNewLiteralAvailable(); }
            if ("appNewRefExistingGroupAvailable".equalsIgnoreCase(groupName)) { return isAppNewRefExistingGroupAvailable(); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppBonvo> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppBonvo> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppBonvo codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppBonvo nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppBonvo> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
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
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppBonvo> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * new group name by literal <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfAppNewLiteralAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional, Mystic));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * new group name referring existing group <br>
         * The group elements:[Formalized, Provisional, Mystic]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfAppNewRefExistingGroupAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional, Mystic));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBonvo> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppBonvo> groupOf(String groupName) {
            if ("appNewLiteralAvailable".equalsIgnoreCase(groupName)) { return listOfAppNewLiteralAvailable(); }
            if ("appNewRefExistingGroupAvailable".equalsIgnoreCase(groupName)) { return listOfAppNewRefExistingGroupAvailable(); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppBonvo> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppBonvo by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
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
        OneMan("FML", "ShowBase"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<AppDstore> _slimmer = new ZzzoneSlimmer<>(AppDstore.class, values());
        private String _code; private String _alias;
        private AppDstore(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppDstore; }
        /**
         * Is the classification in the group? <br>
         * new group name as app classfication <br>
         * The group elements:[OneMan, MiniO]
         * @return The determination, true or false.
         */
        public boolean isAppNewAvailable() { return OneMan.equals(this) || MiniO.equals(this); }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this); }
        public boolean inGroup(String groupName) {
            if ("appNewAvailable".equalsIgnoreCase(groupName)) { return isAppNewAvailable(); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppDstore> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppDstore> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppDstore codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppDstore nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppDstore> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppDstore> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("appNewAvailable".equalsIgnoreCase(groupName)) { return listOfAppNewAvailable(); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppDstore." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppDstore> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * new group name as app classfication <br>
         * The group elements:[OneMan, MiniO]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDstore> listOfAppNewAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, MiniO));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDstore> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppDstore> groupOf(String groupName) {
            if ("appNewAvailable".equalsIgnoreCase(groupName)) { return listOfAppNewAvailable(); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppDstore> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppDstore by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
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
        Formalized("FML", "Formalized", emptyStrings()),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings()),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional", emptyStrings()),
        /** Hangar: Rhythms */
        Mystic("MYS", "Hangar", new String[] {"Choucho"});
        private static String[] emptyStrings() { return new String[0]; }
        private static ZzzoneSlimmer<AppAmba> _slimmer = new ZzzoneSlimmer<>(AppAmba.class, values());
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
        { _code = code; _alias = alias; _sisterSet = ZzzoneSlimmer.toSisterSet(sisters); }
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
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppAmba> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppAmba> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppAmba codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppAmba nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppAmba> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppAmba> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppAmba." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppAmba> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmba> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmba> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppAmba> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppAmba> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppAmba by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
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
        OneMan("FML", "ShowBase", new String[] {"ONE"}),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean", new String[] {"MIN"});
        private static ZzzoneSlimmer<AppMiraco> _slimmer = new ZzzoneSlimmer<>(AppMiraco.class, values());
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
        { _code = code; _alias = alias; _sisterSet = ZzzoneSlimmer.toSisterSet(sisters); }
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
        public boolean isServiceAvailable() { return OneMan.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppMiraco> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppMiraco> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppMiraco codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppMiraco nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppMiraco> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppMiraco> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppMiraco." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppMiraco> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMiraco> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppMiraco> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppMiraco> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppMiraco by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppMiraco to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of included with overriding, expected merged
     */
    public enum AppDohotel implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        OneMan("FML", "Formalized", emptyStrings()),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal", emptyStrings()),
        /** Castle: ParadeTwoYears */
        Provisional("PRV", "Castle", new String[] {"Route"}),
        /** Hangar: Rhythms */
        Mystic("MYS", "Hangar", emptyStrings());
        private static String[] emptyStrings() { return new String[0]; }
        private static ZzzoneSlimmer<AppDohotel> _slimmer = new ZzzoneSlimmer<>(AppDohotel.class, values());
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
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "88");
                _subItemMapMap.put(Mystic.code(), Collections.unmodifiableMap(subItemMap));
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppDohotel(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = ZzzoneSlimmer.toSisterSet(sisters); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppDohotel; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppDohotel> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppDohotel> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppDohotel codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppDohotel nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppDohotel> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppDohotel> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppDohotel." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppDohotel> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDohotel> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDohotel> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppDohotel> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppDohotel> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppDohotel by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppDohotel to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of exists with inheriting, expected merged
     */
    public enum AppAmphi implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized"),
        /** Castle: ParadeTwoYears */
        Provisional("PRV", "Castle");
        private static ZzzoneSlimmer<AppAmphi> _slimmer = new ZzzoneSlimmer<>(AppAmphi.class, values());
        private String _code; private String _alias;
        private AppAmphi(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppAmphi; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppAmphi> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppAmphi> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppAmphi codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppAmphi nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppAmphi> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppAmphi> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppAmphi." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppAmphi> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmphi> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppAmphi> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppAmphi> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppAmphi> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppAmphi by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppAmphi to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of matches with inheriting, expected merged
     */
    public enum AppOrien implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        OneMan("FML", "Formalized", emptyStrings()),
        /** Orleans */
        MiniO("WDL", "Orleans", emptyStrings()),
        /** Castle: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Castle", new String[] {"Provisional"});
        private static String[] emptyStrings() { return new String[0]; }
        private static ZzzoneSlimmer<AppOrien> _slimmer = new ZzzoneSlimmer<>(AppOrien.class, values());
        private static final Map<String, Map<String, Object>> _subItemMapMap = new HashMap<String, Map<String, Object>>();
        static {
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                _subItemMapMap.put(OneMan.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "5");
                _subItemMapMap.put(MiniO.code(), Collections.unmodifiableMap(subItemMap));
            }
            {
                Map<String, Object> subItemMap = new HashMap<String, Object>();
                subItemMap.put("order", "9");
                _subItemMapMap.put(Provisional.code(), Collections.unmodifiableMap(subItemMap));
            }
        }
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppOrien(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = ZzzoneSlimmer.toSisterSet(sisters); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppOrien; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppOrien> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppOrien> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppOrien codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppOrien nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppOrien> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppOrien> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppOrien." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppOrien> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppOrien> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppOrien> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppOrien> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppOrien> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppOrien by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppOrien to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of referring group as included, expects grouped elements only and sub-item, sisters exist
     */
    public enum AppCeleb implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized"),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional");
        private static ZzzoneSlimmer<AppCeleb> _slimmer = new ZzzoneSlimmer<>(AppCeleb.class, values());
        private String _code; private String _alias;
        private AppCeleb(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppCeleb; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppCeleb> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppCeleb> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppCeleb codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppCeleb nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppCeleb> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppCeleb> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppCeleb." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppCeleb> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppCeleb> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppCeleb> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppCeleb> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppCeleb> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppCeleb by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppCeleb to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of referring group as exists, expects grouped elements only and existence checked
     */
    public enum AppToys implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase");
        private static ZzzoneSlimmer<AppToys> _slimmer = new ZzzoneSlimmer<>(AppToys.class, values());
        private String _code; private String _alias;
        private AppToys(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppToys; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppToys> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppToys> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppToys codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppToys nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppToys> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppToys> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppToys." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppToys> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppToys> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppToys> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppToys> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppToys by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppToys to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of referring group as matches, expects grouped elements only and matching checked
     */
    public enum AppBrighton implements AppCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase"),
        /** Castle: Provisional */
        Parade("PRV", "Castle");
        private static ZzzoneSlimmer<AppBrighton> _slimmer = new ZzzoneSlimmer<>(AppBrighton.class, values());
        private String _code; private String _alias;
        private AppBrighton(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppBrighton; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Parade]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this) || Parade.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Parade]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Parade.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppBrighton> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppBrighton> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppBrighton codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppBrighton nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppBrighton> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppBrighton> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppBrighton." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppBrighton> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBrighton> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, Parade));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBrighton> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Parade));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppBrighton> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppBrighton> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppBrighton by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert AppBrighton to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of reference to namedcls, case1
     */
    public enum AppDockside implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized"),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal"),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional");
        private static ZzzoneSlimmer<AppDockside> _slimmer = new ZzzoneSlimmer<>(AppDockside.class, values());
        private String _code; private String _alias;
        private AppDockside(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppDockside; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppDockside> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppDockside> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppDockside codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppDockside nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppDockside> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppDockside> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppDockside." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppDockside> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDockside> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppDockside> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppDockside> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppDockside> fromRefCls(LeonardoCDef.DaSea refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaSea to AppDockside by the referred code: " + refCode);
            });
        }
        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaSea> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaSea.of(appCode).orElse(null), () -> {
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
        OneMan("FML", "ShowBase"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<AppHangar> _slimmer = new ZzzoneSlimmer<>(AppHangar.class, values());
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
        private String _code; private String _alias;
        private AppHangar(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
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
        public boolean isServiceAvailable() { return OneMan.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppHangar> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppHangar> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppHangar codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppHangar nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppHangar> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppHangar> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppHangar." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppHangar> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppHangar> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppHangar> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppHangar> fromRefCls(LeonardoCDef.DaLand refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaLand to AppHangar by the referred code: " + refCode);
            });
        }
        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaLand> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaLand.of(appCode).orElse(null), () -> {
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
        OneMan("FML", "ShowBase"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<AppMagiclamp> _slimmer = new ZzzoneSlimmer<>(AppMagiclamp.class, values());
        private String _code; private String _alias;
        private AppMagiclamp(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppMagiclamp; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppMagiclamp> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppMagiclamp> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppMagiclamp codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppMagiclamp nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppMagiclamp> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppMagiclamp> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppMagiclamp." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppMagiclamp> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppMagiclamp> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppMagiclamp> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppMagiclamp> fromRefCls(LeonardoCDef.DaSea refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaSea to AppMagiclamp by the referred code: " + refCode);
            });
        }
        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaSea> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaSea.of(appCode).orElse(null), () -> {
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
        OneMan("FML", "ShowBase"),
        /** Dstore: Provisional */
        Dstore("PRV", "Dstore"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<AppBroadway> _slimmer = new ZzzoneSlimmer<>(AppBroadway.class, values());
        private String _code; private String _alias;
        private AppBroadway(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppBroadway; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Dstore]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this) || Dstore.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Dstore]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Dstore.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppBroadway> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppBroadway> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppBroadway codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppBroadway nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppBroadway> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppBroadway> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppBroadway." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppBroadway> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBroadway> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, Dstore));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppBroadway> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Dstore));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppBroadway> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The referred classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppBroadway> fromRefCls(LeonardoCDef.DaPiari refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert LeonardoCDef.DaPiari to AppBroadway by the referred code: " + refCode);
            });
        }
        /**
         * @return The referred classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<LeonardoCDef.DaPiari> toRefCls() {
            String appCode = code();
            return OptionalThing.ofNullable(LeonardoCDef.DaPiari.of(appCode).orElse(null), () -> {
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
        True("1", "Checked", new String[] {"true"}),
        /** Unchecked: means no */
        False("0", "Unchecked", new String[] {"false"});
        private static ZzzoneSlimmer<AppFlg> _slimmer = new ZzzoneSlimmer<>(AppFlg.class, values());
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppFlg(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = ZzzoneSlimmer.toSisterSet(sisters); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppFlg; }
        public boolean inGroup(String groupName) { return false; }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppFlg> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppFlg> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppFlg codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppFlg nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppFlg> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppFlg> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: AppFlg." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppFlg> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppFlg> groupOf(String groupName) { return new ArrayList<>(); }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppFlg> fromDBCls(CDef.Flg refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.Flg to AppFlg by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.Flg> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.Flg.of(appCode).orElse(null), () -> {
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
        ByHand("HAN", "by hand"),
        /** bank transfer: bank transfer payment */
        BankTransfer("BAK", "bank transfer"),
        /** credit card: credit card payment */
        CreditCard("CRC", "credit card");
        private static ZzzoneSlimmer<AppPaymentMethod> _slimmer = new ZzzoneSlimmer<>(AppPaymentMethod.class, values());
        private String _code; private String _alias;
        private AppPaymentMethod(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppPaymentMethod; }
        /**
         * Is the classification in the group? <br>
         * the most recommended method <br>
         * The group elements:[ByHand]
         * @return The determination, true or false.
         */
        public boolean isRecommended() { return ByHand.equals(this); }
        public boolean inGroup(String groupName) {
            if ("recommended".equalsIgnoreCase(groupName)) { return isRecommended(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppPaymentMethod> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppPaymentMethod> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppPaymentMethod codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppPaymentMethod nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppPaymentMethod> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppPaymentMethod> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("recommended".equalsIgnoreCase(groupName)) { return listOfRecommended(); }
            throw new ClassificationNotFoundException("Unknown classification group: AppPaymentMethod." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppPaymentMethod> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * the most recommended method <br>
         * The group elements:[ByHand]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<AppPaymentMethod> listOfRecommended() {
            return new ArrayList<>(Arrays.asList(ByHand));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppPaymentMethod> groupOf(String groupName) {
            if ("recommended".equalsIgnoreCase(groupName)) { return listOfRecommended(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<AppPaymentMethod> fromDBCls(CDef.PaymentMethod refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.PaymentMethod to AppPaymentMethod by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.PaymentMethod> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.PaymentMethod.of(appCode).orElse(null), () -> {
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
        OneMan("FML", "ShowBase"),
        /** Castle: Provisional */
        Parade("PRV", "Castle"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<DeepWxLiteralGrouping> _slimmer = new ZzzoneSlimmer<>(DeepWxLiteralGrouping.class, values());
        private String _code; private String _alias;
        private DeepWxLiteralGrouping(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.DeepWxLiteralGrouping; }
        /**
         * Is the classification in the group? <br>
         * can login <br>
         * The group elements:[OneMan, Parade]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return OneMan.equals(this) || Parade.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DeepWxLiteralGrouping> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DeepWxLiteralGrouping> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static DeepWxLiteralGrouping codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static DeepWxLiteralGrouping nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DeepWxLiteralGrouping> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<DeepWxLiteralGrouping> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: DeepWxLiteralGrouping." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<DeepWxLiteralGrouping> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * can login <br>
         * The group elements:[OneMan, Parade]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxLiteralGrouping> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, Parade));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<DeepWxLiteralGrouping> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        @Override public String toString() { return code(); }
    }

    /**
     * test of deprecated classification top
     */
    public enum DeepWxDeprecatedCls implements AppCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized"),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal"),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional"),
        /** All Statuses: without status filter */
        All("ALL", "All Statuses");
        private static ZzzoneSlimmer<DeepWxDeprecatedCls> _slimmer = new ZzzoneSlimmer<>(DeepWxDeprecatedCls.class, values());
        private String _code; private String _alias;
        private DeepWxDeprecatedCls(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.DeepWxDeprecatedCls; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DeepWxDeprecatedCls> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DeepWxDeprecatedCls> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static DeepWxDeprecatedCls codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static DeepWxDeprecatedCls nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DeepWxDeprecatedCls> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<DeepWxDeprecatedCls> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: DeepWxDeprecatedCls." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<DeepWxDeprecatedCls> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedCls> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedCls> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<DeepWxDeprecatedCls> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<DeepWxDeprecatedCls> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to DeepWxDeprecatedCls by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
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
        Formalized("FML", "Formalized"),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal"),
        /** Provisional: first status after entry, allowed to use only part of service (deprecated: test of deprecated) */
        @Deprecated
        Provisional("PRV", "Provisional"),
        /** All Statuses: without status filter (deprecated: and also test of deprecated) */
        @Deprecated
        All("ALL", "All Statuses");
        private static ZzzoneSlimmer<DeepWxDeprecatedElement> _slimmer = new ZzzoneSlimmer<>(DeepWxDeprecatedElement.class, values());
        private String _code; private String _alias;
        private DeepWxDeprecatedElement(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.DeepWxDeprecatedElement; }
        /**
         * Is the classification in the group? <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The determination, true or false.
         */
        public boolean isServiceAvailable() { return Formalized.equals(this) || Provisional.equals(this); }
        /**
         * Is the classification in the group? <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The determination, true or false.
         */
        public boolean isShortOfFormalized() { return Provisional.equals(this); }
        public boolean inGroup(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return isServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return isShortOfFormalized(); }
            return false;
        }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DeepWxDeprecatedElement> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DeepWxDeprecatedElement> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static DeepWxDeprecatedElement codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static DeepWxDeprecatedElement nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DeepWxDeprecatedElement> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<DeepWxDeprecatedElement> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: DeepWxDeprecatedElement." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<DeepWxDeprecatedElement> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedElement> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DeepWxDeprecatedElement> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<DeepWxDeprecatedElement> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<DeepWxDeprecatedElement> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to DeepWxDeprecatedElement by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
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
        OneMan("ONE", "ShowBase", new String[] {"oneman"}),
        /** Orlean: Withdrawal */
        MiniO("MIN", "Orlean", new String[] {"minio"});
        private static ZzzoneSlimmer<AppWxNameOf> _slimmer = new ZzzoneSlimmer<>(AppWxNameOf.class, values());
        private String _code; private String _alias; private Set<String> _sisterSet;
        private AppWxNameOf(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisterSet = ZzzoneSlimmer.toSisterSet(sisters); }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return _sisterSet; }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return AppCDef.DefMeta.AppWxNameOf; }
        public boolean inGroup(String groupName) { return false; }
        /**
         * Get the classification of the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns empty)
         * @return The optional classification corresponding to the code. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppWxNameOf> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<AppWxNameOf> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static AppWxNameOf codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static AppWxNameOf nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<AppWxNameOf> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<AppWxNameOf> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            throw new ClassificationNotFoundException("Unknown classification group: AppWxNameOf." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<AppWxNameOf> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<AppWxNameOf> groupOf(String groupName) { return new ArrayList<>(); }
        @Override public String toString() { return code(); }
    }

    public enum DefMeta implements ClassificationMeta {
        /** test of no theme refCls, expects DB cls */
        AppMaihama(cd -> AppCDef.AppMaihama.of(cd), nm -> AppCDef.AppMaihama.byName(nm)
        , () -> AppCDef.AppMaihama.listAll(), gp -> AppCDef.AppMaihama.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of included, expects grouping, sub-item, sisters */
        AppSea(cd -> AppCDef.AppSea.of(cd), nm -> AppCDef.AppSea.byName(nm)
        , () -> AppCDef.AppSea.listAll(), gp -> AppCDef.AppSea.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of exists, expects minimum grouping, no sub-item, sisters */
        AppLand(cd -> AppCDef.AppLand.of(cd), nm -> AppCDef.AppLand.byName(nm)
        , () -> AppCDef.AppLand.listAll(), gp -> AppCDef.AppLand.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of matches, expects minimum grouping, no sub-item, sisters */
        AppPiari(cd -> AppCDef.AppPiari.of(cd), nm -> AppCDef.AppPiari.byName(nm)
        , () -> AppCDef.AppPiari.listAll(), gp -> AppCDef.AppPiari.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of manual grouping map using refCls as included, expects overridden, added */
        AppBonvo(cd -> AppCDef.AppBonvo.of(cd), nm -> AppCDef.AppBonvo.byName(nm)
        , () -> AppCDef.AppBonvo.listAll(), gp -> AppCDef.AppBonvo.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of manual grouping map using refCls as exists, expects new grouping is available */
        AppDstore(cd -> AppCDef.AppDstore.of(cd), nm -> AppCDef.AppDstore.byName(nm)
        , () -> AppCDef.AppDstore.listAll(), gp -> AppCDef.AppDstore.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of manual sub-item and sisters as included, expects merged, only order() exists */
        AppAmba(cd -> AppCDef.AppAmba.of(cd), nm -> AppCDef.AppAmba.byName(nm)
        , () -> AppCDef.AppAmba.listAll(), gp -> AppCDef.AppAmba.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of manual sub-item and sisters as exists, expects new only here */
        AppMiraco(cd -> AppCDef.AppMiraco.of(cd), nm -> AppCDef.AppMiraco.byName(nm)
        , () -> AppCDef.AppMiraco.listAll(), gp -> AppCDef.AppMiraco.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of included with overriding, expected merged */
        AppDohotel(cd -> AppCDef.AppDohotel.of(cd), nm -> AppCDef.AppDohotel.byName(nm)
        , () -> AppCDef.AppDohotel.listAll(), gp -> AppCDef.AppDohotel.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of exists with inheriting, expected merged */
        AppAmphi(cd -> AppCDef.AppAmphi.of(cd), nm -> AppCDef.AppAmphi.byName(nm)
        , () -> AppCDef.AppAmphi.listAll(), gp -> AppCDef.AppAmphi.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of matches with inheriting, expected merged */
        AppOrien(cd -> AppCDef.AppOrien.of(cd), nm -> AppCDef.AppOrien.byName(nm)
        , () -> AppCDef.AppOrien.listAll(), gp -> AppCDef.AppOrien.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of referring group as included, expects grouped elements only and sub-item, sisters exist */
        AppCeleb(cd -> AppCDef.AppCeleb.of(cd), nm -> AppCDef.AppCeleb.byName(nm)
        , () -> AppCDef.AppCeleb.listAll(), gp -> AppCDef.AppCeleb.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of referring group as exists, expects grouped elements only and existence checked */
        AppToys(cd -> AppCDef.AppToys.of(cd), nm -> AppCDef.AppToys.byName(nm)
        , () -> AppCDef.AppToys.listAll(), gp -> AppCDef.AppToys.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of referring group as matches, expects grouped elements only and matching checked */
        AppBrighton(cd -> AppCDef.AppBrighton.of(cd), nm -> AppCDef.AppBrighton.byName(nm)
        , () -> AppCDef.AppBrighton.listAll(), gp -> AppCDef.AppBrighton.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of reference to namedcls, case1 */
        AppDockside(cd -> AppCDef.AppDockside.of(cd), nm -> AppCDef.AppDockside.byName(nm)
        , () -> AppCDef.AppDockside.listAll(), gp -> AppCDef.AppDockside.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of reference to namedcls, case2 */
        AppHangar(cd -> AppCDef.AppHangar.of(cd), nm -> AppCDef.AppHangar.byName(nm)
        , () -> AppCDef.AppHangar.listAll(), gp -> AppCDef.AppHangar.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of reference to namedcls, exists */
        AppMagiclamp(cd -> AppCDef.AppMagiclamp.of(cd), nm -> AppCDef.AppMagiclamp.byName(nm)
        , () -> AppCDef.AppMagiclamp.listAll(), gp -> AppCDef.AppMagiclamp.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of reference to namedcls, matches */
        AppBroadway(cd -> AppCDef.AppBroadway.of(cd), nm -> AppCDef.AppBroadway.byName(nm)
        , () -> AppCDef.AppBroadway.listAll(), gp -> AppCDef.AppBroadway.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of including sub-item and sisters when implicit classification */
        AppFlg(cd -> AppCDef.AppFlg.of(cd), nm -> AppCDef.AppFlg.byName(nm)
        , () -> AppCDef.AppFlg.listAll(), gp -> AppCDef.AppFlg.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of including grouping map when implicit classification */
        AppPaymentMethod(cd -> AppCDef.AppPaymentMethod.of(cd), nm -> AppCDef.AppPaymentMethod.byName(nm)
        , () -> AppCDef.AppPaymentMethod.listAll(), gp -> AppCDef.AppPaymentMethod.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of groupingMap when literal only */
        DeepWxLiteralGrouping(cd -> AppCDef.DeepWxLiteralGrouping.of(cd), nm -> AppCDef.DeepWxLiteralGrouping.byName(nm)
        , () -> AppCDef.DeepWxLiteralGrouping.listAll(), gp -> AppCDef.DeepWxLiteralGrouping.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of deprecated classification top */
        DeepWxDeprecatedCls(cd -> AppCDef.DeepWxDeprecatedCls.of(cd), nm -> AppCDef.DeepWxDeprecatedCls.byName(nm)
        , () -> AppCDef.DeepWxDeprecatedCls.listAll(), gp -> AppCDef.DeepWxDeprecatedCls.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of deprecatedMap */
        DeepWxDeprecatedElement(cd -> AppCDef.DeepWxDeprecatedElement.of(cd), nm -> AppCDef.DeepWxDeprecatedElement.byName(nm)
        , () -> AppCDef.DeepWxDeprecatedElement.listAll(), gp -> AppCDef.DeepWxDeprecatedElement.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** test of path parameter by nameof() in LastaFlute action */
        AppWxNameOf(cd -> AppCDef.AppWxNameOf.of(cd), nm -> AppCDef.AppWxNameOf.byName(nm)
        , () -> AppCDef.AppWxNameOf.listAll(), gp -> AppCDef.AppWxNameOf.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING);

        private static final Map<String, DefMeta> _nameMetaMap = new HashMap<>();
        static {
            for (DefMeta value : values()) {
                _nameMetaMap.put(value.name().toLowerCase(), value);
            }
        }
        private final Function<Object, OptionalThing<? extends Classification>> _ofCall;
        private final Function<String, OptionalThing<? extends Classification>> _byNameCall;
        private final Supplier<List<? extends Classification>> _listAllCall;
        private final Function<String, List<? extends Classification>> _listByGroupCall;
        private final ClassificationCodeType _codeType;
        private final ClassificationUndefinedHandlingType _undefinedHandlingType;
        private DefMeta(Function<Object, OptionalThing<? extends Classification>> ofCall
                      , Function<String, OptionalThing<? extends Classification>> byNameCall
                      , Supplier<List<? extends Classification>> listAllCall
                      , Function<String, List<? extends Classification>> listByGroupCall
                      , ClassificationCodeType codeType
                      , ClassificationUndefinedHandlingType undefinedHandlingType
                ) {
            _ofCall = ofCall;
            _byNameCall = byNameCall;
            _listAllCall = listAllCall;
            _listByGroupCall = listByGroupCall;
            _codeType = codeType;
            _undefinedHandlingType = undefinedHandlingType;
        }
        public String classificationName() { return name(); } // same as definition name

        public OptionalThing<? extends Classification> of(Object code) { return _ofCall.apply(code); }
        public OptionalThing<? extends Classification> byName(String name) { return _byNameCall.apply(name); }

        public Classification codeOf(Object code) // null allowed, old style
        { return of(code).orElse(null); }
        public Classification nameOf(String name) { // null allowed, old style
            if (name == null) { return null; } // for compatible
            return byName(name).orElse(null); // case insensitive
        }

        public List<Classification> listAll()
        { return toClsList(_listAllCall.get()); }
        public List<Classification> listByGroup(String groupName) // exception if not found
        { return toClsList(_listByGroupCall.apply(groupName)); }

        @SuppressWarnings("unchecked")
        private List<Classification> toClsList(List<?> clsList) { return (List<Classification>)clsList; }

        public List<Classification> listOf(Collection<String> codeList) { // copied from slimmer, old style
            if (codeList == null) {
                throw new IllegalArgumentException("The argument 'codeList' should not be null.");
            }
            List<Classification> clsList = new ArrayList<>(codeList.size());
            for (String code : codeList) {
                clsList.add(of(code).get());
            }
            return clsList;
        }
        public List<Classification> groupOf(String groupName) { // empty if not found, old style
            try {
                return listByGroup(groupName); // case insensitive
            } catch (IllegalArgumentException | ClassificationNotFoundException e) {
                return new ArrayList<>();
            }
        }

        public ClassificationCodeType codeType() { return _codeType; }
        public ClassificationUndefinedHandlingType undefinedHandlingType() { return _undefinedHandlingType; }

        public static OptionalThing<AppCDef.DefMeta> find(String classificationName) { // instead of valueOf()
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            return OptionalThing.ofNullable(_nameMetaMap.get(classificationName.toLowerCase()), () -> {
                throw new ClassificationNotFoundException("Unknown classification: " + classificationName);
            });
        }
        public static AppCDef.DefMeta meta(String classificationName) { // old style so use find(name)
            return find(classificationName).orElseTranslatingThrow(cause -> {
                return new IllegalStateException("Unknown classification: " + classificationName);
            });
        }
    }

    public static class ZzzoneSlimmer<CLS extends AppCDef> {

        public static Set<String> toSisterSet(String[] sisters) { // used by initializer so static
            return Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(sisters)));
        }

        private final Class<CLS> _clsType;
        private final Map<String, CLS> _codeClsMap = new HashMap<>();
        private final Map<String, CLS> _nameClsMap = new HashMap<>();

        public ZzzoneSlimmer(Class<CLS> clsType, CLS[] values) {
            _clsType = clsType;
            initMap(values);
        }

        private void initMap(CLS[] values) {
            for (CLS value : values) {
                _codeClsMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisterSet()) {
                    _codeClsMap.put(sister.toLowerCase(), value);
                }
                _nameClsMap.put(value.name().toLowerCase(), value);
            }
        }

        public OptionalThing<CLS> of(Object code) {
            if (code == null) {
                return OptionalThing.ofNullable(null, () -> {
                    throw new ClassificationNotFoundException("null code specified");
                });
            }
            if (_clsType.isAssignableFrom(code.getClass())) {
                @SuppressWarnings("unchecked")
                CLS cls = (CLS) code;
                return OptionalThing.of(cls);
            }
            if (code instanceof OptionalThing<?>) {
                return of(((OptionalThing<?>) code).orElse(null));
            }
            return OptionalThing.ofNullable(_codeClsMap.get(code.toString().toLowerCase()), () -> {
                throw new ClassificationNotFoundException("Unknown classification code: " + code);
            });
        }

        public OptionalThing<CLS> byName(String name) {
            if (name == null) {
                throw new IllegalArgumentException("The argument 'name' should not be null.");
            }
            return OptionalThing.ofNullable(_nameClsMap.get(name.toLowerCase()), () -> {
                throw new ClassificationNotFoundException("Unknown classification name: " + name);
            });
        }

        public CLS codeOf(Object code) {
            if (code == null) {
                return null;
            }
            if (_clsType.isAssignableFrom(code.getClass())) {
                @SuppressWarnings("unchecked")
                CLS cls = (CLS) code;
                return cls;
            }
            return _codeClsMap.get(code.toString().toLowerCase());
        }

        public CLS nameOf(String name, java.util.function.Function<String, CLS> valueOfCall) {
            if (name == null) {
                return null;
            }
            try {
                return valueOfCall.apply(name);
            } catch (RuntimeException ignored) { // not found
                return null;
            }
        }

        public List<CLS> listAll(CLS[] clss) {
            return new ArrayList<>(Arrays.asList(clss));
        }

        public List<CLS> listOf(Collection<String> codeList) {
            if (codeList == null) {
                throw new IllegalArgumentException("The argument 'codeList' should not be null.");
            }
            List<CLS> clsList = new ArrayList<>(codeList.size());
            for (String code : codeList) {
                clsList.add(of(code).get());
            }
            return clsList;
        }
    }
}
