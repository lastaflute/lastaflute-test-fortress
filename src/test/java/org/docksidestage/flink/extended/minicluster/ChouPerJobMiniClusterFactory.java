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
package org.docksidestage.flink.extended.minicluster;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.apache.flink.api.common.JobSubmissionResult;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.configuration.TaskManagerOptions;
import org.apache.flink.core.execution.JobClient;
import org.apache.flink.runtime.client.JobInitializationException;
import org.apache.flink.runtime.minicluster.MiniCluster;
import org.apache.flink.runtime.minicluster.RpcServiceSharing;
import org.apache.flink.streaming.api.graph.ExecutionPlan;
import org.apache.flink.util.MathUtils;
import org.apache.flink.util.function.FunctionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author copied and modified by jflute
 */
public class ChouPerJobMiniClusterFactory {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger LOG = LoggerFactory.getLogger(ChouPerJobMiniClusterFactory.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Configuration configuration;
    private final Function<? super ChouMiniClusterConfiguration, ? extends ChouMiniCluster> miniClusterFactory;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public static ChouPerJobMiniClusterFactory create() {
        return new ChouPerJobMiniClusterFactory(new Configuration(), ChouMiniCluster::new);
    }

    public static ChouPerJobMiniClusterFactory createWithFactory(Configuration configuration,
            Function<? super ChouMiniClusterConfiguration, ? extends ChouMiniCluster> miniClusterFactory) {
        return new ChouPerJobMiniClusterFactory(configuration, miniClusterFactory);
    }

    private ChouPerJobMiniClusterFactory(Configuration configuration,
            Function<? super ChouMiniClusterConfiguration, ? extends ChouMiniCluster> miniClusterFactory) {
        this.configuration = configuration;
        this.miniClusterFactory = miniClusterFactory;
    }

    // ===================================================================================
    //                                                                          Submit Job
    //                                                                          ==========
    /** Starts a {@link MiniCluster} and submits a job. */
    public CompletableFuture<JobClient> submitJob(ExecutionPlan executionPlan, ClassLoader userCodeClassloader) throws Exception {
        ChouMiniClusterConfiguration miniClusterConfig = getMiniClusterConfig(executionPlan.getMaximumParallelism());
        ChouMiniCluster miniCluster = miniClusterFactory.apply(miniClusterConfig);
        miniCluster.start();

        return miniCluster.submitJob(executionPlan).thenApplyAsync(FunctionUtils.uncheckedFunction(submissionResult -> {
            waitUntilJobInitializationFinished(userCodeClassloader, miniCluster, submissionResult);
            return submissionResult;
        })).thenApply(result -> {
            // by ForkJoinPool.commonPool-worker
            LOG.debug("@@@ MinCluster@submitJob()::thenApply(): result={}", result);
            return createMiniClusterJobClient(userCodeClassloader, miniCluster, result);
        }).whenComplete((ignored, throwable) -> {
            // by ForkJoinPool.commonPool-worker
            LOG.debug("@@@ MinCluster@submitJob()::whenComplete(): ignored={}", ignored);
            if (throwable != null) {
                // We failed to create the JobClient and must shutdown to ensure
                // cleanup.
                shutDownCluster(miniCluster);
            }
        }).thenApply(Function.identity());
    }

    private ChouMiniClusterConfiguration getMiniClusterConfig(int maximumParallelism) {
        Configuration configuration = new Configuration(this.configuration);

        if (!configuration.contains(RestOptions.BIND_PORT)) {
            configuration.set(RestOptions.BIND_PORT, "0");
        }

        int numTaskManagers = configuration.get(TaskManagerOptions.MINI_CLUSTER_NUM_TASK_MANAGERS);

        Map<String, String> overwriteParallelisms = configuration.get(PipelineOptions.PARALLELISM_OVERRIDES);
        if (overwriteParallelisms != null) {
            for (String overrideParallelism : overwriteParallelisms.values()) {
                maximumParallelism = Math.max(maximumParallelism, Integer.parseInt(overrideParallelism));
            }
        }

        int finalMaximumParallelism = maximumParallelism;
        int numSlotsPerTaskManager = configuration.getOptional(TaskManagerOptions.NUM_TASK_SLOTS)
                .orElseGet(() -> finalMaximumParallelism > 0 ? MathUtils.divideRoundUp(finalMaximumParallelism, numTaskManagers)
                        : TaskManagerOptions.NUM_TASK_SLOTS.defaultValue());

        return new ChouMiniClusterConfiguration.Builder().setConfiguration(configuration)
                .setNumTaskManagers(numTaskManagers)
                .setRpcServiceSharing(RpcServiceSharing.SHARED)
                .setNumSlotsPerTaskManager(numSlotsPerTaskManager)
                .build();
    }

    private void waitUntilJobInitializationFinished(ClassLoader userCodeClassloader, ChouMiniCluster miniCluster,
            JobSubmissionResult submissionResult) throws JobInitializationException {
        org.apache.flink.client.ClientUtils.waitUntilJobInitializationFinished(
                () -> miniCluster.getJobStatus(submissionResult.getJobID()).get(),
                () -> miniCluster.requestJobResult(submissionResult.getJobID()).get(), userCodeClassloader);
    }

    private ChouMiniClusterJobClient createMiniClusterJobClient(ClassLoader userCodeClassloader, ChouMiniCluster miniCluster,
            JobSubmissionResult result) {
        return new ChouMiniClusterJobClient(result.getJobID(), miniCluster, userCodeClassloader,
                ChouMiniClusterJobClient.JobFinalizationBehavior.SHUTDOWN_CLUSTER);
    }

    private static void shutDownCluster(ChouMiniCluster miniCluster) {
        miniCluster.closeAsync().whenComplete((ignored, throwable) -> {
            if (throwable != null) {
                LOG.warn("Shutdown of MiniCluster failed.", throwable);
            }
        });
    }
}
