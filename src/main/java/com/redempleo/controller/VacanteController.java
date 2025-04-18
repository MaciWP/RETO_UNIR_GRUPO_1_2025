package com.redempleo.controller;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Vacante;
import com.redempleo.model.enums.EstadoVacante;
import com.redempleo.service.VacanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling vacancy operations.
 */
@RestController
@RequestMapping("/api/vacantes")
@RequiredArgsConstructor
public class VacanteController {

    private final VacanteService vacanteService;

    /**
     * Get all vacancies.
     *
     * @param pageable Pagination information
     * @return Page of vacancies
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Page<Vacante>> getAllVacantes(Pageable pageable) {
        Page<Vacante> vacantes = vacanteService.findAll(pageable);
        return ResponseEntity.ok(vacantes);
    }

    /**
     * Get a vacancy by ID.
     *
     * @param id The vacancy ID
     * @return The vacancy
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vacante> getVacanteById(@PathVariable Integer id) {
        Vacante vacante = vacanteService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacante no encontrada con id: " + id));
        return ResponseEntity.ok(vacante);
    }

    /**
     * Create a new vacancy.
     *
     * @param vacante The vacancy to create
     * @return The created vacancy
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Vacante> createVacante(@Valid @RequestBody Vacante vacante) {
        Vacante savedVacante = vacanteService.save(vacante);
        return new ResponseEntity<>(savedVacante, HttpStatus.CREATED);
    }

    /**
     * Update a vacancy.
     *
     * @param id The vacancy ID
     * @param vacanteDetails The updated vacancy data
     * @return The updated vacancy
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Vacante> updateVacante(
            @PathVariable Integer id,
            @Valid @RequestBody Vacante vacanteDetails) {
        Vacante updatedVacante = vacanteService.update(id, vacanteDetails);
        return ResponseEntity.ok(updatedVacante);
    }

    /**
     * Cancel a vacancy.
     *
     * @param id The vacancy ID
     * @return The canceled vacancy
     */
    @PutMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Vacante> cancelVacante(@PathVariable Integer id) {
        Vacante canceledVacante = vacanteService.cancelVacancy(id);
        return ResponseEntity.ok(canceledVacante);
    }

    /**
     * Assign a vacancy to a candidate.
     *
     * @param id The vacancy ID
     * @param username The username of the candidate
     * @return The assigned vacancy
     */
    @PutMapping("/{id}/asignar/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Vacante> assignVacante(
            @PathVariable Integer id,
            @PathVariable String username) {
        Vacante assignedVacante = vacanteService.assignVacancy(id, username);
        return ResponseEntity.ok(assignedVacante);
    }

    /**
     * Delete a vacancy.
     * Now it cancels the vacancy instead of deleting it.
     *
     * @param id The vacancy ID
     * @return The canceled vacancy
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Vacante> deleteVacante(@PathVariable Integer id) {
        Vacante canceledVacante = vacanteService.cancelVacancy(id);
        return ResponseEntity.ok(canceledVacante);
    }

    /**
     * Get vacancies by company.
     *
     * @param idEmpresa The company ID
     * @return List of vacancies from the specified company
     */
    @GetMapping("/empresa/{idEmpresa}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<List<Vacante>> getVacantesByEmpresa(@PathVariable Integer idEmpresa) {
        List<Vacante> vacantes = vacanteService.findByEmpresaId(idEmpresa);
        return ResponseEntity.ok(vacantes);
    }

    /**
     * Get vacancies by company and state.
     *
     * @param idEmpresa The company ID
     * @param estado The vacancy state
     * @return List of vacancies matching the criteria
     */
    @GetMapping("/empresa/{idEmpresa}/estado/{estado}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<List<Vacante>> getVacantesByEmpresaAndEstado(
            @PathVariable Integer idEmpresa,
            @PathVariable EstadoVacante estado) {
        List<Vacante> vacantes = vacanteService.findByEmpresaIdAndEstado(idEmpresa, estado);
        return ResponseEntity.ok(vacantes);
    }

    /**
     * Get vacancies by category.
     *
     * @param idCategoria The category ID
     * @return List of vacancies from the specified category
     */
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Vacante>> getVacantesByCategoria(@PathVariable Integer idCategoria) {
        List<Vacante> vacantes = vacanteService.findByCategoriaId(idCategoria);
        return ResponseEntity.ok(vacantes);
    }

    /**
     * Get vacancies by state.
     *
     * @param estado The vacancy state
     * @param pageable Pagination information
     * @return Page of vacancies with the specified state
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<Page<Vacante>> getVacantesByEstado(
            @PathVariable EstadoVacante estado,
            Pageable pageable) {
        Page<Vacante> vacantes = vacanteService.findByEstado(estado, pageable);
        return ResponseEntity.ok(vacantes);
    }

    /**
     * Get featured vacancies.
     *
     * @return List of featured vacancies
     */
    @GetMapping("/destacadas")
    public ResponseEntity<List<Vacante>> getVacantesDestacadas() {
        List<Vacante> vacantes = vacanteService.findByDestacado(1);
        return ResponseEntity.ok(vacantes);
    }

    /**
     * Search vacancies with filters.
     *
     * @param estado The vacancy state (optional)
     * @param idCategoria The category ID (optional)
     * @param idEmpresa The company ID (optional)
     * @param searchTerm The search term (optional)
     * @param pageable Pagination information
     * @return Page of vacancies matching the search criteria
     */
    @GetMapping("/search")
    public ResponseEntity<Page<Vacante>> searchVacantes(
            @RequestParam(required = false) EstadoVacante estado,
            @RequestParam(required = false) Integer idCategoria,
            @RequestParam(required = false) Integer idEmpresa,
            @RequestParam(required = false) String searchTerm,
            Pageable pageable) {
        Page<Vacante> vacantes = vacanteService.searchVacantes(estado, idCategoria, idEmpresa, searchTerm, pageable);
        return ResponseEntity.ok(vacantes);
    }

    /**
     * Get public vacancies (CREADA state).
     *
     * @param pageable Pagination information
     * @return Page of public vacancies
     */
    @GetMapping("/publicas")
    public ResponseEntity<Page<Vacante>> getVacantesPublicas(Pageable pageable) {
        Page<Vacante> vacantes = vacanteService.findByEstado(EstadoVacante.CREADA, pageable);
        return ResponseEntity.ok(vacantes);
    }
}