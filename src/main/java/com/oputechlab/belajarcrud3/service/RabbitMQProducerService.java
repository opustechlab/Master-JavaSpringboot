package com.oputechlab.belajarcrud3.service;

public interface RabbitMQProducerService {
    void sendMessage(String message);
    void sendWhatsapp();
}
