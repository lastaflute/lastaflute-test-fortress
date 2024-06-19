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
package org.docksidestage.mylasta.namedcls;

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

/**
 * The definition of leonardo classification.
 * @author FreeGen
 */
public interface LeonardoCDef extends Classification {

    /**
     * Sea Cls
     */
    public enum DaSea implements LeonardoCDef {
        /** Formalized: as formal member, allowed to use all service */
        Formalized("FML", "Formalized"),
        /** Withdrawal: withdrawal is fixed, not allowed to use service */
        Withdrawal("WDL", "Withdrawal"),
        /** Provisional: first status after entry, allowed to use only part of service */
        Provisional("PRV", "Provisional");
        private static ZzzoneSlimmer<DaSea> _slimmer = new ZzzoneSlimmer<>(DaSea.class, values());
        private String _code; private String _alias;
        private DaSea(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return LeonardoCDef.DefMeta.DaSea; }
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
        public static OptionalThing<DaSea> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DaSea> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static DaSea codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static DaSea nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DaSea> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<DaSea> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: DaSea." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<DaSea> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[Formalized, Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DaSea> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(Formalized, Provisional));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Provisional]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DaSea> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Provisional));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<DaSea> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<DaSea> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to DaSea by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert DaSea to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * Land Cls
     */
    public enum DaLand implements LeonardoCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<DaLand> _slimmer = new ZzzoneSlimmer<>(DaLand.class, values());
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
        private DaLand(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return _subItemMapMap.get(code()); }
        public ClassificationMeta meta() { return LeonardoCDef.DefMeta.DaLand; }
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
        public static OptionalThing<DaLand> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DaLand> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static DaLand codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static DaLand nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DaLand> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<DaLand> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            throw new ClassificationNotFoundException("Unknown classification group: DaLand." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<DaLand> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DaLand> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<DaLand> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<DaLand> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to DaLand by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert DaLand to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    /**
     * Piari Cls
     */
    public enum DaPiari implements LeonardoCDef {
        /** ShowBase: Formalized */
        OneMan("FML", "ShowBase"),
        /** Dstore: Provisional */
        Dstore("PRV", "Dstore"),
        /** Orlean: Withdrawal */
        MiniO("WDL", "Orlean");
        private static ZzzoneSlimmer<DaPiari> _slimmer = new ZzzoneSlimmer<>(DaPiari.class, values());
        private String _code; private String _alias;
        private DaPiari(String code, String alias) { _code = code; _alias = alias; }
        public String code() { return _code; } public String alias() { return _alias; }
        public Set<String> sisterSet() { return Collections.emptySet(); }
        public Map<String, Object> subItemMap() { return Collections.emptyMap(); }
        public ClassificationMeta meta() { return LeonardoCDef.DefMeta.DaPiari; }
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
        public static OptionalThing<DaPiari> of(Object code) { return _slimmer.of(code); }
        /**
         * Find the classification by the name. (CaseInsensitive)
         * @param name The string of name, which is case-insensitive. (NotNull)
         * @return The optional classification corresponding to the name. (NotNull, EmptyAllowed: if not found, returns empty)
         */
        public static OptionalThing<DaPiari> byName(String name) { return _slimmer.byName(name); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use of(code).</span>
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static DaPiari codeOf(Object code) { return _slimmer.codeOf(code); }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use byName(name).</span>
         * @param name The string of name, which is case-sensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the name. (NullAllowed: if not found, returns null)
         * @deprecated use byName(name) instead.
         */
        public static DaPiari nameOf(String name) { return _slimmer.nameOf(name, nm -> valueOf(nm)); }
        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The snapshot list of all classification elements. (NotNull)
         */
        public static List<DaPiari> listAll() { return _slimmer.listAll(values()); }
        /**
         * Get the list of classification elements in the specified group. (returns new copied list)
         * @param groupName The string of group name, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the group. (NotNull)
         * @throws ClassificationNotFoundException When the group is not found.
         */
        public static List<DaPiari> listByGroup(String groupName) {
            if (groupName == null) { throw new IllegalArgumentException("The argument 'groupName' should not be null."); }
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            throw new ClassificationNotFoundException("Unknown classification group: DaPiari." + groupName);
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use e.g. Stream API with of().</span>
         * @param codeList The list of plain code, which is case-insensitive. (NotNull)
         * @return The snapshot list of classification elements in the code list. (NotNull, EmptyAllowed: when empty specified)
         * @deprecated use e.g. Stream API with of() instead.
         */
        public static List<DaPiari> listOf(Collection<String> codeList) { return _slimmer.listOf(codeList); }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * means member that can use services <br>
         * The group elements:[OneMan, Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DaPiari> listOfServiceAvailable() {
            return new ArrayList<>(Arrays.asList(OneMan, Dstore));
        }
        /**
         * Get the list of group classification elements. (returns new copied list) <br>
         * Members are not formalized yet <br>
         * The group elements:[Dstore]
         * @return The snapshot list of classification elements in the group. (NotNull)
         */
        public static List<DaPiari> listOfShortOfFormalized() {
            return new ArrayList<>(Arrays.asList(Dstore));
        }
        /**
         * <span style="color: #AD4747; font-size: 120%">Old style so use listByGroup(groupName).</span>
         * @param groupName The string of group name, which is case-sensitive. (NullAllowed: if null, returns empty list)
         * @return The snapshot list of classification elements in the group. (NotNull, EmptyAllowed: if the group is not found)
         * @deprecated use listByGroup(groupName) instead.
         */
        public static List<DaPiari> groupOf(String groupName) {
            if ("serviceAvailable".equalsIgnoreCase(groupName)) { return listOfServiceAvailable(); }
            if ("shortOfFormalized".equalsIgnoreCase(groupName)) { return listOfShortOfFormalized(); }
            return new ArrayList<>();
        }
        /**
         * @param refCls The DB classification to find. (NullAllowed: if null, returns empty)
         * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
         */
        public static OptionalThing<DaPiari> fromDBCls(CDef.MemberStatus refCls) {
            String refCode = refCls != null ? refCls.code() : null;
            return OptionalThing.ofNullable(of(refCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert CDef.MemberStatus to DaPiari by the referred code: " + refCode);
            });
        }
        /**
         * @return The DB classification corresponding to the app classification. (NotNull, EmptyAllowed: when no-related to DB)
         */
        public OptionalThing<CDef.MemberStatus> toDBCls() {
            String appCode = code();
            return OptionalThing.ofNullable(CDef.MemberStatus.of(appCode).orElse(null), () -> {
                throw new IllegalStateException("Cannot convert DaPiari to MemberStatus by the app code: " + appCode);
            });
        }
        @Override public String toString() { return code(); }
    }

    public enum DefMeta implements ClassificationMeta {
        /** Sea Cls */
        DaSea(cd -> LeonardoCDef.DaSea.of(cd), nm -> LeonardoCDef.DaSea.byName(nm)
        , () -> LeonardoCDef.DaSea.listAll(), gp -> LeonardoCDef.DaSea.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** Land Cls */
        DaLand(cd -> LeonardoCDef.DaLand.of(cd), nm -> LeonardoCDef.DaLand.byName(nm)
        , () -> LeonardoCDef.DaLand.listAll(), gp -> LeonardoCDef.DaLand.listByGroup(gp)
        , ClassificationCodeType.String, ClassificationUndefinedHandlingType.LOGGING),

        /** Piari Cls */
        DaPiari(cd -> LeonardoCDef.DaPiari.of(cd), nm -> LeonardoCDef.DaPiari.byName(nm)
        , () -> LeonardoCDef.DaPiari.listAll(), gp -> LeonardoCDef.DaPiari.listByGroup(gp)
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

        public static OptionalThing<LeonardoCDef.DefMeta> find(String classificationName) { // instead of valueOf()
            if (classificationName == null) { throw new IllegalArgumentException("The argument 'classificationName' should not be null."); }
            return OptionalThing.ofNullable(_nameMetaMap.get(classificationName.toLowerCase()), () -> {
                throw new ClassificationNotFoundException("Unknown classification: " + classificationName);
            });
        }
        public static LeonardoCDef.DefMeta meta(String classificationName) { // old style so use find(name)
            return find(classificationName).orElseTranslatingThrow(cause -> {
                return new IllegalStateException("Unknown classification: " + classificationName);
            });
        }
    }

    public static class ZzzoneSlimmer<CLS extends LeonardoCDef> {

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
