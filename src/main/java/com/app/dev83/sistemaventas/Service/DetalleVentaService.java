package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Entity.Producto;

import java.util.List;

public interface DetalleVentaService {
    void registrarDetalleVenta(List<DetalleVenta> detalles, Object request);
    /*
    DetalleVenta buscarDetalleVentaPorId(Integer id);
    DetalleVenta buscarDetalleVentaPorOrdenVenta(OrdenVenta venta);
    */
}