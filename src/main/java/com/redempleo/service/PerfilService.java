package com.redempleo.service;

import com.redempleo.model.Perfil;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing profile operations.
 */
public interface PerfilService {

    /**
     * Save a new profile.
     *
     * @param perfil The profile to save
     * @return The saved profile
     */
    Perfil save(Perfil perfil);

    /**
     * Update an existing profile.
     *
     * @param id The profile ID
     * @param perfilDetails The updated profile data
     * @return The updated profile
     */
    Perfil update(Integer id, Perfil perfilDetails);

    /**
     * Find a profile by ID.
     *
     * @param id The profile ID
     * @return Optional containing the profile if found
     */
    Optional<Perfil> findById(Integer id);

    /**
     * Find a profile by name.
     *
     * @param nombre The profile name
     * @return Optional containing the profile if found
     */
    Optional<Perfil> findByNombre(String nombre);

    /**
     * Find all profiles.
     *
     * @return List of all profiles
     */
    List<Perfil> findAll();

    /**
     * Delete a profile by ID.
     *
     * @param id The profile ID
     */
    void deleteById(Integer id);

    /**
     * Check if a profile with the given name exists.
     *
     * @param nombre The profile name
     * @return true if a profile with the given name exists, false otherwise
     */
    boolean existsByNombre(String nombre);
}