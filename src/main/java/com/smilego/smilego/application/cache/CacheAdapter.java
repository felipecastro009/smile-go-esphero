package com.smilego.smilego.application.cache;

public interface CacheAdapter<T> {
    void put(String name, String key, T value);
    T get(String name, String key);
    void evict(String name, String key);
    void clear(String name);
}
