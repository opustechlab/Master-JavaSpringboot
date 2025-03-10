package com.oputechlab.belajarcrud3.controller;

import com.oputechlab.belajarcrud3.configuration.CoreConfiguration;
import com.oputechlab.belajarcrud3.model.MyRequest;
import com.oputechlab.belajarcrud3.model.Whatsapp;
import com.oputechlab.belajarcrud3.service.UserService;
import com.oputechlab.belajarcrud3.service.WhatsappService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/whatsapp") // Prefix URL
public class WhatsappController {

    private final Map<Integer, String> users = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(WhatsappController.class);
    private final WhatsappService whatsappService;
    private final CoreConfiguration coreConfiguration;


    public WhatsappController(WhatsappService whatsappService, CoreConfiguration coreConfiguration) {
        this.whatsappService = whatsappService;
        this.coreConfiguration = coreConfiguration;
    }

    @PostMapping("/send/message")
    public ResponseEntity<String> deleteUser(@RequestBody Whatsapp.Request request) {
        try{
            return ResponseEntity.ok(whatsappService.sendMessage(request.getSendTo(), request.getMessage(), request.getMediaUrl(), request.getMediaName(), request.getType()));
        }
        catch (DataAccessException e){
            logger.error(e.getMessage(), "Error!");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            logger.error(e.getMessage(), "Error!");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
