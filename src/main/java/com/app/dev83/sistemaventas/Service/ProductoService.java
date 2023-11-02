package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Entity.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> listarProductos();
    List<Producto> listarProductosPorCategoria(String idCategoria);
    List<Producto> listarProductosEnStock();
    Producto obtenerProductoPorId(String id);
    Producto guardarProducto(Producto producto);
    Producto actualizarProducto(Producto producto);
    void eliminarProducto(String id);

}
