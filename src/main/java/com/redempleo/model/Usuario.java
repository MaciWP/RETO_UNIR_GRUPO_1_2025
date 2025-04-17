package com.redempleo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a user in the system.
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Column(name = "username", length = 45)
    private String username;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Email(message = "El formato del email no es válido")
    @NotBlank(message = "El email es obligatorio")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Integer enabled = 1;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UsuarioPerfil> usuariosPerfiles = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private List<Solicitud> listaSolicitudes = new ArrayList<>();

    /**
     * Convenience method to add a perfil to the usuario.
     *
     * @param perfil the perfil to add
     * @return the updated usuario
     */
    public Usuario addPerfil(Perfil perfil) {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(this, perfil);
        this.usuariosPerfiles.add(usuarioPerfil);
        perfil.getUsuariosPerfiles().add(usuarioPerfil);
        return this;
    }

    /**
     * Convenience method to add a perfil to the usuario with empresa relation.
     *
     * @param perfil the perfil to add
     * @param idEmpresa the empresa id
     * @return the updated usuario
     */
    public Usuario addPerfil(Perfil perfil, Integer idEmpresa) {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(this, perfil, idEmpresa);
        this.usuariosPerfiles.add(usuarioPerfil);
        perfil.getUsuariosPerfiles().add(usuarioPerfil);
        return this;
    }

    /**
     * Convenience method to remove a perfil from the usuario.
     *
     * @param perfil the perfil to remove
     * @return the updated usuario
     */
    public Usuario removePerfil(Perfil perfil) {
        this.usuariosPerfiles.removeIf(up -> up.getPerfil().equals(perfil));
        perfil.getUsuariosPerfiles().removeIf(up -> up.getUsuario().equals(this));
        return this;
    }

    /**
     * Get perfiles from usuario_perfil relationship.
     *
     * @return set of perfiles
     */
    @Transient
    public Set<Perfil> getPerfiles() {
        return this.usuariosPerfiles.stream()
                .map(UsuarioPerfil::getPerfil)
                .collect(Collectors.toSet());
    }

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDate.now();
    }
}