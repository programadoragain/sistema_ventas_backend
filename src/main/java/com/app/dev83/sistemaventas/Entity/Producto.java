package com.app.dev83.sistemaventas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codBarra;

    @NotEmpty
    @Size(min = 1, max = 32)
    private String nombre;

    @NotEmpty
    @Size(min = 2, max = 32)
    private String marca;

    @Size(max = 15)
    private String modelo;

    private String descripcion;

    private String imagen; //Se almacena solo el nombre

    @NotNull
    @ManyToOne
    private Categoria categoria;

    private Moneda moneda;

    private Float valor;

    private int stock;

    private boolean activo;
}
