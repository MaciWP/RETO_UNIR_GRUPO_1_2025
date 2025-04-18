package com.redempleo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a user profile (role) in the system.
 */
@Entity
@Table(name = "perfiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer idPerfil;

    @NotBlank(message = "El nombre del perfil es obligatorio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "perfil")
    private Set<UsuarioPerfil> usuariosPerfiles = new HashSet<>();

    /**
     * Constructor with perfil name.
     *
     * @param nombre the perfil name
     */
    public Perfil(String nombre) {
        this.nombre = nombre;
    }
}