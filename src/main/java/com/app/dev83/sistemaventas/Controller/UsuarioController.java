package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Entity.Usuario;
import com.app.dev83.sistemaventas.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(Map<String,String> requestMap) {
        try{
            usuarioService.registrarUsuario(requestMap);
            return ResponseEntity.ok().body(Constantes.SOLICITUD_EXITOSA);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return ResponseEntity.accepted().body(usuarioService.login(requestMap));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try{
            return ResponseEntity.ok().body(usuarioService.listarUsuarios());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarUsuario(Map<String, String> requestMap) {
        try{
            return ResponseEntity.ok().body(usuarioService.update(requestMap));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(usuarioService.eliminarUsuario(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

}
