package com.app.dev83.sistemaventas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Producto producto;

    @NotNull
    private int cantidad;

    @NotNull
    private float valor;

}
