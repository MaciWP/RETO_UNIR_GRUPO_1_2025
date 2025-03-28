package com.redempleo.repository;

import com.redempleo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Categoria entities.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    /**
     * Find a category by its name.
     *
     * @param nombre The category name
     * @return An Optional containing the category if found
     */
    Optional<Categoria> findByNombre(String nombre);

    /**
     * Find categories containing the given text in their name.
     *
     * @param searchTerm The search term
     * @return List of categories matching the search criteria
     */
    List<Categoria> findByNombreContainingIgnoreCase(String searchTerm);

    /**
     * Check if a category with the given name exists.
     *
     * @param nombre The category name
     * @return true if a category with the given name exists, false otherwise
     */
    boolean existsByNombre(String nombre);
}