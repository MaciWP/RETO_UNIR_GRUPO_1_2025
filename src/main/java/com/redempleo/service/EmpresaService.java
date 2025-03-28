package com.redempleo.service;

import com.redempleo.model.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing company operations.
 */
public interface EmpresaService {

    /**
     * Save a new company.
     *
     * @param empresa The company to save
     * @return The saved company
     */
    Empresa save(Empresa empresa);

    /**
     * Update an existing company.
     *
     * @param id The company ID
     * @param empresaDetails The updated company data
     * @return The updated company
     */
    Empresa update(Integer id, Empresa empresaDetails);

    /**
     * Find a company by ID.
     *
     * @param id The company ID
     * @return Optional containing the company if found
     */
    Optional<Empresa> findById(Integer id);

    /**
     * Find all companies.
     *
     * @return List of all companies
     */
    List<Empresa> findAll();

    /**
     * Delete a company by ID.
     *
     * @param id The company ID
     */
    void deleteById(Integer id);

    /**
     * Find companies by name or description.
     *
     * @param query The search query
     * @return List of companies matching the search criteria
     */
    List<Empresa> search(String query);

    /**
     * Find companies by country.
     *
     * @param pais The country
     * @return List of companies from the specified country
     */
    List<Empresa> findByPais(String pais);

    /**
     * Check if a company with the given name exists.
     *
     * @param razonSocial The company name
     * @return true if a company with the given name exists, false otherwise
     */
    boolean existsByRazonSocial(String razonSocial);
}