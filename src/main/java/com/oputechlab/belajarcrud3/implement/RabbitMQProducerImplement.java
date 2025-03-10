package com.oputechlab.belajarcrud3.implement;

import com.oputechlab.belajarcrud3.middleware.RabbitMQConfig;
import com.oputechlab.belajarcrud3.service.RabbitMQProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerImplement implements RabbitMQProducerService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerImplement(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("Sent message: " + message);
    }

    @Override
    public void sendWhatsapp() {
        String phoneNo = "08979713464";
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, phoneNo);
        System.out.println("Sent Whatsapp: " + phoneNo);
    }
}
