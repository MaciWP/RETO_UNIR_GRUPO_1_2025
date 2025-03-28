package com.redempleo.service.impl;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Solicitud;
import com.redempleo.repository.SolicitudRepository;
import com.redempleo.service.SolicitudService;
import com.redempleo.util.FileStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of SolicitudService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Solicitud save(Solicitud solicitud) {
        if (solicitud.getFecha() == null) {
            solicitud.setFecha(LocalDate.now());
        }
        if (solicitud.getEstado() == null) {
            solicitud.setEstado(0); // Estado pendiente por defecto
        }
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud saveWithFile(Solicitud solicitud, MultipartFile cvFile) {
        if (cvFile != null && !cvFile.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + cvFile.getOriginalFilename();
            String filePath = fileStorageService.storeFile(cvFile, fileName);
            solicitud.setArchivo(filePath);
        }
        return save(solicitud);
    }

    @Override
    public Solicitud update(Integer id, Solicitud solicitudDetails) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        solicitud.setComentarios(solicitudDetails.getComentarios());
        if (solicitudDetails.getArchivo() != null) {
            solicitud.setArchivo(solicitudDetails.getArchivo());
        }
        if (solicitudDetails.getEstado() != null) {
            solicitud.setEstado(solicitudDetails.getEstado());
        }

        return solicitudRepository.save(solicitud);
    }

    @Override
    public Optional<Solicitud> findById(Integer id) {
        return solicitudRepository.findById(id);
    }

    @Override
    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        // Eliminar el archivo si existe
        if (solicitud.getArchivo() != null) {
            fileStorageService.deleteFile(solicitud.getArchivo());
        }

        solicitudRepository.deleteById(id);
    }

    @Override
    public Solicitud cancelSolicitud(Integer id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        solicitud.setEstado(3); // Estado cancelado
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud updateEstado(Integer id, Integer estado) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        solicitud.setEstado(estado);
        return solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> findByVacanteId(Integer idVacante) {
        return solicitudRepository.findByVacanteIdVacante(idVacante);
    }

    @Override
    public Page<Solicitud> findByVacanteId(Integer idVacante, Pageable pageable) {
        return solicitudRepository.findByVacanteIdVacante(idVacante, pageable);
    }

    @Override
    public List<Solicitud> findByUsuarioUsername(String username) {
        return solicitudRepository.findByUsuarioUsername(username);
    }

    @Override
    public Page<Solicitud> findByUsuarioUsername(String username, Pageable pageable) {
        return solicitudRepository.findByUsuarioUsername(username, pageable);
    }

    @Override
    public List<Solicitud> findByEstado(Integer estado) {
        return solicitudRepository.findByEstado(estado);
    }

    @Override
    public Optional<Solicitud> findByVacanteIdAndUsuarioUsername(Integer idVacante, String username) {
        return solicitudRepository.findByVacanteIdVacanteAndUsuarioUsername(idVacante, username);
    }

    @Override
    public long countByVacanteId(Integer idVacante) {
        return solicitudRepository.countByVacanteIdVacante(idVacante);
    }

    @Override
    public List<Solicitud> findByEmpresaId(Integer idEmpresa) {
        return solicitudRepository.findByVacanteEmpresaIdEmpresa(idEmpresa);
    }

    @Override
    public boolean hasUserApplied(Integer idVacante, String username) {
        return solicitudRepository
                .findByVacanteIdVacanteAndUsuarioUsername(idVacante, username)
                .isPresent();
    }
}