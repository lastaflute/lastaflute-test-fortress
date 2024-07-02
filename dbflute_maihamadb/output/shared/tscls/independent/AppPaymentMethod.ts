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
