package code;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class OperationExecutorServiceTest {

    private OperationExecutorService operationExecutorService;

    @Before
    public void setUp() {
        operationExecutorService = new OperationExecutorService();
    }

    @Test
    public void test_taskExceptionWithFixedDelay() {
        Task<String> task = () -> {
            throw new RuntimeException("Downstream error");
        };
        Operation<String> op = new Operation<>(task, 100, 3, Strategy.FIXED);
        assertThrows(RuntimeException.class, () -> {
            operationExecutorService.executeWithRetry(op);
        });
    }

    @Test
    public void test_taskExceptionWithRetryZero() {
        Task<String> task = () -> {
            throw new RuntimeException("Downstream error");
        };
        Operation<String> op = new Operation<>(task, 100, 0, Strategy.FIXED);
        assertThrows(RuntimeException.class, () -> {
            operationExecutorService.executeWithRetry(op);
        });
    }

    @Test
    public void test_succeedAfterFailures() {
        AtomicInteger attempts = new AtomicInteger(0);
        Task<String> task = () -> {
            if (attempts.incrementAndGet() < 3) {
                throw new RuntimeException("not yet");
            }
            return "success";
        };
        Operation<String> op = new Operation<>(task, 100, 3, Strategy.FIXED);
        String result = operationExecutorService.executeWithRetry(op);
        assertEquals("success", result);
    }
}