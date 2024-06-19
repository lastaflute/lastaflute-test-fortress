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
