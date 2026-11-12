package code;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {

    private LRUCache<String, String> lruCache;

    @Before
    public void setUp() {
        // Small capacity of 2 to easily test eviction behavior
        lruCache = new LRUCache<>(2);
    }

    @Test
    public void testPutAndGet() {
        lruCache.put("key1", "value1");
        assertEquals("value1", lruCache.get("key1"));
    }

    @Test
    public void testEvictionOrder() {
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");

        // Capacity is full (2). Adding "key3" should evict the oldest entry ("key1").
        lruCache.put("key3", "value3");

        assertNull("Oldest item key1 should be evicted", lruCache.get("key1"));
        assertEquals("value2", lruCache.get("key2"));
        assertEquals("value3", lruCache.get("key3"));
    }

    @Test
    public void testAccessOrderUpdatesOnGet() {
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");

        // Access "key1" so it becomes the most recently used (making "key2" the oldest)
        assertEquals("value1", lruCache.get("key1"));

        // Adding "key3" should now evict "key2" instead of "key1"
        lruCache.put("key3", "value3");

        assertEquals("value1", lruCache.get("key1")); // Kept due to recent access
        assertNull("Least recently used item key2 should be evicted", lruCache.get("key2"));
        assertEquals("value3", lruCache.get("key3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullKeyThrowsException() {
        lruCache.put(null, "value1");
    }
}