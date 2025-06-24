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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.flink.annotation.Internal;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.cache.DistributedCache;
import org.apache.flink.api.dag.Transformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.DeploymentOptions;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.core.execution.PipelineExecutor;
import org.apache.flink.core.execution.PipelineExecutorFactory;
import org.apache.flink.core.execution.PipelineExecutorServiceLoader;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.graph.StreamGraph;
import org.apache.flink.streaming.api.graph.StreamGraphGenerator;
import org.dbflute.utflute.core.PlainTestCase;
import org.docksidestage.flink.extended.ChouLocalExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.util.StatusPrinter;

/**
 * @author jflute
 */
public class FlinkFirstTest extends PlainTestCase {

    private static final Logger logger = LoggerFactory.getLogger(FlinkFirstTest.class);

    public void test_execute_flink_first() throws Exception {
        Configuration configuration = new Configuration(); // instance switched later
        configuration.set(DeploymentOptions.TARGET, "umeyan"); // needed
        configuration.set(DeploymentOptions.ATTACHED, true); // needed

        StreamExecutionEnvironment env = createStreamExecutionEnvironment(configuration);
        env.setParallelism(3);

        DataStreamSource<String> source = env.fromData("sea", "land", "piari"); // has env
        @SuppressWarnings("unused")
        SingleOutputStreamOperator<String> operator = source.map(text -> {
            logger.debug("toUpperCase(): text={}, thread={}", text, Thread.currentThread().hashCode());
            // to show callers
            //new RuntimeException().printStackTrace();
            return text.toUpperCase(); // 業務処理のつもり
        }).map(text -> {
            logger.debug("plus +: text={}, thread={}", text, Thread.currentThread().hashCode());
            return text + "+"; // 業務処理のつもり
        });

        /*
        [Map -> Map (2/3)#0] DEBUG (FlinkFirstTest@lambda$0():65) - toUpperCase(): text=piari, thread=963975210
        [Map -> Map (3/3)#0] DEBUG (FlinkFirstTest@lambda$0():65) - toUpperCase(): text=sea, thread=2063706169
        [Map -> Map (1/3)#0] DEBUG (FlinkFirstTest@lambda$0():65) - toUpperCase(): text=land, thread=64334334
        [Map -> Map (2/3)#0] DEBUG (FlinkFirstTest@lambda$1():70) - plus +: text=PIARI, thread=963975210
        [Map -> Map (3/3)#0] DEBUG (FlinkFirstTest@lambda$1():70) - plus +: text=SEA, thread=2063706169
        [Map -> Map (1/3)#0] DEBUG (FlinkFirstTest@lambda$1():70) - plus +: text=LAND, thread=64334334
         */
        // print()すると、内容のログが出る。ここでログが出るわけじゃなくログを予約してるのかな？
        // 実行に必須ではない。print()しなくても、operatorたちに仕込んだログは出力されるので実行はされている。
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
        PipelineExecutorServiceLoader loader = createPipelineExecutorServiceLoader();
        return new StreamExecutionEnvironment(loader, configuration, ClassLoader.getSystemClassLoader()) {

            @Internal
            public StreamGraph getStreamGraph(boolean clearTransformations) {
                final StreamGraph streamGraph = getStreamGraph(transformations);
                if (clearTransformations) {
                    transformations.clear();
                }
                return streamGraph;
            }

            private StreamGraph getStreamGraph(List<Transformation<?>> transformations) {
                // cannot call by private
                //synchronizeClusterDatasetStatus();
                return getStreamGraphGenerator(transformations).generate();
            }

            private StreamGraphGenerator getStreamGraphGenerator(List<Transformation<?>> transformations) {
                if (transformations.size() <= 0) {
                    throw new IllegalStateException("No operators defined in streaming topology. Cannot execute.");
                }

                // Synchronize the cached file to config option PipelineOptions.CACHED_FILES because the
                // field cachedFile haven't been migrated to configuration.
                if (!getCachedFiles().isEmpty()) {
                    configuration.set(PipelineOptions.CACHED_FILES, DistributedCache.parseStringFromCachedFiles(getCachedFiles()));
                }

                // We copy the transformation so that newly added transformations cannot intervene with the
                // stream graph generation.
                StreamGraphGenerator generator =
                        new StreamGraphGenerator(new ArrayList<>(transformations), config, checkpointCfg, configuration) {

                        };
                return generator
                        // cannot use by private
                        //.setSlotSharingGroupResource(slotSharingGroupResources);
                        .setSlotSharingGroupResource(new HashMap<>());
            }
        };
    }

    private PipelineExecutorServiceLoader createPipelineExecutorServiceLoader() {
        return new PipelineExecutorServiceLoader() {
            @Override
            public PipelineExecutorFactory getExecutorFactory(Configuration configuration) throws Exception {
                return createPipelineExecutorFactory(configuration);
            }

            public Stream<String> getExecutorNames() { // not called if local executor?
                throw new IllegalStateException("getExecutorNames() called");
            }
        };
    }

    private PipelineExecutorFactory createPipelineExecutorFactory(Configuration configuration) {
        return new PipelineExecutorFactory() {
            @Override
            public boolean isCompatibleWith(Configuration configuration) {
                throw new IllegalStateException("isCompatibleWith() called");
            }

            @Override
            public String getName() {
                throw new IllegalStateException("getName() called");
            }

            @Override
            public PipelineExecutor getExecutor(Configuration configuration) {
                return ChouLocalExecutor.create(configuration);
            }
        };
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
