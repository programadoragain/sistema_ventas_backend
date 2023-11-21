package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Entity.Rol;
import com.app.dev83.sistemaventas.Entity.Usuario;
import com.app.dev83.sistemaventas.Jwt.JwtFilter;
import com.app.dev83.sistemaventas.Jwt.JwtUtil;
import com.app.dev83.sistemaventas.Repository.UsuarioRepository;
import com.app.dev83.sistemaventas.Security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtFilter jwtFilter;

    public String registrar(Map<String, String> requestMap) {

        if (validarRegistroMap(requestMap)) {
            usuarioRepository.save(crearUsuario(requestMap));
            return Constantes.SOLICITUD_EXITOSA;
        }

        return Constantes.OCURRIO_UN_ERROR;
    }

    private Usuario crearUsuario(Map<String,String> requestMap) {
        Usuario usuario= new Usuario();
        usuario.setNombre(requestMap.get("nombre"));
        usuario.setApellido(requestMap.get("apellido"));
        usuario.setEmail(requestMap.get("email"));
        usuario.setPassword(passwordEncoder.encode(requestMap.get("password")));
        usuario.setRole(Rol.USER);
        usuario.setStatus("true");

        return usuario;
    }

    @Override
    public String login(Map<String, String> requestMap) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));

            if (authentication.isAuthenticated()) {
                if (customUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true"))
                    return "{\"token\":\"" + jwtUtil.generateToken(customUserDetailsService.getUserDetail().getEmail(), customUserDetailsService.getUserDetail().getRole().name()) + "\"}";
                else
                    return "Autenticación incorrecta";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Ocurrió un error de autenticación";
    }

    @Override
    public String update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Usuario> optUsuario= usuarioRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (optUsuario.isPresent()) {
                    //usuarioRepository.save(crearUsuario(requestMap));
                    return "Usuario actualizado";
                }
                return "El usuario no existe";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Ocurrió un problema al actualizar la información";
    }

    @Override
    public List<Usuario> listar() {
        try {
            if (jwtFilter.isAdmin())
                return usuarioRepository.findAll();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<Usuario>();
    }

    @Override
    public String eliminar(String id) {
        try {
            Optional<Usuario> optUsuario= usuarioRepository.findById(Integer.parseInt(id));
            if (optUsuario.isPresent()) {
                usuarioRepository.deleteById(Integer.parseInt(id));
                return "Se eliminó correctamente el usuario";
            } else
                return "El usuario no existe";

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Ocurrio un error al eliminar usuario";
        }
    }

    private boolean validarRegistroMap(Map<String,String> requestMap) {
        return (requestMap.containsKey("nombre") && requestMap.containsKey("apellido")
                && requestMap.containsKey("email") && requestMap.containsKey("password"));
    }

    public Usuario usuarioActual() {
        String usuario= jwtFilter.getCurrentUser();
        return usuarioRepository.findByEmail(usuario);
    }
}
