package com.redempleo.service;

import com.redempleo.model.Vacante;
import com.redempleo.model.enums.EstadoVacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing vacancy operations.
 */
public interface VacanteService {

    /**
     * Save a new vacancy.
     *
     * @param vacante The vacancy to save
     * @return The saved vacancy
     */
    Vacante save(Vacante vacante);

    /**
     * Update an existing vacancy.
     *
     * @param id The vacancy ID
     * @param vacanteDetails The updated vacancy data
     * @return The updated vacancy
     */
    Vacante update(Integer id, Vacante vacanteDetails);

    /**
     * Find a vacancy by ID.
     *
     * @param id The vacancy ID
     * @return Optional containing the vacancy if found
     */
    Optional<Vacante> findById(Integer id);

    /**
     * Find all vacancies.
     *
     * @return List of all vacancies
     */
    List<Vacante> findAll();

    /**
     * Find all vacancies with pagination.
     *
     * @param pageable Pagination information
     * @return Page of vacancies
     */
    Page<Vacante> findAll(Pageable pageable);

    /**
     * Delete a vacancy by ID.
     *
     * @param id The vacancy ID
     */
    void deleteById(Integer id);

    /**
     * Cancel a vacancy by ID.
     *
     * @param id The vacancy ID
     * @return The updated vacancy
     */
    Vacante cancelVacancy(Integer id);

    /**
     * Assign a vacancy to a candidate.
     *
     * @param idVacante The vacancy ID
     * @param username The username of the candidate
     * @return The updated vacancy
     */
    Vacante assignVacancy(Integer idVacante, String username);

    /**
     * Find vacancies by company ID.
     *
     * @param idEmpresa The company ID
     * @return List of vacancies from the specified company
     */
    List<Vacante> findByEmpresaId(Integer idEmpresa);

    /**
     * Find vacancies by state.
     *
     * @param estado The vacancy state
     * @return List of vacancies with the specified state
     */
    List<Vacante> findByEstado(EstadoVacante estado);

    /**
     * Find paginated vacancies by state.
     *
     * @param estado The vacancy state
     * @param pageable Pagination information
     * @return Page of vacancies with the specified state
     */
    Page<Vacante> findByEstado(EstadoVacante estado, Pageable pageable);

    /**
     * Find vacancies by category ID.
     *
     * @param idCategoria The category ID
     * @return List of vacancies from the specified category
     */
    List<Vacante> findByCategoriaId(Integer idCategoria);

    /**
     * Find vacancies by company ID and state.
     *
     * @param idEmpresa The company ID
     * @param estado The vacancy state
     * @return List of vacancies matching the criteria
     */
    List<Vacante> findByEmpresaIdAndEstado(Integer idEmpresa, EstadoVacante estado);

    /**
     * Find vacancies by featured status.
     *
     * @param destacado The featured status (1 for featured, 0 for not featured)
     * @return List of vacancies with the specified featured status
     */
    List<Vacante> findByDestacado(Integer destacado);

    /**
     * Search vacancies with multiple filters.
     *
     * @param estado The vacancy state (optional)
     * @param idCategoria The category ID (optional)
     * @param idEmpresa The company ID (optional)
     * @param searchTerm The search term (optional)
     * @param pageable Pagination information
     * @return Page of vacancies matching the search criteria
     */
    Page<Vacante> searchVacantes(
            EstadoVacante estado,
            Integer idCategoria,
            Integer idEmpresa,
            String searchTerm,
            Pageable pageable);
}