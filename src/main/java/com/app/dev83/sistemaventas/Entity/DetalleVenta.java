package com.app.dev83.sistemaventas.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "detalle_ventas")
@Data

public class DetalleVenta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Producto producto;

    private int cantidad;

    private float valor;

}
