package com.app.dev83.sistemaventas.Dto;

import com.app.dev83.sistemaventas.Entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    Integer id;
    String nombre;

    public static CategoriaDTO toCategoriaDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNombre());
    }

}
