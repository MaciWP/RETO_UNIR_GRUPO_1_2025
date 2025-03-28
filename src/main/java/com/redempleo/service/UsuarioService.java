package com.redempleo.service;

import com.redempleo.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing user operations.
 */
public interface UsuarioService {

    /**
     * Save a new user.
     *
     * @param usuario The user to save
     * @return The saved user
     */
    Usuario save(Usuario usuario);

    /**
     * Update an existing user.
     *
     * @param username The username
     * @param usuarioDetails The updated user data
     * @return The updated user
     */
    Usuario update(String username, Usuario usuarioDetails);

    /**
     * Find a user by username.
     *
     * @param username The username
     * @return Optional containing the user if found
     */
    Optional<Usuario> findByUsername(String username);

    /**
     * Find a user by email.
     *
     * @param email The user's email
     * @return Optional containing the user if found
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Find all users.
     *
     * @return List of all users
     */
    List<Usuario> findAll();

    /**
     * Delete a user by username.
     *
     * @param username The username
     */
    void deleteByUsername(String username);

    /**
     * Disable a user by username.
     *
     * @param username The username
     * @return The updated user
     */
    Usuario disableUser(String username);

    /**
     * Enable a user by username.
     *
     * @param username The username
     * @return The updated user
     */
    Usuario enableUser(String username);

    /**
     * Find users by enabled status.
     *
     * @param enabled The enabled status (1 for enabled, 0 for disabled)
     * @return List of users with the specified enabled status
     */
    List<Usuario> findByEnabled(Integer enabled);

    /**
     * Find users by profile ID.
     *
     * @param perfilId The profile ID
     * @return List of users with the specified profile
     */
    List<Usuario> findByPerfilId(Integer perfilId);

    /**
     * Find users by name or surname.
     *
     * @param query The search query
     * @return List of users matching the search criteria
     */
    List<Usuario> search(String query);

    /**
     * Add a profile to a user.
     *
     * @param username The username
     * @param perfilId The profile ID
     * @return The updated user
     */
    Usuario addPerfil(String username, Integer perfilId);

    /**
     * Remove a profile from a user.
     *
     * @param username The username
     * @param perfilId The profile ID
     * @return The updated user
     */
    Usuario removePerfil(String username, Integer perfilId);

    /**
     * Check if a user with the given email exists.
     *
     * @param email The email
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Check if a user with the given username exists.
     *
     * @param username The username
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);
}