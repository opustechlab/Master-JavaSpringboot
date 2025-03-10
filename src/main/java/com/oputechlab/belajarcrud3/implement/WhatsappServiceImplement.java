package com.oputechlab.belajarcrud3.implement;

import com.oputechlab.belajarcrud3.repository.UserRepository;
import com.oputechlab.belajarcrud3.repository.WhatsappRepository;
import com.oputechlab.belajarcrud3.service.UserService;
import com.oputechlab.belajarcrud3.service.WhatsappService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

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

