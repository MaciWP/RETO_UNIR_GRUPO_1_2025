package com.redempleo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Composite ID for UsuarioPerfil entity.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPerfilId implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "id_perfil")
    private Integer idPerfil;
}