package com.app.dev83.sistemaventas.Controller;

import com.app.dev83.sistemaventas.Constants.Constantes;
import com.app.dev83.sistemaventas.Service.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadistica")
public class EstadisticaController {

    @Autowired
    private EstadisticaService estadisticaService;

    @GetMapping("venta/cantidad-diaria")
    public ResponseEntity<String> cantidadVentaDiaria() {
        try {
            return ResponseEntity.accepted().body(estadisticaService.cantidadVentaDiaria());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Constantes.OCURRIO_UN_ERROR);
        }
    }

}
