package com.redempleo.controller;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Solicitud;
import com.redempleo.service.SolicitudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller for handling application operations.
 */
@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    /**
     * Get all applications (admin only).
     *
     * @return List of all applications
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Solicitud>> getAllSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.findAll();
        return ResponseEntity.ok(solicitudes);
    }

    /**
     * Get an application by ID.
     *
     * @param id The application ID
     * @return The application
     */
    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Integer id) {
        Solicitud solicitud = solicitudService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        // Verificar permisos
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isEmpresa = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_EMPRESA"));

        // Solo el usuario propietario, administradores o la empresa pueden ver la solicitud
        if (!isAdmin && !isEmpresa && !solicitud.getUsuario().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(solicitud);
    }

    /**
     * Create a new application.
     *
     * @param solicitud The application to create
     * @return The created application
     */
    @PostMapping
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Solicitud> createSolicitud(@Valid @RequestBody Solicitud solicitud) {
        // Asignar el usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        solicitud.getUsuario().setUsername(username);

        Solicitud savedSolicitud = solicitudService.save(solicitud);
        return new ResponseEntity<>(savedSolicitud, HttpStatus.CREATED);
    }

    /**
     * Create a new application with CV file.
     *
     * @param solicitud The application data
     * @param cvFile The CV file
     * @return The created application
     */
    @PostMapping(value = "/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Solicitud> createSolicitudWithFile(
            @RequestPart("solicitud") @Valid Solicitud solicitud,
            @RequestPart(value = "cvFile", required = false) MultipartFile cvFile) {

        // Asignar el usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        solicitud.getUsuario().setUsername(username);

        Solicitud savedSolicitud = solicitudService.saveWithFile(solicitud, cvFile);
        return new ResponseEntity<>(savedSolicitud, HttpStatus.CREATED);
    }

    /**
     * Cancel an application.
     *
     * @param id The application ID
     * @return The canceled application
     */
    @PutMapping("/{id}/cancelar")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Solicitud> cancelSolicitud(@PathVariable Integer id) {
        // Verificar permisos
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Solicitud solicitud = solicitudService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        // Solo el usuario propietario puede cancelar su solicitud
        if (!solicitud.getUsuario().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Solicitud canceledSolicitud = solicitudService.cancelSolicitud(id);
        return ResponseEntity.ok(canceledSolicitud);
    }

    /**
     * Update the state of an application (admin or empresa only).
     *
     * @param id The application ID
     * @param estado The new state
     * @return The updated application
     */
    @PutMapping("/{id}/estado/{estado}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Solicitud> updateEstadoSolicitud(
            @PathVariable Integer id,
            @PathVariable Integer estado) {
        Solicitud updatedSolicitud = solicitudService.updateEstado(id, estado);
        return ResponseEntity.ok(updatedSolicitud);
    }

    /**
     * Delete an application (admin only).
     *
     * @param id The application ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Integer id) {
        solicitudService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get applications by vacancy.
     *
     * @param idVacante The vacancy ID
     * @param pageable Pagination information
     * @return Page of applications for the specified vacancy
     */
    @GetMapping("/vacante/{idVacante}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<Page<Solicitud>> getSolicitudesByVacante(
            @PathVariable Integer idVacante,
            Pageable pageable) {
        Page<Solicitud> solicitudes = solicitudService.findByVacanteId(idVacante, pageable);
        return ResponseEntity.ok(solicitudes);
    }

    /**
     * Get applications by user.
     *
     * @param pageable Pagination information
     * @return Page of applications from the current user
     */
    @GetMapping("/mis-solicitudes")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Page<Solicitud>> getMisSolicitudes(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Page<Solicitud> solicitudes = solicitudService.findByUsuarioUsername(username, pageable);
        return ResponseEntity.ok(solicitudes);
    }

    /**
     * Get applications by state.
     *
     * @param estado The application state
     * @return List of applications with the specified state
     */
    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<List<Solicitud>> getSolicitudesByEstado(@PathVariable Integer estado) {
        List<Solicitud> solicitudes = solicitudService.findByEstado(estado);
        return ResponseEntity.ok(solicitudes);
    }

    /**
     * Check if a user has already applied to a vacancy.
     *
     * @param idVacante The vacancy ID
     * @return Boolean indicating if the user has already applied
     */
    @GetMapping("/check-aplicacion/{idVacante}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Boolean> hasUserApplied(@PathVariable Integer idVacante) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean hasApplied = solicitudService.hasUserApplied(idVacante, username);
        return ResponseEntity.ok(hasApplied);
    }

    /**
     * Get applications by company.
     *
     * @param idEmpresa The company ID
     * @return List of applications for vacancies from the specified company
     */
    @GetMapping("/empresa/{idEmpresa}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public ResponseEntity<List<Solicitud>> getSolicitudesByEmpresa(@PathVariable Integer idEmpresa) {
        List<Solicitud> solicitudes = solicitudService.findByEmpresaId(idEmpresa);
        return ResponseEntity.ok(solicitudes);
    }
}