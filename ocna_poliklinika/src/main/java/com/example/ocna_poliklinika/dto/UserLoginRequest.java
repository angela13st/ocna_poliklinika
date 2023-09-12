package com.example.ocna_poliklinika.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String lozinka;
}
