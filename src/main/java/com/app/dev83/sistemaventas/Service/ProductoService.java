package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Dto.ProductoDTO;
import com.app.dev83.sistemaventas.Entity.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ProductoService {

    List<ProductoDTO> listar();
    List<ProductoDTO> listarPorCategoria(String idCategoria);
    List<ProductoDTO> listarPorNombre(String nombre);
    List<ProductoDTO> listarEnStock();
    Producto obtenerPorId(String id);
    Producto registrar(Producto producto);
    String actualizar(Producto producto);
    String eliminar(String id);
    void restarStock(Integer id, Integer cantidad);
    void sumarStock(Integer id, Integer cantidad);
    long cantidadElementos();
    String uploadPhoto(String id, MultipartFile file) throws IOException;


}
