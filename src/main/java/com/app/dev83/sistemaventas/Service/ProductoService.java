package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Dto.ProductoDTO;
import com.app.dev83.sistemaventas.Entity.Producto;
import java.util.List;

public interface ProductoService {

    List<ProductoDTO> listar();
    List<ProductoDTO> listarPorCategoria(String idCategoria);
    List<ProductoDTO> listarEnStock();
    Producto obtenerPorId(String id);
    String registrar(Producto producto);
    String actualizar(Producto producto);
    String eliminar(String id);
    void restarStock(Integer id, Integer cantidad);
    void sumarStock(Integer id, Integer cantidad);
}
