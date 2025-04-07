package com.app.dev83.sistemaventas.Service;

import java.util.List;
import java.util.Map;

import com.app.dev83.sistemaventas.Dto.OrdenVentaDTO;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;

public interface DashboardService {
    Map<String, String> cardVentas();
    Map<String, String> cardProductos(String fecha);
    Map<String, String> cardGastos();
    Map<String, String> cardTotal();
    List<Map<String, String>> grafico();
    List<Map<String, Object>> graficoTorta1();
    List<OrdenVentaDTO> listarVentaPorDia(String fecha);
}
