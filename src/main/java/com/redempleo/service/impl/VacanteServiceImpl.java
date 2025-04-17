package com.redempleo.service.impl;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Solicitud;
import com.redempleo.model.Vacante;
import com.redempleo.model.enums.EstadoVacante;
import com.redempleo.repository.SolicitudRepository;
import com.redempleo.repository.VacanteRepository;
import com.redempleo.service.VacanteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of VacanteService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class VacanteServiceImpl implements VacanteService {

    private final VacanteRepository vacanteRepository;
    private final SolicitudRepository solicitudRepository;

    @Override
    public Vacante save(Vacante vacante) {
        if (vacante.getEstado() == null) {
            vacante.setEstado(EstadoVacante.CREADA);
        }
        return vacanteRepository.save(vacante);
    }

    @Override
    public Vacante update(Integer id, Vacante vacanteDetails) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacante no encontrada con id: " + id));

        vacante.setNombre(vacanteDetails.getNombre());
        vacante.setDescripcion(vacanteDetails.getDescripcion());
        vacante.setFecha(vacanteDetails.getFecha());
        vacante.setSalario(vacanteDetails.getSalario());
        vacante.setDestacado(vacanteDetails.getDestacado());
        vacante.setImagen(vacanteDetails.getImagen());
        vacante.setDetalles(vacanteDetails.getDetalles());

        if (vacanteDetails.getCategoria() != null) {
            vacante.setCategoria(vacanteDetails.getCategoria());
        }

        return vacanteRepository.save(vacante);
    }

    @Override
    public Optional<Vacante> findById(Integer id) {
        return vacanteRepository.findById(id);
    }

    @Override
    public List<Vacante> findAll() {
        return vacanteRepository.findAll();
    }

    @Override
    public Page<Vacante> findAll(Pageable pageable) {
        return vacanteRepository.findAll(pageable);
    }

    @Override
    public Vacante cancelVacancy(Integer id) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacante no encontrada con id: " + id));

        vacante.setEstado(EstadoVacante.CANCELADA);
        return vacanteRepository.save(vacante);
    }

    @Override
    public Vacante assignVacancy(Integer idVacante, String username) {
        Vacante vacante = vacanteRepository.findById(idVacante)
                .orElseThrow(() -> new ResourceNotFoundException("Vacante no encontrada con id: " + idVacante));

        Solicitud solicitud = solicitudRepository.findByVacanteIdVacanteAndUsuarioUsername(idVacante, username)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada para la vacante: " + idVacante + " y usuario: " + username));

        vacante.setEstado(EstadoVacante.ASIGNADA);
        vacanteRepository.save(vacante);

        // Update the solicitud status to "adjudicada" (1)
        solicitud.setEstado(1);
        solicitudRepository.save(solicitud);

        return vacante;
    }

    @Override
    public List<Vacante> findByEmpresaId(Integer idEmpresa) {
        return vacanteRepository.findByEmpresaIdEmpresa(idEmpresa);
    }

    @Override
    public List<Vacante> findByEstado(EstadoVacante estado) {
        return vacanteRepository.findByEstado(estado);
    }

    @Override
    public Page<Vacante> findByEstado(EstadoVacante estado, Pageable pageable) {
        return vacanteRepository.findByEstado(estado, pageable);
    }

    @Override
    public List<Vacante> findByCategoriaId(Integer idCategoria) {
        return vacanteRepository.findByCategoriaIdCategoria(idCategoria);
    }

    @Override
    public List<Vacante> findByEmpresaIdAndEstado(Integer idEmpresa, EstadoVacante estado) {
        return vacanteRepository.findByEmpresaIdEmpresaAndEstado(idEmpresa, estado);
    }

    @Override
    public List<Vacante> findByDestacado(Integer destacado) {
        return vacanteRepository.findByDestacado(destacado);
    }

    @Override
    public Page<Vacante> searchVacantes(
            EstadoVacante estado,
            Integer idCategoria,
            Integer idEmpresa,
            String searchTerm,
            Pageable pageable) {
        return vacanteRepository.searchVacantes(estado, idCategoria, idEmpresa, searchTerm, pageable);
    }
}