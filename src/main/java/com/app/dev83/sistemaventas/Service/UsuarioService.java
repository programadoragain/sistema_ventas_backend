package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.Usuario;
import java.util.List;
import java.util.Map;

public interface UsuarioService {

    String registrarUsuario(Map<String,String> requestMap);
    String update(Map<String, String> requestMap);
    String login(Map<String, String> requestMap); // return token
    List<Usuario> listarUsuarios();
    String eliminarUsuario(String id);
    Usuario usuarioActual();

}
