package com.oputechlab.belajarcrud3.repository;

import com.oputechlab.belajarcrud3.common.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Repository
public class WhatsappRepository {

    private static final Logger logger = LoggerFactory.getLogger(WhatsappRepository.class);

    private final WebClient webClient;
    private static final String SECRET_KEY = "6a77dcee312093da11feb1347fdae7cf";
    private static final String API_URL = "http://116.197.132.181/thirdparty/whatsapp/whatsapp/send/message";

    public WhatsappRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
    }
    public String sendMessage(String sendTo, String message, String mediaUrl, String mediaName, String type) {

        try {

            // Generate Timestamp (format: YYYY-MM-DDTHH:mm:ssZ)
            String timestamp = Instant.now()
                    .atOffset(ZoneOffset.UTC)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));

            // Request Body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("sendTo", sendTo);
            requestBody.put("message", message);
            requestBody.put("mediaUrl", mediaUrl);
            requestBody.put("mediaName", mediaName);
            requestBody.put("type", type);

            // Convert Body to JSON String
            String bodyJson = Helper.toJson(requestBody);

            // Create String to Sign
            String stringToSign = String.format("*%s:%s:%s*", bodyJson, timestamp, SECRET_KEY);

            // Generate Signature (SHA-256)
            String signature = Helper.hashSHA256(stringToSign);

            logger.info("[WaService] Request: {}", bodyJson);

            logger.info("[WaService] Url: {}", API_URL);

            // Send request (Blocking)
            String response = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("X-TIMESTAMP", timestamp)
                    .header("X-SIGNATURE", signature)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();  // 🔥 Mengubah ke Synchronous (Blocking)

            logger.info("[WaService] Success Response: {}", response);

            return response;
        }catch (WebClientResponseException ex) {
            logger.error("Error Response: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            return ex.getResponseBodyAsString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
