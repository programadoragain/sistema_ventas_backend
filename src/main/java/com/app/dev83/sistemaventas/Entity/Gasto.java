package com.app.dev83.sistemaventas.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String categoria;

    private String nombreProveedor;

    @NotNull
    private String descripcion;

    @NotNull
    private Float monto;

    @NotNull
    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @NotNull
    @Column(name = "forma_pago")
    private String formaPago;
}
