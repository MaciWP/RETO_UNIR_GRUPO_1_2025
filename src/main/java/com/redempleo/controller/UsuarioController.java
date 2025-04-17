package com.redempleo.controller;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Usuario;
import com.redempleo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling user operations.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Get all users (admin only).
     *
     * @return List of all users
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Get a user by username (admin or own user).
     *
     * @param username The username
     * @return The user
     */
    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.principal.username")
    public ResponseEntity<Usuario> getUsuarioByUsername(@PathVariable String username) {
        Usuario usuario = usuarioService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con username: " + username));
        return ResponseEntity.ok(usuario);
    }

    /**
     * Get the current authenticated user.
     *
     * @return The current user
     */
    @GetMapping("/me")
    public ResponseEntity<Usuario> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con username: " + username));
        return ResponseEntity.ok(usuario);
    }

    /**
     * Update a user (admin only).
     *
     * @param username The username
     * @param usuarioDetails The updated user data
     * @return The updated user
     */
    @PutMapping("/admin/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> updateUsuarioAdmin(
            @PathVariable String username,
            @Valid @RequestBody Usuario usuarioDetails) {
        Usuario updatedUsuario = usuarioService.update(username, usuarioDetails);
        return ResponseEntity.ok(updatedUsuario);
    }

    /**
     * Update the current user's own profile.
     *
     * @param usuarioDetails The updated user data
     * @return The updated user
     */
    @PutMapping("/me")
    public ResponseEntity<Usuario> updateCurrentUser(@Valid @RequestBody Usuario usuarioDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario updatedUsuario = usuarioService.update(username, usuarioDetails);
        return ResponseEntity.ok(updatedUsuario);
    }

    /**
     * Disable a user (admin only).
     *
     * @param username The username
     * @return Success message
     */
    @PutMapping("/admin/{username}/disable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> disableUsuario(@PathVariable String username) {
        usuarioService.disableUser(username);
        return ResponseEntity.ok("Usuario deshabilitado exitosamente");
    }

    /**
     * Enable a user (admin only).
     *
     * @param username The username
     * @return Success message
     */
    @PutMapping("/admin/{username}/enable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> enableUsuario(@PathVariable String username) {
        usuarioService.enableUser(username);
        return ResponseEntity.ok("Usuario habilitado exitosamente");
    }

    /**
     * Add a profile to a user (admin only).
     *
     * @param username The username
     * @param perfilId The profile ID
     * @return Success message
     */
    @PostMapping("/admin/{username}/perfiles/{perfilId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addPerfilToUsuario(
            @PathVariable String username,
            @PathVariable Integer perfilId) {
        usuarioService.addPerfil(username, perfilId);
        return ResponseEntity.ok("Perfil agregado exitosamente al usuario");
    }

    /**
     * Remove a profile from a user (admin only).
     *
     * @param username The username
     * @param perfilId The profile ID
     * @return Success message
     */
    @DeleteMapping("/admin/{username}/perfiles/{perfilId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> removePerfilFromUsuario(
            @PathVariable String username,
            @PathVariable Integer perfilId) {
        usuarioService.removePerfil(username, perfilId);
        return ResponseEntity.ok("Perfil eliminado exitosamente del usuario");
    }

    /**
     * Search users by name or surname (admin only).
     *
     * @param query The search query
     * @return List of users matching the search criteria
     */
    @GetMapping("/admin/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> searchUsuarios(@RequestParam String query) {
        List<Usuario> usuarios = usuarioService.search(query);
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Get users by enabled status (admin only).
     *
     * @param enabled The enabled status (1 for enabled, 0 for disabled)
     * @return List of users with the specified enabled status
     */
    @GetMapping("/admin/enabled/{enabled}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> getUsuariosByEnabled(@PathVariable Integer enabled) {
        List<Usuario> usuarios = usuarioService.findByEnabled(enabled);
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Get users by profile (admin only).
     *
     * @param perfilId The profile ID
     * @return List of users with the specified profile
     */
    @GetMapping("/admin/perfil/{perfilId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> getUsuariosByPerfil(@PathVariable Integer perfilId) {
        List<Usuario> usuarios = usuarioService.findByPerfilId(perfilId);
        return ResponseEntity.ok(usuarios);
    }
}