package com.app.dev83.sistemaventas.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dev83.sistemaventas.Constants.Constantes;
import static com.app.dev83.sistemaventas.Constants.Constantes.UPLOAD_DIRECTORY_USERS;
import com.app.dev83.sistemaventas.Entity.Usuario;
import com.app.dev83.sistemaventas.Service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Map<String,String> requestMap) {
        try {
            return ResponseEntity.ok().body(usuarioService.registrar(requestMap));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(new Usuario());
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

    @PutMapping("/upload-photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(usuarioService.uploadPhoto(id, file));
    }

    @GetMapping(value = "/get-photo", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto() throws IOException {
        String rutaImagen = UPLOAD_DIRECTORY_USERS + "\\" + usuarioService.usuarioActual().getImagen();

        try {
            Path path = Paths.get(rutaImagen);

            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (null);
    }

}
