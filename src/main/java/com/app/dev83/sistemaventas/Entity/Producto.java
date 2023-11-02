package com.app.dev83.sistemaventas.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;
    private String codigoBarra;
    private String descripcion;

    @NotNull
    private String marca;

    private String modelo;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @Column(name = "valor_usd")
    private Double valorUsd;

    @NotNull
    private TipoDeCambio tipoDeCambio;

    @NotNull
    private int stock;

    private boolean activo;
}
