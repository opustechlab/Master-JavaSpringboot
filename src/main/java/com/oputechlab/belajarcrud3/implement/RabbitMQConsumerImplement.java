package com.oputechlab.belajarcrud3.implement;

import com.oputechlab.belajarcrud3.middleware.RabbitMQConfig;
import com.oputechlab.belajarcrud3.service.RabbitMQConsumerService;
import com.oputechlab.belajarcrud3.service.RabbitMQProducerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerImplement implements RabbitMQConsumerService {

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
