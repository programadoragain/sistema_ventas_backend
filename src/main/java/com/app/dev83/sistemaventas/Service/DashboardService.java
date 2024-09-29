package com.app.dev83.sistemaventas.Service;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, String> cardVentas();
    Map<String, String> cardProductos();
    Map<String, String> cardGastos();
    Map<String, String> cardTotal();
    List<Map<String, String>> grafico();
}
