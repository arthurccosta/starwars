package com.b2w.swapi.starwars.service.impl;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

/**
 * Created by arthur on 04/05/19
 */
public class CacheImpl<T> extends RedisTemplate<String, Object> {
    private String cacheKey;
    private HashOperations<String, String, T> cache;

    public CacheImpl(
            String cacheKey,
            HashOperations<String, String, T> cache
    ) {
        this.cacheKey = cacheKey;
        this.cache = cache;
    }

    public Optional<T> findCache(String objectKey) {
        return Optional.ofNullable(this.cache.get(this.cacheKey, objectKey));
    }

    public T saveCache(String objectKey, T object) {
        this.cache.put(this.cacheKey, objectKey, object);
        return object;
    }
}

