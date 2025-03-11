package com.oputechlab.mastering.controller;

import com.oputechlab.mastering.common.Helper;
import com.oputechlab.mastering.configuration.CoreConfiguration;
import com.oputechlab.mastering.model.AccountRequest;
import com.oputechlab.mastering.model.AccountResponse;
import com.oputechlab.mastering.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.ContentCachingRequestWrapper;

@RestController
@RequestMapping("/api/account") // Prefix URL
public class AccountController {

    private final Map<Integer, String> users = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final UserService userService;
    private final CoreConfiguration coreConfiguration;
    private final ObjectMapper jsonDecodeMapper = new ObjectMapper(); // ✅ Tambahkan ini

    public AccountController(UserService userService, CoreConfiguration coreConfiguration) {
        this.userService = userService;
        this.coreConfiguration = coreConfiguration;
    }

    // Get User by ID
    @GetMapping("/get")
    public ResponseEntity<String> getUserById() {

        // contoh baca properties.
        return ResponseEntity.ok("App Name: " + coreConfiguration.getName() +
                ", Version: " + coreConfiguration.getVersion());
    }

    @PostMapping("/submit")
    public ResponseEntity<AccountResponse> submitData(
            ContentCachingRequestWrapper requestWrapper,  // ✅ Gunakan wrapper untuk membaca ulang request body
            @Valid @RequestBody AccountRequest request, // @Valid untuk model validation annotations
            @RequestHeader("X-Timestamp") String timestamp,
            @RequestHeader("X-Signature") String signature) {

        try{
            String rawBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
            if(!validateSignature(rawBody, timestamp, signature)){
                AccountResponse response = new AccountResponse("01", "Invalid signature");
                return ResponseEntity.status(401).body(response);
            }

            // contoh decode dari string ke model object
            // AccountRequest accountRequest = jsonDecodeMapper.readValue(rawBody, AccountRequest.class);

            // contoh return json response.
            AccountResponse response = new AccountResponse("00", "Data received for " + request.getName());

            return ResponseEntity.ok(response); // Return JSON dengan HTTP 200
        }
        catch (Exception e){
            logger.error(e.getMessage(), "Error!");
            return ResponseEntity.badRequest().body(new AccountResponse("01", e.getMessage()));
        }
    }

    private boolean validateSignature(String rawBody, String timestamp, String signature) {
        // 🔍 create payload / string to sign
        String payload = Helper.CreateStringToSign("POST", rawBody, timestamp, coreConfiguration.getSecretKey());
        String expectedSignature = Helper.hashSHA256(payload);

        // 🚨 Validasi Signature
        if (!expectedSignature.equals(signature)) {
            return false;
        }

        return true;
    }
}
