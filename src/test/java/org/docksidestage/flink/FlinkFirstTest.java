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
package org.docksidestage.flink;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.client.deployment.executors.LocalExecutorFactory;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.DeploymentOptions;
import org.apache.flink.core.execution.PipelineExecutorFactory;
import org.apache.flink.core.execution.PipelineExecutorServiceLoader;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.dbflute.utflute.core.PlainTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.util.StatusPrinter;

/**
 * @author jflute
 */
public class FlinkFirstTest extends PlainTestCase {

    private static final Logger logger = LoggerFactory.getLogger(FlinkFirstTest.class);

    public void test_execute_flink_first() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set(DeploymentOptions.TARGET, "umeyan"); // needed
        configuration.set(DeploymentOptions.ATTACHED, true); // needed

        StreamExecutionEnvironment env = createStreamExecutionEnvironment(configuration);
        DataStreamSource<String> source = env.fromData("sea", "land", "piari"); // has env
        SingleOutputStreamOperator<String> operator = source.map(text -> {
            logger.debug("toUpperCase(): thread={}", Thread.currentThread().hashCode());
            // to show callers
            //new RuntimeException().printStackTrace();
            return text.toUpperCase(); // 業務処理のつもり
        }).map(text -> {
            logger.debug("plus +: thread={}", Thread.currentThread().hashCode());
            return text + "+"; // 業務処理のつもり
        });

        /*
        2025-06-10 18:27:18,231 [Source: Collection Source -> Map -> (Sink: Print to Std. Out, Sink: Print to Std. Out) (1/1)#0] DEBUG (QuickTest@lambda$0():64) - aaaaa
        SEA
        SEA
        2025-06-10 18:27:18,231 [Source: Collection Source -> Map -> (Sink: Print to Std. Out, Sink: Print to Std. Out) (1/1)#0] DEBUG (QuickTest@lambda$0():64) - aaaaa
        LAND
        LAND
        2025-06-10 18:27:18,231 [Source: Collection Source -> Map -> (Sink: Print to Std. Out, Sink: Print to Std. Out) (1/1)#0] DEBUG (QuickTest@lambda$0():64) - aaaaa
        PIARI
        PIARI
         */
        operator.print(); // ここでログが出るわけじゃない、ログを出す処理を予約している？ (実行に必須ではない)
        //operator.print();

        JobExecutionResult result = env.execute("maihama"); // ここで実際に実行する (期待値: ログが出る)
        log(result);

        // Apache Flink の Backpressure の仕組みについて調べた
        // https://soonraah.github.io/posts/backpressure-for-flink/
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private StreamExecutionEnvironment createStreamExecutionEnvironment(Configuration configuration) {
        return new StreamExecutionEnvironment(new PipelineExecutorServiceLoader() {
            @Override
            public PipelineExecutorFactory getExecutorFactory(Configuration configuration) throws Exception {
                return createPipelineExecutorFactory(configuration);
            }

            public Stream<String> getExecutorNames() { // not called if local executor?
                throw new IllegalStateException("getExecutorNames() called");
            }
        }, configuration, ClassLoader.getSystemClassLoader());
    }

    // TODO jflute LocalExecutorFactoryのThreadの管理を見てみたい (2025/06/10)
    private PipelineExecutorFactory createPipelineExecutorFactory(Configuration configuration) {
        return new LocalExecutorFactory();
    }

    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall
    //    // wall

    // does not need logback initialization logs
    static { // should be before logback initialization
        StatusPrinter.setPrintStream(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                // do nothing
            }
        }));
    }
}
