package com.redempleo.service.impl;

import com.redempleo.exception.ResourceNotFoundException;
import com.redempleo.model.Perfil;
import com.redempleo.model.Usuario;
import com.redempleo.repository.PerfilRepository;
import com.redempleo.repository.UsuarioRepository;
import com.redempleo.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of UsuarioService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario save(Usuario usuario) {
        // Encode password before saving
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (usuario.getEnabled() == null) {
            usuario.setEnabled(1);
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(String username, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con username: " + username));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellidos(usuarioDetails.getApellidos());
        usuario.setEmail(usuarioDetails.getEmail());

        // Only update password if it's provided and not empty
        if (usuarioDetails.getPassword() != null && !usuarioDetails.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDetails.getPassword()));
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findById(username);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void deleteByUsername(String username) {
        usuarioRepository.deleteById(username);
    }

    @Override
    public Usuario disableUser(String username) {
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con username: " + username));

        usuario.setEnabled(0);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario enableUser(String username) {
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con username: " + username));

        usuario.setEnabled(1);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findByEnabled(Integer enabled) {
        return usuarioRepository.findByEnabled(enabled);
    }

    @Override
    public List<Usuario> findByPerfilId(Integer perfilId) {
        return usuarioRepository.findByPerfilId(perfilId);
    }

    @Override
    public List<Usuario> search(String query) {
        return usuarioRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(query, query);
    }

    @Override
    public Usuario addPerfil(String username, Integer perfilId) {
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con username: " + username));

        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id: " + perfilId));

        usuario.addPerfil(perfil);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario removePerfil(String username, Integer perfilId) {
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con username: " + username));

        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id: " + perfilId));

        usuario.removePerfil(perfil);
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsById(username);
    }
}