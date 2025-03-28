package com.redempleo.controller;

import com.redempleo.dto.auth.JwtResponse;
import com.redempleo.dto.auth.LoginRequest;
import com.redempleo.dto.auth.SignupRequest;
import com.redempleo.model.Usuario;
import com.redempleo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication operations.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Authenticate a user and generate a JWT token.
     *
     * @param loginRequest Login credentials
     * @return JWT response with token and user info
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    /**
     * Register a new user.
     *
     * @param signupRequest Registration data
     * @return Success message
     */
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        Usuario usuario = authService.registerUser(signupRequest);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }
}