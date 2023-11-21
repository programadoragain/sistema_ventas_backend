package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import com.app.dev83.sistemaventas.Service.OrdenVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    private OrdenVentaService ordenVentaService;

    @PostMapping("/registrar")
    ResponseEntity<String> registrarVenta(@RequestBody Map<String, Object> requestMap) {
        try {
            return ResponseEntity.ok().body(ordenVentaService.registrar(requestMap));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OrdenVenta>> listarVentas() {
        try {
            return ResponseEntity.ok().body(ordenVentaService.listar());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Integer id) {
        try {
            ordenVentaService.eliminar(id);
            return ResponseEntity.ok().body(Constantes.SOLICITUD_EXITOSA);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

}
