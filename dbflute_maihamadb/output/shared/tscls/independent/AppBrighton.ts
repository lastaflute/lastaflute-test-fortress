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
