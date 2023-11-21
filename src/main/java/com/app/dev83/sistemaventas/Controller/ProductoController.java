package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Dto.ProductoDTO;
import com.app.dev83.sistemaventas.Entity.Producto;
import com.app.dev83.sistemaventas.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody Producto producto) {
        try {
            return ResponseEntity.ok().body(productoService.registrar(producto));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Producto producto) {
        try {
            return ResponseEntity.ok().body(productoService.actualizar(producto));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(productoService.eliminar(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDTO>> listar() {
        try {
            return ResponseEntity.ok().body(productoService.listar());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @GetMapping("/listarenstock")
    public ResponseEntity<List<ProductoDTO>> listarEnStock() {
        try {
            return ResponseEntity.ok().body(productoService.listarEnStock());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @GetMapping("/listarporcategoria/{id}")
    public ResponseEntity<List<ProductoDTO>> listarPoCategoria(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(productoService.listarPorCategoria(id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(ProductoDTO.toProductoDTO(productoService.obtenerPorId(id)));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

}