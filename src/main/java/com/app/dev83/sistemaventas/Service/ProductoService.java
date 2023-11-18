package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.Producto;
import java.util.List;

public interface ProductoService {

    List<Producto> listarProductos();
    List<Producto> listarProductosPorCategoria(String idCategoria);
    List<Producto> listarProductosEnStock();
    Producto obtenerProductoPorId(String id);
    String registrarProducto(Producto producto);
    String actualizarProducto(Producto producto);
    String eliminarProducto(String id);
    void restarStock(Integer id, Integer cantidad);
    void sumarStock(Integer id, Integer cantidad);
}
