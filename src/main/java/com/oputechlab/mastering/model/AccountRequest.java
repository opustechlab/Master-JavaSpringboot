package com.oputechlab.mastering.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {
    @NotBlank(message = "Name is required")  // Tidak boleh kosong atau hanya spasi
    private String name;

    @NotBlank(message = "Age is required")   // Tidak boleh kosong atau hanya spasi
    private String age;

    @NotNull(message = "ID is required")     // Tidak boleh null
    @Min(value = 1, message = "ID must be greater than 0") // Harus lebih dari 0
    private Integer id;
}
