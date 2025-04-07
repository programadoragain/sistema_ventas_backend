package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.app.dev83.sistemaventas.Entity.Gasto;
import com.app.dev83.sistemaventas.Service.GastoService;


@RestController
@RequestMapping("/api/gasto")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @PostMapping("/registrar")
    public ResponseEntity<Gasto> registrar(@RequestBody Gasto gasto) {
        try {
            return ResponseEntity.ok(gastoService.registrar(gasto));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Gasto>> listar() {
        try {
            return ResponseEntity.ok(gastoService.listar());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Gasto> obtenerPorId(@PathVariable String id) {
        try {
            Gasto gasto = gastoService.obtenerPorId(id);
            return gasto != null ? ResponseEntity.ok(gasto) : ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }



    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Gasto> actualizar(@RequestBody Gasto gasto) {
        try {
            Gasto gastoActualizado = gastoService.actualizar(gasto);
            return gastoActualizado != null ? ResponseEntity.ok(gastoActualizado) : ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(gastoService.eliminar(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }
}
