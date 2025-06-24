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
package org.docksidestage.flink.extended;

import static org.apache.flink.util.Preconditions.checkNotNull;
import static org.apache.flink.util.Preconditions.checkState;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import org.apache.flink.annotation.Internal;
import org.apache.flink.api.dag.Pipeline;
import org.apache.flink.client.deployment.executors.LocalExecutor;
import org.apache.flink.client.deployment.executors.PipelineExecutorUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.DeploymentOptions;
import org.apache.flink.core.execution.JobClient;
import org.apache.flink.core.execution.JobStatusChangedListener;
import org.apache.flink.core.execution.JobStatusChangedListenerUtils;
import org.apache.flink.core.execution.PipelineExecutor;
import org.apache.flink.runtime.minicluster.MiniCluster;
import org.apache.flink.runtime.minicluster.MiniClusterConfiguration;
import org.apache.flink.streaming.api.graph.StreamGraph;
import org.apache.flink.util.concurrent.ExecutorThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author copied and modified by jflute
 */
@Internal
public class ChouLocalExecutor implements PipelineExecutor {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger LOG = LoggerFactory.getLogger(LocalExecutor.class);

    private static final int THREAD_COUNT = 2;
    public static final String NAME = "local";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Configuration configuration;
    private final Function<MiniClusterConfiguration, MiniCluster> miniClusterFactory;
    private final List<JobStatusChangedListener> jobStatusChangedListeners;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT, createThreadFactory());

    private ExecutorThreadFactory createThreadFactory() {
        return new ExecutorThreadFactory("Chou-Flink-LocalExecutor-IO") {
            @Override
            public Thread newThread(Runnable runnable) {
                throw new IllegalStateException("Unused?: " + runnable);
            }
        };
    }

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public static ChouLocalExecutor create(Configuration configuration) {
        return new ChouLocalExecutor(configuration, miniConf -> new MiniCluster(miniConf));
    }

    public static ChouLocalExecutor createWithFactory(Configuration configuration,
            Function<MiniClusterConfiguration, MiniCluster> miniClusterFactory) {
        return new ChouLocalExecutor(configuration, miniClusterFactory);
    }

    private ChouLocalExecutor(Configuration configuration, Function<MiniClusterConfiguration, MiniCluster> miniClusterFactory) {
        this.configuration = configuration;
        this.miniClusterFactory = miniClusterFactory;
        this.jobStatusChangedListeners = JobStatusChangedListenerUtils
                .createJobStatusChangedListeners(Thread.currentThread().getContextClassLoader(), configuration, executorService);
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @Override
    public CompletableFuture<JobClient> execute(Pipeline pipeline, Configuration configuration, ClassLoader userCodeClassloader)
            throws Exception {
        checkNotNull(pipeline);
        checkNotNull(configuration);

        Configuration effectiveConfig = new Configuration();
        effectiveConfig.addAll(this.configuration);
        effectiveConfig.addAll(configuration);

        // we only support attached execution with the local executor.
        checkState(configuration.get(DeploymentOptions.ATTACHED));

        final StreamGraph streamGraph = PipelineExecutorUtils.getStreamGraph(pipeline, configuration);

        // これ消しても動く (というか、MiniClusterのsubmitJob()で呼ばれてる...二重呼び出しになってる？)
        //streamGraph.serializeUserDefinedInstances();

        return ChouPerJobMiniClusterFactory.createWithFactory(effectiveConfig, miniClusterFactory)
                .submitJob(streamGraph, userCodeClassloader)
                .whenComplete((ignored, throwable) -> {
                    if (throwable == null) {
                        PipelineExecutorUtils.notifyJobStatusListeners(pipeline, streamGraph, jobStatusChangedListeners);
                    } else {
                        LOG.error("Failed to submit job graph to local mini cluster.", throwable);
                    }
                });
    }
}
