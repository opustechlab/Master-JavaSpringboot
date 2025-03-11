package com.oputechlab.mastering.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Helper {
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public static String toJson(Map<String, Object> data) {
        try {
            return jsonMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }

    public static String hashSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing with SHA-256", e);
        }
    }

    public static String CreateStringToSign(String method, String body, String timestamp, String secret) {
        return String.format("*%s:%s:%s:%s*", method, body, timestamp, secret);
    }
}
