package com.redempleo.repository;

import com.redempleo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Empresa entities.
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    /**
     * Find a company by its name.
     *
     * @param razonSocial The company name
     * @return An Optional containing the company if found
     */
    Optional<Empresa> findByRazonSocial(String razonSocial);

    /**
     * Find companies containing the given text in their name or description.
     *
     * @param searchTerm The search term
     * @return List of companies matching the search criteria
     */
    List<Empresa> findByRazonSocialContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
            String searchTerm, String searchTermDesc);

    /**
     * Find companies by country.
     *
     * @param pais The country
     * @return List of companies from the specified country
     */
    List<Empresa> findByPaisIgnoreCase(String pais);
}