package com.redempleo.repository;

import com.redempleo.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Solicitud entities.
 */
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    /**
     * Find applications by vacancy ID.
     *
     * @param idVacante The vacancy ID
     * @return List of applications for the specified vacancy
     */
    List<Solicitud> findByVacanteIdVacante(Integer idVacante);

    /**
     * Find applications by user.
     *
     * @param username The username
     * @return List of applications from the specified user
     */
    List<Solicitud> findByUsuarioUsername(String username);

    /**
     * Find applications by state.
     *
     * @param estado The application state
     * @return List of applications with the specified state
     */
    List<Solicitud> findByEstado(Integer estado);

    /**
     * Find paginated applications by vacancy ID.
     *
     * @param idVacante The vacancy ID
     * @param pageable Pagination information
     * @return Page of applications for the specified vacancy
     */
    Page<Solicitud> findByVacanteIdVacante(Integer idVacante, Pageable pageable);

    /**
     * Find paginated applications by user.
     *
     * @param username The username
     * @param pageable Pagination information
     * @return Page of applications from the specified user
     */
    Page<Solicitud> findByUsuarioUsername(String username, Pageable pageable);

    /**
     * Find a specific application by vacancy ID and username.
     *
     * @param idVacante The vacancy ID
     * @param username The username
     * @return An Optional containing the application if found
     */
    Optional<Solicitud> findByVacanteIdVacanteAndUsuarioUsername(Integer idVacante, String username);

    /**
     * Count the number of applications for a specific vacancy.
     *
     * @param idVacante The vacancy ID
     * @return The number of applications
     */
    long countByVacanteIdVacante(Integer idVacante);

    /**
     * Find applications by company.
     *
     * @param idEmpresa The company ID
     * @return List of applications for vacancies from the specified company
     */
    List<Solicitud> findByVacanteEmpresaIdEmpresa(Integer idEmpresa);
}