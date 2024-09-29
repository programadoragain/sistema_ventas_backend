package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Dto.ProductoDTO;
import com.app.dev83.sistemaventas.Entity.Producto;
import com.app.dev83.sistemaventas.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.app.dev83.sistemaventas.Constants.Constantes.UPLOAD_DIRECTORY_PRODUCTS;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<Producto> registrar(@RequestBody Producto producto) {
        try {
            return ResponseEntity.ok().body(productoService.registrar(producto));

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new Producto());
        }
    }

    @PutMapping("/upload-photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(productoService.uploadPhoto(id, file));
    }

    @GetMapping(value = "/get-photo/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(UPLOAD_DIRECTORY_PRODUCTS + "\\" + filename));
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

    @CrossOrigin
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

    @GetMapping("/listarporbusqueda/{nombre}")
    public ResponseEntity<List<ProductoDTO>> listarPorNombre(@PathVariable("nombre") String nombre) {
        try {
            return ResponseEntity.ok().body(productoService.listarPorNombre(nombre));

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