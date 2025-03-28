package com.redempleo.repository;

import com.redempleo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Usuario entities.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    /**
     * Find a user by email.
     *
     * @param email The user's email
     * @return An Optional containing the user if found
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Find users with enabled status.
     *
     * @param enabled The enabled status (1 for enabled, 0 for disabled)
     * @return List of users with the specified enabled status
     */
    List<Usuario> findByEnabled(Integer enabled);

    /**
     * Find all users with a specific profile.
     *
     * @param perfilId The profile ID
     * @return List of users with the specified profile
     */
    @Query("SELECT u FROM Usuario u JOIN u.perfiles p WHERE p.idPerfil = :perfilId")
    List<Usuario> findByPerfilId(Integer perfilId);

    /**
     * Find users by name or surname.
     *
     * @param searchTerm The search term
     * @return List of users matching the search criteria
     */
    List<Usuario> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(
            String searchTerm, String searchTermApellidos);

    /**
     * Check if a user with the given email exists.
     *
     * @param email The email
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);
}