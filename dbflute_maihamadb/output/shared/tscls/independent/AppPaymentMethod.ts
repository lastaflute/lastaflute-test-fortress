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
 * test of including grouping map when implicit classification
 */
public enum AppPaymentMethod {
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
     * @param dbCls The DB classification to find. (NullAllowed: if null, returns empty)
     * @return The the app classification corresponding to the DB classification. (NotNull, EmptyAllowed: when null specified, not found)
     */
    public static OptionalThing<AppPaymentMethod> fromDBCls(CDef.PaymentMethod dbCls) {
        String dbCode = dbCls != null ? dbCls.code() : null;
        return OptionalThing.ofNullable(codeOf(dbCode), () -> {
            throw new IllegalStateException("Cannot convert CDef.PaymentMethod to AppPaymentMethod by the DB code: " + dbCode);
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
