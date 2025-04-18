package com.redempleo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing the relationship between user and profile with additional attributes.
 */
@Entity
@Table(name = "usuario_perfil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPerfil {

    @EmbeddedId
    private UsuarioPerfilId id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idPerfil")
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @Column(name = "id_empresa")
    private Integer idEmpresa = 0;

    @Column(name = "comprobado")
    private Byte comprobado = 0;

    /**
     * Constructor with basic relationship.
     *
     * @param usuario the user
     * @param perfil the profile
     */
    public UsuarioPerfil(Usuario usuario, Perfil perfil) {
        this.id = new UsuarioPerfilId(usuario.getUsername(), perfil.getIdPerfil());
        this.usuario = usuario;
        this.perfil = perfil;
        this.idEmpresa = 0;
        this.comprobado = 0;
    }

    /**
     * Constructor with relationship to empresa.
     *
     * @param usuario the user
     * @param perfil the profile
     * @param idEmpresa the empresa id
     */
    public UsuarioPerfil(Usuario usuario, Perfil perfil, Integer idEmpresa) {
        this.id = new UsuarioPerfilId(usuario.getUsername(), perfil.getIdPerfil());
        this.usuario = usuario;
        this.perfil = perfil;
        this.idEmpresa = idEmpresa;
        this.comprobado = 0;
    }
}