package code;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class TTLCacheServiceTest {

    private TTLCacheService<String, String> cacheService;

    @Before
    public void setUp() {
        cacheService = new TTLCacheService<>(100000000L);
    }

    @Test
    public void testCachePutWithDefaultTTL() {
        cacheService.put("key1", "value1");
        String val = cacheService.get("key1");
        assertEquals("value1", val);
    }

    @Test
    public void testCacheGetForNonExistantKeyReturnsNull() {
        cacheService.put("key1", "value1");
        String val = cacheService.get("key2");
        assertNull(val);
    }

    @Test
    public void testCacheGetForExpiredItem() throws InterruptedException {
        cacheService.put("key1", "value1", 1L);
        Thread.sleep(2000);
        String val = cacheService.get("key1");
        assertNull(val);
    }

    @Test
    public void testCacheGetForNonExpiredItem() throws InterruptedException {
        Duration duration = Duration.ofSeconds(2);
        cacheService.put("key1", "value1", duration.getSeconds());
        Thread.sleep(duration.minusSeconds(1).toMillis());
        String val = cacheService.get("key1");
        assertEquals("value1", val);
    }
}