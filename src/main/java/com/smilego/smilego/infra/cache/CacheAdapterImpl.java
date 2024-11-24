package com.smilego.smilego.infra.cache;

import com.smilego.smilego.application.cache.CacheAdapter;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;

@AllArgsConstructor
public class CacheAdapterImpl<T> implements CacheAdapter<T> {
    private final CacheManager cacheManager;

    @Override
    public void put(String name, String key, T value) {
        cacheManager.getCache(name).put(key, value);
    }

    @Override
    public T get(String name, String key) {
        return (T) cacheManager.getCache(name).get(key);
    }

    @Override
    public void evict(String name, String key) {
        cacheManager.getCache(name).evict(key);
    }

    @Override
    public void clear(String name) {
        cacheManager.getCache(name).clear();
    }
}
