package com.test.lifipa.security.service;

import com.test.lifipa.model.Usuario;
import com.test.lifipa.repository.UsuarioRepository;
import com.test.lifipa.security.model.DetalleUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDetalleService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        return usuario.map(DetalleUsuario::new).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado - " + username));
    }
}
