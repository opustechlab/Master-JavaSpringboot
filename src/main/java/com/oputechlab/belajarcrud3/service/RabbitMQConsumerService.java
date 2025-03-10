package com.oputechlab.belajarcrud3.service;

public interface RabbitMQConsumerService {
    void receiveMessage(String message);
}
