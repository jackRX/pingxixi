package com.czxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 10:40 2019-01-03
 */
public class RedisConfig {

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

}
