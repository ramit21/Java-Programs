package code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/*
    TTL cache - see unit test case file TTLCacheServiceTest as well.
 */
public class TTLCacheService<K, V> {

    private final Map<K, CacheItem<K, V>> cache;
    private final long defaultTTL;

    public TTLCacheService(long defaultTTL) {
        this.cache = new HashMap<>();
        this.defaultTTL = defaultTTL;
    }

    public void put(K key, V value, Long ttl) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        if (ttl == null) {
            ttl = defaultTTL;
        }
        CacheItem<K, V> cacheItem = new CacheItem<>(key, value, ttl, Instant.now());
        cache.put(key, cacheItem);
    }

    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        put(key, value, defaultTTL);
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        CacheItem<K, V> cacheItem = cache.get(key);
        if (cacheItem == null) {
            return null;
        }
        if (Instant.now().isAfter(cacheItem.getCreatedAt().plusSeconds(cacheItem.getTtl()))) {
            cache.remove(key);
            return null;
        } else {
            return cacheItem.getValue();
        }
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class CacheItem<K, V> {
    private K key;
    private V value;
    private long ttl;
    private Instant createdAt;
}
