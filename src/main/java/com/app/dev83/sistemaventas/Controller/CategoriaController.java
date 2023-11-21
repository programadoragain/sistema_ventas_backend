package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Dto.CategoriaDTO;
import com.app.dev83.sistemaventas.Entity.Categoria;
import com.app.dev83.sistemaventas.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.registrar(categoria));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.actualizar(categoria));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(categoriaService.eliminar(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDTO>> listar() {
        try {
            return ResponseEntity.ok().body(categoriaService.listar());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }
}
