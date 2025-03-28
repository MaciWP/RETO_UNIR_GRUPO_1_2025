package com.redempleo.controller;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Empresa;
import com.redempleo.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling company operations.
 */
@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    /**
     * Get all companies.
     *
     * @return List of all companies
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    /**
     * Get a company by ID.
     *
     * @param id The company ID
     * @return The company
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPRESA')")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Integer id) {
        Empresa empresa = empresaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con id: " + id));
        return ResponseEntity.ok(empresa);
    }

    /**
     * Create a new company.
     *
     * @param empresa The company to create
     * @return The created company
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Empresa> createEmpresa(@Valid @RequestBody Empresa empresa) {
        Empresa savedEmpresa = empresaService.save(empresa);
        return new ResponseEntity<>(savedEmpresa, HttpStatus.CREATED);
    }

    /**
     * Update a company.
     *
     * @param id The company ID
     * @param empresaDetails The updated company data
     * @return The updated company
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Empresa> updateEmpresa(
            @PathVariable Integer id,
            @Valid @RequestBody Empresa empresaDetails) {
        Empresa updatedEmpresa = empresaService.update(id, empresaDetails);
        return ResponseEntity.ok(updatedEmpresa);
    }

    /**
     * Delete a company.
     *
     * @param id The company ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Integer id) {
        empresaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Search companies by name or description.
     *
     * @param query The search query
     * @return List of companies matching the search criteria
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Empresa>> searchEmpresas(@RequestParam String query) {
        List<Empresa> empresas = empresaService.search(query);
        return ResponseEntity.ok(empresas);
    }

    /**
     * Get companies by country.
     *
     * @param pais The country
     * @return List of companies from the specified country
     */
    @GetMapping("/pais/{pais}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Empresa>> getEmpresasByPais(@PathVariable String pais) {
        List<Empresa> empresas = empresaService.findByPais(pais);
        return ResponseEntity.ok(empresas);
    }

    /**
     * Public endpoint to get companies.
     *
     * @return List of all companies
     */
    @GetMapping("/publicas")
    public ResponseEntity<List<Empresa>> getEmpresasPublicas() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok(empresas);
    }
}