package com.redempleo.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for signup requests.
 */
@Data
public class SignupRequest {

    @NotBlank(message = "El nombre de usuario es requerido")
    @Size(min = 3, max = 45, message = "El nombre de usuario debe tener entre 3 y 45 caracteres")
    private String username;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 45, message = "El nombre debe tener entre 2 y 45 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos son requeridos")
    @Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
    private String apellidos;

    @NotBlank(message = "El email es requerido")
    @Size(max = 100, message = "El email no debe exceder los 100 caracteres")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, max = 40, message = "La contraseña debe tener entre 6 y 40 caracteres")
    private String password;
}