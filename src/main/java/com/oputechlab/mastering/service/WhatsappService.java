package com.oputechlab.mastering.service;

public interface WhatsappService {
    String sendMessage(String sendTo, String message, String mediaUrl, String mediaName, String type);
}