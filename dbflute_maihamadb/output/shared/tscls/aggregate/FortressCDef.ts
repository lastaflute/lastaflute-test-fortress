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


/**
 * test of no theme refCls, expects DB cls
 */
class AppMaihama {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new AppMaihama("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new AppMaihama("WDL", "Withdrawal");

    /** Provisional: first status after entry, allowed to use only part of service */
    static readonly Provisional = new AppMaihama("PRV", "Provisional");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppMaihama> {
        const allList: Array<AppMaihama> = new Array<AppMaihama>();
        allList.push(AppMaihama.Formalized);
        allList.push(AppMaihama.Withdrawal);
        allList.push(AppMaihama.Provisional);
        return allList;
    }
}

/**
 * test of included, expects grouping, sub-item, sisters
 */
class AppSea {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new AppSea("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new AppSea("WDL", "Withdrawal");

    /** Provisional: first status after entry, allowed to use only part of service */
    static readonly Provisional = new AppSea("PRV", "Provisional");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppSea> {
        const allList: Array<AppSea> = new Array<AppSea>();
        allList.push(AppSea.Formalized);
        allList.push(AppSea.Withdrawal);
        allList.push(AppSea.Provisional);
        return allList;
    }
}

/**
 * test of exists, expects minimum grouping, no sub-item, sisters
 */
class AppLand {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppLand("FML", "ShowBase");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppLand("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppLand> {
        const allList: Array<AppLand> = new Array<AppLand>();
        allList.push(AppLand.OneMan);
        allList.push(AppLand.MiniO);
        return allList;
    }
}

/**
 * test of matches, expects minimum grouping, no sub-item, sisters
 */
class AppPiari {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppPiari("FML", "ShowBase");

    /** Castle: Provisional */
    static readonly Parade = new AppPiari("PRV", "Castle");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppPiari("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppPiari> {
        const allList: Array<AppPiari> = new Array<AppPiari>();
        allList.push(AppPiari.OneMan);
        allList.push(AppPiari.Parade);
        allList.push(AppPiari.MiniO);
        return allList;
    }
}

/**
 * test of manual grouping map using refCls as included, expects overridden, added
 */
class AppBonvo {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new AppBonvo("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new AppBonvo("WDL", "Withdrawal");

    /** Provisional: first status after entry, allowed to use only part of service */
    static readonly Provisional = new AppBonvo("PRV", "Provisional");

    /** Hangar: Rhythms */
    static readonly Mystic = new AppBonvo("MYS", "Hangar");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppBonvo> {
        const allList: Array<AppBonvo> = new Array<AppBonvo>();
        allList.push(AppBonvo.Formalized);
        allList.push(AppBonvo.Withdrawal);
        allList.push(AppBonvo.Provisional);
        allList.push(AppBonvo.Mystic);
        return allList;
    }
}

/**
 * test of manual grouping map using refCls as exists, expects new grouping is available
 */
class AppDstore {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppDstore("FML", "ShowBase");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppDstore("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppDstore> {
        const allList: Array<AppDstore> = new Array<AppDstore>();
        allList.push(AppDstore.OneMan);
        allList.push(AppDstore.MiniO);
        return allList;
    }
}

/**
 * test of manual sub-item and sisters as included, expects merged, only order() exists
 */
class AppAmba {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new AppAmba("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new AppAmba("WDL", "Withdrawal");

    /** Provisional: first status after entry, allowed to use only part of service */
    static readonly Provisional = new AppAmba("PRV", "Provisional");

    /** Hangar: Rhythms */
    static readonly Mystic = new AppAmba("MYS", "Hangar");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppAmba> {
        const allList: Array<AppAmba> = new Array<AppAmba>();
        allList.push(AppAmba.Formalized);
        allList.push(AppAmba.Withdrawal);
        allList.push(AppAmba.Provisional);
        allList.push(AppAmba.Mystic);
        return allList;
    }
}

/**
 * test of manual sub-item and sisters as exists, expects new only here
 */
class AppMiraco {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppMiraco("FML", "ShowBase");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppMiraco("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppMiraco> {
        const allList: Array<AppMiraco> = new Array<AppMiraco>();
        allList.push(AppMiraco.OneMan);
        allList.push(AppMiraco.MiniO);
        return allList;
    }
}

/**
 * test of included with overriding, expected merged
 */
class AppDohotel {

