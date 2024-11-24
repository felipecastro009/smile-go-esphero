package com.smilego.smilego.main;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.infra.cache.CacheAdapterImpl;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CacheAdapter cacheAdapter(CacheManager cacheManager) {
        return new CacheAdapterImpl(cacheManager);
    }
}
