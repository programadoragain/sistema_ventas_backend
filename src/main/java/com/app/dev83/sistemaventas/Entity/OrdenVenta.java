package com.app.dev83.sistemaventas.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orden_ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdenVenta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="valor_total")
    private Double valorTotal;

    @Column(name="fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name="detalle_ventas")
    @OneToMany
    private List<DetalleVenta> detalleVenta;
}
