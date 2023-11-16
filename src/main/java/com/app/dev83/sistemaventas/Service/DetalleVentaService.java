package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    void registrarDetalleVenta(List<DetalleVenta> detalles, Object requestMap);
    /*
    DetalleVenta buscarDetalleVentaPorId(Integer id);
    DetalleVenta buscarDetalleVentaPorOrdenVenta(OrdenVenta venta);
    */
}