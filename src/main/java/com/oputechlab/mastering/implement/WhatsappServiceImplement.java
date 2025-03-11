package com.oputechlab.mastering.implement;

import com.oputechlab.mastering.repository.WhatsappRepository;
import com.oputechlab.mastering.service.WhatsappService;
import org.springframework.stereotype.Service;

@Service
public class WhatsappServiceImplement implements WhatsappService {
    private final WhatsappRepository whatsappRepository;

    public WhatsappServiceImplement(WhatsappRepository whatsappRepository) {
        this.whatsappRepository = whatsappRepository;
    }

    @Override
    public String sendMessage(String sendTo, String message, String mediaUrl, String mediaName, String type) {
        return whatsappRepository.sendMessage(sendTo, message, mediaUrl, mediaName, type);
    }
}

