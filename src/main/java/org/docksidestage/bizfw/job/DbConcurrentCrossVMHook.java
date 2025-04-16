package org.docksidestage.bizfw.job;

import java.time.LocalDateTime;

import org.dbflute.exception.EntityAlreadyExistsException;
import org.lastaflute.job.JobManager;
import org.lastaflute.job.subsidiary.ConcurrentCrossVMHook;
import org.lastaflute.job.subsidiary.CrossVMState;
import org.lastaflute.job.subsidiary.ReadableJobState;

/**
 * dummy implementation for CrossVMHook example.
 * @author jflute
 */
public class DbConcurrentCrossVMHook extends ConcurrentCrossVMHook {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public DbConcurrentCrossVMHook(JobManager jobManager) {
        super(jobManager);
    }

    // ===================================================================================
    //                                                                      Hook Beginning
    //                                                                      ==============
    // -----------------------------------------------------
    //                                             Duplicate
    //                                             ---------
    @Override
    protected boolean determineDuplicateBoot(ReadableJobState jobState) {
        boolean registeredSuccess = tryRegisteringJob(jobState);
        return !registeredSuccess; // returning true if duplicate
    }

    @Override
    protected void waitForDuplicateEnding(ReadableJobState jobState) {
        int waitCount = 0; // 1 origin
        while (!tryRegisteringJob(jobState)) { // until registration success
            ++waitCount;
            try {
                Thread.sleep(3000L); // needs adjustment
            } catch (InterruptedException ignored) {
                // do nothing, and try next loop
            }
            if (waitCount >= 20) { // give up
                String msg = "Too waiting so give up: " + jobState;
                throw new IllegalStateException(msg);
            }
        }
    }

    protected boolean tryRegisteringJob(ReadableJobState jobState) {
        try {
            // >>>>>
            // dummy: insert the provisional record as reservation
            // (gurantee unique by e.g. unique code)
            // <<
            return true; // success
        } catch (EntityAlreadyExistsException ignored) {
            return false; // duplicate
        }
    }

    // -----------------------------------------------------
    //                                              Neighbor
    //                                              --------
    @Override
    protected boolean determineNeighborExecutingNow(ReadableJobState jobState) {
        return false; // no implementation for now
    }

    @Override
    protected void waitForNeighborEnding(ReadableJobState jobState) {
        // no implementation for now
    }

    // -----------------------------------------------------
    //                                        Mark Executing
    //                                        --------------
    @Override
    protected void markExecuting(ReadableJobState jobState, LocalDateTime activationTime, CrossVMState crossVMState) {
        // >>>>>
        // dummy: update the existing provisional record as formalized (execute now)
        // dummy: and adjust crossVMState as your policy
        // <<
    }

    // ===================================================================================
    //                                                                         Hook Ending
    //                                                                         ===========
    @Override
    protected void closeExecuting(ReadableJobState jobState, CrossVMState crossVMState, LocalDateTime endTime) {
        // >>>>>
        // dummy: delete the current formalized record
        // <<
    }
}
