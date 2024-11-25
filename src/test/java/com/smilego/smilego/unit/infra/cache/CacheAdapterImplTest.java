package com.smilego.smilego.unit.infra.cache;

import com.smilego.smilego.infra.cache.CacheAdapterImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CacheAdapterImplTest {
    @Mock
    private CacheManager cacheManager;
    @Mock
    private Cache cache;
    @InjectMocks
    private CacheAdapterImpl<String> cacheAdapter;

    @Test
    void shouldPutValueInCache() {
        String name = "testCache";
        String key = "key1";
        String value = "value1";
        when(cacheManager.getCache(name)).thenReturn(cache);
        cacheAdapter.put(name, key, value);
        verify(cache).put(key, value);
    }

    @Test
    void shouldEvictValueFromCache() {
        String name = "testCache";
        String key = "key1";
        when(cacheManager.getCache(name)).thenReturn(cache);
        cacheAdapter.evict(name, key);
        verify(cache).evict(key);
    }

    @Test
    void shouldClearCache() {
        String name = "testCache";
        when(cacheManager.getCache(name)).thenReturn(cache);
        cacheAdapter.clear(name);
        verify(cache).clear();
    }
}
