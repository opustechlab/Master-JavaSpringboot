package com.oputechlab.mastering.controller;

import com.oputechlab.mastering.common.Helper;
import com.oputechlab.mastering.configuration.CoreConfiguration;
import com.oputechlab.mastering.model.AccountRequest;
import com.oputechlab.mastering.model.AccountResponse;
import com.oputechlab.mastering.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountControllerUnitTest {

    private AccountController accountController;

    @Mock
    private UserService userService;

    @Mock
    private CoreConfiguration coreConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountController = new AccountController(userService, coreConfiguration);
    }

    private MockedStatic<Helper> mockedHelper;

    @AfterEach
    void tearDown() {
        if (mockedHelper != null) {
            mockedHelper.close(); // ✅ Pastikan static mock di-unregister setiap selesai test
        }
    }

    @Test
    void testGetUserById() {
        // Mock konfigurasi
        Mockito.when(coreConfiguration.getName()).thenReturn("TestApp");
        Mockito.when(coreConfiguration.getVersion()).thenReturn("1.0.0");

        var response = accountController.getUserById();

        // Pastikan hasilnya sesuai
        assertEquals("App Name: TestApp, Version: 1.0.0", response.getBody());
    }

    @Test
    void testSubmitData_Success() {
        // Arrange
        String validSignature = "valid_signature";
        String requestBody = "{\"name\": \"John\"}";
        String timestamp = "2025-03-12T12:00:00Z";

        // Mock ContentCachingRequestWrapper
        ContentCachingRequestWrapper mockRequestWrapper = Mockito.mock(ContentCachingRequestWrapper.class);
        Mockito.when(mockRequestWrapper.getContentAsByteArray()).thenReturn(requestBody.getBytes(StandardCharsets.UTF_8));


        Mockito.when(coreConfiguration.getSecretKey()).thenReturn("secret");
        mockedHelper = Mockito.mockStatic(Helper.class); // ✅ Simpan instance biar bisa di-close nanti

        mockedHelper.when(() ->
                        Helper.CreateStringToSign("POST", requestBody, timestamp, "secret"))
                .thenReturn("string_to_sign");

        mockedHelper.when(() ->
                        Helper.hashSHA256("string_to_sign"))
                .thenReturn(validSignature);

        AccountRequest request = new AccountRequest();
        request.setName("John");

        // Act
        ResponseEntity<AccountResponse> response = accountController.submitData(mockRequestWrapper, request, timestamp, validSignature);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("00", response.getBody().getStatus());
    }

    @Test
    void testSubmitData_InvalidSignature() {
        // Arrange
        String invalidSignature = "wrong_signature";
        String requestBody = "{\"name\": \"John\"}";
        String timestamp = "2025-03-12T12:00:00Z";


        // Mock ContentCachingRequestWrapper
        ContentCachingRequestWrapper mockRequestWrapper = Mockito.mock(ContentCachingRequestWrapper.class);
        Mockito.when(mockRequestWrapper.getContentAsByteArray()).thenReturn(requestBody.getBytes(StandardCharsets.UTF_8));

        Mockito.when(coreConfiguration.getSecretKey()).thenReturn("secret");
        mockedHelper = Mockito.mockStatic(Helper.class); // ✅ Simpan instance biar bisa di-close nanti

        mockedHelper.when(() ->
                        Helper.CreateStringToSign("POST", requestBody, timestamp, "secret"))
                .thenReturn("string_to_sign");

        mockedHelper.when(() ->
                        Helper.hashSHA256("string_to_sign"))
                .thenReturn("tes");

        AccountRequest request = new AccountRequest();
        request.setName("John");

        // Act
        ResponseEntity<AccountResponse> response = accountController.submitData(mockRequestWrapper, request, timestamp, invalidSignature);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("01", response.getBody().getStatus());
        assertEquals("Invalid signature", response.getBody().getMessage());
    }

}
