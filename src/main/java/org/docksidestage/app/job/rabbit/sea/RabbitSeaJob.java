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
package org.docksidestage.app.job.rabbit.sea;

import javax.annotation.Resource;

import org.docksidestage.app.job.rabbit.base.RabbitJobBase;
import org.docksidestage.bizfw.rabbitmq.job.RabbitJobResource;
import org.docksidestage.dbflute.exbhv.MemberBhv;
import org.lastaflute.db.jta.stage.TransactionStage;
import org.lastaflute.job.LaJobRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class RabbitSeaJob extends RabbitJobBase<RabbitSeaMessageBody> {

    private static final Logger logger = LoggerFactory.getLogger(RabbitSeaJob.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @Resource
    private TransactionStage stage;
    @Resource
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                             Job Run
    //                                                                             =======
    @Override
    protected void doRun(LaJobRuntime runtime, RabbitJobResource jobResource, RabbitSeaMessageBody messageBody) {
        // #rabbit jflute ここにメッセージを受け取った場合の処理を書く (2025/01/17)
        logger.debug("お試しログ: " + messageBody.stageName + ", " + messageBody.oneDayShowCount);
    }

    @Override
    protected Class<RabbitSeaMessageBody> getMessageJsonType() {
        // #rabbit jflute メッセージ文字列をJSONとして受け取るためのクラスの型を指定する (2025/01/17)
        return RabbitSeaMessageBody.class;
    }
}
