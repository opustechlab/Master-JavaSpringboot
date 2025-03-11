package com.oputechlab.mastering.implement;

import com.oputechlab.mastering.middleware.rabbittmq.RabbitMQConfig;
import com.oputechlab.mastering.service.RabbitMQConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerServiceImplement implements RabbitMQConsumerService {

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
