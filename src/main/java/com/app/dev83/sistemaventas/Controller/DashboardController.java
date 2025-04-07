package com.app.dev83.sistemaventas.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dev83.sistemaventas.Dto.OrdenVentaDTO;
import com.app.dev83.sistemaventas.Service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dataWidget/ventas")
    public ResponseEntity<Map<String, String>> cardVentas() {
        try {
            return ResponseEntity.accepted().body(dashboardService.cardVentas());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

    @GetMapping("/dataWidget/gastos")
    public ResponseEntity<Map<String, String>> cardGastos() {
        try {
            return ResponseEntity.accepted().body(dashboardService.cardGastos());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

    @GetMapping("/dataWidget/productos/{fecha}")
    public ResponseEntity<Map<String, String>> cardProductos(@PathVariable("fecha") String fecha) {
        try {
            return ResponseEntity.accepted().body(dashboardService.cardProductos(fecha));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

    @GetMapping("/dataWidget/total")
    public ResponseEntity<Map<String, String>> cardTotal() {
        try {
            return ResponseEntity.accepted().body(dashboardService.cardTotal());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

    @GetMapping("/dataChart/grafico")
    public ResponseEntity<List<Map<String, String>>> grafico() {
        try {
            return ResponseEntity.accepted().body(dashboardService.grafico());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/dataChart/grafico-torta")
    public ResponseEntity<List<Map<String, Object>>> graficoTorta() {
        try {
            return ResponseEntity.accepted().body(dashboardService.graficoTorta1());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/dataSale/listarVentas/{fecha}")
    public ResponseEntity<List<OrdenVentaDTO>> listarVentaDia(@PathVariable("fecha") String fecha) {
        try {
            return ResponseEntity.accepted().body(dashboardService.listarVentaPorDia(fecha));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
