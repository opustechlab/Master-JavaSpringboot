package com.oputechlab.belajarcrud3.service;

import java.util.List;
import java.util.Map;

public interface RedisService {
    void saveToRedis(String key, Object value);
    Object getFromRedis(String key);
}
