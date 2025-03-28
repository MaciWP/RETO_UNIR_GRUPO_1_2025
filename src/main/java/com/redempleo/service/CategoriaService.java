package com.redempleo.service;

import com.redempleo.model.Categoria;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing category operations.
 */
public interface CategoriaService {

    /**
     * Save a new category.
     *
     * @param categoria The category to save
     * @return The saved category
     */
    Categoria save(Categoria categoria);

    /**
     * Update an existing category.
     *
     * @param id The category ID
     * @param categoriaDetails The updated category data
     * @return The updated category
     */
    Categoria update(Integer id, Categoria categoriaDetails);

    /**
     * Find a category by ID.
     *
     * @param id The category ID
     * @return Optional containing the category if found
     */
    Optional<Categoria> findById(Integer id);

    /**
     * Find a category by name.
     *
     * @param nombre The category name
     * @return Optional containing the category if found
     */
    Optional<Categoria> findByNombre(String nombre);

    /**
     * Find all categories.
     *
     * @return List of all categories
     */
    List<Categoria> findAll();

    /**
     * Delete a category by ID.
     *
     * @param id The category ID
     */
    void deleteById(Integer id);

    /**
     * Find categories by name.
     *
     * @param nombre The search term
     * @return List of categories matching the search criteria
     */
    List<Categoria> findByNombreContaining(String nombre);

    /**
     * Check if a category with the given name exists.
     *
     * @param nombre The category name
     * @return true if a category with the given name exists, false otherwise
     */
    boolean existsByNombre(String nombre);
}