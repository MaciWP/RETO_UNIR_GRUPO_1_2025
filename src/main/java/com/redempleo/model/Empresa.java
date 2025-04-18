package com.redempleo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a company in the system.
 */
@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(min = 3, max = 100, message = "La razón social debe tener entre 3 y 100 caracteres")
    @Column(name = "razon_social", nullable = false, length = 100)
    private String razonSocial;

    @NotBlank(message = "La dirección fiscal es obligatoria")
    @Column(name = "direccion_fiscal", nullable = false, length = 200)
    private String direccionFiscal;

    @NotBlank(message = "El país es obligatorio")
    @Column(name = "pais", nullable = false, length = 50)
    private String pais;

    @OneToMany(mappedBy = "empresa")
    @OrderColumn(name = "id_vacante")
    private List<Vacante> listaVacantes = new ArrayList<>();
}