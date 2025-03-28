package com.redempleo.controller;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Categoria;
import com.redempleo.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling category operations.
 */
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    /**
     * Get all categories.
     *
     * @return List of all categories
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }

    /**
     * Get a category by ID.
     *
     * @param id The category ID
     * @return The category
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
        return ResponseEntity.ok(categoria);
    }

    /**
     * Create a new category.
     *
     * @param categoria The category to create
     * @return The created category
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria savedCategoria = categoriaService.save(categoria);
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED);
    }

    /**
     * Update a category.
     *
     * @param id The category ID
     * @param categoriaDetails The updated category data
     * @return The updated category
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Categoria> updateCategoria(
            @PathVariable Integer id,
            @Valid @RequestBody Categoria categoriaDetails) {
        Categoria updatedCategoria = categoriaService.update(id, categoriaDetails);
        return ResponseEntity.ok(updatedCategoria);
    }

    /**
     * Delete a category.
     *
     * @param id The category ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Find categories by name.
     *
     * @param nombre The search term
     * @return List of categories matching the search criteria
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Categoria>> searchCategorias(@RequestParam String nombre) {
        List<Categoria> categorias = categoriaService.findByNombreContaining(nombre);
        return ResponseEntity.ok(categorias);
    }

    /**
     * Public endpoint to get categories.
     *
     * @return List of all categories
     */
    @GetMapping("/publicas")
    public ResponseEntity<List<Categoria>> getCategoriasPublicas() {
        List<Categoria> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }
}