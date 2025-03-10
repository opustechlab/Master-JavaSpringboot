package com.oputechlab.belajarcrud3.implement;

import com.oputechlab.belajarcrud3.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImplement implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveToRedis(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object getFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

