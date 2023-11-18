package com.app.dev83.sistemaventas.Entity;

import java.text.SimpleDateFormat;
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

    @Column(name = "valor_total")
    private Float valorTotal;

    @Column(name = "metodo_pago")
    private MetodoPago metodoPago;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany
    @Column(name = "detalle_ventas")
    private List<DetalleVenta> detalleVenta;

    public String getFechaCreacionConFormato() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY HH:mm");
        return formatter.format(fechaCreacion);
    }
}
