package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Entity.Producto;
import com.app.dev83.sistemaventas.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarProducto(@RequestBody Producto producto) {
        try {
            return ResponseEntity.ok().body(productoService.registrarProducto(producto));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos() {
        try {
            return ResponseEntity.ok().body(productoService.listarProductos());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(productoService.obtenerProductoPorId(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Producto());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarProducto(@RequestBody Producto producto) {
        try {
            return ResponseEntity.ok().body(productoService.actualizarProducto(producto));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(productoService.eliminarProducto(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

}