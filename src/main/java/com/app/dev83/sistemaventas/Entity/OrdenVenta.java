package com.app.dev83.sistemaventas.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orden_ventas")
public class OrdenVenta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private Float valorTotal;

    @Column(name = "metodo_pago")
    private MetodoPago metodoPago;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    private Usuario usuario;

    @OneToMany
    @Column(name = "detalle_ventas")
    private List<DetalleVenta> detalleVenta;

    public String getFechaCreacionConFormato() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY HH:mm");
        return formatter.format(fechaCreacion);
    }
}
