package com.oputechlab.mastering.service;

public interface RedisService {
    void saveToRedis(String key, Object value);
    Object getFromRedis(String key);
}
