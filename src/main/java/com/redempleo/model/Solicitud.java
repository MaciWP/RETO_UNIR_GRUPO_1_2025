package com.redempleo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity representing a job application in the system.
 */
@Entity
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "archivo", length = 250)
    private String archivo;

    @Column(name = "comentarios", length = 2000)
    private String comentarios;

    @Column(name = "estado")
    private Integer estado = 0; // 0: Pendiente, 1: Adjudicada, 2: Rechazada, 3: Cancelada por el usuario

    @Column(name = "destacado")
    private Byte destacado = 0; // 0: Falso/No, 1: Verdadero/Si

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vacante", nullable = false)
    private Vacante vacante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
        if (fecha == null) {
            fecha = LocalDate.now();
        }
        if (estado == null) {
            estado = 0; // Pendiente
        }
        if (destacado == null) {
            destacado = 0; // No destacado por defecto
        }
    }
}