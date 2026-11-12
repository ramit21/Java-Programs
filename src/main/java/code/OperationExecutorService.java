package code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OperationExecutorService {

    public <T> T executeWithRetry(Operation<T> operation) {
        validateOperation(operation);

        for (int attempt = 0; attempt <= operation.getMaxRetries(); attempt++) {

            if (attempt > 0) {
                int nextDelay = getNextDelay(operation, attempt);
                try {
                    System.out.println("Waiting executionCount " + (attempt + 1) + " for " + nextDelay + " ms");
                    Thread.sleep(nextDelay);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Problem waiting for retryCount: " + (attempt + 1) + ", nextDelay: " + nextDelay, e);
                }
            }

            try {
                return operation.getTask().execute();
            } catch (Exception ex) {
                System.out.println("Exception caught: " + ex.getMessage());
                if (attempt >= operation.getMaxRetries()) {
                    throw new RuntimeException("Operation failed after executionCount = " + (attempt + 1), ex);
                }
            }
        }

        throw new RuntimeException("Operation failed after retries");
    }

    private <T> void validateOperation(Operation<T> operation) {
        if (operation.getInitialDelay() < 0) throw new IllegalArgumentException("initialDelay cannot be negative");
        if (operation.getMaxRetries() < 0) throw new IllegalArgumentException("maxRetries cannot be negative");
        if (operation.getTask() == null) throw new IllegalArgumentException("No task supplied");
    }

    private <T> int getNextDelay(Operation<T> operation, int retryCount) {
        if (retryCount == 0) return (int) operation.getInitialDelay();

        switch (operation.getStrategy()) {
            case FIXED:
                return (int) operation.getInitialDelay();
            case LINEAR:
                return (int) (operation.getInitialDelay() * retryCount);
            case EXPONENTIAL:
                return (int) (operation.getInitialDelay() * Math.pow(2, retryCount));
            default:
                return (int) operation.getInitialDelay();
        }
    }
}

// --- Supporting Types Clubbed Together ---

@Data
@AllArgsConstructor
@NoArgsConstructor
class Operation<T> {
    private Task<T> task;
    private long initialDelay;
    private int maxRetries;
    private Strategy strategy;
}

@FunctionalInterface
interface Task<T> {
    T execute() throws Exception;
}

enum Strategy {
    FIXED,
    LINEAR,
    EXPONENTIAL
}