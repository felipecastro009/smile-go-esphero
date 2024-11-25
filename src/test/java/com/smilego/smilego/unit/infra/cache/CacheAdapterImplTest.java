package com.smilego.smilego.unit.infra.cache;

import com.smilego.smilego.infra.cache.CacheAdapterImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CacheAdapterImplTest {
    @Mock
    private CacheManager cacheManager;
    @Mock
    private Cache cache;
    @Mock
    private Cache.ValueWrapper valueWrapper;
    @InjectMocks
    private CacheAdapterImpl<String> cacheAdapter;

    @Test
    void shouldGetValueFromCache() {
        String name = "testCache";
        String key = "key1";
        String value = "value1";
        when(valueWrapper.get()).thenReturn(value);
        when(cacheManager.getCache(name)).thenReturn(cache);
        when(cache.get(key)).thenReturn(valueWrapper);
        String result = cacheAdapter.get(name, key);
        verify(cache).get(key);
        assertEquals(value, result);
    }

    @Test
    void shouldGetValueFromWrapperIsNull() {
        String name = "testCache";
        String key = "key1";
        String value = "value1";
        when(valueWrapper.get()).thenReturn(null);
        when(cacheManager.getCache(name)).thenReturn(cache);
        when(cache.get(key)).thenReturn(valueWrapper);
        String result = cacheAdapter.get(name, key);
        verify(cache).get(key);
        assertNull(result);
    }

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
