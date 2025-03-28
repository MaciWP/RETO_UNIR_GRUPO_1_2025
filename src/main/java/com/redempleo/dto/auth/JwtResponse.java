package com.redempleo.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * DTO for JWT authentication responses.
 */
@Data
@Builder
public class JwtResponse {

    private String token;
    private String type;
    private String username;
    private String nombre;
    private String apellidos;
    private String email;
    private List<String> roles;
}