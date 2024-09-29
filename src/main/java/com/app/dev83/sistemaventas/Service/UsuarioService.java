package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UsuarioService {

    String registrar(Map<String,String> requestMap);
    String update(Map<String, String> requestMap);
    String login(Map<String, String> requestMap); // return token
    List<Usuario> listar();
    String eliminar(String id);
    Usuario usuarioActual();
    String uploadPhoto(String id, MultipartFile file) throws IOException;

}
