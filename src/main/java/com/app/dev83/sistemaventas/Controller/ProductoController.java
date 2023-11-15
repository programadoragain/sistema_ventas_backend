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

@Controller
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarProducto(@RequestBody Producto producto) {
        try {
            productoService.guardarProducto(producto);
            return ResponseEntity.ok().body(Constantes.SOLICITUD_EXITOSA);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
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
            productoService.actualizarProducto(producto);
            return ResponseEntity.ok().body(Constantes.SOLICITUD_EXITOSA);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") String id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok().body(Constantes.SOLICITUD_EXITOSA);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

}