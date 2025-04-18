package com.redempleo.service.impl;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Empresa;
import com.redempleo.repository.EmpresaRepository;
import com.redempleo.service.EmpresaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of EmpresaService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Override
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa update(Integer id, Empresa empresaDetails) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con id: " + id));

        empresa.setRazonSocial(empresaDetails.getRazonSocial());
        empresa.setDireccionFiscal(empresaDetails.getDireccionFiscal());
        empresa.setPais(empresaDetails.getPais());

        return empresaRepository.save(empresa);
    }

    @Override
    public Optional<Empresa> findById(Integer id) {
        return empresaRepository.findById(id);
    }

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public List<Empresa> search(String query) {
        return empresaRepository.findByRazonSocialContainingIgnoreCaseOrDescripcionContainingIgnoreCase(query, query);
    }

    @Override
    public List<Empresa> findByPais(String pais) {
        return empresaRepository.findByPaisIgnoreCase(pais);
    }

    @Override
    public boolean existsByRazonSocial(String razonSocial) {
        return empresaRepository.findByRazonSocial(razonSocial).isPresent();
    }
}