/*
 * Copyright 2015-2022 the original author or authors.
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
