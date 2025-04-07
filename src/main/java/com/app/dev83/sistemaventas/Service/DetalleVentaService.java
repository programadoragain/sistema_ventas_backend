package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.DetalleVenta;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;

import java.util.List;

public interface DetalleVentaService {
    boolean registrar(List<DetalleVenta> detalles, OrdenVenta venta);
    /*
    DetalleVenta buscarDetalleVentaPorId(Integer id);
    DetalleVenta buscarDetalleVentaPorOrdenVenta(OrdenVenta venta);
    */
}