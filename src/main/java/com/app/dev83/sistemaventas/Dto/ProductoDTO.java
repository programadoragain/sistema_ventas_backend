package com.app.dev83.sistemaventas.Dto;

import com.app.dev83.sistemaventas.Entity.Categoria;
import com.app.dev83.sistemaventas.Entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Integer id;
    private String codBarra;
    private String nombre;
    private String marca;
    private String modelo;
    private String descripcion;
    private Categoria categoria;
    private Float valor;
    private int stock;
    private String imagen;
    private boolean activo;

    public static ProductoDTO toProductoDTO(Producto producto) {
        return new ProductoDTO(
                    producto.getId(),
                    producto.getCodBarra(),
                    producto.getNombre(),
                    producto.getMarca(),
                    producto.getModelo(),
                    producto.getDescripcion(),
                    producto.getCategoria(),
                    producto.getValor(),
                    producto.getStock(),
                    producto.getImagen(),
                    producto.isActivo()
                    );
    }

}
