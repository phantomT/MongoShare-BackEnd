package com.ustc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author 田宝宁
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置RedisConnection工厂
        template.setConnectionFactory(factory);
        // 使用String序列化方式序列化Key
        template.setKeySerializer(RedisSerializer.string());
        // 使用Json序列化方式序列化VALUE
        template.setValueSerializer(RedisSerializer.json());
        return template;
    }
}