    /** Formalized: as formal member, allowed to use all service */
    static readonly OneMan = new AppDohotel("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new AppDohotel("WDL", "Withdrawal");

    /** Castle: ParadeTwoYears */
    static readonly Provisional = new AppDohotel("PRV", "Castle");

    /** Hangar: Rhythms */
    static readonly Mystic = new AppDohotel("MYS", "Hangar");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppDohotel> {
        const allList: Array<AppDohotel> = new Array<AppDohotel>();
        allList.push(AppDohotel.OneMan);
        allList.push(AppDohotel.Withdrawal);
        allList.push(AppDohotel.Provisional);
        allList.push(AppDohotel.Mystic);
        return allList;
    }
}

/**
 * test of exists with inheriting, expected merged
 */
class AppAmphi {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new AppAmphi("FML", "Formalized");

    /** Castle: ParadeTwoYears */
    static readonly Provisional = new AppAmphi("PRV", "Castle");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppAmphi> {
        const allList: Array<AppAmphi> = new Array<AppAmphi>();
        allList.push(AppAmphi.Formalized);
        allList.push(AppAmphi.Provisional);
        return allList;
    }
}

/**
 * test of matches with inheriting, expected merged
 */
class AppOrien {

    /** Formalized: as formal member, allowed to use all service */
    static readonly OneMan = new AppOrien("FML", "Formalized");

    /** Orleans */
    static readonly MiniO = new AppOrien("WDL", "Orleans");

    /** Castle: first status after entry, allowed to use only part of service */
    static readonly Provisional = new AppOrien("PRV", "Castle");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppOrien> {
        const allList: Array<AppOrien> = new Array<AppOrien>();
        allList.push(AppOrien.OneMan);
        allList.push(AppOrien.MiniO);
        allList.push(AppOrien.Provisional);
        return allList;
    }
}

/**
 * test of referring group as included, expects grouped elements only and sub-item, sisters exist
 */
class AppCeleb {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new AppCeleb("FML", "Formalized");

    /** Provisional: first status after entry, allowed to use only part of service */
    static readonly Provisional = new AppCeleb("PRV", "Provisional");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppCeleb> {
        const allList: Array<AppCeleb> = new Array<AppCeleb>();
        allList.push(AppCeleb.Formalized);
        allList.push(AppCeleb.Provisional);
        return allList;
    }
}

/**
 * test of referring group as exists, expects grouped elements only and existence checked
 */
class AppToys {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppToys("FML", "ShowBase");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppToys> {
        const allList: Array<AppToys> = new Array<AppToys>();
        allList.push(AppToys.OneMan);
        return allList;
    }
}

/**
 * test of referring group as matches, expects grouped elements only and matching checked
 */
class AppBrighton {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppBrighton("FML", "ShowBase");

    /** Castle: Provisional */
    static readonly Parade = new AppBrighton("PRV", "Castle");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppBrighton> {
        const allList: Array<AppBrighton> = new Array<AppBrighton>();
        allList.push(AppBrighton.OneMan);
        allList.push(AppBrighton.Parade);
        return allList;
    }
}

/**
 * test of reference to namedcls, case1
 */
class AppDockside {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new AppDockside("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new AppDockside("WDL", "Withdrawal");

    /** Provisional: first status after entry, allowed to use only part of service */
    static readonly Provisional = new AppDockside("PRV", "Provisional");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppDockside> {
        const allList: Array<AppDockside> = new Array<AppDockside>();
        allList.push(AppDockside.Formalized);
        allList.push(AppDockside.Withdrawal);
        allList.push(AppDockside.Provisional);
        return allList;
    }
}

/**
 * test of reference to namedcls, case2
 */
class AppHangar {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppHangar("FML", "ShowBase");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppHangar("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppHangar> {
        const allList: Array<AppHangar> = new Array<AppHangar>();
        allList.push(AppHangar.OneMan);
        allList.push(AppHangar.MiniO);
        return allList;
    }
}

/**
 * test of reference to namedcls, exists
 */
class AppMagiclamp {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppMagiclamp("FML", "ShowBase");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppMagiclamp("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppMagiclamp> {
        const allList: Array<AppMagiclamp> = new Array<AppMagiclamp>();
        allList.push(AppMagiclamp.OneMan);
        allList.push(AppMagiclamp.MiniO);
        return allList;
    }
}

/**
 * test of reference to namedcls, matches
 */
class AppBroadway {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppBroadway("FML", "ShowBase");

    /** Dstore: Provisional */
    static readonly Dstore = new AppBroadway("PRV", "Dstore");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppBroadway("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppBroadway> {
        const allList: Array<AppBroadway> = new Array<AppBroadway>();
        allList.push(AppBroadway.OneMan);
        allList.push(AppBroadway.Dstore);
        allList.push(AppBroadway.MiniO);
        return allList;
    }
}

/**
 * test of including sub-item and sisters when implicit classification
 */
class AppFlg {

    /** Checked: means yes */
    static readonly True = new AppFlg("1", "Checked");

