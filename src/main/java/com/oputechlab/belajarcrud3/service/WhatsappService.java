package com.oputechlab.belajarcrud3.service;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface WhatsappService {
    String sendMessage(String sendTo, String message, String mediaUrl, String mediaName, String type);
}