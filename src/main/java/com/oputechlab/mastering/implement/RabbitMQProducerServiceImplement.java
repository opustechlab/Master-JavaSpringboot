package com.oputechlab.mastering.implement;

import com.oputechlab.mastering.middleware.rabbittmq.RabbitMQConfig;
import com.oputechlab.mastering.service.RabbitMQProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerServiceImplement implements RabbitMQProducerService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerServiceImplement(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("Sent message: " + message);
    }
}
