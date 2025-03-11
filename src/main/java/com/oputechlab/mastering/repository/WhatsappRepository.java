package com.oputechlab.mastering.repository;

import com.oputechlab.mastering.configuration.PiwapiConfiguration;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import okhttp3.*;


@Repository
public class WhatsappRepository {

    private static final Logger logger = LoggerFactory.getLogger(WhatsappRepository.class);

    // bisa pindahin ke configuration
    private final WebClient webClient;
    private final PiwapiConfiguration piwapiConfiguration;


    public WhatsappRepository(WebClient.Builder webClientBuilder, PiwapiConfiguration piwapiConfiguration) {
        this.webClient = webClientBuilder.baseUrl(piwapiConfiguration.getUrl()).build();
        this.piwapiConfiguration = piwapiConfiguration;
    }
    public String sendMessage(String sendTo, String message, String mediaUrl, String mediaName, String type) {

        try {

            // Contoh Generate Timestamp (format: YYYY-MM-DDTHH:mm:ssZ)
            /*String timestamp = Instant.now()
                    .atOffset(ZoneOffset.UTC)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));*/

            // Request Body
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("document_name", mediaName)
                    .addFormDataPart("document_type", mediaName.substring(mediaName.lastIndexOf('.') + 1))
                    .addFormDataPart("document_url", mediaUrl)
                    .addFormDataPart("priority", "1")
                    .addFormDataPart("message", message)
                    .addFormDataPart("type", type)
                    .addFormDataPart("recipient", "+62" + sendTo)
                    .addFormDataPart("account", piwapiConfiguration.getAccountId())
                    .addFormDataPart("secret", piwapiConfiguration.getApiKey())
                    .build();

            // Konversi ke String (Harus membaca ulang)
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            String bodyAsString = buffer.readUtf8();

            // Contoh Convert Body to JSON String
            // String bodyJson = Helper.toJson(requestBody);

            logger.info("[WaService.Piwapi] Request: {}", bodyAsString);

            logger.info("[WaService.Piwapi] Url: {}", piwapiConfiguration.getUrl());

            // Send request (Blocking)
            String response = webClient.post()
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();  // 🔥 Mengubah ke Synchronous (Blocking)

            logger.info("[WaService.Piwapi] Success Response: {}", response);

            // olah disini

            return response;
        }catch (WebClientResponseException ex) {
            logger.error("[WaService.Piwapi] Error Response: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            return ex.getResponseBodyAsString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
