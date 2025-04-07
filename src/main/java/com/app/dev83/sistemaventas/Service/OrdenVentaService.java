package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Dto.OrdenVentaDTO;
import com.app.dev83.sistemaventas.Entity.OrdenVenta;
import java.util.List;

public interface OrdenVentaService {

    String registrar(OrdenVenta venta);
    List<OrdenVentaDTO> listar();
    void eliminar(Integer id);

    /*
    List<OrdenVenta> listarOrdenesVenta();
    void guardarOrdenVenta(OrdenVenta ordenVenta);
    OrdenVenta buscarPorId(Integer id);
    List<OrdenVenta> buscarPorFecha(String fecha);
    List<OrdenVenta> buscarPorUsuario(Usuario usuario);
    String generarNumeroOrdenVenta();
    */
}
