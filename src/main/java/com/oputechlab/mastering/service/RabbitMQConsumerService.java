package com.oputechlab.mastering.service;

public interface RabbitMQConsumerService {
    void receiveMessage(String message);
}
