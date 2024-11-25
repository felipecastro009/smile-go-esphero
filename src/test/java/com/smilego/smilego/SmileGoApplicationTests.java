package com.smilego.smilego;

import jakarta.activation.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SmileGoApplicationTests {
    @Mock
    private DataSource dataSource;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private RedisTemplate<String, Object> redisTemplate;
    @Mock
    private CacheManager cacheManager;

    @Test
    void contextLoads() {}
}
