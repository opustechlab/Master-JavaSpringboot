package com.oputechlab.belajarcrud3.controller;

import com.oputechlab.belajarcrud3.configuration.CoreConfiguration;
import com.oputechlab.belajarcrud3.model.MyRequest;
import com.oputechlab.belajarcrud3.service.RabbitMQProducerService;
import com.oputechlab.belajarcrud3.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rabbitmq") // Prefix URL
public class RabbitMqController {

    private final RabbitMQProducerService producer;

    public RabbitMqController(RabbitMQProducerService producer) {
        this.producer = producer;
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }
}
