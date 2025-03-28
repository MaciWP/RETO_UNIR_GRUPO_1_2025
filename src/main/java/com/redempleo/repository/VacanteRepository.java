package com.redempleo.repository;

import com.redempleo.model.Vacante;
import com.redempleo.model.enums.EstadoVacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing Vacante entities.
 */
@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

    /**
     * Find vacancies by company ID.
     *
     * @param idEmpresa The company ID
     * @return List of vacancies from the specified company
     */
    List<Vacante> findByEmpresaIdEmpresa(Integer idEmpresa);

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
    List<Vacante> findByCategoriaIdCategoria(Integer idCategoria);

    /**
     * Find vacancies by company ID and state.
     *
     * @param idEmpresa The company ID
     * @param estado The vacancy state
     * @return List of vacancies matching the criteria
     */
    List<Vacante> findByEmpresaIdEmpresaAndEstado(Integer idEmpresa, EstadoVacante estado);

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
     * @return List of vacancies matching the search criteria
     */
    @Query("SELECT v FROM Vacante v " +
            "WHERE (:estado IS NULL OR v.estado = :estado) " +
            "AND (:idCategoria IS NULL OR v.categoria.idCategoria = :idCategoria) " +
            "AND (:idEmpresa IS NULL OR v.empresa.idEmpresa = :idEmpresa) " +
            "AND (:searchTerm IS NULL OR LOWER(v.nombre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(v.descripcion) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Vacante> searchVacantes(
            @Param("estado") EstadoVacante estado,
            @Param("idCategoria") Integer idCategoria,
            @Param("idEmpresa") Integer idEmpresa,
            @Param("searchTerm") String searchTerm,
            Pageable pageable);
}