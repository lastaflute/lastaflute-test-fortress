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
