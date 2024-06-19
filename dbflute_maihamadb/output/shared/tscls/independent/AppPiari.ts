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
