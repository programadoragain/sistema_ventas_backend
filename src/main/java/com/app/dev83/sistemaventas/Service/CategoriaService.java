package com.app.dev83.sistemaventas.Service;

import com.app.dev83.sistemaventas.Dto.CategoriaDTO;
import com.app.dev83.sistemaventas.Entity.Categoria;
import com.app.dev83.sistemaventas.Entity.Producto;

import java.util.List;

public interface CategoriaService {
    String registrar(Categoria categoria);
    String actualizar(Categoria categoria);
    String eliminar(String id);
    List<CategoriaDTO> listar();
}
