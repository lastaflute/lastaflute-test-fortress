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
package independent;

import java.util.*;

import org.dbflute.exception.ClassificationNotFoundException;
import org.dbflute.optional.OptionalThing;
import static org.dbflute.util.DfTypeUtil.emptyStrings;
import org.docksidestage.dbflute.allcommon.*;

/**
 * test of manual grouping map using refCls as included, expects overridden, added
 */
public enum AppBonvo {
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
     * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
     * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
     */
    public static OptionalThing<AppBonvo> fromDBCls(CDef.MemberStatus dbCls) {
        String dbCode = dbCls != null ? dbCls.code() : null;
        return OptionalThing.ofNullable(codeOf(dbCode), () -> {
            throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppBonvo by the DB code: " + dbCode);
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
