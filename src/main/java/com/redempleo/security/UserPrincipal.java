package com.redempleo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redempleo.model.Perfil;
import com.redempleo.model.Usuario;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom UserDetails implementation that contains user information.
 */
@Getter
@Builder
public class UserPrincipal implements UserDetails {

    private String username;
    private String nombre;
    private String apellidos;
    private String email;

    @JsonIgnore
    private String password;

    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Create a UserPrincipal from a Usuario.
     *
     * @param usuario The Usuario entity
     * @return The UserPrincipal object
     */
    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getPerfiles().stream()
                .map(Perfil::getNombre)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return UserPrincipal.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .enabled(usuario.getEnabled() == 1)
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}