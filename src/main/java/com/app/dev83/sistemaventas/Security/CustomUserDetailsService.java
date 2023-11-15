package com.app.dev83.sistemaventas.Security;

import com.app.dev83.sistemaventas.Entity.Usuario;
import com.app.dev83.sistemaventas.Repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

//@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // log.info("Inside loadUserByUsername {}", username);
        usuario= usuarioRepository.findByEmail(username);

        if (!Objects.isNull(usuario))
            return new org.springframework.security.core.userdetails.User(
                    usuario.getEmail(),
                    usuario.getPassword(),
                    new ArrayList<>());
        else
            throw new UsernameNotFoundException("Usuario no encontrado // Autenticación incorrecta");
    }

    public Usuario getUserDetail() {
        return usuario;
    }

}

