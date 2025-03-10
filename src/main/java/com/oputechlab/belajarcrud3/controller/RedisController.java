package com.oputechlab.belajarcrud3.controller;

import com.oputechlab.belajarcrud3.configuration.CoreConfiguration;
import com.oputechlab.belajarcrud3.model.Whatsapp;
import com.oputechlab.belajarcrud3.service.RedisService;
import com.oputechlab.belajarcrud3.service.WhatsappService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/redis") // Prefix URL
public class RedisController {

    private final Map<Integer, String> users = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
    private final RedisService redisService;
    private final CoreConfiguration coreConfiguration;


    public RedisController(RedisService redisService, CoreConfiguration coreConfiguration) {
        this.redisService = redisService;
        this.coreConfiguration = coreConfiguration;
    }

    @PostMapping("/set/{key}/{message}")
    public Object setRedis(@PathVariable String key, @PathVariable String message) {
        redisService.saveToRedis(key, message);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/get/{key}")
    public Object getFromRedis(@PathVariable String key) {
        // Retrieve value from Redis using the path variable 'param'
        return redisService.getFromRedis(key);
    }




}
