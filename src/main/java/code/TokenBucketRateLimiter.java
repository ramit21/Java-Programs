package code;

import java.time.Duration;
import java.time.Instant;
//see test case as well
public class TokenBucketRateLimiter {

    private final long capacity;
    private final double refillRatePerSecond;
    private double availableTokens;
    private Instant lastRefillTime;

    public TokenBucketRateLimiter(long capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.availableTokens = capacity; // Start with a full bucket
        this.lastRefillTime = Instant.now();
    }

    public synchronized boolean allowRequest() {
        refill();

        if (availableTokens >= 1.0) {
            availableTokens -= 1.0;
            return true; // Execution allowed
        }

        return false; // Rate limited
    }

    private void refill() {
        Instant now = Instant.now();
        Duration elapsed = Duration.between(lastRefillTime, now);
        long elapsedMillis = elapsed.toMillis();

        if (elapsedMillis > 0) {
            // Calculate tokens earned on a pro-rata basis since last update
            double tokensToAdd = elapsedMillis * (refillRatePerSecond / 1000.0);
            availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
            lastRefillTime = now;
        }
    }

    public synchronized double getAvailableTokens() {
        refill();
        return availableTokens;
    }
}