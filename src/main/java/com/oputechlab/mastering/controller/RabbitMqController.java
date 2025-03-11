package com.oputechlab.mastering.controller;

import com.oputechlab.mastering.service.RabbitMQProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbitmq") // Prefix URL
public class RabbitMqController {

    private final RabbitMQProducerService producer;

    public RabbitMqController(RabbitMQProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }

    @GetMapping("/send/test/{message}")
    public String sendMessageTanpaSignature(@PathVariable String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }
}
