package com.app.dev83.sistemaventas.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private LocalDateTime fechaCreacion;

    @ManyToOne
    private Usuario usuario;

    @OneToMany
    @Column(name = "detalle_ventas")
    private List<DetalleVenta> detalleVenta;

    /*
    @JsonCreator
    public OrdenVenta(@JsonProperty("valorTotal") String valorTotal,
                      @JsonProperty("metodoPago") String metodoPago,
                      @JsonProperty("detalleVenta") List<DetalleVenta> detalleVenta) {

        this.valorTotal = Float.parseFloat(valorTotal);
        this.metodoPago = MetodoPago.valueOf(metodoPago);
        this.detalleVenta = detalleVenta;
        this.id = null;
        this.fechaCreacion = null;
        this.usuario = null;
    }

    */

    public String getFechaCreacionConFormato() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return fechaCreacion.format(formatter);
    }

}
