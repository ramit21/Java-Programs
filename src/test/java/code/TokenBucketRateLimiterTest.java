package code;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenBucketRateLimiterTest {

    private TokenBucketRateLimiter rateLimiter;

    @Before
    public void setUp() {
        // Capacity of 3 tokens, refilling at a rate of 2 tokens per second
        rateLimiter = new TokenBucketRateLimiter(3, 2.0);
    }

    @Test
    public void testInitialCapacityAllowsRequests() {
        // Bucket starts full (3 tokens)
        assertTrue("Request 1 should be allowed", rateLimiter.allowRequest());
        assertTrue("Request 2 should be allowed", rateLimiter.allowRequest());
        assertTrue("Request 3 should be allowed", rateLimiter.allowRequest());

        // 4th request should fail since capacity is exhausted
        assertFalse("Request 4 should be rate limited", rateLimiter.allowRequest());
    }

    @Test
    public void testProRataRefillOverTime() throws InterruptedException {
        // Exhaust all 3 tokens
        assertTrue(rateLimiter.allowRequest());
        assertTrue(rateLimiter.allowRequest());
        assertTrue(rateLimiter.allowRequest());
        assertFalse(rateLimiter.allowRequest());

        // Wait for ~1 second (refills 2 tokens at 2 tokens/sec rate)
        Thread.sleep(1050);

        // Should now have ~2 tokens available, allowing 2 requests
        assertTrue("Request after refill should be allowed", rateLimiter.allowRequest());
        assertTrue("Second request after refill should be allowed", rateLimiter.allowRequest());

        // Third request should fail because only 2 tokens were refilled
        assertFalse("Extra request beyond refilled tokens should be blocked", rateLimiter.allowRequest());
    }
}