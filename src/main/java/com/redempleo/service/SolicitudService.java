package com.redempleo.service;

import com.redempleo.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing application operations.
 */
public interface SolicitudService {

    /**
     * Save a new application.
     *
     * @param solicitud The application to save
     * @return The saved application
     */
    Solicitud save(Solicitud solicitud);

    /**
     * Save a new application with a CV file.
     *
     * @param solicitud The application to save
     * @param cvFile The CV file
     * @return The saved application
     */
    Solicitud saveWithFile(Solicitud solicitud, MultipartFile cvFile);

    /**
     * Update an existing application.
     *
     * @param id The application ID
     * @param solicitudDetails The updated application data
     * @return The updated application
     */
    Solicitud update(Integer id, Solicitud solicitudDetails);

    /**
     * Find an application by ID.
     *
     * @param id The application ID
     * @return Optional containing the application if found
     */
    Optional<Solicitud> findById(Integer id);

    /**
     * Find all applications.
     *
     * @return List of all applications
     */
    List<Solicitud> findAll();

    /**
     * Delete an application by ID.
     *
     * @param id The application ID
     */
    void deleteById(Integer id);

    /**
     * Cancel an application by ID.
     *
     * @param id The application ID
     * @return The updated application
     */
    Solicitud cancelSolicitud(Integer id);

    /**
     * Update the state of an application.
     *
     * @param id The application ID
     * @param estado The new state
     * @return The updated application
     */
    Solicitud updateEstado(Integer id, Integer estado);

    /**
     * Find applications by vacancy ID.
     *
     * @param idVacante The vacancy ID
     * @return List of applications for the specified vacancy
     */
    List<Solicitud> findByVacanteId(Integer idVacante);

    /**
     * Find paginated applications by vacancy ID.
     *
     * @param idVacante The vacancy ID
     * @param pageable Pagination information
     * @return Page of applications for the specified vacancy
     */
    Page<Solicitud> findByVacanteId(Integer idVacante, Pageable pageable);

    /**
     * Find applications by user.
     *
     * @param username The username
     * @return List of applications from the specified user
     */
    List<Solicitud> findByUsuarioUsername(String username);

    /**
     * Find paginated applications by user.
     *
     * @param username The username
     * @param pageable Pagination information
     * @return Page of applications from the specified user
     */
    Page<Solicitud> findByUsuarioUsername(String username, Pageable pageable);

    /**
     * Find applications by state.
     *
     * @param estado The application state
     * @return List of applications with the specified state
     */
    List<Solicitud> findByEstado(Integer estado);

    /**
     * Find a specific application by vacancy ID and username.
     *
     * @param idVacante The vacancy ID
     * @param username The username
     * @return Optional containing the application if found
     */
    Optional<Solicitud> findByVacanteIdAndUsuarioUsername(Integer idVacante, String username);

    /**
     * Count the number of applications for a specific vacancy.
     *
     * @param idVacante The vacancy ID
     * @return The number of applications
     */
    long countByVacanteId(Integer idVacante);

    /**
     * Find applications by company.
     *
     * @param idEmpresa The company ID
     * @return List of applications for vacancies from the specified company
     */
    List<Solicitud> findByEmpresaId(Integer idEmpresa);

    /**
     * Check if a user has already applied to a vacancy.
     *
     * @param idVacante The vacancy ID
     * @param username The username
     * @return true if the user has already applied, false otherwise
     */
    boolean hasUserApplied(Integer idVacante, String username);
}