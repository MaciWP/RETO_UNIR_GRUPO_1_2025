package com.redempleo.model;

import com.redempleo.model.enums.EstadoVacante;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a job vacancy in the system.
 */
@Entity
@Table(name = "vacantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacante")
    private Integer idVacante;

    @NotBlank(message = "El nombre de la vacante es obligatorio")
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "salario")
    private Double salario;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoVacante estado = EstadoVacante.CREADA;

    @Column(name = "destacado")
    private Integer destacado;

    @Column(name = "imagen", length = 250)
    private String imagen;

    @Column(name = "detalles", columnDefinition = "TEXT")
    private String detalles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "vacante")
    private Set<Solicitud> solicitudes = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (fecha == null) {
            fecha = LocalDate.now();
        }
        if (estado == null) {
            estado = EstadoVacante.CREADA;
        }
    }
}