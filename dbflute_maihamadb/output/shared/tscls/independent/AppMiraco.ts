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
 * test of manual sub-item and sisters as exists, expects new only here
 */
public enum AppMiraco {
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
     * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
     * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
     */
    public static OptionalThing<AppMiraco> fromDBCls(CDef.MemberStatus dbCls) {
        String dbCode = dbCls != null ? dbCls.code() : null;
        return OptionalThing.ofNullable(codeOf(dbCode), () -> {
            throw new IllegalStateException("Cannot convert CDef.MemberStatus to AppMiraco by the DB code: " + dbCode);
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