    /** Unchecked: means no */
    static readonly False = new AppFlg("0", "Unchecked");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppFlg> {
        const allList: Array<AppFlg> = new Array<AppFlg>();
        allList.push(AppFlg.True);
        allList.push(AppFlg.False);
        return allList;
    }
}

/**
 * test of including grouping map when implicit classification
 */
class AppPaymentMethod {

    /** by hand: payment by hand, face-to-face */
    static readonly ByHand = new AppPaymentMethod("HAN", "by hand");

    /** bank transfer: bank transfer payment */
    static readonly BankTransfer = new AppPaymentMethod("BAK", "bank transfer");

    /** credit card: credit card payment */
    static readonly CreditCard = new AppPaymentMethod("CRC", "credit card");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppPaymentMethod> {
        const allList: Array<AppPaymentMethod> = new Array<AppPaymentMethod>();
        allList.push(AppPaymentMethod.ByHand);
        allList.push(AppPaymentMethod.BankTransfer);
        allList.push(AppPaymentMethod.CreditCard);
        return allList;
    }
}

/**
 * test of groupingMap when literal only
 */
class DeepWxLiteralGrouping {

    /** ShowBase: Formalized */
    static readonly OneMan = new DeepWxLiteralGrouping("FML", "ShowBase");

    /** Castle: Provisional */
    static readonly Parade = new DeepWxLiteralGrouping("PRV", "Castle");

    /** Orlean: Withdrawal */
    static readonly MiniO = new DeepWxLiteralGrouping("WDL", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<DeepWxLiteralGrouping> {
        const allList: Array<DeepWxLiteralGrouping> = new Array<DeepWxLiteralGrouping>();
        allList.push(DeepWxLiteralGrouping.OneMan);
        allList.push(DeepWxLiteralGrouping.Parade);
        allList.push(DeepWxLiteralGrouping.MiniO);
        return allList;
    }
}

/**
 * test of deprecated classification top
 */
class DeepWxDeprecatedCls {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new DeepWxDeprecatedCls("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new DeepWxDeprecatedCls("WDL", "Withdrawal");

    /** Provisional: first status after entry, allowed to use only part of service */
    static readonly Provisional = new DeepWxDeprecatedCls("PRV", "Provisional");

    /** All Statuses: without status filter */
    static readonly All = new DeepWxDeprecatedCls("ALL", "All Statuses");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<DeepWxDeprecatedCls> {
        const allList: Array<DeepWxDeprecatedCls> = new Array<DeepWxDeprecatedCls>();
        allList.push(DeepWxDeprecatedCls.Formalized);
        allList.push(DeepWxDeprecatedCls.Withdrawal);
        allList.push(DeepWxDeprecatedCls.Provisional);
        allList.push(DeepWxDeprecatedCls.All);
        return allList;
    }
}

/**
 * test of deprecatedMap
 */
class DeepWxDeprecatedElement {

    /** Formalized: as formal member, allowed to use all service */
    static readonly Formalized = new DeepWxDeprecatedElement("FML", "Formalized");

    /** Withdrawal: withdrawal is fixed, not allowed to use service */
    static readonly Withdrawal = new DeepWxDeprecatedElement("WDL", "Withdrawal");

    /** Provisional: first status after entry, allowed to use only part of service (deprecated: test of deprecated) */
    // @Deprecated
    static readonly Provisional = new DeepWxDeprecatedElement("PRV", "Provisional");

    /** All Statuses: without status filter (deprecated: and also test of deprecated) */
    // @Deprecated
    static readonly All = new DeepWxDeprecatedElement("ALL", "All Statuses");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<DeepWxDeprecatedElement> {
        const allList: Array<DeepWxDeprecatedElement> = new Array<DeepWxDeprecatedElement>();
        allList.push(DeepWxDeprecatedElement.Formalized);
        allList.push(DeepWxDeprecatedElement.Withdrawal);
        allList.push(DeepWxDeprecatedElement.Provisional);
        allList.push(DeepWxDeprecatedElement.All);
        return allList;
    }
}

/**
 * test of path parameter by nameof() in LastaFlute action
 */
class AppWxNameOf {

    /** ShowBase: Formalized */
    static readonly OneMan = new AppWxNameOf("ONE", "ShowBase");

    /** Orlean: Withdrawal */
    static readonly MiniO = new AppWxNameOf("MIN", "Orlean");

    readonly code: string;
    readonly alias: string;

    constructor(code: string, alias: string) {
        this.code = code;
        this.alias = alias;
    }

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The snapshot list of all classification elements. (NotNull)
     */
    static listAll(): Array<AppWxNameOf> {
        const allList: Array<AppWxNameOf> = new Array<AppWxNameOf>();
        allList.push(AppWxNameOf.OneMan);
        allList.push(AppWxNameOf.MiniO);
        return allList;
    }
}
