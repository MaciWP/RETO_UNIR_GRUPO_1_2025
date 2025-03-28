package com.redempleo.repository;

import com.redempleo.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for managing Perfil entities.
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    /**
     * Find a profile by its name.
     *
     * @param nombre The profile name
     * @return An Optional containing the profile if found
     */
    Optional<Perfil> findByNombre(String nombre);

    /**
     * Check if a profile with the given name exists.
     *
     * @param nombre The profile name
     * @return true if a profile with the given name exists, false otherwise
     */
    boolean existsByNombre(String nombre);
}