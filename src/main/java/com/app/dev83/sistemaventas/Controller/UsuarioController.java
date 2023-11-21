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

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Map<String,String> requestMap) {
        try {
            return ResponseEntity.ok().body(usuarioService.registrar(requestMap));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
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
            return ResponseEntity.ok().body(usuarioService.listar());

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
            return ResponseEntity.ok().body(usuarioService.eliminar(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

}
