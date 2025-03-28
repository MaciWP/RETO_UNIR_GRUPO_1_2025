package com.redempleo.service.impl;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Perfil;
import com.redempleo.repository.PerfilRepository;
import com.redempleo.service.PerfilService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of PerfilService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;

    @Override
    public Perfil save(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    public Perfil update(Integer id, Perfil perfilDetails) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id: " + id));

        perfil.setNombre(perfilDetails.getNombre());

        return perfilRepository.save(perfil);
    }

    @Override
    public Optional<Perfil> findById(Integer id) {
        return perfilRepository.findById(id);
    }

    @Override
    public Optional<Perfil> findByNombre(String nombre) {
        return perfilRepository.findByNombre(nombre);
    }

    @Override
    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        perfilRepository.deleteById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return perfilRepository.existsByNombre(nombre);
    }
}