package com.redempleo.service;

import com.redempleo.dto.auth.JwtResponse;
import com.redempleo.dto.auth.LoginRequest;
import com.redempleo.dto.auth.SignupRequest;
import com.redempleo.model.Usuario;
import com.redempleo.model.Perfil;
import com.redempleo.repository.PerfilRepository;
import com.redempleo.repository.UsuarioRepository;
import com.redempleo.security.JwtTokenProvider;
import com.redempleo.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for handling authentication operations.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    /**
     * Authenticate a user and generate a JWT token.
     *
     * @param loginRequest Login credentials
     * @return JWT response with token and user info
     */
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .token(jwt)
                .type("Bearer")
                .username(userPrincipal.getUsername())
                .nombre(userPrincipal.getNombre())
                .apellidos(userPrincipal.getApellidos())
                .email(userPrincipal.getEmail())
                .roles(roles)
                .build();
    }

    /**
     * Register a new user.
     *
     * @param signupRequest Registration data
     * @return The registered user
     */
    @Transactional
    public Usuario registerUser(SignupRequest signupRequest) {
        // Check if username already exists
        if (usuarioRepository.existsById(signupRequest.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Check if email already exists
        if (usuarioRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }

        // Create new user
        Usuario usuario = new Usuario();
        usuario.setUsername(signupRequest.getUsername());
        usuario.setNombre(signupRequest.getNombre());
        usuario.setApellidos(signupRequest.getApellidos());
        usuario.setEmail(signupRequest.getEmail());
        usuario.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        usuario.setEnabled(1);
        usuario.setFechaRegistro(LocalDate.now());

        // Assign default role
        Perfil userRole = perfilRepository.findByNombre("USUARIO")
                .orElseGet(() -> {
                    Perfil newUserRole = new Perfil("USUARIO");
                    return perfilRepository.save(newUserRole);
                });

        usuario.addPerfil(userRole);

        return usuarioRepository.save(usuario);
    }
}